package br.com.storeadmin.business;

import br.com.storeadmin.dao.CustomerDAO;
import br.com.storeadmin.model.EnumModel.ECustomerType;
import br.com.storeadmin.model.EnumModel.EGender;
import br.com.storeadmin.model.EnumModel.EYesNo;
import br.com.storeadmin.model.Store.Address;
import br.com.storeadmin.model.Store.Contact;
import br.com.storeadmin.model.Store.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustomerBO extends BOModel{


    private CustomerDAO cDAO;

    public CustomerBO()
    {
        cDAO = new CustomerDAO();
    }

    public void update(Customer customer)
    {
        cDAO.update(customer);
    }
    public void save(Customer customer)throws Exception
    {

        if(customer.getCode() != null)
            customer.setCode(customer.getCode().toUpperCase());
        cDAO.save(customer);
    }

    public Set<Customer> getAll()
    {
        return cDAO.findAll();
    }

    @Override
    public List importSheet(List sheetLines) {
        Customer customer;
        Address address;
        Contact contact;
        ArrayList<String> results;
        int qtd;

        results = new ArrayList<String>();
        if(sheetLines == null) {
            return null;
        }
        qtd = sheetLines.size();
        for(int pos = 2; pos < qtd; pos++)
        {
            try
            {
                String line = (String)sheetLines.get(pos);
                String[] array = line.split(";");

                customer = new Customer();
                customer.setName(array[0]);
                if(array[1] != null && array[1].equalsIgnoreCase(ECustomerType.FISICA))
                {
                    customer.setType(array[1]);
                    customer.setCpf(array[2]);
                }else if(array[1] != null && array[1].equalsIgnoreCase(ECustomerType.JURIDICA))
                {
                    customer.setType(array[1]);
                    customer.setCNPJ(array[2]);
                }
                customer.setEmail(array[4]);
                if(array[5] != null && array[5].equalsIgnoreCase(EGender.MALE))
                {
                    customer.setGender(array[5]);
                }else if(array[5] != null && array[5].equalsIgnoreCase(EGender.FEMALE))
                {
                    customer.setGender(array[5]);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    customer.setBirthDate(sdf.parse(array[6]));
                }catch(ParseException e)
                {

                }
                customer.setFacebook(array[7]);
                customer.setInstagram(array[8]);
                customer.setNote(array[9]);
                //     Nome	Tipo	CPF/CNPJ	Codigo	E-mail	Sexo	Nascimento	Facebook	Instagram	Observação	Tipo Endereço	CEP	Número	País	Tipo Contato	Contato	Observação
                if(array[10] != null && array[11] != null)
                {
                    address = new Address();
                    AddressBO adBO = new AddressBO();
                    address = adBO.searchZipCode(array[11]);
                    if(address == null)
                    {
                        continue;
                    }

                    address.setType(new AddressTypeBO().getTypeAddressByName(array[10]));
                    address.setNumber(array[13]);
                    address.setMain(EYesNo.YES);
                    address.setCountry(array[12]);
                    address.setCustomer(customer);
                    HashSet<Address> hashSet = new HashSet<Address>();
                    hashSet.add(address);
                    customer.setAddresses(hashSet);
                }
                if(array[13] != null && array[14] != null)
                {
                    contact = new Contact();
                    ContactBO cBO = new ContactBO();

                    contact.setType(new ContactTypeBO().getTypeContactByName(array[13]));
                    contact.setNumber(array[14]);
                    contact.setNote(array[15]);
                    contact.setMain(EYesNo.YES);
                    contact.setCustomer(customer);
                    HashSet<Contact> hashSet = new HashSet<Contact>();
                    hashSet.add(contact);
                    customer.setContacts(hashSet);
                }
                save(customer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
