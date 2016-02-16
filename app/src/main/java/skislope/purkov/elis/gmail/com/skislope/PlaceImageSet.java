package skislope.purkov.elis.gmail.com.skislope;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;
import java.util.ArrayList;
import java.util.List;
import skislope.purkov.elis.gmail.com.skislope.model.SkiResort;

public class PlaceImageSet extends AsyncTask<Object, Void, List<Bitmap>> {

    private OnImagesReceivedListener callback;

    public void setOnImageReceivedListener(OnImagesReceivedListener listener) {
        callback = listener;
    }

    @Override
    protected List<Bitmap> doInBackground(Object... params) {

        List<Bitmap> placeImages = new ArrayList<>();

        if(params.length == 2) {

            // Get params
            GoogleApiClient mGoogleApiClient = (GoogleApiClient) params[0];
            SkiResort skiResort = (SkiResort)params[1];

            // Get all places
            PendingResult<AutocompletePredictionBuffer> pendingResult = Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, skiResort.getTitle()
                    , null, null);
            AutocompletePredictionBuffer predictionBuffer = pendingResult.await();

            for (AutocompletePrediction prediction : predictionBuffer) {
                PlacePhotoMetadataResult photoMetaDataResult = Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, skiResort.getPlaceId()).await();
                if (photoMetaDataResult.getStatus().isSuccess()) {
                    PlacePhotoMetadataBuffer photoMetadataBuffer = photoMetaDataResult.getPhotoMetadata();
                    // Iterate through images
                    for (PlacePhotoMetadata photo : photoMetadataBuffer) {
                        // Place resized image into a List
                        placeImages.add(photo.getPhoto(mGoogleApiClient).await().getBitmap());
                    }
                    // Free PlacePhotoMetadataBuffer resource
                    photoMetadataBuffer.release();
                }
            }
            // Free AutocompletePredictionBuffer resource
            predictionBuffer.release();

        }

        return placeImages;

    }

    @Override
    protected void onPostExecute(List<Bitmap> v) {

        // Call callback function
        if(callback != null)
            callback.onImagesReceived(v);

    }

    public interface OnImagesReceivedListener {

        void onImagesReceived(List<Bitmap> placeImages);

    }

}
