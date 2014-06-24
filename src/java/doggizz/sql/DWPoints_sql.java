/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;


import doggizz.classes.ActivePoints;
import java.util.ArrayList;
import doggizz.utils.Pool;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Stas
 */
public class DWPoints_sql {
    public ArrayList<ActivePoints> LoadAllDWPoints()
    {
        ArrayList<ActivePoints> ap = new ArrayList<ActivePoints>();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_DWPoints()}");
            rs = cstmt.executeQuery();
            ActivePoints a;
            while(rs.next())
            {
                a = new ActivePoints();
                a.setId(rs.getLong(1));
                a.setUser_id(rs.getLong(2));
                a.setLatitude(rs.getFloat(3));
                a.setLongitude(rs.getFloat(4));
                a.setSend_time(TimestampToCalendar(rs.getTimestamp(5)));
                ap.add(a);
            }
            cstmt.close();
            con.close();
            if(ap != null)
            {
                return ap;
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
    
    public void RemoveDWPoint(long user_id)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Remove_DWPoint(?)}");
            cstmt.setLong(1,user_id);
            cstmt.executeQuery();

            cstmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CheckLogin.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public void SendingDWPoint(ActivePoints ap)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        Calendar c = Calendar.getInstance();
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Add_DWPoint (?,?,?,?)}");
            java.sql.Timestamp timestamp = new java.sql.Timestamp(c.getTimeInMillis());
            cstmt.setLong(1, ap.getUser_id());
            cstmt.setFloat(2, (float)ap.getLatitude());
            cstmt.setFloat(3, (float)ap.getLongitude());
            cstmt.setTimestamp(4, timestamp);
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
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
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
