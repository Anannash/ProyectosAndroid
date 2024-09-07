package tecnm.chihuahua2.desarrollomovil.indicemasacorporal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //varibales
    EditText EdtPeso, EdtAltura;
    Button Btncalular;
    TextView TxtResultado,TxtIMC;
    Toolbar toolbarM;

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




        //vincular
        EdtAltura = findViewById(R.id.edittxtAltura);
        EdtPeso = findViewById(R.id.edittxtPeso);
        Btncalular = findViewById(R.id.IMCbtn);
        TxtResultado = findViewById(R.id.txtresultado);
        TxtIMC = findViewById(R.id.txtInterpretacion);
        toolbarM = findViewById(R.id.toolbar);

        setSupportActionBar(toolbarM);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        //Agregar escuchador al hacer clo9ck
        Btncalular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float peso,altura,IMC;

                 peso = Float.parseFloat(EdtPeso.getText().toString());
                 altura = Float.parseFloat(EdtAltura.getText().toString());

                if (altura <= 0 || peso <= 0) {
                    Toast.makeText(MainActivity.this,
                            "No puede ser cero", Toast.LENGTH_SHORT).show();
                    return;
                }

                //calcular IMC
                IMC=peso/(altura*altura);

                //mostrar resultado
                TxtResultado.setText(String.format("Resultado: %.2f",IMC));

                actualizacionIMC(IMC);







            }
      });



}
    public void actualizacionIMC (float IMC){
        if (IMC<18.5){
            TxtIMC.setText(R.string.BP);
            Toast.makeText(MainActivity.this,
                    "ESTAS ANOREXICO MEDICATE DE VERDAD", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(Color.BLUE);


        } else if (IMC >=18.5  && IMC <24.9) {
            TxtIMC.setText(R.string.N);
            Toast.makeText(MainActivity.this,
                    "Eres mongolito, digo normalito", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(Color.GREEN);

        }else if (IMC >=25 && IMC <29.9) {
            TxtIMC.setText(R.string.SP);
            Toast.makeText(MainActivity.this,
                    "VELE BAJANDO A LOS TAMALES", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.naranja));

        }else if (IMC >=30 && IMC <34.9 ) {
            TxtIMC.setText(R.string.Ob);
            Toast.makeText(MainActivity.this,
                    "La COCA dietetica NO SIRVE", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(Color.RED);

        }else if (IMC >=35 && IMC <39.9) {
            TxtIMC.setText(R.string.Ob2);
            Toast.makeText(MainActivity.this,
                    "qui qui qui qui oink", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.rosa));

        }else if (IMC >=40) {
            TxtIMC.setText(R.string.Ob3);
            Toast.makeText(MainActivity.this,
                    "Mexico nesesita gordos esquiva dietas como tu", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.morado));

        }

    }
}