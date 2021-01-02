package com.github.caijh.commons.util;

import java.util.Date;

public class DateRange {

    private final Date startTime;

    private final Date endTime;

    public DateRange(Date startTime, Date end) {
        this.startTime = startTime;
        this.endTime = end;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

}
