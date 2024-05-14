package com.gdpu.thesismanagementplatform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/announcement")
    public String announcement() {
        return "announcement";
    }
    @RequestMapping("/thesis-topic")
    public String thesisTopic() {
        return "thesis-topic";
    }
}
