package com.bank.repository;

import com.bank.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByBankNameIgnoreCase(String bankName);
}
