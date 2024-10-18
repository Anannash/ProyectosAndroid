package mx.edu.tecnm.chihuahua2.desarrollomovil.sensorluz;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    TextView  TVResultado;

    //Objeto para gestionar sesores
    SensorManager sensorManager;

    //Obejto para gestionar la escucha de amvios en el sensor
    SensorEventListener sensorEventListener;

    //  Onjeto pa manejar sensor de luz
    Sensor Luz;

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

        TVResultado = findViewById(R.id.TVResultado);
        //Instanciamos el SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Instanciamos el sensor de luz
        Luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //Escuchador para detectar cambios en ell sensor de luz
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

}