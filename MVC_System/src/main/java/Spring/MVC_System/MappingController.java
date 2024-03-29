package Spring.MVC_System;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {


    private Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/hello")
    public String hello(){
        log.info("hello");
        return "ok";
    }
}
