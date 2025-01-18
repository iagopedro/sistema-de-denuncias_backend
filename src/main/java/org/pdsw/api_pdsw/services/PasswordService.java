package org.pdsw.api_pdsw.services;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final StringEncryptor encryptor;

    @Autowired
    public PasswordService(@Qualifier("jasyptStringEncryptor") StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String encryptPassword(String plainTextPassword) {
        return encryptor.encrypt(plainTextPassword);
    }

    public String decryptPassword(String encryptedPassword) {
        return encryptor.decrypt(encryptedPassword);
    }
}
