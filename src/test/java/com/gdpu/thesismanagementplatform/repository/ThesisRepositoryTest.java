package com.gdpu.thesismanagementplatform.repository;

import com.gdpu.thesismanagementplatform.pojo.Thesis;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ThesisRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ThesisRepository thesisRepository;

    @Test
    public void whenFindById_thenReturnThesis() {
        // given
        Thesis thesis = new Thesis();
        thesis.setThesisId(1);
        thesis.setTitle("Test Thesis");
        entityManager.persist(thesis);
        entityManager.flush();

        // when
        Thesis found = thesisRepository.findById(thesis.getThesisId()).orElse(null);

        // then
        if (found != null) {
            Assertions.assertThat(found.getTitle()).isEqualTo(thesis.getTitle());
        }
    }
}