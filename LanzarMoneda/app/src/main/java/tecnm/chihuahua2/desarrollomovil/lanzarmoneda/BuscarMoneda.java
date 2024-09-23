package tecnm.chihuahua2.desarrollomovil.lanzarmoneda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class BuscarMoneda extends AppCompatActivity {

    Button Regresarbtn;
    RadioButton RadioGMonedas;
    RadioButton RadioGMonedas2;
    RadioButton RadioGMonedas3;
    RadioButton RadioGMonedas4;
    ImageButton ImagenMoneda;
    TextView DescrBusc;
    RadioGroup  RGMonedas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_moneda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Regresarbtn = findViewById(R.id.Regresarbtn);
        RadioGMonedas = findViewById(R.id.radioGMonedas);
        RadioGMonedas2 = findViewById(R.id.RadioGMonedas2);
        RadioGMonedas3 = findViewById(R.id.RadioGMonedas3);
        RadioGMonedas4 = findViewById(R.id.RadioGMonedas4);
        ImagenMoneda = findViewById(R.id.ImagenMoneda);
        DescrBusc = findViewById(R.id.DescrBusc);
        RGMonedas = findViewById(R.id.RGroupbtn);

        RGMonedas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id_itemSelect) {
                //id contene el id del elemento seleccionado

                //Identificar eleemti radiobutton seleccionado
                if (id_itemSelect == RadioGMonedas.getId()){
                    Toast.makeText(BuscarMoneda.this, "Moneda de cobre de 8 reales ", Toast.LENGTH_SHORT).show();
                    ImagenMoneda.setImageResource(R.drawable.moneda8reales_1812);
                    DescrBusc.setText(R.string.M1);
                } else if (id_itemSelect == RadioGMonedas2.getId()) {
                    Toast.makeText(BuscarMoneda.this, "Toston", Toast.LENGTH_SHORT).show();
                    ImagenMoneda.setImageResource(R.drawable.centavos501919);
                    DescrBusc.setText(R.string.M2);

                } else if (id_itemSelect == RadioGMonedas3.getId()) {
                    Toast.makeText(BuscarMoneda.this, "Real de a 8", Toast.LENGTH_SHORT).show();
                    ImagenMoneda.setImageResource(R.drawable.real_de_a_ocho);
                    DescrBusc.setText(R.string.M3);

                } else if (id_itemSelect == RadioGMonedas4.getId()) {
                    Toast.makeText(BuscarMoneda.this, "Moneda mexicana", Toast.LENGTH_SHORT).show();
                    ImagenMoneda.setImageResource(R.drawable.aratamecoin1859);
                    DescrBusc.setText(R.string.M4);

                }
            }
        });



        Regresarbtn.setOnClickListener(view -> {
            finish();
        });




/*
        RadioGMonedas.setOnClickListener(view -> {
            ImagenMoneda.setImageResource(R.drawable.moneda8reales_1812);
            DescrBusc.setText(R.string.M1);
        });
        RadioGMonedas2.setOnClickListener(view -> {
            ImagenMoneda.setImageResource(R.drawable.centavos501919);
            DescrBusc.setText(R.string.M2);
        });
        RadioGMonedas3.setOnClickListener(view -> {
            ImagenMoneda.setImageResource(R.drawable.real_de_a_ocho);
            DescrBusc.setText(R.string.M3);
        });
        RadioGMonedas4.setOnClickListener(view -> {
            ImagenMoneda.setImageResource(R.drawable.aratamecoin1859);
            DescrBusc.setText(R.string.M4);
        });*/






    }



}