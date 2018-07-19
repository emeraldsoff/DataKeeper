package inc.frontlooksoftwares.datakeeper.pro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import inc.frontlooksoftwares.datakeeper.R;

public class MainActivity extends Activity implements View.OnClickListener {
    FirebaseAuth.AuthStateListener mAuthlistener;
    FirebaseAuth mAuth;

    private Button custadd, custupdate, custshow, settings, about;
    private TextView title;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        int SPLASH_DISPLAY_LENGTH = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Logger.d("Start splash screen");
                mAuthlistener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
//                            title.setText(mAuth.getUid());
                        }
                        else{
                            startActivity(new Intent(MainActivity.this, loginactivity.class));
                        }
                    }
                };
                //add listener
                mAuth.addAuthStateListener(mAuthlistener);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custadd=findViewById(R.id.add_cust);
        custupdate=findViewById(R.id.update_cust);
        custshow=findViewById(R.id.show_cust);
        settings=findViewById(R.id.setting);
        about=findViewById(R.id.aboutus);

        title=findViewById(R.id.title);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_cust:

                break;
            case R.id.update_cust:

                break;
            case R.id.show_cust:

                break;
            case R.id.setting:

                break;
            case R.id.aboutus:

                break;

        }
    }
}
