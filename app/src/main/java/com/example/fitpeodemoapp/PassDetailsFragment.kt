package com.example.fitpeodemoapp

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Geocoder
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController

import com.example.fitpeodemoapp.dropdown.MultiSpinner
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class PassDetailsFragment : Fragment(), MultiSpinner.MultiSpinnerListener {

    private lateinit var multiSpinner: MultiSpinner
    private lateinit var items: List<String>
    private lateinit var textSelected: TextView
    private val REQUEST_CODE = 100
    private lateinit var locationText: TextView
    private var latitude:Double=0.0
    private var longitude:Double=0.0

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val bottomNav=activity?.findViewById<BottomNavigationView>(com.example.fitpeodemoapp.R.id.bottom_navigation)
        bottomNav?.visibility=View.GONE

        return inflater.inflate(
            com.example.fitpeodemoapp.R.layout.fragment_pass_details,
            container,
            false
        )


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        items = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        multiSpinner =
            view.findViewById(com.example.fitpeodemoapp.R.id.multi_spinner) as MultiSpinner
        textSelected = view.findViewById(com.example.fitpeodemoapp.R.id.day_text)
        multiSpinner.setItems(items, "Mon", this@PassDetailsFragment)

        val cancel = view.findViewById(com.example.fitpeodemoapp.R.id.cancel_button) as ImageView
        locationText =
            view.findViewById(com.example.fitpeodemoapp.R.id.location_get_text) as TextView

        cancel.setOnClickListener {
            findNavController().navigateUp()
        }

        locationText.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PERMISSION_GRANTED -> {
                    getLocation()
                }

                else -> {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        REQUEST_CODE
                    )
                }

            }
        }

        val mapText=view.findViewById<TextView>(com.example.fitpeodemoapp.R.id.view_on_map_text)

        mapText.setOnClickListener {
            val uri= Uri.parse("geo:"+latitude.toString()+","+longitude.toString())
            val mapIntent=Intent(Intent.ACTION_VIEW,uri)
            startActivity(mapIntent)
        }


    }

    private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener {
             latitude = it.latitude
             longitude = it.longitude

            Log.e("lat", latitude.toString() + " " + longitude.toString())

            val geoCoder = Geocoder(requireContext(), Locale.getDefault())
            val address = geoCoder.getFromLocation(latitude, longitude, 1)
            Log.e("Location", address[0].locality)
            locationText.text = address[0].locality
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    Log.e("Access denied", "Location permission not granted")
                }
            }
        }
    }

    override fun onItemsSelected(selected: BooleanArray?) {
            var str:String=""
        for (i in 0..selected?.size!! - 1) {
            if (selected[i].equals(true)) {
                Log.i("Check", i.toString())
//                textSelected.append(items[i] + ",")
                 str+=items[i]+","
            }
        }

        str=str.substring(0,str.lastIndexOf(","))
        textSelected.text=str
    }


}