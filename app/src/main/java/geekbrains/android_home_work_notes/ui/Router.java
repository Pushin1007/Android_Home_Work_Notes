package geekbrains.android_home_work_notes.ui;

import androidx.fragment.app.FragmentManager;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.ui.details.NoteDetailsActivity;
import geekbrains.android_home_work_notes.ui.details.NoteDetailsFragment;
import geekbrains.android_home_work_notes.ui.edit.AddNoteFragment;
import geekbrains.android_home_work_notes.ui.edit.EditNoteFragment;
import geekbrains.android_home_work_notes.ui.list.InfoFragment;
import geekbrains.android_home_work_notes.ui.list.NotesListFragment;

public class Router { //класс который будет открывать все экраны

    private final FragmentManager fragmentManager;

    public Router(FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;
    }

    public void showNotesList() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list, new NotesListFragment())
                .commit();
    }

    public void showInfo() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list, new InfoFragment())
                .commit();
    }

    public void showEditNote(Note note) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list, EditNoteFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }
    public void showAddNote() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list,new AddNoteFragment())
                .addToBackStack(null)
                .commit();
    }
    public void showAuth() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list, new AuthFragment())
                .commit();
    }


}
