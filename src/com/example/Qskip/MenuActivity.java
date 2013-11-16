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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MenuActivity extends Activity {
    private  double latitude;
    private double longitude;
    private String url = "http://qskipm.appspot.com/modifyProduct?arg=list&ownerId=";
    private ProgressBar progressbar = null;
    private ListView menuListView = null;
    private ArrayList<Product> itemList = null;

    private Stores store;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearbystores);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        store =  (Stores) this.getIntent().getSerializableExtra("store");
        url = url+store.getStoreId();
        new GetMenuItemsFromServerTask().execute(url);

    }

    private class GetMenuItemsFromServerTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Void result) {
            if (itemList != null) {
                updateList();
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];

            // getting JSON string from URL
            itemList = getMenuItems(url);
            return null;
        }
    }

    public void updateList() {
        menuListView= (ListView) findViewById(R.id.custom_list);
        menuListView.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);

        menuListView.setAdapter(new FoodListAdapter(this, itemList));
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = menuListView.getItemAtPosition(position);
                Product prData = (Product) o;

                 Intent intent = new Intent(MenuActivity.this, PlaceOrderActivity.class);
                intent.putExtra("product", prData);
                startActivity(intent);
            }
        });
    }
    public ArrayList<Product> getMenuItems(String url) {
        InputStream is = null;
        JSONObject jObj = null;
        String json = null;

        ArrayList<Product> prList = new ArrayList<Product>();
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String l = null;
            Gson gson = new Gson();
            while ((l = in.readLine()) ==null){


            }
            Type t = new TypeToken<ArrayList<Product>>(){}.getType();
            prList = (ArrayList<Product>) gson.fromJson(l, t);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return JSON String
        return prList;

    }

}