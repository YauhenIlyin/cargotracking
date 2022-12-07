package by.ilyin.core.repository;

import by.ilyin.core.entity.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WaybillRepository extends JpaRepository<Waybill, Long>, JpaSpecificationExecutor<Waybill> {

}
