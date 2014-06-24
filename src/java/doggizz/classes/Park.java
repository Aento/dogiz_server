/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;


/**
 *
 * @author Stas
 */

public class Park {

        private Long id;
    private String name;
    private String address;
    private long park_pic_id;
    private boolean bench;
    private boolean facilities;
    private boolean fence;
    private String description;
    private int height;
    private int width;
    private int radius;
    private boolean shadow;
    private boolean spacket;
    private boolean water;
    private Long pointid;
    private String city;

    public Park() {
        super();
    }

    public Park(Long id, String name, String address, long park_pic_id, boolean bench, boolean facilities, boolean fence, String description, int height, int width, int radius, boolean shadow, boolean spacket, boolean water, Long pointid, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.park_pic_id = park_pic_id;
        this.bench = bench;
        this.facilities = facilities;
        this.fence = fence;
        this.description = description;
        this.height = height;
        this.width = width;
        this.radius = radius;
        this.shadow = shadow;
        this.spacket = spacket;
        this.water = water;
        this.pointid = pointid;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPark_pic_id() {
        return park_pic_id;
    }

    public void setPark_pic_id(long park_pic_id) {
        this.park_pic_id = park_pic_id;
    }

    public boolean isBench() {
        return bench;
    }

    public void setBench(boolean bench) {
        this.bench = bench;
    }

    public boolean isFacilities() {
        return facilities;
    }

    public void setFacilities(boolean facilities) {
        this.facilities = facilities;
    }

    public boolean isFence() {
        return fence;
    }

    public void setFence(boolean fence) {
        this.fence = fence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public boolean isSpacket() {
        return spacket;
    }

    public void setSpacket(boolean spacket) {
        this.spacket = spacket;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public Long getPointid() {
        return pointid;
    }

    public void setPointid(Long pointid) {
        this.pointid = pointid;
    }

    public String getCity() {
        return city;
    }
        
    public void setCity(String city) {
        this.city = city;
    }
}
