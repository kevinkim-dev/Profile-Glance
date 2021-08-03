package com.profileglance.db.repository;

import com.profileglance.db.entity.Lookatme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LookatmeRepository extends JpaRepository<Lookatme, Long> {

    List<Lookatme> findAllByUser_UserEmail(String userEmail);
}
