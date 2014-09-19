
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
                a.setAction_id(rs.getInt(6));
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
            cstmt.execute();

            cstmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CheckLogin.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public long SendingDWPoint(ActivePoints ap)
    {
        long id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        Calendar c = Calendar.getInstance();
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Add_DWPoint (?,?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            java.sql.Timestamp timestamp = new java.sql.Timestamp(c.getTimeInMillis());
            cstmt.setLong(2, ap.getUser_id());
            cstmt.setFloat(3, (float)ap.getLatitude());
            cstmt.setFloat(4, (float)ap.getLongitude());
            cstmt.setTimestamp(5, timestamp);
            try
            {
                cstmt.execute();
                id = Long.valueOf(cstmt.getInt(1));
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            cstmt.close();
            con.close();
            
            return id;
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
    
    public static Calendar TimestampToCalendar(Timestamp date)
    { 
        System.out.println("date = " + date);
        DateFormat hours = new SimpleDateFormat( "HH" );
        String hour = hours.format(date);
        DateFormat minutes = new SimpleDateFormat( "mm" );
        String minute = minutes.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, c.get(Calendar.YEAR)); 
        cal.set(Calendar.MONTH, c.get(Calendar.MONTH));
        cal.set(Calendar.DATE, c.get(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
        cal.set(Calendar.MINUTE, Integer.valueOf(minute));
        System.out.println("cal = " + cal);
        return cal;
    }
}
