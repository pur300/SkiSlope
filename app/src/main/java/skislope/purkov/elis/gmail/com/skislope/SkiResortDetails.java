package skislope.purkov.elis.gmail.com.skislope;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import skislope.purkov.elis.gmail.com.skislope.model.SkiResort;

public class SkiResortDetails extends AppCompatActivity implements View.OnTouchListener {

    private SkiResort skiresort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ski_resort_details);

        skiresort = getIntent().getExtras().getParcelable("skiResort");

        // Populate all fields with data and set event listeners for email, telnum and homepage textViews
        ((TextView) findViewById(R.id.detailsTitle)).setText(skiresort.getTitle());
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(this.skiresort.getEmail()).matches()) {
            ((TextView) findViewById(R.id.detailsEmail)).setOnTouchListener(this);
            ((TextView) findViewById(R.id.detailsEmail)).setText(skiresort.getEmail());
        }
        if (Patterns.WEB_URL.matcher(this.skiresort.getHomePageUrl()).matches()) {
            ((TextView) findViewById(R.id.detailsHomePage)).setOnTouchListener(this);
            ((TextView) findViewById(R.id.detailsHomePage)).setText(skiresort.getHomePageUrl());
        }
        if (this.skiresort.getTelNum().length() > 0) {
            ((TextView) findViewById(R.id.detailsPhone)).setOnTouchListener(this);
            ((TextView) findViewById(R.id.detailsPhone)).setText(skiresort.getTelNum());
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v == (TextView) findViewById(R.id.detailsEmail)) {
            // Open email application to send email
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", this.skiresort.getEmail(), null));
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } else if (v == ((TextView) findViewById(R.id.detailsPhone)) && this.skiresort.getTelNum().length() > 0) {
            // Make a call
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(this.skiresort.getTelNum().trim()))));
        } else {
            // Visit home page
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(this.skiresort.getHomePageUrl())));
        }

        return true;
    }
}
