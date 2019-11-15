package com.example.homeworkTask3.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.homeworkTask3.R;
import com.example.homeworkTask3.model.CatPhotos;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatDetailActivity extends AppCompatActivity {
    public static ArrayList<CatPhotos> dList = new ArrayList<>();

    private TextView catName;
    private TextView dogFriendly;
    private TextView description;
    private TextView temperament;
    private TextView lifespan;
    private TextView origin;
    private TextView weight;
    private Button wiki;
    private ImageButton favourite;
    private ImageView catImage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_detail_activity);

        dogFriendly = findViewById(R.id.dog_friendly);
        catName = findViewById(R.id.name);
        description = findViewById(R.id.description);
        origin = findViewById(R.id.origin);
        weight = findViewById(R.id.weight);
        temperament = findViewById(R.id.temperament);
        lifespan = findViewById(R.id.lifespan);
        catImage = findViewById(R.id.cat_image);
        favourite = findViewById(R.id.favourite);
        wiki = findViewById(R.id.wikipedia);

        Intent intent = getIntent();
        final String breedID = intent.getStringExtra("BreedID");
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + breedID;

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Gson gson = new Gson();
                CatPhotos[] photoResponse = gson.fromJson(response, CatPhotos[].class);
                final List<CatPhotos> catData = Arrays.asList(photoResponse);
                String imageUrl = catData.get(0).getUrl();
                String imperial = catData.get(0).getBreeds().get(0).getResult().getImperial();
                String metric = catData.get(0).getBreeds().get(0).getResult().getMetric();
                catName.setText(catData.get(0).getBreeds().get(0).getName());
                dogFriendly.setText("Dog Friendliness: \n" + String.valueOf(catData.get(0).getBreeds().get(0).getDog_friendly()));
                description.setText("Description: \n" + catData.get(0).getBreeds().get(0).getDescription());
                lifespan.setText("Lifespan: \n" + catData.get(0).getBreeds().get(0).getLife_span() + "Years");
                origin.setText("Origin: \n" + catData.get(0).getBreeds().get(0).getOrigin());
                temperament.setText("Temperament: \n" + catData.get(0).getBreeds().get(0).getTemperament());


                if (imageUrl != null) {
                    Glide.with(getApplicationContext()).load(imageUrl).into(catImage);
                }

                weight.setText("Weight: \n" + imperial + "Kg \n" + metric + "lbs");

                wiki.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = catData.get(0).getBreeds().get(0).getWikipedia_url();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                favourite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dList.add(catData.get(0));
                        String name = catData.get(0).getBreeds().get(0).getName();
                        Toast.makeText(getApplicationContext(), name + " has been added to favourites!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }
}
