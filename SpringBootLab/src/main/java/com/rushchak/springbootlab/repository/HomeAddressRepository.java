package com.rushchak.springbootlab.repository;

import com.rushchak.springbootlab.domain.HomeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeAddressRepository extends JpaRepository<HomeAddress, Integer> {
}
