package com.linked.task.readers;

import com.linked.task.entities.BusCompany;
import com.linked.task.entities.BusRecord;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

public class BusRecordsTextFileReaderTest {

    BusRecordsFileReader recordsFileReader = new BusRecordsTextFileReaderImpl();

    @Test
    public void shouldCorrectlyReadAllObjectsFromFile() {
        List<BusRecord> busRecords = recordsFileReader.readAllValues("src/test/resources/test.txt");

        BusRecord firstRecord = new BusRecord(BusCompany.Posh, LocalTime.parse("10:20"), LocalTime.parse("11:10"));
        BusRecord secondRecord = new BusRecord(BusCompany.Grotty, LocalTime.parse("10:10"), LocalTime.parse("11:00"));

        Assert.assertEquals(2, busRecords.size());
        Assert.assertEquals(firstRecord, busRecords.get(0));
        Assert.assertEquals(secondRecord, busRecords.get(1));
    }

    @Test
    public void shouldReturnEmptyCollectionForNotExistingFile() {
        List<BusRecord> busRecords = recordsFileReader.readAllValues("test.txt");

        Assert.assertTrue(busRecords.isEmpty());
    }
}
