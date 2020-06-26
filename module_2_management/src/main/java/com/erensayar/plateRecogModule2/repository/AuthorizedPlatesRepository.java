package com.erensayar.plateRecogModule2.repository;

import com.erensayar.plateRecogModule2.entity.AuthorizedPlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author erensayar
 */
@Repository
public interface AuthorizedPlatesRepository extends JpaRepository<AuthorizedPlate, Integer> {

    AuthorizedPlate findByPlate(String plate);
}
