package br.com.storeadmin.business;

import br.com.storeadmin.dao.AddressTypeDAO;
import br.com.storeadmin.model.Store.AddressType;

public class AddressTypeBO
{

    AddressTypeDAO cDAO;


    public AddressTypeBO()
    {
        cDAO = new AddressTypeDAO();
    }
    public AddressType getTypeAddressByName(String s) {
        return cDAO.findByJPQL("where p.name ='"+s+"'").get(0);

    }
}
