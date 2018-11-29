package com.boot.derby.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.derby.dao.StudentDao;
import com.boot.derby.entity.Student;
import com.boot.derby.request.StudentRequest;
import com.boot.derby.response.StudentResponse;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;

	public StudentResponse createStudent(StudentRequest studentRequest) {
		StudentResponse studentResponse = new StudentResponse();
		if(studentRequest != null) {
			Student student = getStudentEntiry(studentRequest);
			studentResponse = getStudentResponse(studentDao.save(student));
		}
		return studentResponse;
	}

	public List<StudentResponse> getAllStudents() {
		List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
		List<Student> students = (List<Student>) studentDao.findAll();
		if(students != null) {
			for (Student student : students) {
				StudentResponse studentResponse = getStudentResponse(student);
				studentResponses.add(studentResponse);
			}
		}
		return studentResponses;
	}

	public StudentResponse getStudent(Integer id) {
		StudentResponse studentResponse = new StudentResponse();
		if(id != null) {
			Optional<Student> student = studentDao.findById(id);
			if(student.get() != null) {
				studentResponse = getStudentResponse(student.get());
			}
		}
		return studentResponse;
	}

	public StudentResponse updateStudent(Integer id, StudentRequest studentRequest) {
		StudentResponse studentResponse = new StudentResponse();
		if(id != null) {
			Optional<Student> student = studentDao.findById(id);
			if(student.get() != null) {
				if(studentRequest.getFirstName() != null){
					student.get().setFirstName(studentRequest.getFirstName());
				}
				if(studentRequest.getLastName() != null){
					student.get().setLastName(studentRequest.getLastName());
				}
				if(studentRequest.getMobile() != null){
					student.get().setMobile(studentRequest.getMobile());
				}
				if(studentRequest.getEmail() != null){
					student.get().setEmail(studentRequest.getEmail());
				}
				studentResponse = getStudentResponse(studentDao.save(student.get()));
			}
		}
		return studentResponse;
	}

	public StudentResponse deleteStudent(Integer id) {
		StudentResponse studentResponse = new StudentResponse();
		if(id != null) {
			Optional<Student> student = studentDao.findById(id);
			if(student.get() != null) {
				studentDao.delete(student.get());
				studentResponse.setMessage("Student deleted successfully");
			}
		}
		return studentResponse;
	}
	
	private Student getStudentEntiry(StudentRequest studentRequest) {
		Student student = new Student();
		student.setFirstName(studentRequest.getFirstName());
		student.setLastName(studentRequest.getLastName());
		student.setMobile(studentRequest.getMobile());
		student.setEmail(studentRequest.getEmail());
		return student;
	}
	
	private StudentResponse getStudentResponse(Student student) {
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setId(student.getId());
		studentResponse.setFirstName(student.getFirstName());
		studentResponse.setLastName(student.getLastName());
		studentResponse.setMobile(student.getMobile());
		studentResponse.setEmail(student.getEmail());
		return studentResponse;
	}

}
