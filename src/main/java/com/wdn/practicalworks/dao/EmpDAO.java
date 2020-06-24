package com.wdn.practicalworks.dao;

import com.wdn.practicalworks.models.pr3.Emp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpDAO implements DAO {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Emp> findAll() {
        String sql="select * from TESTUSER.EMP";
        return template.query(sql,new RowMapper<Emp>(){
            public Emp mapRow(ResultSet rs, int row) throws SQLException {
                Emp e = new Emp();
                e.setEmpno(rs.getInt(1));
                e.setEname(rs.getString(2));
                e.setJob(rs.getString(3));
                e.setDeptno(rs.getInt(8));
                return e;
            }
        });
    }

    @Override
    public List<Emp> findById() {
        return null;
    }

    @Override
    public boolean insertEmp(Emp emp) {
        return false;
    }

    @Override
    public boolean updateEmp(Emp emp) {
        return false;
    }

    @Override
    public boolean deleteEmp(Emp emp) {
        return false;
    }
}
