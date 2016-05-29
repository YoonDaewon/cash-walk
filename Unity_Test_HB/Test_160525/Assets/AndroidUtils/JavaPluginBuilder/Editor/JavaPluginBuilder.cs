using UnityEngine;
using UnityEditor;
using System.Collections;
using System.Collections.Generic;
using System.IO;

using Process = System.Diagnostics.Process;
using ProcessInfo = System.Diagnostics.ProcessStartInfo;
using Debug = UnityEngine.Debug;

public static class Folders
{
	public static string AndroidSDKPath
	{
		get { 
			return EditorPrefs.GetString("AndroidSdkRoot"); 
		}
	}

	public static string JavaJDKPath
	{
		get { 
			return System.Environment.GetEnvironmentVariable("JAVA_HOME") + "/bin/" ;
		}
	}

	public static string BootClasspath
	{
		get {
			string androidSDK = Folders.AndroidSDKPath;
			string[] sourceFiles = Directory.GetFiles (androidSDK, "android.jar", SearchOption.AllDirectories);
			
			List<string> list = new List<string> (sourceFiles);
			list.Sort (
				delegate(string s1, string s2)
				{
				int c = s1.Length - s2.Length;
				if (c == 0)
				{
					return s1.CompareTo(s2);
				}
				return c;
			}
			);
			
			if (list.Count <= 0) return null;
	        
	        string androidjar = list[list.Count - 1];
	        return androidjar;
		}
    }
}

public class JavaPluginBuilder
{
	[MenuItem ("Assets/Build Java Project", true)]
	static bool ValidateBuild () 
	{
		return Selection.activeObject is JavaProjectSettings;
	}

	[MenuItem ("Assets/Build Java Project")]
	public static void Build ()
	{
		JavaProjectSettings settings = Selection.activeObject as JavaProjectSettings;

		new JavaPluginBuilder (settings).DoBuild ();
	}

	#if UNITY_EDITOR_WIN
	const string cp_separator = ";";
	#else
	const string cp_separator = ":";
    #endif

	string bootclasspath;
	string jarFileName;
	string javajdk;
	string outputDir;
	string workingDirectory;
	string projectDir;

	string[] sourceFiles;
	string[] jarFiles;

	string binDir;

	public JavaPluginBuilder(JavaProjectSettings settings)
	{
		string path = AssetDatabase.GetAssetPath (settings);

		bootclasspath = Folders.BootClasspath;
		jarFileName = settings.name+".jar";
		javajdk = Folders.JavaJDKPath;
		outputDir = settings.output_dir;

		workingDirectory = Application.dataPath;
		workingDirectory = workingDirectory.Substring (0, workingDirectory.Length - "Assets/".Length) + "/" + path;
		workingDirectory = Path.GetDirectoryName (workingDirectory);

		projectDir = Application.dataPath;

		sourceFiles = Directory.GetFiles (workingDirectory, "*.java", SearchOption.AllDirectories);
		jarFiles = settings.classpath;

		binDir = projectDir + "/" + outputDir + "/bin-temp/";
	}

	public void DoBuild()
	{
		if (!PathsOK()) return;
		
		SetupOutputDir ();
		
		RunCommands ();
		
		CleanupOutputDir ();       
		
		AssetDatabase.Refresh ();
    }
    
    private bool PathsOK()
    {
        if (sourceFiles.Length <= 0)
		{
			EditorUtility.DisplayDialog (
				"Error with Java Project",
				"No .java files to compile under "+workingDirectory,
				"OK"
				);
			return false;
		}
		
		if (string.IsNullOrEmpty(Folders.AndroidSDKPath))
		{
			EditorUtility.DisplayDialog (
				"Error with Android requirements",
				"Android SDK not set up correctly in Unity or not installed",
				"OK"
				);
			return false;
		}
		
		if (string.IsNullOrEmpty(javajdk))
		{
			EditorUtility.DisplayDialog (
				"Error with Java requirements",
				"JAVA_HOME not set up correctly.",
				"OK"
				);
            return false;
        }
        
        if (string.IsNullOrEmpty(bootclasspath))
        {
            EditorUtility.DisplayDialog (
                "Error with Android requirements",
                "Android.jar not found under Android sdk path: "+Folders.AndroidSDKPath + " Be sure you have installed at least one platform",
                "OK"
                );
            
            return false;
        }

		return true;
	}

	private void SetupOutputDir()
	{
		if (Directory.Exists(binDir))
			Directory.Delete (binDir, true);
		Directory.CreateDirectory (binDir);
	}

	private void CleanupOutputDir()
	{
		Directory.Delete (binDir, true);
    }

	private bool RunCommands()
	{
		ProcessInfo startInfo = compileCommand ();
		Debug.Log (startInfo.WorkingDirectory +"\n"+ startInfo.FileName +"\n"+ startInfo.Arguments);

		string error = RunBuildProcess (startInfo);
		if (error != null)
		{
			Debug.LogError (error);

			EditorUtility.DisplayDialog (
				"Error compiling java",
				"Check out debug console for errors",
				"OK"
				);
			return false;
		}

		startInfo = jarCommand ();
		Debug.Log (startInfo.WorkingDirectory +"\n"+ startInfo.FileName +"\n"+ startInfo.Arguments);

		error = RunBuildProcess (startInfo);
		if (error != null)
		{
			Debug.LogError ("Error building jar: "+ error);

			EditorUtility.DisplayDialog (
				"Error Building java",
				"Check out debug console for errors",
				"OK"
				);
            
            return false;
        }

		return true;
    }

	private ProcessInfo compileCommand()
	{
		string args = "\"" + string.Join ("\" \"", sourceFiles) + "\"";
		
		if (bootclasspath != null) args += " -bootclasspath \"" + bootclasspath + "\"";
		
		args += " -classpath \"" + EditorApplication.applicationContentsPath + "/PlaybackEngines/AndroidPlayer/bin/classes.jar";
		if (jarFiles != null && jarFiles.Length >= 1) args += cp_separator + string.Join (cp_separator, jarFiles);
		args += "\"";
		
		args += " -d " + binDir;
		
		ProcessInfo pInfo = new ProcessInfo ()
		{
			WorkingDirectory = workingDirectory,
			FileName = Path.Combine (javajdk, "javac"),
            Arguments = args,
            UseShellExecute = false,
            RedirectStandardError = true,
		};

		return pInfo;
	}

	private ProcessInfo jarCommand()
	{
		string args = "cvfM " +
			Path.Combine ( projectDir + "/" + outputDir, jarFileName) + 
			" " + "-C " + binDir + 
			" " + "./";
        
		ProcessInfo pInfo = new ProcessInfo ()
		{
            WorkingDirectory = workingDirectory,
			FileName = Path.Combine (javajdk, "jar"),
			Arguments = args,
			UseShellExecute = false,
			RedirectStandardError = true,
        };

		return pInfo;
	}

	static string RunBuildProcess (ProcessInfo pInfo)
	{
		Process process = Process.Start (pInfo);
		process.WaitForExit ();

		if (!process.StandardError.EndOfStream)
		{
			return process.StandardError.ReadToEnd ();
		}

		return null;
	}
}
