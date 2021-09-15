package geekbrains.android_home_work_notes.domain;

import java.util.List;

public interface NotesRepository {

    void getNotes(Callback<List<Note>> callback);

    void addNote( String nameNote, String dataNote, String textNote, Callback<Note> callback);

    void removeNote(Note note, Callback<Void> callback);
}
