package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.DBManager;
import ro.teamnet.zth.api.em.*;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ramona.arsene on 7/14/2017.
 */
public class EmployeesDao {
    EntityManager entityManager = new EntityManagerImpl();

    public List<Employee> findEmployeesByDepartment(String dep) throws SQLException {
        Connection con = DBManager.getConnection();

        List<ColumnInfo> columns = EntityUtils.getColumns(Employee.class);

        Condition joinCond = new Condition();
        joinCond.setColumnName("department_id");
        joinCond.setValue("department_id");

        Condition cond = new Condition();
        cond.setColumnName("department_name");
        cond.setValue(dep);

        QueryBuilder query = new QueryBuilder();
        query.addQueryColumns(columns);
        query.setTableName("employees");
        query.setJoinTableName("departments");
        query.setQueryType(QueryType.SELECT);
        query.addJoinCondition(joinCond);
        query.addCondition(cond);

        String sqlQuery = query.createQuery();
        System.out.println(sqlQuery);


        try (Statement stmt = con.createStatement()) {
            ResultSet res = stmt.executeQuery(sqlQuery);
            Employee myNewInstance = new Employee();
            List<Employee> entries = new ArrayList<Employee>();
            int count = 0;
            while(res.next()){
                for (ColumnInfo column : columns) {
                    Object colDbValue= res.getObject(column.getDbColumnName());
                    try {
                        Field field = Employee.class.getDeclaredField(column.getColumnName());
                        field.setAccessible(true);
                        field.set(myNewInstance, EntityUtils.castFromSqlType(colDbValue, column.getColumnType()));
                        if(count % columns.size() == 0){
                            entries.add(myNewInstance);
                        }
                        count ++;
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }



        return null;
    }

}
