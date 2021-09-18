package geekbrains.android_home_work_notes.domain;

import android.os.Handler;
import android.os.Looper;

import geekbrains.android_home_work_notes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceNotesRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new FireStoreNotesRepository();
    private Handler handler = new Handler(Looper.getMainLooper());
    private ArrayList<Note> notes = new ArrayList<>();


    public DeviceNotesRepository() {
        notes.add(new Note("id1","First look", "August 12, 2021", "Some Text"));
        notes.add(new Note("id2","Layouts", "August 16, 2021", "Some Text"));
        notes.add(new Note("id3","Activities", "August 19, 2021", "Some Text"));
        notes.add(new Note("id4","Resourses", "August 23, 2021", "Some Text"));
        notes.add(new Note("id5","Intents", "August 26, 2021", "Some Text"));
        notes.add(new Note("id6","Fragments", "August 30, 2021", "Some Text"));
        notes.add(new Note("id7","Navigation", "September 02, 2021", "Some Text"));

    }


    @Override
    public void getNotes(Callback<List<Note>> callback) {

        new Thread(new Runnable() { //выполняем в паралельном потоке
            @Override
            public void run() {

                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notes);
                    }
                });
            }
        }).start();
    }

    @Override
    public void addNote( String nameNote, String dataNote, String textNote, Callback<Note> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Note result = new Note(UUID.randomUUID().toString(),nameNote, dataNote, textNote);
                notes.add(result);


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(result);
                    }
                });
            }
        }).start();
    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                notes.remove(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });
            }
        }).start();
    }
}
