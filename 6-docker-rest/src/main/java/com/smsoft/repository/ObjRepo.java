package com.smsoft.repository;

import com.smsoft.entity.Obj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjRepo extends JpaRepository<Obj,Integer> {

}
