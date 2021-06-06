package com.smsoft.entity;

import com.smsoft.service.DateConverterService;

import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class Status {
    private String id;

    private String serviceNumber;
   private String statusCode;

   private Date created;
   private String msgId;
   private Date put;
   private String reasonCode;
   private String route;
   private String direction;

    public Status() {
    }

    public Status(String recordData) {
        String[] split = recordData.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        this.id = split[0];
        this.serviceNumber = split[1];
        this.statusCode = split[2];
        this.created = DateConverterService.convertFromCSVFormat(split[3]);
        this.msgId = split[4];
        this.put = DateConverterService.convertFromCSVFormat(split[5]);
        this.reasonCode = split[6];
        this.route = split[7];
        this.direction = split[8];
    }

    @Override
    public String toString() {
        return "Status{" +
                "id='" + id + '\'' +
                ", serviceNumber='" + serviceNumber + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", created=" + created +
                ", msgId='" + msgId + '\'' +
                ", put=" + put +
                ", reasonCode='" + reasonCode + '\'' +
                ", route='" + route + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public Date getCreated() {
        return created;
    }

    public String getMsgId() {
        return msgId;
    }

    public Date getPut() {
        return put;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getRoute() {
        return route;
    }

    public String getDirection() {
        return direction;
    }
}
