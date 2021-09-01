package geekbrains.android_home_work_notes.ui.details;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.City;
import geekbrains.android_home_work_notes.ui.list.CitiesListFragment;

public class CityDetailsFragment extends Fragment {

    private static final String ARG_CITY = "ARG_CITY";
    private TextView cityName;
    private ImageView coat;

    public CityDetailsFragment() {
        super(R.layout.fragment_city_details);
    }

    public static CityDetailsFragment newInstance(City city) {
        CityDetailsFragment fragment = new CityDetailsFragment();
        Bundle arguments = new Bundle();

        arguments.putParcelable(ARG_CITY, city);

        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cityName = view.findViewById(R.id.city_name);

        coat = view.findViewById(R.id.img_coat_of_arms);

        getParentFragmentManager().setFragmentResultListener(CitiesListFragment.KEY_SELECTED_CITY, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                City city = result.getParcelable(CitiesListFragment.ARG_CITY);

                displayCity(city);
            }
        });

        if (getArguments() != null && getArguments().containsKey(ARG_CITY)) {

            City city = getArguments().getParcelable(ARG_CITY);

            if (city != null) {
                displayCity(city);
            }
        }
    }

    private void displayCity(City city) {
        cityName.setText(city.getName());

        coat.setImageResource(city.getCoatOfArms());
    }
}

