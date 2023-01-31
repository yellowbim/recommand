package jjuni.pjt.recommend.connectDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectDBService {

    @Autowired
    private ConnectDBRepository connectDBRepository;

    public List<SdkSmsSend> findAll() {
        List<SdkSmsSend> sends = new ArrayList<>();
        connectDBRepository.findAll().forEach(e -> sends.add(e));
        return sends;
    }

    public Optional<SdkSmsSend> findById(Integer mbrNo) {
        Optional<SdkSmsSend> member = connectDBRepository.findById(mbrNo);
        return member;
    }

    public Optional<List<SdkSmsSend>> findByUserIdLike(String mbrNo) {
        Optional<List<SdkSmsSend>> member = connectDBRepository.findByUserIdContaining(mbrNo);
        return member;
    }

    // select를 한 후에 delete를 한번씩 호출;;; 결과 겁나느림
    // @Query로 처리해야할듯함....
    public void deleteByMsgIdIn(List<Integer> mbrNo) {
        System.out.println("삭제할 id 리스트 : " + mbrNo.toString());
        connectDBRepository.deleteAllById(mbrNo);
    }

    public SdkSmsSend save(SdkSmsSend member) {
        // 날짜 변환 - yyyyMMddHHmmss
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String repTime = now.format(formatter);

        member.setSendDate(repTime);
        System.out.println("memberrrrrrrrrrrrrrrrrrrrrr " + member.toString());
        connectDBRepository.save(member);
        return member;
    }

    public void updateById(Long mbrNo, SdkSmsSend member) {
//        Optional<SdkSmsSend> e = connectDBRepository.findById(mbrNo);

//        if (e.isPresent()) {
//            e.get().setMbrNo(member.getMbrNo());
//            e.get().setId(member.getId());
//            e.get().setName(member.getName());
//            memberRepository.save(member);
//        }
    }



}
