package com.smsoft.dto;

import com.smsoft.entity.Status;
import java.util.List;

public class StatusDto  {
    private long count;
    private List<Status> statuses;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
