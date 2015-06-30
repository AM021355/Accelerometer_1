package com.android.eyeeyes;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
	int count = 0;
	float xx,yy,zz;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //�Ѩt�ΪA�Ȩ��o�P�����޲z��
        setContentView(R.layout.main);
        xViewA = (TextView) findViewById(R.id.xbox); //���oTextView����
        yViewA = (TextView) findViewById(R.id.ybox);
        zViewA = (TextView) findViewById(R.id.zbox);
        c = (TextView) findViewById(R.id.count);

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