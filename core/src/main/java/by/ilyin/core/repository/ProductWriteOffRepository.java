package by.ilyin.core.repository;

import by.ilyin.core.entity.ProductWriteOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductWriteOffRepository extends JpaRepository<ProductWriteOff, Long>, JpaSpecificationExecutor<ProductWriteOff> {

    void deleteByIdIsIn(List<Long> productWriteOffIdList);

    List<ProductWriteOff> findAllByProduct_Invoice_Id(Long invoiceId);

}
