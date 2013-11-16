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
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class PlaceOrderActivity extends Activity {
    private  double latitude;
    private double longitude;
    private String url = "http://qskipm.appspot.com/modifyProduct?arg=list&ownerId=";
    private ProgressBar progressbar = null;
    private ListView menuListView = null;
    private ArrayList<Product> itemList = null;

    private Product product;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearbystores);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        product =  (Product) this.getIntent().getSerializableExtra("Product");

    }

}