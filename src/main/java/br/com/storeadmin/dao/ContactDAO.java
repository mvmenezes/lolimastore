package br.com.storeadmin.dao;

import br.com.storeadmin.model.Store.Contact;
import br.com.storeadmin.model.Store.Customer;

public class ContactDAO extends GenericDAO<Contact>{

    public ContactDAO()
    {
        super(Contact.class);
    }


    @Override
    public void save(Contact entity) throws Exception {
        super.save(entity);

    }
}
