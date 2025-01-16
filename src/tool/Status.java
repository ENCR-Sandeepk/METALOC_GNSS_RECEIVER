/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author sandeepk
 */
public class Status {

    SimpleStringProperty date_time = null;
    SimpleStringProperty rover = null;
    SimpleStringProperty base = null;
    SimpleStringProperty gnss_time = null;
    SimpleStringProperty rms = null;
    SimpleStringProperty lat = null;
    SimpleStringProperty lon = null;
    SimpleStringProperty east = null;
    SimpleStringProperty north = null;
    SimpleStringProperty alti = null;
    SimpleStringProperty ratio = null;
    SimpleStringProperty un = null;
    SimpleStringProperty gps = null;
    SimpleStringProperty beidou = null;
    SimpleStringProperty glonass = null;
    SimpleStringProperty galileo = null;

    public Status(String date_time,
            String rover,
            String base,
            String gnss_time,
            String rms,
            String lat,
            String lon,
            String east,
            String north,
            String alti,
            String ratio,
            String un,
            String gps,
            String beidou,
            String glonass,
            String galileo) {

        this.date_time = new SimpleStringProperty(date_time);
        this.rover = new SimpleStringProperty(rover);
        this.base = new SimpleStringProperty(base);
        this.gnss_time = new SimpleStringProperty(gnss_time);
        this.rms = new SimpleStringProperty(rms);
        this.lat = new SimpleStringProperty(lat);
        this.lon = new SimpleStringProperty(lon);
        this.east = new SimpleStringProperty(east);
        this.north = new SimpleStringProperty(north);
        this.alti = new SimpleStringProperty(alti);
        this.ratio = new SimpleStringProperty(ratio);
        this.un = new SimpleStringProperty(un);
        this.gps = new SimpleStringProperty(gps);
        this.beidou = new SimpleStringProperty(beidou);
        this.glonass = new SimpleStringProperty(glonass);
        this.galileo = new SimpleStringProperty(galileo);

    }

    public String getDate_time() {
        return date_time.get();
    }

    public void setDate_time(String date_time) {
        this.date_time = new SimpleStringProperty(date_time);
    }

    public String getRover() {
        return rover.get();
    }

    public void setRover(String rover) {
        this.rover = new SimpleStringProperty(rover);
    }

    public String getBase() {
        return base.get();
    }

    public void setBase(String base) {
        this.base = new SimpleStringProperty(base);
    }

    public String getGnss_time() {
        return gnss_time.get();
    }

    public void setGnss_time(String gnss_time) {
        this.gnss_time = new SimpleStringProperty(gnss_time);
    }

    public String getRms() {
        return rms.get();
    }

    public void setRms(String rms) {
        this.rms = new SimpleStringProperty(rms);
    }

    public String getLat() {
        return lat.get();
    }

    public void setLat(String lat) {
        this.lat = new SimpleStringProperty(lat);
    }

    public String getLon() {
        return lon.get();
    }

    public void setLon(String lon) {
        this.lon = new SimpleStringProperty(lon);
    }

    public String getEast() {
        return east.get();
    }

    public void setEast(String east) {
        this.east = new SimpleStringProperty(east);
    }

    public String getNorth() {
        return north.get();
    }

    public void setNorth(String north) {
        this.north = new SimpleStringProperty(north);
    }

    public String getAlti() {
        return alti.get();
    }

    public void setAlti(String alti) {
        this.alti = new SimpleStringProperty(alti);
    }

    public String getRatio() {
        return ratio.get();
    }

    public void setRatio(String ratio) {
        this.ratio = new SimpleStringProperty(ratio);
    }

    public String getUn() {
        return un.get();
    }

    public void setUn(String un) {
        this.un = new SimpleStringProperty(un);
    }

    public String getGps() {
        return gps.get();
    }

    public void setGps(String gps) {
        this.gps = new SimpleStringProperty(gps);
    }

    public String getBeidou() {
        return beidou.get();
    }

    public void setBeidou(String beidou) {
        this.beidou = new SimpleStringProperty(beidou);
    }

    public String getGlonass() {
        return glonass.get();
    }

    public void setGlonass(String glonass) {
        this.glonass = new SimpleStringProperty(glonass);
    }

    public String getGalileo() {
        return galileo.get();
    }

    public void setGalileo(String galileo) {
        this.galileo = new SimpleStringProperty(galileo);
    }
}
