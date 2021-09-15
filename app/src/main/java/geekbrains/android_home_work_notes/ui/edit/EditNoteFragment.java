package geekbrains.android_home_work_notes.ui.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;


public class EditNoteFragment extends Fragment {

    public static final String KEY_NOTE_RESULT = "KEY_NOTE_RESULT";
    public static final String ARG_NOTE = "ARG_NOTE";

    public EditNoteFragment() {

    }


    public static EditNoteFragment newInstance(Note note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit__note_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editName = view.findViewById(R.id.edit_note_name);
        EditText editText = view.findViewById(R.id.edit_note_text);

        Note note = requireArguments().getParcelable(ARG_NOTE);

        editName.setText(note.getNameNote());
        editText.setText(note.getTextNote());

        view.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                note.setNameNote(editName.getText().toString());
                note.setTextNote(editText.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTE, note);

                getParentFragmentManager()
                        .setFragmentResult(KEY_NOTE_RESULT, bundle);

                getParentFragmentManager().popBackStack();
            }
        });

    }
}