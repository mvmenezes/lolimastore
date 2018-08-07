package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ADDRESS")
public class Address implements Serializable {


    @Transient
    private boolean favorite;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ADD_ID")
    private long id;
    @Column(name="ADD_STREET")
    private String street;
    @Column(name="ADD_ADDITIONAL")
    private String additional;
    @Column(name="ADD_DISTRICT")
    private String district;
    @Column(name="ADD_CITY")
    private String city;
    @Column(name="ADD_IND_MAIN")
    private String main;
    @Column(name="ADD_STATE")
    private String state;
    @Column(name="ADD_NUMBER")
    private String number;
    @Column(name="ADD_ZIP_CODE")
    private String zipcode;
    @Column(name="ADD_COUNTRY")
    private String country;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ADT_ID")
    private AddressType type;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "CTM_ID")
    private Customer customer;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "FAC_ID")
    private Factory factory;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "PRV_ID")
    private Provider provider;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "RTL_ID")
    private Retailer retailer;

    @Temporal(TemporalType.DATE)
    @Column(name="ADD_CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name="ADD_MOD_DATE")
    private Date modifiedDate;


    @PrePersist
    @PreUpdate
    public void prePersist()
    {
        modifiedDate = new Date();
        if(createDate == null)
        {
            createDate = modifiedDate;
        }
    }

    public Address()
    {
        type = new AddressType();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setState(String state) {
        this.state = state.toUpperCase();
    }

    public String getState() {
        return state;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Provider getProvider() {
        return provider;
    }


    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
