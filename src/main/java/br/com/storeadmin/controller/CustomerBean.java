package br.com.storeadmin.controller;


import br.com.storeadmin.model.Customer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "customerBean")
@RequestScoped
public class CustomerBean implements Serializable {

    Customer customer;
    public CustomerBean()
    {
        init();
    }

    @PostConstruct
    public void init()
    {
        customer = new Customer();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void insertCustomer()
    {
        int i = 0;
    }
}
