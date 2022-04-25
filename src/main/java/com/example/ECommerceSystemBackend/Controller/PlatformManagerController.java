package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PlatformManagerDTO;
import com.example.ECommerceSystemBackend.Model.PlatformManager;
import com.example.ECommerceSystemBackend.Service.PlatformManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/platformManager")
@RestController
@CrossOrigin
public class PlatformManagerController {

    @Autowired
    PlatformManagerService platformManagerService;

    @PostMapping
    public PlatformManager addStoreOwner(@RequestBody PlatformManager platformManager) {
        return platformManagerService.addPlatformManager(platformManager);
    }

    @GetMapping(path = "/{id}")
    public PlatformManager getPlatformManager(@PathVariable Integer id) {
        return platformManagerService.getPlatformManager(id);
    }

    @GetMapping("/{id}/password")
    public String getPlatformManagerOldPassword(@PathVariable Integer id) {
        return platformManagerService.getPlatformManagerPassword(id);
    }

    @PutMapping(path = "/{id}/info")
    public void updatePlatformManagerInfo(@RequestBody PlatformManagerDTO platformManagerDTO) {
        platformManagerService.updatePlatformManagerInfo(platformManagerDTO);
    }

    @PutMapping(path = "/{id}/password")
    public void updatePlatformManagerPassword(@RequestBody PasswordDTO passwordDTO) {
        platformManagerService.updatePlatformManagerPassword(passwordDTO);
    }
}
