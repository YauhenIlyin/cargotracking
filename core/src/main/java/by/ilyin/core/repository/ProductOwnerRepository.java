package by.ilyin.core.repository;

import by.ilyin.core.entity.ProductOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductOwnerRepository extends JpaRepository<ProductOwner, Long>, JpaSpecificationExecutor<ProductOwner> {

    void deleteByIdIsIn(List<Long> storageIdList);

}
