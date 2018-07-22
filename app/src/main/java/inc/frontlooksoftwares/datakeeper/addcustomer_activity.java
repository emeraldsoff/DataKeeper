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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class addcustomer_activity extends Activity {

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
                            startActivity(new Intent(addcustomer_activity.this, loginactivity.class));
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

    private ImageButton c_photo;
    private EditText cfname, cmname, clname;
    private EditText cgfname, cgmname, cglname, cdob, cid_no;
    private EditText cadri_road_house, cadrii_vlg_area, cadriii_city, cadriv_po, cadrv_pin, cadrvi_dist, cadrvii_state, cadrviii_country;
    private EditText cisd, cstd, cmob, csmob, ctele, cemail;

    private String cfnames, cmnames, clnames, cgfnames, cgmnames, cglnames, cdobs, cid_nos;
    private String cadri_road_houses, cadrii_vlg_areas, cadriii_citys, cadriv_pos, cadrv_pins, cadrvi_dists, cadrvii_states, cadrviii_countrys;
    private String cisds, cstds, cmobs, csmobs, cteles, cemails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcustomer_activity);
        //resources declared
        save = findViewById(R.id.save_data);
        add_cust = findViewById(R.id.add_cust);

        c_photo = findViewById(R.id.photo);

        cfname = findViewById(R.id.fname);
        cmname = findViewById(R.id.mname);
        clname = findViewById(R.id.lname);

        cgfname = findViewById(R.id.gfname);
        cgmname = findViewById(R.id.gmname);
        cglname = findViewById(R.id.glname);
        cdob = findViewById(R.id.dob);
        cid_no = findViewById(R.id.client_id);

        cadri_road_house = findViewById(R.id.addressi);
        cadrii_vlg_area = findViewById(R.id.addressii);
        cadriii_city = findViewById(R.id.addressiii);
        cadriv_po = findViewById(R.id.addressiv);
        cadrv_pin = findViewById(R.id.addressv);
        cadrvi_dist = findViewById(R.id.addressvi);
        cadrvii_state = findViewById(R.id.addressvii);
        cadrviii_country = findViewById(R.id.addressviii);

        cisd = findViewById(R.id.isd_code);
        cstd = findViewById(R.id.std_code);
        cmob = findViewById(R.id.mob_no);
        csmob = findViewById(R.id.sec_mob_no);
        ctele = findViewById(R.id.telephone_no);
        cemail = findViewById(R.id.email_id);

        //strings

        c_photo = findViewById(R.id.photo);

        cfnames = cfname.toString();
        cmnames = cmname.toString();
        clnames = clname.toString();

        cgfnames = cgfname.toString();
        cgmnames = cgmname.toString();
        cglnames = cglname.toString();
        cdobs = cdob.toString();
        cid_nos = cid_no.toString();

        cadri_road_houses = cadrii_vlg_area.toString();
        cadrii_vlg_areas = cadrii_vlg_area.toString();
        cadriii_citys = cadriii_city.toString();
        cadriv_pos = cadriv_po.toString();
        cadrv_pins = cadrv_pin.toString();
        cadrvi_dists = cadrvi_dist.toString();
        cadrvii_states = cadrvii_state.toString();
        cadrviii_countrys = cadrviii_country.toString();

        cisds = cisd.toString();
        cstds = cstd.toString();
        cmobs = cmob.toString();
        csmobs = csmob.toString();
        cteles = ctele.toString();
        cemails = cemail.toString();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
