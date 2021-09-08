package geekbrains.android_home_work_notes.ui.list;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.domain.DeviceNotesRepository;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public interface OnNoteClicked {
        void onNoteOnClicked(Note note);
    }

    public static final String KEY_SELECTED_NOTE = "KEY_SELECTED_NOTE";
    public static final String ARG_NOTE = "ARG_NOTE";

    private NotesListPresenter presenter;
    private final NotesAdapter adapter = new NotesAdapter();


    private OnNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }


    @Override
    public void onDetach() {
        onNoteClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, new DeviceNotesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView notesList = view.findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        notesList.setAdapter(adapter);

        presenter.requestNotes();

        Toolbar toolbar = view.findViewById(R.id.toolbar_list);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

//            public boolean onCreateOptionsMenu(Menu view) { // это если поиск - виджет, возможно его использовать не буду
//
//                MenuItem search = view.findItem(R.id.search_note);
//                SearchView searchView = (SearchView) search.getActionView();
//
//                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String query) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String newText) {
//                        return false;
//                    }
//                });
//
//
//                return true;
//            }

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_note) { // это если просто кнопка, возможно к ней вернусь
                    Toast.makeText(requireContext(), "Search note", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.add_note) {
                    Toast.makeText(requireContext(), "Add new note", Toast.LENGTH_SHORT).show();
                    return true;
                }


                return false;
            }
        });
    }

    @Override
    public void showNotes(List<Note> notes) {

        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
        for (Note note : notes) {

//            View noteItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, container, false);

//            noteItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onNoteClicked != null) {
//                        onNoteClicked.onNoteOnClicked(note);
//                    }
//
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable(ARG_NOTE, note);
//
//                    getParentFragmentManager().setFragmentResult(KEY_SELECTED_NOTE, bundle);
//                }
//            });

//            TextView noteName = noteItem.findViewById(R.id.note_name);
//
//            noteName.setText(note.getNameNote());
//            TextView noteData = noteItem.findViewById(R.id.note_data);
//
//            noteData.setText(note.getDataNote());
//
//            container.addView(noteItem);
        }
    }
}

