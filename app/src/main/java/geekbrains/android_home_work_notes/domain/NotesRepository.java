package geekbrains.android_home_work_notes.domain;

import java.util.List;

public interface NotesRepository {

   // List<Note> getNotes(); синхронный вывод
   void getNotes(Callback<List<Note>> callback); // асинхронный вывод
}
