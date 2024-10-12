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

import com.juang.jplot.PlotPlanitoXY;

public class GraficaPuntos extends AppCompatActivity {

    private PlotPlanitoXY plot;
    private LinearLayout pantalla;
    Context context;

    float [] X,Y;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_grafica_puntos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            finish();
        });

        context=this;
        pantalla= (LinearLayout) (findViewById(R.id.main2));

        X=new float[4]; Y=new float[4];// si se desean graficar datos tipo double debe convertirse de "double a float"
        X[0]=3.4f;Y[0]=2.5f;
        X[1]=11.3f;Y[1]=6.6f;
        X[2]=12.4f;Y[2]=7.6f;
        X[3]=20.9f;Y[3]=10.4f;
        plot = new PlotPlanitoXY(context,"Titulo principal del grafico","titulo eje x","titulo eje y");
        plot.SetSerie1(X,Y,"graph 1",5,true);// el 5 es el tamaño de punto "true" es para unir los puntos
        //con una linea
       /*antes de mostrar el grafico en pantalla(LinearLayout) deben de ir todos los ajustes "Set" del grafico.
       Todos los metodos publicos que ayudan a personalizar el grafico se describen cada uno en la siguiente sección */

       /*
       //agregando imagem.png al fondo de la cuadricula que esta en la carpeta "drawable" del proyecto.
       Drawable myDrawable = getResources().getDrawable(R.drawable.fneon);//debe cambiarse "fneon" por tu imagen
       Bitmap myFondo = ((BitmapDrawable) myDrawable).getBitmap();
       plot.SetImagFondo1(myFondo);
       */

        plot.SetHD(true); //ajustamos la calidad hd que suaviza bordes del grafico. por default esta desactivado
        plot.SetTouch(true);// activa el touch sobre el grafico no es necesario colocarlo ya que por default esta activado
        pantalla.addView(plot);
    }
}