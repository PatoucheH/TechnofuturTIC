package be.bstorm.tf2026javaspringmvc.pl.controllers;

import be.bstorm.tf2026javaspringmvc.bll.aspects.perfomance.Performance;
import be.bstorm.tf2026javaspringmvc.bll.excpetions.QueryFailedException;
import be.bstorm.tf2026javaspringmvc.bll.plane.PlaneService;
import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneEntity;
import be.bstorm.tf2026javaspringmvc.dal.specifications.BaseSpecification;
import be.bstorm.tf2026javaspringmvc.dal.specifications.PlaneSpecification;
import be.bstorm.tf2026javaspringmvc.pl.exceptions.NotFoundHttpException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/planes")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaneService planeService;

    @Performance(millis = 1000)
    @GetMapping
    public String indexAction(
            Map<String, Object> model
    ) {
        //region Example

//        PlaneEntity example = new PlaneEntity();
//
//        if (params.containsKey("imma")) {
//            example.setImma(params.get("imma"));
//        }
//        if (params.containsKey("owner")) {
//            example.setOwnerId(Long.parseLong(params.get("owner")));
//        }
//
//        var exampleQuery = Example.of(example,
//                ExampleMatcher.matching()
//                        .withIgnoreCase()
//                        .withIgnoreNullValues()
//                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
//        );
//
//        List<PlaneEntity> planes = planeRepository.findAll(exampleQuery);
//
//        model.put("planes", planes);
//
//        return "plane/index";
        //endregion
        //region Specification

//        Specification<PlaneEntity> spec = PlaneSpecification.isActive();
//
//        if (params.containsKey("imma")) {
//            spec = spec.and(PlaneSpecification.containsImma(params.get("imma")));
//        }
//        if (params.containsKey("owner")) {
//            spec = spec.and(PlaneSpecification.likeOwnerName(params.get("owner")));
//        }
        //endregion


        List<PlaneEntity> planes = planeService.findAll(BaseSpecification.isActive());

        model.put("planes", planes);

        return "plane/index";
    }

    @GetMapping("/{imma}")
    public String showAction(Map<String, Object> model, @PathVariable String imma){

        try {
            PlaneEntity plane = planeService.findByImma(imma);
            model.put("plane", plane);
            return "plane/show";
        } catch (QueryFailedException e) {
            log.error("Error while fetching plane with imma {}", imma);
            throw new NotFoundHttpException("Plane not found with imma: " + imma);
        }
    }



}
