package geekbrains.android_home_work_notes.domain;

import geekbrains.android_home_work_notes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(R.string.ebrg, R.drawable.ebrg));
        notes.add(new Note(R.string.msc, R.drawable.msc));
        notes.add(new Note(R.string.nsk, R.drawable.nsk));
        notes.add(new Note(R.string.sam, R.drawable.sam));
        notes.add(new Note(R.string.spb, R.drawable.spb));

        return notes;
    }
}
