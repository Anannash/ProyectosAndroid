package tecnm.chihuahua2.desarrollomovil.graficasandroidplot;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.juang.jplot.PlotBarritas;

public class GraficaBarras extends AppCompatActivity {

    PlotBarritas Columna;
    Context context;
    LinearLayout pantalla;
    Button btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grafica_barras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        context = this;

        pantalla= (LinearLayout) (findViewById(R.id.main2));

        String x[]={"lunes","martes","miercoles","jueves","viernes"};
        double y[]={20,30,44,0,-25};
        Columna=new PlotBarritas(context,"Gráfico de Columnas ","articulos vendidos por dia");
        //en context puede colocarse simplemente this
        //personalizacion del grafico
        Columna.Columna(x,y);// OJO x e y DEBEN SER DEL MISMO TAMAÑO O CAUSARA QUE SE CIERRE LA APLICACION.
        Columna.SetHD(true);
        //cambiemos el color del dato 3 o sea "44" rojo=255,verde=0,Azul=0 los ultimos tres enteros son los colores en rgb
        Columna.SetColorPila(3,255,0,0);//muestra el tercer dato en color rojo

        //mostrando en pantalla
        pantalla.removeAllViews();
        pantalla.addView(Columna);

        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            finish();
        });
    }
}