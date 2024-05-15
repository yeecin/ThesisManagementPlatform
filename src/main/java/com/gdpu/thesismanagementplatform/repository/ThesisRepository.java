package com.gdpu.thesismanagementplatform.repository;

import com.gdpu.thesismanagementplatform.pojo.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gdpu.thesismanagementplatform.pojo.Thesis;
import java.util.List;
import java.util.Optional;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    List<Thesis> findByTitleContaining(String keyword);
    Optional<Thesis> findByUrl(String url);
}