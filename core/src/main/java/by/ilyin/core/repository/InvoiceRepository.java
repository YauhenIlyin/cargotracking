package by.ilyin.core.repository;

import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.Invoice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

    void deleteByIdIsIn(List<Long> invoiceIdList);

    Optional<Invoice> findByDriver(CustomUser driver);

}
