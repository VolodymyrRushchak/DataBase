package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.domain.HomeAddress;
import com.rushchak.springbootlab.exception.HomeAddressNotFoundException;
import com.rushchak.springbootlab.repository.HomeAddressRepository;
import com.rushchak.springbootlab.service.HomeAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HomeAddressServiceImpl implements HomeAddressService {

    @Autowired
    private HomeAddressRepository homeAddressRepository;

    @Override
    public List<HomeAddress> findAll() {
        return homeAddressRepository.findAll();
    }

    @Override
    public HomeAddress findById(Integer id) {
        return homeAddressRepository.findById(id).orElseThrow(() -> new HomeAddressNotFoundException(id));
    }

    @Override
    @Transactional
    public HomeAddress create(HomeAddress homeAddress) {
        homeAddressRepository.save(homeAddress);
        return homeAddress;
    }

    @Override
    @Transactional
    public void update(Integer id, HomeAddress updHomeAddress) {
        HomeAddress homeAddress = homeAddressRepository.findById(id)
                .orElseThrow(() -> new HomeAddressNotFoundException(id));
        homeAddress.setCountry(updHomeAddress.getCountry());
        homeAddress.setState(updHomeAddress.getState());
        homeAddress.setCity(updHomeAddress.getCity());
        homeAddress.setStreetName(updHomeAddress.getStreetName());
        homeAddress.setHouseNumber(updHomeAddress.getHouseNumber());
        homeAddressRepository.save(homeAddress);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        HomeAddress homeAddress = homeAddressRepository.findById(id)
                .orElseThrow(() -> new HomeAddressNotFoundException(id));
        homeAddressRepository.delete(homeAddress);
    }

}
