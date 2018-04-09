package br.com.storeadmin.controller;


import br.com.storeadmin.WS.SearchCEPWS;
import br.com.storeadmin.dao.AddressTypeDAO;
import br.com.storeadmin.dao.ContactTypeDAO;
import br.com.storeadmin.dao.CustomerDAO;
import br.com.storeadmin.model.Common.ColumnModel;
import br.com.storeadmin.model.EnumModel.EGender;
import br.com.storeadmin.model.EnumModel.EStoreAttribute;
import br.com.storeadmin.model.EnumModel.EYesNo;
import br.com.storeadmin.model.Store.*;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "customerBean")
@SessionScoped
public class CustomerBean extends BeanModel implements Serializable, Bean {


    private Customer customer;
    private Contact contact;
    private Address address;
    private long typeAddressID;
    private long typeContactID;
    private boolean company;
    private boolean mainAddress;
    private boolean mainContact;
    private boolean wppContact;
    private String page;
    private CustomerDAO daoCustomer;
    private AddressTypeDAO daoAddressType;
    private ContactTypeDAO daoContactType;
    private List<ColumnModel> columns;
    private List<Address> addressList;
    private List<Contact> contactList;
    private List<AddressType> addressTypeList;
    private List<ContactType> contactTypeList;
    private List<Customer> customersList;
    private DataTable dataTableContacts;
    public CustomerBean() {
        page = "/customer/customerfront";
        init();
    }


    @Override
    public void init() {
        daoCustomer = new CustomerDAO();
        daoAddressType = new AddressTypeDAO();
        daoContactType = new ContactTypeDAO();
        customer = new Customer();
        address = new Address();
        contact = new Contact();
        columns = new ArrayList<ColumnModel>();
        customersList = new ArrayList<Customer>();
        addressList = new ArrayList<Address>(5);
        contactList = new ArrayList<Contact>(5);
        addressTypeList = new ArrayList<AddressType>();
        contactTypeList = new ArrayList<ContactType>();
        page = "/customer/customerpersonal";
        String s = EGender.MALE;
        customer.setType("F");
        company=false;
    }

    public void includeAddress(ActionEvent e) {

        if(typeAddressID > 0)
        {
            address.setType((AddressType)daoAddressType.findByID(typeAddressID));
        }
        if(mainAddress && addressList.size() > 0)
        {
            addressList.forEach(ad -> ad.setMain(EYesNo.NO));
        }
        address.setMain(mainAddress? EYesNo.YES:EYesNo.NO);
        address.setCustomer(customer);
        mainAddress =false;
        if (addressList == null) {
            addressList = (Arrays.asList(address));
        } else {
            addressList.add(address);
        }

        customer.setAddresses(addressList);
        addMsgInfo(MSG_ID_ADDRESS,"Endereço adicionado com sucesso!");

        address = new Address();
    }

    public void resetInputs() {
        PrimeFaces.current().resetInputs("mainForm:j_idt47:divResp");
    }

    public void insertCustomer() {
        int i = 0;
    }

    public void setRegContact() {
        this.page = "/template/register/contacts";
    }

    public void setRegPersonal() {
        this.page = "/customer/customerpersonal";
    }

    public void setRegAdress() {
        this.page = "/customer/customerpersonal";
    }

    @Override
    public void refreshStaticInformation() {
        AddressTypeDAO Adddao = new AddressTypeDAO();
        ContactTypeDAO contDAO = new ContactTypeDAO();
/*
        Adddao.save(new AddressType("COMERCIAL", EStoreAttribute.MASTER));
        Adddao.save(new AddressType("RESIDENCIAL", EStoreAttribute.MASTER));
        Adddao.save(new AddressType("OUTRO", EStoreAttribute.MASTER));
        contDAO.save(new ContactType("COMERCIAL", EStoreAttribute.MASTER));
        contDAO.save(new ContactType("TELEFONE", EStoreAttribute.MASTER));
        contDAO.save(new ContactType("FAX", EStoreAttribute.MASTER));
        contDAO.save(new ContactType("CELULAR", EStoreAttribute.MASTER));
        contDAO.save(new ContactType("OUTRO", EStoreAttribute.MASTER));
*/
        addressTypeList =Adddao.findAll();
        contactTypeList =contDAO.findAll();
        customersList =daoCustomer.findAll();
    }

    @Override
    public void lastValidation() {
        if(addressList.size() == 0)
        {
            mainAddress = true;
        }
        if(contactList.size() == 0)
        {
            mainContact = true;
        }
    }

    public void onChangePerson(ValueChangeEvent evt)
    {
        int t = 0;
        if(evt.getNewValue().toString().equals("J"))
        {
            company=true;
        }
        else
        {
            company=false;
        }
    }

