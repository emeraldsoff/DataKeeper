package inc.frontlooksoftwares.datakeeper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

//import inc.frontlooksoftwares.datakeeper.R;


public class addclient_activity extends Activity implements View.OnClickListener {

    FirebaseAuth.AuthStateListener mAuthlistener;
    FirebaseAuth mAuth;
    private Context mcontext;
    ResideMenu resideMenu;

    private ResideMenuItem Home,Search_Client,Add_Client,Edit_Client,Delete_Client,Setting,Share,About_us;
    String userid;
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
                            userid = mAuth.getUid();
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

    private ImageView photo;
    private EditText fname_e, mname_e, lname_e;
    private EditText gfname_e, gmname_e, glname_e, dob_e, birthplace_e, id_e, nationality_e;
    private EditText adri_road_house, adrii_vlg_area, adriii_city, adriv_po, adrv_pin, adrvi_dist, adrvii_state, adrviii_country;
    private EditText isd, std_e, mob, smob, tele, email;

    private RadioGroup gender_grp;
    private RadioButton male, female, unspecified;

    String fname, mname, lname, gfname, gmname, glname, dob, birthplace, id, nationality;
    String address_i, address_ii, city, country, post_office, areapin, dist, state;
    String isdcode, std, mobile_no, smobile_no, telephoneno, emailid;
    String gender, date, app_userid;
    String g, males, females, unspecifieds, app_username;

    String img;
//    String imgDecodableString;
//    private static int RESULT_LOAD_IMG = 1;

    //database manager
//    Cursor cur;
//    sqlitefetcher fetcher1;
//    ArrayAdapter adapter;
//    ArrayList<sqlitefetcher> sqlitelist;
//    ArrayList<String> arrayList;
//    sqlite_db db_access;
//    SQLiteDatabase db_add;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build();


    //Photo
//    private final static int RESULT_SELECT_IMAGE = 100;
//    public static final int MEDIA_TYPE_IMAGE = 1;
//    private static final String TAG = "GalleryUtil";
//
//    String mCurrentPhotoPath;
//    File photoFile = null;
//    int GALLERY_ACTIVITY_CODE=20000;
//    int RESULT_CROP = 40000;
    private static final int PICK_FROM_GALLERY = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclient_activity);

        mcontext = this;
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        setUpMenu();
//        db.onCreate(SQLiteDatabase db);
        //resources declared
        save = findViewById(R.id.save_data);
        Button menu = findViewById(R.id.menu);

        add_cust = findViewById(R.id.add_cust);

        photo = findViewById(R.id.clientphoto_btn);
        id_e = findViewById(R.id.client_id);
        fname_e = findViewById(R.id.fname);
        mname_e = findViewById(R.id.mname);
        lname_e = findViewById(R.id.lname);

        gfname_e = findViewById(R.id.gfname);
        gmname_e = findViewById(R.id.gmname);
        glname_e = findViewById(R.id.glname);
        dob_e = findViewById(R.id.dob);
        birthplace_e = findViewById(R.id.birthplace);
        nationality_e = findViewById(R.id.nationality);


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

        gender_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.g_male:
                        g = male.getText().toString();
                        break;
                    case R.id.g_female:
                        g = female.getText().toString();
                        break;
                    case R.id.g_unspecified:
                        g = unspecified.getText().toString();
                        break;
