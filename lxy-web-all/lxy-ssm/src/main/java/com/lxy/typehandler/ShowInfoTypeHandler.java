package com.lxy.typehandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.lxy.domain.TestShowInfo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(TestShowInfo.class)
public class ShowInfoTypeHandler extends BaseTypeHandler<TestShowInfo> {

    private Class<?> showInfoClazz;

    public ShowInfoTypeHandler(Class<?> ShowInfoClazz) {
        this.showInfoClazz = ShowInfoClazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, TestShowInfo showInfo, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(showInfo));
    }

    @Override
    public TestShowInfo getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JSON.parseObject(resultSet.getString(s), TestShowInfo.class, Feature.InitStringFieldAsEmpty);
    }

    @Override
    public TestShowInfo getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSON.parseObject(resultSet.getString(i), TestShowInfo.class);
    }

    @Override
    public TestShowInfo getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JSON.parseObject(callableStatement.getString(i), TestShowInfo.class);
    }
}
