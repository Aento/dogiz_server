/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

/**
 *
 * @author Stas
 */
public class FavoriteParks {
    long id;
    long user_id;
    long park_id;
    
    public FavoriteParks(){
        super();
    }

    public FavoriteParks(long id, long user_id, long park_id) {
        this.park_id = park_id;
        this.id = id;
        this.user_id = user_id;
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

    public long getPark_id() {
        return park_id;
    }

    public void setPark_id(long park_id) {
        this.park_id = park_id;
    }
}
