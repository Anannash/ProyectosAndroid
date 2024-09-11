package tecnm.chihuahua2.desarrollomovil.lanzarmoneda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AcercaDe extends AppCompatActivity {

    Button Regresarbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acerca_de);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linear), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });//fin oview

        Regresarbtn = findViewById(R.id.button);

        //escuchador
        Regresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cerrar actividad
                finish();
            }
        });


    }//fin onCreate
}//FIN FIN