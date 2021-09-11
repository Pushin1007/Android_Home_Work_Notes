package geekbrains.android_home_work_notes.ui.list;

import geekbrains.android_home_work_notes.domain.Note;

import java.util.List;

public interface NotesListView {

    void showNotes(List<Note> notes);
    void showProgress();

    void hideProgress();
}

