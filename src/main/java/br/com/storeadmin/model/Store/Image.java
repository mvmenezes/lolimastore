package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="IMAGE")
public class Image  implements Serializable {


    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="IMG_ID")
    private long id;
    @Column(name="IMG_PATH")
    private String path;
    @ManyToOne
    @JoinColumn(name="PRD_ID")
    private Product product;
    @Temporal(TemporalType.DATE)
    @Column(name="IMG_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="IMG_MOD_DATE")
    private Date modifiedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
