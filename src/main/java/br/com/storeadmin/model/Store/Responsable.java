package br.com.storeadmin.model.Store;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="RESPONSABLE")
public class Responsable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RSP_ID")
    private long id;
    @Column(name="RSP_TYPE")
    private String ResponsableType;
    @Column(name="RSP_REASON")
    private String reason;
    @Column(name="RSP_QUANTITY")
    private int quantity;
    @OneToOne
    @JoinColumn(name = "FAC_ID")
    private Factory factory;
    @OneToOne
    @JoinColumn(name = "CTM_ID")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "PRV_ID")
    private Provider provider;
    @OneToOne
    @JoinColumn(name = "RTL_ID")
    private Retailer retailer;
    @ManyToOne
    @JoinColumn(name = "PRD_ID")
    private Product product;
    @Column(name="RSP_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="RSP_MOD_DATE")
    private Date modifiedDate;

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Factory getFactory() {
        return factory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public Provider getProvider() {
        return provider;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public String getReason() {
        return reason;
    }

    public String getResponsableType() {
        return ResponsableType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResponsableType(String responsableType) {
        ResponsableType = responsableType;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
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
