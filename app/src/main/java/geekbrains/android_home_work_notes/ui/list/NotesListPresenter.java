package geekbrains.android_home_work_notes.ui.list;


import geekbrains.android_home_work_notes.domain.Callback;
import geekbrains.android_home_work_notes.domain.NotesRepository;
import geekbrains.android_home_work_notes.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesListPresenter {

    private final NotesListView view;

    private final NotesRepository repository;
    private ArrayList<Note> notes = new ArrayList<>();

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        view.showProgress();

        repository.getNotes(new Callback<List<Note>>() {
            @Override

            public void onSuccess(List<Note> data) {
                notes.clear();
                notes.addAll(data);

                view.showNotes(new ArrayList<>(notes));

                view.hideProgress();
            }
        });
    }

    public void addNote(String nameNote, String dataNote, String textNote) {
        view.showProgress();

        repository.addNote(nameNote, dataNote, textNote, new Callback<Note>() {
            @Override
            public void onSuccess(Note data) {
                view.hideProgress();

                notes.add(data);

                view.showNotes(new ArrayList<>(notes));
            }
        });
    }

    public void removeNode(Note selectedNote) {
        view.showProgress();

        repository.removeNote(selectedNote, new Callback<Void>() {
            @Override
            public void onSuccess(Void data) {

                view.hideProgress();

                notes.remove(selectedNote);

                view.showNotes(new ArrayList<>(notes));
            }
        });
    }
}
