package com.arturomarmolejo.yelpappjamm.views.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturomarmolejo.yelpappjamm.databinding.FragmentRestaurantListBinding
import com.arturomarmolejo.yelpappjamm.utils.BaseFragment
import com.arturomarmolejo.yelpappjamm.utils.UIState
import com.arturomarmolejo.yelpappjamm.views.adapter.BusinessesListAdapter
import com.google.android.gms.location.LocationServices

private const val TAG = "RestaurantListFragment"

class RestaurantListFragment: BaseFragment() {
    private var arePermissionsGranted = false

    private val binding by lazy {
        FragmentRestaurantListBinding.inflate(layoutInflater)
    }

    private val businessesListAdapter by lazy {
        BusinessesListAdapter {
            yelpViewModel.selectedBusinesses = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val permissions = arrayListOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        // checkSelfPermission && requestPermissions come from context
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(), permissions.toTypedArray(), 900)
            }else {
                arePermissionsGranted = true
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(arePermissionsGranted) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
            fusedLocationClient.lastLocation
                .addOnSuccessListener {  location: Location? ->
                    // Got last known location. In some rare situations this can be null
                    location?.let {
                        yelpViewModel.location = location
                        getRestaurantsList()
                        yelpViewModel.getRestaurantsList()
                    } ?: run {
                        Log.e(TAG, "onCreateView: Location is null", )
                    }
                }
        } else Log.e(TAG, "onCreateView: Permissions were not granted", )

        if(yelpViewModel.location != null) getRestaurantsList()

        binding.rvRestaurantList.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = businessesListAdapter
        }

        return binding.root
    }

    private fun getRestaurantsList() {
        yelpViewModel.businesses.observe(viewLifecycleOwner) {state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    Log.d(TAG, "getRestaurantsList: ${state.response}")
                    businessesListAdapter.updateBusinesses(state.response ?: emptyList())
                }
                is UIState.ERROR -> {
                    Log.d(TAG, "getRestaurantsList: UIState ERROR ")
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>, //array container of permissions asked.
        grantResults: IntArray //array container of permissions granted.
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 900 ) {
            grantResults.forEach {
                arePermissionsGranted = it === PackageManager.PERMISSION_GRANTED
            }
        }

    }
}