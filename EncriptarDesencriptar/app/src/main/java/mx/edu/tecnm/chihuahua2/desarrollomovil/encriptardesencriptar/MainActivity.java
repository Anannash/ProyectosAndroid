package mx.edu.tecnm.chihuahua2.desarrollomovil.encriptardesencriptar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {
    EditText editText_encriptar;
    EditText editText_desencriptar;
    Button button_encriptar, button_desencriptar;

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

        editText_encriptar = findViewById(R.id.editText_encrypt);
        editText_desencriptar = findViewById(R.id.editText_desencrypt);
        button_encriptar = findViewById(R.id.button_encriptar);
        button_desencriptar = findViewById(R.id.button_desencriptar);

        button_encriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encryptedText = null;
                try {
                    encryptedText = EncypDescryp.encrypt(
                            editText_encriptar.getText().toString());
                } catch (NoSuchPaddingException | NoSuchAlgorithmException |
                         IllegalBlockSizeException | BadPaddingException |
                         InvalidAlgorithmParameterException | InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
                editText_desencriptar.setText(encryptedText);
            }
        });
        button_desencriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String decryptedText =
                            EncypDescryp.decrypt(
                                    editText_desencriptar.getText().toString());
                    editText_encriptar.setText(decryptedText);
                }
                catch(Exception e){
                    e.printStackTrace();
                }}});
    }
}