package com.linked.task.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class BusRecord {
    private BusCompany busCompany;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @Override
    public String toString() {
        return String.format("%s %s %s", busCompany, departureTime, arrivalTime);
    }
}
