package uinbdg.id.doa.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import uinbdg.id.doa.App;
import uinbdg.id.doa.Model.Doa;
import uinbdg.id.doa.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.objectbox.Box;
import io.objectbox.query.Query;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.btn_materi)
    RelativeLayout btnMateri;
    @BindView(R.id.btn_latihan)
    RelativeLayout btnLatihan;
    @BindView(R.id.btn_skor)
    RelativeLayout btnSkor;
    @BindView(R.id.btn_tentang)
    RelativeLayout btnTentang;

    private Box<Doa> notesBox;
    private Query<Doa> notesQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        notesBox = ((App) getApplication()).getBoxStore().boxFor(Doa.class);
        notesQuery = notesBox.query().build();
        loadJSONFromAsset();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.btn_materi, R.id.btn_latihan, R.id.btn_skor, R.id.btn_tentang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_materi:
                startActivity(new Intent(this,MateriActivity.class));
                break;
            case R.id.btn_latihan:
                startActivity(new Intent(this,SoalActivity.class));
                break;
            case R.id.btn_skor:
                startActivity(new Intent(this,SkorActivity.class));
                break;
            case R.id.btn_tentang:
                break;
        }
    }


    public ArrayList<Doa> loadJSONFromAsset() {
        ArrayList<Doa> locList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getAssets().open("doa.json");
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
            JSONArray m_jArry = obj.getJSONArray("doa");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Doa doa = new Doa();
                doa.setId( jo_inside.getInt("id"));
                doa.setNamaDoa(jo_inside.getString("nama_doa"));
                doa.setLapadz(jo_inside.getString("lapadz"));
                doa.setLatin(jo_inside.getString("latin"));
                doa.setArti(jo_inside.getString("arti"));

                //Add your values in your `ArrayList` as below:
                locList.add(doa);
                notesBox.put(doa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locList;
    }
}
