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
public class eyeeyes extends Activity implements SensorListener { //�Ψӹ�@�P������ť����
	
	final String tag = "IBMEyes";
	SensorManager sm; //�P�����޲z��
	
	TextView xViewA; //�e�����[�t������r����
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
        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //�Ѩt�ΪA�Ȩ��o�P�����޲z��
        setContentView(R.layout.main);
        xViewA = (TextView) findViewById(R.id.xbox); //���oTextView����
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
    
   
    													//onSensorChanged�O�C��P����������ܮɧY�|�I�s����k
    public void onSensorChanged(int sensor, float[] values) { //�[�t�׭Ȫ�����
        synchronized (this) {
            Log.d(tag, "onSensorChanged: " + sensor + ", x: " + values[0] + ", y: " + values[1] + ", z: " + values[2]);
            if (sensor == SensorManager.SENSOR_ACCELEROMETER) { //��O�[�t�׷P�����ɶi��B�z
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
    													//onAccuracyChanged�O�C��P��������ǫק��ܮɧY�|�I�s����k
    public void onAccuracyChanged(int sensor, int accuracy) { //��ǫק��ܮɪ��B�z
    	
    }		//�i�ѰѼ�accuracy��Ū�����ܫ᪺��ǫ�
 

    @Override
    protected void onResume() { //��Activity�e����ܥX�Ӯ�
        super.onResume();
        sm.registerListener(this, //�Nthis���U���[�t�P�������󪺷P����
                SensorManager.SENSOR_ORIENTATION |
        		SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_NORMAL); //�V�[�J�P���� 
        											//���U��ť���� (this)
    }
    
    @Override
    protected void onStop() { //��Activity�e���Q�л\��(�������L�{��)
        sm.unregisterListener(this); //������ť����(this)�����U
        super.onStop();
    }    
    
    
}