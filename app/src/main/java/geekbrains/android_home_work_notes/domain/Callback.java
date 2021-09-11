package geekbrains.android_home_work_notes.domain;

public interface Callback<T> {
    void onSuccess(T data);// метод который говорит что все готово
}
