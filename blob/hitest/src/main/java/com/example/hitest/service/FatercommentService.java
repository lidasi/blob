package com.example.hitest.service;

import com.example.hitest.model.Fatercomment;

import java.util.List;

public interface FatercommentService {
    List<Fatercomment> commentQuery(String[] keys, Object[] values);

    Fatercomment byOneQuery(String[] keys, Object[] values);
}
