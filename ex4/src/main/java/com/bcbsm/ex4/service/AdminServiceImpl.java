package com.bcbsm.ex4.service;

import com.bcbsm.ex4.entity.Rating;
import com.bcbsm.ex4.repository.RatingRepository;
import com.bcbsm.ex4.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    final RatingRepository ratingRepository;

    @Autowired
    public AdminServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> findAllByUserName(String username) {
        return ratingRepository.findAllByUser(username);
    }
}
