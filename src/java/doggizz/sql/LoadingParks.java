/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Park;
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
public class LoadingParks {
    public ArrayList<Park> LoadingAllParks() throws SQLException
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<Park> parkList = new ArrayList<Park>();
        
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_Parks}");
            //cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                Park p = new Park();
                p.setId(rs.getLong(1));
                p.setName(rs.getString(2));
                p.setAddress(rs.getString(3));
                p.setPark_pic_id(rs.getLong(4));
                if(rs.getInt(5) == 1)
                    p.setBench(true);
                else
                    p.setBench(false);
                if(rs.getInt(6) == 1)
                    p.setFacilities(true);
                else
                    p.setFacilities(false);
                if(rs.getInt(7) == 1)
                    p.setFence(true);
                else
                    p.setFence(false);
                p.setDescription(rs.getString(8));
                p.setHeight(rs.getInt(9));
                p.setWidth(rs.getInt(10));
                p.setRadius(rs.getInt(11));
                if(rs.getInt(12) == 1)
                    p.setShadow(true);
                else
                    p.setShadow(false);
                if(rs.getInt(13) == 1)
                    p.setSpacket(true);
                else
                    p.setSpacket(false);
                if(rs.getInt(14) == 1)
                    p.setWater(true);
                else
                    p.setWater(false);
                p.setPointid(rs.getLong(15));
                p.setCity(rs.getString(16));
                parkList.add(p);
                
            }
            cstmt.close();
            con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                try { cstmt.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
                try { rs.close(); } catch (Exception e) { /* ignored */ }
            }
        return parkList;
    }
}
