package com.example.hitest.service;

import com.example.hitest.model.Userinfo;

public interface UserinfoService {
    Userinfo queryUsername(String[] keys, Object[] values);

    void save(Userinfo userinfo1);
}
