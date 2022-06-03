package com.example.ECommerceSystemBackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerceSystemBackend.Model.PlatformManager;
import com.example.ECommerceSystemBackend.Service.PlatformManagerService;

@RestController
@RequestMapping(path = "/platformManager")
@CrossOrigin
public class PlatformManagerController {

    @Autowired
    PlatformManagerService platformManagerService;

    @GetMapping("/{email}")
    public PlatformManager getPlatformManager(@PathVariable String email) {
        return platformManagerService.getPlatformManager(email);
    }
}
