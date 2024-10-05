package tecnm.chihuahua2.desarrollomovil.vidavegeta__novelassanderson;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button Peidobtn;

    int[] MetalesDesc = new int[]{
            R.string.TVDescHierro,
            R.string.TVDescAcero,
            R.string.TVDescEstano,
            R.string.TVPeltre,
            R.string.TVZinc,
            R.string.TVLaton,
            R.string.TVDescCobre,
            R.string.TVDescBronce,
            R.string.TVCromo,
            R.string.TVNicrosil,
            R.string.TVAluminio,
            R.string.TVDuralumin,
            R.string.TVCadmio,
            R.string.TVBandaleo,
            R.string.TVOro,
            R.string.TVElectrum,
            R.string.TVAtium,
            R.string.TVEttmetal,
            R.string.TVLerasium
    };

        int[] MetalTitulos = new int[]{
                R.string.Titulos1,
                R.string.Titulos2,
                R.string.Titulos3,
                R.string.Titulos4,
                R.string.Titulos5,
                R.string.Titulos6,
                R.string.Titulos7,
                R.string.Titulos8,
                R.string.Titulos9,
                R.string.Titulos10,
                R.string.Titulos11,
                R.string.Titulos12,
                R.string.Titulos13,
                R.string.Titulos14,
                R.string.Titulos15,
                R.string.Titulos16,
                R.string.Titulos17,
                R.string.Titulos18,
                R.string.Titulos19
        };

        int[] NumberPickers = new int[]{
                R.id.NP1,
                R.id.NP2,
                R.id.NP3,
                R.id.NP4,
                R.id.NP5,
                R.id.NP6,
                R.id.NP7,
                R.id.NP8,
                R.id.NP9,
                R.id.NP10,
                R.id.NP11,
                R.id.NP12,
                R.id.NP13,
                R.id.NP14,
                R.id.NP15,
                R.id.NP16,
                R.id.NP17,
                R.id.NP18,
                R.id.NP19
        };

        int[] Precios = new int[]{
                R.string.PrecioHierrio,
                R.string.PrecioAcero,
                R.string.PrecioEstaño,
                R.string.PrecioPeltre,
                R.string.PrecioZinc,
                R.string.PrecioLaton,
                R.string.PrecioCobre,
                R.string.PrecioBronce,
                R.string.PrecioCromo,
                R.string.PrecioNicrosil,
                R.string.PrecioAluminio,
                R.string.PrecioDuralumin,
                R.string.PrecioCadmio,
                R.string.PrecioBandaleo,
                R.string.PrecioOro,
                R.string.PrecioElectrum,
                R.string.PrecioAtium,
                R.string.PrecioEttmetal,
                R.string.PrecioLerasium
        };




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

        Peidobtn = findViewById(R.id.BTNPedido);

        // Acceder a cada NumberPickery asignar valores mínimos y máximos
        for (int i = 0; i < 19; i++) {
        // Obtener la referencia al NumberPicker actual
            NumberPicker numberPicker = findViewById(NumberPickers[i]);
        // Establecer los valores mínimo y máximo
            if (numberPicker != null) {
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(100);
            }
        }

        Peidobtn.setOnClickListener(v -> {
           int total  = 0;
           String pedido = "";


           for (int i = 0; i < NumberPickers.length; i++) {
               NumberPicker numberPicker = findViewById(NumberPickers[i]);
               int value = numberPicker.getValue();

               if (numberPicker.getValue()>0){
                   //obtenr titulo y descrpcion
                   pedido += getResources().getString(MetalTitulos[i]) + "\n"+
                           getResources().getString(MetalesDesc[i]) ;

                   int preciometal = Integer.parseInt(getResources().getString(Precios[i]));

                   int subtotal = value * preciometal;

                   String unidadPrecio = String.valueOf(value)+"x$"+String.valueOf(preciometal);

                   pedido += "\n" + unidadPrecio;
                   pedido += " = $ " + String.valueOf(subtotal);
                   total+= subtotal;
                   pedido += "\n-------\n";

               }
           }

            pedido += "\n--------------------------------------\n";
            pedido += String.valueOf(total) +"$";

            // Mostrar el pedido en un cuadro de diálogo
            //Toast.makeText(MainActivity.this, pedido, Toast.LENGTH_LONG).show();
            // Abrir actividad Mostrar Pedido y pasar datos del pedido
            Intent mostrar_pedido;
            mostrar_pedido = new Intent(MainActivity.this, MostrarPedido.class);
            mostrar_pedido.putExtra("pedido", pedido);
            startActivity(mostrar_pedido);



        });

    }
}