package com.example.CRUDSPRINGBOOTDEMO.service;

import com.example.CRUDSPRINGBOOTDEMO.DTO.StudentRequestDTO;
import com.example.CRUDSPRINGBOOTDEMO.DTO.Studentresponsedto;
import com.example.CRUDSPRINGBOOTDEMO.DTO.Updaterequestdto;
import com.example.CRUDSPRINGBOOTDEMO.DTO.Updateresponsedto;
import com.example.CRUDSPRINGBOOTDEMO.entity.Student;
import com.example.CRUDSPRINGBOOTDEMO.exception.Duplicateresourseexception;
import com.example.CRUDSPRINGBOOTDEMO.exception.Resourcenotfoundexception;
import com.example.CRUDSPRINGBOOTDEMO.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){

        this.studentRepository=studentRepository;

    }


    public Studentresponsedto createdStudent(StudentRequestDTO studentRequestDTO){

       Student student=mapToEntity(studentRequestDTO);

       if(emailExists(student)){
           throw new Duplicateresourseexception("Student with this email "+student.getEmail()+" is already exist");

       }
       Student studentresp=studentRepository.save(student);

       return maptodto(studentresp);






    }
    public Studentresponsedto getStudent(Long id){

      Student studentrespp =studentRepository.findByIdAndIsdeletedFalse(id).orElseThrow(()->new Resourcenotfoundexception("Student with the "+id+"not found"));

//      if(studentresponse.isPresent()){
//
//          return studentresponse.get();
//
//      }
//      return null;

//        return maptodto(studentresponse.get());

        return maptodto(studentrespp);




    }

    public List<Student> getAllStudent()
    {

        List<Student> sl=studentRepository.findByIsdeletedFalse();

        return sl;

    }


    public  Updateresponsedto updateStudent(Long id, Updaterequestdto studentreq){

        Student studenttosave= studentRepository.findByIdAndIsdeletedFalse(id).orElseThrow(()-> new Resourcenotfoundexception("Student with this id "+id+"is not exist in the database"));

//        if(studentexisting.isEmpty()){
//
//            return null;
//
//        }

        studenttosave.setAge(studentreq.getAge());

        studenttosave.setName(studentreq.getName());
        studenttosave.setSubject(studentreq.getSubject());
        studenttosave.setIsdeleted(false);
        studenttosave.setUpdated_at(LocalDateTime.now());


         Student savestudent= studentRepository.save(studenttosave);


         return  maptoupdatedto(savestudent);







    }

    public void deleteStudent(Long id){


       Student isstudenttobedelete= studentRepository.findById(id).orElseThrow(()->new Resourcenotfoundexception("Stuent with this id:"+id+"is not exist in the database"));


//       if(!isstudent){
//
//           return false;
//
//       }
       studentRepository.delete(isstudenttobedelete);




    }

    public Boolean deleteAllStudent(){

        if (studentRepository.count() == 0) {
            return false;
        }
        studentRepository.deleteAll();

        return true;


    }

    public void softdelete(Long id){

//       Optional<Student> existingstudent= studentRepository.findByIdAndIsdeletedFalse(id);
//
//       if(existingstudent.isEmpty()){
//           return false;
//
//
//       }
        Student studenttosoftdelete= studentRepository.findByIdAndIsdeletedFalse(id).orElseThrow(()->new Resourcenotfoundexception("Stuent with this id:"+id+"is not exist in the database"));




        studenttosoftdelete.setIsdeleted(true);
        studentRepository.save(studenttosoftdelete);


    }


    //end point listen
    // business logic
    //
     //intereact with db
    // response to call back

    private Student mapToEntity(StudentRequestDTO studentreqdto){

        Student student=new Student();
        student.setName(studentreqdto.getName());
        student.setAge(studentreqdto.getAge());
        student.setEmail(studentreqdto.getEmail());
        student.setBranch(studentreqdto.getBranch());
        student.setSubject(studentreqdto.getSubject());
        student.setRoll_no(studentreqdto.getRoll_no());
        student.setYear(studentreqdto.getYear());
        student.setCreated_at(LocalDateTime.now());
        student.setUpdated_at(LocalDateTime.now());

        student.setIsdeleted(false);

        return student;

    }
    private Studentresponsedto maptodto(Student student){

        Studentresponsedto responsedto=new Studentresponsedto();

        responsedto.setAge(student.getAge());
        responsedto.setBranch(student.getBranch());
        responsedto.setEmail(student.getEmail());
        responsedto.setName(student.getName());
        responsedto.setRoll_no(student.getRoll_no());
        responsedto.setSubject(student.getSubject());
        responsedto.setYear(student.getYear());
        responsedto.setMessage("Student Saved Successfully");
        responsedto.setCreated_at(student.getCreated_at());
        responsedto.setUpdated_at(student.getUpdated_at());

        return responsedto;



    }

    private Updateresponsedto maptoupdatedto(Student student){
        Updateresponsedto responsedto=new Updateresponsedto();

        responsedto.setAge(student.getAge());
        responsedto.setBranch(student.getBranch());
        responsedto.setEmail(student.getEmail());
        responsedto.setName(student.getName());
        responsedto.setRoll_no(student.getRoll_no());
        responsedto.setSubject(student.getSubject());
        responsedto.setYear(student.getYear());

        responsedto.setUpdated_at(student.getUpdated_at());

        return responsedto;


    }

    private boolean emailExists(Student student){

        return studentRepository.existsByEmail(student.getEmail());



    }


}
