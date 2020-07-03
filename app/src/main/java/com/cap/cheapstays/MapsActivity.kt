package com.cap.cheapstays

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var adapter: ArrayAdapter<String>? = null


    var hotelMarriott : LatLng = LatLng(12.9724, 77.5951)
    val hotelAloft : LatLng = LatLng(12.9372,77.6952)
    val hotelConrad : LatLng = LatLng(12.9755, 77.6205)
    val hotelRenaissance  : LatLng = LatLng(12.9839, 77.5764)
    val hotelLeela : LatLng = LatLng(12.9606, 77.6484)
    val hotelFourSeason : LatLng = LatLng(13.0194, 77.5853)
    val hotelRitz : LatLng = LatLng(12.9680, 77.6016)
    val hotelGrand : LatLng = LatLng(12.9893, 77.7299)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mySearchView = findViewById<View>(R.id.search) as SearchView
        val myList = findViewById<View>(R.id.listview) as ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,resources.getStringArray(R.array.cities))
        myList.adapter = adapter
        mySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mySearchView.clearFocus()
                mySearchView.setQuery("", false)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapter!!.filter.filter(s)
                if (myList.visibility != View.VISIBLE) {
                    myList.visibility = View.VISIBLE

                }
                return true
            }

        })

        myList.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val select: String = myList.getItemAtPosition(0).toString()

                if (select.equals("Bangalore")) {
                    val intent = Intent(this,HotelActivity::class.java)
                    startActivity(intent)
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(R.string.app_name)
                    builder.setMessage("Sorry, No Hotel service for this Location")
                    builder.show()
                }

            }
    }


    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }





    override fun onMapReady(googleMap: GoogleMap) {
       map = googleMap

    map.addMarker(MarkerOptions().position(hotelMarriott).title("Marriott Hotel Bengaluru"))
        map.addMarker(MarkerOptions().position(hotelAloft).title("Aloft Bengaluru Cessna Business Park"))
        map.addMarker(MarkerOptions().position(hotelConrad).title("Conrad Bengaluru"))
        map.addMarker(MarkerOptions().position(hotelRenaissance).title("Renaissance"))
        map.addMarker(MarkerOptions().position(hotelLeela).title("The Leela Palace Bengaluru"))
        map.addMarker(MarkerOptions().position(hotelFourSeason).title("Hotel Four Seasons"))
        map.addMarker(MarkerOptions().position(hotelRitz).title("Hotel Ritz- Carlton"))
        map.addMarker(MarkerOptions().position(hotelGrand).title("Hotel Sheraton Grand"))

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelMarriott, 12.0f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelAloft, 12.0f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelConrad, 12.0f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelRenaissance, 12.0f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelLeela, 12.0f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelFourSeason, 12.0f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelRitz, 12.0f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelGrand, 12.0f))


        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)
        setUpMap()
    }

    override fun onMarkerClick(p0: Marker?) =false

  /*  startActivity(Intent(this,HotelActivity::class.java))
    return true*/

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}