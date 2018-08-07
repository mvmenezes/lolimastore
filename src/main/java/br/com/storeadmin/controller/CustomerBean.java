package br.com.storeadmin.controller;


import br.com.storeadmin.WS.SearchCEPWS;
import br.com.storeadmin.business.CustomerBO;
import br.com.storeadmin.dao.AddressTypeDAO;
import br.com.storeadmin.dao.ContactTypeDAO;
import br.com.storeadmin.dao.CustomerDAO;
import br.com.storeadmin.model.Common.ColumnModel;
import br.com.storeadmin.model.EnumModel.EGender;
import br.com.storeadmin.model.EnumModel.EStoreAttribute;
import br.com.storeadmin.model.EnumModel.EYesNo;
import br.com.storeadmin.model.Store.*;
import br.com.storeadmin.util.StoreUtil;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean(name = "customerBean")
@ViewScoped
public class CustomerBean extends BeanModel implements Serializable, Bean {



    @ManagedProperty(value="#{contactBean}")
    private ContactBean contactBean;
    @ManagedProperty(value="#{addressBean}")
    private AddressBean addressBean;

    private Set<Customer> customersList;
    private List<Customer> customersFilteredList;
    private Customer customer;
    private CustomerBO customerBO;
    private boolean company;
    private int tabIndex;
    private DataTable dataTableCustomers;

    public CustomerBean()
    {
    }


    @Override
    public void init()
    {
        customerBO = new CustomerBO();
        customer = new Customer();
        customersList = new HashSet<Customer>();
        customersFilteredList = new ArrayList<Customer>();
        String s = EGender.MALE;
        customer.setType("F");
    }




    @Override
    public void refreshStaticInformation()
    {
        List<Customer> lista = customerBO.getAll().stream().collect(Collectors.toList());


        Collections.sort(lista, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        customersList = lista.stream().collect(Collectors.toSet());
        customersFilteredList = lista;

        PrimeFaces.current().ajax().update("formSearchClient:tblSearchClient");

    }

    @Override
    public void lastValidation() {

    }


    public void onChangePerson(ValueChangeEvent evt)
    {
        if(evt.getNewValue().toString().equals("J"))
        {
            company=true;
        }
        else
        {
            company=false;
        }
    }
    @Override
    public void save(ActionEvent e) {
        try
        {
            customer.setAddresses(addressBean.getAddressList());
            customer.setContacts(contactBean.getContactList());
            addressBean.setCustomer(customer);
            contactBean.setCustomer(customer);

            if(customer.getId() == 0)
            {
                customerBO.save(customer);
            }else
            {
                customerBO.update(customer);
            }
            resetVariables();
            addMsgInfo(MSG_ID_GROW_INDEX,"Cliente inserido com sucesso!");
            refreshStaticInformation();
            PrimeFaces.current().executeScript("PF('dlgNewCustomer').hide();");
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
    public void editCustomer(Customer customer) {
        this.customer = customerBO.findById(customer.getId());
        contactBean.setContactList(customer.getContacts());
        addressBean.setAddressList(customer.getAddresses());
    }

    private int sortByName(String name1, String name2)
    {
        return name1.compareToIgnoreCase(name2);

    }
    private void resetVariables()
    {
        customer = new Customer();
        addressBean.resetVariables();
        contactBean.resetVariables();
    }
    /*  GET AND SETTERS */



    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    public AddressBean getAddressBean() {
        return addressBean;
    }

    public ContactBean getContactBean() {
        return contactBean;
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }

    public void setContactBean(ContactBean contactBean) {
        this.contactBean = contactBean;
    }

    public Set<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(Set<Customer> customersList) {
        this.customersList = customersList;
    }

    public boolean isCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public List<Customer> getCustomersFilteredList() {
        return customersFilteredList;
    }

    public void setCustomersFilteredList(List<Customer> customersFilteredList) {
        this.customersFilteredList = customersFilteredList;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public CustomerBO getCustomerBO() {
        return customerBO;
    }

    public DataTable getDataTableCustomers() {
        return dataTableCustomers;
    }

    public void setDataTableCustomers(DataTable dataTableCustomers) {
        this.dataTableCustomers = dataTableCustomers;
    }
}
