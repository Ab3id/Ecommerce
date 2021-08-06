package ab3id.ecom.ui.fragments.shopAdmin

import ab3id.ecom.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminFragment  : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_shop_admin, container, false)
        val fabAddProduct:FloatingActionButton = root.findViewById(R.id.fabAddProduct);

        fabAddProduct.setOnClickListener {
            run {
                Toast.makeText(root.context, "Admin Add Products", Toast.LENGTH_SHORT).show()
            }
        }
        return root;
    }
}