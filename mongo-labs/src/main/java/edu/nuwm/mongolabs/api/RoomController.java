package edu.nuwm.mongolabs.api;

import edu.nuwm.mongolabs.persistence.entity.*;
import edu.nuwm.mongolabs.persistence.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final StaffRepository staffRepository;
    private final ServiceRepository serviceRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public RoomController(
            RoomRepository roomRepository,
            GuestRepository guestRepository,
            ReservationRepository reservationRepository,
            StaffRepository staffRepository,
            ServiceRepository serviceRepository,
            BookingRepository bookingRepository,
            PaymentRepository paymentRepository
    ) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.staffRepository = staffRepository;
        this.serviceRepository = serviceRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/guest")
    public List<Guest> getGuests() {
        return guestRepository.findAll();
    }

    @GetMapping("/reservation")
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @GetMapping("/staff")
    public List<Staff> getStaff() {
        return staffRepository.findAll();
    }

    @GetMapping("/service")
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/booking")
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/payment")
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/search")
    public Room getRoomByNumber(@RequestParam final String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    // Пошук гостя за ім'ям та прізвищем
    @GetMapping("/guest/search")
    public Guest getGuestByName(@RequestParam final String firstName, @RequestParam final String lastName) {
        return guestRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    // Пошук працівника за ім'ям та посадою
    @GetMapping("/staff/search")
    public Staff getStaffByNameAndPosition(
            @RequestParam final String firstName,
            @RequestParam final String position
    ) {
        return staffRepository.findByFirstNameAndPosition(firstName, position);
    }


    // Пошук послуги за назвою
    @GetMapping("/service/search")
    public Service getServicesByName(@RequestParam final String serviceName) {
        return serviceRepository.findByServiceName(serviceName);
    }


    // Пошук платежу за датою платежу та методом
    @GetMapping("/payment/search")
    public List<Payment> getPaymentsByMethod(@RequestParam final String amount) {
        return paymentRepository.findByAmount(amount);
    }



}
