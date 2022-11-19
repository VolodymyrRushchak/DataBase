package com.rushchak.springbootlab.service;

import com.rushchak.springbootlab.domain.HomeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HomeAddressService extends GeneralService<HomeAddress, Integer> {
}
