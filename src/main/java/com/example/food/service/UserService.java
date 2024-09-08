package com.example.food.service;

import com.example.food.entity.Admin;
import com.example.food.entity.Product;
import com.example.food.entity.User;
import com.example.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService  {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {
        user.setRole("ROLE_USER");
       String encodePassword= passwordEncoder.encode(user.getUpassword());
        user.setUpassword(encodePassword);
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
//        hoặc
//        Optional<User> optional = this.userRepository.findById(id);
//        User user = optional.get();
//        return user;
    }
    public void updateUser(User user, int id)
    {
        user.setId(id);
        Optional<User> optional = this.userRepository.findById(id);
        User userId=optional.get();

        if(userId.getId()==id)
        {
            userId.setUname(user.getUname());
            userId.setUemail(user.getUemail());

            userId.setUnumber(user.getUnumber());
            userId.setRole(user.getRole());
            this.userRepository.save(userId);
        }
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }

//    public boolean validateLoginCredentials(String email, String password) {
//        User user=userRepository.findByUemail(email);
//        if(user!=null && user.getUpassword().equals(password))
//        {
//            return true;
//        }
//        return false;
//    }

    public User getUserByUemail(String email) {
       return userRepository.findByUemail(email);
    }
    public User addAdmin(User user) {
        user.setRole("ROLE_ADMIN");
        String encodePassword= passwordEncoder.encode(user.getUpassword());
        user.setUpassword(encodePassword); // setpassword mã hóa
        return userRepository.save(user);
    }
    public void updateAdmin(User user, int id)
    {

        Optional<User> optional = this.userRepository.findById(id);
        User AdminById=optional.get();

        if(AdminById.getId()==id)
        {
            AdminById.setUname(user.getUname());
            AdminById.setUemail(user.getUemail());

            AdminById.setUnumber(user.getUnumber());
            AdminById.setRole(user.getRole());


            this.userRepository.save(AdminById);
        }
    }

    public void deleteAdmin(Integer id) {
        this.userRepository.deleteById(id);
    }
}
