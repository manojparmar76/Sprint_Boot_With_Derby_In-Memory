package com.boot.derby.dao;

import org.springframework.data.repository.CrudRepository;

import com.boot.derby.entity.Student;

public interface StudentDao extends CrudRepository<Student, Integer>{

}
