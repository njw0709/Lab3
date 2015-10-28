package com.example.jong.savangerhunt_1;

/**
 * Created by root on 10/27/15.
 */
import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class S3service {
    private JSONArray pathJSON;

    // Constructor
    public S3service(Context context) {

        // Instantiate the RequestQueue
        final RequestQueue queue = Volley.newRequestQueue(context);

        String url = "http://45.55.65.113/scavengerhunt";
        // Request a JSON response from the url
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Get the JSON array of the images
                    pathJSON = response.getJSONArray("path");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("VOLLEY","got the path JSON array");
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error: ","An error occurred in Volley");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }

    public String getVideoURLForStage(int stage) {
        String videoID;
        try {
            videoID = pathJSON.getJSONObject(stage - 1).get("s3id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "INVALID";
        }
        return "https://s3.amazonaws.com/olin-mobile-proto/" + videoID;
    }
    public String getLatitudeForStage(int stage) {
        try {
            return pathJSON.getJSONObject(stage - 1).get("latitude").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "INVALID";
        }
    }
    public String getLongitudeForStage(int stage) {
        try {
            return pathJSON.getJSONObject(stage - 1).get("longitude").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "INVALID";
        }
    }
}
