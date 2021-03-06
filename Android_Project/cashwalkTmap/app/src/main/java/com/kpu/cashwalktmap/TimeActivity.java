package com.kpu.cashwalktmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.Tmap.BizCategory;
import com.skp.Tmap.MapUtils;
import com.skp.Tmap.TMapCircle;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapData.BizCategoryListenerCallback;
import com.skp.Tmap.TMapData.ConvertGPSToAddressListenerCallback;
import com.skp.Tmap.TMapData.FindAllPOIListenerCallback;
import com.skp.Tmap.TMapData.FindAroundNamePOIListenerCallback;
import com.skp.Tmap.TMapData.FindPathDataAllListenerCallback;
import com.skp.Tmap.TMapData.FindPathDataListenerCallback;
import com.skp.Tmap.TMapData.TMapPathType;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapGpsManager.onLocationChangedCallback;
import com.skp.Tmap.TMapInfo;
import com.skp.Tmap.TMapLabelInfo;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapMarkerItem2;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapPolygon;
import com.skp.Tmap.TMapTapi;
import com.skp.Tmap.TMapView;
import com.skp.Tmap.TMapView.MapCaptureImageListenerCallback;
import com.skp.Tmap.TMapView.TMapLogoPositon;

public class TimeActivity extends BaseActivity2 implements onLocationChangedCallback
{


    LinearLayout scroll;
    LinearLayout scrollMenu;
    boolean dragFlag = false;
    int width;
    boolean checkarrive = false;


    //거리값 받아오려고 만든 전역변수
    private  double realdistance=0;
    private static long WORKINGSPEED=12;

    //타이머를 위한 변수
    TextView myOutput;
    TextView myRec;
    Button myBtnStart;
    Button myBtnRec;


    //버튼들

    Button myZoomIn;
    Button myZoomOut;
    Button myRemoveMapPath;
    Button mySetTracking;
    Button myZoomLevel;
    Button myVison;
    Button myCompass;
    Button myZoomSetting;
    Button myDistance;

    static boolean flag = false;

    final static int Init =0;
    final static int Run =1;
    final static int Pause =2;
    final static int ready = 3;

    int cur_Status = Init; //현재의 상태를 저장할변수를 초기화함.
    int myCount=1;
    long myBaseTime;
    long myPauseTime;


    @Override
    public void onLocationChange(Location location) {
        LogManager.printLog("onLocationChange " + location.getLatitude() +  " " + location.getLongitude() + " " + location.getSpeed() + " " + location.getAccuracy());
        if(m_bTrackingMode) {
            mMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        }
        TMapPoint m_point = mMapView.getLocationPoint();
        if(checkarrive)
        {
            checkArrive(m_point,g_Point);
        }

        if(checkGoal){
            // 변수 설정
            kcal = (weight*g_Distance)/1000;
            String value1 = String.format("%.0f", kcal);
            String value2 = String.format("%.0f", g_Distance);

            String strMessage = "적립되었습니다.\n소모 칼로리 : " + value1 + "\n총 이동거리 : " + value2 + "\n소요시간 : ";
            Common.showAlertDialog(TimeActivity.this, " ", strMessage);
            checkGoal = false;
        }
    }


    private TMapView		mMapView = null;
    private Context 		mContext;

    public static String mApiKey = "ffe26ff2-23c0-306d-8b07-337866842f14"; // 발급받은 appKey
    public static String mBizAppID; // 발급받은 BizAppID (TMapTapi로 TMap앱 연동을 할 때 BizAppID 꼭 필요)

    private static final int[] mArrayMapButton = {

            R.id.btnZoomIn2,
            R.id.btnZoomOut2,
            R.id.btnGetZoomLevel,
            R.id.btnsetZoomLevel,
            R.id.btnSetCompassMode,
            R.id.btnSetSightVisible,
            R.id.btnSetTrackIngMode2,
            R.id.btnSetSightVisible,

            R.id.btnRemoveMapPath2,
            R.id.GetDistance
    };




