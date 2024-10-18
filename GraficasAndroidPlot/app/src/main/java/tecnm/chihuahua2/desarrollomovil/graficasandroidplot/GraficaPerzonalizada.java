package tecnm.chihuahua2.desarrollomovil.graficasandroidplot;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.juang.jplot.PlotBarritas;

public class GraficaPerzonalizada extends AppCompatActivity {

    PlotBarritas GRBarras;
    Context context;
    LinearLayout PGrafica;
    Button ObtnerGrbtn, Regresarbtn;
    EditText ValorX,ValorY ;


    //Arreglos
    String[] etiqueta;
    double [] valores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grafica_perzonalizada);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        PGrafica= (LinearLayout) (findViewById(R.id.main3));

        ObtnerGrbtn = findViewById(R.id.Aceptarbtn) ;

        //obtner datos de la UI

        ValorX = findViewById(R.id.ValorXtxt);
        ValorY = findViewById(R.id.ValorYtxt);
        ObtnerGrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //obtner datos de la intefaz de usuario

                String X = ValorX.getText().toString();
                String Y = ValorY.getText().toString();

                //Separar los valores de las etiquetas

                etiqueta = X.split(" ");
                valores = new double[etiqueta.length];

                //obtner cada valor numerico y guardarlo

                for (int i = 0; i < valores.length; i++) {

                    valores[i] = Double.parseDouble(Y.split(" ")[i]);

                }

                //Configurar la grafica barras
                GRBarras = new PlotBarritas(context,
                        "Grafica de Barras", "Articulos Vendidos por dia"
                );
                GRBarras.Columna(etiqueta, valores);

                //Asignar datos a al grafica
                GRBarras.SetHD(true);

                //Asignar color a la tercer barra
                GRBarras.SetColorPila(3,255,0,0);
                //Configuracion para desplegar la grafica
                PGrafica.removeAllViews();

                //Agregar grafica al contenerdor ()Linear Layout

                PGrafica.addView(GRBarras);

            }
        });







    }
}