package com.bcbsm.ex4.service.interfaces;

import com.bcbsm.ex4.entity.Rating;
import java.util.List;

public interface UserService {
    String registerUser(String name, String password, String role);


    String rate(int rating, String feedback, String userName);

    List<Rating> getRatings(String username);

}
