package br.com.storeadmin.business;

import br.com.storeadmin.dao.ContactDAO;
import br.com.storeadmin.dao.CustomerDAO;
import br.com.storeadmin.model.Store.Contact;
import br.com.storeadmin.model.Store.Customer;

import java.util.Set;

public class ContactBO {


    private ContactDAO cDAO;

    public ContactBO()
    {
        cDAO = new ContactDAO();
    }

    public void update(Contact entity)
    {
        cDAO.update(entity);
    }
    public void save(Contact entity)throws Exception
    {
        cDAO.save(entity);
    }

    public void delete(Contact entity) {
        cDAO.delete(entity);
    }
    public Set<Contact> getAll()
    {
        return cDAO.findAll();
    }

}
