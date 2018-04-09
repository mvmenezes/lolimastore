package br.com.storeadmin.dao;

import br.com.storeadmin.model.Store.Customer;

public class CustomerDAO extends GenericDAO<Customer>{

    public CustomerDAO()
    {
        super(Customer.class);
    }
}
