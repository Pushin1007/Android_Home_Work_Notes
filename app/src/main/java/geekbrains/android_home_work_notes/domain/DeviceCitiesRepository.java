package geekbrains.android_home_work_notes.domain;

import geekbrains.android_home_work_notes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceCitiesRepository implements CitiesRepository {

    @Override
    public List<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();

        cities.add(new City(R.string.ebrg, R.drawable.ebrg));
        cities.add(new City(R.string.msc, R.drawable.msc));
        cities.add(new City(R.string.nsk, R.drawable.nsk));
        cities.add(new City(R.string.sam, R.drawable.sam));
        cities.add(new City(R.string.spb, R.drawable.spb));

        return cities;
    }
}