    private 	int 		m_nCurrentZoomLevel = 0;
    private 	double 		m_Latitude  = 0;
    private    double  	m_Longitude = 0;
    // 목적지 좌표 저장 위한
    private double g_Latitude = 0;
    private double g_Longitude = 0;

    // 현재 위치와 목표 위치를 저장하기 위한point 변수

    private TMapPoint s_Point;
    private TMapPoint g_Point;
    private TMapPoint T_Point;

    // 도착 알람을 위한 오차범위 설정
    private double radius = 20;

    // 노래 재생을 위한 전역변수 설정
    private SoundPool mSoundPool;
    private int streamid;
    private int soundid;

    // 칼로리 계산 및 거리 계산을 위한 전역 변수
    private double g_Distance = 0;
    private double weight = 70;
    private double kcal = 0;

    private 	boolean 	m_bShowMapIcon = true;    // 자기 위치 아이콘 표시

    private 	boolean 	checkGoal = false;
    private 	boolean 	m_bSightVisible = false;
    private 	boolean 	m_bTrackingMode = true;   // 추적 모드 on
    private    boolean   drawPath = false;
    private    boolean    m_bOverlayMode = false;

    ArrayList<String>      mArrayID;

    ArrayList<String>      mArrayCircleID;
    private static    int    mCircleID;

    ArrayList<String>      mArrayLineID;
    private static    int    mLineID;

    ArrayList<String>      mArrayPolygonID;
    private static  int    mPolygonID;

    ArrayList<String>       mArrayMarkerID;
    private static int       mMarkerID;

    TMapGpsManager gps = null;

    /**
     * onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 권한 설정
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        checkLocationPermission1();
        checkLocationPermission2();


        myOutput = (TextView) findViewById(R.id.time_out);
        myRec = (TextView) findViewById(R.id.record);
        myBtnStart = (Button) findViewById(R.id.btn_start);
        myBtnRec = (Button) findViewById(R.id.btn_rec);





        setContentView(R.layout.activity_main2);





        DisplayMetrics dm = getApplication().getResources().getDisplayMetrics();
        width = dm.widthPixels; //사용하는 디스플레이의 넓이를 가지고온다.
        scrollMenu = (LinearLayout) findViewById(R.id.scrollMenu); //드래그시 움직여야할 레이아웃
        scroll = (LinearLayout) findViewById(R.id.scrollButton); // 드래그를 할 레이아웃
        scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        dragFlag = true;
                        float movePoint = scrollMenu.getX() + event.getX();
                        //스크롤이 레이아웃 범위내에서만 이동하도록 지정
                        if (movePoint < width - scroll.getWidth() && movePoint > width - scrollMenu.getWidth())
                            scrollMenu.setX(movePoint);
                        return true;

                    case MotionEvent.ACTION_UP:
                        if (dragFlag) { //드래그를 하다가 터치를 실행
                            if (scrollMenu.getX() < width - scrollMenu.getWidth() / 2) { //열린다
                                while (scrollMenu.getX() > width - scrollMenu.getWidth()) {
                                    scrollMenu.setX(scrollMenu.getX() - 0.01f);
                                }
                            } else { //닫힌다.
                                scrollMenu.setX(width - scroll.getWidth());
                                while (scrollMenu.getX() < width - scroll.getWidth()) {
                                    scrollMenu.setX(scrollMenu.getX() + 0.01f);
                                }
                            }
                        }
                        dragFlag = false;
                        return false;
                }
                return true;
            }
        });

        mContext = this;

        mMapView = new TMapView(this);
        addView(mMapView);

        configureMapView();

        initView();

        mArrayID = new ArrayList<String>();

        mArrayCircleID = new ArrayList<String>();
        mCircleID = 0;

        mArrayLineID = new ArrayList<String>();
        mLineID = 0;

        mArrayPolygonID = new ArrayList<String>();
        mPolygonID = 0;

        mArrayMarkerID   = new ArrayList<String>();
        mMarkerID = 0;

        gps = new TMapGpsManager(TimeActivity.this);

        gps.setMinTime(500);
        gps.setMinDistance(5);
        gps.setProvider(gps.GPS_PROVIDER);
        gps.OpenGps();

        mMapView.setTMapLogoPosition(TMapLogoPositon.POSITION_BOTTOMRIGHT);

        // 액티비티가 만들어지면서 트래킹모드 트루로.
        setTrackingMode();

        // 초기 Zoom Level 17로 세팅
        mMapView.setZoomLevel(17);

        // 자기 위치 아이콘 표시
        setMapIcon();

        //Pool 생성
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        // 사운드 로드
        soundid = mSoundPool.load(this, R.raw.track5, 1);
        // 사운드 재생
        //streamid = mSoundPool.play(soundid, 1.0f, 1.0f, 1, -1, 1.0f);
    }
    /**
     * setSKPMapApiKey()에 ApiKey를 입력 한다.
     * setSKPMapBizappId()에 mBizAppID를 입력한다.
     * -> setSKPMapBizappId는 TMapTapi(TMap앱 연동)를 사용할때 BizAppID 설정 해야 한다. TMapTapi 사용하지 않는다면 setSKPMapBizappId를 하지 않아도 된다.
     */
    private void configureMapView() {
        mMapView.setSKPMapApiKey(mApiKey);
        //mMapView.setSKPMapBizappId(mBizAppID);
    }

