package com.erensayar.plateRecogModule2.repository;

import com.erensayar.plateRecogModule2.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author erensayar
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
