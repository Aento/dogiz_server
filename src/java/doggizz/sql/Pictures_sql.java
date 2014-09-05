/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Picture;
import doggizz.utils.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class Pictures_sql {
    public long UploadPicture(String picture,long current_id)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        long id = 0;
        try
        {
            if(current_id == 0) {
                con = Pool.getConnection();
                cstmt = con.prepareCall("{?=call sp_Add_Picture (?)}");
                cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
                cstmt.setString(2, picture);
                cstmt.execute();
                id = Long.valueOf(cstmt.getInt(1));
            }
            else {
                con = Pool.getConnection();
                cstmt = con.prepareCall("{?=call sp_Add_Picture (?,?)}");
                cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
                cstmt.setString(2, picture);
                cstmt.setLong(3, current_id);
                cstmt.execute();
                id = Long.valueOf(cstmt.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return id;
    }
    
    public Picture LoadPicture(long id)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Picture pic = new Picture();
        System.out.println("Load picture id = "+id);
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_Picture (?)}");
            cstmt.setLong(1, id);
            rs = cstmt.executeQuery();
            
            while(rs.next())
            {
                pic.setId(rs.getLong(1));
                pic.setPicture(rs.getString(2));
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return pic;
    }
    
    public Picture LoadPictureOfUser(long id,String picture_of)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Picture pic = new Picture();
        try{
            con = Pool.getConnection();

            if(picture_of==null || picture_of.equals("user"))

                cstmt = con.prepareCall("{call sp_Load_Picture_Of_User (?)}");
            else
                  cstmt = con.prepareCall("{call sp_Load_Picture_Of_Dog (?)}");  
            cstmt.setLong(1, id);
            rs = cstmt.executeQuery();
            
            while(rs.next())
            {
                pic.setId(rs.getLong(1));
                pic.setPicture(rs.getString(2));
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return pic;
    }
    
}
