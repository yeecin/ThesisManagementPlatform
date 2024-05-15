package com.gdpu.thesismanagementplatform.Controller;
import com.gdpu.thesismanagementplatform.pojo.Thesis;
import com.gdpu.thesismanagementplatform.repository.ThesisRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ThesisController {

    @Autowired
    private ThesisRepository thesisRepository;

    @PostMapping("/createThesis")
    public Thesis createThesis(@RequestBody Thesis newThesis) {
        return thesisRepository.save(newThesis);
    }
}