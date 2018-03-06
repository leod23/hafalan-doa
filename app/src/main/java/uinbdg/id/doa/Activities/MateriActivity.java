package uinbdg.id.doa.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.Box;
import io.objectbox.query.Query;
import uinbdg.id.doa.Adapter.AdapterMateri;
import uinbdg.id.doa.App;
import uinbdg.id.doa.Model.Doa;
import uinbdg.id.doa.R;

public class MateriActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_materi)
    RecyclerView recyclerViewMateri;

    private Box<Doa> notesBox;
    private Query<Doa> notesQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notesBox = ((App) getApplication()).getBoxStore().boxFor(Doa.class);
        notesQuery = notesBox.query().build();

        List<Doa> notes = notesQuery.find();
        recyclerViewMateri.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMateri.setAdapter(new AdapterMateri(this,notes));

    }
}
