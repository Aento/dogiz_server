/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Park;
import doggizz.classes.PointLocation;
import doggizz.utils.Pool;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class LoadingPointLocation implements Serializable {
    
    public ArrayList<PointLocation> LoadingPointLocation()
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<PointLocation> pointList = new ArrayList<PointLocation>();
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_Points}");
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                PointLocation pl = new PointLocation();
                pl.setId(rs.getLong(1));
                pl.setLatitude(rs.getDouble(2));
                pl.setLongitude(rs.getDouble(3));
                pl.setPoint_kind(rs.getInt(4));
                pointList.add(pl);
            } 
            cstmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoadingPointLocation.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return pointList;
    }
}
