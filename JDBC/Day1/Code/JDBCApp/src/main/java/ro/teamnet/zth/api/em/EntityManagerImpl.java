package ro.teamnet.zth.api.em;

import com.sun.org.apache.xpath.internal.SourceTree;
import ro.teamnet.zth.api.DBManager;
import ro.teamnet.zth.api.DBProperties;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by ramona.arsene on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {

    @Override
    public <T> T findById(Class<T> entityClass, Long id) throws SQLException {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fields  = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

        Condition cond = new Condition();
        ColumnInfo columnId = this.findFieldIdFromField(columns);
        cond.setColumnName(columnId.getDbColumnName());
        cond.setValue(id);

        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.addCondition(cond);
        query.setQueryType(QueryType.SELECT);
        query.createQuery();
        String sqlQuery = query.createQuery();

        try (Statement stmt = con.createStatement( )){
            ResultSet rez = stmt.executeQuery(sqlQuery);
            T myNewInstance = entityClass.newInstance();

            while (rez.next()) {
                for (ColumnInfo column : columns) {
                   Object colDbValue= rez.getObject(column.getDbColumnName());
                    try {
                        Field field = entityClass.getDeclaredField(column.getColumnName());
                        field.setAccessible(true);
                        field.set(myNewInstance, EntityUtils.castFromSqlType(colDbValue, column.getColumnType()));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }

            }
            return myNewInstance;
        }
        catch (SQLException | InstantiationException | IllegalAccessException e) {
        }
        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnName) {
        Object colDb;
        Long nextId = null;
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement stmt = con.createStatement()) {
            String sqlQuery = "SELECT MAX(" + columnName + ") FROM " + tableName;
            ResultSet rez = stmt.executeQuery(sqlQuery);
            if (rez.next()) {
                BigDecimal bdValue = (BigDecimal) rez.getObject(rez.getRow());
                nextId = bdValue.longValue();
                nextId ++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

        @Override
    public <T> Object insert(T entity) throws IllegalAccessException {
            String columnId = null;;
            try {
                Connection con = DBManager.getConnection();
                String tableName = EntityUtils.getTableName(entity.getClass());
                List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
                List<Field> fields  = EntityUtils.getFieldsByAnnotations(entity.getClass(), Column.class);

                for (ColumnInfo column : columns) {
                    if(column.isId()){
                        System.out.println(this.getNextIdVal(tableName,column.getDbColumnName()));
                        column.setValue(this.getNextIdVal(tableName, column.getDbColumnName()));
                        columnId = column.getDbColumnName();
                    }
                    else{
                        Field field = entity.getClass().getDeclaredField(column.getColumnName());
                        field.setAccessible(true);
                        column.setValue(field.get(entity));
                    }
                }

                QueryBuilder query = new QueryBuilder();
                query.setTableName(tableName);
                query.addQueryColumns(columns);
                query.setQueryType(QueryType.INSERT);
                query.createQuery();
                String sqlQuery = query.createQuery();

                try(Statement stmt = con.createStatement( )){
                    if(stmt.execute(sqlQuery)){
                        //return entity;
                        return this.findById(entity.getClass(), this.getNextIdVal(tableName, columnId));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }


            return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {

        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        try {
            Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);

            List<T> entries = new ArrayList<>();

            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
            List<Field> fields  = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

            QueryBuilder query = new QueryBuilder();
            query.setTableName(tableName);
            query.addQueryColumns(columns);
            query.setQueryType(QueryType.SELECT);
            query.createQuery();
            String sqlQuery = query.createQuery();

            try (Statement stmt = con.createStatement( )){
                ResultSet rez = stmt.executeQuery(sqlQuery);

                int count = 0;
                while (rez.next()) {

                    T myNewInstance = entityClass.newInstance();
                    for (ColumnInfo column : columns) {
                        Object colDbValue= rez.getObject(column.getDbColumnName());
                        try {
                            Field field = entityClass.getDeclaredField(column.getColumnName());
                            field.setAccessible(true);
                            field.set(myNewInstance, EntityUtils.castFromSqlType(colDbValue, column.getColumnType()));

                            if(count % columns.size() == 0){
                                entries.add(myNewInstance);
                            }
                            count ++;
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return entries;
            }
            catch (SQLException | InstantiationException | IllegalAccessException e) {
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    public <T> T update(T entity) throws SQLException {
        Long id = null;
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        try {
            Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
            List<Field> fields  = EntityUtils.getFieldsByAnnotations(entity.getClass(), Column.class);

            for (ColumnInfo column : columns){
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                System.out.println("Fields values: " + field.get(entity));
                column.setValue(field.get(entity));
            }
            Condition cond = new Condition();
            ColumnInfo columnId = this.findFieldIdFromField(columns);
            System.out.println("Update id " + columnId.getValue());
            cond.setColumnName(columnId.getDbColumnName());
            cond.setValue((Long) columnId.getValue());
            id = (Long)columnId.getValue();

            QueryBuilder query = new QueryBuilder();
            query.setTableName(tableName);
            query.addQueryColumns(columns);
            query.addCondition(cond);
            query.setQueryType(QueryType.UPDATE);
            query.createQuery();
            String sqlQuery = query.createQuery();

            try (Statement stmt = con.createStatement()) {
                ResultSet rez = stmt.executeQuery(sqlQuery);
                if(rez.next()){
                    return (T)findById(entity.getClass(), id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete (Object entity) {

        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        try {
            Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
            List<Field> fields  = EntityUtils.getFieldsByAnnotations(entity.getClass(), Column.class);

            for (ColumnInfo column : columns){
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
            }

            Condition cond = new Condition();
            ColumnInfo columnId = this.findFieldIdFromField(columns);
            System.out.println("Update id " + columnId.getValue());
            cond.setColumnName(columnId.getDbColumnName());
            cond.setValue((Long) columnId.getValue());
            //id = (Long)columnId.getValue();

            QueryBuilder query = new QueryBuilder();
            query.setTableName(tableName);
            query.addQueryColumns(columns);
            query.addCondition(cond);
            query.setQueryType(QueryType.DELETE);
            query.createQuery();
            String sqlQuery = query.createQuery();

            try (Statement stmt = con.createStatement()) {
                stmt.executeQuery(sqlQuery);
                System.out.println("Delete query executed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params){

        try {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
            List<Field> fields  = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);


            QueryBuilder query = new QueryBuilder();
            query.setTableName(tableName);
            query.addQueryColumns(columns);
            query.setQueryType(QueryType.SELECT);
            query.createQuery();

            Condition cond = new Condition();
            for(Map.Entry<String, Object> param : params.entrySet()){
                cond.setColumnName(param.getKey());
                cond.setValue(param.getValue());
                query.addCondition(cond);
            }

            String sqlQuery = query.createQuery();

            try (Statement stmt = con.createStatement()) {
                ResultSet res = stmt.executeQuery(sqlQuery);
                T myNewInstance = entityClass.newInstance();
                List<T> entries = new ArrayList<T>();
                int count = 0;
                while(res.next()){
                    for (ColumnInfo column : columns) {
                        Object colDbValue= res.getObject(column.getDbColumnName());
                        try {
                            Field field = entityClass.getDeclaredField(column.getColumnName());
                            field.setAccessible(true);
                            field.set(myNewInstance, EntityUtils.castFromSqlType(colDbValue, column.getColumnType()));
                            if(EntityUtils.castFromSqlType(colDbValue, column.getColumnType()) == params.get(column.getColumnName())){
                                field.set(myNewInstance, EntityUtils.castFromSqlType(colDbValue, column.getColumnType()));
                            }
                            if(count % columns.size() == 0){
                                entries.add(myNewInstance);
                            }
                            count ++;
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return entries;
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    //public findByString()

//    <T> List<Object> insertEntities( List<T> entities) throws IllegalAccessException{
//
//
//
//
//        return null;
//
//    }



    private ColumnInfo findFieldIdFromField(List<ColumnInfo> columns){
        ColumnInfo id = null;
        for (ColumnInfo column: columns) {
            if(column.isId()){
                id = column;
            }
        }
        System.out.println(id.getDbColumnName());
        return id;
    }

}
