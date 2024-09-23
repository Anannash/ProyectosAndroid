package tecnm.chihuahua2.desarrollomovil.intencionesandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button btnAbrirPaginaWeb;
    Button btnLlamada;
    Button btnGoogleMaps;
    Button btnGoogleMapsCalle;
    Button btnTomarFoto;
    Button btnMandarMensaje;

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

        btnAbrirPaginaWeb = findViewById(R.id.PaginaWebtn);
        btnLlamada = findViewById(R.id.Telbtn);
        btnGoogleMaps = findViewById(R.id.GoogleMbtn);
        btnGoogleMapsCalle = findViewById(R.id.GoogleMCbtn);
        btnTomarFoto = findViewById(R.id.Fotobtn);



        btnAbrirPaginaWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentPW =
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.chihuahua2.tecnm.mx/"));

                startActivity(intentPW);

            }
        });

        btnLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentLL = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:6145994178"));
                startActivity(intentLL);

            }
        });

        btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGM = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=Chihuahua"));
                startActivity(intentGM);
            }
        });

        btnGoogleMapsCalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGMc = new Intent(Intent.ACTION_VIEW,Uri.parse("google.streetview:cbll=28.7245144,-106.1081964"));
                startActivity(intentGMc);
            }
        });

        //Abrir camara

        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTF = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intentTF);
            }
        });


        btnMandarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMM = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                intentMM.putExtra(Intent.EXTRA_SUBJECT, "Mandar mensaje desde nuestra app");
                intentMM.putExtra(Intent.EXTRA_TEXT, "Este es un mensaje enviado desde mi telefono");

                // Agregar destinatarios
                intentMM.putExtra(Intent.EXTRA_EMAIL, new String[]{"aome2005.kirara7@gmail.com", "myrandy22@hotmail.com"});

                if (intentMM.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentMM);
                }
            }
        });


    }
}