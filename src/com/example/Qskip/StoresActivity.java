package com.example.Qskip;

/**
 * Created with IntelliJ IDEA.
 * User: akash
 * Date: 11/15/13
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoresActivity extends Activity {
    private  double latitude;
    private double longitude;
    private String url = "http://qskipm.appspot.com/getStores?";
    private ProgressBar progressbar = null;
    private ListView storeListView = null;
    private ArrayList<Stores> storesList = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearbystores);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        Bundle extras = getIntent().getExtras();
        latitude = Double.parseDouble(extras.getString("lat"));
        longitude =  Double.parseDouble(extras.getString("lon"));
        System.out.println("Latitude:"+ latitude + " Longitude:" + longitude);

        new GetfromServerTask().execute(url);
    }

    private class GetfromServerTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Void result) {
            if (storesList != null) {
                updateList();
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];

            // getting JSON string from URL
            storesList = getStoresNearby(url);
            return null;
        }
    }

    public void updateList() {
        storeListView= (ListView) findViewById(R.id.custom_list);
        storeListView.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);

        storeListView.setAdapter(new ListAdapter(this, storesList));
        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = storeListView.getItemAtPosition(position);
                Stores stData = (Stores) o;

               Intent intent = new Intent(StoresActivity.this, MenuActivity.class);
               intent.putExtra("store", stData);
               startActivity(intent);
            }
        });
    }
    public ArrayList<Stores> getStoresNearby(String url) {
        InputStream is = null;
        JSONObject jObj = null;
        String json = null;

        ArrayList<Stores> stList = new ArrayList<Stores>();
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            url = url+"lat="+latitude+"&long="+longitude;
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String l = null;
            Gson gson = new Gson();
            while ((l = in.readLine()) ==null){


            }
            Type t = new TypeToken<ArrayList<Stores>>(){}.getType();
            stList = (ArrayList<Stores>) gson.fromJson(l, t);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return JSON String
        return stList;

    }

}