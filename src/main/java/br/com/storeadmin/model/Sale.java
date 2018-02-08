package br.com.storeadmin.model;

import java.util.Date;
import java.util.List;

public class Sale {

    private List<Product> products;
    private Retailer retailer;
    private Date purchaseDate;
    private List<Payment> payments;
    private Customer customer;
    private String note;
}
