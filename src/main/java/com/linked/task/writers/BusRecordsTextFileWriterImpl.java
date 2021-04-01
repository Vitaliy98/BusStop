package com.linked.task.writers;

import com.linked.task.entities.BusRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class BusRecordsTextFileWriterImpl implements BusRecordsFileWriter {
    private final Logger LOG = LoggerFactory.getLogger(BusRecordsTextFileWriterImpl.class);

    @Override
    public void writeToFile(String filePath, List<BusRecord> busRecords) {
        try {
            List<String> recordsToWrite = busRecords.stream()
                    .map(BusRecord::toString)
                    .collect(Collectors.toList());

            Files.write(Paths.get(filePath), recordsToWrite, StandardOpenOption.CREATE);
        } catch (IOException exception) {
            LOG.error("Error during writing records to file: ", exception);
        }
    }
}
