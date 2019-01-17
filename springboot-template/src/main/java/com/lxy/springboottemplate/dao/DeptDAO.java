package com.lxy.springboottemplate.dao;

import com.lxy.springboottemplate.domain.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface DeptDAO {
    @Delete({
        "delete from dept",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into dept (id, name)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
    })
    int insert(Dept record);

    @InsertProvider(type=DeptSqlProvider.class, method="insertSelective")
    int insertSelective(Dept record);

    @Select({
        "select",
        "id, name",
        "from dept",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Dept selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DeptSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Dept record);

    @Update({
        "update dept",
        "set name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Dept record);
}