package tecnm.chihuahua2.desarrollomovil.indicemasacorporal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AcercaDe extends AppCompatActivity {
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acerca_de);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Linear), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });//fin del enalbe
        //vincular objeto java con el xml
        btnRegresar = findViewById(R.id.btnVolver);
        //evento click boton volver
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });






    }//fin del oncreate
}// fin de la clase
