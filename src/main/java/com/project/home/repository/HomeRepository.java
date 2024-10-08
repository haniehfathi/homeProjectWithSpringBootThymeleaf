package com.project.home.repository;


import com.project.home.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.home.entity.Home;

import javax.transaction.Transactional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long>{
	
//	@Query("select h from Home h join h.category hc  where (:#{#home.title} is null or " +
//            "h.title like concat('%',:#{#home.title},'%'))" +
//            " and (coalesce(:#{#home.category},null) is null or hc in (:#{#home.category})) " +
//            "group by h.id having count (h.id) >= :num"+
//            "and ( coalesce(:#{#home.province},null) is null or h.province==home.province)"
//			)
//	@Query("select h from Home h WHERE h.province.id = :#{#home.province.id}")

//
//
//	@Query("select h from Home h join h.category hc  where (  :#{#home.title} is null or " +
//			"h.title like concat('%',:#{#home.title},'%'))" +
//			"and( coalesce(:#{#home.category},null) is null or hc in (:#{#home.category})) " +
//			"and( coalesce(:#{#home.province},null) is null or h.province = :#{#home.province} )"+
//			"and( coalesce(:#{#home.city},null) is null or h.city = :#{#home.city} )"+
//			"and ( :#{#home.zone} is NULL or h.zone like concat('%',:#{#home.zone},'%')) " +
//			"and ( :#{#home.totalAreaFrom} is 0 or  h.totalArea >=:#{#home.totalAreaFrom} ) " +
//			"and ( :#{#home.totalAreaTo} is 0 or  h.totalArea <=:#{#home.totalAreaTo} ) " +
//			"and ( :#{#home.totalPriceFrom} is NULL or  h.totalPrice >=:#{#home.totalPriceFrom} ) " +
//			"and ( :#{#home.totalPriceTo} is NULL or  h.totalPrice <=:#{#home.totalPriceTo} ) " +
//			"and ( :#{#home.yearOfConstructionFrom} is 0 or  h.yearOfConstruction >=:#{#home.yearOfConstructionFrom} ) " +
//			"and ( :#{#home.yearOfConstructionTo} is 0 or  h.yearOfConstruction <=:#{#home.yearOfConstructionTo} ) " +
//			"group by h.id having count (h.id) >= :num"  )


	@Query("select h from Home h  where (  :#{#home.title} is null or " +
			"h.title like concat('%',:#{#home.title},'%'))" +
			"and h.enabled=TRUE "+
//			"and( coalesce(:#{#home.category},null) is null or hc in (:#{#home.category})) " +
//			"and( coalesce(:#{#home.province},null) is null or h.province = :#{#home.province} )"+
//			"and( coalesce(:#{#home.city},null) is null or h.city = :#{#home.city} )"+
//			"and ( :#{#home.zone} is NULL or h.zone like concat('%',:#{#home.zone},'%')) " +
//			"and ( :#{#home.totalAreaFrom} is 0 or  h.totalArea >=:#{#home.totalAreaFrom} ) " +
//			"and ( :#{#home.totalAreaTo} is 0 or  h.totalArea <=:#{#home.totalAreaTo} ) " +
//			"and ( :#{#home.totalPriceFrom} is NULL or  h.totalPrice >=:#{#home.totalPriceFrom} ) " +
//			"and ( :#{#home.totalPriceTo} is NULL or  h.totalPrice <=:#{#home.totalPriceTo} ) " +
//			"and ( :#{#home.yearOfConstructionFrom} is 0 or  h.yearOfConstruction >=:#{#home.yearOfConstructionFrom} ) " +
//			"and ( :#{#home.yearOfConstructionTo} is 0 or  h.yearOfConstruction <=:#{#home.yearOfConstructionTo} ) " +
			"group by h.id having count (h.id) >= :num"  )
		Page<Home> findBySearch(Home home, @Param("num") Long size,Pageable pageable);

	@Query("select h from Home h  where  h.enabled=FALSE")
	Page<Home> getDisabledHome(Pageable pageable);

	public  Home getHomeById(Long id);

    Page<Home> getHomeByUser(User user, Pageable pageable);

	@Transactional
	@Modifying
    @Query("update  Home h set h.enabled=TRUE WHERE h.id=:#{#home.id}")
	void enableHome(Home home);

	@Transactional
	@Modifying
	@Query("update  Home h set h.enabled=FALSE WHERE h.id=:#{#home.id}")
	void disableHome(Home home);
}
