package ab3id.ecom.ui

import ab3id.ecom.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UserRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
        val userLogin:TextView = findViewById(R.id.tv_AccountLogin);
        userLogin.setOnClickListener {
            run {
                val loginIntent = Intent(this, UserLogin::class.java)
                startActivity(loginIntent)
            }
        }
    }
}