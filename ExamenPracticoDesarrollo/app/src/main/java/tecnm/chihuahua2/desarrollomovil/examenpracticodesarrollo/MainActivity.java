package tecnm.chihuahua2.desarrollomovil.examenpracticodesarrollo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Conversionbtn;
    EditText txtValor;
    EditText Resultadotxt;
    TextView cantidadestxt;

    int numeroConversiones = 0;
    List<String> resultados = new ArrayList<>();

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

        Conversionbtn = findViewById(R.id.Convertirbtn);
        txtValor = findViewById(R.id.txtValor);
        Resultadotxt = findViewById(R.id.Resultadotxt);
        cantidadestxt = findViewById(R.id.cantidadestxt);


        Conversionbtn.setOnClickListener(v -> {
            numeroConversiones++;

            Double pulgadas = Double.parseDouble(txtValor.getText().toString());

            Double centimetros = pulgadas * 2.54;
            Double metros = pulgadas * 0.0254;

            resultados.add(pulgadas +" pulgada = " + centimetros+ " cm " + "\n"+
                    pulgadas +" pulgada = " + metros + " m " +"\n");

            for (int i=0; i< resultados.size(); i++){
                Resultadotxt.setText(resultados.get(i));
                cantidadestxt.setText(String.valueOf(numeroConversiones));

            }
        });




    }
}