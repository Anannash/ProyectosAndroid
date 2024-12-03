package mx.edu.tecnm.chihuahua2.desarrollomovil.permisosejecucion;


import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Para identificar el requerimiento de permisos
    private static final int PERMISSION_REQUEST_CODE = 200;

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button revisarBtn = (Button) findViewById(R.id.RevisarBTN);
        Button solicitarBtn = (Button) findViewById(R.id.SolicitarBTN);
        revisarBtn.setOnClickListener(this);
        solicitarBtn.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });


    }
    @Override
    public void onClick(View v){
        view =v;

        int id=v.getId();
        if (id == R.id.RevisarBTN) {
            if (checarPermisos()) {
                Snackbar.make(view, "Permisos concedidos", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(view, "Permisos denegados", Snackbar.LENGTH_LONG).show();
            }
        } else if (id == R.id.SolicitarBTN) {
            if (checarPermisos()) {
                solicitarPermisos();
            } else {
                Snackbar.make(view, "Permisos ya concedidos", Snackbar.LENGTH_LONG).show();
            }
        }

    }

    private boolean checarPermisos() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

        return result == PackageManager.PERMISSION_GRANTED
                && result1 == PackageManager.PERMISSION_GRANTED;

    }

    private void solicitarPermisos(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION,CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length >0){
                    boolean locacionAceptada = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean camaraAceptada = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(locacionAceptada && camaraAceptada){
                        Snackbar.make(view,"Permisos concedidos, ahora puedes acceder a la camara y la ubicacion",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(view,"Permisos no concedidos, NO puedes acceder a la camara y la ubicacion",Snackbar.LENGTH_LONG).show();
                    }

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                        if(shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)){
                            showMessageOKCancel("Debes permitir el acceso a ambos permisos",
                                    new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{ACCESS_FINE_LOCATION,CAMERA},
                                                PERMISSION_REQUEST_CODE);
                                        }}});return;}}}break;}}


    private void showMessageOKCancel(String messege, DialogInterface.OnClickListener okListener){
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(messege)
                .setPositiveButton("OK",okListener)
                .setNegativeButton("Cancelar",null)
                .create().show();
    }

}//FINAL DE TODO

