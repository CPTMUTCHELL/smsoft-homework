package Lombok.model.taskModel;


import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "task", schema = "public", catalog = "etpreport")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "num")
    private String num;

    @Column(name = "type_label")
    private String typeLabel;

    @Column(name = "complexity_label")
    private String complexityLabel;

    @Column(name = "work_label_pz")
    private String workLabelPz;

    @Column(name = "work_label_pmi")
    private String workLabelPmi;

    @Column(name = "task_order")
    private Long taskOrder;

    @Column(name = "service_name")
    private String serviceName;

    @Type(type = "list-array")
    @Column(name = "service_code", columnDefinition = "integer[]")
    private List<Integer> serviceCode;

    @Column(name = "mode_label")
    private String modeLabel;

    @Column(name = "contract_stage_id")
    private Long contractStageId;

    @Column(name = "template_type_label")
    private String templateTypeLabel;

    @Column(name = "template_word_label")
    private String templateWordLabel;

    @Column(name = "chtz_num")
    private String chtzNum;

    @OneToOne(mappedBy = "task")
    private PfData pfData;

    @OneToOne(mappedBy = "task")
    private EtpData etpData;

    @OneToOne(mappedBy = "task")
    private EfpData efpData;

    @OneToOne(mappedBy = "task")
    private KiData kiData;

    @ManyToOne
    @JoinColumn(name = "inf_system_code")
    private DictSubsystems dictSubsystems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    public String getComplexityLabel() {
        return complexityLabel;
    }

    public void setComplexityLabel(String complexityLabel) {
        this.complexityLabel = complexityLabel;
    }

    public String getWorkLabelPz() {
        return workLabelPz;
    }

    public void setWorkLabelPz(String workLabelPz) {
        this.workLabelPz = workLabelPz;
    }

    public String getWorkLabelPmi() {
        return workLabelPmi;
    }

    public void setWorkLabelPmi(String workLabelPmi) {
        this.workLabelPmi = workLabelPmi;
    }

    public Long getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(Long taskOrder) {
        this.taskOrder = taskOrder;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<Integer> getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(List<Integer> serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getModeLabel() {
        return modeLabel;
    }

    public void setModeLabel(String modeLabel) {
        this.modeLabel = modeLabel;
    }

    public Long getContractStageId() {
        return contractStageId;
    }

    public void setContractStageId(Long contractStageId) {
        this.contractStageId = contractStageId;
    }

    public String getTemplateTypeLabel() {
        return templateTypeLabel;
    }

    public void setTemplateTypeLabel(String templateTypeLabel) {
        this.templateTypeLabel = templateTypeLabel;
    }

    public String getTemplateWordLabel() {
        return templateWordLabel;
    }

    public void setTemplateWordLabel(String templateWordLabel) {
        this.templateWordLabel = templateWordLabel;
    }

    public String getChtzNum() {
        return chtzNum;
    }

    public void setChtzNum(String chtzNum) {
        this.chtzNum = chtzNum;
    }

    public PfData getPfData() {
        return pfData;
    }

    public void setPfData(PfData pfData) {
        this.pfData = pfData;
    }

    public EtpData getEtpData() {
        return etpData;
    }

    public void setEtpData(EtpData etpData) {
        this.etpData = etpData;
    }

    public EfpData getEfpData() {
        return efpData;
    }

    public void setEfpData(EfpData efpData) {
        this.efpData = efpData;
    }

    public KiData getKiData() {
        return kiData;
    }

    public void setKiData(KiData kiData) {
        this.kiData = kiData;
    }

    public DictSubsystems getDictSubsystems() {
        return dictSubsystems;
    }

    public void setDictSubsystems(DictSubsystems dictSubsystems) {
        this.dictSubsystems = dictSubsystems;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", typeLabel='" + typeLabel + '\'' +
                ", complexityLabel='" + complexityLabel + '\'' +
                ", workLabelPz='" + workLabelPz + '\'' +
                ", workLabelPmi='" + workLabelPmi + '\'' +
                ", taskOrder=" + taskOrder +
                ", serviceName='" + serviceName + '\'' +
                ", serviceCode=" + serviceCode +
                ", modeLabel='" + modeLabel + '\'' +
                ", contractStageId=" + contractStageId +
                ", templateTypeLabel='" + templateTypeLabel + '\'' +
                ", templateWordLabel='" + templateWordLabel + '\'' +
                ", chtzNum='" + chtzNum + '\'' +
                ", pfData=" + pfData +
                ", etpData=" + etpData +
                ", efpData=" + efpData +
                ", kiData=" + kiData +
                ", dictSubsystems=" + dictSubsystems +
                '}';
    }
}