    /**
     * initView - 버튼에 대한 리스너를 등록한다.
     */
    private void initView() {


        myZoomIn = (Button)findViewById(R.id.btnZoomIn2);
        myZoomOut = (Button)findViewById(R.id.btnZoomOut2);
        myRemoveMapPath = (Button)findViewById(R.id.btnRemoveMapPath2);
        mySetTracking = (Button)findViewById(R.id.btnSetTrackIngMode2);

        myZoomLevel= (Button)findViewById(R.id.btnGetZoomLevel);
        myVison= (Button)findViewById(R.id.btnSetSightVisible);
        myCompass= (Button)findViewById(R.id.btnSetCompassMode);
        myZoomSetting= (Button)findViewById(R.id.btnsetZoomLevel);
        myDistance= (Button)findViewById(R.id.GetDistance);


        mMapView.setOnApiKeyListener(new TMapView.OnApiKeyListenerCallback() {
            @Override
            public void SKPMapApikeySucceed() {
                LogManager.printLog("TimeActivity SKPMapApikeySucceed");
            }

            @Override
            public void SKPMapApikeyFailed(String errorMsg) {
                LogManager.printLog("TimeActivity SKPMapApikeyFailed " + errorMsg);
            }
        });

        mMapView.setOnEnableScrollWithZoomLevelListener(new TMapView.OnEnableScrollWithZoomLevelCallback() {
            @Override
            public void onEnableScrollWithZoomLevelEvent(float zoom, TMapPoint centerPoint) {
                LogManager.printLog("TimeActivity onEnableScrollWithZoomLevelEvent " + zoom + " " + centerPoint.getLatitude() + " " + centerPoint.getLongitude());
            }
        });

        mMapView.setOnDisableScrollWithZoomLevelListener(new TMapView.OnDisableScrollWithZoomLevelCallback() {
            @Override
            public void onDisableScrollWithZoomLevelEvent(float zoom, TMapPoint centerPoint) {
                LogManager.printLog("TimeActivity onDisableScrollWithZoomLevelEvent " + zoom + " " + centerPoint.getLatitude() + " " + centerPoint.getLongitude());
            }
        });

        mMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point, PointF pointf) {
                LogManager.printLog("TimeActivity onPressUpEvent " + markerlist.size());

                return false;
            }

            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point, PointF pointf) {
                LogManager.printLog("TimeActivity onPressEvent " + markerlist.size());

                for (int i = 0; i < markerlist.size(); i++) {
                    TMapMarkerItem item = markerlist.get(i);
                    LogManager.printLog("TimeActivity onPressEvent " + item.getName() + " " + item.getTMapPoint().getLatitude() + " " + item.getTMapPoint().getLongitude());
                }
                return false;
            }
        });

        mMapView.setOnLongClickListenerCallback(new TMapView.OnLongClickListenerCallback() {
            @Override
            public void onLongPressEvent(ArrayList<TMapMarkerItem> markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point) {
                LogManager.printLog("TimeActivity onLongPressEvent " + markerlist.size());;
                g_Latitude = point.getLatitude();
                g_Longitude = point.getLongitude();
                checkarrive = true;

                // 목적지 위치를 g_Point에 저장
                g_Point = point;
                // 현재 위치를 s_Point 전역 변수에 저장
                s_Point = mMapView.getLocationPoint();

                double Distance = MapUtils.getDistance(s_Point,g_Point);

                // 출력을 위한 변수값들 설정
                g_Distance = Distance;

                // 롱 클릭 리스너에서 경로는 바로 그림
                drawCashPath(s_Point,g_Point);


                //String strResult = String.format("목적지\nLatitude = %f\nLongitude = %f\n직선거리 = %.0f m\n경로거리 =%f", g_Latitude, g_Longitude,Distance,realdistance);
                String strResult = String.format("목적지 설정이 완료되었습니다.\n거리를 확인하려면 버튼을 눌러주세요");


                // 경로 그리는 트리거 on
                // drawPath = true;

                Common.showAlertDialog(TimeActivity.this, " ",strResult);


            }
        });

        mMapView.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem markerItem) {
                String strMessage = "";
                strMessage = "ID: " + markerItem.getID() + " " + "Title " + markerItem.getCalloutTitle();
                Common.showAlertDialog(TimeActivity.this, "Callout Right Button", strMessage);
            }
        });

        mMapView.setOnClickReverseLabelListener(new TMapView.OnClickReverseLabelListenerCallback() {
            @Override
            public void onClickReverseLabelEvent(TMapLabelInfo findReverseLabel) {
                if(findReverseLabel != null) {
                    LogManager.printLog("TimeActivity setOnClickReverseLabelListener " + findReverseLabel.id + " / " + findReverseLabel.labelLat
                            + " / " + findReverseLabel.labelLon + " / " + findReverseLabel.labelName);

                }
            }
        });

        m_nCurrentZoomLevel = -1;
        m_bShowMapIcon = true;
        m_bSightVisible = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //streamid = mSoundPool.play(soundid, 1.0f, 1.0f, 1, -1, 1.0f);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 화면 전환시 노래 종료
       // mSoundPool.stop(streamid);
    }

    @Override
    protected void onDestroy() {

        gps.CloseGps();
        myTimer.removeMessages(0);
        super.onDestroy();
    }

    /**
     * onClick Event
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnZoomIn			  : 	mapZoomIn(); 			break;
            case R.id.btnZoomOut		  : 	mapZoomOut(); 			break;
            case R.id.btnGetZoomLevel	  :  	getZoomLevel(); 		break;
            case R.id.btnsetZoomLevel	  :  	setZoomLevel(); 		break;
            case R.id.btnSetCompassMode	  : 	setCompassMode();		break;
            case R.id.btnSetSightVisible  : 	setSightVisible();		break;
            case R.id.btnSetTrackIngMode  : 	setTrackingMode();		break;
            case R.id.btnRemoveMapPath    :     removeMapPath(); 		break;
            case R.id.GetDistance   : DistanceShow();   break;

        }
    }
    // 랜덤한 위치 찍어주는
    public TMapPoint randomTMapPoint() {
        double latitude = ((double)Math.random() ) * (37.575113-37.483086) + 37.483086;
        double longitude = ((double)Math.random() ) * (127.027359-126.878357) + 126.878357;


        latitude = Math.min(37.575113, latitude);
        latitude = Math.max(37.483086, latitude);

        longitude = Math.min(127.027359, longitude);
        longitude = Math.max(126.878357, longitude);

        LogManager.printLog("randomTMapPoint" + latitude + " " + longitude);

        TMapPoint point = new TMapPoint(latitude, longitude);

        return point;
    }

    /**
     * mapZoomIn
     * 지도를 한단계 확대한다.
     */
    public void mapZoomIn() {
        mMapView.MapZoomIn();
    }

    /**
     * mapZoomOut
     * 지도를 한단계 축소한다.
     */
    public void mapZoomOut() {
        mMapView.MapZoomOut();
    }

    /**
     * getZoomLevel
     * 현재 줌의 레벨을 가지고 온다.
     */
    public void getZoomLevel() {
        int nCurrentZoomLevel = mMapView.getZoomLevel();
        Common.showAlertDialog(this, "", "현재 Zoom Level : " + Integer.toString(nCurrentZoomLevel));



    }


    //저장한 거리를 보여주는함수
    public  void DistanceShow() {
        String strResult = String.format("목적지\nLatitude = %f\nLongitude = %f\n직선거리 = %.0f m\n경로거리 =%f", g_Latitude, g_Longitude, g_Distance, realdistance);


        Common.showAlertDialog(TimeActivity.this, " ", strResult);

    }


    /**
     * setZoomLevel
     * Zoom Level을 설정한다.
     */
    public void setZoomLevel() {
        final String[] arrString = getResources().getStringArray(R.array.a_zoomlevel);
        AlertDialog dlg = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher)
                .setTitle("Select Zoom Level")
                .setSingleChoiceItems(R.array.a_zoomlevel, m_nCurrentZoomLevel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        m_nCurrentZoomLevel = item;
                        dialog.dismiss();
                        mMapView.setZoomLevel(Integer.parseInt(arrString[item]));
                    }
                }).show();
    }

    /**
     * getLocationPoint
     * 현재위치로 표시될 좌표의 위도, 경도를 반환한다.
     */
    public void getLocationPoint() {
        TMapPoint point = mMapView.getLocationPoint();

        double Latitude = point.getLatitude();
        double Longitude = point.getLongitude();

        m_Latitude  = Latitude;
        m_Longitude = Longitude;

        LogManager.printLog("Latitude " + Latitude + " Longitude " + Longitude);

        String strResult = String.format("Latitude = %f Longitude = %f", Latitude, Longitude);

        Common.showAlertDialog(this, "", strResult);
    }

    /**
     * setLocationPoint
     * 현재위치로 표시될 좌표의 위도,경도를 설정한다.
     */
    public void setLocationPoint() {
        double    Latitude  = 37.5077664;
        double  Longitude = 126.8805826;

        LogManager.printLog("setLocationPoint " + Latitude + " " + Longitude);

        // mMapView.setLocationPoint(Longitude, Latitude);
        TMapPoint point = randomTMapPoint();
        mMapView.setLocationPoint(point.getLongitude(), point.getLatitude());
        // mMapView.setCenterPoint(point.getLongitude(), point.getLatitude(), true);
    }

    /**
     * setMapIcon
     * 현재위치로 표시될 아이콘을 설정한다.
     */
    public void setMapIcon() {
        m_bShowMapIcon = true;

        if (m_bShowMapIcon) {
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.my_location);
            mMapView.setIcon(bitmap);
        }
        mMapView.setIconVisibility(m_bShowMapIcon);
    }

    /**
     * setCompassMode
     * 단말의 방항에 따라 움직이는 나침반모드로 설정한다.
     */
    public void setCompassMode() {
        mMapView.setCompassMode(!mMapView.getIsCompass());
    }

    /**
     * getIsCompass
     * 나침반모드의 사용여부를 반환한다.
     */
    public void getIsCompass() {
        Boolean bGetIsCompass = mMapView.getIsCompass();
        Common.showAlertDialog(this, "", "현재 나침반 모드는 : " + bGetIsCompass.toString());
    }

    /**
     * setSightVisible
     * 시야표출여부를 설정한다.
     */
    public void setSightVisible() {
        m_bSightVisible = !m_bSightVisible;
        mMapView.setSightVisible(m_bSightVisible);
    }

    /**
     * setTrackingMode
     * 화면중심을 단말의 현재위치로 이동시켜주는 트래킹모드로 설정한다.
     */
    public void setTrackingMode() {
        m_bTrackingMode = true;
        mMapView.setTrackingMode(m_bTrackingMode);
    }

    /**
     * getIsTracking
     * 트래킹모드의 사용여부를 반환한다.
     */
    public void getIsTracking() {
        Boolean bIsTracking = mMapView.getIsTracking();
        Common.showAlertDialog(this, "", "현재 트래킹모드 사용 여부  : " + bIsTracking.toString());
    }

    public void removeMarker() {
        if(mArrayMarkerID.size() <= 0 )
            return;

        String strMarkerID = mArrayMarkerID.get(mArrayMarkerID.size() - 1);
        mMapView.removeMarkerItem(strMarkerID);
        mArrayMarkerID.remove(mArrayMarkerID.size() - 1);
    }

    /**
     * drawMapPath
     * 지도에 시작-종료 점에 대해서 경로를 표시한다.
     */
    public void drawMapPath() {
        TMapPoint point1 = mMapView.getCenterPoint();
        TMapPoint point2 = randomTMapPoint();

        TMapData tmapdata = new TMapData();

        tmapdata.findPathData(point1, point2, new FindPathDataListenerCallback() {

            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                mMapView.addTMapPath(polyLine);
            }
        });
    }

    /**
     * removeMapPath
     * 경로 표시를 삭제한다.
     */
    public void removeMapPath() {
        mMapView.removeTMapPath();

    }

    // 경로 그리는 함수

    public void drawCashPath(TMapPoint point1, TMapPoint point2) {
        TMapData tmapdata = new TMapData();
        tmapdata.findPathDataWithType(TMapPathType.PEDESTRIAN_PATH, point1, point2, new FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                polyLine.setLineColor(Color.BLUE);
                mMapView.addTMapPath(polyLine);
                realdistance = polyLine.getDistance();


            }
        });
    }

    /**
     * getCenterPoint
     * 지도의 중심점을 가지고 온다.
     */
    public void getCenterPoint() {
        TMapPoint point = mMapView.getCenterPoint();

        Common.showAlertDialog(this, "", "지도의 중심 좌표는 " + point.getLatitude() + " " + point.getLongitude());
    }

    private boolean bZoomEnable = false;

    public void disableZoom() {
        bZoomEnable = !bZoomEnable;
        mMapView.setUserScrollZoomEnable(bZoomEnable);
    }

    // 권한 체크 위한 함수
    public boolean checkLocationPermission1()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public boolean checkLocationPermission2()
    {
        String permission = "android.permission.ACCESS_COARSE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    //도착 확인을 위한 함수
    public void checkArrive(TMapPoint point1, TMapPoint point2)
    {
        double distance = MapUtils.getDistance(point1,point2);
        if(distance <= radius)
            checkGoal =  true;


        String strid = String.format("%.0f", distance);
        Toast.makeText(this,"남은 거리 :" + strid + " m", Toast.LENGTH_LONG).show();
    }

    public void myOnClick(View v){
        switch(v.getId()){
            case R.id.btn_start: //시작버튼을 클릭했을때 현재 상태값에 따라 다른 동작을 할수있게끔 구현.
                switch(cur_Status){
                    case Init:
                        int i = ((int)(Math.random()*10))%6;
                        if(i==0) {
                            TMapPoint point = new TMapPoint(37.355140, 126.715236);
                            T_Point = point;//옥구
                        }
                        if(i==1) {
                            TMapPoint point2 = new TMapPoint(37.333041, 126.725578);
                            T_Point = point2;//소망공원
                        }
                        if(i==2) {
                            TMapPoint point3 = new TMapPoint(37.343464, 126.737638);
                            T_Point = point3;//49블럭
                        }
                        if(i==3) {
                            TMapPoint point = new TMapPoint(37.338236, 126.751070);
                            T_Point = point;//시화초등학교
                        }
                        if(i==4) {
                            TMapPoint point = new TMapPoint(37.344795, 126.748130);
                            T_Point = point;//정왕우체국
                        }
                        if(i==5) {
                            TMapPoint point = new TMapPoint(37.351840, 126.743045);
                            T_Point = point;//정왕역
                        }
                        if(i==6) {
                            TMapPoint point = new TMapPoint(37.336589, 126.727767);
                            T_Point = point;//센트럴병원
                        }
                        s_Point = mMapView.getLocationPoint();
                        drawCashPath(s_Point,T_Point);

                        myBaseTime = SystemClock.elapsedRealtime()+60000;
                        System.out.println(myBaseTime);
                        //myTimer이라는 핸들러를 빈 메세지를 보내서 호출
                        myTimer.sendEmptyMessage(0);

                        myBtnStart.setText("멈춤"); //버튼의 문자"시작"을 "멈춤"으로 변경
                        myBtnRec.setEnabled(true); //기록버튼 활성
                        cur_Status = Run; //현재상태를 런상태로 변경

                        break;
                    case Run:
                        myTimer.removeMessages(0); //핸들러 메세지 제거
                        myPauseTime = SystemClock.elapsedRealtime();
                        myBtnStart.setText("시작");
                        myBtnRec.setText("리셋");
                        cur_Status = Pause;
                        break;
                    case Pause:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now- myPauseTime);
                        myBtnStart.setText("멈춤");
                        myBtnRec.setText("기록");
                        cur_Status = Run;
                        break;


                }
                break;
            case R.id.btn_rec:
                switch(cur_Status){
                    case Run:

                        String str = myRec.getText().toString();
                        str +=  String.format("%d. %s\n",myCount,getTimeOut());
                        myRec.setText(str);
                        myCount++; //카운트 증가

                        break;
                    case Pause:
                        //핸들러를 멈춤
                        myTimer.removeMessages(0);

                        myBtnStart.setText("시작");
                        myBtnRec.setText("기록");
                        myOutput.setText("00:00:00");

                        cur_Status = Init;
                        myCount = 1;
                        myRec.setText("");
                        myBtnRec.setEnabled(false);
                        break;


                }
                break;


            case R.id.btnZoomIn2			  : 	mapZoomIn(); 			break;
            case R.id.btnZoomOut2		  : 	mapZoomOut(); 			break;
            case R.id.btnGetZoomLevel	  :  	getZoomLevel(); 		break;
            case R.id.btnsetZoomLevel	  :  	setZoomLevel(); 		break;
            case R.id.btnSetCompassMode	  : 	setCompassMode();		break;
            case R.id.btnSetSightVisible  : 	setSightVisible();		break;
            case R.id.btnSetTrackIngMode2  : 	setTrackingMode();		break;
            case R.id.btnRemoveMapPath2    :     removeMapPath(); 		break;
            case R.id.GetDistance   : DistanceShow();   break;


        }
    }

    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){

            myOutput.setText(getTimeOut());

            //sendEmptyMessage 는 비어있는 메세지를 Handler 에게 전송하는겁니다.
            myTimer.sendEmptyMessage(0);
        }
    };
    //현재시간을 계속 구해서 출력하는 메소드
    String getTimeOut(){
        long now = SystemClock.elapsedRealtime(); //애플리케이션이 실행되고나서 실제로 경과된 시간(??)^^;
        long outTime = myBaseTime - now;
        String easy_outTime = String.format("%02d:%02d:%02d", outTime/1000 / 60, (outTime/1000)%60,(outTime%1000)/10);
        return easy_outTime;

    }


}
