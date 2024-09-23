package tecnm.chihuahua2.desarrollomovil.indicemasacorporal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Scara extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String THEME_KEY = "theme";

    Button Temabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applyTheme();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scara);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Temabtn = findViewById(R.id.Temabtn);



        Temabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarTema() ;
            }
        });

    }
    private void applyTheme() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int themeId = preferences.getInt(THEME_KEY, R.style.ThemeLuz); // Por defecto, tema claro
        setTheme(themeId);

    }
    private void cambiarTema() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Cambiar entre tema claro y oscuro
        int currentTheme = preferences.getInt(THEME_KEY, R.style.ThemeLuz);
        if (currentTheme == R.style.ThemeLuz) {
            editor.putInt(THEME_KEY, R.style.ThemeNoche);
        } else {
            editor.putInt(THEME_KEY, R.style.ThemeLuz);
        }

        // Guardar cambios y reiniciar la actividad para aplicar el nuevo tema
        editor.apply();
        recreate(); // Reinicia la actividad para aplicar el nuevo tema
    }
    


}