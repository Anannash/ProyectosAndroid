package tecnm.chihuahua2.desarrollomovil.graficasandroidplot;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.juang.jplot.PlotPastelito;

public class pastelPersonalizado extends AppCompatActivity {
    PlotPastelito pastel;
    Context context;
    LinearLayout pantalla;

    Button button_obtener_grafica;
    EditText editTextEtiquetasX;
    EditText editTextEtiquetasY;
    EditText Titulotxt;

    // Arreglos para los valores
    String[] etiquetas;
    double[] valores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pastel_personalizado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        pantalla = findViewById(R.id.maindiez);
        button_obtener_grafica = findViewById(R.id.button_obtenerGrafica);
        editTextEtiquetasX = findViewById(R.id.editTextEtiquetas);
        editTextEtiquetasY = findViewById(R.id.editTextValores);
        Titulotxt = findViewById(R.id.etxtTituloGraficaPastel);

        button_obtener_grafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tituloPerzonalizado= Titulotxt.getText().toString();

                String etiquetasTexto = editTextEtiquetasX.getText().toString();
                String valoresTexto = editTextEtiquetasY.getText().toString();

                String[] etiquetas = etiquetasTexto.split(",");
                String[] valoresString = valoresTexto.split(",");

                if (etiquetas.length != valoresString.length) {
                    // Manejar el caso en el que los arreglos no tienen la misma longitud
                    Toast.makeText(context, "Los arreglos no tienen la misma longitud", Toast.LENGTH_SHORT).show();
                    return;
                }
                float[] valores = new float[valoresString.length];
                for (int i = 0; i < valoresString.length; i++) {
                    try {
                        valores[i] = Float.parseFloat(valoresString[i]);
                    } catch (NumberFormatException e) {
                        Toast.makeText(context, "Los valores no son válidos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                pastel = new PlotPastelito(context, tituloPerzonalizado);
                pastel.SetDatos(valores, etiquetas);
                pastel.SetHD(true);
                pantalla.removeAllViews();
                pantalla.addView(pastel);
            }
        });

    }
}