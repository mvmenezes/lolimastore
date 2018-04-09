package br.com.storeadmin.dao;

import br.com.storeadmin.model.Store.AddressType;
import br.com.storeadmin.model.Store.ContactType;

public class ContactTypeDAO extends GenericDAO<ContactType>{

    public ContactTypeDAO()
    {
        super(ContactType.class);
    }
}
