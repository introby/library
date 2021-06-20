package by.intro.library.controller;

import by.intro.library.model.Address;
import by.intro.library.model.AddressPage;
import by.intro.library.model.dto.AddressDto;
import by.intro.library.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@RequestBody final AddressDto addressDto) {
        Address address = addressService.addAddress(Address.from(addressDto));
        return new ResponseEntity<>(AddressDto.from(address), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AddressDto>> getAddresses(AddressPage addressPage) {
        Page<Address> addresses = addressService.getAddresses(addressPage);
        Pageable pageable = addresses.getPageable();
        List<AddressDto> addressesDto = addresses.stream().map(AddressDto::from).collect(Collectors.toList());
        PageImpl<AddressDto> addressDtoPage = new PageImpl<>(addressesDto, pageable, addresses.getTotalElements());
        return new ResponseEntity<>(addressDtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable final Long id) {
        Address address = addressService.getAddress(id);
        return new ResponseEntity<>(AddressDto.from(address), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable final Long id) {
        Address address = addressService.deleteAddress(id);
        return new ResponseEntity<>(AddressDto.from(address), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<AddressDto> editAddress(@PathVariable final Long id,
                                            @RequestBody final AddressDto addressDto) {
        Address editedAddress = addressService.editAddress(id, Address.from(addressDto));
        return new ResponseEntity<>(AddressDto.from(editedAddress), HttpStatus.OK);
    }
}
