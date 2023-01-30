package jjuni.pjt.recommand.connectDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectDBRepository extends JpaRepository<SdkSmsSend, Integer> {

    public Optional<SdkSmsSend> findById(Integer id);

    // like 검색어
    public Optional<List<SdkSmsSend>> findByUserIdContaining(String name);



}
