package Lombok.model.taskModel;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ki_data", schema = "public", catalog = "etpreport")
public class KiData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "list_of_required")
    private String listOfRequired;

    @Column(name = "result_description")
    private String resultDescription;

    @Column(name = "contact")
    private String contact;

    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListOfRequired() {
        return listOfRequired;
    }

    public void setListOfRequired(String listOfRequired) {
        this.listOfRequired = listOfRequired;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "KiData{" +
                "id=" + id +
                ", listOfRequired='" + listOfRequired + '\'' +
                ", resultDescription='" + resultDescription + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
