package mx.edu.tecnm.chihuahua2.desarrollomovil.sms_sos;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button SMS_SOSbtn;
    private GPSReceiver gpsReceiver;
    private LocationManager locationManager;
    private double latitud = 0, longitud = 0;

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
        //VINCULACION
        SMS_SOSbtn = findViewById(R.id.SOSbtn);

        gpsReceiver = new GPSReceiver();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Defonir parametros
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000l,1.0F,gpsReceiver);

         





    }//FIN ONCREATE

    //clase que funciona como receptor de mensajes
    private class GPSReceiver implements LocationListener {


        //Se incvica al dectectar el cambio de ubucacion
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if(location != null){
                latitud = location.getLatitude();
                longitud = location.getLongitude();

                //Confirma que ua se tuene la ubicacion

                Toast.makeText(MainActivity.this,
                    getString(R.string.PreparadoEnviuar),Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(MainActivity.this,
                        getString(R.string.NoPreparadoEnviuar),Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        public void onProviderDisabled(@NonNull String provider) {
            LocationListener.super.onProviderDisabled(provider);

            //Confirma que el GPS esta deshabilitado
            Toast.makeText(MainActivity.this,
                    getString(R.string.GPSFavor),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            LocationListener.super.onProviderEnabled(provider);

            //Confirma que el GPS esta habilitado
            Toast.makeText(MainActivity.this,
                    getString(R.string.GPSHabilitado),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            LocationListener.super.onStatusChanged(provider, status, extras);


        }

        @Override
        public void onFlushComplete(int requestCode) {
            LocationListener.super.onFlushComplete(requestCode);


        }

        @Override
        public void onLocationChanged(@NonNull List<Location> locations) {
            LocationListener.super.onLocationChanged(locations);
        }
    }
}//FIN DE TODO