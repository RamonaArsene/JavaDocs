package ro.teamnet.zth.appl.dao;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by ramona.arsene on 7/14/2017.
 */
public class LocationDaoTest {
    @Test
    public void testSelectOne() throws SQLException {
        LocationDao depDao = new LocationDao();
        assertEquals("Roma", depDao.selectOne(1000L).getCity());

    }

    @Test
    public void testUpdate() throws SQLException {
        LocationDao depDao = new LocationDao();
        Location loc = new Location();
        loc.setId(1000L);
        loc.setCity("Manchester");
        loc.setStreetAddress("etc");
        loc.setStateProvince("etc");
        loc.setPostalCode("etc");
        depDao.updateOne(loc);
        assertEquals( "Manchester", depDao.selectOne(1000L).getCity());
    }

    @Test
    public void testSelectAll() throws SQLException {
        LocationDao locationDao = new LocationDao();
        assertEquals(23 , locationDao.selectAll().size());
    }

    @Test
    public void testInsert() throws SQLException, IllegalAccessException {
        LocationDao depDao = new LocationDao();
        Location loc = new Location();
        loc.setId(3201L);
        loc.setCity("Manchester");
        loc.setStreetAddress("etc");
        loc.setStateProvince("etc");
        loc.setPostalCode("etc");
        depDao.updateOne(loc);
        assertEquals( "Manchester", depDao.selectOne(3201L).getCity());
    }

    @Test
    public void testFindByParams() throws SQLException {
        LocationDao depDao = new LocationDao();
        Map<String, Object> params= new HashMap<String, Object>();
        Object value = "Tokyo";
        params.put("city", value);
        assertEquals(1 , depDao.findByParams(params).size());

    }
}
