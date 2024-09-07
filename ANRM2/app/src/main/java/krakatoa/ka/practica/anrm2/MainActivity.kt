package krakatoa.ka.practica.anrm2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawer:DrawerLayout
    private lateinit var toogle: ActionBarDrawerToggle

    lateinit var btn: Button
    lateinit var Num: EditText
    lateinit var Res:TextView
    var suma:Int = 0





    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar:Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)



        drawer = findViewById(R.id.drawerLayout)

        toogle = ActionBarDrawerToggle(this, drawer,toolbar,R.string.NavigationDR_close)


        //Inizializacion
        btn= findViewById(R.id.BtnS)
        Num = findViewById(R.id.Numerotxt)
        Res= findViewById(R.id.Resultadotxt)





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }

        btn.setOnClickListener{
            var numero = Num.text.toString().toIntOrNull()



            if (numero != null){
                suma+= numero
                Res.setText("$suma")
                Num.text.clear()
            }else{
                Toast.makeText(this,"Introduce un numero valido", Toast.LENGTH_SHORT).show()
            }
        }



    }// Fin del  One Create
}//fin del Main