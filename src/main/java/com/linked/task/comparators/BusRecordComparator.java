package com.linked.task.comparators;

import com.linked.task.entities.BusCompany;
import com.linked.task.entities.BusRecord;

import java.util.Comparator;

import static com.linked.task.utils.BusRecordUtils.isStartsAtSameTime;
import static com.linked.task.utils.BusRecordUtils.isStartsEarlier;

public class BusRecordComparator implements Comparator<BusRecord> {
    @Override
    public int compare(BusRecord first, BusRecord second) {
        if (!first.getBusCompany().equals(second.getBusCompany()) && first.getBusCompany().equals(BusCompany.Posh)) {
            return -1;
        }
        if (isStartsAtSameTime(first, second) && first.getBusCompany().equals(BusCompany.Posh)) {
            return -1;
        }
        if (isStartsEarlier(first, second) && first.getBusCompany().equals(second.getBusCompany())) {
            return -1;
        }
        return 1;
    }
}
