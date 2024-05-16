package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {

    @Query("SELECT f FROM Fare f WHERE :currentDate BETWEEN f.startDate AND f.endDate")
    List<Fare> getByCurrentDate(@Param("currentDate") Date currentDate);

}



