package mx.edu.tecnm.chihuahua2.desarrollomovil.sensorluz;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView  TVResultado;
    ImageView imageView;

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
        imageView = findViewById(R.id.luz);



        //Escuchador para detectar cambios en ell sensor de luz
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //obteer el valor de ilumibnacion y aplicar TV
                TVResultado.setText(sensorEvent.values[0]+"");
                //Asignar colores segun el numero de sensor
                int gris = (int) sensorEvent.values[0];

                if(gris>255){
                    gris = 255;
                    imageView.setImageResource(R.drawable.baseline_lightbulb_24);
                }else {
                    imageView.setImageResource(R.drawable.baseline_lightbulb_outline_24);

                }
                TVResultado.setTextColor(Color.rgb(255-gris,
                        255-gris,255-gris));
                TVResultado.setBackgroundColor(Color.rgb(gris,gris,gris));


           }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        //Registrar el sensor de luz
        sensorManager.registerListener(sensorEventListener, Luz,
                SensorManager.SENSOR_DELAY_FASTEST);
    }
    //ES NECESARIO APAGAR EL SENCSOR CUANDO LA APP NO ESRA EB USI
    //PASA A UN SEGUNDO PLANO O ES DESTRUIDA
        @Override
    protected void onPause() {
        super.onPause();

        //Apagar el senson
        sensorManager.unregisterListener(sensorEventListener, Luz
        );
    }
}