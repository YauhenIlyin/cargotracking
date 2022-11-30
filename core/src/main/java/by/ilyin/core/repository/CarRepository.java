package by.ilyin.core.repository;

import by.ilyin.core.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    void deleteByIdIsIn(List<Long> carIdList);

}
