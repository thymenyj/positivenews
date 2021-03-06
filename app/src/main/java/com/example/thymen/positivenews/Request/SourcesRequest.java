/*
    SourcesRequest calls the api to get a list containing all the news sources.
 */

package com.example.thymen.positivenews.Request;

import android.content.Context;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.thymen.positivenews.Object.NewsSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SourcesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    public Callback activity;
    public Context context;

    public interface Callback {
        void gotSources(ArrayList<NewsSource> sources);
        void gotSourcesError(String message);
    }

    public SourcesRequest(Context context) {
        this.context = context;
    }

    public void getSources(Callback activity) {
        this.activity = activity;
        String url = "https://newsapi.org/v2/sources?apiKey=95523811729048518c1cf1c3da766379";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    public void onResponse(JSONObject response) {
        try {
            JSONArray array = response.getJSONArray("sources");
            int length = array.length();
            ArrayList<NewsSource> sources = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                NewsSource newsSource = new NewsSource();
                JSONObject item = array.getJSONObject(i);
                String name = item.getString("name");
                String url = item.getString("url");
                String description = item.getString("description");

                newsSource.setName(name);
                newsSource.setUrl(url);
                newsSource.setDescription(description);

                sources.add(newsSource);
            }
            activity.gotSources(sources);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("RESPONSE", response.toString());
        }
    }

    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotSourcesError(message);
    }



}
