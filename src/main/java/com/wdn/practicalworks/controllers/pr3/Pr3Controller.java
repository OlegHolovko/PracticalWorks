package com.wdn.practicalworks.controllers.pr3;

import com.wdn.practicalworks.config.ConnectionInit3;
import com.wdn.practicalworks.dao.EmpMapper;
import com.wdn.practicalworks.models.pr3.Emp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Pr3Controller {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(ConnectionInit3.class);
    private Connection conn = ctx.getBean(Connection.class);


    @RequestMapping(value = "/pr3/list", method = { RequestMethod.GET })
    public String list(Model model) throws SQLException {
        ArrayList<Emp> emps = new ArrayList<>();
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from TESTUSER.EMP");
        while (rs.next()) {
            Emp emp = new Emp();
            emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setJob(rs.getString("job"));
            emp.setMgr(rs.getInt("mgr"));
            emp.setHiredate(rs.getDate("hiredate"));
            emp.setSal(rs.getInt("sal"));
            emp.setComm(rs.getInt("comm"));
            emp.setDeptno(rs.getInt("deptno"));
            emps.add(emp);
        }
        model.addAttribute("emps", emps);
        model.addAttribute("hasParent", "3");
        model.addAttribute("activeMenu", "pr3_list");
        return "pr3/list";
    }

    @RequestMapping(value = "/pr3/edit/{id}", method = { RequestMethod.GET })
    public String edit(@PathVariable("id") int id, Model model) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from TESTUSER.EMP WHERE EMPNO = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            Map<String, String> emp = new LinkedHashMap<>();
            emp.put("empno", rs.getString("empno"));
            emp.put("ename", rs.getString("ename"));
            emp.put("job", rs.getString("job"));
            emp.put("mgr", rs.getString("mgr"));
            emp.put("hiredate", rs.getString("hiredate"));
            emp.put("sal", rs.getString("sal"));
            emp.put("comm", rs.getString("comm"));
            emp.put("deptno", rs.getString("deptno"));
            model.addAttribute("emp", emp);
        }
        model.addAttribute("hasParent", "3");
        model.addAttribute("activeMenu", "pr3_list");
        return "pr3/edit";
    }

    @RequestMapping(value = "/pr3/view/{id}", method = { RequestMethod.GET })
    public String view(@PathVariable("id") int id, Model model) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from TESTUSER.EMP WHERE EMPNO = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            Map<String, String> emp = new LinkedHashMap<>();
            emp.put("empno", rs.getString("empno"));
            emp.put("ename", rs.getString("ename"));
            emp.put("job", rs.getString("job"));
            emp.put("mgr", rs.getString("mgr"));
            emp.put("hiredate", rs.getString("hiredate"));
            emp.put("sal", rs.getString("sal"));
            emp.put("comm", rs.getString("comm"));
            emp.put("deptno", rs.getString("deptno"));
            model.addAttribute("emp", emp);
        }
        model.addAttribute("hasParent", "3");
        model.addAttribute("activeMenu", "pr3_list");
        return "pr3/view";
    }
}
