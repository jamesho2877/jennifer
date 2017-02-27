package com.jennifer.controller.rest;

import com.jennifer.controller.ShippingAddressController;
import com.jennifer.entity.ShippingAddress;
import com.jennifer.service.ShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for ShippingAddress activities
 */
@RestController
@RequestMapping("/api/shipping-address")
public class RestShippingAddressController {
    private static final Logger log = LoggerFactory.getLogger(RestShippingAddressController.class);

    private ShippingAddressService shippingAddressService;

    @Autowired
    public RestShippingAddressController(ShippingAddressService shippingAddressService){
        this.shippingAddressService = shippingAddressService;
    }

    @GetMapping
    public List<ShippingAddress> findAll() {
        List<ShippingAddress> shippingAddresses = shippingAddressService.findAllShippingAddresses();
        for(ShippingAddress s:shippingAddresses){
            s.toString();
        }
        return shippingAddressService.findAllShippingAddresses();
    }

}
