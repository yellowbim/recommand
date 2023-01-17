package jjuni.pjt.RECOMMAND.health;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class HealthCheck {

    @ResponseBody
    @RequestMapping("/health")
    public String healthCheck() {
        log.info("하이1");
        log.info("하이2");
        return "살아있다!!";
    }

}
