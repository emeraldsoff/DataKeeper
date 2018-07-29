package inc.frontlooksoftwares.datakeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import inc.frontlooksoftwares.datakeeper.R;


public class addclient_activity extends Activity implements View.OnClickListener {

    FirebaseAuth.AuthStateListener mAuthlistener;
    FirebaseAuth mAuth;
    private Context mcontext;
    ResideMenu resideMenu;

    private ResideMenuItem Home,Search_Client,Add_Client,Edit_Client,Delete_Client,Setting,Share,About_us;

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
                        } else {
                            startActivity(new Intent(addclient_activity.this, loginactivity.class));
                        }
                    }
                };
                //add listener
                mAuth.addAuthStateListener(mAuthlistener);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private ConstraintLayout add_cust;
    private Button save;

    private ImageButton photo;
    private EditText fname_e, mname_e, lname_e;
    private EditText gfname_e, gmname_e, glname_e, dob_e, birthplace_e, id_e, nationality_e;
    private EditText adri_road_house, adrii_vlg_area, adriii_city, adriv_po, adrv_pin, adrvi_dist, adrvii_state, adrviii_country;
    private EditText isd, std_e, mob, smob, tele, email;

    private RadioGroup gender_grp;
    private RadioButton male, female, unspecified;

    public String fname, mname, lname, gfname, gmname, glname, dob, birthplace, id, nationality;
    public String address_i, address_ii, city, country, post_office, areapin, dist, state;
    public String isdcode, std, mobile_no, smobile_no, telephoneno, emailid;
    public String gender,males,females,unspecifieds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclient_activity);

        mcontext = this;
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        setUpMenu();

        //resources declared
        save = findViewById(R.id.save_data);
       Button menu=findViewById(R.id.menu);
        add_cust = findViewById(R.id.add_cust);

        photo = findViewById(R.id.clientphoto_btn);

        fname_e = findViewById(R.id.fname);
        mname_e = findViewById(R.id.mname);
        lname_e = findViewById(R.id.lname);

        gfname_e = findViewById(R.id.gfname);
        gmname_e = findViewById(R.id.gmname);
        glname_e = findViewById(R.id.glname);
        dob_e = findViewById(R.id.dob);
        birthplace_e = findViewById(R.id.birthplace);
        nationality_e = findViewById(R.id.nationality);
        id_e = findViewById(R.id.client_id);

        adri_road_house = findViewById(R.id.addressi);
        adrii_vlg_area = findViewById(R.id.addressii);
        adriii_city = findViewById(R.id.addressiii);
        adriv_po = findViewById(R.id.addressiv);
        adrv_pin = findViewById(R.id.addressv);
        adrvi_dist = findViewById(R.id.addressvi);
        adrvii_state = findViewById(R.id.addressvii);
        adrviii_country = findViewById(R.id.addressviii);

        isd = findViewById(R.id.isd_code);
        std_e = findViewById(R.id.std_code);
        mob = findViewById(R.id.mob_no);
        smob = findViewById(R.id.sec_mob_no);
        tele = findViewById(R.id.telephone_no);
        email = findViewById(R.id.email_id);

        gender_grp = findViewById(R.id.gender_select);
        male = findViewById(R.id.g_male);
        female = findViewById(R.id.g_female);
        unspecified = findViewById(R.id.g_unspecified);
        //strings

//        c_photo = findViewById(R.id.photo);

        fname = fname_e.toString();
        mname = mname_e.toString();
        lname = lname_e.toString();

        gfname = gfname_e.toString();
        gmname = gmname_e.toString();
        glname = glname_e.toString();
        dob = dob_e.toString();
        birthplace = birthplace_e.toString();
        males = male.getText().toString();
        females = female.getText().toString();
        unspecifieds = unspecified.getText().toString();
        nationality = nationality_e.toString();
        id = id_e.toString();

        address_i = adrii_vlg_area.toString();
        address_ii = adrii_vlg_area.toString();
        city = adriii_city.toString();
        post_office = adriv_po.toString();
        areapin = adrv_pin.toString();
        dist = adrvi_dist.toString();
        state = adrvii_state.toString();
        country = adrviii_country.toString();

        isdcode = isd.toString();
        std = std_e.toString();
        mobile_no = mob.toString();
        smobile_no = smob.toString();
        telephoneno = tele.toString();
        emailid = email.toString();

        gender_grp.getCheckedRadioButtonId();

//        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//            }
//
//
//
//
//        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

        switch (gender_grp.getCheckedRadioButtonId()) {
            case R.id.g_male:
                gender = males;
                break;
            case R.id.g_female:
                gender = females;
                break;
            case R.id.g_unspecified:
                gender = unspecifieds;
                break;
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
        }
        else if (view==Add_Client){
            startActivity(new Intent(addclient_activity.this, addclient_activity.class));
        }
        else if (view==Search_Client){
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
        }
        else if (view==Edit_Client){
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
        }
        else if (view==Delete_Client){
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
        }
        else if (view==Setting){
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
        }
        else if (view==Share){
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
        }
        else if (view==About_us){
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
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

}
