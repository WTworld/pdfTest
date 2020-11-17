package com.u_anywhere.base.mapper;

import org.apache.ibatis.mapping.MappedStatement;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Iterator;
import java.util.Set;

public class MySelectProvider extends MapperTemplate{
    public MySelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }


    public String insertBatch(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if (column.isInsertable()) {
                sql.append(column.getColumnHolder("record") + ",");
            }
        }

        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }
    /**
     * 根据Example查询
     *
     * @param ms
     * @return
     */
    public String selectMapByExample(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //将返回值修改为实体类型
//        setResultType(ms, Map.class);
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("<if test=\"distinct\">distinct</if>");
        //支持查询指定列
        sql.append(exampleSelectColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.exampleWhereClause());
        sql.append(SqlHelper.exampleOrderBy(entityClass));
        return sql.toString();
    }
    public String selectMap(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
        return sql.toString();
    }

    public static String exampleSelectColumns(Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append("<if test=\"@tk.mybatis.mapper.util.OGNL@hasSelectColumns(_parameter)\">");
        sql.append("<foreach collection=\"_parameter.selectColumns\" item=\"selectColumn\" separator=\",\">");
        sql.append("${selectColumn}");
        sql.append("</foreach>");
        sql.append("</if>");
        //不支持指定列的时候查询全部列
        sql.append("<if test=\"@tk.mybatis.mapper.util.OGNL@hasNoSelectColumns(_parameter)\">");
        sql.append(" * ");
        sql.append("</if>");
        return sql.toString();
    }
    public String selectByMap(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append(whereColumnsNotEmpty());
        return sql.toString();
    }

    public String selectByKey(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append("where ${key}= #{value}");
        return sql.toString();
    }

    public String deleteByIdBatch(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, this.tableName(entityClass)));
        sql.append(wherePKColumnsIn(entityClass));
        return sql.toString();
    }

    public String selectByIdBatch(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append(wherePKColumnsIn(entityClass));
        return sql.toString();
    }

    private String wherePKColumnsIn(Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        Set<EntityColumn> columnList = EntityHelper.getPKColumns(entityClass);
        if (columnList.size() == 1) {
            EntityColumn column = columnList.iterator().next();
            sql.append(" where ");
            sql.append(column.getColumn()).append(" in ");
            sql.append("<foreach close=\")\" collection=\"ids\" item=\"item\" open=\"(\" separator=\",\">\n");
            sql.append("                #{item}\n");
            sql.append("              </foreach>");
            return sql.toString();
        } else {
            throw new RuntimeException("继承 selectByIds 方法的实体类[" + entityClass.getCanonicalName() + "]中必须只有一个带有 @Id 注解的字段");
        }
    }

    private String whereColumnsNotEmpty() {
        StringBuilder sql = new StringBuilder();
        sql.append("where 1=1");
        sql.append("<foreach collection=\"params\" index=\"key\" item=\"item\">\n");
        sql.append("        and ${key}=#{item}\n");
        sql.append("</foreach>");
        return sql.toString();
    }
    
    
}
