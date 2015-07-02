package com.android.eyeeyes;



import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.hardware.SensorListener;

@SuppressWarnings("deprecation")
public class eyeeyes extends Activity implements SensorListener { //用來實作感測器監聽介面
	
	final String tag = "IBMEyes";
	SensorManager sm; //感測器管理員
	
	TextView xViewA; //畫面中加速器的文字元件
	TextView yViewA;
	TextView zViewA;
	TextView c;
	Button bt1=null;
	int count = 0;
	String ggmaple555=null;
	float xx,yy,zz;
	Socket connect;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //由系統服務取得感測器管理員
        setContentView(R.layout.main);
        xViewA = (TextView) findViewById(R.id.xbox); //取得TextView元件
        yViewA = (TextView) findViewById(R.id.ybox);
        zViewA = (TextView) findViewById(R.id.zbox);
        bt1=(Button) findViewById(R.id.button1);
        c = (TextView) findViewById(R.id.count1);
        try {
			connect = new Socket("192.168.56.1", 8888);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					TextView ggmod=(TextView) c;
					PrintWriter pw=null;


					byte[] bytelist=new byte[256];


					pw=new PrintWriter(connect.getOutputStream());


					pw.write(c.getText()+"\n");
					pw.flush();
					count=0;
					c.setText("count :" + count);
					

					}catch(Exception ex){
						System.out.println(ex.toString());
							
					}
				// TODO Auto-generated method stub
			   
			}
		});
   
    }
    
   
    													//onSensorChanged是每當感測器的質改變時即會呼叫此方法
    public void onSensorChanged(int sensor, float[] values) { //加速度值的改變
        synchronized (this) {
            Log.d(tag, "onSensorChanged: " + sensor + ", x: " + values[0] + ", y: " + values[1] + ", z: " + values[2]);
            if (sensor == SensorManager.SENSOR_ACCELEROMETER) { //當是加速度感測器時進行處理
	            xViewA.setText("Accel X: " + -values[0]);
	            yViewA.setText("Accel Y: " + -values[1]);
	            zViewA.setText("Accel Z: " + -values[2]);
	            
	            if(values[0] < 0)
	            	xx = -values[0];
	            else if(values[0] < 0)
	            	xx = values[0];
	            
	            if(values[1] < 0)
	            	yy = -values[1];
	            else if(values[1] < 0)
	            	yy = values[1];
	            
	            if(values[2] < 0)
	            	zz = -values[2];
	            else if(values[2] < 0)
	            	zz = values[2];
	            
	            if(xx + yy + zz > 22){
	            	count ++ ;
	            	c.setText("count :" + count);
	     
	            }
            }            
        }
    }
    													//onAccuracyChanged是每當感測器的精準度改變時即會呼叫此方法
    public void onAccuracyChanged(int sensor, int accuracy) { //精準度改變時的處理
    	
    }		//可由參數accuracy來讀取改變後的精準度
 

    @Override
    protected void onResume() { //當Activity畫面顯示出來時
        super.onResume();
        sm.registerListener(this, //將this註冊為加速感測器物件的感測器
                SensorManager.SENSOR_ORIENTATION |
        		SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_NORMAL); //向加入感測器 
        											//註冊監聽物件 (this)
    }
    
    @Override
    protected void onStop() { //當Activity畫面被覆蓋時(切換到其他程式)
        sm.unregisterListener(this); //取消監聽物件(this)的註冊
        super.onStop();
    }    
    
    
}