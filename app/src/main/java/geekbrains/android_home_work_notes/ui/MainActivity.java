package geekbrains.android_home_work_notes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.navigation.NavigationView;

import geekbrains.android_home_work_notes.R;
import geekbrains.android_home_work_notes.domain.Note;
import geekbrains.android_home_work_notes.ui.details.NoteDetailsActivity;
import geekbrains.android_home_work_notes.ui.list.NotesListFragment;
import geekbrains.android_home_work_notes.ui.list.InfoFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked, RouterHolder{

    private static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;

    private Router router;

    @Override
    public Router getRouter() {
        return router;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        router = new Router(getSupportFragmentManager());

        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) {
//            if (isAuthorized()) {
//                router.showNotesList();
//            } else {
//                router.showAuth();
//            }
//        }

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.info) {
                    router.showInfo();
                    return true;
                }
                if (item.getItemId() == R.id.notes_list_menu) {
//                    if (savedInstanceState == null) {
//                        if (isAuthorized()) {
                            router.showNotesList();
//                        } else {
//                            router.showAuth();
//                        }
//                    }
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
    ?????????????????? ?? ?????????????????????? ?????????????????????????? ???? 7-?? ??????????????
    DrawerLayout ?????????? ?? Activity, ?? ???? ???? ??????????????????
    (???? ???????????????? ?? ???????????????? ?????????? ?????????????????? ???? view ?????????????????? ?? findViewById,
    ??.?? View ?????????????????? ?????????? ?? ????????????????, ?? ???????????????? ?????????????? ?????? ???????????????? ???????? ???? ??????????????????),
     ??.?? ???????? ???? ???????? ?? ???????????????? ???????????????? ?????????? ?????????????????? ??????????????
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
    private boolean isAuthorized() {
//return true;
        return GoogleSignIn.getLastSignedInAccount(this)!=null;
//        return GoogleSignIn.getLastSignedInAccount(requireContext()) != null;
    }
}