package ab3id.ecom.ui.fragments.profile

import ab3id.ecom.R
import ab3id.ecom.ui.userLogin.UserLogin
import ab3id.ecom.utils.getUserNameFromLocal
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import ab3id.ecom.utils.userLogout
import android.content.Intent
import android.widget.TextView


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnLogout:MaterialButton = root.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener {
            run {
                root.context.userLogout()
                val intent = Intent(root.context,UserLogin::class.java);
                startActivity(intent)
            }
        }
        val textView: TextView = root.findViewById(R.id.tv_UserName)
        textView.text = root.context.getUserNameFromLocal()

//        profileViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}