package be.spring_flavyan.services;

import be.spring_flavyan.entities.fiscal.Fiscal;
import be.spring_flavyan.repositories.FiscalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FiscalService {

    private final FiscalRepository fiscalRepository;

    public List<Fiscal> findAll(){
        List<Fiscal> fiscals =  fiscalRepository.findAll();
        return fiscals;
    }

    public List<Fiscal> findAllOnlyFiscal(){
        List<Fiscal> fiscals =  fiscalRepository.findBaseFiscalsOnly();
        return fiscals;
    }

    public Fiscal save(Fiscal fiscal){
        fiscalRepository.save(fiscal);
        return fiscal;
    }

    public void delete(Long id) {
        fiscalRepository.deleteById(id);
    }

    public void update(Long id, String name) {
        Fiscal fiscal = fiscalRepository.findById(id).orElseThrow();
        fiscal.setName(name);
        fiscalRepository.save(fiscal);
    }
}
