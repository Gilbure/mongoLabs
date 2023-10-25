package edu.nuwm.mongolabs.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import edu.nuwm.mongolabs.persistence.entity.Reservation;
import edu.nuwm.mongolabs.persistence.repository.ReservationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationImporter {

    private static final String RESERVATION_CSV_FILE_PATH = "import/Reservation.csv"; // Оновіть шлях до CSV файлу для Reservation

    private final ReservationRepository reservationRepository; // Додайте репозиторій Reservation

    public ReservationImporter(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @PostConstruct
    public void importData() throws IOException, URISyntaxException {
        final Path filePath = Paths.get(ClassLoader.getSystemResource(RESERVATION_CSV_FILE_PATH).toURI());
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = getCsvReader(reader)) {
                final List<String[]> rows = csvReader.readAll();
                importReservationsFromRows(rows);
            }
        }
    }

    private CSVReader getCsvReader(final Reader reader) {
        final CSVParser parser = getParser();
        return new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();
    }

    private CSVParser getParser() {
        return new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();
    }

    private void importReservationsFromRows(final List<String[]> rows) {
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Формат дати, як використовується у CSV файлі

        final List<Reservation> reservations = rows.stream()
                .map(row -> {
                    Reservation reservation = new Reservation(
                            row[0], // Передайте checkInDate у конструктор
                            row[1], // Передайте checkOutDate у конструктор
                            row[2], // Передайте total price у конструктор
                            row[3] // Передайте status у конструктор
                    );
                    return reservation;
                })
                .toList();
        reservationRepository.saveAll(reservations);
    }

}
