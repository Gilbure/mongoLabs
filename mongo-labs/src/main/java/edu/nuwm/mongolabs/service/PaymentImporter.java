package edu.nuwm.mongolabs.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import edu.nuwm.mongolabs.persistence.entity.Payment;
import edu.nuwm.mongolabs.persistence.repository.PaymentRepository;
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
public class PaymentImporter {

    private static final String PAYMENT_CSV_FILE_PATH = "import/Payment.csv";

    private final PaymentRepository paymentRepository;

    public PaymentImporter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostConstruct
    public void importData() throws IOException, URISyntaxException {
        final Path filePath = Paths.get(ClassLoader.getSystemResource(PAYMENT_CSV_FILE_PATH).toURI());
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = getCsvReader(reader)) {
                final List<String[]> rows = csvReader.readAll();
                importPaymentsFromRows(rows);
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

    private void importPaymentsFromRows(final List<String[]> rows) {
        final List<Payment> payments = rows.stream()
                .map(row -> {
                    Payment payment = new Payment(
                    row[0],
                    row[1],
                    row[2]);
                    //payment.setBookingID(Long.parseLong(row[3]));
                    return payment;
                })
                .toList();
        paymentRepository.saveAll(payments);
    }


}
