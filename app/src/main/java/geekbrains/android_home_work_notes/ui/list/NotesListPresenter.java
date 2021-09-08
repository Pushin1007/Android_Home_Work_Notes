package geekbrains.android_home_work_notes.ui.list;


import geekbrains.android_home_work_notes.domain.NotesRepository;
import geekbrains.android_home_work_notes.domain.Note;

import java.util.List;

public class NotesListPresenter {

    private final NotesListView view;

    private final NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        List<Note> result = repository.getNotes();

        view.showNotes(result);
    }
}
