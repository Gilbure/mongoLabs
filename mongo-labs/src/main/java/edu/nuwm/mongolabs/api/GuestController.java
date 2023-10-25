package edu.nuwm.mongolabs.api;

import edu.nuwm.mongolabs.persistence.entity.Guest;
import edu.nuwm.mongolabs.persistence.repository.GuestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/guest")
public class GuestController {
    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<String> getGuestNames() {
        return guestRepository.findAll().stream()
                .map(guest -> guest.getFirstName() + " " + guest.getLastName() + " " + guest.getEmail() + " " + guest.getPhone() + " " + guest.getPaymentInfo())
                .collect(Collectors.toList());    }
}
