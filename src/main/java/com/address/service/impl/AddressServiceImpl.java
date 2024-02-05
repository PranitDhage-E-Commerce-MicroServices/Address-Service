package com.address.service.impl;

import com.address.exceptions.BusinessException;
import com.address.exceptions.ResourceNotFoundException;
import com.address.exceptions.ValidationException;
import com.address.pojo.Address;
import com.address.repository.AddressRepository;
import com.address.service.IAddressService;
import com.address.utils.Constants;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepo;

    @Autowired
    public AddressServiceImpl(final AddressRepository addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public List<Address> getAllAddresses(int userId)
            throws BusinessException {

        try {
            return addressRepo.findAllByUserId(userId);
        } catch (Exception e) {
            log.error("Exception While Getting All Address for Given User Id : {}, Message: {}", userId, e.getMessage());
            throw new BusinessException(e.getMessage(), Constants.ERR_BUSINESS);
        }
    }

    @Override
    public Address addAddress(Address address)
            throws BusinessException, ValidationException {

        try {
            address.setDate(new Date());
            return addressRepo.save(address);
        } catch (Exception e) {
            log.error("Exception While Saving Address: {}", e.getMessage());
            throw new BusinessException(e.getMessage(), Constants.ERR_BUSINESS);
        }
    }

    @Override
    public Address updateAddress(int addId, Address newAddress)
            throws ResourceNotFoundException, BusinessException, ValidationException {

        try {

            Address oldAddress = addressRepo.findById(addId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "Address not found for given address Id : " + addId,
                                    Constants.ERR_RESOURCE_NOT_FOUND)
                    );

            if (StringUtils.isNotBlank(newAddress.getAddress()))
                oldAddress.setAddress(newAddress.getAddress());

            if (StringUtils.isNotBlank(newAddress.getCity()))
                oldAddress.setCity(newAddress.getCity());

            if (StringUtils.isNotBlank(newAddress.getState()))
                oldAddress.setState(newAddress.getState());

            if (StringUtils.isNotBlank(newAddress.getCountry()))
                oldAddress.setCountry(newAddress.getCountry());

            if (StringUtils.isNotBlank(newAddress.getPin()))
                oldAddress.setPin(newAddress.getPin());

            return addressRepo.save(oldAddress);

        } catch (ResourceNotFoundException | BeansException e) {
            log.error("Exception While Updating Address: {}", e.getMessage());
            throw new BusinessException(e.getMessage(), Constants.ERR_BUSINESS);
        }
    }

    @Override
    public boolean deleteAddress(int addId)
            throws ResourceNotFoundException, BusinessException {

        try {
            if (!addressRepo.existsById(addId)) {
                throw new ResourceNotFoundException(
                        "Address not found for given Address Id : " + addId,
                        Constants.ERR_RESOURCE_NOT_FOUND);
            }

            addressRepo.deleteById(addId);
            return true;
        } catch (ResourceNotFoundException e) {
            log.error("Exception While deleting Address for Id: {} - {}", addId, e.getMessage());
            throw new BusinessException(e.getMessage(), Constants.ERR_BUSINESS);
        }
    }
}
