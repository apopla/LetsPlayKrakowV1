package com.example.kitowcy.letsplaykrakow.entities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.kitowcy.letsplaykrakow.Constants;
import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.adapters.FilterBuilder;
import com.example.kitowcy.letsplaykrakow.adapters.FilterDialogBuilder;
import com.example.kitowcy.letsplaykrakow.adapters.PlacesAdapter;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;

public class PlacesFragment extends Fragment {
    public static final String TAG = PlacesFragment.class.getSimpleName();
    private MainActivity parentActivity;
    private RecyclerView recyclerView;
    private PlacesAdapter placesAdapter;
    private FrameLayout parent;
    private FilterBuilder currentFilter;
    public static PlacesFragment instance;

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
        instance = this;
        MainActivity.currentFragmentDisplayedId = Constants.PLACES;
        currentFilter = new FilterBuilder().withAll();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);

        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            getActivity().getActionBar().setDisplayShowCustomEnabled(true);
            getActivity().getActionBar().setHomeButtonEnabled(true);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        parent = (FrameLayout) view.findViewById(R.id.parent);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        placesAdapter = new PlacesAdapter(parentActivity, currentFilter);
        recyclerView.setAdapter(placesAdapter);

        parent.setBackgroundColor(placesAdapter.getItemCount() % 2 == 0 ?
                this.getResources().getColor(R.color.colorPrimary100) : this.getResources().getColor(R.color.colorPrimary200));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG, "onCreateOptionsMenu ");
        inflater.inflate(R.menu.menu_display_place, menu);
    }

    public void setupFilterDialog() {
        FilterDialogBuilder.build(getActivity(), currentFilter, new FilterDialogBuilder.OnRefreshListener() {
            @Override
            public void onRefresh(FilterBuilder currentFilter) {
                placesAdapter.updateDataSet(getActivity(), currentFilter);
                parent.setBackgroundColor(placesAdapter.getItemCount() % 2 == 0 ?
                        getResources().getColor(R.color.colorPrimary100)
                        : getResources().getColor(R.color.colorPrimary200));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.menu.menu_display_place) {

            return true;
        }
        return super.onOptionsItemSelected(item);
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
