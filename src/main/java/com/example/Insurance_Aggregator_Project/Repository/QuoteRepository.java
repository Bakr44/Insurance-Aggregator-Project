package com.example.Insurance_Aggregator_Project.Repository;

import com.example.Insurance_Aggregator_Project.Model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {
}
