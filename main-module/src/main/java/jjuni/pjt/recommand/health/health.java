package jjuni.pjt.recommand.health;

import jjuni.pjt.recommand.common.model.TestVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class health {

    @ResponseBody
    @RequestMapping("/health")
    public String main(){
        TestVO testVO = new TestVO();
        testVO.setTest("ggggg");
        System.out.println(testVO);
        System.out.println("gd");
        return "health";
    }

    @RequestMapping("/test")
    public ResponseEntity<Map> test() {
        Map<String,String> map = new HashMap<>();
        map.put("test", "하이하이");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
