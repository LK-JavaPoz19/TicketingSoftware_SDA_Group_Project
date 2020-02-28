package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ticketing_software_sda_gp.exception.ResourceNotFoundException;

import java.util.Set;

public class ServiceUtility {

    static <E> Set<E> addElementsOrFindIntersection(Set<E> operationalSet, Set<E> resultSet) {
        if (resultSet.isEmpty()) resultSet.addAll(operationalSet);
        else resultSet.retainAll(operationalSet);
        return resultSet;
    }

    static <O> O findElementOrThrowException(JpaRepository<O, Long> repository, Long id, String exMessage) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(exMessage));
    }
}
