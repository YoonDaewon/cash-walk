package com.example.yoon.newgps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private SensorManager mSensorManager;

    private boolean mCompassEnabled;
    private static final int REQUEST_CODE_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 지도 객체 참조
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //위치확인하며 표시 시작
        startLocationService();
    }

    @Override
    public void onResume(){
        super.onResume();

        // 내 위치 자동 표시
        // 사용권한 체크
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //권한이 없을경우
            // 최초 권한 요청인지, 혹은 사용자에 의한 재요청인지 확인
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                // 사용자가 임의로 권한을 취소시킨 경우
                // 권한 재요청
                ActivityCompat.requestPermissions(this,new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION);

                Toast.makeText(this,"권한 설정 필요", Toast.LENGTH_SHORT).show();
            }
            else {
                // 최초로 권한을 요청하는 경우 ( 첫실행)
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            }
        }
        else {
            //사용 권한이 있음을 확인한 경우
            mMap.setMyLocationEnabled(true);
        }
        if(mCompassEnabled){
            mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public  void onPause(){
        super.onPause();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //권한이 없을경우
            // 최초 권한 요청인지, 혹은 사용자에 의한 재요청인지 확인
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                // 사용자가 임의로 권한을 취소시킨 경우
                // 권한 재요청
                ActivityCompat.requestPermissions(this,new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION);

                Toast.makeText(this,"권한 설정 필요", Toast.LENGTH_SHORT).show();
            }
            else {
                // 최초로 권한을 요청하는 경우 ( 첫실행)
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            }
        }
        else {
            //사용 권한이 있음을 확인한 경우
            mMap.setMyLocationEnabled(false);
        }


        if(mCompassEnabled){
            mSensorManager.unregisterListener(mListener);
        }
    }

    // 현재 위치 확인을 위해 정의한 메소드
    private void startLocationService(){
        // 위치 관리자 객체 참조
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 리스너 객체 생성
        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;



        // GPS 기반 위치 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //권한이 없을경우
            // 최초 권한 요청인지, 혹은 사용자에 의한 재요청인지 확인
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                // 사용자가 임의로 권한을 취소시킨 경우
                // 권한 재요청
                ActivityCompat.requestPermissions(this,new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION);

                Toast.makeText(this,"권한 설정 필요", Toast.LENGTH_SHORT).show();
            }
            else {
                // 최초로 권한을 요청하는 경우 ( 첫실행)
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            }
        }
        else {
            //사용 권한이 있음을 확인한 경우
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);
        }

        // 네트워크 기반 위치 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //권한이 없을경우
            // 최초 권한 요청인지, 혹은 사용자에 의한 재요청인지 확인
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                // 사용자가 임의로 권한을 취소시킨 경우
                // 권한 재요청
                ActivityCompat.requestPermissions(this,new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION);

                Toast.makeText(this,"권한 설정 필요", Toast.LENGTH_SHORT).show();
            }
            else {
                // 최초로 권한을 요청하는 경우 ( 첫실행)
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            }
        }
        else {
            //사용 권한이 있음을 확인한 경우
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);
        }
        Toast.makeText(getApplicationContext(), "위치 확인 시작함. 로그 확인", Toast.LENGTH_SHORT).show();
    }

    // 리스너 정의
    private class GPSListener implements LocationListener{
        //위치 정보가 확인되면 호출되는 메서드
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "Latitude : " + latitude + "\nLongitude:" + longitude;
            Log.i("GPSLocationService", msg);

            // 현재 위치의 지도를 보여주기 위해 정의한 메소드 호출
            showCurrentLocation(latitude, longitude);
        }
        public void onProviderDisabled(String provider){
        }
        public void onProviderEnabled(String provider){
        }
        public  void onStatusChanged(String provider, int status, Bundle extras){
        }
    }

    // 현재 지도를 보여주기 위해 정의한 메서드
    private void showCurrentLocation(Double latitude, Double longitude){
        // 현재 위치를 이용해 LatLon 객체 생성
        LatLng curPoint = new LatLng(latitude,longitude);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        Toast.makeText(getApplicationContext()," 위치 확인 완료", Toast.LENGTH_SHORT).show();

        // 지도 유형 설정, 이걸로 모양 변경 가능
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private final SensorEventListener mListener = new SensorEventListener() {
        private  int iOrientation = -1;
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(iOrientation < 0)
            {
                iOrientation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

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
