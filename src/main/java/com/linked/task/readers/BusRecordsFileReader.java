package com.linked.task.readers;

import com.linked.task.entities.BusRecord;

import java.util.List;

/**
 * Interface for reading and parsing values from files into list of BusRecord objects.
 */
public interface BusRecordsFileReader {
    /**
     * Read all values from file and parse string lines into list of BusRecord objects.
     * @param filePath path to file that should be parsed.
     * @return list of BusRecord objects parsed from file.
     */
    List<BusRecord> readAllValues(String filePath);
}
