package Lombok.model.taskModel;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "efp_data", schema = "public", catalog = "etpreport")
public class EfpData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column (name = "task_id", insertable = false, updatable = false)
   // @ForeignKey(name = "task_id")
    private Long task_id;

    @Column(name = "wso2_api_name")
    private String wso2ApiName;

    @Column(name = "wso2_api_endpoint")
    private String wso2ApiEndpoint;

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

    public String getWso2ApiName() {
        return wso2ApiName;
    }

    public void setWso2ApiName(String wso2ApiName) {
        this.wso2ApiName = wso2ApiName;
    }

    public String getWso2ApiEndpoint() {
        return wso2ApiEndpoint;
    }

    public void setWso2ApiEndpoint(String wso2ApiEndpoint) {
        this.wso2ApiEndpoint = wso2ApiEndpoint;
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
        return "EfpData{" +
                "id=" + id +
                ", task_id=" + task_id +
                ", wso2ApiName='" + wso2ApiName + '\'' +
                ", wso2ApiEndpoint='" + wso2ApiEndpoint + '\'' +
                ", trText='" + trText + '\'' +
                ", pmiTaskText='" + pmiTaskText + '\'' +
                ", pmiResultText='" + pmiResultText + '\'' +
                '}';
    }
}
