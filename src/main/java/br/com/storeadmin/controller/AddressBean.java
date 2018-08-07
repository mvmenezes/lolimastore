package br.com.storeadmin.controller;


import br.com.storeadmin.WS.SearchCEPWS;
import br.com.storeadmin.business.AddressBO;
import br.com.storeadmin.dao.AddressTypeDAO;
import br.com.storeadmin.dao.ContactTypeDAO;
import br.com.storeadmin.dao.CustomerDAO;
import br.com.storeadmin.model.Common.ColumnModel;
import br.com.storeadmin.model.EnumModel.EGender;
import br.com.storeadmin.model.EnumModel.EYesNo;
import br.com.storeadmin.model.Store.*;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.flow.FlowScoped;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "addressBean")
@NoneScoped
public class AddressBean  implements Serializable, Bean {


    private Customer customer;
    private Address address;
    private long typeAddressID;
    private boolean company;
    private boolean mainAddress;
    private boolean editMode;
    private AddressTypeDAO daoAddressType;
    private AddressBO addressBO;
    private Set<Address> addressList;
    private Set<AddressType> addressTypeList;
    public AddressBean() {
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
        daoAddressType = new AddressTypeDAO();
        customer = new Customer();
        address = new Address();
        addressBO = new AddressBO();
        addressList = new HashSet<Address>(5);
        addressTypeList = new HashSet<AddressType>();
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
        address.setFavorite(mainAddress);
        address.setCustomer(customer);
        mainAddress =false;
        addressList.add(address);
        customer.setAddresses(addressList);

        // Se o evento estiver nulo é pq é a entrada do metodo editar
        if(e != null) {
            try {
               // addressBO.save(address);
                addMsgInfo(MSG_ID_ADDRESS, "Endereço adicionado com sucesso!");
            } catch (Exception erro) {
                int r = 3;
            }
        }
        address = new Address();
    }

    public void refreshStaticInformation() {
        AddressTypeDAO Adddao = new AddressTypeDAO();

        addressTypeList =Adddao.findAll();
    }

    public void lastValidation() {
        if(addressList.size() == 0)
        {
            mainAddress = true;
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



    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }


    public void markAsFavorite(Address adr) {


        try
        {
            addressList.forEach(adr1 -> adr1.setMain(EYesNo.NO));
            adr.setMain(EYesNo.YES);
            adr.setFavorite(true);
            customer.setAddresses(addressList);

        }
        catch(IndexOutOfBoundsException exp)
        {
            addMsgError(MSG_ID_ADDRESS,"Não foi possível alterar o endereço!");
        }
    }


    public void onAddressRowEdit(RowEditEvent event)
    {
        Address adr = (Address) event.getObject();

        adr.getType().getId();
        try
        {
            AddressType ct = (AddressType)Arrays.asList(addressTypeList.stream().filter(c -> c.getId() == adr.getType().getId()).toArray()).get(0);
            adr.setType(ct);
            if(adr.isFavorite()) {
                markAsFavorite(adr);
            }
            addMsgInfo(MSG_ID_ADDRESS,"Endereço alterado com sucesso!");
            mainAddress = false;
        }catch(Exception exp)
        {
            addMsgError(MSG_ID_ADDRESS,"Não foi possível alterar o Endereço!");
        }
    }

    public void removeAddress(Address c)
    {
        addressList.remove(c);
        customer.setAddresses(addressList);

        if(addressList.size() > 0 && c.isFavorite())
        {
            markAsFavorite(addressList.stream().findFirst().get());
        }

        if(c.getId() > 0)
        {
            addressBO.delete(c);
        }
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
                addMsgError(MSG_ID_ADDRESS,"CEP não encontrado!");
            }else {
                addMsgInfo(MSG_ID_ADDRESS,"CEP encontrado!");
            }
        }else
        {
            addMsgWarn(MSG_ID_ADDRESS,"Digite o CEP para pesquisar");

        }
    }

    public void onAddresCancel(RowEditEvent event) {
int y =0;
    }

    @Override
    public void save(ActionEvent e) {
        try
        {

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

    public void resetVariables()
    {
        customer = new Customer();
        address = new Address();
        addressList = new HashSet<Address>(5);
        mainAddress = false;
        editMode = false;
    }
    /*  GET AND SETTERS */


    public Set<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Set<Address> addressList) {
        addressList.forEach(x -> setAddress(x));

        this.addressList = addressList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        addressList.forEach(x -> x.setCustomer(this.customer));
    }

    public Customer getCustomer() {
        return customer;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if(address != null && address.getId() > 0)
        includeAddress(null);
    }


    public Set<AddressType> getAddressTypeList() {
        return addressTypeList;
    }


    public void setAddressTypeList(Set<AddressType> addressTypeList) {
        this.addressTypeList = addressTypeList;
    }


    public long getTypeAddressID() {
        return typeAddressID;
    }

    public void setTypeAddressID(long typeAddressID) {
        this.typeAddressID = typeAddressID;
    }


    public boolean isCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }


    public boolean isMainAddress() {
        return mainAddress;
    }


    public void setMainAddress(boolean mainAddress) {
        this.mainAddress = mainAddress;
    }


}
