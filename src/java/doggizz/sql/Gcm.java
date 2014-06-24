/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Gcm_id;
import doggizz.classes.Veterinarian;
import doggizz.utils.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Stas
 */
public class Gcm {
    
        //IPHONE - 1
        //ANDROID - 2
        public void Registrate_Gcm(String gcm_id,Long user_id,Long os_code) throws SQLException
        {
            Connection con = null;
            CallableStatement cstmt = null;
            try{
                con = Pool.getConnection();
                cstmt = con.prepareCall("{call sp_Reg_New_Gcm_User (?,?,?)}");
                cstmt.setString(1, gcm_id);
                cstmt.setLong(2, user_id);
                cstmt.setLong(3, os_code);
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
            }catch(SQLException e){
                System.out.println(e);
            }finally {
                try { cstmt.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
            }
            
        }
        
        public Gcm_id Load_Gcm(Long user_id) throws SQLException
        {
            Connection con = null;
            CallableStatement cstmt = null;
            ResultSet rs = null;
            String gcm_id = "";
            Gcm_id gcm= new Gcm_id();
            try{
                con = Pool.getConnection();
                cstmt = con.prepareCall("{call sp_Load_Gcm_id (?)}");
                cstmt.setLong(1, user_id);


                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    gcm.setGcm_id(rs.getString(1));
                    gcm.setOs_code(rs.getInt(2));
                }

                cstmt.close();
                con.close();
            }
            catch(SQLException e){
                System.out.println(e);
            }finally {
                try { cstmt.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
                try { rs.close(); } catch (Exception e) { /* ignored */ }
            }
            return gcm;
        }
        
}
