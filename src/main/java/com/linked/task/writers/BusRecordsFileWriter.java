package com.linked.task.writers;

import com.linked.task.entities.BusRecord;

import java.util.List;

public interface BusRecordsFileWriter {
    /**
     * Writes all values to file.
     * @param filePath path to file to be written to.
     * @param busRecords list of values that should be written to file.
     */
    void writeToFile(String filePath, List<BusRecord> busRecords);
}
