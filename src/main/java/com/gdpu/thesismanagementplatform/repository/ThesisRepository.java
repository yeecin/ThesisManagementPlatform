package com.gdpu.thesismanagementplatform.repository;

import com.gdpu.thesismanagementplatform.pojo.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThesisRepository extends JpaRepository<Thesis, Integer> {

    List<Thesis> findAllByTeacherId(int teacherId);
}