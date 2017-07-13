package ro.teamnet.zth.api.database;

import org.junit.Test;
import ro.teamnet.zth.api.DBManager;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ramona.arsene on 7/13/2017.
 */
public class DBManagerTest {

    @Test
    public void testCheckConnection() throws SQLException {
        assertEquals(true, DBManager.checkConnection(DBManager.getConnection()));
    }

    @Test
    public void testGetConnection() throws SQLException {
       assertNotNull(DBManager.getConnection());
    }
}
