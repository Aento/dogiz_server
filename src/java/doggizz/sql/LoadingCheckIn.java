/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.ParkCheckIn;
import doggizz.classes.ParkCheckinTotal;
import doggizz.classes.Picture;
import doggizz.classes.User;
import doggizz.utils.Pool;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class LoadingCheckIn {
    public void SendingCheckOut(Long personId)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Set_Checkout (?)}");
            cstmt.setLong(1, personId);
            try
            {
                cstmt.execute();
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            cstmt.close();
            con.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public void SendingCheckIn(ParkCheckIn pcheck,Float latitude,Float longtitude)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        Calendar c = Calendar.getInstance();
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Set_Checkin (?,?,?,?,?)}");
            java.sql.Timestamp timestamp = new java.sql.Timestamp(c.getTimeInMillis());
            cstmt.setTimestamp(1,timestamp);
            cstmt.setLong(2, pcheck.getParkID());
            cstmt.setLong(3, pcheck.getPersonID());
            cstmt.setFloat(4,latitude);
            cstmt.setFloat(5,longtitude);
            System.out.println("parkID = " + pcheck.getParkID() + "personID = " + pcheck.getPersonID()+ "latitude = "+ latitude + "longitude = " + longtitude);
            try
            {
                cstmt.execute();
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            cstmt.close();
            con.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public ArrayList<ParkCheckIn> LoadingAllCheckIn(Long parkId)
    {
        ArrayList<ParkCheckIn> checkinList = new ArrayList<ParkCheckIn>();
        User u = new User();
        try
        {
            Connection con = Pool.getConnection();
            CallableStatement cstmt = con.prepareCall("{call sp_Checkin_get_all (?)}");
            cstmt.setLong(1, parkId);
            ResultSet rs = cstmt.executeQuery();
            Calendar myCal = new GregorianCalendar();
            while(rs.next())
            {
               /* ParkCheckIn pch = new ParkCheckIn();
                pch.setId(rs.getLong(1));
                myCal.setTime(rs.getDate(2));
                pch.setChecked_time(myCal);
                pch.setIf_checked(rs.getBoolean(3));
                pch.setParkID(rs.getLong(4));
                pch.setPersonID(rs.getLong(5));
                u.setId(rs.getLong(6));
                u.setAddress(rs.getString(7));
                u.setBreedID(rs.getLong(8));
                u.setCity(rs.getString(9));
                u.setDog_birthdate(DateToCalendar(rs.getDate(10)));
                u.setDog_name(rs.getString(11));
                u.setDog_sex(rs.getInt(12));
                u.setDog_size(rs.getInt(13));
                u.setEmail(rs.getString(14));
                u.setFirst_name(rs.getString(15));
                u.setLast_name(rs.getString(16));
                u.setMesuras(rs.getInt(17));
                u.setPhone(rs.getInt(18));
                u.setVeterinarian_id(rs.getLong(19));
                pch.setUser(u);
                checkinList.add(pch);*/
            }
            
            cstmt.close();
            con.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return checkinList;
    }
    
    public ParkCheckIn LoadingCheckIn(Long personId)
    {
        ParkCheckIn checkin = new ParkCheckIn();
        try
        {
            Connection con = Pool.getConnection();
            CallableStatement cstmt = con.prepareCall("{call sp_Checkin_get (?)}");
            cstmt.setLong(1, personId);
            ResultSet rs = cstmt.executeQuery();
            Calendar myCal = new GregorianCalendar();
            while(rs.next())
            {
                checkin.setId(rs.getLong(1));
                myCal.setTime(rs.getDate(2));
                checkin.setChecked_time(myCal);
                //checkin.setIf_checked(rs.getBoolean(3));
                checkin.setParkID(rs.getLong(4));
                checkin.setPersonID(rs.getLong(5));
            }
            
            cstmt.close();
            con.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return checkin;
    }
    
    
    public static Calendar DateToCalendar(Date date)
    { 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public ParkCheckinTotal CheckInGenderCount(long park_id)
    {
        ParkCheckinTotal pTotal = new ParkCheckinTotal();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Checkin_Gender_Count (?)}");
            cstmt.setLong(1, park_id);
            rs = cstmt.executeQuery();
            User u;
            while(rs.next())
            {
                pTotal.setPark_id(rs.getLong(1));
                pTotal.setUsers_male(rs.getInt(2));
                pTotal.setUsers_female(rs.getInt(3));
                pTotal.setDogs_male(rs.getInt(4));
                pTotal.setDogs_female(rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return pTotal;
    }
    
    public ArrayList<User> CheckInDogsPictures(long park_id)
    {
        ArrayList<User> user_list = new ArrayList<User>();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_CheckIn_Dogs_Pictures (?)}");
            cstmt.setLong(1, park_id);
            rs = cstmt.executeQuery();
            User u;
            Picture p;
            while(rs.next())
            {
                u = new User();
                //p = new Picture();
                Picture op = new Picture();
                Picture dp = new Picture();   
                u.setId(rs.getLong(1));
                u.setOwner_name(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setOwner_gender(rs.getInt(4));
                op.setId(rs.getLong(5));
                u.setOwner_pic(op);
                u.setOwner_pic_id(op.getId());
                dp.setId(rs.getLong(6));
                dp.setPicture(rs.getString(20));
                u.setDog_pic(dp);
                u.setDog_pic_id(dp.getId());
                u.setDog_name(rs.getString(7));
                u.setDog_breed(rs.getInt(8));
                u.setDog_birthdate(TimestampToCalendar(rs.getTimestamp(9)));
                u.setDog_gender(rs.getInt(10));
                u.setDw_status(rs.getInt(11));
                u.setPhone(rs.getInt(12));
                u.setPhone_second(rs.getInt(13));
                u.setDw_details(rs.getString(14));
                u.setDog_castrated(rs.getInt(15));
                u.setVet_id(rs.getLong(16));
                u.setFood_id(rs.getLong(17));
                u.setOwner_surname(rs.getString(18));
                u.setDw_active(rs.getInt(19));
                user_list.add(u);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return user_list;
    }
    
    
    public ArrayList<User> CheckInUsers(long park_id)
    {
        ArrayList<User> user_list = new ArrayList<User>();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_CheckIn_Users (?)}");
            cstmt.setLong(1, park_id);
            rs = cstmt.executeQuery();
            User u;
            Picture p;
            while(rs.next())
            {
                u = new User();
                //p = new Picture();  
                Picture op = new Picture();
                Picture dp = new Picture();
                u.setId(rs.getLong(1));
                u.setOwner_name(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setOwner_gender(rs.getInt(4));
                op.setId(rs.getLong(5));
                u.setOwner_pic(op);
                u.setOwner_pic_id(op.getId());
                dp.setId(rs.getLong(6));
                u.setDog_pic(dp);
                u.setDog_pic_id(dp.getId());
                u.setDog_name(rs.getString(7));
                u.setDog_breed(rs.getInt(8));
                u.setDog_birthdate(DateToCalendar(rs.getDate(9)));
                u.setDog_gender(rs.getInt(10));
                u.setDw_status(rs.getInt(11));
                u.setPhone(rs.getInt(12));
                u.setPhone_second(rs.getInt(13));
                u.setDw_details(rs.getString(14));
                u.setDog_castrated(rs.getInt(15));
                u.setVet_id(rs.getLong(16));
                u.setFood_id(rs.getLong(17));
                u.setOwner_surname(rs.getString(18));
                u.setDw_active(rs.getInt(19));
                user_list.add(u);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return user_list;
    }
    
    public static Calendar TimestampToCalendar(Timestamp date)
    { 
        DateFormat hours = new SimpleDateFormat( "HH" );
        String hour = hours.format(date);
        DateFormat minutes = new SimpleDateFormat( "mm" );
        String minute = minutes.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, c.get(Calendar.YEAR)); 
        cal.set(Calendar.MONTH, c.get(Calendar.MONTH));
        cal.set(Calendar.DATE, c.get(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
        cal.set(Calendar.MINUTE, Integer.valueOf(minute));
        return cal;
    }
}
