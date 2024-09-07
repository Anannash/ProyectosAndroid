package tenm.chihuahua2.desarrollomoviles.sumarnumeros;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // declarar objeto JAVA
    EditText Num1;
    EditText Num2;
    Button buttonS;
    Button buttonR;
    TextView Resultadotext;


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
        //Vincular objeto java con elementos de la Interfaz de usuaroi UI del archivo XML(activityMain.xml)
        //Buscar por el id los
        Num1= findViewById(R.id.editT1);
        Num2= findViewById(R.id.editT2);
        buttonS = findViewById(R.id.buttonS);
        Resultadotext = findViewById(R.id.textVResultado);
        buttonR = findViewById(R.id.buttonR);




        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float N1= Float.valueOf(Num1.getText().toString());
                float N2= Float.valueOf(Num2.getText().toString());

                /*Crear un escuchador de evento para el boton*/
                //suamr numeros


                float suma =N1 +N2;

                Resultadotext.setText(""+suma);


                if(suma>0){
                    Resultadotext.setTextColor(Color.BLUE);
                }if (suma <0){
                    Resultadotext.setTextColor(Color.GREEN);
                }if (suma ==0){
                    Resultadotext.setTextColor(Color.RED);
                }

            }
        });

        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float N1= Float.valueOf(Num1.getText().toString());
                float N2= Float.valueOf(Num2.getText().toString());
                Resultadotext.setText(""+resta(N1,N2));

                if(resta(N1,N2)>0){
                    Resultadotext.setTextColor(Color.BLUE);
                }if (resta(N1,N2) <0){
                    Resultadotext.setTextColor(Color.GREEN);
                }if (resta(N1,N2) ==0){
                    Resultadotext.setTextColor(Color.RED);
                }

            }

            
        });

    }//Fin del One create


    private float resta (float n1,float n2){
        float operacion= n1-n2;

        return operacion;

    }



}//fin del activyty