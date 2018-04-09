package br.com.storeadmin.model.Store;

import br.com.storeadmin.model.EnumModel.EStoreAttribute;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ADDRESS_TYPE")
public class AddressType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ADT_ID")
    private long id;

    @Column(name="ADT_NAME")
    private String name;

    @Column(name="ADT_TYPE")
    private String type;

    @Temporal(TemporalType.DATE)
    @Column(name="ADT_CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name="ADT_MOD_DATE")
    private Date modifiedDate;

    public AddressType()
    {

    }
    public AddressType(String s, String t) {
        name = s;
        type = t;
    }


    @PrePersist
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
