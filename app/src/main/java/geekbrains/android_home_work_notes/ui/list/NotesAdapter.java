package geekbrains.android_home_work_notes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Note> data = new ArrayList<>();
    private OnNoteClickedListener listener;

    public void setNotes(List<Note> toSet) {
        data.clear();
        data.addAll(toSet);
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Note note = data.get(position);

        holder.
        holder.getNoteName().setText(note.getNameNote());

        holder.getNoteData().setText(note.getDataNote());

    }

//            TextView noteName = noteItem.findViewById(R.id.note_name);
//
//            noteName.setText(note.getNameNote());
//            TextView noteData = noteItem.findViewById(R.id.note_data);
//
//            noteData.setText(note.getDataNote());

    @Override
    public int getItemCount() {
        return data.size();
    }

    public OnNoteClickedListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickedListener listener) {
        this.listener = listener;
    }

    interface OnNoteClickedListener {
        void onNoteClicked(Note note);

    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView noteName;

        private final TextView noteData;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getListener() != null) {
                        getListener().onNoteClicked(data.get(getAdapterPosition()));
                    }
                }
            });

            noteName = itemView.findViewById(R.id.note_name);
            noteData = itemView.findViewById(R.id.note_data);
        }

        public TextView getNoteName() {
            return noteName;
        }

        public TextView getNoteData() {
            return noteData;
        }

    }
}
