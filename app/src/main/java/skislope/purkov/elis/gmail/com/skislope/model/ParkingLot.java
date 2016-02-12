package skislope.purkov.elis.gmail.com.skislope.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by pur300 on 8.2.2016.
 */
public class ParkingLot implements Parcelable{

    private String title, description;
    private LatLng location;

    private ParkingLot() {
    }

    public ParkingLot(String title, String description, LatLng coordinates) {

        this.title = title;
        this.description = description;
        this.location = coordinates;
    }

    public ParkingLot(Parcel in){

        this.title = in.readString();
        this.description = in.readString();
        this.location = in.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LatLng getLocation() { return this.location; }

    public static final Parcelable.Creator<ParkingLot> CREATOR
            = new Parcelable.Creator<ParkingLot>() {
        public ParkingLot createFromParcel(Parcel in) {
            return new ParkingLot(in);
        }

        public ParkingLot[] newArray(int size) {
            return new ParkingLot[size];
        }
    };

}
