package by.intro.library.service;

import by.intro.library.model.Address;
import by.intro.library.model.AddressPage;
import by.intro.library.model.exception.AddressNotFoundException;
import by.intro.library.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Page<Address> getAddresses(AddressPage addressPage) {
        Sort sort = Sort.by(addressPage.getSortDirection(), addressPage.getSortBy());
        Pageable pageable = PageRequest.of(addressPage.getPageNumber(),
                addressPage.getPageSize(),
                sort);
        return addressRepository.findAll(pageable);
    }

    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new AddressNotFoundException(id));
    }

    public Address deleteAddress(Long id) {
        Address address = getAddress(id);
        addressRepository.delete(address);
        return address;
    }

    @Transactional
    public Address editAddress(Long id, Address address) {
        Address addressToEdit = getAddress(id);
        addressToEdit.setStreet(address.getStreet());
        return addressToEdit;
    }
}
