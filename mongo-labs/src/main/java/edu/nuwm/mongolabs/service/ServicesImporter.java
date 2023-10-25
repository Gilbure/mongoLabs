package edu.nuwm.mongolabs.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import edu.nuwm.mongolabs.persistence.repository.ServiceRepository; // Додайте імпорт для репозиторія Service
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
public class ServicesImporter {

    private static final String SERVICE_CSV_FILE_PATH = "import/Service.csv"; // Оновіть шлях до CSV файлу для Service

    private final ServiceRepository serviceRepository; // Додайте репозиторій Service

    public ServicesImporter(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @PostConstruct
    public void importData() throws IOException, URISyntaxException {
        final Path filePath = Paths.get(ClassLoader.getSystemResource(SERVICE_CSV_FILE_PATH).toURI());
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = getCsvReader(reader)) {
                final List<String[]> rows = csvReader.readAll();
                importServicesFromRows(rows);
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

    private void importServicesFromRows(final List<String[]> rows) {
        final List<edu.nuwm.mongolabs.persistence.entity.Service> servicesList = rows.stream()
                .map(row -> {
                    edu.nuwm.mongolabs.persistence.entity.Service service = new edu.nuwm.mongolabs.persistence.entity.Service();
                    service.setServiceName(row[0]);
                    service.setDescription(row[1]);
                    service.setPrice(row[2]);
                    return service;
                })
                .toList();
        serviceRepository.saveAll(servicesList);
    }
}
