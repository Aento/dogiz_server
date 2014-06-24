/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.User;
import doggizz.classes.UserPass;
import doggizz.utils.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class SendingRegistrationInfo {
    Long id = null;
    public long SendingRegistrationInfo(User u)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        try
        {           
            con = Pool.getConnection();       
            cstmt = con.prepareCall("{?=call sp_Reg_New_User (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
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
            
            if(u.getOwner_pic().getPicture().length()>1){
                cstmt.setString(20, u.getOwner_pic().getPicture());
            }
            else {
                cstmt.setString(20, null);
            }
            if(u.getDog_pic().getPicture().length()>1){
                cstmt.setString(21, u.getOwner_pic().getPicture());
            }
            else {
                cstmt.setString(21, null);
            }     
            cstmt.execute();
            id = Long.valueOf(cstmt.getInt(1));
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
    
    public long SendingRegistrationInfo(UserPass up)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Reg_New_User_Pass (?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            cstmt.setString(2, up.getEmail());
            cstmt.setString(3, up.getPassword());
            cstmt.setLong(4, up.getPersonID());
            cstmt.execute();
            //up.setId(Long.valueOf(cstmt.getInt(1)));
            cstmt.close();
            id = Long.valueOf(cstmt.getInt(1));
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
    
    public int CheckIfMailExist(User u)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        int count = 0;
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Check_If_Mail_Exist (?)}");
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2, u.getEmail());
            cstmt.execute();
            count = cstmt.getInt(1);
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
        return count;
    }
}
