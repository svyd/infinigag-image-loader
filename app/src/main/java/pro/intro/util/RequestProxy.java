package pro.intro.util;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

import pro.intro.Config;


/**
 * Created by vsvydenko on 27.08.14.
 */
public class RequestProxy {

    private RequestQueue mRequestQueue;
    private RequestQueue mImageLoaderQueue;

    // package access constructor
    RequestProxy(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        mImageLoaderQueue = Volley.newRequestQueue(context.getApplicationContext());

        ImageManager.initializeWith(context.getApplicationContext(), mImageLoaderQueue);
    }

    public void trendingImages(String page,
            Response.Listener listener, Response.ErrorListener errorListener) {
        // coub request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Config.TRENDING_URL + page,
                null,
                listener,
                errorListener
        );
        jsonObjectRequest.setRetryPolicy(setTimeoutPolicy(15000));
        mRequestQueue.add(jsonObjectRequest);
    }

    private RetryPolicy setTimeoutPolicy(int timeInMilisec) {

        return new DefaultRetryPolicy(timeInMilisec,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

}
