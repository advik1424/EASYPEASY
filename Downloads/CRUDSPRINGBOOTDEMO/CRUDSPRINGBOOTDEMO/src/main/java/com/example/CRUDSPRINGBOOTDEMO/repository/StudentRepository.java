package com.example.CRUDSPRINGBOOTDEMO.repository;

import com.example.CRUDSPRINGBOOTDEMO.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Optional;

//@Repository


public interface StudentRepository extends JpaRepository<Student,Long> {


    Optional<Student> findByIdAndIsdeletedFalse(Long id);

    List<Student> findByIsdeletedFalse();

    boolean existsByEmail(String email);;


}

