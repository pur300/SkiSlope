package skislope.purkov.elis.gmail.com.skislope;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import java.util.List;

import skislope.purkov.elis.gmail.com.skislope.model.SkiResort;

public class SkiResortDetails extends AppCompatActivity implements View.OnLongClickListener, PlaceImageSet.OnImagesReceivedListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnTouchListener, ViewSwitcher.ViewFactory {

    // Resol

    private SkiResort skiResort;
    private GoogleApiClient apiClient;
    private PlaceImageSet imageSet;
    private List<Bitmap> placeImages;
    private ImageSwitcher detailsGallery;
    private int currentDetailsGalleryImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ski_resort_details);

        // Get data from intent
        skiResort = getIntent().getParcelableExtra("skiResort");

        // Configure ImageSwitcher
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        detailsGallery = (ImageSwitcher)findViewById(R.id.detailsGallery);
        detailsGallery.setInAnimation(in);
        detailsGallery.setOutAnimation(out);
        detailsGallery.setFactory(this);
        detailsGallery.setOnTouchListener(this);

        // Create PlaceImageSet object
        imageSet = new PlaceImageSet();

        // Set listener
        imageSet.setOnImageReceivedListener(this);

        apiClient = new GoogleApiClient.Builder(this).addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        apiClient.connect();

        // Populate all fields with data and set event listeners for email, telnum and homepage textViews
        ((TextView) findViewById(R.id.detailsTitle)).setText(skiResort.getTitle());
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(this.skiResort.getEmail()).matches()) {
            ((TextView) findViewById(R.id.detailsEmail)).setOnLongClickListener(this);
            ((TextView) findViewById(R.id.detailsEmail)).setText(skiResort.getEmail());
        }
        if (Patterns.WEB_URL.matcher(this.skiResort.getHomePageUrl()).matches()) {
            ((TextView) findViewById(R.id.detailsHomePage)).setOnLongClickListener(this);
            ((TextView) findViewById(R.id.detailsHomePage)).setText(skiResort.getHomePageUrl());
        }
        if (this.skiResort.getTelNum().length() > 0) {
            ((TextView) findViewById(R.id.detailsPhone)).setOnLongClickListener(this);
            ((TextView) findViewById(R.id.detailsPhone)).setText(skiResort.getTelNum());
        }

    }

    @Override
    public boolean onLongClick(View v) {

        if (v == (TextView) findViewById(R.id.detailsEmail)) {
            // Open email application to send email
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", this.skiResort.getEmail(), null));
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } else if (v == ((TextView) findViewById(R.id.detailsPhone)) && this.skiResort.getTelNum().length() > 0) {
            // Make a call
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(this.skiResort.getTelNum().trim()))));
        } else {
            // Visit home page
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(this.skiResort.getHomePageUrl())));
        }

        return false;
    }

    @Override
    public View makeView() {
        ImageView imgView = new ImageView(getApplicationContext());
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imgView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(placeImages.size() > 0){
            detailsGallery.setImageDrawable(new BitmapDrawable(getResources(), placeImages.get((currentDetailsGalleryImageIndex++) % placeImages.size())));
        }
        return false;
    }

    @Override
    public void onImagesReceived(List<Bitmap> placeImages) {
        this.placeImages = placeImages;
        // Set first image
        if(placeImages.size() > 0)
            detailsGallery.setImageDrawable(new BitmapDrawable(getResources(), placeImages.get((currentDetailsGalleryImageIndex++) % placeImages.size())));

    }

    @Override
    public void onConnected(Bundle bundle) {
        imageSet.execute(apiClient, skiResort);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d("yeah", "Connection failed!");

    }

}
