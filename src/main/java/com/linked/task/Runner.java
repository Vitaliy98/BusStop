package com.linked.task;

import com.linked.task.entities.BusRecord;
import com.linked.task.readers.BusRecordsFileReader;
import com.linked.task.readers.BusRecordsTextFileReaderImpl;
import com.linked.task.services.impl.BusRecordSortServiceImpl;
import com.linked.task.services.impl.BusRecordsFilteringSortedRecordsService;
import com.linked.task.services.BusRecordsFilteringService;
import com.linked.task.writers.BusRecordsFileWriter;
import com.linked.task.writers.BusRecordsTextFileWriterImpl;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        String filePath = getFilePathFromArgs(args);

        BusRecordsFileReader fileReader = new BusRecordsTextFileReaderImpl();
        BusRecordSortServiceImpl sortService = new BusRecordSortServiceImpl();
        BusRecordsFilteringService filteringService = new BusRecordsFilteringSortedRecordsService();
        BusRecordsFileWriter fileWriter = new BusRecordsTextFileWriterImpl();

        List<BusRecord> records = fileReader.readAllValues(filePath);
        sortService.sortAllRecords(records);
        filteringService.filterRecords(records);
        fileWriter.writeToFile("out/output.txt", records);
    }

    private static String getFilePathFromArgs(String[] args) {
        if (args != null && args.length == 1) {
            return args[0];
        }
        throw new IllegalArgumentException("No path to file in arguments.");
    }
}
