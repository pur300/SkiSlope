package skislope.purkov.elis.gmail.com.skislope.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by pur300 on 8.2.2016.
 */
public class SkiResort {

    private String title;
    private String description;
    private String homePageUrl;
    private String wikiUrl;
    private ParkingLot[] parkingLots;
    private LatLng location;


    private SkiResort() {
    }

    public SkiResort(String title, String description, String homePageUrl, String wikiUrl, ParkingLot[] parkingLots, LatLng location) {

        this.title = title;
        this.description = description;
        this.homePageUrl = homePageUrl;
        this.wikiUrl = wikiUrl;
        this.parkingLots = parkingLots;
        this.location = location;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public ParkingLot[] getParkingLots() {
        return parkingLots;
    }

    public LatLng getLocation() {
        return location;
    }

}
