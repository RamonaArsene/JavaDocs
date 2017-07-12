package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by ramona.arsene on 7/12/2017.
 */

public class EntityUtilesTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtiles.getTableName(Department.class);
        assertEquals("Table name should be departments!", "Departments", tableName);
    }

    @Test
    public void testGetTableNameMethod2() {
        String tableName = EntityUtiles.getTableName(Employee.class);
        assertEquals("Table name should be departments!", "Employees", tableName);
    }

    @Test
    public void getColumnsTest(){
        Department d1 = new Department();
        int num = EntityUtiles.getColumns(Department.class).size();
        assertEquals(3, num);
    }

    @Test
    public void castBySqlTypesTest(){
        BigDecimal b = BigDecimal.valueOf(3);
        Integer i = 9;
        System.out.println(i.getClass());
        assertEquals(Integer.class, EntityUtiles.castFromSqlType(b, i.getClass()).getClass());
    }

//    @Test
//    public void castFromSqlType (){
//        Object obj =
//
//    }

}