//                    default:
//                        g = male.getText().toString();
//                        break;
                }

            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Start Activity To Select Image From Gallery
                Intent intent = new Intent();
                //******call android default gallery
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //******code for crop image
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(
                            Intent.createChooser(intent, "Complete action using"),
                            PICK_FROM_GALLERY);
                } catch (ActivityNotFoundException e) {
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                adddata();
                id = id_e.getText().toString().trim();
                fname = fname_e.getText().toString().trim();
                mname = mname_e.getText().toString().trim();
                lname = lname_e.getText().toString().trim();
                gfname = gfname_e.getText().toString().trim();
                gmname = gmname_e.getText().toString().trim();
                glname = glname_e.getText().toString().trim();
                dob = dob_e.getText().toString().trim();
                gender = g;
                birthplace = birthplace_e.getText().toString().trim();
                nationality = nationality_e.getText().toString().trim();
                address_i = adri_road_house.getText().toString().trim();
                address_ii = adrii_vlg_area.getText().toString().trim();
                city = adriii_city.getText().toString().trim();
                post_office = adriv_po.getText().toString().trim();
                areapin = adrv_pin.getText().toString().trim();
                dist = adrvi_dist.getText().toString().trim();
                state = adrvii_state.getText().toString().trim();
                country = adrviii_country.getText().toString().trim();
                isdcode = isd.getText().toString().trim();
                std = std_e.getText().toString().trim();
                mobile_no = mob.getText().toString().trim();
                smobile_no = smob.getText().toString().trim();
                telephoneno = tele.getText().toString().trim();
                emailid = email.getText().toString().trim();
                app_userid = userid;
                date = com.google.firebase.Timestamp.now().toDate().toString();

                Map<String, Object> client = new HashMap<>();
                client.put("id", id);
//                client.put("img",);
                client.put("fname", fname);
                client.put("mname", mname);
                client.put("lname", lname);
                client.put("gfname", gfname);
                client.put("gmname", gmname);
                client.put("glname", glname);
                client.put("dob", dob);
                client.put("gender", gender);
                client.put("birthplace", birthplace);
                client.put("nationality", nationality);
                client.put("address_i", address_i);
                client.put("address_ii", address_ii);
                client.put("city", city);
                client.put("post_office", post_office);
                client.put("areapin", areapin);
                client.put("dist", dist);
                client.put("state", state);
                client.put("country", country);
                client.put("isdcode", isdcode);
                client.put("std", std);
                client.put("mobile_no", mobile_no);
                client.put("smobile_no", smobile_no);
                client.put("telephoneno", telephoneno);
                client.put("emailid", emailid);
                client.put("date", date);
                client.put("app_userid", app_userid);

                if (!validateInputs(fname, mname, lname, gfname,
                        gmname, glname, dob, birthplace, gender, nationality, id,
                        address_i, address_ii, city, post_office, areapin, dist, state, country,
                        isdcode, std, mobile_no, smobile_no, telephoneno, emailid,
                        date, app_userid)) {
                    fdb.collection("DATAKEEPER/" + app_userid + "/" + id).document("basic")
                            .set(client)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                    Toast.makeText(addclient_activity.this, "Client Added To Database...", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                    Toast.makeText(addclient_activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }

//    public void setDate() {
//        date = com.google.firebase.Timestamp.now().toDate().toString();
//    }

    private boolean validateInputs(String id, String fname, String mname, String lname, String gfname, String gmname, String glname, String dob, String gender, String birthplace, String nationality, String address_i, String
            address_ii, String city, String post_office, String areapin, String dist, String state, String country, String isdcode, String std, String mobile_no, String smobile_no, String telephoneno, String
                                           emailid, String date, String app_userid) {
        if (id.isEmpty()) {
            id_e.setError("Unique Identification Code Required..!!");
            id_e.requestFocus();
            return true;
        }
//        else if (fname.isEmpty()){
//            fname_e.setError("First Name Required..!!");
//            fname_e.requestFocus();
//            return true;
//        }
//        else if (areapin.isEmpty()){
//            adrv_pin.setError("Pin/Zip code Required..!!");
//            adrv_pin.requestFocus();
//            return true;
//        }
//        else if (mobile_no.isEmpty()){
//            mob.setError("Mobile Number Required..!!");
//            mob.requestFocus();
//            return true;
//        }
//        else if (country.isEmpty()){
//            adrviii_country.setError("First Name Required..!!");
//            adrviii_country.requestFocus();
//            return true;
//        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_FROM_GALLERY) {
                Bundle extras2 = data.getExtras();
                img = extras2.toString();
                if (extras2 != null) {
                    Bitmap image = extras2.getParcelable("data");
                    photo.setImageBitmap(image);
                }
            }
        } catch (RuntimeException x) {
            // display an error message
            String errorMessage = "Failure delivering result ResultInfo" +
                    "{who=null, request=2, result=0, data=null} to activity " +
                    "{inc.frontlooksoftwares.datakeeper/inc.frontlooksoftwares.datakeeper.addclient_activity}" +
                    ": java.lang.NullPointerException: Attempt to invoke virtual method " +
                    "'android.os.Bundle android.content.Intent.getExtras()' on a null object reference";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
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
