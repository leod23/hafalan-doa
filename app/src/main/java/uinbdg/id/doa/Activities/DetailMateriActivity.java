package uinbdg.id.doa.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uinbdg.id.doa.R;

public class DetailMateriActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nama_doa)
    TextView tvNamaDoa;
    @BindView(R.id.nama_lapadz)
    TextView tvLapadz;
    @BindView(R.id.latin)
    TextView tvLatin;
    @BindView(R.id.arti)
    TextView tvArti;

    String doa,lapadz,arti,latin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        doa = getIntent().getStringExtra("nama_doa");
        lapadz = getIntent().getStringExtra("lapadz");
        arti = getIntent().getStringExtra("arti");
        latin = getIntent().getStringExtra("latin");

        tvNamaDoa.setText(doa);
        tvLapadz.setText(lapadz);
        tvLatin.setText(latin);
        tvArti.setText(arti);

    }
}
