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

    // Objeto para gestionar sensores
    SensorManager sensorManager;
    ListView listaSensores;
    TextView contadorTxt;

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

        // Vinculación de vistas
        contadorTxt = findViewById(R.id.Contadortxtx);
        listaSensores = findViewById(R.id.LVsensores);

        // Obtener referencia del servicio de sensores
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtener una lista de los sensores del dispositivo
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // Crear un adaptador para asignar los sensores a la lista
        final ArrayAdapter<Sensor> adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, deviceSensors
        );

        // Asociar el adaptador a la ListView
        listaSensores.setAdapter(adapter);

        // Mostrar el número total de sensores en el TextView
        contadorTxt.setText(getString(R.string.Contador)+ " " + deviceSensors.size());
    }
}