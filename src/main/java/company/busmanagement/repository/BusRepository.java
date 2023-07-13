package company.busmanagement.repository;

import company.busmanagement.entity.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<BusEntity, Long> {
    List<BusEntity> findByNumber(String number);
}