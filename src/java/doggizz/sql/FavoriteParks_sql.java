/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.FavoriteParks;
import doggizz.utils.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Stas
 */
public class FavoriteParks_sql {
    public long UploadFavoritePark(FavoriteParks fav)
    {
       long id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{?=call sp_Add_Favorite (?,?)}");
            cstmt.registerOutParameter(1, java.sql.Types.BIGINT);
            cstmt.setLong(2, fav.getUser_id());
            cstmt.setLong(3, fav.getPark_id());
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
           
    public void RemoveFavoritePark(long id)
    {
        Connection con = null;
        CallableStatement cstmt = null;
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Remove_Favorites (?)}");
            cstmt.setLong(1, id);
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }   
    
    public ArrayList<FavoriteParks> LoadAllFavoritesParks(long user_id)
   {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<FavoriteParks> tagList = new ArrayList<FavoriteParks>();
        try{
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_Favorites (?)}");
            cstmt.setLong(1, user_id);
            rs = cstmt.executeQuery();
            
            
            while(rs.next())
            {
                FavoriteParks tag = new FavoriteParks();
                tag.setId(rs.getLong(1));
                tag.setUser_id(rs.getLong(2));
                tag.setPark_id(rs.getLong(3));
                tagList.add(tag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadingUsers.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try { cstmt.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
            try { rs.close(); } catch (Exception e) { /* ignored */ }
        }
        return tagList;
   }
}
