package tecnm.chihuahua2.desarrollomovil.lanzarmoneda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //definir varaible
    ImageView ImgMoneda;
    Button BtnLanzar;
    TextView txtSellosTo, txtAgilasTo,txtAgilasPo, txtSellosPo;
    int sellos, agilas, total;
    ProgressBar PogresBSello, PogresBAgilas, Pogres;


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

        //Inicializadores contadores
        agilas = 0;
        sellos = 0;
        total = 0;

        //vincular objetos java con elementos xml
        ImgMoneda= findViewById(R.id.monedaImaV);
        BtnLanzar = findViewById(R.id.lanzarbtn);
        PogresBAgilas = findViewById(R.id.pbAgila);
        PogresBSello = findViewById(R.id.pbSello);
        txtSellosTo = findViewById(R.id.txtSellosT);
        txtAgilasTo = findViewById(R.id.txtAgilasT);
        txtAgilasPo = findViewById(R.id.txtAgilasP);
        txtSellosPo = findViewById(R.id.txtSelloP);



        //agregar escribir de evento para el boton

        BtnLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random ran = new Random();
                int numero = ran.nextInt(2+1);
                //actualizar resultados
                ProcesarSalida(numero);

                }//fin del on click
        });//fin del button
        }

        public void ProcesarSalida(int numero){
            // generar un numero aleatorio 0 o 1
            //mostrar numero generado
            total++;
            if(numero == 1){
                ImgMoneda.setImageResource(R.drawable.mora);
                Toast.makeText(MainActivity.this,
                        "MORA CRUZ", Toast.LENGTH_SHORT).show();
                sellos++;
            }else if(numero == 2){
                ImgMoneda.setImageResource(R.drawable.moracara);
                Toast.makeText(MainActivity.this,
                        "MORA CHILD", Toast.LENGTH_SHORT).show();
                agilas++;
            }
            actualizarResultados();
        }

    public void actualizarResultados() {
        String lanzaminetosST;
        lanzaminetosST = this.getResources().getString
                (R.string.txt_sellosTotales);

        txtSellosTo.setText(lanzaminetosST + "" + sellos);

        String lanzaminetosAT;
        lanzaminetosAT = this.getResources().getString
                (R.string.txt_agilasTotales);

        txtAgilasTo.setText(lanzaminetosAT + "" + agilas);

        //Obtener porcentajes
        float SellosPorce = ((float) sellos * 100) / total;
        float AgilasPorce = ((float) agilas * 100) / total;

        // Actualizar gráfico de porcentaje con valores enteros
        PogresBAgilas.setProgress(Math.round(AgilasPorce));
        PogresBSello.setProgress(Math.round(SellosPorce));

        // Mostrar porcentaje con dos dígitos después del punto
        txtAgilasPo.setText(String.format("%.2f%%", AgilasPorce));
        txtSellosPo.setText(String.format("%.2f%%", SellosPorce));



    }//fin onCreate




}//Fin Main