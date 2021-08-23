package com.mvc.mudi.repository;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mvc.mudi.model.OrderStatus;
import com.mvc.mudi.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{
	
	@Cacheable("orders")
	List<Orders> findByStatus(OrderStatus status, Pageable sort);
	
	@Query("SELECT o FROM Orders o join o.user u WHERE u.username = :username")
	List<Orders> findByUser(@Param("username")String username);

	@Query("SELECT o FROM Orders o join o.user u WHERE u.username = :username AND o.status = :status")
	List<Orders> findByStatusAndUser(@Param("status")OrderStatus status, @Param("username")String username);
	
}
	

