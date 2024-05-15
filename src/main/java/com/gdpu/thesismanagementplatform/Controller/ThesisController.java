package com.gdpu.thesismanagementplatform.Controller;

import com.gdpu.thesismanagementplatform.exception.ResourceNotFoundException;
import com.gdpu.thesismanagementplatform.pojo.Thesis;
import com.gdpu.thesismanagementplatform.repository.ThesisRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
    public class ThesisController {

        @Autowired
        private ThesisRepository thesisRepository;

        @GetMapping("/getThesisDetail/{id}")
        public String getThesisDetail(@PathVariable Long id) {
            // 从数据库中获取论文的详细信息
            Optional<Thesis> thesis = thesisRepository.findById(id);

            // 如果找到了论文，就返回论文的详细信息
            // 否则，返回一个错误消息（你可能需要创建一个新的异常类来处理这种情况）
            return thesis.map(Thesis::getStatus)
                    .orElseThrow(() -> new ResourceNotFoundException("Thesis not found with id " + id));
        }

        @PostMapping("/createThesis")
        public Thesis createThesis(@RequestBody Thesis newThesis) {
            return thesisRepository.save(newThesis);
        }

        @GetMapping("/getThesis")
        public List<Thesis> getThesisList() {
            return thesisRepository.findAll();
        }

        @GetMapping("/searchThesis")
        public List<Thesis> searchThesis(@RequestParam String keyword) {
            // 这里需要实现你的搜索逻辑，以下是一个基本的示例
            return thesisRepository.findByTitleContaining(keyword);
        }
    }


