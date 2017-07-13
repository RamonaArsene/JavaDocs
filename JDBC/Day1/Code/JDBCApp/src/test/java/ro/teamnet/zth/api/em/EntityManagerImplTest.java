package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.DBProperties;
import ro.teamnet.zth.appl.domain.Department;

import java.math.BigDecimal;
import java.sql.*;

import static javafx.scene.input.KeyCode.T;
import static org.junit.Assert.assertEquals;

/**
 * Created by ramona.arsene on 7/13/2017.
 */
public class EntityManagerImplTest {



    @Test
    public void testFindById() throws SQLException {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        EntityManagerImpl emp = new EntityManagerImpl();
        assertEquals("Administration", emp.findById(Department.class, (long) 10).getDepartmentName());

    }

    @Test
    public void testGetNextIdVal() throws SQLException {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        //BigDecimal nextId = new Long(0);
        Long nextId = new Long(207);
        EntityManagerImpl emp = new EntityManagerImpl();
        assertEquals(nextId, emp.getNextIdVal("EMPLOYEES", "EMPLOYEE_ID"));
    }

    @Test
    public void testInsert() throws SQLException, IllegalAccessException {
        Long id = new Long(207);
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        EntityManagerImpl emp = new EntityManagerImpl();
        Department dep = new Department();
        dep.setDepartmentName("DepNou");
        dep.setLocation(1000L);
         Object rez = emp.insert(dep);
        assertEquals("DepNou", emp.findById(Department.class,id).getDepartmentName());
    }

    @Test
    public void testFindAll() throws SQLException {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
            EntityManagerImpl emp = new EntityManagerImpl();
            assertEquals(28, emp.findAll(Department.class).size()/3);

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
        Long id = new Long(1700L);
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        EntityManagerImpl emp = new EntityManagerImpl();
        Department dep = new Department();
        dep.setDepartmentName("DepNou5");
        dep.setLocation(1000L);
        dep.setId(207L);
        Object rez = emp.update(dep);
        assertEquals(id.longValue(),  emp.findById(Department.class,id).getLocation().longValue());
    }
}
