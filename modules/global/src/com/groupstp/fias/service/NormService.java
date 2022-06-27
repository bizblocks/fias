package com.groupstp.fias.service;

import com.groupstp.fias.entity.Address;

public interface NormService {
    String NAME = "fias_NormService";

    Address normalize(String srcAddress);
}