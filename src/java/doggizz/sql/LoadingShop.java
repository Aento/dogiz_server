/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.sql;

import doggizz.classes.Shop;
import doggizz.utils.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *111111111111
 * @author Stas
 */
public class LoadingShop {
    public ArrayList<Shop> LoadingShop() throws SQLException
    {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<Shop> shopList = new ArrayList<Shop>();
        try {
            con = Pool.getConnection();
            cstmt = con.prepareCall("{call sp_Load_All_Shop}");
            rs = cstmt.executeQuery();

            while(rs.next())
            {
                //ID, ADDITIONAL_INFO, ADDRESS, CITY, INTERNET_ADDRESS, PHONE, POINTID, SHOP_NAME
                Shop shop = new Shop();
                shop.setId(rs.getLong(1));
                shop.setAdditional_info(rs.getString(2));
                shop.setAddress(rs.getString(3));
                shop.setCity(rs.getLong(4));
                shop.setInternet_address(rs.getString(5));
                shop.setPhone(rs.getString(6));
                shop.setPointid(rs.getLong(7));
                shop.setShop_name(rs.getString(8));
                shopList.add(shop);
                shop = null;

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
        return shopList;
    }
    
    //*********************
}
