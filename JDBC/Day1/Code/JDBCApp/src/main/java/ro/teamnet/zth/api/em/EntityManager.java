package ro.teamnet.zth.api.em;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by ramona.arsene on 7/13/2017.
 */
public interface EntityManager {

    <T> T findById(Class<T> entityClass, Long id) throws SQLException;
    Long getNextIdVal(String tableName, String columnName) throws SQLException;
    <T> Object insert(T entity) throws IllegalAccessException;
    <T> List<T> findAll(Class<T> entityClass);
    <T> T update(T entity) throws SQLException;
//    void delete (Object entity);
//    List<T> findByParams(Class<T> entityClass, Map<String, Object> params);


}
