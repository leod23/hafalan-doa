package uinbdg.id.doa.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uinbdg.id.doa.R;
import uinbdg.id.doa.Util.CommonUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtil.showProgress(LoginActivity.this,"Authenticating . . .");

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();
                                // onLoginFailed();
                                CommonUtil.hideDialog();
                            }
                        }, 3000);

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
