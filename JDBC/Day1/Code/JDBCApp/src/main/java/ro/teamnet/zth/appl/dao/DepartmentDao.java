package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ramona.arsene on 7/14/2017.
 */
public class DepartmentDao {

    EntityManager entityMan = new EntityManagerImpl();

    public Department selectOne(Long id) throws SQLException {
       Department dep = new Department();
       System.out.println(entityMan.findById(Department.class, id).getDepartmentName());
       dep.setDepartmentName(entityMan.findById(Department.class, id).getDepartmentName());
       dep.setLocation(entityMan.findById(Department.class, id).getLocation());
       dep.setId(entityMan.findById(Department.class, id).getId());

       return dep;

    }

    public List<Department> selectAll(){
        List<Department> deps = entityMan.findAll(Department.class);
        return deps;
    }

    public void updateOne(Department dep) throws SQLException {
            entityMan.update(dep);

    }

    public void insertOne(Department dep) throws IllegalAccessException {
        entityMan.insert(dep);
    }

    public Long finLastIndex() throws SQLException {
        return entityMan.getNextIdVal("Departments", "Department_id");
    }

    public List<Department>findByParams( Map<String, Object> params){
        List<Department> deps = entityMan.findByParams(Department.class, params);
        return deps;
    }

    public void delete(Department dep){
        entityMan.delete(dep);
    }
}
