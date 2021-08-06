package ab3id.ecom.ui.userRegister

import ab3id.ecom.R
import ab3id.ecom.data.api.ApiCalls
import ab3id.ecom.data.api.ApiClient

import ab3id.ecom.models.UserAccount
import ab3id.ecom.ui.userLogin.UserLogin
import ab3id.ecom.utils.customToast
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRegister : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
        val userLogin:TextView = findViewById(R.id.tv_AccountLogin);
        val etUsername:TextInputLayout = findViewById(R.id.inpRegisternUsername);
        val etPassword:TextInputLayout = findViewById(R.id.inpRegisterPassword);
        val etPasswordConfirm:TextInputLayout = findViewById(R.id.inpRegisterPasswordConfirm);

        val registerBtn:MaterialButton = findViewById(R.id.btn_register);

        registerBtn.setOnClickListener {
            val username:String = etUsername.editText?.text.toString().trim();
            val pass:String = etPassword.editText?.text.toString().trim();
            val pa:String = etPasswordConfirm.editText?.text.toString().trim();
            if(username.isNullOrEmpty()){
                etUsername.error = "Required"

            }else{
                etUsername.error = ""
            }
            if (pass.isNullOrEmpty()){
                etPassword.error="Required"
            }else{
                etPassword.error =""
            }
            if(pa.isNullOrEmpty()){
                etPasswordConfirm.error = "Required"
            }else{
                etPasswordConfirm.error =""
            }

            if(pass == pa && pass.isNotEmpty() && username.isNotEmpty()){
                //lets call network
            val apiCalls: ApiCalls? = ApiClient().getClient()?.create(ApiCalls::class.java)
                val call: Call<UserAccount> = apiCalls!!.userRegister(username, pa)

                call.enqueue(object : Callback<UserAccount?> {
                    override fun onResponse(
                        call: Call<UserAccount?>?,
                        response: Response<UserAccount?>
                    ) {
                        if (response.isSuccessful) {
                            customToast("Success Please Login")
                            val loginIntent = Intent(applicationContext, UserLogin::class.java)
                            startActivity(loginIntent)
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<UserAccount?>?, t: Throwable) {
                        customToast(t.message.toString())
                    }
                })

            }else{
                customToast("username"+username+"pas1"+pass+"PAS2"+pa)
            }
        }
        userLogin.setOnClickListener {
            run {
                val loginIntent = Intent(this, UserLogin::class.java)
                startActivity(loginIntent)
            }
        }
    }




}