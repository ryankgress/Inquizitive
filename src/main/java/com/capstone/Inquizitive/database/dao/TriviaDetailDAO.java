package com.capstone.Inquizitive.database.dao;

import com.capstone.Inquizitive.database.entity.Team;
import com.capstone.Inquizitive.database.entity.TriviaDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TriviaDetailDAO extends JpaRepository<TriviaDetail, Long> {
    TriviaDetail findById(int id);

    @Query(value="SELECT * FROM trivia_details where active = 'true' order by date_time;", nativeQuery = true)
    List<TriviaDetail> getAllActiveRecords();

    @Query(value="SELECT * FROM trivia_details where active = 'false' order by date_time;", nativeQuery = true)
    List<TriviaDetail> getAllInactiveRecords();
    // Likely want to add more to this query

    @Query
    List<TriviaDetail> getTriviaDetailsByHostId(Integer id);

    @Query(value = "SELECT * FROM trivia_details WHERE active = 'true' AND host_id = :id ;", nativeQuery = true)
    List<TriviaDetail> getActiveTriviaByHostId(Integer id);

    TriviaDetail findByTriviaName(String name);

}
