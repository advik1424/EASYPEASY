package com.example.CRUDSPRINGBOOTDEMO.controller;


import com.example.CRUDSPRINGBOOTDEMO.DTO.StudentRequestDTO;
import com.example.CRUDSPRINGBOOTDEMO.DTO.Studentresponsedto;
import com.example.CRUDSPRINGBOOTDEMO.DTO.Updaterequestdto;
import com.example.CRUDSPRINGBOOTDEMO.DTO.Updateresponsedto;
import com.example.CRUDSPRINGBOOTDEMO.entity.Student;
import com.example.CRUDSPRINGBOOTDEMO.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")


public class StudentController {


    private StudentService studentService;

    public StudentController(StudentService studentService){

        this.studentService=studentService;

    }


    @PostMapping
    public  ResponseEntity<Studentresponsedto> createstudent(@Valid @RequestBody StudentRequestDTO studentrequestdto){

     Studentresponsedto createdStudent= studentService.createdStudent(studentrequestdto);



        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);

    }

@GetMapping("/{id}")
    public ResponseEntity<Studentresponsedto> getStudent(@PathVariable Long id){

        Studentresponsedto studentres=studentService.getStudent(id);


        if(studentres==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }

return ResponseEntity.status(HttpStatus.OK).body(studentres);


    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent(){

        List<Student> studentlist=studentService.getAllStudent();


        if(studentlist.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }

        return ResponseEntity.status(HttpStatus.OK).body(studentlist);


    }

    @PutMapping("/update")
    public ResponseEntity<Updateresponsedto> updateStudent(@RequestParam Long id, @RequestBody Updaterequestdto updatestudentrq){

        Updateresponsedto studentupdate=studentService.updateStudent(id,updatestudentrq);


//        if(studentupdate==null){
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//
//        }

        return ResponseEntity.status(HttpStatus.OK).body(studentupdate);


    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam long id){
//
//       Boolean isDeleted =studentService.deleteStudent(id);

//        if(!isDeleted){
//
//            return ResponseEntity.notFound().build();
//
//        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

    @DeleteMapping("/delete/all")

    public ResponseEntity<String> deleteAllStudent(){

        Boolean isalldeleted=studentService.deleteAllStudent();

        if(!isalldeleted){

            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok("Record Deleted");

    }


    @PatchMapping("/soft-delete")
    public ResponseEntity<String> softdelete(@RequestParam Long id){

                  studentService.softdelete(id);


//        if(!isdeleted){
//
//            return ResponseEntity.notFound().build();
//
//        }
        return ResponseEntity.noContent().build();




    }
}
