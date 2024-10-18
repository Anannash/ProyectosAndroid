package mx.edu.tecnm.chihuahua2.desarrollomovil.obtenersensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Objeto para gestion sensores
    SensorManager sensorManager;
    ListView ListaSensores;
    int Contador = 0;
    TextView contadortxt;

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

        //Vincular

        contadortxt = findViewById(R.id.contador);

        ListaSensores = findViewById(R.id.LVsensores);

        //obtner referncia
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        //Obtener una lista de los sensotres del dispositivoi

        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //Creatr un adaptador para asignar sensore
        //Cinculacion listas V

        final ArrayAdapter<Sensor> adapter = new ArrayAdapter<Sensor>(
                MainActivity.this, android.R.layout.simple_list_item_1,
                deviceSensors
        );

        //Asociar adaptador a LV
        ListaSensores.setAdapter(adapter);


        contadortxt.setText(deviceSensors.lenght);







    }///ON CREATE BITCH
}