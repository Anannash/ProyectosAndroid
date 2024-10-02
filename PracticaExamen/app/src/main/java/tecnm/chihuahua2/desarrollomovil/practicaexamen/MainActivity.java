package tecnm.chihuahua2.desarrollomovil.practicaexamen;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarM;
    MediaPlayer mediaPlayer;
    WebView webView;
    ImageButton PausarMbtn, ReproducirMbtn;

    private static final String PREFS_NAME = "theme_prefs";
    private static final String THEME_KEY = "theme";
    private static final String CHANNEL_ID = "notification_channel";
    private static final int NOTIFICATION_ID = 1; // notificacion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        // Inicializa MediaPlayer y carga el archivo de audio
        mediaPlayer = MediaPlayer.create(this, R.raw.nier);
        mediaPlayer.setLooping(true);  // Establece el loop
        mediaPlayer.start();  // Inicia la música

        webView = findViewById(R.id.WvNier);

        // Habilitar JavaScript en el WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Asegurar que los links se abran dentro del WebView y no en el navegador externo
        webView.setWebViewClient(new WebViewClient());

        // Cargar la URL de YouTube
        webView.loadUrl("https://www.youtube.com/watch?v=85h_6AeyUwQ&t=16193s");

        toolbarM = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarM);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        PausarMbtn = findViewById(R.id.imageButton);
        ReproducirMbtn = findViewById(R.id.imageButton2);


        PausarMbtn.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();  // Pausa la música
            }

        });

        ReproducirMbtn.setOnClickListener(v -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();  // Reanuda la música
            }

        });


    }

    //escuchar eventos de los eventos del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.CamaraItem) {
            Toast.makeText(this, "Camara", Toast.LENGTH_SHORT).show();
            //abrir camara

            Intent actividadCamara = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivity(actividadCamara);

        }  else if (itemId == R.id.notificacionItem) {
            Toast.makeText(this, "Enviar Notificacion", Toast.LENGTH_SHORT).show();

            showNotification();
            /*
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(
                            MainActivity.this)
                            .setContentTitle("Estoy Solito")
                            .setContentText("No tengo nadie aqui a mi lado")
                            .setSmallIcon(R.drawable.baseline_notifications_24)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService
                            (Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, builder.build());*/

        }





        return true;
    }

    @Override

    public boolean onCreateOptionsMenu(android.view.Menu menu){

        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detén y libera el MediaPlayer cuando se destruya la actividad
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Crear canal de notificación (Android 8.0 y superior)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "Canal de notificaciones";
            String description = "Descripción del canal";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Registrar el canal en el sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    // Método para mostrar la notificación
    private void showNotification() {
        // Construir la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Estoy Solito")
                .setContentText("No tengo nadie aquí a mi lado")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Verifica el permiso de notificación en Android 13 (API 33) o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Solicitar permisos si no están otorgados
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
        }
        // Mostrar la notificación
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    // Pausar el MediaPlayer cuando la actividad entra en estado pausado
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();  // Pausa la música
        }
    }





}