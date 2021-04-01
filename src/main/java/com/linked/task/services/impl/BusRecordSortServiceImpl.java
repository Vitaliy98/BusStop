package com.linked.task.services.impl;

import com.linked.task.comparators.BusRecordComparator;
import com.linked.task.entities.BusRecord;
import com.linked.task.services.BusRecordsSortService;

import java.util.List;

public class BusRecordSortServiceImpl implements BusRecordsSortService {
    private final BusRecordComparator busRecordComparator = new BusRecordComparator();

    public void sortAllRecords(List<BusRecord> busRecords) {
        busRecords.sort(busRecordComparator);
    }
}
