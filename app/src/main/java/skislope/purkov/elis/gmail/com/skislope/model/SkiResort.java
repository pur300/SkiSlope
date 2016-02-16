package skislope.purkov.elis.gmail.com.skislope.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by pur300 on 8.2.2016.
 */
public class SkiResort implements Parcelable {

    private String title;
    private String homePageUrl;
    private String wikiUrl;
    private String email;
    private String telNum;
    private ParkingLot[] parkingLots;
    private LatLng location;
    private String placeId;


    private SkiResort() {
    }

    public SkiResort(String title, String homePageUrl, String wikiUrl, String email, String telNum, ParkingLot[] parkingLots, LatLng location, String placeId) {

        this.title = title;
        this.homePageUrl = homePageUrl;
        this.wikiUrl = wikiUrl;
        this.email = email;
        this.telNum = telNum;
        this.parkingLots = parkingLots;
        this.location = location;
        this.placeId = placeId;

    }

    public SkiResort(Parcel in) {

        title = in.readString();
        homePageUrl = in.readString();
        wikiUrl = in.readString();
        email = in.readString();
        telNum = in.readString();
        placeId = in.readString();
        parkingLots = (ParkingLot[]) in.createTypedArray(ParkingLot.CREATOR);
        location = in.readParcelable(LatLng.class.getClassLoader());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(homePageUrl);
        dest.writeString(wikiUrl);
        dest.writeString(email);
        dest.writeString(telNum);
        dest.writeString(placeId);
        dest.writeTypedArray(parkingLots, flags);
        dest.writeParcelable(location, flags);
    }

    public String getTitle() {
        return title;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getTelNum() {
        return telNum;
    }

    public ParkingLot[] getParkingLots() {
        return parkingLots;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getPlaceId() { return placeId; }

    public static final Parcelable.Creator<SkiResort> CREATOR
            = new Parcelable.Creator<SkiResort>() {
        public SkiResort createFromParcel(Parcel in) {
            return new SkiResort(in);
        }

        public SkiResort[] newArray(int size) {
            return new SkiResort[size];
        }
    };

}
