/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.apache.tomcat.dbcp.dbcp.cpdsadapter.DriverAdapterCPDS;





/**
 *
 * @author Stas
 */
public class Pool {
    private static DataSource ds;

    static
    {
        DriverAdapterCPDS cpds = new DriverAdapterCPDS();
        try {
            cpds.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
        }
        cpds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=Dogiz");
        cpds.setUser("doggizz_server");
        cpds.setPassword("QWaszx12");

        SharedPoolDataSource tds = new SharedPoolDataSource();
        tds.setConnectionPoolDataSource(cpds);
        tds.setMaxActive(1);
        tds.setMaxWait(1000000);
        tds.setMaxIdle(0);

        ds = tds;
    }

    public static Connection  getConnection() throws SQLException
    {
        return ds.getConnection();
    }
}
