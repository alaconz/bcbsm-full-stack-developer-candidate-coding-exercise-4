package com.bcbsm.ex4.controller;

import com.bcbsm.ex4.controller.interfaces.AdminController;
import com.bcbsm.ex4.entity.Rating;
import com.bcbsm.ex4.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminControllerImpl implements AdminController {

    final AdminService adminService;

    @Autowired
    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/ratings")
    @PreAuthorize("hasAuthority('admin')")
    @Override
    public List<Rating> getRatings() {
        return adminService.findAllRatings();
    }
}
