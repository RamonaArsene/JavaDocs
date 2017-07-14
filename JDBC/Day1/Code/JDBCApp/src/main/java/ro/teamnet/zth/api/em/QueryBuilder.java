package ro.teamnet.zth.api.em;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    private List<Condition> joinConditions;

    public Object getJoinTableName() {
        return joinTableName;
    }

    public void setJoinTableName(Object joinTableName) {
        this.joinTableName = joinTableName;
    }

    private Object joinTableName = null;

    public String getValueForQuery(Object value){
        if (value instanceof String){
            System.out.println(value);
            return "'" + value + "'";
        } else if (value instanceof java.sql.Date){
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
        } else {
            System.out.println("Value: "+ value.toString());
            return value.toString();
        }

    }

    public QueryBuilder addCondition(Condition condition){
        if (this.conditions == null){
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    public QueryBuilder addJoinCondition(Condition condition){
        if (this.joinConditions == null){
            this.joinConditions = new ArrayList<>();
        }
        this.joinConditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName){
        this.tableName = tableName;

        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        if (this.queryColumns == null){
            this.queryColumns = new ArrayList<>();
        }
        this.queryColumns.addAll(queryColumns);

        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType = queryType;
        return this;
    }

    private String createSelectQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        boolean isFirst = true;
        for(ColumnInfo columnInfo : queryColumns) {
            if(!isFirst) {
                sql.append(",");
            }
            sql.append(tableName + "." + columnInfo.getDbColumnName());
            isFirst = false;
        }
        sql.append(" from " + tableName);

        boolean whereAdded = false;
        if(conditions != null && !conditions.isEmpty()) {
            for(Condition condition : conditions) {
                System.out.println(condition.getValue());
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=")
                        .append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }
    private String createSelectJoinQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        boolean isFirst = true;
        for(ColumnInfo columnInfo : queryColumns) {
            if(!isFirst) {
                sql.append(",");
            }
            sql.append(tableName + "." + columnInfo.getDbColumnName());
            isFirst = false;
        }
        sql.append(" from " + tableName + "inner join " + joinTableName + " on ");

        boolean joinAdded = false;
        if(joinConditions != null && !joinConditions.isEmpty()) {
            for(Condition  joinCondition : joinConditions) {
                System.out.println(joinCondition.getValue());
                sql.append(joinAdded ? " and" : "").append(joinCondition.getColumnName()).append("=")
                        .append(getValueForQuery(joinCondition.getValue()));
            }
        }
        boolean whereAdded = false;
        if(conditions != null && !conditions.isEmpty()) {
            for(Condition condition : conditions) {
                System.out.println(condition.getValue());
                sql.append(whereAdded ? " and" : " where UPPER(").append(condition.getColumnName()).append(") like UPPER('%")
                        .append(getValueForQuery(condition.getValue())).append("%)");
        }
        }
        return sql.toString();
    }


    private String createDeleteQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(tableName);
        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createUpdateQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" set ");
        boolean first = true;
        for (ColumnInfo column : queryColumns) {

                if (!first) {
                    sql.append(",");
                } else {
                    first = false;
                }
                sql.append(column.getDbColumnName()).append("=").append(getValueForQuery(column.getValue()));

        }

        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createInsertQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append(" (");
        StringBuilder sqlValues = new StringBuilder(" values (");
        boolean first = true;
        for (ColumnInfo columnInfo : queryColumns) {

            if (!first) {
                sql.append(",");
                sqlValues.append(",");
            } else {
                first = false;
            }
            sql.append(columnInfo.getDbColumnName());
            sqlValues.append(getValueForQuery(columnInfo.getValue()));
        }

        sql.append(") ");
        sqlValues.append(")");
        sql.append(sqlValues);

        return sql.toString();
    }

    public String createQuery(){
        if (QueryType.SELECT.equals(this.queryType)){
            return createSelectQuery();
        } else if (QueryType.INSERT.equals(this.queryType)) {
            return createInsertQuery();
        } else if (QueryType.UPDATE.equals(this.queryType)) {
            return createUpdateQuery();
        } else if (QueryType.DELETE.equals(this.queryType)) {
            return createDeleteQuery();
        }else if(QueryType.SELECT.equals(this.queryType) && joinTableName != null){
            return createSelectJoinQuery();
        }
        return null;
    }

    public QueryType getQueryType(){
        return this.queryType;
    }
}
