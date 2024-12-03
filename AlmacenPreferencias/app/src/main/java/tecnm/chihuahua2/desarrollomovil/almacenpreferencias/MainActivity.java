package tecnm.chihuahua2.desarrollomovil.almacenpreferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText nombretxt, editText;
    RadioGroup radioGroup;
    RadioButton plantarb, animalrb, figurarb;
    CalendarView calendarView;
    String selectDate;
    Button guardarbtn, verbtn;

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

        nombretxt = findViewById(R.id.Nombretxt);
        radioGroup = findViewById(R.id.radioGroup);
        plantarb = findViewById(R.id.Plantarb);
        animalrb = findViewById(R.id.Animalrb);
        figurarb = findViewById(R.id.Figurarb);
        guardarbtn = findViewById(R.id.Guardarbtn);
        verbtn = findViewById(R.id.Verbtn);


        calendarView = findViewById(R.id.calendarView2);

        //Cofico par ale boton guardar
        guardarbtn.setOnClickListener(v -> {
            /*El metodo getShatedPreference() permite definnir un nombre de archivo para guardar preferencias.
            Este archivo se podra usar en cualquier actividad de la app  * */
            SharedPreferences sharedPreferences =
                    getSharedPreferences("preferencias", MODE_PRIVATE);
            //Declarar objeto para editar prefesrencias
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //Recupera y guarda el nombre
            editor.putString("nombre", nombretxt.getText().toString());
            //Recuperar y guardar el tema
            if (plantarb.isChecked()) {
                editor.putString("tema", "Planta");} else if (animalrb.isChecked()) {
                editor.putString("tema", "Animal");} else if (figurarb.isChecked()) {
                editor.putString("tema", "Figura");}
            //Recuperar y guardar la fecha
            editor.putString("fecha", selectDate);
            //Guardar cambios
            editor.apply();
            //Mostrar mensaje
            Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_SHORT).show();
        });

        //Aggregar escuchador de eventos en caldendarViw y guradarl fehc
        // en formato perzonalizado
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView
                                                    calendarView, int month, int year, int dayOfMonth) {
                selectDate = dayOfMonth + "/" + (month + 1) + "/" + year;

            }
        });

        //Escuchador del boton abriri VerDatodds"
        verbtn.setOnClickListener(v -> {
           Intent intent = new Intent(this, VerDatos.class);
           startActivity(intent);
        });

    }//oncreate
}