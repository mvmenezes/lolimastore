package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ATTRIBUTE")
public class Attribute implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ATT_ID")
    private long id;
    @Column(name = "ATT_NAME")
    private String name;
    @Column(name = "ATT_DESCRIPTION")
    private String description;
    @ManyToOne
    @JoinColumn(name="ATC_ID")
    private AttributesClass attributeClass;

    @Temporal(TemporalType.DATE)
    @Column(name="ATT_CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name="ATT_MOD_DATE")
    private Date modifiedDate;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public AttributesClass getAttributeClass() {
        return attributeClass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttributeClass(AttributesClass attributeClass) {
        this.attributeClass = attributeClass;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
}
