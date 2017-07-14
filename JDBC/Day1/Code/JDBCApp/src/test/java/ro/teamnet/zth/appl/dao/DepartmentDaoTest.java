package ro.teamnet.zth.appl.dao;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by ramona.arsene on 7/14/2017.
 */
public class DepartmentDaoTest {

    @Test
    public void testSelectOne() throws SQLException {
        DepartmentDao depDao = new DepartmentDao();
        assertEquals((Long) 1700L, depDao.selectOne(10L).getLocation());

    }

    @Test
    public void testUpdate() throws SQLException {
        DepartmentDao depDao = new DepartmentDao();
        Department dep = new Department();
        dep.setId(20L);
        dep.setLocation(2400L);
        dep.setDepartmentName("Depnounounou");
        depDao.updateOne(dep);
        assertEquals( "Depnounounou", depDao.selectOne(20L).getDepartmentName());


    }

    @Test
    public void testSelectAll() throws SQLException {
        DepartmentDao depDao = new DepartmentDao();
        assertEquals(28 , depDao.selectAll().size());

    }

    @Test
    public void testInsert() throws SQLException, IllegalAccessException {
        DepartmentDao depDao = new DepartmentDao();
        Department dep = new Department();
        dep.setId(271L);
        dep.setLocation(2400L);
        dep.setDepartmentName("Depnounounounou");
        depDao.insertOne(dep);
        assertEquals( "Depnounounou", depDao.selectOne(depDao.finLastIndex()-1).getDepartmentName());
    }

    @Test
    public void testFindByParams() throws SQLException {
        DepartmentDao depDao = new DepartmentDao();
        Map<String, Object> params= new HashMap<String, Object>();
        Object value = new Long(1400L);
        params.put("LOCATION_ID", value);
        assertEquals(1 , depDao.findByParams(params).size());
    }




}
