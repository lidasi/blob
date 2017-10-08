package com.example.hitest.service;

import com.example.hitest.model.Verification;

public interface VerificationService {

    Verification queyEmail(String[] keys, Object[] values);

    void save(Verification verification1);

    void update(Verification verification);
}
