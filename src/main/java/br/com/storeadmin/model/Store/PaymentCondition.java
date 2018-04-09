package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "PAYMENT_CONDITION")
public class PaymentCondition  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PCN_ID")
    private long id;
    @Column(name="PCN_NAME")
    private String name;
    @Column(name="PCN_TYPE")
    private String type;
    @Column(name="PCN_ADVANCED")
    private String paymentAdvanced;
    @Column(name="PCN_DATE_ADVANCED")
    private Date datePaymentAdvanced;
    @Column(name="PCN_ACTIVE")
    private String active;
    @Temporal(TemporalType.DATE)
    @Column(name="PCN_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="PCN_MOD_DATE")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentAdvanced() {
        return paymentAdvanced;
    }

    public void setPaymentAdvanced(String paymentAdvanced) {
        this.paymentAdvanced = paymentAdvanced;
    }

    public Date getDatePaymentAdvanced() {
        return datePaymentAdvanced;
    }

    public void setDatePaymentAdvanced(Date datePaymentAdvanced) {
        this.datePaymentAdvanced = datePaymentAdvanced;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