    public void includeContact() {
        if(typeContactID > 0)
        {
            contact.setType((ContactType) daoContactType.findByID(typeContactID));
        }

        if(mainContact && contactList.size() > 0)
        {
            contactList.forEach(contact1 -> contact1.setMain(EYesNo.NO));
            contactList.forEach(contact1 -> contact1.setFavorite(false));
        }
        // Se for o primeiro da lista, coloca como principal
        if(contactList.size() == 0)
        {
            mainContact = true;
        }
        if(wppContact)
        {
            contact.setLinkWpp(Contact.LINK_WPP+ contact.getNumber());
        }
        contact.setMain(mainContact? EYesNo.YES:EYesNo.NO);
        contact.setFavorite(mainContact);
        contact.setCustomer(customer);
        mainContact = false;
        wppContact  = false;
        if (contactList == null) {
            contactList = (Arrays.asList(contact));
        } else {
            contactList.add(contact);
        }

        customer.setContacts(contactList);
        addMsgInfo(MSG_ID_CONTACT,"Contato adicionado com sucesso!");

        contact = new Contact();
    }


    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void onContactRowEdit(RowEditEvent event) {

        Contact cont = (Contact) event.getObject();
        cont.getType().getId();
        try
        {
            ContactType ct = (ContactType)Arrays.asList(contactTypeList.stream().filter(c -> c.getId() == cont.getType().getId()).toArray()).get(0);
            cont.setType(ct);

            // Se editar o contato tem que trocar o link do WhatsApp
            if(cont.getLinkWpp() != null && !cont.getLinkWpp().isEmpty())
            {
                cont.setLinkWpp(Contact.LINK_WPP+ cont.getNumber());
            }

            if(cont.isFavorite()) {
                markAsFavorite(cont);
            }

            addMsgInfo(MSG_ID_CONTACT,"Contato alterado com sucesso!");
        }catch(IndexOutOfBoundsException exp)
        {
            addMsgError(MSG_ID_CONTACT,"Não foi possível alterar o contato!");

        }
    }


    public void markAsFavorite(Contact cont) {


        try
        {
            contactList.forEach(contact1 -> contact1.setMain(EYesNo.NO));
            contactList.forEach(contact1 -> contact1.setFavorite(false));
            contactList.forEach(contact1 -> contact1.setNote(cont.getNumber()));
            cont.setMain(EYesNo.YES);
            cont.setFavorite(true);
            customer.setContacts(contactList);

        }
        catch(IndexOutOfBoundsException exp)
        {
            addMsgError(MSG_ID_CONTACT,"Não foi possível alterar o contato!");
        }
    }


    public void onAddresRowEdit(RowEditEvent event)
    {
        Address adr = (Address) event.getObject();

        adr.getType().getId();
        try
        {
            AddressType ct = (AddressType)Arrays.asList(addressTypeList.stream().filter(c -> c.getId() == adr.getType().getId()).toArray()).get(0);
            adr.setType(ct);
            addMsgInfo(MSG_ID_ADDRESS,"Endereço alterado com sucesso!");
            mainAddress = false;
        }catch(IndexOutOfBoundsException exp)
        {
            addMsgError(MSG_ID_CONTACT,"Não foi possível alterar o Endereço!");
        }
    }

    public void removeContact(Contact c)
    {
        contactList.remove(c);
        customer.setContacts(contactList);
        if(contactList.size() > 0 && c.isFavorite())
        {
            markAsFavorite(contactList.get(0));
        }
        addMsgInfo(MSG_ID_CONTACT,"Contato removido com sucesso!");
    }
    public void removeAddress(Address c)
    {
        addressList.remove(c);
        customer.setAddresses(addressList);
        addMsgInfo(MSG_ID_ADDRESS,"Endereço removido com sucesso!");
    }

    public void searchZipCode()
    {
        SearchCEPWS ws = new SearchCEPWS();
        if(!address.getZipcode().equals("")) {
            address = ws.searchAddress(address.getZipcode());
            if(address == null)
            {
                address = new Address();
                addMsgError("msgSearchZipCode","CEP não encontrado!");
            }else {
                addMsgInfo("msgSearchZipCode","CEP encontrado!");
            }
        }else
        {
            addMsgWarn("msgSearchZipCode","Digite o CEP para pesquisar");

        }
    }
    public void onContactCancel(RowEditEvent event) {

    }

    public void onAddresCancel(RowEditEvent event) {

    }

    @Override
    public void save(ActionEvent e) {
        try
        {

            daoCustomer.save(customer);
            resetVariables();
            addMsgInfo(MSG_ID_GROW_INDEX,"Cliente inserido com sucesso!");
            refreshStaticInformation();
        }
        catch (Exception erro)
        {
            addMsgError(MSG_ID_GROW_INDEX,"Falha ao Inserir o Cliente! Tente novamente!");
        }
    }

    @Override
    public void remove() {

    }

    @Override
    public void add() {

    }

    private void resetVariables()
    {
        customer = new Customer();
        address = new Address();
        contact = new Contact();
        addressList = new ArrayList<Address>(5);
        contactList = new ArrayList<Contact>(5);
        mainAddress = false;
        mainContact = false;
        wppContact = false;
    }
    /*  GET AND SETTERS */


    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<AddressType> getAddressTypeList() {
        return addressTypeList;
    }

    public List<ContactType> getContactTypeList() {
        return contactTypeList;
    }

    public void setAddressTypeList(List<AddressType> addressTypeList) {
        this.addressTypeList = addressTypeList;
    }

    public void setContactTypeList(List<ContactType> contactTypeList) {
        this.contactTypeList = contactTypeList;
    }

    public long getTypeAddressID() {
        return typeAddressID;
    }

    public void setTypeAddressID(long typeAddressID) {
        this.typeAddressID = typeAddressID;
    }

    public long getTypeContactID() {
        return typeContactID;
    }

    public void setTypeContactID(long typeContactID) {
        this.typeContactID = typeContactID;
    }

    public boolean isCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public List<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
    }

    public boolean isMainAddress() {
        return mainAddress;
    }

    public boolean isMainContact() {
        return mainContact;
    }

    public void setMainAddress(boolean mainAddress) {
        this.mainAddress = mainAddress;
    }

    public void setMainContact(boolean mainContact) {
        this.mainContact = mainContact;
    }

    public void setWppContact(boolean wppContact) {
        this.wppContact = wppContact;
    }

    public boolean isWppContact() {
        return wppContact;
    }

    public DataTable getDataTableContacts() {
        return dataTableContacts;
    }

    public void setDataTableContacts(DataTable dataTableContacts) {
        this.dataTableContacts = dataTableContacts;
    }
}
