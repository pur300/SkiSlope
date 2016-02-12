package skislope.purkov.elis.gmail.com.skislope.model;

import android.os.Parcel;
import android.os.Parcelable;

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


    private SkiResort() {
    }

    public SkiResort(String title, String homePageUrl, String wikiUrl, String email, String telNum, ParkingLot[] parkingLots, LatLng location) {

        this.title = title;
        this.homePageUrl = homePageUrl;
        this.wikiUrl = wikiUrl;
        this.email = email;
        this.telNum = telNum;
        this.parkingLots = parkingLots;
        this.location = location;

    }

    public SkiResort(Parcel in) {

        this.title = in.readString();
        this.homePageUrl = in.readString();
        this.wikiUrl = in.readString();
        this.email = in.readString();
        this.telNum = in.readString();
        this.parkingLots = (ParkingLot[]) in.createTypedArray(ParkingLot.CREATOR);
        this.location = in.readParcelable(LatLng.class.getClassLoader());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.title);
        dest.writeString(this.homePageUrl);
        dest.writeString(this.wikiUrl);
        dest.writeString(this.email);
        dest.writeString(this.telNum);
        dest.writeTypedArray(this.parkingLots, flags);
        dest.writeParcelable(this.location, flags);
    }


    public String getTitle() { return title; }

    public String getHomePageUrl() { return homePageUrl; }

    public String getWikiUrl() { return wikiUrl; }

    public String getEmail() { return email; }

    public String getTelNum() { return telNum; }

    public ParkingLot[] getParkingLots() {
        return parkingLots;
    }

    public LatLng getLocation() {
        return location;
    }

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
