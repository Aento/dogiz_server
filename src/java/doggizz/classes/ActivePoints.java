/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

import java.util.Calendar;

/**
 *
 * @author Stas
 */
public class ActivePoints {
    private long id;
    private Calendar send_time;
    private long user_id;
    private double latitude;
    private double longitude;
    private int action_id;
    private long park_id;
    private String free_text;

    public ActivePoints()
    {
        super();
    }

    public ActivePoints(long id, Calendar send_time, long user_id, double latitude, double longitude, int action_id, long park_id, String free_text) {
        this.id = id;
        this.send_time = send_time;
        this.user_id = user_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.action_id = action_id;
        this.park_id = park_id;
        this.free_text = free_text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getSend_time() {
        return send_time;
    }

    public void setSend_time(Calendar send_time) {
        this.send_time = send_time;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public long getPark_id() {
        return park_id;
    }

    public void setPark_id(long park_id) {
        this.park_id = park_id;
    }

    public String getFree_text() {
        return free_text;
    }

    public void setFree_text(String free_text) {
        this.free_text = free_text;
    }
}
