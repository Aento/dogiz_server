/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.TaggedUser;
import doggizz.classes.User;
import doggizz.utils.Pool;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Stas
 */
public class TaggedUser_sql {
    
    public long UploadTaggedUser(TaggedUser tag)
    {
       long id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Add_TaggedUser (?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            cstmt.setLong(2, tag.getUser_id());
            cstmt.setLong(3, tag.getTagged_user_id());
            cstmt.execute();
            id = Long.valueOf(cstmt.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        return id;
    }
           
    public void RemoveTaggedUser(long id)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Remove_TaggedUser (?)}");
            cstmt.setLong(1, id);
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }   
    
    public ArrayList<TaggedUser> LoadAllTaggedUsers(long user_id)
   {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<TaggedUser> tagList = new ArrayList<TaggedUser>();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_TaggedUsers (?)}");
            cstmt.setLong(1, user_id);
            rs = cstmt.executeQuery();
            
            
            while(rs.next())
            {
                User u = new User();
                TaggedUser tag = new TaggedUser();
                tag.setId(rs.getLong(1));
                tag.setUser_id(rs.getLong(2));
                tag.setTagged_user_id(rs.getLong(3));
                
                u.setId(rs.getLong(4));
                u.setOwner_name(rs.getString(5));
                u.setEmail(rs.getString(6));
                u.setOwner_gender(rs.getInt(7));
                u.setOwner_pic_id(rs.getLong(8));
                u.setDog_pic_id(rs.getLong(9));
                u.setDog_name(rs.getString(10));
                u.setDog_breed(rs.getLong(11));
                u.setDog_birthdate(TimestampToCalendar(rs.getTimestamp(12)));
                u.setDog_gender(rs.getInt(13));
                u.setDw_status(rs.getInt(14));
                u.setPhone(rs.getInt(15));
                u.setPhone_second(rs.getInt(16));
                u.setDw_details(rs.getString(17));
                u.setDog_castrated(rs.getInt(18));
                u.setVet_id(rs.getLong(19));
                u.setFood_id(rs.getLong(20));
                u.setOwner_surname(rs.getString(21));
                u.setDw_active(rs.getInt(22));
                
                tagList.add(tag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return tagList;
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
        cal.set(Calendar.YEAR, c.YEAR);
        cal.set(Calendar.MONTH, c.MONTH);
        cal.set(Calendar.DATE, c.DATE);
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
        cal.set(Calendar.MINUTE, Integer.valueOf(minute));
        return cal;
    }
}
