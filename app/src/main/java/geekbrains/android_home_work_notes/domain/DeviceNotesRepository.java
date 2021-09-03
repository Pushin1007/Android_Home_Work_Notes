package geekbrains.android_home_work_notes.domain;

import geekbrains.android_home_work_notes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(R.string.not1name, R.string.not1data, R.drawable.ebrg));
        notes.add(new Note(R.string.not2name,R.string.not2data, R.drawable.ebrg));
        notes.add(new Note(R.string.not3name,R.string.not3data, R.drawable.ebrg));
        notes.add(new Note(R.string.not4name,R.string.not4data, R.drawable.ebrg));
        notes.add(new Note(R.string.not5name,R.string.not5data, R.drawable.ebrg));
        notes.add(new Note(R.string.not6name,R.string.not6data, R.drawable.ebrg));
        notes.add(new Note(R.string.not7name,R.string.not7data, R.drawable.ebrg));

        return notes;
    }
}
