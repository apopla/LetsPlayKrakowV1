package com.example.kitowcy.letsplaykrakow.entities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.adapters.CustomGridViewAdapter;
import com.example.kitowcy.letsplaykrakow.data.Place;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class GridViewFragment extends Fragment {

    private static final String TAG = GridViewFragment.class.getSimpleName();
    GridView gridView;

    List<Pair<Integer, String>> gridArray = new ArrayList<>();

    CustomGridViewAdapter customGridAdapter;

    public static GridViewFragment newInstance() {
        GridViewFragment fragment = new GridViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public GridViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);
        Realm realm = Realm.getInstance(getActivity());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Place> realmResults = realm.where(Place.class).findAll();
                for (Place place : realmResults) {
                    //// FIXME: 22.11.15
                    boolean isUnlocked = place.isSeen();
                    int resource = isUnlocked ? place.getImageResourceId() : place.getBlockedImageRecourceId();
                    gridArray.add(new Pair<>(resource, place.getName()));
                }
            }
        });

        gridView = (GridView) view.findViewById(R.id.achievements_grid_view);
        customGridAdapter = new CustomGridViewAdapter(getActivity(), R.layout.grid_achievment_item, gridArray);
        gridView.setAdapter(customGridAdapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    private void setGridView(View view) {
//
//        GridView gridview = (GridView) view.findViewById(R.id.achievements_grid_view);
//        gridview.setAdapter(new AchievementAdapter(getActivity()));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Log.d(TAG, "onclick: " + position);
//            }
//        });
//
//    }
}
