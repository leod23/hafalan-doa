package uinbdg.id.doa.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.Box;
import io.objectbox.query.Query;
import uinbdg.id.doa.App;
import uinbdg.id.doa.Model.Skor;
import uinbdg.id.doa.R;

public class SkorActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.skor)
    TextView tvSkor;
    private Box<Skor> notesBox;
    private Query<Skor> notesQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor);
        ButterKnife.bind(this);

        notesBox = ((App) getApplication()).getBoxStore().boxFor(Skor.class);
        notesQuery = notesBox.query().build();
        Skor skor = notesQuery.findFirst();

        if(skor != null){
            tvSkor.setText(String.valueOf(skor.getNilai()));
        }else {
            tvSkor.setText("Anda belum memmpunyai nilai");
        }
    }
}
