package skislope.purkov.elis.gmail.com.skislope;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import skislope.purkov.elis.gmail.com.skislope.model.DataProvider;
import skislope.purkov.elis.gmail.com.skislope.model.ParkingLot;
import skislope.purkov.elis.gmail.com.skislope.model.SkiResort;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final short COUNTRY_MAP_ZOOM = 7;
    private static final short RESORT_MAP_ZOOM = 10;
    private static final LatLng LJUBLJANA_POSITION = new LatLng(46.30648, 14.303078);

    private Map<Marker, SkiResort> skiResorts;
    private Map<Marker, ParkingLot> parkingLots;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Init HashMaps
        skiResorts = new HashMap<>();
        parkingLots = new HashMap<>();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set action listeners
        mMap.setOnMarkerClickListener(this);

        // Set position to ljubljana and set zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LJUBLJANA_POSITION));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(COUNTRY_MAP_ZOOM));

        // Populate map with ski resorts
        for (SkiResort resort : DataProvider.getSkiResorts()) {
            skiResorts.put(putMarker(resort), resort);
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        // Draw parking lots if marker is not present in parkingLots hashmap

        if (parkingLots.get(marker) == null) {

            // Remove previous markers
            for (Marker markerKey : parkingLots.keySet())
                markerKey.remove();

            parkingLots.clear();

            // Set new markers
            ParkingLot[] parkingLotsArr = skiResorts.get(marker).getParkingLots();
            for (int i = 0; i < parkingLotsArr.length; i++)
                parkingLots.put(putMarker(parkingLotsArr[i]), parkingLotsArr[i]);

            // Zoom to selected resort
            mMap.animateCamera(CameraUpdateFactory.zoomTo(RESORT_MAP_ZOOM));

        }

        return false;
    }

    public Marker putMarker(SkiResort resort) {
        return mMap.addMarker(new MarkerOptions().position(resort.getLocation()).title(resort.getTitle()).snippet(resort.getDescription()));

    }

    public Marker putMarker(ParkingLot parkingLot) {
        return mMap.addMarker(new MarkerOptions().position(parkingLot.getLocation()).title(parkingLot.getTitle()).snippet(parkingLot.getDescription())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

    }

}
