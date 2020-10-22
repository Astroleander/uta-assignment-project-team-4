package mavs.uta.team4carental.pojo;

import mavs.uta.team4carental.utils.EnumTable;

public class Car {
    private String carname;
    private String capicity;
    private String weekday;
    private String weekend;
    private String weel;
    private String gps;
    private String onstar;
    private String siriusxm;

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCapicity() {
        return capicity;
    }

    public void setCapicity(String capicity) {
        this.capicity = capicity;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getWeel() {
        return weel;
    }

    public void setWeel(String weel) {
        this.weel = weel;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getOnstar() {
        return onstar;
    }

    public void setOnstar(String onstar) {
        this.onstar = onstar;
    }

    public String getSiriusxm() {
        return siriusxm;
    }

    public void setSiriusxm(String siriusxm) {
        this.siriusxm = siriusxm;
    }

    @Override
    public String toString() {
        return "[Car]" +
                "\ncarname=" + carname +
                "\ncapicity=" + capicity +
                "\nweekday=" + weekday +
                "\nweekend=" + weekend +
                "\nweel=" + weel  +
                "\ngps=" + gps +
                "\nonstar=" + onstar +
                "\nsiriusxm=" + siriusxm +
                "\n";
    }

    public Car(String carname, String capicity, String weekday, String weekend, String weel, String gps, String onstar, String siriusxm) {
        this.carname = carname;
        this.capicity = capicity;
        this.weekday = weekday;
        this.weekend = weekend;
        this.weel = weel;
        this.gps = gps;
        this.onstar = onstar;
        this.siriusxm = siriusxm;
    }
}
