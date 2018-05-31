package br.com.storeadmin.business;

import br.com.storeadmin.WS.SearchCEPWS;
import br.com.storeadmin.dao.AddressDAO;
import br.com.storeadmin.dao.ContactDAO;
import br.com.storeadmin.model.Store.Address;
import br.com.storeadmin.model.Store.AddressType;
import br.com.storeadmin.model.Store.Contact;

import java.util.Set;

public class AddressBO {


    private AddressDAO cDAO;

    public AddressBO()
    {
        cDAO = new AddressDAO();
    }

    public void update(Address entity)
    {
        cDAO.update(entity);
    }
    public void save(Address entity)throws Exception
    {
        cDAO.save(entity);
    }
    public void delete(Address entity) {
        cDAO.delete(entity);
    }

    public Set<Address> getAll()
    {
        return cDAO.findAll();
    }

    public Address searchZipCode(String zipCode)
    {
        Address ad = null;
        SearchCEPWS ws = new SearchCEPWS();
        if(!zipCode.equals("")) {
            ad = ws.searchAddress(zipCode);
        }
        return ad;
    }


}
