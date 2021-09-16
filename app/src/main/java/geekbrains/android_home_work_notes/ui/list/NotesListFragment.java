package geekbrains.android_home_work_notes.ui.list;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.domain.DeviceNotesRepository;
import geekbrains.android_home_work_notes.ui.MainActivity;
import geekbrains.android_home_work_notes.ui.Router;
import geekbrains.android_home_work_notes.ui.RouterHolder;
import geekbrains.android_home_work_notes.ui.edit.AddNoteFragment;
import geekbrains.android_home_work_notes.ui.edit.EditNoteFragment;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView, RouterHolder {

    @Override
    public Router getRouter() {
        return router;
    }

    public interface OnNoteClicked {
        void onNoteOnClicked(Note note);
    }

    public static final String KEY_SELECTED_NOTE = "KEY_SELECTED_NOTE";

    public static final String ARG_NOTE = "ARG_NOTE";

    public static final String KEY_NOTE_RESULT = "KEY_NOTE_RESULT";


    private Calendar date = Calendar.getInstance();

    private RecyclerView notesList;

    private NotesListPresenter presenter;

    private NotesAdapter adapter;

    private ProgressBar progressBar;

    private OnNoteClicked onNoteClicked;
    private Note selectedNote;
    private Router router;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
        if (context instanceof RouterHolder) {
            router = ((RouterHolder) context).getRouter();
        } else if (getParentFragment() instanceof RouterHolder) {
            router = ((RouterHolder) getParentFragment()).getRouter();
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

        presenter = new NotesListPresenter(this, DeviceNotesRepository.INSTANCE);

        adapter = new NotesAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener(EditNoteFragment.KEY_NOTE_RESULT,
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                        Note note = result.getParcelable(EditNoteFragment.ARG_NOTE);

//                        int index = adapter.updateNote(note);
//
//                        adapter.notifyItemChanged(index);
                        presenter.updateNote(note);
                    }
                });

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

        adapter.setLongClickListener(new NotesAdapter.OnNoteLongClickedListener() {
            @Override
            public void onNoteLongClicked(Note note) {
                selectedNote = note;
            }
        });


        progressBar = view.findViewById(R.id.progress);


        notesList = view.findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
//        notesList.setLayoutManager(new GridLayoutManager(requireContext(), 2)); //вариант с гридом

        notesList.setAdapter(adapter);

        presenter.requestNotes();// обновление вьюхи

        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_separator));
        notesList.addItemDecoration(itemDecoration);

        // настройка анимации
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(2000L); //изменение скорости анимации удаления

        notesList.setItemAnimator(animator);


        //    Замечания и исправления преподавателя на 7-е занятие!!! чтобы открывать бар через 3 полоски
        // И уже из фрагмента туда передавать toolbar, в onViewCreated
        Toolbar toolbar = view.findViewById(R.id.toolbar_list);

        MainActivity activity = (MainActivity) requireActivity(); // getActivity()

        activity.setDrawerToogle(toolbar); //передаем тул бар в активити

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_note) {
                    Toast.makeText(requireContext(), "Search note", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.add_note) {
                    router.showAddNote();

                    getParentFragmentManager().setFragmentResultListener(AddNoteFragment.KEY_NOTE_RESULT_ADD,
                            getViewLifecycleOwner(), new FragmentResultListener() {
                                @Override
                                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                                    Note note = result.getParcelable(AddNoteFragment.ARG_NOTE_ADD);
                                    presenter.addNote(note.getNameNote(), note.getDataNote(), note.getTextNote());
                                    adapter.notifyDataSetChanged();
                                }
                            });

                    return true;
                }
                if (item.getItemId() == R.id.delete_all_notes) {
//                    adapter.setNotes(Collections.emptyList());
//                    adapter.notifyDataSetChanged();
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void showNotes(List<Note> notes) {
        adapter.submitList(notes);
//        adapter.setNotes(notes);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

//    @Override
//    public void onNoteAdded(Note note) {
//
//        adapter.addNote(note);
//        adapter.notifyItemInserted(adapter.getItemCount() - 1);
//        notesList.smoothScrollToPosition(adapter.getItemCount() - 1);
//    }

//    @Override
//    public void onNoteRemoved(Note selectedNote) {
//        int index = adapter.removeNote(selectedNote);
//        adapter.notifyItemRemoved(index); //С анимацией
////        adapter.notifyDataSetChanged(); // без анимации
//    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_notes_list_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {
            presenter.removeNode(selectedNote);
            return true;
        }
        if (item.getItemId() == R.id.action_update) {
            if (router != null) {
                router.showEditNote(selectedNote);


                getParentFragmentManager().setFragmentResultListener(EditNoteFragment.KEY_NOTE_RESULT,
                        getViewLifecycleOwner(), new FragmentResultListener() {
                            @Override
                            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                                selectedNote = result.getParcelable(EditNoteFragment.ARG_NOTE);
//                                presenter.addNote(note.getNameNote(), note.getDataNote(), note.getTextNote());
//                                adapter.notifyDataSetChanged();
                            }
                        });


            }
            return true;
        }

        return super.onContextItemSelected(item);
    }
}

