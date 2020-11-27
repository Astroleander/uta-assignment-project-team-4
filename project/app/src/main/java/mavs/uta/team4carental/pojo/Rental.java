package mavs.uta.team4carental.pojo;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.SSLEngineResult;

public class Rental implements Serializable{
    private String ReservationNumber;//这些变量名即为数据库中存的字段名
    private String UserName;
    private String CarName;
    private String start;
    private String back;
    private String GPS;
    private String onStar;
    private String siriusXM;
    private String totalPrice;
    private String status;
    private String Duration;

    public Rental(String ID, String UserName, String CarName, String start, String back, String GPS, String onStar, String siriusXM, String totalPrice, String status) {
        this.ReservationNumber = ID;
        this.UserName = UserName;
        this.CarName = CarName;
        this.start = start;
        this.back = back;
        this.GPS = GPS;
        this.onStar = onStar;
        this.siriusXM = siriusXM;
        this.totalPrice = totalPrice;
        this.status = status;
        // 计算并获取duration
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date startdate = null;
        Date backdate = null;
        try {
            startdate = format.parse(start);
            backdate = format.parse(back);
        }catch (ParseException e){
            e.printStackTrace();
        }

        float durations = (backdate.getTime() - startdate.getTime())/(1000*24*60*60);
        if(((backdate.getTime() - startdate.getTime())%(1000*24*60*60))==0){

        }else{
            durations+=1;
        }
        String dur = String.valueOf(durations);
        this.Duration = dur;
    }
    public String getCarName() {
        return CarName;
    }
    public String getID(){
        return ReservationNumber;
    }
    public void setID(String id){
        this.ReservationNumber = id;
    }
    public String getUsername() {
        return UserName;
    }

    public void setUsername(String username) {
        this.UserName = username;
    }



    public void setCarName(String carname) {
        this.CarName = carname;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return back;
    }

    public void setEnd(String back) {
        this.back = back;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

    public String getOnstar() {
        return onStar;
    }

    public void setOnstar(String onstar) {
        this.onStar = onstar;
    }

    public String getDuration() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date startdate = null;
        Date backdate = null;
        try {
            startdate = format.parse(start);
            backdate = format.parse(back);
        }catch (ParseException e){
            e.printStackTrace();
        }

        float durations = (backdate.getTime() - startdate.getTime())/(1000*24*60*60);
        if(((backdate.getTime() - startdate.getTime())%(1000*24*60*60))==0){

        }else{
            durations+=1;
        }
        String dur = String.valueOf(durations);
        Duration = dur;
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getSiriusxm() {
        return siriusXM;
    }

    public void setSiriusxm(String siriusxm) {
        this.siriusXM = siriusxm;
    }
    public String getTotalPrice(){
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice){
        this.totalPrice = totalPrice;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }

    @Override
    public String toString() {
        return "[Reservation]" +
                "\nReservationNumber=" + ReservationNumber +
                "\nUserName=" + UserName +
                "\nCarName=" + CarName +
                "\nstart=" + start +
                "\nend=" + back  +
                "\nGPS=" + GPS +
                "\nonStar=" + onStar +
                "\nsiriusXM=" + siriusXM +
                "\ntotalPrice=" + totalPrice +
                "\nstatus=" + status +
                "\n";
    }
}