/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

import java.util.Calendar;
import javax.persistence.*;

/**
 *
 * @author Stas
 */
public class User{
    private Long id;
    private String owner_name;
    private String owner_surname;
    private String email;
    private int phone;
    private int phone_second;
    private long dog_breed;
    private String dog_name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dog_birthdate;
    private int owner_gender; //1-male 2 -female
    private int dog_castrated; //1-yes 2-no
    private long vet_id;
    private long owner_pic_id;
    private long dog_pic_id;
    private int dog_gender;
    private int dw_status;
    private String dw_details;
    private int dw_active;
    private long food_id;
    private Picture dog_pic;
    private Picture owner_pic;

    public User() {
        super();
    }

    public User(long id, String owner_name, String owner_surname, String email, int phone, int phone_second, long dog_breed, String dog_name, Calendar dog_birthdate, int owner_gender, int dog_castrated, long vet_id, long owner_pic_id, long dog_pic_id, int dog_gender, int dw_status, String dw_details, int dw_active, long food_id) {
        this.id = id;
        this.owner_name = owner_name;
        this.owner_surname = owner_surname;
        this.email = email;
        this.phone = phone;
        this.phone_second = phone_second;
        this.dog_breed = dog_breed;
        this.dog_name = dog_name;
        this.dog_birthdate = dog_birthdate;
        this.owner_gender = owner_gender;
        this.dog_castrated = dog_castrated;
        this.vet_id = vet_id;
        this.owner_pic_id = owner_pic_id;
        this.dog_pic_id = dog_pic_id;
        this.dog_gender = dog_gender;
        this.dw_status = dw_status;
        this.dw_details = dw_details;
        this.dw_active = dw_active;
        this.food_id = food_id;
    }

    public User(long id, String owner_name, String owner_surname, String email, int phone, int phone_second, long dog_breed, String dog_name, Calendar dog_birthdate, int owner_gender, int dog_castrated, long vet_id, long owner_pic_id, long dog_pic_id, int dog_gender, int dw_status, String dw_details, int dw_active, long food_id, Picture dog_pic, Picture owner_pic) {
        this.id = id;
        this.owner_name = owner_name;
        this.owner_surname = owner_surname;
        this.email = email;
        this.phone = phone;
        this.phone_second = phone_second;
        this.dog_breed = dog_breed;
        this.dog_name = dog_name;
        this.dog_birthdate = dog_birthdate;
        this.owner_gender = owner_gender;
        this.dog_castrated = dog_castrated;
        this.vet_id = vet_id;
        this.owner_pic_id = owner_pic_id;
        this.dog_pic_id = dog_pic_id;
        this.dog_gender = dog_gender;
        this.dw_status = dw_status;
        this.dw_details = dw_details;
        this.dw_active = dw_active;
        this.food_id = food_id;
        this.dog_pic = dog_pic;
        this.owner_pic = owner_pic;
    }
        
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone_second() {
        return phone_second;
    }

    public void setPhone_second(int phone_second) {
        this.phone_second = phone_second;
    }

    public long getDog_breed() {
        return dog_breed;
    }

    public void setDog_breed(long dog_breed) {
        this.dog_breed = dog_breed;
    }

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public Calendar getDog_birthdate() {
        return dog_birthdate;
    }

    public void setDog_birthdate(Calendar dog_birthdate) {
        this.dog_birthdate = dog_birthdate;
    }

    public int getOwner_gender() {
        return owner_gender;
    }

    public void setOwner_gender(int owner_gender) {
        this.owner_gender = owner_gender;
    }

    public int getDog_castrated() {
        return dog_castrated;
    }

    public void setDog_castrated(int dog_castrated) {
        this.dog_castrated = dog_castrated;
    }

    public long getVet_id() {
        return vet_id;
    }

    public void setVet_id(long vet_id) {
        this.vet_id = vet_id;
    }

    public long getOwner_pic_id() {
        return owner_pic_id;
    }

    public void setOwner_pic_id(long owner_pic_id) {
        this.owner_pic_id = owner_pic_id;
    }

    public long getDog_pic_id() {
        return dog_pic_id;
    }

    public void setDog_pic_id(long dog_pic_id) {
        this.dog_pic_id = dog_pic_id;
    }

    public int getDog_gender() {
        return dog_gender;
    }

    public void setDog_gender(int dog_gender) {
        this.dog_gender = dog_gender;
    }

    public int getDw_status() {
        return dw_status;
    }

    public void setDw_status(int dw_status) {
        this.dw_status = dw_status;
    }

    public String getDw_details() {
        return dw_details;
    }

    public void setDw_details(String dw_details) {
        this.dw_details = dw_details;
    }

    public long getFood_id() {
        return food_id;
    }

    public void setFood_id(long food_id) {
        this.food_id = food_id;
    }

    public int getDw_active() {
        return dw_active;
    }

    public void setDw_active(int dw_active) {
        this.dw_active = dw_active;
    }
    
    public Picture getDog_pic() {
        return dog_pic;
    }

    public void setDog_pic(Picture dog_pic) {
        this.dog_pic = dog_pic;
    }

    public Picture getOwner_pic() {
        return owner_pic;
    }

    public void setOwner_pic(Picture owner_pic) {
        this.owner_pic = owner_pic;
    }

}
