package geekbrains.android_home_work_notes.ui.details;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import java.util.Calendar;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;

import geekbrains.android_home_work_notes.ui.list.NotesListFragment;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";
    private TextView noteName;
    private TextView noteData;
    private TextView noteText;

    private Calendar date = Calendar.getInstance();

    public NoteDetailsFragment() {
        super(R.layout.fragment_note_details);
    }

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle arguments = new Bundle();

        arguments.putParcelable(ARG_NOTE, note);

        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View name = view.findViewById(R.id.note_name); // Всплывающее поп меню  при нажатии на названии заметки
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(requireContext(), v);
                getActivity().getMenuInflater().inflate(R.menu.menu_pop_up_list, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.delete) {
                            Toast.makeText(requireContext(), "Delete this note", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show(); // показываем всплывающее менб
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar_details); // отрабатывыем нажатия на меню в етализации заметок
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.note_important) {
                    Toast.makeText(requireContext(), "To do this note important", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.change_name) {
                    Toast.makeText(requireContext(), "To do change name note", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.change_data) {
                    Toast.makeText(requireContext(), "To do change data note", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.edit_note) {
                    Toast.makeText(requireContext(), "Edit this note", Toast.LENGTH_SHORT).show();
                    return true;
                }


                return false;
            }
        });


        noteName = view.findViewById(R.id.note_name);
        noteData = view.findViewById(R.id.note_data);
        noteText = view.findViewById(R.id.note_text);

        noteData.setOnClickListener(new View.OnClickListener() {   // отображаем диалоговое окно для выбора даты
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), d, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show();
            }

        });


        getParentFragmentManager().setFragmentResultListener(NotesListFragment.KEY_SELECTED_NOTE,
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                        Note note = result.getParcelable(NotesListFragment.ARG_NOTE);

                        displayNote(note);
                    }
                });

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {

            Note note = getArguments().getParcelable(ARG_NOTE);

            if (note != null) {
                displayNote(note);
            }
        }
    }

    private void displayNote(Note note) {
        noteName.setText(note.getNameNote());
        noteData.setText(note.getDataNote());
        noteText.setText(note.getTextNote());
    }


    private void setInitialDateTime() {      // установка начальных даты и времени
        noteData.setText(DateUtils.formatDateTime(getContext(), date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {      //     установка обработчика выбора даты
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

}

