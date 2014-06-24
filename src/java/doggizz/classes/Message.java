/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

import java.util.Calendar;



public class Message{
    
    //message, send_time, new_message, message_group
    long id;
    String message;
    Calendar send_time;
    int new_message;
    long group_id;
    User sender;
    User receiver;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getSend_time() {
        return send_time;
    }

    public void setSend_time(Calendar send_time) {
        this.send_time = send_time;
    }

    public int getNew_message() {
        return new_message;
    }

    public void setNew_message(int new_message) {
        this.new_message = new_message;
    }
    
    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
    
    
}
