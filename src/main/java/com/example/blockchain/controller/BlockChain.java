package com.example.blockchain.controller;

import com.example.blockchain.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class BlockChain {
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "John", "Rukundo");
        // return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok().header("Custom-Header", "spring boot").body(student);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getString() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Rukundo"));
        students.add(new Student(2, "Keza", "Jessica"));
        students.add(new Student(3, "Kamali", "James"));
        students.add(new Student(4, "Kalisa", "Jay"));
        students.add(new Student(5, "Enock", "Joe"));
        return ResponseEntity.ok(students);

    }

    // spring boot rest api with path variable
    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // spring boot rest api with request
    // localhost:8080/students/query?id=1&firstName=John&lastName=Rukundo
    @GetMapping("/query")
    public ResponseEntity<Student> getStudentId(@RequestParam int id, @RequestParam String firstName,
            @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    /*
     * Spring Boot Rest API with that handle Post Request - Create new resource
     *
     * @RequestBody and @PostMapping
     * localhost:8080/students/create
     */

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    /*
     * Spring Boot Rest API with that handle Put Request-Update existing resource
     */
    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    /*
     * Spring Boot Rest API with that handle Delete Request-Delete existing resource
     * localhost:8080/students/1/delete
     */
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println("Student with id " + studentId + " deleted successfully");
        return ResponseEntity.ok("Student with id " + studentId + " deleted successfully");
    }
}
