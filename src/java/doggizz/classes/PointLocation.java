/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;


/**
 *
 * @author Stas
 */

public class PointLocation{
    private Long id;
    private int point_kind;
    private double latitude;
    private double longitude;
    
    
    public PointLocation() {
        super();
    }

    public PointLocation(Long id, int point_kind, double latitude,
        double longitude) 
    {
        super();
        this.id = id;
        this.point_kind = point_kind;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPoint_kind() {
        return point_kind;
    }

    public void setPoint_kind(int point_kind) {
        this.point_kind = point_kind;
    }
    
}
