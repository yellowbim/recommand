package jjuni.pjt.recommend.connectDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ConnectDBController {

    @Autowired
    private ConnectDBService connectDBService;

    // 모든 회원 조회
//    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @RequestMapping("/findAll")
    public ResponseEntity<List<SdkSmsSend>> getAllmembers() {
        List<SdkSmsSend> sends = connectDBService.findAll();
        return new ResponseEntity<List<SdkSmsSend>>(sends, HttpStatus.OK);
    }

    // userId로 검색(like)
    @GetMapping(value = "/test/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<SdkSmsSend>> getUserId(@PathVariable("userId") String userId) {
        Optional<List<SdkSmsSend>> member = connectDBService.findByUserIdLike(userId);
        return new ResponseEntity<List<SdkSmsSend>>(member.get(), HttpStatus.OK);
    }

    // 회원번호로 한명의 회원 조회
    @GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<SdkSmsSend> getMember(@PathVariable("mbrNo") Integer mbrNo) {
        Optional<SdkSmsSend> member = connectDBService.findById(mbrNo);
        return new ResponseEntity<SdkSmsSend>(member.get(), HttpStatus.OK);
    }

    // 회원번호로 회원 삭제
    @DeleteMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteMember(@PathVariable("mbrNo") List<Integer> mbrNo) {
        connectDBService.deleteByMsgIdIn(mbrNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 회원번호로 회원 수정(mbrNo로 회원을 찾아 Member 객체의 id, name로 수정함)
//    @PutMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<SdkSmsSend> updateMember(@PathVariable("mbrNo") Long mbrNo, SdkSmsSend member) {
//        connectDBService.updateById(mbrNo, member);
//        return new ResponseEntity<SdkSmsSend>(member, HttpStatus.OK);
//    }

    // 회원 입력
    @PostMapping
    public ResponseEntity<SdkSmsSend> save(SdkSmsSend member) {
        return new ResponseEntity<SdkSmsSend>(connectDBService.save(member), HttpStatus.OK);
    }
//
//    // 회원 입력
//    @RequestMapping(value="/saveMember", method = RequestMethod.GET)
//    public ResponseEntity<SdkSmsSend> save(HttpServletRequest req, SdkSmsSend member){
//        return new ResponseEntity<SdkSmsSend>(connectDBService.save(member), HttpStatus.OK);
//    }

}
