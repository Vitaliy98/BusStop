package com.linked.task.utils;

import com.linked.task.entities.BusRecord;

import java.time.temporal.ChronoUnit;

public class BusRecordUtils {
    public static boolean isStartsAtSameTime(BusRecord firstRecord, BusRecord secondRecord) {
        return firstRecord.getDepartureTime().equals(secondRecord.getDepartureTime());
    }

    public static boolean isStartsLater(BusRecord firstRecord, BusRecord secondRecord) {
        return firstRecord.getDepartureTime().isAfter(secondRecord.getDepartureTime());
    }

    public static boolean isArriveAtSameTime(BusRecord firstRecord, BusRecord secondRecord) {
        return firstRecord.getArrivalTime().equals(secondRecord.getArrivalTime());
    }

    public static boolean isArriveEarlier(BusRecord firstRecord, BusRecord secondRecord) {
        return firstRecord.getArrivalTime().isBefore(secondRecord.getArrivalTime());
    }

    public static boolean isStartsEarlier(BusRecord firstRecord, BusRecord secondRecord) {
        return firstRecord.getDepartureTime().isBefore(secondRecord.getDepartureTime());
    }

    public static boolean isTakeMoreThanHour(BusRecord busRecord) {
        return ChronoUnit.HOURS.between(busRecord.getArrivalTime(), busRecord.getDepartureTime()) >= 1;
    }
}
