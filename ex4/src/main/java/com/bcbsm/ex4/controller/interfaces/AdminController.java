package com.bcbsm.ex4.controller.interfaces;

import com.bcbsm.ex4.entity.Rating;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface AdminController {
    @GetMapping("/ratings")
    @PreAuthorize("hasAuthority('admin')")
    List<Rating> getRatings();
}
