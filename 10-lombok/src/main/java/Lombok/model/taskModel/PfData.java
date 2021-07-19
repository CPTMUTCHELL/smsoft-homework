package Lombok.model.taskModel;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "pf_data", schema = "public", catalog = "etpreport")
public class PfData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column (name = "task_id", insertable = false, updatable = false)
    // @ForeignKey(name = "task_id")
    private Long task_id;

    @Column(name = "work_description")
    private String workDescription;

    @Column(name = "tr_text")
    private String trText;

    @Column(name = "pmi_task_text")
    private String pmiTaskText;

    @Column(name = "pmi_result_text")
    private String pmiResultText;

    @OneToOne
    @JoinColumn(name = "task_source_id", referencedColumnName = "id")
    @ToString.Exclude
    private Task taskSourceId;


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

    public Task getTaskSourceId() {
        return taskSourceId;
    }

    public void setTaskSourceId(Task taskSourceId) {
        this.taskSourceId = taskSourceId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "PfData{" +
                "id=" + id +
                ", task_id=" + task_id +
                ", workDescription='" + workDescription + '\'' +
                ", trText='" + trText + '\'' +
                ", pmiTaskText='" + pmiTaskText + '\'' +
                ", pmiResultText='" + pmiResultText + '\'' +
                '}';
    }
}
