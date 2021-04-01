package com.linked.task.services;

import com.linked.task.entities.BusCompany;
import com.linked.task.entities.BusRecord;
import com.linked.task.services.impl.BusRecordsFilteringSortedRecordsService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BusRecordsFilteringSortedRecordsServiceTest {
    BusRecordsFilteringService filteringService = new BusRecordsFilteringSortedRecordsService();

    /**
     * Test should check that all ineffective records are founded and removed from list.
     * Criteria for effective records:
     * 1. If it starts at the same time and reaches earlier.
     * 2. If it starts later and reaches at the same time.
     * 3. If it starts later and reaches earlier.
     * 4. If not longer than an hour.
     */
    @Test
    public void shouldFilterAndRemoveIneffectiveSortedRecords() {
        BusRecord firstRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("08:30"), LocalTime.parse("08:50"));
        BusRecord secondRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("08:30"), LocalTime.parse("09:00"));
        BusRecord thirdRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("09:30"), LocalTime.parse("10:00"));
        BusRecord fourthRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("09:40"), LocalTime.parse("10:00"));
        BusRecord fifthRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("10:30"), LocalTime.parse("11:00"));
        BusRecord sixRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("10:40"), LocalTime.parse("10:55"));
        BusRecord sevenRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("11:20"), LocalTime.parse("15:10"));


        List<BusRecord> records = new ArrayList<>();
        records.add(firstRecord);
        records.add(secondRecord);
        records.add(thirdRecord);
        records.add(fourthRecord);
        records.add(fifthRecord);
        records.add(sixRecord);
        records.add(sevenRecord);

        filteringService.filterRecords(records);

        Assert.assertEquals(3, records.size());
        Assert.assertEquals(firstRecord, records.get(0));
        Assert.assertEquals(fourthRecord, records.get(1));
        Assert.assertEquals(sixRecord, records.get(2));
    }
}
