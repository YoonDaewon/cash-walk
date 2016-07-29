package com.example.yoon.cashwalknm;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

public class MainActivity extends NMapActivity {

    private NMapView mMapView;
    private NMapController mMapController;
    private static final String CLIENT_ID = "jLSZMsTP7rso3Qlz80_X";
    private static final String LOG_TAG = "Cash Walk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create map
        mMapView = new NMapView(this);

        //set Clients ID
        mMapView.setClientId(CLIENT_ID);

        //set the acticity content to map view
        setContentView(mMapView);

        // initialize map view
        mMapView.setClickable(true);

        // 맵 상태 변환을 위한 리스너 등록
        // 지도 초기화가 완료되면 아래의 콜백 인터페이스가 호출됨
        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);

        //맵 확대 등을 이용하기 위한 컨트롤러 세팅
        mMapController = mMapView.getMapController();

        // 지도 줌을 위한 내장컨트롤로 사용 설정
        mMapView.setBuiltInZoomControls(true, null);
    }

    // 예외 처리를 위한
    public void onMapInitHandler(NMapView mapView, NMapError errorInfo){
        if(errorInfo == null) { //  success
            mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 11);
        } else {    //  fali
            Log.e(LOG_TAG, "onMapInitHandler : error =" + errorInfo.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
