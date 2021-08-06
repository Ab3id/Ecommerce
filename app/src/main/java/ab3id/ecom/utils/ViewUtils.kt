package ab3id.ecom.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast


    fun Context.customToast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun ProgressBar.show(){
        visibility = View.VISIBLE
    }

    fun ProgressBar.hide(){
        visibility = View.GONE
    }

    fun Context.saveUserLocal (username:String?, role:String?){
        val shareStorage: SharedPreferences = this.getSharedPreferences("app_user_data",Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =  shareStorage.edit()
        editor.putString("username",username);
        editor.putString("role",role)
        editor.apply()
        editor.commit()
    }

fun Context.userLogout(){
    val shareStorage: SharedPreferences = this.getSharedPreferences("app_user_data",Context.MODE_PRIVATE)
    val editor = shareStorage.edit()
    editor.clear()
    editor.apply()
}

fun Context.getUserNameFromLocal():String {
    val shareStorage: SharedPreferences = this.getSharedPreferences("app_user_data",Context.MODE_PRIVATE)
    return shareStorage.getString("username","demo").toString()
}

fun Context.getUserRole():String {
    val shareStorage: SharedPreferences = this.getSharedPreferences("app_user_data",Context.MODE_PRIVATE)
    return shareStorage.getString("role","0").toString()
}

