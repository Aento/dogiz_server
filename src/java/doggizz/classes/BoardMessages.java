/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

import java.util.Calendar;

/**
 *
 * @author Stas
 */
public class BoardMessages {
        long id;
    long parent_id;
    long user_id;
    String message_text;
    Calendar create_date;
    String user_name;
    
    public BoardMessages(){
        super();
    }

    public BoardMessages(long id, long parent_id, long user_id, String message_text, Calendar create_date, String user_name) {
        this.id = id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.message_text = message_text;
        this.create_date = create_date;
        this.user_name = user_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Calendar getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Calendar create_date) {
        this.create_date = create_date;
    }
    
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
