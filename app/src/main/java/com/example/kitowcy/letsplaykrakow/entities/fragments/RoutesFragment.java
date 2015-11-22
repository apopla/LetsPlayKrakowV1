package com.example.kitowcy.letsplaykrakow.entities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;

public class RoutesFragment extends Fragment {

    private static final String TAG = RoutesFragment.class.getSimpleName();

    private RelativeLayout layoutOsemka;
    private RelativeLayout layout24;
    private MainActivity parentActivity;
    private RelativeLayout layoutDwadziescia;

    public static RoutesFragment newInstance() {
        RoutesFragment fragment = new RoutesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RoutesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routes, container, false);

        layoutOsemka = (RelativeLayout) view.findViewById(R.id.layout_osemka);

        layoutOsemka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Osemeczka jedzie", Toast.LENGTH_SHORT).show();

                parentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_fragment_placeholder,
                                GoogleMapFragment.newInstance(8))
                        .setCustomAnimations(R.anim.slide_from_left, R.anim.slide_to_right)
                        .commitAllowingStateLoss();
            }
        });

        layout24 = (RelativeLayout) view.findViewById(R.id.layout_dwadziescia);

        layout24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "24 jedzie", Toast.LENGTH_SHORT).show();

                parentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_fragment_placeholder,
                                GoogleMapFragment.newInstance(24))
                        .setCustomAnimations(R.anim.slide_from_left, R.anim.slide_to_right)
                        .commitAllowingStateLoss();
            }
        });


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
