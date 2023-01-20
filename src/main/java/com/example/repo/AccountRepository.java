package com.example.repo;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author loop
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}