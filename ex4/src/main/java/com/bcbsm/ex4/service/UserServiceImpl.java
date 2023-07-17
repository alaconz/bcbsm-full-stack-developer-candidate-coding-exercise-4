package com.bcbsm.ex4.service;

import com.bcbsm.ex4.entity.Rating;
import com.bcbsm.ex4.entity.UserEntity;
import com.bcbsm.ex4.repository.RatingRepository;
import com.bcbsm.ex4.repository.RoleRepository;
import com.bcbsm.ex4.repository.UserRepository;
import com.bcbsm.ex4.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RatingRepository ratingRepository;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RatingRepository ratingRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public String registerUser(String name, String password, String role) {
        UserEntity user = new UserEntity();
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode(password));

        roleRepository.findByRole(role).orElseThrow(() -> new RuntimeException("Role Not Found"));
        user.setRole(role);
        userRepository.save(user);
        return "User registered success";
    }

    @Override
    public String rate(int rating, String feedback, String userName) {
        Rating ratingEntity = new Rating();
        ratingEntity.setRating(rating);
        ratingEntity.setComment(feedback);
        ratingEntity.setDate(new Date());
        ratingEntity.setUser(userName);
        Rating rated = ratingRepository.save(ratingEntity);
        return rated.toString();
    }

    @Override
    public List<Rating> getRatings(String username) {
        return ratingRepository.findAllByUser(username);
    }

}
