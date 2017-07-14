package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.DBProperties;
import ro.teamnet.zth.appl.domain.Department;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static javafx.scene.input.KeyCode.T;
import static org.junit.Assert.assertEquals;

/**
 * Created by ramona.arsene on 7/13/2017.
 */
public class EntityManagerImplTest {



    @Test
    public void testFindById() throws SQLException {
        EntityManagerImpl emp = new EntityManagerImpl();
        assertEquals((Long)1700L, emp.findById(Department.class, (long) 10).getLocation());

    }

    @Test
    public void testGetNextIdVal() throws SQLException {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        //BigDecimal nextId = new Long(0);
        Long nextId = new Long(271);
        EntityManagerImpl emp = new EntityManagerImpl();
        assertEquals(nextId, emp.getNextIdVal("departments", "department_ID"));
    }

    @Test
    public void testInsert() throws SQLException, IllegalAccessException {
        Long id = new Long(271);
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        EntityManagerImpl emp = new EntityManagerImpl();
        Department dep = new Department();
        dep.setDepartmentName("DepNou");
        //dep.setLocation(1000L);
         Object rez = emp.insert(dep);
        assertEquals("DepNou", emp.findById(Department.class,id).getDepartmentName());
    }

    @Test
    public void testFindAll() throws SQLException {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
            EntityManagerImpl emp = new EntityManagerImpl();
            assertEquals(28, emp.findAll(Department.class).size());

    }

    @Test
    public void testUpdate() throws SQLException, IllegalAccessException {
        Long id = new Long(207);
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        EntityManagerImpl emp = new EntityManagerImpl();
        Department dep = new Department();
        dep.setDepartmentName("DepNou5");
        dep.setLocation(1000L);
        dep.setId(207L);
        Object rez = emp.update(dep);
        assertEquals("DepNou5", emp.findById(Department.class,id).getDepartmentName());
    }

    @Test
    public void testUpdate2() throws SQLException, IllegalAccessException {
        Long id = new Long(207L);
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        EntityManagerImpl emp = new EntityManagerImpl();
        Department dep = new Department();
        dep.setDepartmentName("DepNou5");
        dep.setLocation(1500L);
        dep.setId(207L);
        Object rez = emp.update(dep);
        //assertEquals(1500L,  emp.findById(Department.class,id).getLocation().longValue());
    }

    @Test
    public void testDelete() throws SQLException, IllegalAccessException {
        Long id = new Long(207L);
        EntityManagerImpl emp = new EntityManagerImpl();
        Department dep = new Department();
        dep.setDepartmentName("DepNou");
        dep.setLocation(1500L);
        dep.setId(207L);
        emp.delete(dep);
        int entries = emp.findAll(Department.class).size()/3;
        assertEquals(27, entries);
    }

    @Test
    public void testFindByParams() throws SQLException {
        EntityManagerImpl emp = new EntityManagerImpl();
        Map<String, Object> params= new HashMap<String, Object>();
        Object value = new Long(1400L);
        params.put("LOCATION_ID", value);
        assertEquals(1, emp.findByParams(Department.class, params).size());

    }
}
