/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.classes;

/**
 *
 * @author Stas
 */

public class TaggedUser {
    private long id;
    private long user_id;
    private long tagged_user_id;
    private User user;

    public TaggedUser() {
        super();
    }

    public TaggedUser(long id, long user_id, long tagged_user_id) {
        this.id = id;
        this.user_id = user_id;
        this.tagged_user_id = tagged_user_id;
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

    public long getTagged_user_id() {
        return tagged_user_id;
    }

    public void setTagged_user_id(long tagged_user_id) {
        this.tagged_user_id = tagged_user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
