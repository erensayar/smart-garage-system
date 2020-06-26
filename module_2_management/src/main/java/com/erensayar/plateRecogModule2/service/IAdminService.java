package com.erensayar.plateRecogModule2.service;

import com.erensayar.plateRecogModule2.entity.Admin;

import java.util.List;
import java.util.Optional;

/**
 * @author erensayar
 */
public interface IAdminService {

    List<Admin> getAll();

    Optional<Admin> getAdminById(Integer id);

    void addAdmin(Admin admin);

    void deleteById(Integer id);

}