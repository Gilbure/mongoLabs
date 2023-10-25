package edu.nuwm.mongolabs.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import edu.nuwm.mongolabs.persistence.entity.Room;
import edu.nuwm.mongolabs.persistence.repository.RoomRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RoomsImporter { // Перейменуйте клас на відповідний RoomsImporter

    private static final String ROOM_CSV_FILE_PATH = "import/Room.csv"; // Оновіть шлях до CSV файлу для Room

    private final RoomRepository roomRepository; // Додайте репозиторій Room

    public RoomsImporter(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    public void importData() throws IOException, URISyntaxException {
        final Path filePath = Paths.get(ClassLoader.getSystemResource(ROOM_CSV_FILE_PATH).toURI());
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = getCsvReader(reader)) {
                final List<String[]> rows = csvReader.readAll();
                importRoomsFromRows(rows);
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

    private void importRoomsFromRows(final List<String[]> rows) {
        final List<Room> rooms = rows.stream()
                .map(row -> new Room(
                        row[0], // RoomNumber
                        row[1], // RoomType
                        row[2], // PricePerNight
                        row[3], // Capacity
                        row[4], // Description
                        row[5] // Status
                ))
                .toList();
        roomRepository.saveAll(rooms);
    }
}
