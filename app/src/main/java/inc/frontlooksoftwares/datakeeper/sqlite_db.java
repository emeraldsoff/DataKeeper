package inc.frontlooksoftwares.datakeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlite_db extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "datakeeper.db";
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
    public static final String address_i = "address_i";
    public static final String address_ii = "address_ii";

    //    private static final String create_clientdata_basic = "CREATE TABLE "
//            + clientdata_basic + "(" + c_id + " PRIMARY KEY," + c_img
//            + " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
//            + " DATETIME" + ")";
    public static final String city = "city";
    public static final String post_office = "post_office";
    public static final String areapin = "areapin";
    public static final String state = "state";
    public static final String country = "country";
    public static final String isdcode = "isdcode";
    public static final String std = "std";
    public static final String mobile_no = "mobile_no";
    public static final String smobile_no = "smobile_no";
    public static final String telephoneno = "telephoneno";
    public static final String emailid = "emailid";
    public static final String app_username = "app_username";
    public static final String app_userid = "app_userid";
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 1;
//    public static final String COL_27="";

    // clientdata_basic table create statement
//    private static final String create_clientdata_basic = "CREATE TABLE "
//            + clientdata_basic + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TODO
//            + " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
//            + " DATETIME" + ")";


    public sqlite_db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
