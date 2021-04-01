package com.linked.task.services;

import com.linked.task.entities.BusRecord;

import java.util.List;

public interface BusRecordsFilteringService {
    /**
     * Filter and removing values by some criteria.
     * @param busRecords list of recrods that should be filtered.
     */
    void filterRecords(List<BusRecord> busRecords);
}
