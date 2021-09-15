package geekbrains.android_home_work_notes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

import java.util.Objects;

public class Note implements Parcelable {

    private String nameNote;

    private String dataNote;

    private String textNote;

    public Note(String nameNote, String dataNote, String textNote) {
        this.nameNote = nameNote;
        this.dataNote = dataNote;
        this.textNote = textNote;

    }

    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }

    public void setDataNote(String dataNote) {
        this.dataNote = dataNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    protected Note(Parcel in) {
        nameNote = in.readString();
        dataNote = in.readString();
        textNote = in.readString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(nameNote, note.nameNote) &&
                Objects.equals(dataNote, note.dataNote) &&
                Objects.equals(textNote, note.textNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameNote, dataNote, textNote);
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTextNote() {
        return textNote;
    }

    public String getNameNote() {
        return nameNote;
    }

    public String getDataNote() {
        return dataNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameNote);
        dest.writeString(dataNote);
        dest.writeString(textNote);
    }
}
