package mx.edu.tecnm.chihuahua2.desarrollomovil.stepcounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
implements SensorEventListener {
    TextView tvCounter, tvDistance;
    Button btnReset;

    SensorManager sensorManager;
    Sensor stepCounter;

    int padosIniciales=-1;
    int Contadorglobaln =0;

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

        tvCounter = findViewById(R.id.textView);
        tvDistance = findViewById(R.id.textView2);
        btnReset = findViewById(R.id.button);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}