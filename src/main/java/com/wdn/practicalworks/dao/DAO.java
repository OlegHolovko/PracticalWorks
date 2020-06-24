package com.wdn.practicalworks.dao;

import com.wdn.practicalworks.models.pr3.Emp;
import java.util.List;

interface DAO {

    List<Emp> findAll();
    List<Emp> findById();
    boolean insertEmp(Emp emp);
    boolean updateEmp(Emp emp);
    boolean deleteEmp(Emp emp);

}
