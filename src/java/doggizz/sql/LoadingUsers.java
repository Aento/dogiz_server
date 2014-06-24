/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Picture;
import doggizz.classes.User;
import doggizz.classes.UserPass;
import doggizz.utils.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class LoadingUsers {
    Long id = null;
    public User LoadingUsers(Long id) 
    {
        User u = new User();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
       
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_User (?)}");
            cstmt.setLong(1, id);
            rs = cstmt.executeQuery();

            while(rs.next())
            {   
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
            }
            cstmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return u;
    }
    
    public static Calendar DateToCalendar(Date date)
    { 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public void UpdateUserInfo(User u){
        Connection con = null;
        CallableStatement cstmt = null;
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Update_User (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setLong(1, u.getId());
            cstmt.setString(2, u.getOwner_name());
            cstmt.setString(3, u.getEmail());
            cstmt.setInt(4, u.getOwner_gender());
            cstmt.setLong(5, u.getOwner_pic_id());
            cstmt.setLong(6, u.getDog_pic_id());
            cstmt.setString(7, u.getDog_name());
            cstmt.setLong(8, u.getDog_breed());
            if(u.getDog_birthdate()!=null)
                cstmt.setDate(9, new java.sql.Date(u.getDog_birthdate().getTimeInMillis()));
            else
                cstmt.setDate(9, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            cstmt.setInt(10, u.getDog_gender());
            cstmt.setInt(11, u.getDw_status());
            cstmt.setInt(12, u.getPhone());
            cstmt.setInt(13, u.getPhone_second());
            cstmt.setString(14, u.getDw_details());
            cstmt.setInt(15, u.getDog_castrated());
            cstmt.setLong(16, u.getVet_id());
            cstmt.setLong(17, u.getFood_id());
            cstmt.setString(18, u.getOwner_surname());
            cstmt.setInt(19, u.getDw_active());
            if(u.getOwner_pic().getPicture().length()>1)
                cstmt.setString(20, u.getOwner_pic().getPicture());
            else
                cstmt.setString(20, null);
            
            if(u.getDog_pic().getPicture().length()>1)
                cstmt.setString(20, u.getDog_pic().getPicture());
            else
                cstmt.setString(20, null);
            
            cstmt.execute();
            //up.setId(Long.valueOf(cstmt.getInt(1)));
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
    
    public long UpdateUserPass(UserPass up)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Update_User_Pass (?,?,?,?)}");
            cstmt.setLong(1, up.getId());
            cstmt.setString(2, up.getEmail());
            cstmt.setString(3, up.getPassword());
            cstmt.setLong(4, up.getPersonID());
            cstmt.execute();
            //up.setId(Long.valueOf(cstmt.getInt(1)));
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
        return id;
    }
}
