/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

/**
 *
 * @author Stas
 */
public class ParkCheckinTotal {
    private long park_id;
    private int users_female;
    private int users_male;
    private int dogs_female;
    private int dogs_male;

    public ParkCheckinTotal(){
        super();
    }

    public ParkCheckinTotal(long park_id, int users_female, int users_male, int dogs_female, int dogs_male) {
        this.park_id = park_id;
        this.users_female = users_female;
        this.users_male = users_male;
        this.dogs_female = dogs_female;
        this.dogs_male = dogs_male;
    }

    public long getPark_id() {
        return park_id;
    }

    public void setPark_id(long park_id) {
        this.park_id = park_id;
    }

    public int getUsers_female() {
        return users_female;
    }

    public void setUsers_female(int users_female) {
        this.users_female = users_female;
    }

    public int getUsers_male() {
        return users_male;
    }

    public void setUsers_male(int users_male) {
        this.users_male = users_male;
    }

    public int getDogs_female() {
        return dogs_female;
    }

    public void setDogs_female(int dogs_female) {
        this.dogs_female = dogs_female;
    }

    public int getDogs_male() {
        return dogs_male;
    }

    public void setDogs_male(int dogs_male) {
        this.dogs_male = dogs_male;
    }
}

