package mavs.uta.team4carental.utils;

public class EnumTable {
    public static final int UserLength = 13;
    public static final class User{
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String UTAID = "uta_id";
        public static final String LASTNAME = "lastname";
        public static final String FIRSTNAME = "firstname";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String ADDRESS = "adddress";
        public static final String CITY = "city";
        public static final String STATE = "state";
        public static final String ZIPCODE = "zipcode";
        public static final String MEMBER = "member";
        public static final String STATUS = "status";
    }
    public static final class CAR{
        public static final String CARNAME = "name";
        public static final String CAPACITY = "capacity";
        public static final String WEEKDAY = "weekday";
        public static final String WEEKEND = "weekend";
        public static final String WEEK = "week";
        public static final String GPS = "GPS";
        public static final String ONSTAR = "onstar";
        public static final String SIRIUSXM = "siriusXM";
    }
    public static final class Reservation{
        public static final String RESERVATIONNUMBER = "ReservationNumber";
        public static final String USERNAME = "UserName";
        public static final String CARNAME = "CarName";
        public static final String START = "start";
        public static final String Back = "back";
        public static final String GPS = "GPS";
        public static final String ONSTAR = "onStar";
        public static final String SIRIUSXM = "siriusXM";
        public static final String TOTALPRICE = "totalPrice";
        public static final String STATUS = "status";

    }
}
