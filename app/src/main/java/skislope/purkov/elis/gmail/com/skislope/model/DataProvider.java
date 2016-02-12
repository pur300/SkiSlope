package skislope.purkov.elis.gmail.com.skislope.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pur300 on 9.2.2016.
 */
public final class DataProvider {

    private static List<SkiResort> skiResorts = new ArrayList<>();

    static {

        addResort("Mariborsko pohorje", "http://www.mariborskopohorje.si", "", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Vzpenjača", "", new LatLng(46.533928, 15.600561)),
                new ParkingLot("Bolfenk", "", new LatLng(46.514446, 15.576524)),
                new ParkingLot("Pisker II", "", new LatLng(46.513060, 15.495825)),
                new ParkingLot("Lobnica", "", new LatLng(46.496259, 15.506791))
        }, new LatLng(46.520992, 15.594694));

        addResort("Krvavec", "http://www.rtc-krvavec.si/si/aktivnosti/pozimi/smucisce", "https://sl.wikipedia.org/wiki/Krvavec", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Bovec", "", new LatLng(46.332673, 13.538684)),
                new ParkingLot("Sella Nevea", "", new LatLng(46.387866, 13.473303))
        }, new LatLng(46.367015, 13.480740));

        addResort("Kanin - Sella Nevea", "", "", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Grad", "", new LatLng(46.284665, 14.495350)),
                new ParkingLot("Zgornja postaja žičnice", "", new LatLng(46.295467, 14.521700))
        }, new LatLng(46.300943, 14.531829));

        addResort("Kranjska gora", "http://www.kr-gora.si/smucisce-kranjska-gora/", "https://sl.wikipedia.org/wiki/RTC_Kranjska_Gora", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Kranjska Gora", "", new LatLng(46.485979, 13.778702)),
                new ParkingLot("Podkoren", "", new LatLng(46.490900, 13.753162))
        }, new LatLng(46.483290, 13.767500));

        addResort("Vogel", "http://www.vogel.si/zima/", "https://sl.wikipedia.org/wiki/Smučišče_Vogel", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Spodnja postaja žič - Ukanc 6", "", new LatLng(46.275619, 13.835516))
        }, new LatLng(46.259695, 13.840130));

        addResort("Cerkno", "http://www.hotel-cerkno.si/sl/smucanje.html", "https://sl.wikipedia.org/wiki/Smučarski_center_Cerkno", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Počivališče", "", new LatLng(46.154970, 14.049640)),
                new ParkingLot("Davča (Brdo)", "", new LatLng(46.175695, 14.046588))
        }, new LatLng(46.168506, 14.059751));

        addResort("Rogla", "http://www.rogla.eu/si/nacrtujte-obisk", "https://sl.wikipedia.org/wiki/Smučišče_Rogla", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Rogla", "", new LatLng(46.452322, 15.330403))
        }, new LatLng(46.457975, 15.333690));

        addResort("Golte", "http://www.golte.si/slo/smucisce/smucarski-center", "https://sl.wikipedia.org/wiki/Golte", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Radegunda", "", new LatLng(46.356299, 14.931751)),
                new ParkingLot("Golte Hotel", "", new LatLng(46.368765, 14.894739))
        }, new LatLng(46.372360, 14.890194));

        addResort("Stari Vrh", "http://www.starivrh.si/", "https://sl.wikipedia.org/wiki/Smučišče_Stari_vrh", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Šestsedežnica", "", new LatLng(46.183102, 14.191529)),
                new ParkingLot("Sankališče", "", new LatLng(46.169571, 14.191135))
        }, new LatLng(46.175914, 14.185133));

        addResort("Soriška planina", "http://www.soriska-planina.si/#", "https://sl.wikipedia.org/wiki/Soriška_planina", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Brunarica", "", new LatLng(46.240909, 14.010432))
        }, new LatLng(46.236234, 14.007402));

        addResort("Velika planina", "http://www.velikaplanina.si/Zimske-aktivnosti/Smucanje", "https://sl.wikipedia.org/wiki/Velika_planina#smu.C4.8Di.C5.A1.C4.8De", "test@test.si", "040417957", new ParkingLot[]{
                new ParkingLot("Nihalka", "", new LatLng(46.305440, 14.609047))
        }, new LatLng(46.302460, 14.630631));

    }

    private static void addResort(String title, String homePageUrl, String wikiUrl, String email, String telNum, ParkingLot[] parkingLots, LatLng location) {

        skiResorts.add(new SkiResort(title, homePageUrl, wikiUrl, email, telNum, parkingLots, location));

    }

    public static List<SkiResort> getSkiResorts() {
        return skiResorts;
    }

}
