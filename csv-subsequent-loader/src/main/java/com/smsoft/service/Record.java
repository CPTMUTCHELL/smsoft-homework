package com.smsoft.service;

import javax.persistence.*;
import java.util.Date;

//import static com.smsoft.service.Service.convertFromCSVFormat;

@Entity

public class Record {

    @Id
    @GeneratedValue
    private Long id;
    private String ssoid;
    private String ts;
    private String grp;
    private String type;
    private String subtype;
    private String url;
    private String orgid;
    private String formid;
    private String code;
    private String ltpa;
    private String sudirresponse;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ymdh;

    public Record() {
    }

    public Record(String recordData) {

        String[] splittedData = recordData.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        this.ssoid = splittedData[0];
        this.ts = splittedData[1];
        this.grp = splittedData[2];
        this.type = splittedData[3];
        this.subtype = splittedData[4];
        this.url = splittedData[5];
        this.orgid = splittedData[6];
        this.formid = splittedData[7];
        this.code = splittedData[8];
        this.ltpa = splittedData[9];
        this.sudirresponse = splittedData[10];
        this.ymdh =  Service.convertFromCSVFormat(splittedData[11]);
    }
}