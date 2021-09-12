package geekbrains.android_home_work_notes.domain;

import android.os.Handler;
import android.os.Looper;

import geekbrains.android_home_work_notes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    private Handler handler = new Handler(Looper.getMainLooper());
    private ArrayList<Note> notes = new ArrayList<>();

    public DeviceNotesRepository() {
        notes.add(new Note("First look", "August 12, 2021", "Some Text"));
        notes.add(new Note("Layouts", "August 16, 2021", "Some Text"));
        notes.add(new Note("Activities", "August 19, 2021", "Some Text"));
        notes.add(new Note("Resourses", "August 23, 2021", "Some Text"));
        notes.add(new Note("Intents", "August 26, 2021", "Some Text"));
        notes.add(new Note("Fragments", "August 30, 2021", "Some Text"));
        notes.add(new Note("Navigation", "September 02, 2021", "Some Text"));

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
    public void addNote(String nameNote, String dataNote, String textNote, Callback<Note> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Note result = new Note(nameNote, dataNote, textNote);
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
