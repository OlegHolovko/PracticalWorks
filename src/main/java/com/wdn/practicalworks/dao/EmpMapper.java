package com.wdn.practicalworks.dao;

import com.wdn.practicalworks.models.pr3.Emp;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpMapper implements RowMapper<Emp> {
    public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
        Emp emp = new Emp();
        emp.setEmpno(rs.getInt("empno"));
        emp.setEname(rs.getString("ename"));
        emp.setJob(rs.getString("job"));
        emp.setMgr(rs.getInt("mgr"));
        emp.setHiredate(rs.getDate("hiredate"));
        emp.setSal(rs.getInt("sal"));
        emp.setComm(rs.getInt("comm"));
        emp.setDeptno(rs.getInt("deptno"));

        return emp;
    }
}
