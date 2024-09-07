package tenm.chihuahua2.desarrollomoviles.lanzardado;

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

    //Declarar objetos java
    Button lanzadorB;
    ImageView dadosIm;
    TextView Resultado;
    int L1,L2,L3,L4,L5,L6,total;
    TextView textViewL1,textViewL2,textViewL3,textViewL4,textViewL5,textViewL6;
    TextView textViewPorcentaje1,TextViewPorcentaje2,TextViewPorcentaje3,
    TextViewPorcentaje4,TextViewPorcentaje5,TextViewPorcentaje6;
    ProgressBar PB1,PB2,PB3,PB4,PB5,PB6;


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

        //Vincular objeyos java con los elementos de interfaz de usuario XML4

        dadosIm = findViewById(R.id.ImaDado);
        lanzadorB = findViewById(R.id.button);
        textViewL1 = findViewById(R.id.TextViewL1);
        textViewL2 = findViewById(R.id.TextViewL2);
        textViewL3 = findViewById(R.id.TextViewL3);
        textViewL4 = findViewById(R.id.TextViewL4);
        textViewL5 = findViewById(R.id.TextViewL5);
        textViewL6 = findViewById(R.id.TextViewL6);
        PB1= findViewById(R.id.progressBar1);
        PB2= findViewById(R.id.progressBar2);
        PB3= findViewById(R.id.progressBar3);
        PB4= findViewById(R.id.progressBar4);
        PB5= findViewById(R.id.progressBar5);
        PB6= findViewById(R.id.progressBar6);
        textViewPorcentaje1 = findViewById(R.id.TextViewPorc1);
        TextViewPorcentaje2 = findViewById(R.id.TextViewPorc2);
        TextViewPorcentaje3 = findViewById(R.id.TextViewPorc3);
        TextViewPorcentaje4 = findViewById(R.id.TextViewPorc4);
        TextViewPorcentaje5 = findViewById(R.id.TextViewPorc5);
        TextViewPorcentaje6 = findViewById(R.id.TextViewPorc6);



       // Resultado = findViewById(R.id.Resultadotxt);

        lanzadorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Genrar numeres aleadorios entre 1 y 5

                Random numrandom = new Random();

                int numero = numrandom.nextInt(6) + 1;

                //Mostrar resultado

                Toast.makeText(MainActivity.this,
                        "numero: " + numero, Toast.LENGTH_SHORT).show();

                procesarResultado(numero);

            }
        });

    }//Fin del OneCreate
        private void procesarResultado (int num){
            total++;

            switch (num){

                case 1:
                    dadosIm.setImageResource(R.drawable.dado1);
                    L1++;
                    break;
                case 2 :
                    dadosIm.setImageResource(R.drawable.dado2);
                    L2++;
                    break;
                case 3 :
                    dadosIm.setImageResource(R.drawable.dado3);
                    L3++;
                    break;
                case 4 :
                    dadosIm.setImageResource(R.drawable.dado4);
                    L4++;
                    break;
                case 5 :
                    dadosIm.setImageResource(R.drawable.dado5);
                    L5++;
                    break;

                case 6 :
                    dadosIm.setImageResource(R.drawable.dado6);
                    L6++;
                    break;

                default:
                    Toast.makeText(this, "No existe bro", Toast.LENGTH_SHORT).show();

            }
            mostrarResultados();
        }


    public void mostrarResultados() {
        //recuperara recursos string
        String L1_string = getResources().getString(R.string.textView_lado1);
        //mostrar ocurrencia del lado 1
        textViewL1.setText(L1_string+""+L1);

        String L2_string = getResources().getString(R.string.textView_lado2);
        //mostrar ocurrencia del lado 1
        textViewL2.setText(L2_string+""+L2);

        String L3_string = getResources().getString(R.string.textView_lado3);
        //mostrar ocurrencia del lado 1
        textViewL3.setText(L3_string+""+L3);

        String L4_string = getResources().getString(R.string.textView_lado4);
        //mostrar ocurrencia del lado 1
        textViewL4.setText(L4_string+""+L4);

        String L5_string = getResources().getString(R.string.textView_lado5);
        //mostrar ocurrencia del lado 1
        textViewL5.setText(L2_string+""+L5);

        String L6_string = getResources().getString(R.string.textView_lado6);
        //mostrar ocurrencia del lado 1
        textViewL6.setText(L6_string+""+L6);

        //mostrar graficas de %

        float L1Porce = (float) (L1 * 100/total);

        PB1.setProgress((int) L1Porce);

        float L2Porce = (float) (L2 * 100/total);

        PB2.setProgress((int) L2Porce);

        float L3Porce = (float) (L3 * 100/total);

        PB3.setProgress((int) L3Porce);

        float L4Porce = (float) (L4 * 100/total);

        PB4.setProgress((int) L4Porce);

        float L5Porce = (float) (L5 * 100/total);

        PB5.setProgress((int) L5Porce);

        float L6Porce = (float) (L6 * 100/total);

        PB6.setProgress((int) L6Porce);



        textViewPorcentaje1.setText(L1Porce +"%");
        TextViewPorcentaje2.setText(L2Porce +"%");
        TextViewPorcentaje3.setText(L3Porce +"%");
        TextViewPorcentaje4.setText(L4Porce +"%");
        TextViewPorcentaje5.setText(L5Porce +"%");
        TextViewPorcentaje6.setText(L6Porce +"%");
    }
}//Fin del Main Activity
