package geekbrains.android_home_work_notes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Note implements Parcelable {

    @StringRes
    private int nameNote;

    @DrawableRes
    private int textNote;

    public Note(int nameNote, int textNote) {
        this.nameNote = nameNote;
        this.textNote = textNote;
    }

    protected Note(Parcel in) {
        nameNote = in.readInt();
        textNote = in.readInt();
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

    public int getTextNote() {
        return textNote;
    }

    public int getNameNote() {
        return nameNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nameNote);
        dest.writeInt(textNote);
    }
}
