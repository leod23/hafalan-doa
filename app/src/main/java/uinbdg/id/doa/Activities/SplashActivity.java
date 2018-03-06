package uinbdg.id.doa.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uinbdg.id.doa.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {
    private static final int TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MenuActivity.class));
                finish();
            }
        },TIME);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
