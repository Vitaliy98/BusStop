package com.linked.task.services;

import com.linked.task.comparators.BusRecordComparator;
import com.linked.task.entities.BusCompany;
import com.linked.task.entities.BusRecord;
import com.linked.task.services.impl.BusRecordSortServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class BusRecordSortServiceTest {
    BusRecordsSortService sortService = new BusRecordSortServiceImpl();

    @Test
    public void shouldSortValuesByDepartureTime() {
        BusRecord firstRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("10:20"), LocalTime.parse("11:10"));
        BusRecord secondRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("08:10"), LocalTime.parse("09:00"));
        BusRecord thirdRecord = new BusRecord(BusCompany.Grotty, LocalTime.parse("11:10"), LocalTime.parse("11:40"));
        BusRecord fourthRecord = new BusRecord(BusCompany.Grotty, LocalTime.parse("09:10"), LocalTime.parse("10:00"));

        List<BusRecord> records = Arrays.asList(firstRecord, secondRecord, thirdRecord,fourthRecord);

        sortService.sortAllRecords(records);

        Assert.assertEquals(secondRecord, records.get(0));
        Assert.assertEquals(firstRecord, records.get(1));
        Assert.assertEquals(fourthRecord, records.get(2));
        Assert.assertEquals(thirdRecord, records.get(3));
    }
}
