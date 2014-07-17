/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Message;
import doggizz.classes.PointLocation;
import doggizz.classes.User;
import doggizz.utils.Pool;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class Messages {
    public ArrayList<Message> Load_All_New_Messages(Long user_id)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_New_Messages (?)}");
            cstmt.setLong(1, user_id);
            try
            {
                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    User sender = new User();
                    User receiver = new User();
                    Message m = new Message();
                    m.setId(rs.getLong(1));
                    m.setMessage(rs.getString(2));
                    m.setSend_time(TimestampToCalendar(rs.getTimestamp(3)));
                    m.setNew_message(rs.getInt(4));
                    m.setGroup_id(rs.getLong(5));
                    sender.setId(rs.getLong(6));
                    receiver.setId(rs.getLong(7));
                    sender.setOwner_name(rs.getString(8));
                    sender.setOwner_surname(rs.getString(9));
                    receiver.setOwner_name(rs.getString(10));
                    receiver.setOwner_surname(rs.getString(11));
                    m.setSender(sender);
                    m.setReceiver(receiver);
                    messageList.add(m);
                } 
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            if(messageList.size()>0){
                cstmt = con.prepareCall("{call sp_Uncheck_Message (?)}");
                //cstmt.setLong(1, messageList.get(0).getGroup_id());
                cstmt.setLong(1,user_id);
                try
                {
                    cstmt.execute();
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                }
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
        return messageList;
    }
    public Message Sending_New_Message(Message m)
    {
        long id = 0;
        Calendar c = Calendar.getInstance();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {

            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Send_Message (?,?,?,?,?)}");
            //cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            Long sender_id = m.getSender().getId();
            Long receiver_id = m.getReceiver().getId();
            if(sender_id!=null&&receiver_id!=null)
            {
                cstmt.setString(1, m.getMessage());
                java.sql.Timestamp timestamp = new java.sql.Timestamp(c.getTimeInMillis());
                cstmt.setTimestamp(2, timestamp);
                m.setSend_time(c);
                cstmt.setLong(3, m.getSender().getId() );
                cstmt.setLong(4, m.getReceiver().getId());
                Long group_id = m.getGroup_id();
                cstmt.setLong(5, group_id);
                try
                {
                    rs = cstmt.executeQuery();
                    while(rs.next())
                    {
                        m.setId(rs.getLong(1));
                        m.setGroup_id(rs.getLong(2));
                    } 
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                }
                cstmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return m;
    }
    
    public ArrayList<Message> Load_All_Messages(Long user_id)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_Messages (?)}");
            cstmt.setLong(1, user_id);
            try
            {
                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    User sender = new User();
                    User receiver = new User();
                    Message m = new Message();
                    m.setId(rs.getLong(1));
                    m.setMessage(rs.getString(2));
                    m.setSend_time(TimestampToCalendar(rs.getTimestamp(3)));
                    m.setNew_message(rs.getInt(4));
                    m.setGroup_id(rs.getLong(5));
                    sender.setId(rs.getLong(6));
                    receiver.setId(rs.getLong(7));
                    sender.setOwner_name(rs.getString(8));
                    sender.setOwner_surname(rs.getString(9));
                    receiver.setOwner_name(rs.getString(10));
                    receiver.setOwner_surname(rs.getString(11));
                    m.setSender(sender);
                    m.setReceiver(receiver);
                    messageList.add(m);
                } 
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            if(messageList.size()>0){
                cstmt = con.prepareCall("{call sp_Uncheck_Message (?)}");
                //cstmt.setLong(1, messageList.get(0).getGroup_id());
                cstmt.setLong(1,user_id);
                try
                {
                    cstmt.execute();
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                }
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
        return messageList;
    }
    
    
    public ArrayList<Message> Check_Groip_Id_Count(Long group_id,Long count)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Check_Message_group_Count (?,?)}");
            cstmt.setLong(1, group_id);
            cstmt.setInt(2, count.intValue());
            try
            {
                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    User sender = new User();
                    User receiver = new User();
                    Message m = new Message();
                    m.setId(rs.getLong(1));
                    m.setMessage(rs.getString(2));
                    m.setSend_time(TimestampToCalendar(rs.getTimestamp(3)));
                    m.setNew_message(rs.getInt(4));
                    m.setGroup_id(rs.getLong(5));
                    sender.setId(rs.getLong(6));
                    receiver.setId(rs.getLong(7));
                    sender.setOwner_name(rs.getString(8));
                    sender.setOwner_surname(rs.getString(9));
                    receiver.setOwner_name(rs.getString(10));
                    receiver.setOwner_surname(rs.getString(11));
                    m.setSender(sender);
                    m.setReceiver(receiver);
                    messageList.add(m);
                } 
            }
            catch(SQLException e)
            {
                System.out.println(e);
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
        return messageList;
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
