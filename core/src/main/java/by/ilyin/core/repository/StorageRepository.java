package by.ilyin.core.repository;

import by.ilyin.core.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long>, JpaSpecificationExecutor<Storage> {

    void deleteByIdIsIn(List<Long> storageIdList);

}
