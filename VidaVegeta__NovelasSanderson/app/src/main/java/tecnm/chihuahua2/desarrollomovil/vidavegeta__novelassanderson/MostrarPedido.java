package tecnm.chihuahua2.desarrollomovil.vidavegeta__novelassanderson;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MostrarPedido extends AppCompatActivity {

    Button confirmar_pedido;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_mostrar_pedido);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });

         editText = findViewById(R.id.editText_pedido);
        editText.setEnabled(false);



        Intent intent = getIntent();
        String pedido = intent.getStringExtra("pedido");
        EditText editText = findViewById(R.id.editText_pedido);
        editText.setText(pedido);


        confirmar_pedido = findViewById(R.id.button_confirmar_pedido);


        confirmar_pedido.setOnClickListener(v -> {

            String pedidoConfirmado = editText.getText().toString();

            Intent intentEnviar = new Intent(
                    Intent.ACTION_SEND);
            intentEnviar.setType("text/plain");
            intentEnviar.putExtra(Intent.EXTRA_SUBJECT, "Pedido");
            intentEnviar.putExtra(Intent.EXTRA_TEXT, pedidoConfirmado);
            startActivity(intentEnviar);


        });

    }





}