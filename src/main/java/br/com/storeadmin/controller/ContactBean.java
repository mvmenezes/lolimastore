package br.com.storeadmin.controller;


import br.com.storeadmin.business.ContactBO;
import br.com.storeadmin.dao.AddressTypeDAO;
import br.com.storeadmin.dao.ContactTypeDAO;
import br.com.storeadmin.model.EnumModel.EYesNo;
import br.com.storeadmin.model.Store.*;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "contactBean")
@NoneScoped
public class ContactBean implements Serializable, Bean {


    private Customer customer;
    private Contact contact;
    private long typeContactID;
    private boolean mainContact;
    private boolean wppContact;
    private ContactTypeDAO daoContactType;
    private Set<Contact> contactList;
    private List<ContactType> contactTypeList;
    private ContactBO contactBO;
    public ContactBean() {
        init();
    }


    protected final String MSG_ID_ADDRESS = "msgAddress";
    protected final String MSG_ID_GROW_INDEX = "GrowMessage";
    protected final String MSG_ID_CONTACT = "msgContact";
    protected final String MSG_ID_IMPORT = "messageImport";



    @PostConstruct
    public void start()
    {
        init();
        refreshStaticInformation();
        lastValidation();
    }


    public void addMsgInfo(String componentID, String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msg));
    }
    public void addMsgWarn(String componentID,String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
    }

    public void addMsgError(String componentID,String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
    }

    public void addMsgFatal(String componentID,String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal!", msg));
    }
    public void addMsgInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msg));
    }

    public void addMsgWarn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
    }

    public void addMsgError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
    }

    public void addMsgFatal(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal!", msg));
    }


    public void init() {
        daoContactType = new ContactTypeDAO();
        customer = new Customer();
        contact = new Contact();
        contactList = new HashSet<Contact>(5);
        contactTypeList = new ArrayList<ContactType>();
        contactBO = new ContactBO();
    }





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
        contactTypeList =contDAO.findAllList();
    }

    public void lastValidation() {
        if(contactList.size() == 0)
        {
            mainContact = true;
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
        contactList.add(contact);
        customer.setContacts(contactList);
        addMsgInfo(MSG_ID_CONTACT,"Contato adicionado com sucesso!");

        contact = new Contact();
    }



    public void onContactRowEdit(Contact event) {

        Contact cont = (Contact) event;//.getObject();
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



    public void removeContact(Contact c)
    {
        contactList.remove(c);
        customer.setContacts(contactList);
        if(contactList.size() > 0 && c.isFavorite())
        {
            markAsFavorite(contactList.stream().findFirst().get());
        }

        if(c.getId() > 0)
        {
            contactBO.delete(c);
        }
        addMsgInfo(MSG_ID_CONTACT,"Contato removido com sucesso!");
    }
    public void onContactCancel(RowEditEvent event) {
        int u = 0;
    }

    @Override
    public void save(ActionEvent e) {

        }

    @Override
    public void remove() {

    }

    @Override
    public void add() {

    }

    public void resetVariables()
    {
        customer = new Customer();
        contact = new Contact();
        contactList = new HashSet<Contact>(5);
        mainContact = false;
        wppContact = false;
    }
    /*  GET AND SETTERS */




    public void setCustomer(Customer customer) {
        this.customer = customer;
        contactList.forEach(x -> x.setCustomer(this.customer));
    }

    public Customer getCustomer() {
        return customer;
    }


    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Set<Contact> contactList) {
        this.contactList = contactList;
    }


    public List<ContactType> getContactTypeList() {
        return contactTypeList;
    }


    public void setContactTypeList(List<ContactType> contactTypeList) {
        this.contactTypeList = contactTypeList;
    }


    public long getTypeContactID() {
        return typeContactID;
    }

    public void setTypeContactID(long typeContactID) {
        this.typeContactID = typeContactID;
    }

    public boolean isMainContact() {
        return mainContact;
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

}
