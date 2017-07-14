package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by ramona.arsene on 7/14/2017.
 */
public class LocationDao {

    EntityManager entityMan = new EntityManagerImpl();

    public Location selectOne(Long id) throws SQLException {
        Location location = new Location();
        location.setCity(entityMan.findById(Location.class, id).getCity());
        location.setPostalCode(entityMan.findById(Location.class, id).getPostalCode());
        location.setStateProvince(entityMan.findById(Location.class, id).getStateProvince());
        location.setStreetAddress(entityMan.findById(Location.class, id).getStreetAddress());
        location.setId(entityMan.findById(Location.class, id).getId());

        return location;

    }

    public List<Location> selectAll(){
        List<Location> deps = entityMan.findAll(Location.class);
        return deps;
    }

    public void updateOne(Location dep) throws SQLException {
        entityMan.update(dep);

    }

    public void insertOne(Location dep) throws IllegalAccessException {
        entityMan.insert(dep);
    }

    public Long finLastIndex() throws SQLException {
        return entityMan.getNextIdVal("Departments", "Department_id");
    }

    public List<Location>findByParams( Map<String, Object> params){
        List<Location> deps = entityMan.findByParams(Location.class, params);
        return deps;
    }

    public void delete(Location dep){
        entityMan.delete(dep);
    }
}
