package jjuni.pjt.recommend.connectDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectDBRepository extends JpaRepository<SdkSmsSend, Integer> {

    public Optional<SdkSmsSend> findById(Integer id);

    // like 검색어
    public Optional<List<SdkSmsSend>> findByUserIdContaining(String name);

//    @Query(value="DELETE FROM ${dbSchema}.sdk_sms_send WHERE MSG_ID IN ()")
//    public void deleteByMsgIdIn(@Param("param")Map<String, String> param, List<Integer> ids);

}
