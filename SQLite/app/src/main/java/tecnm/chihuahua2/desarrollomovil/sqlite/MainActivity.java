package tecnm.chihuahua2.desarrollomovil.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //objeto para gestionar una BD SQLite
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;
    //objeto cursor que contiene el resultado de la consulta
    //e indica el registro actual, necesario para operaciuones de edicion
    private Cursor cursor;


    //Objetos para GUI
    ImageButton primeroIBtn;
    ImageButton anteriorIBtn;
    ImageButton siguienteIBtn;
    ImageButton ultimoIBtn;
    ImageButton buscarIBtn;
    ImageButton modificarIBtn;
    ImageButton agregarIBtn;
    ImageButton eliminarIBtn;
    EditText txtId;
    EditText txtNombre;
    EditText txtDesc;



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


        primeroIBtn = findViewById(R.id.PrimeroIBtn);
        anteriorIBtn = findViewById(R.id.AnteriorIBtn);
        siguienteIBtn = findViewById(R.id.SiguienteIBtn);
        ultimoIBtn = findViewById(R.id.Ultimortn);

        buscarIBtn= findViewById(R.id.BuscarIBtn);
        modificarIBtn= findViewById(R.id.ModificarIBtn);
        agregarIBtn= findViewById(R.id.AgreggarIBtn);
        eliminarIBtn= findViewById(R.id.EliminarIBtn);

        txtId= findViewById(R.id.txtId);
        txtNombre= findViewById(R.id.txtNombre);
        txtDesc= findViewById(R.id.txtDesc);

        admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        bd = admin.getWritableDatabase();
        cursor = admin.consultaGra();

        /*Escuchadores y codigo para botones de navegacion
        * ------------------------------------------------------------
        * */

        primeroIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        //Cargar datos
        cargarDatos();

        //Mostrar primer registro

        if(!String.valueOf(cursor.getCount()).equals("0")){
            mostrarRegistro(cursor);

        }

        agregarIBtn.setOnClickListener(view -> {

            admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            bd = admin.getWritableDatabase();
            cursor = admin.consultaGra();

            //obtener datso de los campos de texto

            String id = txtId.getText().toString();
            String nombre = txtNombre.getText().toString();
            String descripcion = txtDesc.getText().toString();

            //Obtener datos
            ContentValues registro = new ContentValues();
            registro.put("id", id);
            registro.put("nombre", nombre);
            registro.put("descripcion", descripcion);

            //insertar registro
            bd.insert("gramineas", null, registro);
            bd.close();

            //Mostrar mendaj3 de confirmacion
            Toast.makeText(this, "Registro agregado", Toast.LENGTH_SHORT).show();

            //cargar datos
            cargarDatos();
            mostrarRegistro(cursor);


        });



    }


    public void cargarDatos(){
        //Declarar e iniciar sesion

        admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);

        bd = admin.getWritableDatabase();
        cursor = admin.consultaGra();


//validar si hay registro
        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
        }

    bd.close();

    }

    public void mostrarRegistro(Cursor cursor){
        if (!String.valueOf(cursor.getCount()).equals("0")) {
            txtId.setText(cursor.getString(0));
            txtNombre.setText(cursor.getString(1));
            txtDesc.setText(cursor.getString(2));

        }else {
            //No hay registros
            txtId.setText("");
            txtNombre.setText("");
            txtDesc.setText("");

        }
    }


}