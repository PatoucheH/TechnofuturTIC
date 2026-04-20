package be.spring_flavyan.services;

import be.spring_flavyan.dtos.request.FiscalRequest;
import be.spring_flavyan.dtos.response.FiscalResponse;
import be.spring_flavyan.entities.fiscal.Address;
import be.spring_flavyan.entities.fiscal.Fiscal;
import be.spring_flavyan.repositories.FiscalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FiscalService {

    private final FiscalRepository fiscalRepository;

    public List<FiscalResponse> findAll() {
        return fiscalRepository.findAll().stream()
                .map(FiscalResponse::from)
                .toList();
    }

    public List<FiscalResponse> findAllOnlyFiscal() {
        return fiscalRepository.findBaseFiscalsOnly().stream()
                .map(FiscalResponse::from)
                .toList();
    }

    public void save(FiscalRequest request) {
        fiscalRepository.save(request.toEntity());
    }

    public void delete(Long id) {
        fiscalRepository.deleteById(id);
    }

    public void update(Long id, String name, String street, String city, String zip, String country, String state) {
        Fiscal fiscal = fiscalRepository.findById(id).orElseThrow();
        fiscal.setName(name);
        fiscal.setAddress(new Address(street, city, zip, country, state));
        fiscalRepository.save(fiscal);
    }
}
