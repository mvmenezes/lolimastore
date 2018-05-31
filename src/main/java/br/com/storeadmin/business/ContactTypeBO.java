package br.com.storeadmin.business;

import br.com.storeadmin.dao.AddressTypeDAO;
import br.com.storeadmin.dao.ContactTypeDAO;
import br.com.storeadmin.model.Store.AddressType;
import br.com.storeadmin.model.Store.ContactType;

public class ContactTypeBO
{

    ContactTypeDAO cDAO;


    public ContactTypeBO()
    {
        cDAO = new ContactTypeDAO();
    }
    public ContactType getTypeContactByName(String s) {
        return cDAO.findByJPQL("where p.name ='"+s+"'").get(0);

    }
}
