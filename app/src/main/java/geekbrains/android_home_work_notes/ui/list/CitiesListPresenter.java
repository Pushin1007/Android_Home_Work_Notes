package geekbrains.android_home_work_notes.ui.list;


import geekbrains.android_home_work_notes.domain.CitiesRepository;
import geekbrains.android_home_work_notes.domain.City;

import java.util.List;

public class CitiesListPresenter {

    private final CitiesListView view;

    private final CitiesRepository repository;

    public CitiesListPresenter(CitiesListView view, CitiesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestCities() {
        List<City> result = repository.getCities();

        view.showCities(result);
    }
}
