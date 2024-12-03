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

    int pasosIniciales=-1;
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
        //Metodo que se ejecute cada vez que ocurre cambios
        //El sensor . En este caso cuando el dist[positivo
        //Detecta un paso del usuario

        //Detectar cambios del contador de pasos
        if(sensorEvent.sensor.getType()==Sensor.TYPE_STEP_COUNTER){
            if (pasosIniciales==-1){
                pasosIniciales=(int) sensorEvent.values[0];

            }
            int stepsTaken = (int) sensorEvent.values[0]
                    -pasosIniciales;

            //mostrar contador de pasos
            String textito=(getString(R.string.TVstepcount)+stepsTaken);
            tvCounter.setText(textito+" pasongos");

            //Calcular una distancia aproximada

            /*Promedio de sancada en H:0.76
             * Promedio de sancadas en mujeres: 0.67m
             * Promdedio global: 0.715 m
             * */

            float distancia= stepsTaken*0.715f;
            String textito2=(getString(R.string.TVdistancia)+String.format("%.2f",distancia) +" metros");
            tvDistance.setText(textito2);


        }



        
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    //Escribir metodos para cuando la actividad se reanude o pause//Metodo cuando la act esta en pausa
    @Override
    protected void onPause() {
        super.onPause();
        //Eliminar el sensor de pasos
        if (stepCounter != null) {
            sensorManager.unregisterListener(this);
        }
    }
    //Metodo cuando la actividad vuelve a la vida
    @Override
    protected void onResume() {
        super.onResume();
        //Reanudar el sensor de pasos
        if (stepCounter != null) {
            sensorManager.registerListener(this, stepCounter,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


    }
}