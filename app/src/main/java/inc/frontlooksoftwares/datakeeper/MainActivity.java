package inc.frontlooksoftwares.datakeeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;


public class MainActivity extends Activity implements View.OnClickListener {
    //    implements View.OnClickListener
    FirebaseAuth.AuthStateListener mAuthlistener;
    FirebaseAuth mAuth;
//    private int ;

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

    private Button custadd, custupdate, custshow, settings, about;
    private TextView title;

    private Layout constraint;
    private Context mcontext;
    ResideMenu resideMenu;

    private ResideMenuItem Home,Search_Client,Add_Client,Edit_Client,Delete_Client,Setting,Share,About_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext = this;
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        Button menu=findViewById(R.id.menu);
        setUpMenu();


//        resideMenu = new ResideMenu(this);
//        resideMenu.setBackground(R.drawable.menubackgroundc);
//        resideMenu.attachToActivity(this);



        custadd=findViewById(R.id.add_cust);
        custupdate=findViewById(R.id.update_cust);
        custshow=findViewById(R.id.show_cust);
        settings=findViewById(R.id.setting);
        about=findViewById(R.id.aboutus);

        title=findViewById(R.id.title);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

//        custadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//            }
//        });
//
//        custupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//            }
//        });
//
//        custshow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//            }
//        });
//
//        settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//            }
//        });
//
//        about.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//            }
//        });


    }
    private void setUpMenu() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menubackgroundc);
        resideMenu.attachToActivity(this);
        //valid scale factor is between 0.0f and 1.0f. left menu'width is 150dip.
        resideMenu.setScaleValue(0.6f);
        createMenuItems();
        Menuitemlisten();
        addMenuItems_inMenu();
    }

    public void createMenuItems(){
        // create menu items;
        Home     = new ResideMenuItem(this, R.drawable.round_home_24, "Home");
        Add_Client  = new ResideMenuItem(this, R.drawable.round_person_add_24, "Add Client Details" );
        Search_Client = new ResideMenuItem(this, R.drawable.baseline_search_24, "Search Client Details");
        Edit_Client = new ResideMenuItem(this, R.drawable.round_edit_24, "Edit Client Details");
        Delete_Client = new ResideMenuItem(this, R.drawable.round_delete_sweep_24, "Delete Client");
        Setting = new ResideMenuItem(this, R.drawable.round_settings_24, "Settings");
        Share = new ResideMenuItem(this, R.drawable.round_share_24, "Share");
        About_us = new ResideMenuItem(this, R.drawable.round_info_24, "About Us");
    }

    public void Menuitemlisten(){
        Home.setOnClickListener(this);
        Add_Client.setOnClickListener(this);
        Search_Client.setOnClickListener(this);
        Edit_Client.setOnClickListener(this);
        Delete_Client.setOnClickListener(this);
        Setting.setOnClickListener(this);
        Share.setOnClickListener(this);
        About_us.setOnClickListener(this);
    }

    public void onClick(View view){
        if (view==Home){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }
        else if (view==Add_Client){
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
        }
        else if (view==Search_Client){
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
        }
        else if (view==Edit_Client){
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
        }
        else if (view==Delete_Client){
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
        }
        else if (view==Setting){
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
        }
        else if (view==Share){
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
        }
        else if (view==About_us){
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
        }
        resideMenu.closeMenu();
    }

    public void addMenuItems_inMenu(){
        resideMenu.addMenuItem(Home,    ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Add_Client, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Search_Client,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Edit_Client,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Delete_Client, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Setting, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Share, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(About_us, ResideMenu.DIRECTION_LEFT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
                Toast.makeText(mcontext, "Menu Open",Toast.LENGTH_LONG).show();
        }
        @Override
        public void closeMenu() {
            Toast.makeText(mcontext, "Menu Close",Toast.LENGTH_LONG).show();
        }
    };



//    @Override
//    public void onClick(View v) {
//        ResideMenu.generateViewId();
//        Switch (v.getId()){
//            case R.id.
//        }
//
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.add_cust:
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//                break;
//            case R.id.update_cust:
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//                break;
//            case R.id.show_cust:
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//                break;
//            case R.id.setting:
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//                break;
//            case R.id.aboutus:
//                startActivity(new Intent(MainActivity.this, addclient_activity.class));
//                break;
//
//        }
//    }
}
