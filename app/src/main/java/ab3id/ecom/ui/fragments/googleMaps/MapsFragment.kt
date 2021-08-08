package ab3id.ecom.ui.fragments.googleMaps

import ab3id.ecom.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker


class MapsFragment:Fragment() {

    private lateinit var mapsViewModel: MapsViewModel
    private lateinit var  mMap: GoogleMap
    private lateinit var mapView: MapView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val storeName: String? = arguments?.getString("store_name")
        val storeLat : String? = arguments?.getString("lat")
        val storeLng: String? = arguments?.getString("lng")
        mapsViewModel = ViewModelProvider(this).get(MapsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_maps, container, false)
        mapView = root.findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()

        try {
            MapsInitializer.initialize(this.context)
        }catch (exception: Exception){
            exception.printStackTrace()
        }

        mapView.getMapAsync { gmap ->
            run {
                mMap = gmap
                if(!storeName.isNullOrEmpty() && !storeLat.isNullOrEmpty() && !storeLng.isNullOrEmpty()){
                    val lat:Double = storeLat.toDouble()
                    val lng:Double = storeLng.toDouble();

                    addMarker(lat = lat,long = lng, name = storeName)
                }else{
                    addMarker()
                }
            }
        }
        return root;
    }

    private fun addMarker (lat:Double = -6.819432429195501, long:Double = 39.27480161257706, name:String = "Wool Essentials, kariakoo"){
        //kariakoo -6.819432429195501, 39.27480161257706
        mMap.clear()
        val sydney = LatLng(lat, long)
        mMap.addMarker {
            position(sydney)
            title(name)
        }

        animateCamera(lat,long)
    }

    private fun animateCamera (lat:Double, lng:Double) {
        val coordinate =  LatLng(lat, lng);
        val location:CameraUpdate  = CameraUpdateFactory.newLatLngZoom(
                coordinate, 18.0F
        );
        mMap.animateCamera(location);
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}