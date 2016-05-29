using UnityEngine;
using UnityEditor;
using System.Collections;

[System.Serializable]
public class JavaProjectSettings : ScriptableObject
{
	public string[] classpath;
	public string output_dir = "Plugins\\Android";

	[MenuItem ("Assets/Create Java Project", true)]
	static bool ValidateCreateJavaProjectFile () 
	{
		string path = AssetDatabase.GetAssetPath (Selection.activeObject);

		if (path.StartsWith("Assets/Plugins/Android"))
			return false;

		if (! System.IO.Directory.Exists(path))
			return false;
		
		return true;
	}

	[MenuItem ("Assets/Create Java Project")]
	public static void CreateJavaProjectFile ()
	{
		string path = AssetDatabase.GetAssetPath (Selection.activeObject);

		DoCreateProject (path + "/JavaProject.asset");
	}

	private static void DoCreateProject(string assetFile)
	{
		var projectAsset = (JavaProjectSettings)AssetDatabase.LoadAssetAtPath (assetFile, typeof (JavaProjectSettings));

		if (projectAsset != null)
		{
			Selection.activeObject = projectAsset;

			return;
		}

		projectAsset = ScriptableObject.CreateInstance<JavaProjectSettings> ();

		string fn = AssetDatabase.GenerateUniqueAssetPath(assetFile);

		AssetDatabase.CreateAsset(projectAsset, fn);
		
		Selection.activeObject = projectAsset;
		EditorUtility.FocusProjectWindow();
	}
}

