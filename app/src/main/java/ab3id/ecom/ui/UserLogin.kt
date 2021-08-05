package ab3id.ecom.ui

import ab3id.ecom.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class UserLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        val tvRegister:TextView = findViewById(R.id.tv_createAccount);
        val btnLogin:MaterialButton = findViewById(R.id.btnLogin);
        tvRegister.setOnClickListener {
            run {
                val regIntent = Intent(this, UserRegister::class.java)
                startActivity(regIntent)
            }
        }

        btnLogin.setOnClickListener {
            run {
                val mainActivityIntent = Intent(this, MainActivity::class.java);
                startActivity(mainActivityIntent)
            }
        }
    }
}