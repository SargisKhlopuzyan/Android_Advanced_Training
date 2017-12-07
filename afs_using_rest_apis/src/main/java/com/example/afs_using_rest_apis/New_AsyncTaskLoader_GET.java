package com.example.afs_using_rest_apis;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by sargiskh on 12/7/2017.
 */

public class New_AsyncTaskLoader_GET extends AsyncTaskLoader<KeysValues> {

    private URL url;

    public New_AsyncTaskLoader_GET(Context context, URL url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e("LOG_TAG", "***onStartLoading()");
            forceLoad();
    }

    @Override
    public KeysValues loadInBackground() {
        Log.e("LOG_TAG", "***loadInBackground()");
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        HttpsURLConnection connection = null;
        try {
            // Create connection
            connection = (HttpsURLConnection)(url.openConnection());
            connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            connection.setRequestProperty("Contact-Me", "hathibelagal@example.com");

            Log.e("LOG_TAG", "try* Code: " + connection.getResponseCode() + " Message: " + connection.getResponseMessage());

            if (connection.getResponseCode() == 200) {
                // Success
                // Further processing here

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                JsonReader jsonReader = new JsonReader(inputStreamReader);
                jsonReader.beginObject(); // Start processing the JSON object

                while (jsonReader.hasNext()) { // Loop through all keys

                    String key = jsonReader.nextName(); // Fetch the next key
                    String value = jsonReader.nextString();
                    Log.e("LOG_TAG", "key: " + key + " : value: " + value);

                    keys.add(key);
                    values.add(value);
                }
                jsonReader.close();

            } else {
                // Error handling code goes here
            }

            connection.disconnect();
        } catch (IOException e) {
            Log.e("LOG_TAG", "catch: " + e);
        }

        KeysValues keysValues = new KeysValues(keys, values);
        return keysValues;
    }
}
