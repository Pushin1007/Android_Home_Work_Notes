package geekbrains.android_home_work_notes.domain;

import geekbrains.android_home_work_notes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(R.string.noteName1, R.string.noteData1, R.string.noteText1));
        notes.add(new Note(R.string.noteName2, R.string.noteData2, R.string.noteText2));
        notes.add(new Note(R.string.noteName3, R.string.noteData3, R.string.noteText3));
        notes.add(new Note(R.string.noteName4, R.string.noteData4, R.string.noteText4));
        notes.add(new Note(R.string.noteName5, R.string.noteData5, R.string.noteText5));
        notes.add(new Note(R.string.noteName6, R.string.noteData6, R.string.noteText6));
        notes.add(new Note(R.string.noteName7, R.string.noteData7, R.string.noteText7));

        return notes;
    }
}
