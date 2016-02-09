package skislope.purkov.elis.gmail.com.skislope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import skislope.purkov.elis.gmail.com.skislope.model.SkiResort;

public class SkiResortDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ski_resort_details);

        // Set values sent by main activity to all views
        SkiResort skiResort = getIntent().getExtras().getParcelable("skiResort");

        ((TextView)findViewById(R.id.titleTextView)).setText(skiResort.getTitle());
        ((TextView)findViewById(R.id.homePageTextView)).setText(Html.fromHtml("<a href = \"" + skiResort.getHomePageUrl() + "\">Klikni</a>"));
        ((TextView)findViewById(R.id.wikiPageTextView)).setText(Html.fromHtml("<a href = \"" + skiResort.getWikiUrl() + "\">Klikni</a>"));
        ((TextView)findViewById(R.id.scrollViewDescription)).setText(skiResort.getDescription());
    }
}
