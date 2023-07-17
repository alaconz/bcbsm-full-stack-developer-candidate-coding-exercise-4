package com.bcbsm.ex4.service.interfaces;

import com.bcbsm.ex4.entity.Rating;

import java.util.List;

public interface AdminService {


    List<Rating> findAllRatings();


    List<Rating> findAllByUserName(String username);
}
