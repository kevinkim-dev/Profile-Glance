package com.profileglance.db.repository;

import com.profileglance.db.entity.Lookatme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LookatmeRepository extends JpaRepository<Lookatme, Long> {

    List<Lookatme> findAllByUser_UserEmail(String userEmail);

    @Query(value = "select L.* from lookatme L where L.category_id = (select C.category_id from category C where C.category = :categoryName) order by L.lookatme_id desc limit :limit, 10", nativeQuery = true)
    List<Lookatme> findAllByCategory_CategoryName(@Param("categoryName") String categoryName, @Param("limit") Long limit);

    @Query(value = "select L.* from lookatme L where L.user_id in (select U.user_id from user U where U.user_nickname like :userNickname) order by L.lookatme_id desc limit :limit,10", nativeQuery = true)
    List<Lookatme> findAllByUser_UserNickname(@Param("userNickname") String userNickname, @Param("limit") Long limit);

    @Query(value = "select L.* from lookatme L where title like :title order by L.lookatme_id desc limit :limit, 16", nativeQuery = true)
    List<Lookatme> findAllByTitle(@Param("title") String title,@Param("limit") Long limit);

    @Query(value = "select L.* from lookatme L order by L.view desc limit :limit, 16", nativeQuery = true)
    List<Lookatme> findAllByOrderByView(@Param("limit") Long limit);

    Long countByUser_UserEmail(String userEmail);

    Optional<Lookatme> findByLookatmeId(Long lookatmeId);

    @Transactional
    void deleteByLookatmeId(Long lookatmeId);

    @Transactional
    void deleteAllByUser_UserNickname(String userNickname);

}
