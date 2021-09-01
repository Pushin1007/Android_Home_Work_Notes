package geekbrains.android_home_work_notes.ui.details;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.City;

public class CityDetailsActivity extends AppCompatActivity {

    public static final String ARG_CITY = "ARG_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        if (getResources().getBoolean(R.bool.isLandscape)) {
            finish();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();

            City city = getIntent().getParcelableExtra(ARG_CITY);

            fragmentManager.beginTransaction()
                    .replace(R.id.container, CityDetailsFragment.newInstance(city), "CityDetailsFragment")
                    .commit();
        }
    }
}
