package com.example.banking.account.investment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    // Native query to directly reference the discriminator column
    @Query(value = "SELECT * FROM investments WHERE investment_type = ?1", nativeQuery = true)
    public List<Investment> getAllInvestments(String investmentType);

    @Query(value = "SELECT * FROM investments", nativeQuery = true)
    public List<Investment> getAllInvestments();
}
