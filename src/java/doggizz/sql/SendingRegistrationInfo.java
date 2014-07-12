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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class SendingRegistrationInfo {
    Long id = null;
    public User SendingRegistrationInfo(User u)
    {
        
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try
        {           
            con = Pool.getConnection();       
            cstmt = con.prepareCall("{call sp_Reg_New_User (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, u.getOwner_name());
            cstmt.setString(2, u.getEmail());
            cstmt.setInt(3, u.getOwner_gender());
            cstmt.setLong(4, u.getOwner_pic_id());
            cstmt.setLong(5, u.getDog_pic_id());
            cstmt.setString(6, u.getDog_name());
            cstmt.setLong(7, u.getDog_breed());
            if(u.getDog_birthdate()!=null)
                cstmt.setDate(8, new java.sql.Date(u.getDog_birthdate().getTimeInMillis()));
            else
                cstmt.setDate(8, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            cstmt.setInt(9, u.getDog_gender());
            cstmt.setInt(10, u.getDw_status());
            cstmt.setInt(11, u.getPhone());
            cstmt.setInt(12, u.getPhone_second());
            cstmt.setString(13, u.getDw_details());
            cstmt.setInt(14, u.getDog_castrated());
            cstmt.setLong(15, u.getVet_id());
            cstmt.setLong(16, u.getFood_id());
            cstmt.setString(17, u.getOwner_surname());
            cstmt.setInt(18, u.getDw_active());
            
            if(u.getOwner_pic().getPicture().length()>1){
                cstmt.setString(19, u.getOwner_pic().getPicture());
            }
            else {
                cstmt.setString(19, null);
            }
            if(u.getDog_pic().getPicture().length()>1){
                cstmt.setString(20, u.getOwner_pic().getPicture());
            }
            else {
                cstmt.setString(20, null);
            }     
            try
            {
                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    Picture op = new Picture();
                    Picture dp = new Picture();
                    u.setId(rs.getLong(1));
                    op.setId(rs.getLong(2));
                    u.setOwner_pic(op);
                    u.setOwner_pic_id(op.getId());
                    dp.setId(rs.getLong(3));
                    u.setDog_pic(dp);
                    u.setDog_pic_id(dp.getId());
                } 
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            //id = Long.valueOf(cstmt.getInt(1));
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
