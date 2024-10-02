package tecnm.chihuahua2.desarrollomovil.examen1practico;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


     EditText CalificacionEDtxt;
     EditText ListaCaltxt;
     TextView CantCaltxt;
     TextView Promediotxt;
     TextView Evaluaciontxtt;
     Button Agregarbtn;

    int numerodeCalificaciones = 0;

    ArrayList<Double> calificaciones = new ArrayList<>();

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

        CantCaltxt = findViewById(R.id.CantCaltxt);
        Promediotxt = findViewById(R.id.Promediotxt);
        Evaluaciontxtt = findViewById(R.id.Evaluaciontxtt);
        CalificacionEDtxt = findViewById(R.id.CalificacionEDtxt);
        ListaCaltxt = findViewById(R.id.ListaCaltxt);
        Agregarbtn = findViewById(R.id.Agregarbtn);


        Agregarbtn.setOnClickListener(v -> {
            numerodeCalificaciones++;

            CantCaltxt.setText(getString(R.string.Cantidad)+
                    " "+numerodeCalificaciones);

            double calificacion = Double.parseDouble(
                    CalificacionEDtxt.getText().toString());
            calificaciones.add(calificacion);
            ListaCaltxt.setText(getString(R.string.CaliList)
                    +" "+calificaciones.toString());

            double promedio = 0;
            for (int i = 0; i < calificaciones.size(); i++) {
                promedio += calificaciones.get(i);
            }
            promedio = promedio / calificaciones.size();

            Promediotxt.setText(getString(R.string.Promedio)+" "+promedio);
            CalificacionEDtxt.setText("");
            if (promedio >= 70) {
                Evaluaciontxtt.setText(getString(R.string.Evaluacion)+" Aprobado");
                Evaluaciontxtt.setTextColor(Color.GREEN);
            } else {
                Evaluaciontxtt.setText(getString(R.string.Evaluacion)+" Reprobado");
                Evaluaciontxtt.setTextColor(Color.RED);
            }




        });


    }




}