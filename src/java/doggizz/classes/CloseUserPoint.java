/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package doggizz.classes;

/**
 *
 * @author Michael
 * This Light Class will represent ClosePoint to current User
 * Close points may be: DogWalkers ,CheckIns, Officer.
 * ActivePoint Id will be used as Id for the element.
 * No Need for table to those points.
 * Additional attributes may be added.
 */
public class CloseUserPoint {
    private long id;
    private long user_id;
    private int action_id;
    private double latitude;
    private double longitude;
    private String owner_name;
    private String owner_surname; 
    
     public CloseUserPoint()
    {
        super();
    }
     
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    
    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
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
    
     public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_surname() {
        return owner_surname;
    }

    public void setOwner_surname(String owner_surname) {
        this.owner_surname = owner_surname;
    }
    
}

