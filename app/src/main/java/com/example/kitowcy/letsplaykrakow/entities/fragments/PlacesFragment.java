package com.example.kitowcy.letsplaykrakow.entities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.adapters.FilterBuilder;
import com.example.kitowcy.letsplaykrakow.adapters.PlacesAdapter;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;

public class PlacesFragment extends Fragment {

    private MainActivity parentActivity;
    private RecyclerView recyclerView;
    private PlacesAdapter placesAdapter;
    private FrameLayout parent;
    public static PlacesFragment newInstance() {
        PlacesFragment fragment = new PlacesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        parent = (FrameLayout) view.findViewById(R.id.parent);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FilterBuilder filterBuilder = new FilterBuilder();
        filterBuilder.withAll();
        placesAdapter = new PlacesAdapter(parentActivity, filterBuilder);
        recyclerView.setAdapter(placesAdapter);



        parent.setBackgroundColor(placesAdapter.getItemCount()% 2 == 0 ?
                this.getResources().getColor(R.color.colorPrimary400):this.getResources().getColor(R.color.colorPrimary600));
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
}
