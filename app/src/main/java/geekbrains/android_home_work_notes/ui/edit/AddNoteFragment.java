package geekbrains.android_home_work_notes.ui.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;
import java.util.UUID;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.domain.NotesRepository;


public class AddNoteFragment extends Fragment {

    public static final String KEY_NOTE_RESULT_ADD = "KEY_NOTE_RESULT_ADD";
    public static final String ARG_NOTE_ADD = "ARG_NOTE_ADD";
    private Calendar date = Calendar.getInstance();

    public AddNoteFragment() {
    }

    public static AddNoteFragment newInstance(Note note) {
        AddNoteFragment fragment = new AddNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE_ADD, note);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText addName = view.findViewById(R.id.add_note_name);
        EditText addText = view.findViewById(R.id.add_note_text);


        view.findViewById(R.id.add_btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note note = new Note(UUID.randomUUID().toString(),addName.getText().toString(), DateUtils.formatDateTime(getContext(), date.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR), addText.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTE_ADD, note);

                getParentFragmentManager()
                        .setFragmentResult(KEY_NOTE_RESULT_ADD, bundle);

                getParentFragmentManager().popBackStack();
            }
        });

    }
}