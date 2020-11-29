package mavs.uta.team4carental.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.pojo.User;

public class DBHelper extends SQLiteOpenHelper {

    private static class TABLE_LIST {
        public static String USER = "tbl_user";
        public static String CAR = "tbl_car";
        public static String Reservation = "tbl_reservation";
    }

    private ArrayList userFields = new ArrayList<String>();

    public static final String DB_NAME = "test2";
    public static final int DB_VERSION = 1;

    private static final String RESERVATION_CREATE =
            "create table "+ TABLE_LIST.Reservation + " (" +
                    EnumTable.Reservation.RESERVATIONNUMBER               + " varchar(30) primary key, " +
                    EnumTable.Reservation.USERNAME                        + " varchar(30) not null, "    +
                    EnumTable.Reservation.CARNAME                         + " varchar(30) not null, "    +
                    EnumTable.Reservation.START                           + " varchar(30) not null, "    +
                    EnumTable.Reservation.Back                             + " varchar(30) not null, "    +
                    EnumTable.Reservation.GPS                             + " varchar(30) not null, "    +
                    EnumTable.Reservation.ONSTAR                          + " varchar(20) not null, "    +
                    EnumTable.Reservation.SIRIUSXM                        + " varchar(20) not null, "    +
                    EnumTable.Reservation.TOTALPRICE                      + " varchar(20) not null, "    +
                    EnumTable.Reservation.STATUS                          + " varchar(20) not null "     +
                    ")";
    private static final String CAR_CREATE =
            "create table "+ TABLE_LIST.CAR + " (" +
                    EnumTable.CAR.CARNAME       + " varchar(30) primary key, " +
                    EnumTable.CAR.CAPACITY      + " varchar(30) not null, " +
                    EnumTable.CAR.WEEKDAY       + " varchar(30) not null," +
                    EnumTable.CAR.WEEKEND       + " varchar(10) not null," +
                    EnumTable.CAR.WEEK          + " varchar(30) not null," +
                    EnumTable.CAR.GPS           + " varchar(30) not null," +
                    EnumTable.CAR.ONSTAR        + " varchar(20) not null," +
                    EnumTable.CAR.SIRIUSXM      + " varchar(20) not null" +
                    ")";
    private static final String USER_CREATE =
            "create table "+ TABLE_LIST.USER + " (" +
                    EnumTable.User.ID           + " integer primary key autoincrement, " +
                    EnumTable.User.USERNAME     + " varchar(30) not null, " +
                    EnumTable.User.PASSWORD     + " varchar(30) not null," +
                    EnumTable.User.ROLE         + " varchar(10) not null," +
                    EnumTable.User.UTAID        + " varchar(10) not null," +
                    EnumTable.User.LASTNAME     + " varchar(30) not null," +
                    EnumTable.User.FIRSTNAME    + " varchar(30) not null," +
                    EnumTable.User.PHONE        + " varchar(20) not null," +
                    EnumTable.User.EMAIL        + " varchar(30) not null," +
                    EnumTable.User.ADDRESS      + " varchar(20)," +
                    EnumTable.User.CITY         + " varchar(20)," +
                    EnumTable.User.STATE        + " varchar(20)," +
                    EnumTable.User.ZIPCODE      + " varchar(20)," +
                    EnumTable.User.MEMBER       + " varchar(20)," +
                    EnumTable.User.STATUS       + " varchar(20) not null" +
                    ")";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        Field[] fields = User.class.getDeclaredFields();
//        for (Field field: fields) {
//            String field_name = field.toString().split(" ")[2];
//            String[] group = field_name.split("\\.");
//            userFields.add(group[group.length - 1]);
//        }
//        Log.d("DBHelper", "constructor");
//        Log.d("DBHelper", userFields.toString());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "create TABEL");
        db.execSQL(USER_CREATE);
        db.execSQL(CAR_CREATE);
        db.execSQL(RESERVATION_CREATE);
        init_Car_tbl(db);//把车表中的车插入数据库中
        init_reservation_tbl(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST.USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST.Reservation);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST.CAR);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void init_reservation_tbl(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Reservation.Back, "2020-10-11-4:00");
        cv.put(EnumTable.Reservation.START, "2020-10-12-4:00");
        cv.put(EnumTable.Reservation.CARNAME, "Smart");
        cv.put(EnumTable.Reservation.GPS, "1");
        cv.put(EnumTable.Reservation.ONSTAR, "1");
        cv.put(EnumTable.Reservation.RESERVATIONNUMBER, "1111");
        cv.put(EnumTable.Reservation.SIRIUSXM, "1");
        cv.put(EnumTable.Reservation.STATUS, "1");
        cv.put(EnumTable.Reservation.TOTALPRICE, "67.98");
        cv.put(EnumTable.Reservation.USERNAME, "321");
        long res = db.insert(TABLE_LIST.Reservation, null, cv);
        Log.e("[inittable]", "init_reservation_tbl: " + res);

        cv = new ContentValues();
        cv.put(EnumTable.Reservation.Back, "2020-10-13-4:00");
        cv.put(EnumTable.Reservation.START, "2020-10-14-4:00");
        cv.put(EnumTable.Reservation.CARNAME, "Smart");
        cv.put(EnumTable.Reservation.GPS, "1");
        cv.put(EnumTable.Reservation.ONSTAR, "1");
        cv.put(EnumTable.Reservation.RESERVATIONNUMBER, "2222");
        cv.put(EnumTable.Reservation.SIRIUSXM, "1");
        cv.put(EnumTable.Reservation.STATUS, "1");
        cv.put(EnumTable.Reservation.TOTALPRICE, "127.96");
        cv.put(EnumTable.Reservation.USERNAME, "123456");
        db.insert(TABLE_LIST.Reservation, null, cv);
        res = db.insert(TABLE_LIST.Reservation, null, cv);
        Log.e("[inittable]", "init_reservation_tbl: " + res);
    }

    public String init_Car_tbl(SQLiteDatabase db){

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "Smart");
        cv.put(EnumTable.CAR.CAPACITY, "1");
        cv.put(EnumTable.CAR.WEEKDAY,     "32.99");
        cv.put(EnumTable.CAR.WEEKEND, "37.99");
        cv.put(EnumTable.CAR.WEEK,"230.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        long res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "Economy");
        cv.put(EnumTable.CAR.CAPACITY, "3");
        cv.put(EnumTable.CAR.WEEKDAY,     "39.99");
        cv.put(EnumTable.CAR.WEEKEND, "44.99");
        cv.put(EnumTable.CAR.WEEK,"279.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "Compact");
        cv.put(EnumTable.CAR.CAPACITY, "4");
        cv.put(EnumTable.CAR.WEEKDAY,     "44.99");
        cv.put(EnumTable.CAR.WEEKEND, "49.99");
        cv.put(EnumTable.CAR.WEEK,"314.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "Intermediate");
        cv.put(EnumTable.CAR.CAPACITY, "4");
        cv.put(EnumTable.CAR.WEEKDAY,     "45.99");
        cv.put(EnumTable.CAR.WEEKEND, "50.99");
        cv.put(EnumTable.CAR.WEEK,"321.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "Standard");
        cv.put(EnumTable.CAR.CAPACITY, "5");
        cv.put(EnumTable.CAR.WEEKDAY,     "48.99");
        cv.put(EnumTable.CAR.WEEKEND, "53.99");
        cv.put(EnumTable.CAR.WEEK,"342.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "Full_Size");
        cv.put(EnumTable.CAR.CAPACITY, "6");
        cv.put(EnumTable.CAR.WEEKDAY,     "52.99");
        cv.put(EnumTable.CAR.WEEKEND, "57.99");
        cv.put(EnumTable.CAR.WEEK,"370.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "SUV");
        cv.put(EnumTable.CAR.CAPACITY, "8");
        cv.put(EnumTable.CAR.WEEKDAY,     "59.99");
        cv.put(EnumTable.CAR.WEEKEND, "64.99");
        cv.put(EnumTable.CAR.WEEK,"419.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "MiniVan");
        cv.put(EnumTable.CAR.CAPACITY, "9");
        cv.put(EnumTable.CAR.WEEKDAY,     "59.99");
        cv.put(EnumTable.CAR.WEEKEND, "64.99");
        cv.put(EnumTable.CAR.WEEK,"419.93");
        cv.put(EnumTable.CAR.GPS,    "3.00");
        cv.put(EnumTable.CAR.ONSTAR,    "5.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "7.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";

        cv = new ContentValues();
        cv.put(EnumTable.CAR.CARNAME, "Ultra_Sports");
        cv.put(EnumTable.CAR.CAPACITY, "2");
        cv.put(EnumTable.CAR.WEEKDAY,     "199.99");
        cv.put(EnumTable.CAR.WEEKEND, "204.99");
        cv.put(EnumTable.CAR.WEEK,"1399.93");
        cv.put(EnumTable.CAR.GPS,    "5.00");
        cv.put(EnumTable.CAR.ONSTAR,    "7.00");
        cv.put(EnumTable.CAR.SIRIUSXM,  "9.00");
        res = db.insert(TABLE_LIST.CAR, null, cv);
        if(res == -1)
            return "failed";


        return "Car_tbl Created Successfully";
    }
    public String addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.User.USERNAME, user.getUsername());
        cv.put(EnumTable.User.PASSWORD, user.getPassword());
        cv.put(EnumTable.User.ROLE,     user.getRole());
        cv.put(EnumTable.User.UTAID,    user.getUta_id());
        cv.put(EnumTable.User.LASTNAME, user.getLastname());
        cv.put(EnumTable.User.FIRSTNAME,user.getFirstname());
        cv.put(EnumTable.User.PHONE,    user.getPhone());
        cv.put(EnumTable.User.EMAIL,    user.getEmail());
        cv.put(EnumTable.User.ADDRESS,  user.getAddress());
        cv.put(EnumTable.User.CITY,     user.getCity());
        cv.put(EnumTable.User.STATE,    user.getState());
        cv.put(EnumTable.User.ZIPCODE,  user.getZipcode());
        cv.put(EnumTable.User.MEMBER,   user.getMember());
        cv.put(EnumTable.User.STATUS,   user.getStatus());

        long res = db.insert(TABLE_LIST.USER, null, cv);
        if(res == -1)
            return "failed";
        else
            return "Account Created Successfully";
    }

    //预约车辆
    public String addReservation(Rental rental){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Reservation.RESERVATIONNUMBER,rental.getID());
        cv.put(EnumTable.Reservation.USERNAME,rental.getUsername());
        cv.put(EnumTable.Reservation.CARNAME,rental.getCarName());
        cv.put(EnumTable.Reservation.START,rental.getStart());
        cv.put(EnumTable.Reservation.Back,rental.getEnd());
        cv.put(EnumTable.Reservation.GPS,rental.getGPS());
        cv.put(EnumTable.Reservation.ONSTAR,rental.getOnstar());
        cv.put(EnumTable.Reservation.SIRIUSXM,rental.getSiriusxm());
        cv.put(EnumTable.Reservation.TOTALPRICE,rental.getTotalPrice());
        cv.put(EnumTable.Reservation.STATUS,rental.getStatus());
        long res = db.insert(TABLE_LIST.Reservation, null, cv);
        if(res == -1)
            return "failed";
        else
            return "Reserve Successfully";
    }
    public String queryUserstatus(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                EnumTable.User.USERNAME + "=\"" + username+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String r = cursor.getString(cursor.getColumnIndex(EnumTable.User.MEMBER));
            cursor.close();
            if(r==null){
                return "None";
            }
            return  r;
        } else {
            cursor.close();
            return "None";
        }
    }
    public String queryUserliveordead(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                EnumTable.User.USERNAME + "=\"" + username+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String r = cursor.getString(cursor.getColumnIndex(EnumTable.User.STATUS));
            cursor.close();
            return  r;
        } else {
            cursor.close();
            return "None";
        }
    }

    public User[] queryUser(String columns, String[] value) {
        ArrayList<User> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                columns,
                value,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex(EnumTable.User.USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(EnumTable.User.PASSWORD));
            String role = cursor.getString(cursor.getColumnIndex(EnumTable.User.ROLE));
            String uta_id = cursor.getString(cursor.getColumnIndex(EnumTable.User.UTAID));
            String firstname = cursor.getString(cursor.getColumnIndex(EnumTable.User.FIRSTNAME));
            String lastname = cursor.getString(cursor.getColumnIndex(EnumTable.User.LASTNAME));
            String phone = cursor.getString(cursor.getColumnIndex(EnumTable.User.PHONE));
            String email = cursor.getString(cursor.getColumnIndex(EnumTable.User.EMAIL));
            String address = cursor.getString(cursor.getColumnIndex(EnumTable.User.ADDRESS));
            String city = cursor.getString(cursor.getColumnIndex(EnumTable.User.CITY));
            String state = cursor.getString(cursor.getColumnIndex(EnumTable.User.STATE));
            String zipcode = cursor.getString(cursor.getColumnIndex(EnumTable.User.ZIPCODE));
            String member = cursor.getString(cursor.getColumnIndex(EnumTable.User.MEMBER));
            String status = cursor.getString(cursor.getColumnIndex(EnumTable.User.STATUS));

            // TODO: not finished
            result.add(new User(
                    username,
                    password,
                    role,
                    uta_id,
                    lastname,
                    firstname,
                    phone,
                    email,
                    address,
                    city,
                    state,
                    zipcode,
                    member,
                    status
            ));
        }
        cursor.close();
        User[] result_user = new User[result.size()];
        result.toArray(result_user);
        return result_user;
    }

    public String editUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.User.USERNAME, user.getUsername());
        cv.put(EnumTable.User.PASSWORD, user.getPassword());
        cv.put(EnumTable.User.ROLE,     user.getRole());
        cv.put(EnumTable.User.UTAID,    user.getUta_id());
        cv.put(EnumTable.User.LASTNAME, user.getLastname());
        cv.put(EnumTable.User.FIRSTNAME,user.getFirstname());
        cv.put(EnumTable.User.PHONE,    user.getPhone());
        cv.put(EnumTable.User.EMAIL,    user.getEmail());
        cv.put(EnumTable.User.ADDRESS,  user.getAddress());
        cv.put(EnumTable.User.CITY,     user.getCity());
        cv.put(EnumTable.User.STATE,    user.getState());
        cv.put(EnumTable.User.ZIPCODE,  user.getZipcode());
        cv.put(EnumTable.User.MEMBER,   user.getMember());
        cv.put(EnumTable.User.STATUS,   user.getStatus());

        long res = db.update(TABLE_LIST.USER, cv, "USERNAME=?", new String[]{user.getUsername()});
        if(res == -1)
            return "failed";
        else
            return "User Update Successfully";

    }

    //输入车名，起始时间，结束时间，gps，onstar，siriusxm来计算最终价格
    public String getTotalPrcie(String carName, String start, String back, String gps, String onstar, String siriusxm) {
        SQLiteDatabase db = this.getWritableDatabase();
        // 根据车名将车的wekkday， wenkday提取出来
        Cursor cursor = db.query(
                TABLE_LIST.CAR,
                null,
                EnumTable.CAR.CARNAME + "=\"" + carName + "\" ",
                null,
                null,
                null,
                null);
        String weekday = null;
        String weekend = null;
        String gpsPrice = null;
        String onstarPrice = null;
        String siriusxmPrice = null;
        int dazhe = 0;
        while (cursor.moveToNext()) {

            weekday = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.WEEKDAY));
            weekend = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.WEEKEND));
            gpsPrice = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.GPS));
            onstarPrice = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.ONSTAR));
            siriusxmPrice = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.SIRIUSXM));

        }

        //计算不考虑三个extras的报价
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date startdate = null;
        Date backdate = null;
        try {
            startdate = format.parse(start);
            backdate = format.parse(back);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        float durations = (backdate.getTime() - startdate.getTime()) / (1000 * 24 * 60 * 60);
        if (((backdate.getTime() - startdate.getTime()) % (1000 * 24 * 60 * 60)) == 0) {

        } else {
            durations += 1;
        }
        String dur = String.valueOf(durations);
        long flag = startdate.getTime();
        int day_of_weekend = 0;
        int day_of_weekday = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startdate);
        int start_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        cal.setTime(backdate);
        int back_week = cal.get(Calendar.DAY_OF_WEEK) - 1;

        int flag_week = start_week;
        for (; (backdate.getTime() - flag) > (1000 * 24 * 60 * 60); ) {
            if (flag_week == 0 || flag_week == 6) {
                day_of_weekend += 1;
            } else {
                day_of_weekday += 1;
            }
            flag_week += 1;
            flag_week = flag_week % 7;
            flag += (1000 * 24 * 60 * 60);
        }
        if (back_week == 0 || back_week == 6) {
            day_of_weekend += 1;
        } else {
            day_of_weekday += 1;
        }


