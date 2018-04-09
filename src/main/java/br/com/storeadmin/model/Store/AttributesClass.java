package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ATTRIBUTE_CLASS")
public class AttributesClass implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ATC_ID")
    private long id;
    @Column(name="ATC_NAME")
    private String name;
    @OneToOne(mappedBy = "attributeClass")
    private ProductType productType;
    @OneToMany(mappedBy="attributeClass")
    private List<Attribute> attributes;

    @Temporal(TemporalType.DATE)
    @Column(name="ATC_CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name="ATC_MOD_DATE")
    private Date modifiedDate;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
