package skislope.purkov.elis.gmail.com.skislope;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import skislope.purkov.elis.gmail.com.skislope.model.DataProvider;
import skislope.purkov.elis.gmail.com.skislope.model.ParkingLot;
import skislope.purkov.elis.gmail.com.skislope.model.SkiResort;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private static final long COUNTRY_MAP_ZOOM = 7;
    private static final long RESORT_MAP_ZOOM = 12;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mapTypeNormal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.mapTypeSattelite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set action listeners
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        // Move position to ljubljana and set zoom
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LJUBLJANA_POSITION, COUNTRY_MAP_ZOOM), 1500, null);

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

            // Set new markers for parking lots
            ParkingLot[] parkingLotsArr = skiResorts.get(marker).getParkingLots();
            for (int i = 0; i < parkingLotsArr.length; i++)
                parkingLots.put(putMarker(parkingLotsArr[i]), parkingLotsArr[i]);

            // Move and zoom to selected resort
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), RESORT_MAP_ZOOM), 1500, null);

        }

        // Show info window
        marker.showInfoWindow();

        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        Intent intent;

        if (parkingLots.get(marker) == null) {
            SkiResort skiResort = skiResorts.get(marker);
            intent = new Intent(this, SkiResortDetails.class);
            intent.putExtra("skiResort", skiResorts.get(marker));
        } else {
            ParkingLot parkingLot = parkingLots.get(marker);
            intent = new Intent(this, ParkingLotDetails.class);
            intent.putExtra("parkingLot", parkingLots.get(marker));
        }

        // Start activity
        startActivity(intent);

    }

    public Marker putMarker(SkiResort resort) {
        return mMap.addMarker(new MarkerOptions().position(resort.getLocation()).title(resort.getTitle()));

    }

    public Marker putMarker(ParkingLot parkingLot) {
        return mMap.addMarker(new MarkerOptions().position(parkingLot.getLocation()).title(parkingLot.getTitle())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

    }

}
