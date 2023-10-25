package edu.nuwm.mongolabs.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import edu.nuwm.mongolabs.persistence.entity.Staff; // Додайте імпорт для класу Staff
import edu.nuwm.mongolabs.persistence.repository.StaffRepository; // Додайте імпорт для репозиторія Staff
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
public class StaffImporter {

    private static final String STAFF_CSV_FILE_PATH = "import/Staff.csv";

    private final StaffRepository staffRepository;

    public StaffImporter(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostConstruct
    public void importData() throws IOException, URISyntaxException {
        final Path filePath = Paths.get(ClassLoader.getSystemResource(STAFF_CSV_FILE_PATH).toURI());
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = getCsvReader(reader)) {
                final List<String[]> rows = csvReader.readAll();
                importStaffFromRows(rows);
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

    private void importStaffFromRows(final List<String[]> rows) {
        final List<Staff> staffList = rows.stream()
                .map(row -> new Staff(
                        row[0], // firstName
                        row[1], // lastName
                        row[2], // position
                        row[3], // salary
                        row[4], // email
                        row[5]  // phone
                        // hotelID, якщо це необхідно
                ))
                .toList();
        staffRepository.saveAll(staffList);
    }

}
