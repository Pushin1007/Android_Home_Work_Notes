package geekbrains.android_home_work_notes.domain;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FireStoreNotesRepository implements NotesRepository {
    private static final String NOTES = "notes";
    private static final String NAMENOTE = "nameNote";
    private static final String DATANOTE = "dataNote";
    private static final String TEXTNOTE = "textNote";


    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(Callback<List<Note>> callback) {

        db.collection(NOTES)
                .orderBy(NAMENOTE, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            ArrayList<Note> result = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String nameNote = document.getString(NAMENOTE);
                                String dataNote = document.getString(DATANOTE);
                                String textNote = document.getString(TEXTNOTE);

                                result.add(new Note(document.getId(), nameNote, dataNote, textNote));

                            }

                            callback.onSuccess(result);
                        } else {

                        }

                    }
                });
    }

    @Override
    public void addNote(String nameNote, String dataNote, String textNote, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();


        data.put(NAMENOTE, nameNote);
        data.put(DATANOTE, dataNote);
        data.put(TEXTNOTE, textNote);

        db.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {
                            String noteId = task.getResult().getId();

                            callback.onSuccess(new Note(noteId, nameNote, dataNote, textNote));

                        }

                    }
                });
    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {

        db.collection(NOTES)
                .document(note.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(unused);
                    }
                });
    }
}


