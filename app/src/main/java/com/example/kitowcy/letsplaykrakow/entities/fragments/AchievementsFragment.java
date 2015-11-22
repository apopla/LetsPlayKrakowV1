package com.example.kitowcy.letsplaykrakow.entities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.kitowcy.letsplaykrakow.AchievementAdapter;
import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.adapters.FilterBuilder;
import com.example.kitowcy.letsplaykrakow.adapters.PlacesAdapter;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;

public class AchievementsFragment extends Fragment {

    private static final String TAG = AchievementsFragment.class.getSimpleName();

    private MainActivity parentActivity;
    private RecyclerView recyclerView;
    private PlacesAdapter placesAdapter;

    public static AchievementsFragment newInstance() {
        AchievementsFragment fragment = new AchievementsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public AchievementsFragment() {
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

        setGridView(view);

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity = (MainActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setGridView(View view){

        GridView gridview = (GridView) view.findViewById(R.id.achievements_grid_view);
        gridview.setAdapter(new AchievementAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Log.d(TAG, "onclick: " + position);
            }
        });

    }
}
