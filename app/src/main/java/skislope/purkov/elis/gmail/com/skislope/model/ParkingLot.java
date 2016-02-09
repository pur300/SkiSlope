package skislope.purkov.elis.gmail.com.skislope.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by pur300 on 8.2.2016.
 */
public class ParkingLot {

    private String title, description;
    private LatLng location;

    private ParkingLot() {
    }

    public ParkingLot(String title, String description, LatLng coordinates) {

        this.title = title;
        this.description = description;
        this.location = coordinates;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LatLng getLocation() {

        return this.location;
    }

}
