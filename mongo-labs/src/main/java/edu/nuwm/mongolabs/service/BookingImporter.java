package edu.nuwm.mongolabs.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import edu.nuwm.mongolabs.persistence.entity.Booking;
import edu.nuwm.mongolabs.persistence.repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BookingImporter {

    private static final String BOOKING_CSV_FILE_PATH = "import/Booking.csv";

    private final BookingRepository bookingRepository;

    public BookingImporter(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @PostConstruct
    public void importData() throws IOException, URISyntaxException {
        final Path filePath = Paths.get(ClassLoader.getSystemResource(BOOKING_CSV_FILE_PATH).toURI());
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = getCsvReader(reader)) {
                final List<String[]> rows = csvReader.readAll();
                importBookingsFromRows(rows);
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

    private void importBookingsFromRows(final List<String[]> rows) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final List<Booking> bookings = rows.stream()
                .map(row -> {
                    Booking booking = new Booking(
                    row[0],
                    row[1],
                    row[2]);
                    //booking.setReservationID(Long.parseLong(row[3]));
                    //booking.setRoomID(Long.parseLong(row[4]));
                    return booking;
                })
                .filter(booking -> booking != null)
                .toList();
        bookingRepository.saveAll(bookings);
    }
}
