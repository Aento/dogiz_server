/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.BoardMessages;
import doggizz.classes.ParkCheckinTotal;
import doggizz.classes.TopBoardMessages;
import doggizz.classes.User;
import doggizz.utils.Pool;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.digester.ArrayStack;

/**
 *
 * @author Stas
 */
public class SqlBoardMessages {
    public ArrayList<TopBoardMessages> LoadTopBoardMessages(long park_id,long max_id)
    {
        ArrayList<TopBoardMessages> bList = new ArrayStack<TopBoardMessages>();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try {
            con = Pool.getConnection();
            if(max_id!=-1){
                if(max_id!=0){
                    cstmt = con.prepareCall("{call sp_Load_Top_Board_Messages (?,?)}");
                    cstmt.setLong(1, park_id);
                    cstmt.setLong(2, max_id);
                }
                else {
                    cstmt = con.prepareCall("{call sp_Load_Top_Board_Messages (?)}");
                    cstmt.setLong(1, park_id);
                }
                rs = cstmt.executeQuery();
                TopBoardMessages tb;
                while(rs.next())
                {
                    tb = new TopBoardMessages();
                    tb.setId(rs.getLong(1));
                    tb.setUser_id(rs.getLong(2));
                    tb.setComments_count(rs.getInt(3));
                    tb.setPark_id(rs.getLong(4));
                    tb.setMessage_text(rs.getString(5));
                    tb.setUpdate_date(TimestampToCalendar(rs.getTimestamp(6)));
                    tb.setUser_name(rs.getString(7));
                    bList.add(tb);
                }
            }
            else
            {
                cstmt = con.prepareCall("{call sp_Load_Top_Board_Messages_Count (?)}");
                cstmt.setLong(1, park_id);
                rs = cstmt.executeQuery();
                TopBoardMessages tb = new TopBoardMessages();
                while(rs.next())
                {
                    tb.setId(rs.getLong(1));
                    tb.setComments_count(rs.getInt(2));
                    bList.add(tb);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return bList;
    }
    
    public ArrayList<BoardMessages> LoadBoardMessages(long parent_id,long max_id)
    {
        ArrayList<BoardMessages> bList = new ArrayStack<BoardMessages>();
        BoardMessages tb;
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try {
            con = Pool.getConnection();
            if(max_id!=0){
                cstmt = con.prepareCall("{call sp_Load_Board_Messages (?,?)}");
                cstmt.setLong(1, parent_id);
                cstmt.setLong(2, max_id);
            }
            else {
                cstmt = con.prepareCall("{call sp_Load_Board_Messages (?)}");
                cstmt.setLong(1, parent_id);
            }
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                tb = new BoardMessages();
                tb.setId(rs.getLong(1));
                tb.setParent_id(rs.getLong(2));
                tb.setUser_id(rs.getLong(3));
                tb.setMessage_text(rs.getString(4));
                tb.setCreate_date(TimestampToCalendar(rs.getTimestamp(5)));
                tb.setUser_name(rs.getString(6));
                bList.add(tb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return bList;
    }
    
    public long LoadTopBoardUserId(long parent_id)
    {
        //ArrayList<BoardMessages> bList = new ArrayStack<BoardMessages>();
        BoardMessages tb;
        long user_id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_Top_Board_User (?)}");
            cstmt.setLong(1, parent_id);
            
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                user_id = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return user_id;
    }
    
    public BoardMessages UploadBoardMEssage(BoardMessages bm) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        long id = 0;
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Add_BoardMessage (?,?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            cstmt.setLong(2, bm.getParent_id());
            cstmt.setLong(3, bm.getUser_id());
            cstmt.setString(4, bm.getMessage_text());  
            if(bm.getCreate_date()!=null)
                cstmt.setDate(5, new java.sql.Date(bm.getCreate_date().getTimeInMillis()));
            else
                cstmt.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            cstmt.execute();
            id = Long.valueOf(cstmt.getInt(1));
            if(id!=0)
                bm.setId(id);
            else 
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return bm;
    }
    public static Calendar DateToCalendar(Date date)
    { 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
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
     
    public long UploadTopBoardMEssage(TopBoardMessages bm) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        long id = 0;
        try
        {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Add_TopBoardMessage (?,?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            cstmt.setLong(2, bm.getUser_id());
            cstmt.setLong(3, bm.getPark_id());
            cstmt.setString(4, bm.getMessage_text());
            cstmt.execute();
            id = Long.valueOf(cstmt.getInt(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return id;
    }
}