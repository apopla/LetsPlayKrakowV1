package com.example.kitowcy.letsplaykrakow.entities.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;
import com.squareup.picasso.Picasso;


public class PlaceActivity extends AppCompatActivity {
    public static final String TAG = PlaceActivity.class.getSimpleName();

    public CollapsingToolbarLayout collapsingToolbarLayout;

    public NestedScrollView nestedScrollView;
    public TextView placeDescription;
    public ImageView imageView;
    public android.support.v7.widget.Toolbar toolbar;
    private String name, descrption;
    private boolean play;
    private int imageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");
        setContentView(R.layout.activity_display_place);
        ActionBar ab = getActionBar();
        if (ab != null) {
            Log.d(TAG, "editing actionbar!");
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        } else {
            Log.d(TAG, "actionbar is null :(");
        }
        injectViews();

        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        descrption = intent.getStringExtra("DESCRIPTION");
        imageRes = intent.getIntExtra("IMAGE_RES", R.drawable.menu_header);
        play = intent.getBooleanExtra("PLAY", false);
        Picasso.with(this).load(imageRes).into(imageView);
        placeDescription.setText(descrption);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        if(collapsingToolbarLayout!=null){
            collapsingToolbarLayout.setTitle(name);
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
            collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        }
    }

    private void injectViews() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        nestedScrollView = (NestedScrollView) findViewById(R.id.scroll);
        imageView = (ImageView) findViewById(R.id.image);
        placeDescription = (TextView) findViewById(R.id.place_description);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed ");
        super.onBackPressed();
//        switchToOfferDetailsActivity(offerCode);
    }

    @SuppressWarnings("unchecked")
    public void switchToOfferDetailsActivity(String offerCode) {
        Log.d(TAG, "switchToOfferDetailsActivity ");
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FRAGMENT_ID", "MAP");

        ActivityOptionsCompat offerDetailsTransitionOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        Pair.create((View) imageView, "transition"));
        startActivity(intent, offerDetailsTransitionOptions.toBundle());
        finish();
    }
}
