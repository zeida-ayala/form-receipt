package com.company.encriptors;

public interface IEncryptDecrypt {
    String encrypt(String toEncrypt, String key) throws Exception;
    String decrypt(String toDecrypt, String key) throws Exception;
}
