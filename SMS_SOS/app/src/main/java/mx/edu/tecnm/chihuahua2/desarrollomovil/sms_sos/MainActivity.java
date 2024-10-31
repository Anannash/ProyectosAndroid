package mx.edu.tecnm.chihuahua2.desarrollomovil.sms_sos;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;
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
    private int contadorMensajesExitosos = 0;
    private TextView contadortextView;
    private static final int PERMISSION_REQUEST_CODE = 100;

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

        // VINCULACION
        SMS_SOSbtn = findViewById(R.id.SOSbtn);
        contadortextView = findViewById(R.id.contadorTV);

        gpsReceiver = new GPSReceiver();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Verificar si los permisos ya han sido concedidos
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Solicitar permisos
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
        } else {
            iniciarUbicacion();
        }

        // Configurar el botón para enviar SMS SOS
        SMS_SOSbtn.setOnClickListener(view -> {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                String phonenumber = "6145994178";
                String message = "Mensaje de auxilio, Me encuentro en la latitud: " + latitud + " y longitud: " + longitud;

                // Enviar mensaje
                smsManager.sendTextMessage(phonenumber, null, message, null, null);

                // Incrementar el contador de mensajes enviados con éxito
                contadorMensajesExitosos++;

                // Actualizar el TextView con el nuevo valor del contador
                contadortextView.setText(getString(R.string.ContadorSMS) + contadorMensajesExitosos);

                Toast.makeText(MainActivity.this, getString(R.string.Enviado), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, getString(R.string.FalloEnvio), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Manejo de respuesta de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarUbicacion();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para iniciar la obtención de la ubicación
    private void iniciarUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0F, gpsReceiver);

            // Usar última ubicación conocida como fallback
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                latitud = lastKnownLocation.getLatitude();
                longitud = lastKnownLocation.getLongitude();
            }
        }
    }

    // Clase que funciona como receptor de mensajes GPS
    private class GPSReceiver implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            if (location != null) {
                latitud = location.getLatitude();
                longitud = location.getLongitude();

                Toast.makeText(MainActivity.this, getString(R.string.PreparadoEnviuar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.NoPreparadoEnviuar), Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onProviderDisabled(@NonNull String provider) {
            Toast.makeText(MainActivity.this, getString(R.string.GPSFavor), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            Toast.makeText(MainActivity.this, getString(R.string.GPSHabilitado), Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // No se requiere implementación adicional por ahora
        }
        @Override
        public void onFlushComplete(int requestCode) {
            // No se requiere implementación adicional por ahora
        }
        @Override
        public void onLocationChanged(@NonNull List<Location> locations) {
            // No se requiere implementación adicional por ahora
        }
    }
}//FIN DE TODO