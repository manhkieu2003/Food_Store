package com.example.food.service;

import com.example.food.entity.Admin;
import com.example.food.entity.User;
import com.example.food.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Component
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }
    public Admin getAdminId(int id) {
        return adminRepository.findById(id).get();
    }
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    public void updateAdmin(Admin admin,int id) {
        admin.setAdminId(id);
        Optional<Admin> optional = this.adminRepository.findById(id);
        Admin AdminId=optional.get();

        if(admin.getAdminId()==id)
        {
            this.adminRepository.save(admin);
        }
    }
//    public boolean validateAdminCredentials(String email,String password)
//    {
//        Admin admin=adminRepository.findByAdminEmail(email);
//        if(admin!=null && admin.getAdminPassword().equals(password))
//        {
//            return true;
//        }
//        return false;
//    }

}
