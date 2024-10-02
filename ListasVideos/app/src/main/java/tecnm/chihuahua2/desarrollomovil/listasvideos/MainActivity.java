package tecnm.chihuahua2.desarrollomovil.listasvideos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnListas, btnVideos;

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

        btnListas = findViewById(R.id.btnListas);
        btnVideos = findViewById(R.id.btnVideos);

        btnListas.setOnClickListener(v -> {
            startActivity(new Intent(this, Listas.class));
        });

        btnVideos.setOnClickListener(v -> {
            startActivity(new Intent(this, Videos.class));
        });

        


    }
}