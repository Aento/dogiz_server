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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class CheckLogin {
    public UserPass CheckLogin(String email,String pass)
    {
        UserPass up = new UserPass();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Check_Login (?,?)}");
            cstmt.setString(1, email);
            cstmt.setString(2, pass);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                //ID, EMAIL, PASSWORD, PERSONID
                up.setId(rs.getLong(1));
                up.setPersonID(rs.getLong(2));
                up.setPassword(rs.getString(3));
                up.setEmail(rs.getString(4));
            }
            cstmt.close();
            con.close();
            if(up != null)
            {
                return up;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CheckLogin.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return null;
    }
}
