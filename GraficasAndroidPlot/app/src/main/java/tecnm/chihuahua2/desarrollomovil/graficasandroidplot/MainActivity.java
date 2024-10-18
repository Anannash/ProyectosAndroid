package tecnm.chihuahua2.desarrollomovil.graficasandroidplot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button GPuntosbtn, GPastelbtn, GBarrasbtn,
            GPerzonalizadabtn, GPuntosPbtn, GPastelPbtn;



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


        GBarrasbtn = findViewById(R.id.btnBarras);
        GPastelbtn = findViewById(R.id.btnPastel);
        GPerzonalizadabtn = findViewById(R.id.btnPerzonalizada);
        GPuntosbtn = findViewById(R.id.btnPuntos);
        GPuntosPbtn = findViewById(R.id.btnPuntosP);
        GPastelPbtn = findViewById(R.id.btnPatelP);

        GBarrasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IBarras = new Intent(MainActivity.this,
                        GraficaBarras.class);
                startActivity(IBarras);

            }
        });

        GPuntosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IPuntos= new Intent(MainActivity.this,
                        GraficaPuntos.class);
                startActivity(IPuntos);
            }
        });

        GPerzonalizadabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IPer = new Intent(MainActivity.this,
                        GraficaPerzonalizada.class);
                startActivity(IPer);
            }
        });

        GPuntosPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IPuntosP = new Intent(MainActivity.this,
                        PuntosPerzonalizado.class);
                startActivity(IPuntosP);
            }});

        GPastelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IPastel = new Intent(MainActivity.this,
                        GraficaPastel.class);
                startActivity(IPastel);
            }
        });

        GPastelPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IPastelP = new Intent(MainActivity.this,
                        pastelPersonalizado.class);
                startActivity(IPastelP);
            }
        });


    }
}