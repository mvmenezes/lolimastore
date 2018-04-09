package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PAYMENT")
public class Payment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PAY_ID")
    private long id;
    @OneToOne
    @JoinColumn(name = "CTM_ID")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "SAL_ID")
    private Sale sale;
    @Column(name="PAY_VALUE")
    private double paymentValue;
    @Column(name="PAY_VALUE_PAID")
    private double valuePaid;
    @Column(name="PAY_DATE_PAYMENT")
    private Date paymentDate;
    @Column(name="PAY_PAID")
    private String paid;
    @Column(name="PAY_ACTIVE")
    private String active;
    @Temporal(TemporalType.DATE)
    @Column(name="PAY_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="PAY_MOD_DATE")
    private Date modifiedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public double getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(double paymentValue) {
        this.paymentValue = paymentValue;
    }

    public double getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(double valuePaid) {
        this.valuePaid = valuePaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
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
