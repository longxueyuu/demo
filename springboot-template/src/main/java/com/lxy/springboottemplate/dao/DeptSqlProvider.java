package com.lxy.springboottemplate.dao;

import com.lxy.springboottemplate.domain.Dept;
import org.apache.ibatis.jdbc.SQL;

public class DeptSqlProvider {

    public String insertSelective(Dept record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("dept");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Dept record) {
        SQL sql = new SQL();
        sql.UPDATE("dept");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}