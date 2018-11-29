package com.boot.derby.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.derby.request.StudentRequest;
import com.boot.derby.response.StudentResponse;
import com.boot.derby.service.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public StudentResponse createStudent(@RequestBody StudentRequest studentRequest) {
		return studentService.createStudent(studentRequest);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<StudentResponse> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public StudentResponse getStudent(@PathVariable Integer id) {
		return studentService.getStudent(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public StudentResponse updateStudent(@PathVariable Integer id, @RequestBody StudentRequest studentRequest) {
		return studentService.updateStudent(id, studentRequest);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public StudentResponse deleteStudent(@PathVariable Integer id) {
		return studentService.deleteStudent(id);
	}

}
