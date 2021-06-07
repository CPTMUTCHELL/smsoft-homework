package com.smsoft.repository;

import com.smsoft.dto.RecordDTO;
import com.smsoft.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByYmdhBetween(Date fromDate, Date toDate);
    List<Record> findAllBySubtypeIsNotIn(String[] subtype);
    @Query(value = "SELECT new com.smsoft.dto.RecordDTO (ssoid,formid,subtype) from Record where subtype not in :subtypes")
    List<RecordDTO> findAllDTOBySubtypeIsNotIn(String[] subtypes);
    @Query(value = "SELECT new com.smsoft.dto.RecordDTO (ssoid,formid,subtype) from Record where ymdh between :fromDate AND :toDate")
    List<RecordDTO> findAllDTOByYmdhBetween(Date fromDate, Date toDate);

}
