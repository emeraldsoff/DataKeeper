package inc.frontlooksoftwares.datakeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class addclient_activity extends Activity {

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
    private EditText fname, mname, lname;
    private EditText gfname, gmname, glname, dob, birthplace, id, nationality;
    private EditText adri_road_house, adrii_vlg_area, adriii_city, adriv_po, adrv_pin, adrvi_dist, adrvii_state, adrviii_country;
    private EditText isd, std, mob, smob, tele, email;

    private RadioGroup gender_grp;
    private RadioButton male, female, unspecified;

    private String fnames, mnames, lnames, gfnames, gmnames, glnames, dobs, birthplaces, ids, nationalitys;
    private String adri_road_houses, adrii_vlg_areas, adriii_citys, adriv_pos, adrv_pins, adrvi_dists, adrvii_states, adrviii_countrys;
    private String isds, stds, mobs, smobs, teles, emails;
    private String males, females, unspecifieds, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclient_activity);
        //resources declared
        save = findViewById(R.id.save_data);
        add_cust = findViewById(R.id.add_cust);

        photo = findViewById(R.id.clientphoto_btn);

        fname = findViewById(R.id.fname);
        mname = findViewById(R.id.mname);
        lname = findViewById(R.id.lname);

        gfname = findViewById(R.id.gfname);
        gmname = findViewById(R.id.gmname);
        glname = findViewById(R.id.glname);
        dob = findViewById(R.id.dob);
        birthplace = findViewById(R.id.birthplace);
        nationality = findViewById(R.id.nationality);
        id = findViewById(R.id.client_id);

        adri_road_house = findViewById(R.id.addressi);
        adrii_vlg_area = findViewById(R.id.addressii);
        adriii_city = findViewById(R.id.addressiii);
        adriv_po = findViewById(R.id.addressiv);
        adrv_pin = findViewById(R.id.addressv);
        adrvi_dist = findViewById(R.id.addressvi);
        adrvii_state = findViewById(R.id.addressvii);
        adrviii_country = findViewById(R.id.addressviii);

        isd = findViewById(R.id.isd_code);
        std = findViewById(R.id.std_code);
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

        fnames = fname.toString();
        mnames = mname.toString();
        lnames = lname.toString();

        gfnames = gfname.toString();
        gmnames = gmname.toString();
        glnames = glname.toString();
        dobs = dob.toString();
        birthplaces = birthplace.toString();
        males = male.getText().toString();
        females = female.getText().toString();
        unspecifieds = unspecified.getText().toString();
        nationalitys = nationality.toString();
        ids = id.toString();

        adri_road_houses = adrii_vlg_area.toString();
        adrii_vlg_areas = adrii_vlg_area.toString();
        adriii_citys = adriii_city.toString();
        adriv_pos = adriv_po.toString();
        adrv_pins = adrv_pin.toString();
        adrvi_dists = adrvi_dist.toString();
        adrvii_states = adrvii_state.toString();
        adrviii_countrys = adrviii_country.toString();

        isds = isd.toString();
        stds = std.toString();
        mobs = mob.toString();
        smobs = smob.toString();
        teles = tele.toString();
        emails = email.toString();

        gender_grp.getCheckedRadioButtonId();

        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

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
}
