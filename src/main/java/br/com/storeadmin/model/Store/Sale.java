package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="SALE")
public class Sale  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="SAL_ID")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*
    private List<Product> products;
    private Retailer retailer;
    private PaymentCondition paymentCondition;
    private Date purchaseDate;
    private List<Payment> payments;
    private Customer customer;
    private String note;
    private double value;
    private double rate;
    private int amountProducts;
    private int quotas;
*/

}
