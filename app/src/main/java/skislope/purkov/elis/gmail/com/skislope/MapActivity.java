package skislope.purkov.elis.gmail.com.skislope;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import skislope.purkov.elis.gmail.com.skislope.model.DataProvider;
import skislope.purkov.elis.gmail.com.skislope.model.ParkingLot;
import skislope.purkov.elis.gmail.com.skislope.model.SkiResort;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private Map<String, Marker> skiResorts;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        skiResorts = new HashMap<>();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Populate map with ski resorts and parking lots
        for (SkiResort resort : DataProvider.getSkiResorts()) {
            Marker resortMarker = putMarker(resort);
            skiResorts.put(resortMarker.getId(), resortMarker);
            /*for(ParkingLot parkingLot : resort.getParkingLots())
                putMarker(parkingLot);*/
        }

    }

    public Marker putMarker(SkiResort resort) {
        return mMap.addMarker(new MarkerOptions().position(resort.getLocation()).title(resort.getTitle()).snippet(resort.getDescription()));

    }

    public Marker putMarker(ParkingLot parkingLot){
        return mMap.addMarker(new MarkerOptions().position(parkingLot.getLocation()).title(parkingLot.getTitle()).snippet(parkingLot.getDescription()));

    }
}
