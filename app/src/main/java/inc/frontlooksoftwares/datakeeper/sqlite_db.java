package inc.frontlooksoftwares.datakeeper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.DateTimeKeyListener;

import inc.frontlooksoftwares.datakeeper.addclient_activity;

public class sqlite_db extends SQLiteOpenHelper {

    Context context;
    public static final String DATABASE_NAME = "datakeeper";
    public static final String clientdata_basic = "clientdata_basic";
    public static final String clientdata_details = "clientdata_details";
    public static final String clientdata_insurance = "clientdata_insurance";
    //Clientdata_Basic Table Data
    public static final String c_id = "c_id";
    public static final String c_img = "c_img";
    public static final String c_fname = "c_fname";
    public static final String c_mname = "c_mname";
    public static final String c_lname = "c_lname";
    public static final String c_gfname = "c_gfname";
    public static final String c_gmname = "c_gmname";
    public static final String c_glname = "c_glname";
    public static final String c_dob = "c_dob";
    public static final String c_birthplace = "c_birthplace";
    public static final String c_gender = "gender";
    public static final String c_nationality = "nationality";
    public static final String c_address_i = "address_i";
    public static final String c_address_ii = "address_ii";

    public static final String c_city = "city";
    public static final String c_post_office = "post_office";
    public static final String c_areapin = "areapin";
    public static final String c_dist = "dist";
    public static final String c_state = "state";
    public static final String c_country = "country";
    public static final String c_isdcode = "isdcode";
    public static final String c_std = "std";
    public static final String c_mobile_no = "mobile_no";
    public static final String c_smobile_no = "smobile_no";
    public static final String c_telephoneno = "telephoneno";
    public static final String c_emailid = "emailid";
    public static final String c_date = "date";
    public static final String c_app_username = "app_username";
    public static final String c_app_userid = "app_userid";


    public String fname, mname, lname, gfname, gmname, glname, dob, birthplace, id, nationality;
    public String address_i, address_ii, city, country, post_office, areapin, dist, state;
    public String isdcode, std, mobile_no, smobile_no, telephoneno, emailid;
    public String gender,males,females,unspecifieds;

    SQLiteDatabase db;
//    public static final String KEY_CREATED = DateTimeKeyListener.getInstance().toString();
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 1;
//    public static final String COL_27="";

    // clientdata_basic table create statement
    private static final String create_clientdata_basic = "CREATE TABLE IF NOT EXISTS " + clientdata_basic
            + "(" + c_id + "VARCHAR(50) PRIMARY KEY NOT NULL UNIQUE," + c_img + " BLOG," + c_fname + " VARCHAR(50),"+ c_mname + " VARCHAR(50),"+ c_lname + " VARCHAR(50)," + c_gfname + " VARCHAR(50),"
            + c_gmname + " VARCHAR(50),"+ c_glname + " VARCHAR(50),"+ c_dob + " DATE,"+ c_birthplace + " VARCHAR(50)," + c_gender + " VARCHAR(50),"+ c_nationality + " VARCHAR(50),"
            + c_address_i + " VARCHAR(50),"+ c_address_ii + " VARCHAR(50),"+ c_city + " VARCHAR(50),"+ c_post_office + " VARCHAR(50),"+ c_areapin + " VARCHAR(50),"
            + c_dist + " VARCHAR(50),"+ c_state + " VARCHAR(50),"+ c_country + " VARCHAR(50),"+ c_isdcode + " VARCHAR(50),"+ c_std + " VARCHAR(50),"
            + c_mobile_no + " VARCHAR(50)," + c_smobile_no + " VARCHAR(50),"+ c_telephoneno + " VARCHAR(50),"+ c_emailid + " VARCHAR(50),"+ c_date + " DATETIME,"
            + c_app_username + " VARCHAR(50)," + c_app_userid + " VARCHAR(50)" + ")";

//    private static final String insert_clientdata_basic= "insert into " + clientdata_basic
//            + "(" + c_id + ", " + c_img + ", " + c_fname + ","+ c_mname + ","+ c_lname + "," + c_gfname + " ,"
//            + c_gmname + " ,"+ c_glname + " ,"+ c_dob + ","+ c_birthplace + " ," + c_gender + " ,"+ c_nationality + " ,"
//            + c_address_i + " ,"+ c_address_ii + " ,"+ c_city + " ,"+ c_post_office + " ,"+ c_areapin + " ,"
//            + c_dist + ","+ c_state + " ,"+ c_country + " ,"+ c_isdcode + " ,"+ c_std + " ,"+ c_mobile_no + " ,"
//            + c_smobile_no + " ,"+ c_telephoneno + " ,"+ c_emailid + " ,"+ c_date + " ,"+ c_app_username + " ,"
//            + c_app_userid + ") " +
//
//            "values (" + id + ", " + img + ", " + fname + ","+ mname + ","+ lname + "," + gfname + " ,"
//            + gmname + " ,"+ glname + " ,"+ dob + ","+ birthplace + " ," + gender + " ,"+ nationality + " ,"
//            + address_i + " ,"+ address_ii + " ,"+ city + " ,"+ post_office + " ,"+ areapin + " ,"
//            + dist + " ,"+ state + " ,"+ country + " ,"+ isdcode + " ,"+ std + " ,"+ mobile_no + " ,"
//            + smobile_no + " ,"+ telephoneno + " ,"+ emailid + " ,"+ date + " ,"+ app_username + " ,"
//            + app_userid + ")";

//    public sqlite_db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public sqlite_db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context =  context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(create_clientdata_basic);
//        db.execSQL(CREATE_TABLE_TAG);
//        db.execSQL(CREATE_TABLE_TODO_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**Drop table when the database version change**/
        db.execSQL("drop table if exists " + clientdata_basic + ";");
        onCreate(db);
    }

//    public int ID() {
//        db = getReadableDatabase();
//        Cursor cur = db.rawQuery("Select NULLIF(max(" + c_id + "),0) from " + clientdata_basic, null);
//        int max = 0;
//        if (cur.getCount()>0) {
//            cur.moveToNext();
//            max = cur.getInt(0);
//        }
//        cur.close();
//        return max+1;
//    }

    public void Insert (){
        db=getWritableDatabase();
//        int max = ID();
//        db.execSQL(insert_clientdata_basic);
    }

    /**Return All the saved data as Cursor**/
    public Cursor view() {
        Cursor cur = db.rawQuery("Select * from " + clientdata_basic, null);
        return cur;
    }

    /**Returns true if exists else false**/
    public boolean checkExists(String id) {
        Cursor cur = db.rawQuery("Select * from " + clientdata_basic + " where " + c_id + " = '" + id + "'", null);
        return cur.getCount() > 0 ? true : false;
    }

}
