package ro.teamnet.zth.api.em;
import ro.teamnet.zth.api.annotations.*;


import ro.teamnet.zth.api.annotations.Table;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by ramona.arsene on 7/12/2017.
 */
public class EntityUtiles {

    private EntityUtiles() throws UnsupportedOperationException{

    }

    public static String getTableName(Class entity){
        if(entity.getAnnotation(Table.class) == null){
            return null;
        }
        return ((Table)entity.getAnnotation(Table.class)).name();
    }

    public static ArrayList<ColumnInfo> getColumns (Class entity){
        ArrayList<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();
        Field[] classFields = entity.getDeclaredFields();
        ColumnInfo columnInfo = new ColumnInfo();

        for (int i = 0; i < classFields.length; i++) {
            if(classFields[i].isAnnotationPresent(Id.class)){
                columnInfo.setId(true);
                columnInfo.setDbColumnName(classFields[i].getAnnotation(Id.class).name());
                System.out.println(classFields[i].getAnnotation(Id.class).name());
            }
            else
                columnInfo.setId(false);
            columnInfo.setColumnType(classFields[i].getType());
            columnInfo.setColumnName(classFields[i].getName());
            System.out.println(classFields[i].getAnnotation(Column.class).name());

            columnInfo.setDbColumnName(((Column)classFields[i].getAnnotation(Column.class)).name());

            columnInfos.add(columnInfo);
        }

        return columnInfos;
    }


    public static Object castFromSqlType(Object value, Class wantedType){

        if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.Integer") ){
            //System.out.println(((BigDecimal) value).intValue() instanceof int);
            return (Integer)(((BigDecimal) value).intValue());
        }
        else
            if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.Long") ){
                return ((BigDecimal) value).longValue();
            }
            else
                if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.Float") ){
                     return ((BigDecimal) value).floatValue();
                }
                else
                if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.Double") ){
                    return ((BigDecimal) value).doubleValue();
                }
                else return value;

    }

    public static ArrayList<Field> getFieldByAnnotations (Class clazz, Class annotation){
        ArrayList<Field> fieldsList = new ArrayList<Field>();
        Field[] fields = clazz.getFields();
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].isAnnotationPresent(annotation)){
                fieldsList.add(fields[i]);
            }
        }

        return fieldsList;
    }

    public static Object getSqlValue(Object object){

        if(object.getClass().isAnnotationPresent(Table.class)){
            Field[] fields = object.getClass().getFields();
            for (int i = 0; i < fields.length; i++) {
                if(fields[i].isAnnotationPresent(Id.class)){
                    fields[i].setAccessible(true);
                    return fields[i];
                }
            }
        }
        return object;
    }


}
