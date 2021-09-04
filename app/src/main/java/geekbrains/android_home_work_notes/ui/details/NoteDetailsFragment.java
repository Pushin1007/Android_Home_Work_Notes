package geekbrains.android_home_work_notes.ui.details;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        noteData.setText(DateUtils.formatDateTime(getContext(), date.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
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

