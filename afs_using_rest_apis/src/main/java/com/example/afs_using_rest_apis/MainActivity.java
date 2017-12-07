package com.example.afs_using_rest_apis;

import android.content.Context;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<KeysValues> {

    private ProgressBar progressBar;
    private ListView listViewKeys;
    private ListView listViewValues;

    String stringURL = "https://api.github.com/";
    private final static int LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        listViewKeys = findViewById(R.id.list_view_keys);
        listViewValues = findViewById(R.id.list_view_values);

//        getSupportLoaderManager().initLoader(LOADER_ID, null, callbacks).forceLoad();;
    }

    public void executeAsyncTaskLoader_GET(View view) {
        Log.e("LOG_TAG", "executeAsyncTaskLoader_GET");
        progressBar.setVisibility(View.VISIBLE);
        getSupportLoaderManager().initLoader(LOADER_ID, null, callbacks);;
    }

    public void executeAsyncTask_GET(View view) {
        AsyncTask_GET asyncTaskGet = new AsyncTask_GET();
        // Create URL
        try {
            URL githubEndpoint = new URL("https://api.github.com/");
            asyncTaskGet.execute(githubEndpoint);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void executeAsyncTask_POST(View view) {
        AsyncTask_POST asyncTaskPOST = new AsyncTask_POST();
        // Create URL
        try {
            URL httpbinEndpoint = new URL("https://httpbin.org/post");
            asyncTaskPOST.execute(httpbinEndpoint);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void executeAsyncTask_Cache(View view) {
        AsyncTask_Cache asyncTask_Cache = new AsyncTask_Cache();
        // Create URL
        try {
            URL githubEndpoint = new URL("https://api.github.com/");
            asyncTask_Cache.execute(githubEndpoint);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Loader<KeysValues> onCreateLoader(int id, Bundle args) {

        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new New_AsyncTaskLoader_GET(MainActivity.this, url);
    }

    @Override
    public void onLoadFinished(Loader<KeysValues> loader, KeysValues keysValues) {

        progressBar.setVisibility(View.INVISIBLE);
        Log.e("LOG_TAG", "onLoadFinished");

        ArrayAdapter<String> adapterKeys = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.keys);
        listViewKeys.setAdapter(adapterKeys);

        ArrayAdapter<String> adapterValues = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.values);
        listViewValues.setAdapter(adapterValues);
    }

    @Override
    public void onLoaderReset(Loader<KeysValues> loader) {
        progressBar.setVisibility(View.INVISIBLE);
        Log.e("LOG_TAG", "onLoaderReset");

        ArrayAdapter<String> adapterKeys = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listViewKeys.setAdapter(adapterKeys);

        ArrayAdapter<String> adapterValues = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listViewValues.setAdapter(adapterValues);
    }

    // must be static or in new .class file
    public class AsyncTaskLoader_GET extends AsyncTaskLoader<KeysValues> {

        public AsyncTaskLoader_GET(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            Log.e("LOG_TAG", "onStartLoading()");
            progressBar.setVisibility(View.VISIBLE);
//            forceLoad();
        }

        @Override
        public KeysValues loadInBackground() {
            List<String> keys = new ArrayList<>();
            List<String> values = new ArrayList<>();

            HttpsURLConnection connection = null;
            try {
                // Create connection
                URL url = new URL(stringURL);
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

    private class AsyncTask_GET extends AsyncTask<URL, Integer, KeysValues> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            Log.e("LOG_TAG", "Download started");
        }

        @Override
        protected KeysValues doInBackground(URL... urls) {

            List<String> keys = new ArrayList<>();
            List<String> values = new ArrayList<>();

            HttpsURLConnection connection = null;
            try {
                // Create connection
                connection = (HttpsURLConnection)(urls[0].openConnection());
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

                        /*
                        if (key.equals("organization_url")) { // Check if desired key

                            // Fetch the value as a String
                            String value = jsonReader.nextString();
                            Log.e("LOG_TAG", "try: value: " + value);
                            // Do something with the value
                            // ...

                            break; // Break out of the loop
                        } else {
                            jsonReader.skipValue(); // Skip values of other keys
                        }
                        */
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

        @Override
        protected void onPostExecute(KeysValues keysValues) {
            super.onPostExecute(keysValues);
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("LOG_TAG", "Download finished");

//            if(isCancelled()) {
//                return;
//            }

            ArrayAdapter<String> adapterKeys = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.keys);
            listViewKeys.setAdapter(adapterKeys);

            ArrayAdapter<String> adapterValues = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.values);
            listViewValues.setAdapter(adapterValues);
        }
    }

    private class AsyncTask_POST extends AsyncTask<URL, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            Log.e("LOG_TAG", "Download started");
        }

        @Override
        protected String doInBackground(URL... urls) {

            String responseMessage = "";

            HttpsURLConnection connection = null;
            try {
                // Create connection
                connection = (HttpsURLConnection)(urls[0].openConnection());
                connection.setRequestMethod("POST");

//                connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
//                connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
//                connection.setRequestProperty("Contact-Me", "hathibelagal@example.com");

                // Enable writing
                connection.setDoInput(true);

                // Create the data
                String data = "message=Hello";

                // Write the data
                connection.getOutputStream().write(data.getBytes());
                responseMessage = connection.getResponseMessage();

                connection.disconnect();
            } catch (IOException e) {
                Log.e("LOG_TAG", "catch: " + e);
            }

            return responseMessage;
        }

        @Override
        protected void onPostExecute(String responseMessage) {
            super.onPostExecute(responseMessage);
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("LOG_TAG", "Download finished with message : " + responseMessage);

            if(isCancelled()) {
                return;
            }
        }
    }

    private class AsyncTask_Cache extends AsyncTask<URL, Integer, KeysValues> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            Log.e("LOG_TAG", "Download started");
        }

        @Override
        protected KeysValues doInBackground(URL... urls) {

            List<String> keys = new ArrayList<>();
            List<String> values = new ArrayList<>();

            HttpResponseCache cache = null;
            try {
                cache = HttpResponseCache.install(getCacheDir(), 100000L);
                Log.e("LOG_TAG", "try: cache");
            } catch (IOException e) {
                Log.e("LOG_TAG", "catch: cache");
                e.printStackTrace();
            }

            HttpsURLConnection connection = null;
            try {
                // Create connection
                connection = (HttpsURLConnection)(urls[0].openConnection());
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

                        /*
                        if (key.equals("organization_url")) { // Check if desired key

                            // Fetch the value as a String
                            String value = jsonReader.nextString();
                            Log.e("LOG_TAG", "try: value: " + value);
                            // Do something with the value
                            // ...

                            break; // Break out of the loop
                        } else {
                            jsonReader.skipValue(); // Skip values of other keys
                        }
                        */
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

            if (cache != null && cache.getHitCount() > 0) {
                Log.e("LOG_TAG", "cache: " + cache.getHitCount());
                // The cache is working
            } else {
                Log.e("LOG_TAG", "cache: null");
            }

            return keysValues;
        }

        @Override
        protected void onPostExecute(KeysValues keysValues) {
            super.onPostExecute(keysValues);
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("LOG_TAG", "Download finished");

            if(isCancelled()) {
                return;
            }

            ArrayAdapter<String> adapterKeys = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.keys);
            listViewKeys.setAdapter(adapterKeys);
            ArrayAdapter<String> adapterValues = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.values);
            listViewValues.setAdapter(adapterValues);
        }
    }


    private LoaderManager.LoaderCallbacks<KeysValues> callbacks =  new LoaderManager.LoaderCallbacks<KeysValues>() {

        URL url = null;
        @Override
        public Loader<KeysValues> onCreateLoader(int i, Bundle bundle) {
            Log.e("LOG_TAG", "***callbacks: onCreateLoader()");
            try {
                url = new URL(stringURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return new New_AsyncTaskLoader_GET(MainActivity.this, url);
        }

        @Override
        public void onLoadFinished(Loader<KeysValues> loader, KeysValues keysValues) {
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("LOG_TAG", "***callbacks: onLoadFinished()");

            ArrayAdapter<String> adapterKeys = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.keys);
            listViewKeys.setAdapter(adapterKeys);

            ArrayAdapter<String> adapterValues = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, keysValues.values);
            listViewValues.setAdapter(adapterValues);
        }

        @Override
        public void onLoaderReset(Loader<KeysValues> loader) {
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("LOG_TAG", "***callbacks: onLoaderReset()");

            ArrayAdapter<String> adapterKeys = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new ArrayList<String>());
            listViewKeys.setAdapter(adapterKeys);

            ArrayAdapter<String> adapterValues = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new ArrayList<String>());
            listViewValues.setAdapter(adapterValues);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
