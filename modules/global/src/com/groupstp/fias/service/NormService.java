package com.groupstp.fias.service;

import com.groupstp.fias.entity.Address;

import java.util.Map;

public interface NormService {
    String NAME = "fias_NormService";

    Address normalize(String srcAddress);

    Map<String, Object> normalize(String address, Boolean force);
}