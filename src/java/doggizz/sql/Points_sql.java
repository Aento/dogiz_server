
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.ActivePoints;
import doggizz.classes.ParkCheckinTotal;
import doggizz.utils.Pool;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class Points_sql {
    
   public long UploadPoint(ActivePoints actPoint)
   {
       long id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        Calendar c = Calendar.getInstance();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Add_ActivePoint (?,?,?,?,?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            java.sql.Timestamp timestamp = new java.sql.Timestamp(c.getTimeInMillis());
            cstmt.setTimestamp(2,timestamp);
            cstmt.setLong(3, actPoint.getUser_id());
            cstmt.setFloat(4, (float)actPoint.getLatitude());
            cstmt.setFloat(5, (float)actPoint.getLongitude());
            cstmt.setInt(6, actPoint.getAction_id());
            cstmt.setLong(7, actPoint.getPark_id());
            if(actPoint.getFree_text()!=null)
                cstmt.setString(8,actPoint.getFree_text());
            else
                cstmt.setString(8,"");
            cstmt.execute();
            id = Long.valueOf(cstmt.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        return id;
   }
   
   public ArrayList<ActivePoints> LoadingPoints(Float latitude,Float longtitude)
   {
        long id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<ActivePoints> pointsList = new ArrayList<ActivePoints>();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_ActivePoint (?,?)}");
            cstmt.setFloat(1, latitude);
            cstmt.setFloat(2, longtitude);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                ActivePoints point = new ActivePoints();
                point.setId(rs.getLong(1));
                point.setSend_time(TimestampToCalendar(rs.getTimestamp(2)));
                point.setUser_id(rs.getLong(3));
                point.setLatitude(rs.getDouble(4));
                point.setLongitude(rs.getDouble(5));
                point.setAction_id(rs.getInt(6));
                point.setPark_id(rs.getLong(7));
                point.setFree_text(rs.getString(8));
                pointsList.add(point);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return pointsList;
   }
   
   public ArrayList<ParkCheckinTotal> LoadingMyAreaPoints(Float latitude,Float longtitude)
   {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<ParkCheckinTotal> totalList = new ArrayList<ParkCheckinTotal>();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_My_Area (?,?)}");
            cstmt.setFloat(1, latitude);
            cstmt.setFloat(2, longtitude); 
            rs = cstmt.executeQuery();
            
            while(rs.next())
            {
                ParkCheckinTotal pTotal = new ParkCheckinTotal();
                pTotal.setPark_id(rs.getLong(1));
                pTotal.setUsers_male(rs.getInt(2));
                pTotal.setUsers_female(rs.getInt(3));
                pTotal.setDogs_male(rs.getInt(4));
                pTotal.setDogs_female(rs.getInt(5));
                totalList.add(pTotal);        
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return totalList;
   }
   
   public ArrayList<ActivePoints> LoadingMyAreaReports(Float latitude,Float longtitude)
   {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<ActivePoints> pointsList = new ArrayList<ActivePoints>();
        //ArrayList<Picture> picList = new ArrayList<Picture>();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_Center_Screen_Report_Points (?,?)}");
            cstmt.setFloat(1, latitude);
            cstmt.setFloat(2, longtitude);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                ActivePoints point = new ActivePoints();
                point.setId(rs.getLong(1));
                point.setSend_time(TimestampToCalendar(rs.getTimestamp(2)));
                point.setUser_id(rs.getLong(3));
                point.setLatitude(rs.getDouble(4));
                point.setLongitude(rs.getDouble(5));
                point.setAction_id(rs.getInt(6));
                point.setPark_id(rs.getLong(7));
                point.setFree_text(rs.getString(8));
                pointsList.add(point);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return pointsList;
   }        
   
   public ArrayList<ActivePoints> LoadingCeneterScreenAreaPoints(Float latitude,Float longtitude)
   {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<ActivePoints> pointsList = new ArrayList<ActivePoints>();
        //ArrayList<Picture> picList = new ArrayList<Picture>();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_Center_Screen_Area (?,?)}");
            cstmt.setFloat(1, latitude);
            cstmt.setFloat(2, longtitude);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                ActivePoints point = new ActivePoints();
                point.setId(rs.getLong(1));
                point.setSend_time(TimestampToCalendar(rs.getTimestamp(2)));
                point.setUser_id(rs.getLong(3));
                point.setLatitude(rs.getDouble(4));
                point.setLongitude(rs.getDouble(5));
                point.setAction_id(rs.getInt(6));
                point.setPark_id(rs.getLong(7));
                point.setFree_text(rs.getString(8));
                pointsList.add(point);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return pointsList;
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
        cal.set(Calendar.YEAR, c.get(Calendar.YEAR)); 
        cal.set(Calendar.MONTH, c.get(Calendar.MONTH));
        cal.set(Calendar.DATE, c.get(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
        cal.set(Calendar.MINUTE, Integer.valueOf(minute));
        return cal;
    }
}

