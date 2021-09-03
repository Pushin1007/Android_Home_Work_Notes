package geekbrains.android_home_work_notes.domain;

import geekbrains.android_home_work_notes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(R.string.not1, R.drawable.ebrg));
        notes.add(new Note(R.string.not2, R.drawable.ebrg));
        notes.add(new Note(R.string.not3, R.drawable.ebrg));
        notes.add(new Note(R.string.not4, R.drawable.ebrg));
        notes.add(new Note(R.string.not5, R.drawable.ebrg));
        notes.add(new Note(R.string.not6, R.drawable.ebrg));
        notes.add(new Note(R.string.not7, R.drawable.ebrg));

        return notes;
    }
}
