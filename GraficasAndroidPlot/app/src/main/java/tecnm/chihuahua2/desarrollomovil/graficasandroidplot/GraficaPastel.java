package tecnm.chihuahua2.desarrollomovil.graficasandroidplot;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.juang.jplot.PlotPastelito;

public class GraficaPastel extends AppCompatActivity {

    private PlotPastelito pastel;
    private LinearLayout pantalla;
    Context context;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grafica_pastel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        context = this;
        pantalla = (LinearLayout) (findViewById(R.id.main2));

        // Crear el gráfico de pastel con el título "Ganancias Diarias"
        pastel = new PlotPastelito(context, "Ganancias Diarias");

        // Datos de ejemplo
        String[] etiquetas = {"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"};
        String[] rawData = {"2", "5", "8", "11", "23", "7", "16"};  // Datos originales, podrían venir con comas

        // Convertir los datos eliminando comas si fuera necesario
        float[] datapoints = convertirDatos(rawData);

        // Configurar los datos del gráfico
        pastel.SetDatos(datapoints, etiquetas);

        // Ajustes adicionales del gráfico
        pastel.SetHD(true); // Activar calidad HD

        // Agregar el gráfico a la pantalla
        pantalla.addView(pastel);

        // Botón de regresar
        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> finish());
    }

    // Método para convertir los datos de String a float[] asegurándose de eliminar comas
    private float[] convertirDatos(String[] rawData) {
        float[] datapoints = new float[rawData.length];

        for (int i = 0; i < rawData.length; i++) {
            try {
                // Remover comas de los valores numéricos si existen
                String cleanValue = rawData[i].replace(",", "");
                datapoints[i] = Float.parseFloat(cleanValue);
            } catch (NumberFormatException e) {
                Log.e("GraficaPastel", "Error en formato de número: " + rawData[i], e);
                datapoints[i] = 0;  // Si hay un error, asignar 0 como valor por defecto
            }
        }
        return datapoints;
    }
}