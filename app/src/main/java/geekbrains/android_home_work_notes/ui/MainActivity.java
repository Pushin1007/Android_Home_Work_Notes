package geekbrains.android_home_work_notes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.ui.details.NoteDetailsActivity;
import geekbrains.android_home_work_notes.ui.list.NotesListFragment;
import geekbrains.android_home_work_notes.ui.list.InfoFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    private static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.info) {

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.notes_list, new InfoFragment())
                            .commit();
//                   main.closeDrawer(GravityCompat.START);

                    return true;
                }
                if (item.getItemId() == R.id.notes_list_menu) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.notes_list, new NotesListFragment())
                            .commit();
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    public void onNoteOnClicked(Note note) {

        selectedNote = note;

        if (getResources().getBoolean(R.bool.isLandscape)) {


        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNote != null) {
            outState.putParcelable(ARG_NOTE, selectedNote);
        }
        super.onSaveInstanceState(outState);
    }

    /*
    Замечания и исправления преподавателя на 7-е занятие
    DrawerLayout лежит в Activity, а не во фрагменте
    (из активити в принципе можно добраться до view фрагмента с findViewById,
    т.е View фрагмента лежит в активити, в обратную сторону это понятное дело не получится),
     т.е надо по сути в активити передать метод установки тулбара
     */
    public void setDrawerToogle(Toolbar toolbar) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}