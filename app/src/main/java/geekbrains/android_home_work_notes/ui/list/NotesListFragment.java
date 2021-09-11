package geekbrains.android_home_work_notes.ui.list;


import android.app.Activity;
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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.domain.DeviceNotesRepository;
import geekbrains.android_home_work_notes.ui.MainActivity;

import java.util.Collections;
import java.util.List;

public class  NotesListFragment extends Fragment implements NotesListView {

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

        adapter.setListener(new NotesAdapter.OnNoteClickedListener() {
            @Override
            public void onNoteClicked(Note note) {
                if (onNoteClicked != null) {
                    onNoteClicked.onNoteOnClicked(note);
                }

                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTE, note);

                getParentFragmentManager().setFragmentResult(KEY_SELECTED_NOTE, bundle);
//                Snackbar.make(view, note.getNameNote(), Snackbar.LENGTH_SHORT).show();
            }
        });


        RecyclerView notesList = view.findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
//        notesList.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        notesList.setAdapter(adapter);

        presenter.requestNotes();

        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_separator));
        notesList.addItemDecoration(itemDecoration);
        //
        //
        // И уже из фрагмента туда передавать toolbar, в onViewCreated
        Toolbar toolbar = view.findViewById(R.id.toolbar_list);


        // Не могу разобраться с этим куском кода. хочу открывать бар через 3 полоски
        //Если раскоментировать, то компилятор не ругается, но при запуке креш
        // В отличии от занятия у меня  DrawerLayout привязан к фрагменту, вместо  this  я поставил  getActivity(), может поэтому?

        DrawerLayout drawerLayout = view.findViewById(R.id.drawer_layout);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//               getActivity() ,
//                drawerLayout,
//                toolbar,
//                R.string.open_drawer,
//                R.string.close_drawer);
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();



        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_note) {
                    Toast.makeText(requireContext(), "Search note", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.add_note) {
                    Toast.makeText(requireContext(), "Add new note", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.delete_all_notes) {
                    adapter.setNotes(Collections.emptyList());
                    adapter.notifyDataSetChanged();
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
    }
}

