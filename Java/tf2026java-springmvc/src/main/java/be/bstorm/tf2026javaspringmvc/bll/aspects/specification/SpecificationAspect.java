package be.bstorm.tf2026javaspringmvc.bll.aspects.specification;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class SpecificationAspect {
    private final HttpServletRequest request;

    @Around(value = "@annotation(withSpecification)")
    public Object beforeSpecification(ProceedingJoinPoint joinPoint, WithSpecification withSpecification)
            throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        var attributes = request.getParameterMap();
        Map<String, Object> attributesMap = new HashMap<>();
        attributes.forEach((key, value) -> attributesMap.put(key, value[0]));


        Parameter[] parameters = method.getParameters();
        Object[] arguments = joinPoint.getArgs();
        for (int i = 0; i < parameters.length; i++) {
            var parameter = parameters[i];
            if (parameter.isAnnotationPresent(SpecificationParam.class)) {
                Specification<?> spec = getSpecifications(withSpecification.type(), attributesMap, withSpecification.acceptsField());
                if (arguments[i] == null) {
                    arguments[i] = spec;
                } else if (arguments[i] instanceof Specification defaultSpecification) {
                    arguments[i] = Specification.allOf(defaultSpecification, spec);
                }
            }
        }

        return joinPoint.proceed(arguments);
    }

    private enum Operator {
        EQ,
        NE,
        GT,
        GTE,
        LT,
        LTE,
        START,
        END,
        CONTAINS,
        IN,
        NIN
    }

    private record SearchParam<T>(Operator op, String field, Object value) {
        public static <T> SearchParam<T> create(String fieldEntry, Object value) {
            String field;
            Operator op;
            String[] key = fieldEntry.split("_");
            if (key.length == 1) {
                op = Operator.EQ;
                field = key[0];
            } else {
                op = Operator.valueOf(key[0].toUpperCase());
                field = key[1];
            }

            return new SearchParam<>(op, field, value);
        }

//        public static <T> List<SearchParam<T>> create(Map<String, Object> routeParams) {
//            return routeParams.entrySet().stream()
//                    .map(entry -> (SearchParam<T>) SearchParam.create(entry))
//                    .toList();
//        }
    }

    private <T> Specification<T> getSpecifications(Class<T> type, Map<String, Object> params, String[] acceptedField) {
        Stream.Builder<Specification<T>> builder = Stream.builder();
        params.forEach((key, value) -> {
            SearchParam<T> searchParam = SearchParam.create(key, value);
            if (Arrays.stream(acceptedField).anyMatch(it -> it.contains(searchParam.field))) {
                builder.add(getSpecification(type, searchParam));
            }
        });

        return builder.build().reduce(Specification::allOf).orElse(null);
    }

    private <T> Specification<T> getSpecification(Class<T> type, SearchParam<T> searchParam) {
        return (root, cq, cb) -> switch (searchParam.op()) {
            case EQ -> cb.equal(root.get(searchParam.field()), searchParam.value());
            case NE -> cb.notEqual(root.get(searchParam.field()), searchParam.value());
            case GT -> {
                if (!(searchParam.value() instanceof Number)) throw new RuntimeException("Value is not a number");
                yield cb.gt(root.get(searchParam.field()), (Number) searchParam.value());
            }
            case GTE -> {
                if (!(searchParam.value() instanceof Number)) throw new RuntimeException("Value is not a number");
                yield cb.ge(root.get(searchParam.field()), (Number) searchParam.value());
            }
            case LT -> {
                if (!(searchParam.value() instanceof Number)) throw new RuntimeException("Value is not a number");
                yield cb.lt(root.get(searchParam.field()), (Number) searchParam.value());
            }
            case LTE -> {
                if (!(searchParam.value() instanceof Number)) throw new RuntimeException("Value is not a number");
                yield cb.le(root.get(searchParam.field()), (Number) searchParam.value());
            }
            case START -> cb.like(root.get(searchParam.field()), searchParam.value() + "%");
            case END -> cb.like(root.get(searchParam.field()), "%" + searchParam.value());
            case CONTAINS -> cb.like(root.get(searchParam.field()), "%" + searchParam.value() + "%");
            case IN -> root.get(searchParam.field()).in(((String) searchParam.value()).split(","));
            case NIN -> cb.not(root.get(searchParam.field()).in(((String) searchParam.value()).split(",")));
        };
    }
}
