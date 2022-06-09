package com.tau.rest3d;

import lombok.Data;

@Data
public class PrintInfo {

    private String timetoPrint;
    private String costOfPrint;

    public PrintInfo(String timetoPrint, String costOfPrint, String plasticUsed) {
        this.timetoPrint = timetoPrint;
        this.costOfPrint = costOfPrint;
        this.plasticUsed = plasticUsed;
    }

    private String plasticUsed;

}
