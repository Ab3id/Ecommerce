package ab3id.ecom.ui.userLogin

import ab3id.ecom.R
import ab3id.ecom.data.api.ApiCalls
import ab3id.ecom.data.api.ApiClient
import ab3id.ecom.models.UserAccount
import ab3id.ecom.ui.MainActivity
import ab3id.ecom.ui.userRegister.UserRegister
import ab3id.ecom.utils.customToast
import ab3id.ecom.utils.getUserNameFromLocal
import ab3id.ecom.utils.saveUserLocal
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        if(getUserNameFromLocal() !== "demo"){
            val regIntent = Intent(this, MainActivity::class.java)
            startActivity(regIntent)
            finish()
        }
        val tvRegister:TextView = findViewById(R.id.tv_createAccount);
        val btnLogin:MaterialButton = findViewById(R.id.btnLogin);
        val etUsername: TextInputEditText = findViewById(R.id.inpLoginUsername);
        val etPassword: TextInputEditText = findViewById(R.id.inpLoginPassword);
        tvRegister.setOnClickListener {
            run {
                val regIntent = Intent(this, UserRegister::class.java)
                startActivity(regIntent)
            }
        }



        btnLogin.setOnClickListener {

            val username:String = etUsername.text.toString().trim();
            val pass:String = etPassword.text.toString().trim();

            if(username.isNotEmpty() && pass.isNotEmpty()){
                //lets call network
                val apiCalls: ApiCalls? = ApiClient().getClient()?.create(ApiCalls::class.java)
                val call: Call<UserAccount> = apiCalls!!.userLogin(username, pass)
                call.enqueue(object : Callback<UserAccount?> {
                    override fun onResponse(
                        call: Call<UserAccount?>?,
                        response: Response<UserAccount?>
                    ) {
                        if (response.isSuccessful) {
                            customToast("Success!, Welcome")
                            val responseUserData:UserAccount? = response.body()
                            saveUserLocal(responseUserData?.username,responseUserData?.userRole)
                            val loginIntent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(loginIntent)
                            finish()
                        }else{
                            customToast(response.message())
                        }
                    }
                    override fun onFailure(call: Call<UserAccount?>?, t: Throwable) {
                        customToast(t.message.toString())
                    }
                })

            }
        }



//        btnLogin.setOnClickListener {
//            run {
//                val mainActivityIntent = Intent(this, MainActivity::class.java);
//                startActivity(mainActivityIntent)
//            }
//        }
    }
}