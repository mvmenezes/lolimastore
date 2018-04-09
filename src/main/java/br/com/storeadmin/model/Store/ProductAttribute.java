package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="PRODUCT_ATTIBUTE")
public class ProductAttribute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAT_ID")
    private long id;
    @Column(name = "PAT_NAME")
    private String name;
    @Column(name = "PAT_DESCRIPTION")
    private String description;
    @ManyToOne
    @JoinColumn(name="PRD_ID")
    Product product;
    @Temporal(TemporalType.DATE)
    @Column(name="PAT_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="PAT_MOD_DATE")
    private Date modifiedDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
