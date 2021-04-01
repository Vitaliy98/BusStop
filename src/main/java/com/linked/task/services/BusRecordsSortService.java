package com.linked.task.services;

import com.linked.task.entities.BusRecord;

import java.util.List;

public interface BusRecordsSortService {
    /**
     * Sort all values in list.
     * @param busRecords list that should be sorted.
     */
    void sortAllRecords(List<BusRecord> busRecords);
}
