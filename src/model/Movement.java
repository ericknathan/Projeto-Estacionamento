package model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import util.Util;

public class Movement {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    private String ID;
    private String licensePlate;
    private String model;
    private LocalDateTime entranceDate;
    private LocalDateTime exitDate;
    private String time;
    private double price;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEntranceDate() {
        return entranceDate.toString();
    }

    public void setEntranceDate(LocalDateTime entranceDate) {
        this.entranceDate = entranceDate;
    }

    public String getEntranceHour() {
        return Util.formatHour(entranceDate);
    }

    public String getExitDate() {
        return exitDate.toString();
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public String getExitHour() {
        return Util.formatHour(exitDate);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toStringEntrance() {
        String constructor = ID + ';' + licensePlate + ';' + model + ';' + entranceDate.toString().substring(0,19) + "+03:00"+ ";;;";
        return constructor.toUpperCase();
    }

    public String toStringExit() {
        String constructor = ID + ';' + licensePlate + ';' + model + ';' + entranceDate.toString().substring(0,19) + "+03:00"+ ";" + exitDate.toString() + "+03:00;" + time + ";" + price;
        return constructor.toUpperCase();
    }

}
