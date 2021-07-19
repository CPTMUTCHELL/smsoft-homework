package Lombok.model.taskModel;


import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "etp_data", schema = "public", catalog = "etpreport")
public class EtpData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column (name = "task_id", insertable = false, updatable = false)
    // @ForeignKey(name = "task_id")
    private Long task_id;

    @Column(name = "service_number")
    private String serviceNumber;

    @Column(name = "front_request")
    private String frontRequest;

    @Column(name = "front_headers")
    private String frontHeaders;

    @Column(name = "app_queue")
    private String appQueue;

    @Column(name = "app_queue_qm")
    private String appQueueQm;

    @Column(name = "status_queue")
    private String statusQueue;

    @Column(name = "status_queue_qm")
    private String statusQueueQm;

    @Column(name = "oiv_status")
    private String oivStatus;

    @Column(name = "work_description")
    private String workDescription;

    @Column(name = "tr_text")
    private String trText;

    @Column(name = "pmi_task_text")
    private String pmiTaskText;

    @Column(name = "pmi_result_text")
    private String pmiResultText;

    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getFrontRequest() {
        return frontRequest;
    }

    public void setFrontRequest(String frontRequest) {
        this.frontRequest = frontRequest;
    }

    public String getFrontHeaders() {
        return frontHeaders;
    }

    public void setFrontHeaders(String frontHeaders) {
        this.frontHeaders = frontHeaders;
    }

    public String getAppQueue() {
        return appQueue;
    }

    public void setAppQueue(String appQueue) {
        this.appQueue = appQueue;
    }

    public String getAppQueueQm() {
        return appQueueQm;
    }

    public void setAppQueueQm(String appQueueQm) {
        this.appQueueQm = appQueueQm;
    }

    public String getStatusQueue() {
        return statusQueue;
    }

    public void setStatusQueue(String statusQueue) {
        this.statusQueue = statusQueue;
    }

    public String getStatusQueueQm() {
        return statusQueueQm;
    }

    public void setStatusQueueQm(String statusQueueQm) {
        this.statusQueueQm = statusQueueQm;
    }

    public String getOivStatus() {
        return oivStatus;
    }

    public void setOivStatus(String oivStatus) {
        this.oivStatus = oivStatus;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getTrText() {
        return trText;
    }

    public void setTrText(String trText) {
        this.trText = trText;
    }

    public String getPmiTaskText() {
        return pmiTaskText;
    }

    public void setPmiTaskText(String pmiTaskText) {
        this.pmiTaskText = pmiTaskText;
    }

    public String getPmiResultText() {
        return pmiResultText;
    }

    public void setPmiResultText(String pmiResultText) {
        this.pmiResultText = pmiResultText;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "EtpData{" +
                "id=" + id +
                ", task_id=" + task_id +
                ", serviceNumber='" + serviceNumber + '\'' +
                ", frontRequest='" + frontRequest + '\'' +
                ", frontHeaders='" + frontHeaders + '\'' +
                ", appQueue='" + appQueue + '\'' +
                ", appQueueQm='" + appQueueQm + '\'' +
                ", statusQueue='" + statusQueue + '\'' +
                ", statusQueueQm='" + statusQueueQm + '\'' +
                ", oivStatus='" + oivStatus + '\'' +
                ", workDescription='" + workDescription + '\'' +
                ", trText='" + trText + '\'' +
                ", pmiTaskText='" + pmiTaskText + '\'' +
                ", pmiResultText='" + pmiResultText + '\'' +
                '}';
    }
}
