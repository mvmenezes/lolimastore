package br.com.storeadmin.dao;

import br.com.storeadmin.model.Store.Address;
import br.com.storeadmin.model.Store.Contact;
import br.com.storeadmin.model.Store.Customer;

public class AddressDAO extends GenericDAO<Address>{

    public AddressDAO()
    {
        super(Address.class);
    }


    @Override
    public void save(Address entity) throws Exception {
        super.save(entity);

    }
}
