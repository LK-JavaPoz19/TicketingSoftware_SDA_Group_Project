package pl.sda.ticketing_software_sda_gp.service;

import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.User;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.Set;

public class ServiceUtility {

    static <E> Set<E> addElementsOrFindIntersection(Set<E> operationalSet, Set<E> resultSet) {
        if (resultSet.isEmpty()) resultSet.addAll(operationalSet);
        else resultSet.retainAll(operationalSet);
        return resultSet;
    }

    static User getGeneralRecipient(UserRepository userRepository) {
        return userRepository.findById(1L)
                .filter(user -> user.getUsername().equals("gen_recipient"))
                .orElseThrow(() -> new IllegalStateException("General Recipient not set."));
    }

    static Status getNewStatus(StatusRepository statusRepository) {
        return statusRepository.findById(1L)
                .filter(status -> status.getStatusName().equals("NEW"))
                .orElseThrow(() -> new IllegalStateException("NEW status not set."));
    }
}
