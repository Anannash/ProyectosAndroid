package mx.edu.tecnm.chihuahua2.desarrollomovil.brujula;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
    implements SensorEventListener  {

    Float azimuth_angle;;
    SensorManager sensorManager;
    Sensor accelerometer, magnetometer;

    float current_degres=0f;

    TextView TVgrados;
    ImageView brujula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TVgrados = findViewById(R.id.txtVGrados);
        brujula = findViewById(R.id.imageView);


        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }
    float[] accel_read;
    float[] magnet_read;



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accel_read = sensorEvent.values;}
        if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magnet_read = sensorEvent.values;}
        if(accel_read != null && magnet_read != null){
            float[] R1 = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R1,I,accel_read,magnet_read);
            if(success){
                float orientation[] = new float[3];
                SensorManager.getOrientation(R1,orientation);
                azimuth_angle = orientation[0];
                float degrees = ((azimuth_angle * 180)/(float) Math.PI);
                String valorAngulo= (getString(R.string.Grados)+" "+(degrees));
                TVgrados.setText(valorAngulo);
                //Animacion
                RotateAnimation rotate = new RotateAnimation(
                        current_degres,
                        -degrees,
                        RotateAnimation.RELATIVE_TO_SELF,
                        0.5f,
                        RotateAnimation.RELATIVE_TO_SELF,
                        0.5f
                );
                rotate.setDuration(100);
                rotate.setFillAfter(true);
                brujula.startAnimation(rotate);
                current_degres = -degrees;
        }}}
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);}
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                accelerometer,SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this,
                magnetometer,SensorManager.SENSOR_DELAY_UI);}
}