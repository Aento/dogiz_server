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
public class TopBoardMessages {
    long id;
    long user_id;
    int comments_count;
    long park_id;
    String message_text;
    String user_name;
    Calendar update_date;
    
    public TopBoardMessages(){
        super();
    }

    public TopBoardMessages(long id, long user_id, int comments_count, long park_id, String message_text, String user_name, Calendar update_date) {
        this.id = id;
        this.user_id = user_id;
        this.comments_count = comments_count;
        this.park_id = park_id;
        this.message_text = message_text;
        this.user_name = user_name;
        this.update_date = update_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public long getPark_id() {
        return park_id;
    }

    public void setPark_id(long park_id) {
        this.park_id = park_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Calendar getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Calendar update_date) {
        this.update_date = update_date;
    }
}
