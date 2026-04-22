package be.bstorm.tf2026javaspringmvc;

import be.bstorm.tf2026javaspringmvc.dal.embbed.Address;
import be.bstorm.tf2026javaspringmvc.dal.entities.FiscalEntity;
import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneEntity;
import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneTypeEntity;
import be.bstorm.tf2026javaspringmvc.dal.repositories.FiscalRepository;
import be.bstorm.tf2026javaspringmvc.dal.repositories.PlaneRepository;
import be.bstorm.tf2026javaspringmvc.dal.repositories.PlaneTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class Tf2026javaSpringmvcApplication {
    private final FiscalRepository fiscalRepository;
    private final PlaneTypeRepository planeTypeRepository;
    private final PlaneRepository planeRepository;

    static void main(String[] args) {
        SpringApplication.run(Tf2026javaSpringmvcApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("Application is ready");

        Address address = new Address();
        address.setNumber("123");
        address.setStreet("Test street");
        address.setCity("Test city");
        address.setZip("12345");
        address.setCountry("Test country");
        address.setState("Test state");

        FiscalEntity fiscal = new FiscalEntity();
        fiscal.setAddress(address);
        fiscal.setName("Test");
        fiscal.setPhoneNumber("0612345678");
        fiscalRepository.save(fiscal);

        PlaneTypeEntity planeType = new PlaneTypeEntity();
        planeType.setName("Test plane type");
        planeType.setConstructor("Bombardier");
        planeType.setEnginePower(1200);
        planeType.setMaxPassengers(250);
        planeTypeRepository.save(planeType);


        PlaneEntity plane = new PlaneEntity();
        plane.setImma("1-BWW-114");
        plane.setPlaneTypeId(planeType.getId());
        plane.setOwnerId(fiscal.getId());
        planeRepository.save(plane);

    }
}
