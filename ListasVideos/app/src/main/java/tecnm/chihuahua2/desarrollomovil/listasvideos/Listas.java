package tecnm.chihuahua2.desarrollomovil.listasvideos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Listas extends AppCompatActivity {

    Button btnRegresar;
    TextView txtNombre, txtEdad, txtCiudad;
    ImageView ImgUsuario;
    ListView listaUser;

    //deginir datos para l alista usuarios
    String[] ListElements= new String[]{
            "Usuario1: ",
            "Usuario2:",
            "Usuario3:",
            "Usuario4:",
            "Usuario5:"
    };

    String[][] ListElementsDetails= new String[][]{
            {"Xiao", "200", "Liyue"},
            {"Neuvillette", "500", "Fontain"},
            {"Venti", "2,600", "Mondstadt"},
            {"Scaramuche alias 'Tronaculos 3000' ", "500", "Inazuma"},
            {"Kinich", "25", "Natlan / Culiacan"}
    };

    int[] ListaIm= new int[]{
            R.drawable.xio,
            R.drawable.neu,
            R.drawable.venti,
            R.drawable.scara,
            R.drawable.ken
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnRegresar = findViewById(R.id.btnRegresar);
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        txtCiudad = findViewById(R.id.txtCiiudad);
        ImgUsuario = findViewById(R.id.ImgUsuario);
        listaUser = findViewById(R.id.listaUsusarios);

        //Declarar pbjetos  para vincular con la lista
        //final es -----> constante
        final List<String> listElements = new ArrayList<String>
                (Arrays.asList(ListElements));

        //usar el adaptador de datos que permite pasar datos
        //de un objeto tipo list a un elemento listview
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (Listas.this, android.R.layout.simple_list_item_1, listElements);

        listaUser.setAdapter(adapter);


            btnRegresar.setOnClickListener(v -> {
                finish();
            });

            //Agregar escuchador de enventos para el eleemtnos ListView

        listaUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {

                Toast.makeText(Listas.this,
                        "Elemento: "+ i,
                        Toast.LENGTH_SHORT).show();
                //Mostrar los detalles del elemento seleccionado

                txtNombre.setText(getResources().getString(R.string.TXTnombre)
                        +"" +ListElementsDetails[i][0]);
                txtEdad.setText(getResources().getString(R.string.TXTedad)
                        +""+ListElementsDetails[i][1]);
                txtCiudad.setText(getResources().getString(R.string.TXTciudad)
                       +"" +ListElementsDetails[i][2]);
                ImgUsuario.setImageResource( ListaIm[i]);


            }
        });





    }
}