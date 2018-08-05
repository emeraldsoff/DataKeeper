//package inc.frontlooksoftwares.datakeeper;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class sqlite_db extends SQLiteOpenHelper {
//
//    Context context;
//    public static final String DATABASE_NAME = "datakeeper";
//    public static final String clientdata_basic = "clientdata_basic";
//    public static final String clientdata_details = "clientdata_details";
//    public static final String clientdata_insurance = "clientdata_insurance";
//    //Clientdata_Basic Table Data
//    public static final String c_id = "c_id";
//    public static final String c_img = "c_img";
//    public static final String c_fname = "c_fname";
//    public static final String c_mname = "c_mname";
//    public static final String c_lname = "c_lname";
//    public static final String c_gfname = "c_gfname";
//    public static final String c_gmname = "c_gmname";
//    public static final String c_glname = "c_glname";
//    public static final String c_dob = "c_dob";
//    public static final String c_birthplace = "c_birthplace";
//    public static final String c_gender = "gender";
//    public static final String c_nationality = "nationality";
//    public static final String c_address_i = "address_i";
//    public static final String c_address_ii = "address_ii";
//
//    public static final String c_city = "city";
//    public static final String c_post_office = "post_office";
//    public static final String c_areapin = "areapin";
//    public static final String c_dist = "dist";
//    public static final String c_state = "state";
//    public static final String c_country = "country";
//    public static final String c_isdcode = "isdcode";
//    public static final String c_std = "std";
//    public static final String c_mobile_no = "mobile_no";
//    public static final String c_smobile_no = "smobile_no";
//    public static final String c_telephoneno = "telephoneno";
//    public static final String c_emailid = "emailid";
//    public static final String c_date = "date";
//    public static final String c_app_userid = "app_userid";
//
//    SQLiteDatabase db;
////    public static final String KEY_CREATED = DateTimeKeyListener.getInstance().toString();
//    // Logcat tag
//    private static final String LOG = "DatabaseHelper";
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
////    public static final String COL_27="";
//
//    // clientdata_basic table create statement
//    private static final String create_clientdata_basic = "CREATE TABLE IF NOT EXISTS " + clientdata_basic
//            + "(" + c_id + " VARCHAR(50) PRIMARY KEY NOT NULL," + c_img + " BLOB," + c_fname + " VARCHAR(50),"+ c_mname + " VARCHAR(50),"+ c_lname + " VARCHAR(50)," + c_gfname + " VARCHAR(50),"
//            + c_gmname + " VARCHAR(50),"+ c_glname + " VARCHAR(50),"+ c_dob + " DATE,"+ c_birthplace + " VARCHAR(50)," + c_gender + " VARCHAR(50),"+ c_nationality + " VARCHAR(50),"
//            + c_address_i + " VARCHAR(50),"+ c_address_ii + " VARCHAR(50),"+ c_city + " VARCHAR(50),"+ c_post_office + " VARCHAR(50),"+ c_areapin + " VARCHAR(50),"
//            + c_dist + " VARCHAR(50),"+ c_state + " VARCHAR(50),"+ c_country + " VARCHAR(50),"+ c_isdcode + " VARCHAR(50),"+ c_std + " VARCHAR(50),"
//            + c_mobile_no + " VARCHAR(50)," + c_smobile_no + " VARCHAR(50),"+ c_telephoneno + " VARCHAR(50),"+ c_emailid + " VARCHAR(50),"+ c_date + " DATETIME,"
//            + c_app_userid + " VARCHAR(50)" + ")";
//
//    private static final String drop_clientdata_basic = "drop table if exists " + clientdata_basic + ";";
//
////    public sqlite_db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
////        super(context, name, factory, version);
////    }
//
//    public sqlite_db(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context =  context;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // creating required tables
//        db.execSQL(create_clientdata_basic);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        /**Drop table when the database version change**/
//        db.execSQL(drop_clientdata_basic);
//        onCreate(db);
//    }
//
////    public int ID() {
////        db = getReadableDatabase();
////        Cursor cur = db.rawQuery("Select NULLIF(max(" + c_id + "),0) from " + clientdata_basic, null);
////        int max = 0;
////        if (cur.getCount()>0) {
////            cur.moveToNext();
////            max = cur.getInt(0);
////        }
////        cur.close();
////        return max+1;
////    }
//
//    public boolean insert_clientdata_basic (String id, String img, String fname,String mname,String lname,String gfname,
//                                         String gmname, String glname, String dob,String gender, String birthplace,
//                                         String nationality, String address_i, String address_ii, String city, String country,
//                                         String post_office, String areapin, String dist,String  state, String isdcode,
//                                         String std, String mobile_no, String smobile_no, String telephoneno, String emailid,
//                                         String date,String app_userid)
//    {
//        db=getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(c_id,id);
//        contentValues.put(c_img,img);
//        contentValues.put(c_fname,fname);
//        contentValues.put(c_mname,mname);
//        contentValues.put(c_lname,lname);
//        contentValues.put(c_gfname,gfname);
//        contentValues.put(c_gmname,gmname);
//        contentValues.put(c_glname,glname);
//        contentValues.put(c_dob,dob);
//        contentValues.put(c_gender,gender);
//        contentValues.put(c_birthplace,birthplace);
//        contentValues.put(c_nationality,nationality);
//        contentValues.put(c_address_i,address_i);
//        contentValues.put(c_address_ii,address_ii);
//        contentValues.put(c_city,city);
//        contentValues.put(c_country,country);
//        contentValues.put(c_post_office,post_office);
//        contentValues.put(c_areapin,areapin);
//        contentValues.put(c_dist,dist);
//        contentValues.put(c_state,state);
//        contentValues.put(c_isdcode,isdcode);
//        contentValues.put(c_std,std);
//        contentValues.put(c_mobile_no,mobile_no);
//        contentValues.put(c_smobile_no,smobile_no);
//        contentValues.put(c_telephoneno,telephoneno);
//        contentValues.put(c_emailid,emailid);
//        contentValues.put(c_date,date);
////        contentValues.put(c_app_username,app_username);
//        contentValues.put(c_app_userid,app_userid);
//        db.insert(clientdata_basic, null, contentValues);
//        return true;
////        int max = ID();
////
//    }
//
//    public boolean update_clientdata_basic (String id, String img, String fname,String mname,String lname,String gfname,
//                                            String gmname, String glname, String dob,String gender, String birthplace,
//                                            String nationality, String address_i, String address_ii, String city, String country,
//                                            String post_office, String areapin, String dist,String  state, String isdcode,
//                                            String std, String mobile_no, String smobile_no, String telephoneno, String emailid,
//                                            String date,String app_userid)
//    {
////        String x=id.toString();
//        db=getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(c_id,id);
//        contentValues.put(c_img,img);
//        contentValues.put(c_fname,fname);
//        contentValues.put(c_mname,mname);
//        contentValues.put(c_lname,lname);
//        contentValues.put(c_gfname,gfname);
//        contentValues.put(c_gmname,gmname);
//        contentValues.put(c_glname,glname);
//        contentValues.put(c_dob,dob);
//        contentValues.put(c_gender,gender);
//        contentValues.put(c_birthplace,birthplace);
//        contentValues.put(c_nationality,nationality);
//        contentValues.put(c_address_i,address_i);
//        contentValues.put(c_address_ii,address_ii);
//        contentValues.put(c_city,city);
//        contentValues.put(c_country,country);
//        contentValues.put(c_post_office,post_office);
//        contentValues.put(c_areapin,areapin);
//        contentValues.put(c_dist,dist);
//        contentValues.put(c_state,state);
//        contentValues.put(c_isdcode,isdcode);
//        contentValues.put(c_std,std);
//        contentValues.put(c_mobile_no,mobile_no);
//        contentValues.put(c_smobile_no,smobile_no);
//        contentValues.put(c_telephoneno,telephoneno);
//        contentValues.put(c_emailid,emailid);
//        contentValues.put(c_date,date);
////        contentValues.put(c_app_username,app_username);
//        contentValues.put(c_app_userid,app_userid);
//        db.update(clientdata_basic, contentValues,id + " = ? ",new String[]{id});
//        return true;
////        int max = ID();
////
//    }
//
////    public void update_clientdata_basic (String img, String fname,String mname,String lname,String gfname,String gmname, String glname, String dob, String birthplace,
////                                         String id, String nationality, String address_i, String address_ii, String city, String country, String post_office, String areapin,
////                                         String dist,String  state, String isdcode, String std, String mobile_no, String smobile_no, String telephoneno, String emailid,
////                                         String gender,String date,String app_username,String app_userid)
////    {
////        db=getWritableDatabase();
////        db.execSQL("update "+ clientdata_basic +" set "
////                + c_id + " = '"+id+"'," + c_img + " = '"+img+"', " + c_fname + " = '"+fname+"',"+ c_mname + " = '"+mname+"',"+ c_lname + " = '"+lname+"',"
////                + c_gfname + " = '"+gfname+"' ," + c_gmname + " = '"+gmname+"' ,"+ c_glname + " = '"+glname+"' ,"+ c_dob + " = '"+dob+"',"+ c_birthplace + " = '"+birthplace+"' ,"
////                + c_gender + " = '"+gender+"' ," + c_nationality + " = '"+nationality+"' ," + c_address_i + " = '"+address_i+"' ,"+ c_address_ii + " = '"+address_ii+"' ,"
////                + c_city + " = '"+city+"',"+ c_post_office + "  = '"+post_office+"',"+ c_areapin + "  = '"+areapin+"' ," + c_dist + "  = '"+dist+"',"+ c_state + "  = '"+state+"',"
////                + c_country + "  = '"+country+"',"+ c_isdcode + "  = '"+isdcode+"',"+ c_std + "  = '"+std+"',"+ c_mobile_no + "  = '"+mobile_no+"',"
////                + c_smobile_no + "  = '"+smobile_no+"',"+ c_telephoneno + "  = '"+telephoneno+"',"+ c_emailid + "  = '"+emailid+"',"+ c_date + "  = '"+date+"',"
////                + c_app_username + "  = '"+app_username+"'," + c_app_userid + " = '"+app_userid+"' where "+c_id+"='"+id+"';");
////    }
//
////    public void delete_clientdata_basic (String img, String fname,String mname,String lname,String gfname,String gmname, String glname, String dob, String birthplace,
////                                         String id, String nationality, String address_i, String address_ii, String city, String country, String post_office, String areapin,
////                                         String dist,String  state, String isdcode, String std, String mobile_no, String smobile_no, String telephoneno, String emailid,
////                                         String gender,String date,String app_username,String app_userid)
////    {
////        db=getWritableDatabase();
//////        int max = ID();
////        db.execSQL("update "+ clientdata_basic +" set "
////                + c_id + " = '"+id+"'," + c_img + " = '"+img+"', " + c_fname + " = '"+fname+"',"+ c_mname + " = '"+mname+"',"+ c_lname + " = '"+lname+"',"
////                + c_gfname + " = '"+gfname+"' ," + c_gmname + " = '"+gmname+"' ,"+ c_glname + " = '"+glname+"' ,"+ c_dob + " = '"+dob+"',"+ c_birthplace + " = '"+birthplace+"' ,"
////                + c_gender + " = '"+gender+"' ," + c_nationality + " = '"+nationality+"' ," + c_address_i + " = '"+address_i+"' ,"+ c_address_ii + " = '"+address_ii+"' ,"
////                + c_city + " = '"+city+"',"+ c_post_office + "  = '"+post_office+"',"+ c_areapin + "  = '"+areapin+"' ," + c_dist + "  = '"+dist+"',"+ c_state + "  = '"+state+"',"
////                + c_country + "  = '"+country+"',"+ c_isdcode + "  = '"+isdcode+"',"+ c_std + "  = '"+std+"',"+ c_mobile_no + "  = '"+mobile_no+"',"
////                + c_smobile_no + "  = '"+smobile_no+"',"+ c_telephoneno + "  = '"+telephoneno+"',"+ c_emailid + "  = '"+emailid+"',"+ c_date + "  = '"+date+"',"
////                + c_app_username + "  = '"+app_username+"'," + c_app_userid + " = '"+app_userid+"' where "+c_id+"='"+id+"';");
////    }
//
//    /**Return All the saved data as Cursor**/
//    public Cursor getclient(String x) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery( "SELECT * FROM " + clientdata_basic + " WHERE " +
//                c_id + "=?", new String[]{x} );
//        return res;
//    }
//    public Cursor getAllclients() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery( "SELECT * FROM " + clientdata_basic, null );
//        return res;
//    }
//
//    public int deleteClient(String x) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(clientdata_basic, c_id + " = ? ", new String[] {x});
//    }
//
//}
