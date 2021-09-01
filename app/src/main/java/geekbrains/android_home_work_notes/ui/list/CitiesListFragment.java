package geekbrains.android_home_work_notes.ui.list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.City;
import geekbrains.android_home_work_notes.domain.DeviceCitiesRepository;
import geekbrains.android_home_work_notes.ui.details.CityDetailsActivity;

import java.util.List;

public class CitiesListFragment extends Fragment implements CitiesListView {

    public interface OnCityClicked {
        void onCityOnClicked(City city);
    }

    public static final String KEY_SELECTED_CITY = "KEY_SELECTED_CITY";
    public static final String ARG_CITY = "ARG_CITY";

    private CitiesListPresenter presenter;

    private LinearLayout container;

    private OnCityClicked onCityClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnCityClicked) {
            onCityClicked = (OnCityClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onCityClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CitiesListPresenter(this, new DeviceCitiesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        container = view.findViewById(R.id.root);

        presenter.requestCities();
    }

    @Override
    public void showCities(List<City> cities) {

        for (City city: cities) {

            View cityItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_city, container, false);

            cityItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCityClicked != null) {
                        onCityClicked.onCityOnClicked(city);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_CITY, city);

                    getParentFragmentManager().setFragmentResult(KEY_SELECTED_CITY, bundle);
                }
            });

            TextView cityName = cityItem.findViewById(R.id.city_name);

            cityName.setText(city.getName());

            container.addView(cityItem);
        }
    }
}

