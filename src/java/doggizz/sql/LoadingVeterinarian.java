/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Veterinarian;
import doggizz.utils.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class LoadingVeterinarian {
    public ArrayList<Veterinarian> LoadingVeterinarian() throws SQLException
    {
                Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<Veterinarian> vetList = new ArrayList<Veterinarian>();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_Veterinarian}");
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                //ID, ADDITIONAL_INFO, ADDRESS, INTERNET_ADDRESS, NAME, OPENING_HOURS, PHONE, POINTID
                Veterinarian vet = new Veterinarian();
                vet.setId(rs.getLong(1));
                vet.setAdditional_info(rs.getString(2));
                vet.setAddress(rs.getString(3));
                vet.setInternet_address(rs.getString(4));
                vet.setName(rs.getString(5));
                vet.setOpening_hours(rs.getString(6));
                vet.setPhone(rs.getString(7));
                vet.setPointid(rs.getLong(8));
                vetList.add(vet);
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
        return vetList;
    }
}
