package tecnm.chihuahua2.desarrollomovil.listasvideos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Videos extends AppCompatActivity {

    Button btnRegresar;
    VideoView videoView;
    ImageButton RegredarImbtn, PlayImbtn, PausaImbtn, NextImbtn;
    Button V3btn, V2btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRegresar = findViewById(R.id.regresarbtn);
        videoView = findViewById(R.id.videoView);
        RegredarImbtn = findViewById(R.id.RegredarImbtn);
        PlayImbtn = findViewById(R.id.PlayImbtn);
        PausaImbtn = findViewById(R.id.PausaImbtn);
        NextImbtn = findViewById(R.id.NextImbtn);
        V3btn = findViewById(R.id.V3btn);
        V2btn = findViewById(R.id.V2btn);

        btnRegresar.setOnClickListener(v -> {
            finish();
        });

        //Agregar escuchador de ventos para cada boton

        V3btn.setOnClickListener(v -> {

            //Acceder al objeto videoView y establecer la ruta del video a reproducir

            videoView.setVideoPath("android.resource://"
                    + getPackageName() + "/" +
                    R.raw.nudomono);
            videoView.start();

        });

        V2btn.setOnClickListener(v -> {

            //Acceder al objeto videoView y establecer la ruta del video a reproducir

            videoView.setVideoPath("android.resource://"
                    + getPackageName() + "/" +
                    R.raw.papaute);
            videoView.start();

        });

        PlayImbtn.setOnClickListener(v -> {
            videoView.start();
        });

        PausaImbtn.setOnClickListener(v -> {
            videoView.pause();
        });




    }
}