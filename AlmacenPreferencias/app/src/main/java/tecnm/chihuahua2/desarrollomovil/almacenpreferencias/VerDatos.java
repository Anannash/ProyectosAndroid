package tecnm.chihuahua2.desarrollomovil.almacenpreferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerDatos extends AppCompatActivity {

    TextView NombreTV, TemaTV, FechaTV;
    ImageView TemaIM;
    Button RegresarBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_datos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NombreTV = findViewById(R.id.NombreTV);
        TemaTV = findViewById(R.id.TemaTV);
        FechaTV = findViewById(R.id.FechaTV);
        TemaIM = findViewById(R.id.TemaIM);
        RegresarBTN = findViewById(R.id.RegresarBTN);
        //RECUPERAR DATOS DE PREFERENCIAS
        String nombre;
        String tema;
        String fecha;
        //Acceder a la preferncia
        SharedPreferences sharedPreferences =
                getSharedPreferences("preferencias",
                        Context.MODE_PRIVATE);
        nombre = sharedPreferences.getString("nombre", "");
        tema = sharedPreferences.getString("tema", "");
        fecha = sharedPreferences.getString("fecha", "");

        //Mostrzr datos GUI
        NombreTV.setText(nombre);
        FechaTV.setText(fecha);

        //Mostar imagen segun tema
        if (tema.equals("Planta")){
           TemaIM.setImageResource(R.drawable.plantita);
        }else if (tema.equals("Animal")) {

            TemaIM.setImageResource(R.drawable.gatito);
        }else{
            TemaIM.setImageResource(R.drawable.kinger);
        }
        //Escuchador para boton
        RegresarBTN.setOnClickListener(v -> {
            finish();
        });

    }//ONCREATE

}