//
//
////        TextView test = findViewById(R.id.for_test);
////        if (car != null) {
////            test.setText(car.toString());
////        } else {
////            test.setText("error token");
////        }
////        Intent i = getIntent();
////        Car car = (Car) i.getSerializableExtra(CarListAdapter.CAR_INTENT_TOKEN);


        float price_weekday = Float.valueOf(weekday);
        float price_weekend = Float.valueOf(weekend);
        final float[] price = {price_weekday * day_of_weekday + price_weekend * day_of_weekend};


//        System.out.println(totalprice);
        String totalprice = "";
        totalprice = String.valueOf(price[0]);


//        totalprice = String.valueOf(price[0]);
//        final String finalExtra = extra;

        final String[] flag_gps = {"0"};
        final String[] flag_onstar = {"0"};
        final String[] flag_siriusXM = {"0"};
        final String[] extras = {""};

        int finalDazhe = dazhe;

        float finalDurations = durations;
        String carname = carName;
        // 计算考虑上extras之后的价格
        if(gps.equals("1")){
            flag_gps[0] ="1";
            price[0] += finalDurations *(Float.valueOf(gpsPrice));
            extras[0] +="gps ";
        }
        if(onstar.equals("1")){
            flag_onstar[0] ="1";
            price[0] += finalDurations *(Float.valueOf(onstarPrice));
            extras[0]+="onstar ";
        }
        if(siriusxm.equals("1")){
            flag_siriusXM[0] ="1";
            price[0] += finalDurations *(Float.valueOf(siriusxmPrice));
            extras[0]+="siriusXM ";
        }
        if(finalDazhe == 1){
            price[0]= (float) (price[0]*0.75);
        }
        price[0]+=price[0]*0.0825;
        totalprice = String.valueOf(price[0]);


        final String finaltotalprice = totalprice;

        return finaltotalprice;
    }

    public String queryLogin(String qusername, String qpassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                EnumTable.User.USERNAME + "=\"" + qusername + "\" AND " + EnumTable.User.PASSWORD + "=\"" + qpassword +"\"",
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String r = cursor.getString(cursor.getColumnIndex(EnumTable.User.ROLE));
            cursor.close();
            return  r;
        } else {
            cursor.close();
            return "None";
        }
    }

    public Car[] queryCar() {
        ArrayList<Car> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.CAR,
                null,
                null,
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            String carname = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.CARNAME));
            String capacity= cursor.getString(cursor.getColumnIndex(EnumTable.CAR.CAPACITY));
            String weekday = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.WEEKDAY));
            String weekend = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.WEEKEND));
            String week = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.WEEK));
            String GPS = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.GPS));
            String onstar = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.ONSTAR));
            String siriusXM = cursor.getString(cursor.getColumnIndex(EnumTable.CAR.SIRIUSXM));
            // TODO: not finished
            result.add(new Car(
                    carname,
                    capacity,
                    weekday,
                    weekend,
                    week,
                    GPS,
                    onstar,
                    siriusXM
            ));
        }
        cursor.close();
        Car[] result_car = new Car[result.size()];
        result.toArray(result_car);
        return result_car;
    }
    public String queryCapability(String carName) {
        ArrayList<Car> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String capacity="";
        Cursor cursor = db.query(
                TABLE_LIST.CAR,
                null,
                EnumTable.CAR.CARNAME + "=" + "\'" + carName+"\'",
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            capacity= cursor.getString(cursor.getColumnIndex(EnumTable.CAR.CAPACITY));
        }
        return capacity;
    }
