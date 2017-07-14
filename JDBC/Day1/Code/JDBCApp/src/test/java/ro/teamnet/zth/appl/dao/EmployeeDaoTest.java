package ro.teamnet.zth.appl.dao;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Employee;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ramona.arsene on 7/14/2017.
 */
public class EmployeeDaoTest {

    @Test
    public void testFindEmployees() throws SQLException {
        String dep = "adm";
        EmployeesDao empDao = new EmployeesDao();

        List<Employee> employees = empDao.findEmployeesByDepartment(dep);
        assertEquals(1, employees.size());
    }
}
