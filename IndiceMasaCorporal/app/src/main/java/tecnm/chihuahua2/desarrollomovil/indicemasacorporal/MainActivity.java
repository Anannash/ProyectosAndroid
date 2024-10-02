package tecnm.chihuahua2.desarrollomovil.indicemasacorporal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    //varibales
    EditText EdtPeso, EdtAltura;
    Button Btncalular, Notiboton;
    TextView TxtResultado,TxtIMC;
    Toolbar toolbarM;

    PendingIntent pendingIntent;
    String CHANNEL_ID = "01";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Linear), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;

        });




        //vincular
        EdtAltura = findViewById(R.id.edittxtAltura);
        EdtPeso = findViewById(R.id.edittxtPeso);
        Btncalular = findViewById(R.id.IMCbtn);
        TxtResultado = findViewById(R.id.txtresultado);
        TxtIMC = findViewById(R.id.txtInterpretacion);
        Notiboton = findViewById(R.id.Notibtn);


        toolbarM = findViewById(R.id.toolbar);

        setSupportActionBar(toolbarM);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Noti","Notificacion"
                    , NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }*/

        Notiboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(
                                MainActivity.this)
                        .setContentTitle("Estoy Solito")
                        .setContentText("No tengo nadie aqui a mi lado")
                        .setSmallIcon(R.drawable.baseline_doorbell_24)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService
                                (Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());
            }
        });


        //Agregar escuchador al hacer clo9ck
        Btncalular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float peso,altura,IMC;

                 peso = Float.parseFloat(EdtPeso.getText().toString());
                 altura = Float.parseFloat(EdtAltura.getText().toString());

                if (altura <= 0 || peso <= 0) {
                    Toast.makeText(MainActivity.this,
                            "No puede ser cero", Toast.LENGTH_SHORT).show();
                    return;
                }

                //calcular IMC
                IMC=peso/(altura*altura);

                //mostrar resultado
                TxtResultado.setText(String.format("Resultado: %.2f",IMC));

                actualizacionIMC(IMC);


            }



      });



}//Fin One Create
/*
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


    }




    public void MostrarNotificacion() {
        // Solo crear el canal si la versión es Android 8.0 (API 26) o superior
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "NEW",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        // Mostrar la notificación (en cualquier versión)
        MostrarNuevaNotificacion();
    }

    public void MostrarNuevaNotificacion(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_doorbell_24)
                .setContentTitle("Estoy Solito")
                .setContentText("No tengo nadie aqui a mi lado")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManager manager =   (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Mostrar la notificación (para todas las versiones)
        if (manager != null) {
            manager.notify(1, builder.build());
        }
    }

*/



    //escuchar eventos de los eventos del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.camera) {
            Toast.makeText(this, "Camara", Toast.LENGTH_SHORT).show();
            //abrir camara
            Intent actividadCamara = new Intent(MediaStore.
                    INTENT_ACTION_VIDEO_CAMERA);
            startActivity(actividadCamara);

        } else if (itemId == R.id.settings) {
            Toast.makeText(this, "SCARA", Toast.LENGTH_SHORT).show();
            Intent ScaraIntent;
            ScaraIntent = new Intent(this, Scara.class);
            startActivity(ScaraIntent);

        } else if (itemId == R.id.about) {
            Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show();
            //llamr a la actividad Acerca DE
            Intent actividadAcercade;
            actividadAcercade = new Intent(this, AcercaDe.class);
            startActivity(actividadAcercade);
        }
        return true;
    }

        @Override

        public boolean onCreateOptionsMenu(android.view.Menu menu){

            getMenuInflater().inflate(R.menu.toobar_menu,menu);
            return true;

        }



    public void actualizacionIMC (float IMC){
        if (IMC<18.5){
            TxtIMC.setText(R.string.BP);
            Toast.makeText(MainActivity.this,
                    "ESTAS ANOREXICO MEDICATE DE VERDAD", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(Color.BLUE);


        } else if (IMC >=18.5  && IMC <24.9) {
            TxtIMC.setText(R.string.N);
            Toast.makeText(MainActivity.this,
                    "Eres mongolito, digo normalito", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(Color.GREEN);

        }else if (IMC >=25 && IMC <29.9) {
            TxtIMC.setText(R.string.SP);
            Toast.makeText(MainActivity.this,
                    "VELE BAJANDO A LOS TAMALES", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(
                    ContextCompat.getColor(MainActivity.this,R.color.naranja));

        }else if (IMC >=30 && IMC <34.9 ) {
            TxtIMC.setText(R.string.Ob);
            Toast.makeText(MainActivity.this,
                    "La COCA dietetica NO SIRVE", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(Color.RED);

        }else if (IMC >=35 && IMC <39.9) {
            TxtIMC.setText(R.string.Ob2);
            Toast.makeText(MainActivity.this,
                    "qui qui qui qui oink", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.rosa));

        }else if (IMC >=40) {
            TxtIMC.setText(R.string.Ob3);
            Toast.makeText(MainActivity.this,
                    "Mexico nesesita gordos esquiva dietas como tu", Toast.LENGTH_SHORT).show();
            TxtIMC.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.morado));

        }

    }//fIN acTUALIZACION
}//FIN MAIN