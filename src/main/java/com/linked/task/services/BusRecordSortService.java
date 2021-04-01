package com.linked.task.services;

import com.linked.task.comparators.BusRecordComparator;
import com.linked.task.entities.BusCompany;
import com.linked.task.entities.BusRecord;
import com.linked.task.utils.BusRecordUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static com.linked.task.entities.BusCompany.Grotty;
import static com.linked.task.utils.BusRecordUtils.*;

public class BusRecordSortService implements SortService {
    private final BusRecordComparator busRecordComparator = new BusRecordComparator();

    public void sortAllRecords(List<BusRecord> busRecords) {
        busRecords.sort(busRecordComparator);
        busRecords.removeIf(BusRecordUtils::isTakeMoreThanHour);
        removeIneffectiveRecords(busRecords);
        removeSimilarRecords(busRecords);
    }

    /**
     * Removed ineffective records from list.
     * Effective criteria:
     * 1. Starts at the same time and reaches earlier.
     * 2. Starts later and reaches at the same time.
     * 3. Starts later and reaches earlier.
     * In order to decrease execution time, used the two links and single pass through the collection.
     * @param busRecords records that should be checked.
     */
    protected void removeIneffectiveRecords(List<BusRecord> busRecords) {
        List<BusRecord> recordsToRemove = new ArrayList<>();
        BusRecord firstRecord = busRecords.get(0);

        for (BusRecord secondRecord : busRecords.subList(1, busRecords.size())) {
            if (!firstRecord.getBusCompany().equals(secondRecord.getBusCompany())) {
                firstRecord = secondRecord;
                continue;
            }
            // If it starts at the same time and reaches earlier
            if (isStartsAtSameTime(firstRecord, secondRecord)) {
                if (isArriveEarlier(firstRecord, secondRecord)) {
                    recordsToRemove.add(secondRecord);
                } else {
                    recordsToRemove.add(firstRecord);
                    firstRecord = secondRecord;
                }
                continue;
            }
            // If it starts later and reaches at the same time
            if (isStartsLater(secondRecord, firstRecord) && isArriveAtSameTime(secondRecord, firstRecord)) {
                recordsToRemove.add(firstRecord);
            }
            // If it starts later and reaches earlier
            if (isStartsLater(secondRecord, firstRecord) && isArriveEarlier(secondRecord, firstRecord)) {
                recordsToRemove.add(firstRecord);
            }
            firstRecord = secondRecord;
        }
        busRecords.removeAll(recordsToRemove);
    }


    protected void removeSimilarRecords(List<BusRecord> records) {
        List<BusRecord> recordsToRemove = new ArrayList<>();
        OptionalInt indexOpt = findIndexOfStartCompanyRecords(records, Grotty);

        if (indexOpt.isPresent()) {
            int indexOfGrottyStart = indexOpt.getAsInt();

            List<BusRecord> poshRecords = records.subList(0, indexOfGrottyStart);
            List<BusRecord> grottyRecords = records.subList(indexOfGrottyStart, records.size());

            for (BusRecord record : poshRecords) {
                grottyRecords.stream()
                        .filter(busRecord -> !busRecord.getBusCompany().equals(record.getBusCompany()))
                        .filter(busRecord -> isStartsAtSameTime(busRecord, record) && isArriveAtSameTime(busRecord, record))
                        .findAny()
                        .ifPresent(recordsToRemove::add);
            }
            records.removeAll(recordsToRemove);
        }

    }

    protected OptionalInt findIndexOfStartCompanyRecords(List<BusRecord> records, final BusCompany busCompany) {
        return IntStream.range(0, records.size())
                .filter(i -> busCompany.equals(records.get(i).getBusCompany()))
                .findFirst();
    }
}
