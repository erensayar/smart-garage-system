package com.erensayar.plateRecogModule2.service;

import com.erensayar.plateRecogModule2.entity.Admin;
import com.erensayar.plateRecogModule2.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author erensayar
 */
@Service
public class AdminService implements IAdminService {

    //--------------------Dependency Injection----------------------
    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //---------------------Basic DB Process-------------------------
    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Integer id) {
        return adminRepository.findById(id);
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }

}
