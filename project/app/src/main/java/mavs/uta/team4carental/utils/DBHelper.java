package mavs.uta.team4carental.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

import androidx.annotation.Nullable;

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

    public static final String DB_NAME = "test1";
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

    public  String init_Car_tbl(SQLiteDatabase db){

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
        cv.put(EnumTable.Reservation.CARNAME,rental.getUsername());
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
        result.toArray(result_Reservation);
        return result_Reservation;
    }
}