//在pojo中为Rental，其它地方均为Reservations

    public Rental[] queryReservations(String userName, String start_time, String back_time) {
        ArrayList<Rental> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //使用三个参数对reservation进行筛选
        //返回的结果为开始晚于输入的开始时间，且结束时间早于输入的结束时间的reservation
        Cursor cursor = db.query(
                TABLE_LIST.Reservation,
                null,
                EnumTable.Reservation.USERNAME + "=" + "\'" + userName+"\'" + " AND "+ EnumTable.Reservation.START + ">=" + "\'" + start_time + "\'" + " AND " + EnumTable.Reservation.Back + "<=" + "\'" + back_time + "\'",
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            String ReservationNumber = cursor.getString((cursor.getColumnIndex(EnumTable.Reservation.RESERVATIONNUMBER)));
            String UserName = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.USERNAME));
            String CarName= cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.CARNAME));
            String start = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.START));
            String end = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.Back));
            String GPS = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.GPS));
            String onstar = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.ONSTAR));
            String siriusXM = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.SIRIUSXM));
            String totalPrice = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.TOTALPRICE));
            String status = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.STATUS));
            // TODO: not finished
            result.add(new Rental(
                    ReservationNumber,
                    UserName,
                    CarName,
                    start,
                    end,
                    GPS,
                    onstar,
                    siriusXM,
                    totalPrice,
                    status
            ));
        }
        cursor.close();
        Rental[] result_Reservation = new Rental[result.size()];
        Log.d("[Rental]", result.toString());
        Log.d("[Rental]", ""+result.size());
        result.toArray(result_Reservation);
        return result_Reservation;
    }


    public Rental[] queryallReservations() {
        ArrayList<Rental> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.Reservation,
                null,
                null,
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            String ReservationNumber = cursor.getString((cursor.getColumnIndex(EnumTable.Reservation.RESERVATIONNUMBER)));
            String UserName = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.USERNAME));
            String CarName= cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.CARNAME));
            String start = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.START));
            String end = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.Back));
            String GPS = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.GPS));
            String onstar = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.ONSTAR));
            String siriusXM = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.SIRIUSXM));
            String totalPrice = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.TOTALPRICE));
            String status = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.STATUS));
            // TODO: not finished
            result.add(new Rental(
                    ReservationNumber,
                    UserName,
                    CarName,
                    start,
                    end,
                    GPS,
                    onstar,
                    siriusXM,
                    totalPrice,
                    status
            ));
        }
        cursor.close();
        Rental[] result_Reservation = new Rental[result.size()];
        Log.d("[Rental]", result.toString());
        Log.d("[Rental]", ""+result.size());
        result.toArray(result_Reservation);
        return result_Reservation;
    }
    public Rental[] queryAllReservations(String start_time, String back_time) {
        ArrayList<Rental> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.Reservation,
                null,
                EnumTable.Reservation.START + ">=" + "\'" + start_time + "\'" + " AND " + EnumTable.Reservation.Back + "<=" + "\'" + back_time + "\'",
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            String ReservationNumber = cursor.getString((cursor.getColumnIndex(EnumTable.Reservation.RESERVATIONNUMBER)));
            String UserName = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.USERNAME));
            String CarName= cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.CARNAME));
            String start = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.START));
            String end = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.Back));
            String GPS = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.GPS));
            String onstar = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.ONSTAR));
            String siriusXM = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.SIRIUSXM));
            String totalPrice = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.TOTALPRICE));
            String status = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.STATUS));
            // TODO: not finished
            result.add(new Rental(
                    ReservationNumber,
                    UserName,
                    CarName,
                    start,
                    end,
                    GPS,
                    onstar,
                    siriusXM,
                    totalPrice,
                    status
            ));
        }
        cursor.close();
        Rental[] result_Reservation = new Rental[result.size()];
        Log.d("[Rental]", result.toString());
        Log.d("[Rental]", ""+result.size());
        result.toArray(result_Reservation);
        return result_Reservation;
    }
    public Rental[] queryActiveReservations() {
        ArrayList<Rental> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //返回的结果为开始晚于输入的开始时间，且结束时间早于输入的结束时间的reservation
        Cursor cursor = db.query(
                TABLE_LIST.Reservation,
                null,
                EnumTable.Reservation.STATUS + "=1",
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            String ReservationNumber = cursor.getString((cursor.getColumnIndex(EnumTable.Reservation.RESERVATIONNUMBER)));
            String UserName = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.USERNAME));
            String CarName= cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.CARNAME));
            String start = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.START));
            String end = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.Back));
            String GPS = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.GPS));
            String onstar = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.ONSTAR));
            String siriusXM = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.SIRIUSXM));
            String totalPrice = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.TOTALPRICE));
            String status = cursor.getString(cursor.getColumnIndex(EnumTable.Reservation.STATUS));
            // TODO: not finished
            result.add(new Rental(
                    ReservationNumber,
                    UserName,
                    CarName,
                    start,
                    end,
                    GPS,
                    onstar,
                    siriusXM,
                    totalPrice,
                    status
            ));
        }
        cursor.close();
        Rental[] result_Reservation = new Rental[result.size()];
        result.toArray(result_Reservation);
        return result_Reservation;
    }
    // 输入为一个rental实例，此函数可以更改数据库中的Reservation
    public String editReservation(Rental rental){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Reservation.RESERVATIONNUMBER, rental.getID());
        cv.put(EnumTable.Reservation.USERNAME, rental.getUsername());
        cv.put(EnumTable.Reservation.CARNAME, rental.getCarName());
        cv.put(EnumTable.Reservation.START, rental.getStart());
        cv.put(EnumTable.Reservation.Back, rental.getEnd());
        cv.put(EnumTable.Reservation.GPS, rental.getGPS());
        cv.put(EnumTable.Reservation.ONSTAR, rental.getOnstar());
        cv.put(EnumTable.Reservation.SIRIUSXM, rental.getSiriusxm());
        cv.put(EnumTable.Reservation.TOTALPRICE, rental.getTotalPrice());
        cv.put(EnumTable.Reservation.STATUS, rental.getStatus());

        long res = db.update(TABLE_LIST.Reservation, cv, "ReservationNumber=?", new String[]{rental.getID()});
        if(res == -1)
            return "failed";
        else
            return "User Update Successfully";

    }
    // 当用户取消此预约时，将预约的Status置为"0"
    public void removeReservation(Rental rental){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Reservation.RESERVATIONNUMBER, rental.getID());
        cv.put(EnumTable.Reservation.USERNAME, rental.getUsername());
        cv.put(EnumTable.Reservation.CARNAME, rental.getCarName());
        cv.put(EnumTable.Reservation.START, rental.getStart());
        cv.put(EnumTable.Reservation.Back, rental.getEnd());
        cv.put(EnumTable.Reservation.GPS, rental.getGPS());
        cv.put(EnumTable.Reservation.ONSTAR, rental.getOnstar());
        cv.put(EnumTable.Reservation.SIRIUSXM, rental.getSiriusxm());
        cv.put(EnumTable.Reservation.TOTALPRICE, rental.getTotalPrice());
        cv.put(EnumTable.Reservation.STATUS, "0");

        long res = db.update(TABLE_LIST.Reservation, cv, "ReservationNumber=?", new String[]{rental.getID()});
        if(res == -1) {
        }

    }
}
