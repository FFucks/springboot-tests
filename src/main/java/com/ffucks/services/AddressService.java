package com.ffucks.services;

import com.ffucks.entities.Address;
import com.ffucks.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Value("${brasil.api.base-url}")
    private String brasilServiceUrl;

    @Value("${brasil.api.search-endpoint}")
    private String searchEndpoint;


    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address findById(Long id) {
        return repository.findById(id).orElse(null);
    }



}
