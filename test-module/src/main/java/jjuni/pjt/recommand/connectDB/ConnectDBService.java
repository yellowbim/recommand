package jjuni.pjt.recommand.connectDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteById(Integer mbrNo) {
        connectDBRepository.deleteById(mbrNo);
    }

    public SdkSmsSend save(SdkSmsSend member) {
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
