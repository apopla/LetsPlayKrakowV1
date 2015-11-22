package com.example.kitowcy.letsplaykrakow.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.data.Place;
import com.example.kitowcy.letsplaykrakow.entities.activities.PlaceActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by lukasz on 21.11.15.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.VH> {


    private List<Place> dataSet = new ArrayList<>();
    private AppCompatActivity context;

    public PlacesAdapter(AppCompatActivity context, @Nullable final FilterBuilder filterBuilder) {
        this.context = context;
        Realm realm = Realm.getInstance(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Place> results = realm.where(Place.class).findAll();
                insertToDataSet(results, filterBuilder);
            }
        });
    }

    private void insertToDataSet(RealmResults<Place> results, FilterBuilder builder) {
        dataSet.clear();
        for (Place place : results) {
            if (place.getCategory().equals("CULTURE") && builder.contains(FilterBuilder.CULTURE))
                dataSet.add(place);
            if (place.getCategory().equals("FOOD") && builder.contains(FilterBuilder.FOOD))
                dataSet.add(place);
            if (place.getCategory().equals("MONUMENTS") && builder.contains(FilterBuilder.MONUMENTS))
                dataSet.add(place);
            if (place.getCategory().equals("ENTERTAINMENT") && builder.contains(FilterBuilder.ENTERTAINMENT))
                dataSet.add(place);
        }
        notifyItemRangeChanged(0, getItemCount());
        notifyDataSetChanged();
//        if(dataSet.size()==0)

    }

    public void updateDataSet(Context context, final FilterBuilder filterBuilder) {
        dataSet.clear();
        Realm realm = Realm.getInstance(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Place> results = realm.where(Place.class).findAll();
                insertToDataSet(results, filterBuilder);
            }
        });
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_places_item_row, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(final VH viewHolder, int position) {
        final Place place = dataSet.get(position);
        viewHolder.letsPlayCracow.setVisibility(place.isLetsPlayKrakow() ? View.VISIBLE : View.GONE);
        viewHolder.name.setText(place.getName());
        String shortDescription = place.getDescription().substring(0, Math.min(place.getDescription().length(), 100));
        viewHolder.description.setText(shortDescription + "...");
        viewHolder.description.setTextColor(Color.WHITE);
        viewHolder.name.setTextColor(Color.WHITE);
        viewHolder.image.setImageResource(place.getImageResourceId());
        viewHolder.itemView.setBackgroundColor(context.getResources()
                .getColor(position % 2 == 0 ? R.color.colorPrimary400 : R.color.colorPrimary600));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked generics")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceActivity.class);
                intent.putExtra("NAME", place.getName());
                intent.putExtra("DESCRIPTION", place.getDescription());
                intent.putExtra("IMAGE_RES", place.getImageResourceId());
                intent.putExtra("PLAY", place.isLetsPlayKrakow());
                ActivityOptionsCompat offerDetailsTransitionOptions =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                                Pair.create((View) viewHolder.image, "image"));
                context.startActivity(intent, offerDetailsTransitionOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        public TextView name, description;
        public ImageView image, letsPlayCracow;

        public VH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.place_name);
            description = (TextView) itemView.findViewById(R.id.place_description);
            image = (ImageView) itemView.findViewById(R.id.place_image);
            letsPlayCracow = (ImageView) itemView.findViewById(R.id.place_play_krakow);
        }
    }
}
