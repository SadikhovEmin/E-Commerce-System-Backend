package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.PlatformManager;
import com.example.ECommerceSystemBackend.Repository.PlatformManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformManagerService {

    @Autowired
    private PlatformManagerRepository platformManagerRepository;

    public PlatformManager getPlatformManager(Integer id) {
        return platformManagerRepository.getPlatformManagerById(id);
    }

    public PlatformManager getPlatformManager(String email) {
        return platformManagerRepository.getPlatformManagerByEmail(email);
    }

    public void updatePlatformManagerMfa(PlatformManager platformManager, boolean mfa) {
        platformManagerRepository.updatePlatformManagerMfaById(platformManager.getId(), mfa);
    }

    public void updatePlatformManagerMfa(String email, boolean mfa) {
        platformManagerRepository.updatePlatformManagerMfaByEmail(email, mfa);
    }
    
}
