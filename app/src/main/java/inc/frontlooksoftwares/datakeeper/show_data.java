package inc.frontlooksoftwares.datakeeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

//import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

public class show_data extends Activity implements View.OnClickListener {
    FirebaseAuth.AuthStateListener mAuthlistener;
    FirebaseAuth mAuth;

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
                            String userid = mAuth.getUid();
                            cliadapter.startListening();
//                            Toast.makeText(show_data.this, "Welcome "+userid, Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(show_data.this, loginactivity.class));
                        }
                    }
                };
                //add listener
                mAuth.addAuthStateListener(mAuthlistener);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private Context mcontext;
    ResideMenu resideMenu;
    TextView app_title_bar;

    private ResideMenuItem FrontLook_DataKeeper, Home, Search_Client, Add_Client, Setting, Share, About_us;
    SearchView id_search;
    RecyclerView id_recycleview;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //    8K2869YLYfaB6nKan6Sy9ZeTQAo1/DATAKEEPER/client_basic_data
    private cli_adapter cliadapter;

    //    com.google.firebase.database.Query querypic = databaseList()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data);
        app_title_bar = findViewById(R.id.app_title_bar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Harlow.ttf");
        app_title_bar.setTypeface(typeface);
        mcontext = this;
        Button menu = findViewById(R.id.menu);
        setUpMenu();
//        id_search=findViewById(R.id.id_search);
//        id_search_btn=findViewById(R.id.id_search_btn);
//        id_recycleview=findViewById(R.id.id_recycle_view);

        setUpRecycleview();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

    }

    private void setUpRecycleview() {
        mAuth = FirebaseAuth.getInstance();
        String app_userid = mAuth.getUid();
        String collection = app_userid + "/" + "DATAKEEPER";
        CollectionReference cliref = db.collection(collection + "/" + "client_basic_data");
        try {
            Query query = cliref.orderBy("id", Query.Direction.ASCENDING);

            FirestoreRecyclerOptions<clicard_gen> options = new FirestoreRecyclerOptions.Builder<clicard_gen>()
                    .setQuery(query, clicard_gen.class)
                    .build();
            cliadapter = new cli_adapter(options);
            id_recycleview = findViewById(R.id.id_recycle_view);
            id_recycleview.setHasFixedSize(true);
            id_recycleview.setLayoutManager(new LinearLayoutManager(this));
            id_recycleview.setAdapter(cliadapter);
        } catch (Exception e) {
            Toast.makeText(show_data.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        cliadapter.stopListening();
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

    public void createMenuItems() {
        // create menu items;
        FrontLook_DataKeeper = new ResideMenuItem(this, R.drawable.menubackgroundc, "");
        Home = new ResideMenuItem(this, R.drawable.round_home_24, "Home");
        Add_Client = new ResideMenuItem(this, R.drawable.round_person_add_24, "Add Client Details");
        Search_Client = new ResideMenuItem(this, R.drawable.baseline_search_24, "Search Client Details");
//        Edit_Client = new ResideMenuItem(this, R.drawable.round_edit_24, "Edit Client Details");
//        Delete_Client = new ResideMenuItem(this, R.drawable.round_delete_sweep_24, "Delete Client");
        Setting = new ResideMenuItem(this, R.drawable.round_settings_24, "Settings");
        Share = new ResideMenuItem(this, R.drawable.round_share_24, "Share");
        About_us = new ResideMenuItem(this, R.drawable.round_info_24, "About Us");
    }

    public void Menuitemlisten() {
        Home.setOnClickListener(this);
        Add_Client.setOnClickListener(this);
        Search_Client.setOnClickListener(this);
//        Edit_Client.setOnClickListener(this);
//        Delete_Client.setOnClickListener(this);
        Setting.setOnClickListener(this);
        Share.setOnClickListener(this);
        About_us.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == Home) {
            startActivity(new Intent(show_data.this, MainActivity.class));
        } else if (view == Add_Client) {
            startActivity(new Intent(show_data.this, addclient_activity.class));
        }
//        else if (view==Search_Client){
//            startActivity(new Intent(show_data.this, show_data.class));
//        }
//        else if (view==Edit_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//        }
//        else if (view==Delete_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//        }
        else if (view == Setting) {
            startActivity(new Intent(show_data.this, MainActivity.class));
        } else if (view == Share) {
            startActivity(new Intent(show_data.this, MainActivity.class));
        } else if (view == About_us) {
            startActivity(new Intent(show_data.this, MainActivity.class));
        }
        resideMenu.closeMenu();
    }

    public void addMenuItems_inMenu() {
        resideMenu.addMenuItem(FrontLook_DataKeeper, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Home, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Add_Client, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Search_Client, ResideMenu.DIRECTION_LEFT);
//        resideMenu.addMenuItem(Edit_Client, ResideMenu.DIRECTION_LEFT);
//        resideMenu.addMenuItem(Delete_Client, ResideMenu.DIRECTION_LEFT);
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
            Toast.makeText(mcontext, "Menu Open", Toast.LENGTH_LONG).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mcontext, "Menu Close", Toast.LENGTH_LONG).show();
        }
    };
}
