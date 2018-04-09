package br.com.storeadmin.model.Store;

import br.com.storeadmin.model.EnumModel.EStoreAttribute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="CONTACT_TYPE")
public class ContactType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="CTT_ID")
    private long id;

    @Column(name="CTT_NAME")
    private String name;

    @Column(name="CTT_TYPE")
    private String type;

    @Temporal(TemporalType.DATE)
    @Column(name="CTT_CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name="CTT_MOD_DATE")
    private Date modifiedDate;

    public ContactType()
    {

    }
    public ContactType(String name, String type) {
        this.name = name;
        this.type = type;
    }


    @PrePersist
    @PreUpdate
    public void prePersist()
    {
        modifiedDate = new Date();
        if(createDate == null)
        {
            createDate = modifiedDate;
        }
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
}
