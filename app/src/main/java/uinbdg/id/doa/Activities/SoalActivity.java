package uinbdg.id.doa.Activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.objectbox.Box;
import io.objectbox.query.Query;
import uinbdg.id.doa.App;
import uinbdg.id.doa.Interfaces.Levenshtein;
import uinbdg.id.doa.Model.Skor;
import uinbdg.id.doa.Model.Soal;
import uinbdg.id.doa.R;

public class SoalActivity extends AppCompatActivity {

    @BindView(R.id.tv_soal)
    TextView tvSoal;
    @BindView(R.id.tv_soal_lanjutan)
    TextView tvSoalLanjutan;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_jawaban)
    TextView tvJawaban;
    @BindView(R.id.tv_jawaban_dari_db)
    TextView tvJawabanDariDb;
    @BindView(R.id.card_2)
    CardView card2;
    @BindView(R.id.jawab)
    FloatingActionButton jawab;
    @BindView(R.id.next)
    FloatingActionButton next;
    @BindView(R.id.tv_kemiripan)
    TextView tvKemiripan;
    @BindView(R.id.jumlah)
    TextView jumlah;
    private Box<Soal> notesBox;
    private Query<Soal> notesQuery;

    private final int REQ_CODE_SPEECH_INPUT = 100;
    List<Soal> notes;

    int pos = 0;

    int nilai;

    private Box<Skor> notesBoxSkor;
    private Query<Skor> notesQuerySkor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        ButterKnife.bind(this);
        notesBox = ((App) getApplication()).getBoxStore().boxFor(Soal.class);
        notesQuery = notesBox.query().build();

        notesBoxSkor = ((App) getApplication()).getBoxStore().boxFor(Skor.class);
        notesQuerySkor = notesBoxSkor.query().build();

        loadJSONFromAsset();

        notes = notesQuery.find();
        Collections.shuffle(notes);
        tvSoal.setText(notes.get(pos).getPertanyaan());
        tvSoalLanjutan.setText(notes.get(pos).getPertanyaanLanjutan());
        jumlah.setText(pos + 1 + "/" + notes.size());
    }

    public ArrayList<Soal> loadJSONFromAsset() {
        ArrayList<Soal> locList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getAssets().open("soal.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("soal");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Soal doa = new Soal();
                doa.setId(jo_inside.getInt("id"));
                doa.setJawaban(jo_inside.getString("jawaban"));
                doa.setPertanyaan(jo_inside.getString("pertanyaan"));
                doa.setPertanyaanLanjutan(jo_inside.getString("pertanyaan_lanjutan"));

                //Add your values in your `ArrayList` as below:
                locList.add(doa);
                notesBox.put(doa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locList;
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        Locale loc = new Locale("ar");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-SA");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Silahkan jawab");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    card2.setVisibility(View.VISIBLE);
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvJawaban.setText(result.get(0));
                    tvJawabanDariDb.setText(notes.get(pos).getJawaban());
                    next.setVisibility(View.VISIBLE);
                    jawab.setVisibility(View.GONE);
                    Levenshtein jw = new Levenshtein();
                    double similarity = jw.similarity(tvJawaban.getText().toString(), tvJawabanDariDb.getText().toString());
                    tvKemiripan.setText("Kemiripan " + String.valueOf(similarity));

                    if (similarity > 0.5) {
                        nilai += 1;
                    }
                }
                break;
            }

        }
    }


    @OnClick({R.id.jawab, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jawab:
                promptSpeechInput();
                break;
            case R.id.next:
                pos += 1;
                nextQuis();
                break;
        }
    }

    void nextQuis() {
        if (pos != notes.size()) {
            tvSoal.setText(notes.get(pos).getPertanyaan());
            tvSoalLanjutan.setText(notes.get(pos).getPertanyaanLanjutan());
            card2.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            jawab.setVisibility(View.VISIBLE);
            jumlah.setText(pos + 1 + "/" + notes.size());
        } else {
            showHasil();
            Skor skor = new Skor();
            skor.setId(1);
            skor.setNilai(nilai);
            notesBoxSkor.put(skor);
        }
    }


    void showHasil() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hasil").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setMessage("Jawaban Anda benar " + nilai + " dari " + notes.size() + " Pertanyaan");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
