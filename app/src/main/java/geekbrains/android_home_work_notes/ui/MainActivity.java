package geekbrains.android_home_work_notes.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.ui.details.NoteDetailsActivity;
import geekbrains.android_home_work_notes.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    private static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onNoteOnClicked(Note note) {

        selectedNote = note;

        if (getResources().getBoolean(R.bool.isLandscape)) {


        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNote != null) {
            outState.putParcelable(ARG_NOTE, selectedNote);
        }
        super.onSaveInstanceState(outState);
    }
}