package com.example.practicajson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    RecyclerView recyclerView;
    List<String> peakNames = new ArrayList<>();
    List<String> peakHeight = new ArrayList<>();
    List<String> peakImages = new ArrayList<>();
    List<String> peakCountry = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hook

        recyclerView = findViewById(R.id.recyclerView);

        //Layout

        recyclerView.setLayoutManager(new
                LinearLayoutManager(getApplicationContext()));

        //Instanciar MyAdapter i assignar-lo al RecyclerView
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, peakNames, peakHeight,
                peakImages, peakCountry);
        recyclerView.setAdapter(myAdapter);

        try
        {
            //Getting JSONObject from file
            JSONObject obj = new JSONObject(loadJSONObjectefromAssets());
            //obtenir el JSONArray aqui
            JSONArray peakArray = obj.getJSONArray("peaks");
            for (int i = 0; i < peakArray.length(); i++)
            {
                JSONObject peakDetail = peakArray.getJSONObject(i);
                //afegir les dades a cada un dels ArrayList
                peakNames.add(peakDetail.getString("name"));
                peakHeight.add(peakDetail.getString("height"));
                peakImages.add(peakDetail.getString("url"));
                peakCountry.add(peakDetail.getString("country"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }


    }

    private String loadJSONObjectefromAssets() {

        String json = null;
        try
        {
            InputStream input = getAssets().open("peaks.json");
            //Returns an estimate of the number of bytes that can be read
            int size = input.available();
            Log.d(TAG, "loadJSONObjectefromAssets: size  json : " + size);
            byte[] buffer = new byte[size];
            //Reads some number of bytes from the input stream
            //and stores them into the buffer array.
            input.read(buffer);
            input.close();
            json = new String(buffer, "UTF-8"); //accpet all char types
        } catch (IOException e)
        {
            e.printStackTrace();

            return null;
        }
        Log.d(TAG, "loadJSONObjectefromAssets: json loaded");
        return json;
    }

}