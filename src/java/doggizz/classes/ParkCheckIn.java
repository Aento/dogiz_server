/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

import java.io.Serializable;
import java.util.Calendar;


/**
 *
 * @author Stas
 */
public class ParkCheckIn implements Serializable {
    //private static final long serialVersionUID = 1L;
    private Long id;
    private Long personID;
    private Long parkID;
    private Calendar checked_time;
    //private boolean if_checked;
    //private User user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getChecked_time() {
        return checked_time;
    }

    public void setChecked_time(Calendar checked_time) {
        this.checked_time = checked_time;
    }

    public Long getParkID() {
        return parkID;
    }

    public void setParkID(Long parkID) {
        this.parkID = parkID;
    }

    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long personID) {
        this.personID = personID;
    }
  
}
