package tecnm.chihuahua2.desarrollomovil.graficasandroidplot;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.juang.jplot.PlotPlanitoXY;

public class PuntosPerzonalizado extends AppCompatActivity {


    PlotPlanitoXY plot;
    LinearLayout pantalla;
    Context context;
    EditText txtX, txtY, titulotxt;

    float[] X, Y;
    Button btnCrear;

    float[] partesX, partesY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_puntos_perzonalizado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        btnCrear = findViewById(R.id.Crearbtn);
        pantalla = findViewById(R.id.mainPuntosP);
        titulotxt = findViewById(R.id.etxtTituloGraficaPuntos);
        context = this;

        btnCrear.setOnClickListener(view -> {

            String tituloPerzonalizado = titulotxt.getText().toString();

            String xComas = txtX.getText().toString();
            String yComas = txtY.getText().toString();

            // Dividir las coordenadas X y Y por comas
            String[] xStrings = xComas.split(",");
            String[] yStrings = yComas.split(",");

            // Inicializar los arreglos partesX y partesY
            partesX = new float[xStrings.length];
            partesY = new float[yStrings.length];
            // Comprobar si los tamaños son iguales
            if (xStrings.length != yStrings.length) {
                Toast.makeText(this, "Los números introducidos deben tener la misma cantidad en X y Y", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                // Asignar valores a los arreglos partesX y partesY
                for (int i = 0; i < partesX.length; i++) {
                    partesX[i] = Float.parseFloat(xStrings[i].trim().replace(",", "."));
                    partesY[i] = Float.parseFloat(yStrings[i].trim().replace(",", "."));
                }
                // Inicializar los arreglos X y Y
                X = new float[partesX.length];
                Y = new float[partesY.length];
                // Copiar los valores
                System.arraycopy(partesX, 0, X, 0, partesX.length);
                System.arraycopy(partesY, 0, Y, 0, partesY.length);

                // Crear el gráfico
                plot = new PlotPlanitoXY(context, tituloPerzonalizado, "Número de Infidelidades", "Meses");
                plot.SetSerie1(X, Y, "Infidelidades", 5, true);

                plot.SetHD(true);
                plot.SetTouch(true);

                // Limpiar vistas anteriores
                pantalla.removeAllViews();
                pantalla.addView(plot);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Los números introducidos deben ser numéricos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}