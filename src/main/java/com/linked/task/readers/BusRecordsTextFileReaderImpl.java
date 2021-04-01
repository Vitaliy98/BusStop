package com.linked.task.readers;

import com.linked.task.entities.BusCompany;
import com.linked.task.entities.BusRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class BusRecordsTextFileReaderImpl implements BusRecordsFileReader {
    private final Logger LOG = LoggerFactory.getLogger(BusRecordsTextFileReaderImpl.class);
    private final Integer numberOfFields = 3;

    public List<BusRecord> readAllValues(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(this::convertToBusRecord)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            LOG.error("Error during opening file: ", exception);
        }
        return Collections.emptyList();
    }

    protected BusRecord convertToBusRecord(String lineRecord) {
        String[] spliitedValues = lineRecord.split("\\s");
        if (spliitedValues.length == numberOfFields) {
            String company = spliitedValues[0];
            LocalTime departureTime = LocalTime.parse(spliitedValues[1]);
            LocalTime arrivalTime = LocalTime.parse(spliitedValues[2]);

            return new BusRecord(BusCompany.valueOf(company), departureTime, arrivalTime);
        } else {
            LOG.error("Incorrect format of values.");
        }
        return null;
    }
}
