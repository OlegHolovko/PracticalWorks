package com.wdn.practicalworks.controllers.pr2;

import com.wdn.practicalworks.config.ConnectionInit2;
import com.wdn.practicalworks.config.ConnectionInit3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.*;

@RestController
public class QueryController {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(ConnectionInit2.class);
    private Connection con = ctx.getBean(Connection.class);

    @RequestMapping(value = "/pr2/query1", method = { RequestMethod.POST })
    public ArrayList<Map> query1() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from TESTUSER.DEPT");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("DEPTNO", rs.getObject("DEPTNO"));
            map.put("DNAME", rs.getObject("DNAME"));
            map.put("LOC", rs.getObject("LOC"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query2", method = { RequestMethod.POST })
    public ArrayList<Map> query2() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT (ENAME || ',' || JOB) as NAME_JOB, (SAL*12) as YEAR_SAL from TESTUSER.EMP");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("NAME_JOB", rs.getObject("NAME_JOB"));
            map.put("YEAR_SAL", rs.getObject("YEAR_SAL"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query3", method = { RequestMethod.POST })
    public ArrayList<Map> query3() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT ENAME, SAL from TESTUSER.EMP WHERE SAL NOT BETWEEN 1500 AND 2800 ORDER BY ENAME ASC");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("ENAME", rs.getObject("ENAME"));
            map.put("SAL", rs.getObject("SAL"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query4", method = { RequestMethod.POST })
    public ArrayList<Map> query4() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT ENAME, ROUND(MONTHS_BETWEEN(current_date,HIREDATE)) AS MONTHES from TESTUSER.EMP ORDER BY HIREDATE ASC");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("ENAME", rs.getObject("ENAME"));
            map.put("MONTHES", rs.getObject("MONTHES"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query5", method = { RequestMethod.POST })
    public ArrayList<Map> query5() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT ENAME AS NAME, NVL(TO_CHAR(COMM), 'NO COMMISSIONS') as COMMISSIONS from TESTUSER.EMP");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("ENAME", rs.getObject("NAME"));
            map.put("COMMISSIONS", rs.getObject("COMMISSIONS"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query6", method = { RequestMethod.POST })
    public ArrayList<Map> query6() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT DISTINCT JOB from TESTUSER.EMP WHERE DEPTNO='30'");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("JOB", rs.getObject("JOB"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query7", method = { RequestMethod.POST })
    public ArrayList<Map> query7() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT E.ENAME AS NAME, E.JOB AS JOB, E.DEPTNO AS DEPTNO from TESTUSER.EMP E INNER JOIN TESTUSER.DEPT D ON E.DEPTNO = D.DEPTNO WHERE D.LOC = 'DALLAS'");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("NAME", rs.getObject("NAME"));
            map.put("JOB", rs.getObject("JOB"));
            map.put("DEPTNO", rs.getObject("DEPTNO"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query8", method = { RequestMethod.POST })
    public ArrayList<Map> query8() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT E.ENAME AS EMPNAME, E.EMPNO AS EMPNO, E2.ENAME AS MANAGERNAME, E2.EMPNO AS MANAGERNO from TESTUSER.EMP E LEFT JOIN TESTUSER.EMP E2 ON E.MGR = E2.EMPNO");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("EMPNAME", rs.getObject("EMPNAME"));
            map.put("EMPNO", rs.getObject("EMPNO"));
            map.put("MANAGERNAME", rs.getObject("MANAGERNAME"));
            map.put("MANAGERNO", rs.getObject("MANAGERNO"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query9", method = { RequestMethod.POST })
    public ArrayList<Map> query9() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT E.ENAME AS ENAME, E.DEPTNO AS DEPTNO, E.SAL AS SAL, (SELECT GRADE FROM TESTUSER.SALGRADE WHERE E.SAL BETWEEN MINSAL AND HISAL) AS GRADE from TESTUSER.EMP E");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("ENAME", rs.getObject("ENAME"));
            map.put("DEPTNO", rs.getObject("DEPTNO"));
            map.put("SAL", rs.getObject("SAL"));
            map.put("GRADE", rs.getObject("GRADE"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query10", method = { RequestMethod.POST })
    public ArrayList<Map> query10() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT MIN(E.SAL) AS MINSAL, MAX(E.SAL) AS MAXSAL, AVG(E.SAL) AS AVGSAL, SUM(E.SAL) AS SUMSAL  FROM TESTUSER.EMP E");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("MINSAL", rs.getObject("MINSAL"));
            map.put("MAXSAL", rs.getObject("MAXSAL"));
            map.put("AVGSAL", rs.getObject("AVGSAL"));
            map.put("SUMSAL", rs.getObject("SUMSAL"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query11", method = { RequestMethod.POST })
    public ArrayList<Map> query11() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT E1.EMPNO AS EMPNO, MAX(E2.SAL) AS SALARY  FROM TESTUSER.EMP E1 JOIN TESTUSER.EMP E2 ON E1.EMPNO = E2.MGR GROUP BY E1.EMPNO HAVING MAX(E2.SAL)>=1000");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("EMPNO", rs.getObject("EMPNO"));
            map.put("SALARY", rs.getObject("SALARY"));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/pr2/query12", method = { RequestMethod.POST })
    public ArrayList<Map> query12() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT DEPTNO, ENAME FROM TESTUSER.EMP WHERE SAL>(SELECT AVG(SAL) from TESTUSER.EMP)");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("DEPTNO", rs.getObject("DEPTNO"));
            map.put("ENAME", rs.getObject("ENAME"));
            result.add(map);
        }
        return result;
    }


    @RequestMapping(value = "/pr2/query13", method = { RequestMethod.POST })
    public ArrayList<Map> query13() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT DISTINCT E1.ENAME AS ENAME, E1.DEPTNO AS DEPTNO, E1.SAL AS SAL FROM TESTUSER.EMP E1 JOIN TESTUSER.EMP E2 ON E1.SAL = E2.SAL AND E1.DEPTNO = E2.DEPTNO AND E1.ENAME <> E2.ENAME  WHERE  E1.COMM IS NOT NULL ");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("ENAME", rs.getObject("ENAME"));
            map.put("DEPTNO", rs.getObject("DEPTNO"));
            map.put("SAL", rs.getObject("SAL"));
            result.add(map);
        }
        return result;
    }


    @RequestMapping(value = "/pr2/query14", method = { RequestMethod.POST })
    public ArrayList<Map> query14() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT EMPNO, ENAME, JOB, DEPTNO, SAL FROM TESTUSER.EMP WHERE SAL>(SELECT MAX(SAL) from TESTUSER.EMP WHERE JOB = 'CLERK')");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("EMPNO", rs.getObject("EMPNO"));
            map.put("ENAME", rs.getObject("ENAME"));
            map.put("JOB", rs.getObject("JOB"));
            map.put("DEPTNO", rs.getObject("DEPTNO"));
            map.put("SAL", rs.getObject("SAL"));
            result.add(map);
        }
        return result;
    }


    @RequestMapping(value = "/pr2/query15", method = { RequestMethod.POST })
    public ArrayList<Map> query15() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT (lpad(' ', 5*(level-1))|| ENAME) AS ENAME, (lpad(' ', 5*(level-1))|| JOB) AS JOB FROM TESTUSER.EMP START WITH MGR IS NULL CONNECT BY PRIOR EMPNO = MGR");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("ENAME", rs.getObject("ENAME"));
            map.put("JOB", rs.getObject("JOB"));
            result.add(map);
        }
        return result;
    }


    @RequestMapping(value = "/pr2/query16", method = { RequestMethod.POST })
    public ArrayList<Map> query16() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT (lpad(' ', 5*(level-1))|| ENAME) AS EMPNAME, (lpad(' ', 5*(level-1))|| JOB) AS JOB, (lpad(' ', 5*(level-1))|| SAL) AS SAL FROM TESTUSER.EMP WHERE SAL>(SELECT AVG(SAL) from TESTUSER.EMP) START WITH MGR IS NULL CONNECT BY PRIOR EMPNO = MGR ORDER SIBLINGS BY ENAME");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("ENAME", rs.getObject("EMPNAME"));
            map.put("JOB", rs.getObject("JOB"));
            map.put("SAL", rs.getObject("SAL"));
            result.add(map);
        }
        return result;
    }


    @RequestMapping(value = "/pr2/query17", method = { RequestMethod.POST })
    public ArrayList<Map> query17() throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT SYS_CONNECT_BY_PATH(ENAME, '/') AS EMPNAME FROM TESTUSER.EMP START WITH EMPNO = '7566' CONNECT BY PRIOR EMPNO = MGR");
        while (rs.next()) {
            Map<String, Object> map= new LinkedHashMap<>();
            map.put("EMPNAME", rs.getObject("EMPNAME"));
            //map.put("SALARY", rs.getObject("SALARY"));
            result.add(map);
        }
        return result;
    }
}
