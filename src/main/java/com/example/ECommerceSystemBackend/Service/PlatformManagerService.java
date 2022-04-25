package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PlatformManagerDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerInfoDTO;
import com.example.ECommerceSystemBackend.Model.PlatformManager;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.PlatformManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformManagerService {
    @Autowired
    public PlatformManagerRepository platformManagerRepository;

    public PlatformManager addPlatformManager(PlatformManager storeOwner) {
        return platformManagerRepository.save(storeOwner);
    }

    public PlatformManager getPlatformManager(Integer id) {return platformManagerRepository.getPlatformManagerByID(id);}

    public String getPlatformManagerPassword(Integer id){return platformManagerRepository.getPlatformManagerPassword(id);}

    public void updatePlatformManagerInfo(PlatformManagerDTO platformManagerDTO) { platformManagerRepository.updatePlatformManagerInfo(platformManagerDTO.getId(), platformManagerDTO.getName(),platformManagerDTO.getSurname(), platformManagerDTO.getEmail());}

    public void updatePlatformManagerPassword(PasswordDTO passwordDTO) {
        platformManagerRepository.updatePlatformManagerPassword(passwordDTO.getId(),passwordDTO.getPassword());}
}
