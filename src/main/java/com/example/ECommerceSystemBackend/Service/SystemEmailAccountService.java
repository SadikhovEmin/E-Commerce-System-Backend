package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.SystemEmailAccount;
import com.example.ECommerceSystemBackend.Repository.SystemEmailAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemEmailAccountService {
    
    @Autowired
    private SystemEmailAccountRepository systemEmailAccountRepository;

    public SystemEmailAccount getSystemEmailAccount(Integer id){return systemEmailAccountRepository.getSystemEmailAccountById(id);}

    public SystemEmailAccount getSystemEmailAccount(String email){return systemEmailAccountRepository.getSystemEmailAccountByEmail(email);}
}
