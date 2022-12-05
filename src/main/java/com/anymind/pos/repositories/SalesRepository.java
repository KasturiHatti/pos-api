package com.anymind.pos.repositories;

import com.anymind.pos.entities.Sales;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, String> {

	List<Sales> findAllByCreatedDateTimeBetween(ZonedDateTime start, ZonedDateTime till);
    
}
