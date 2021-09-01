package geekbrains.android_home_work_notes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class City implements Parcelable {

    @StringRes
    private int name;

    @DrawableRes
    private int coatOfArms;

    public City(int name, int coatOfArms) {
        this.name = name;
        this.coatOfArms = coatOfArms;
    }

    protected City(Parcel in) {
        name = in.readInt();
        coatOfArms = in.readInt();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getCoatOfArms() {
        return coatOfArms;
    }

    public int getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(name);
        dest.writeInt(coatOfArms);
    }
}
