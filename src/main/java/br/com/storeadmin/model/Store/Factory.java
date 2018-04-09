package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="FACTORY")
public class Factory  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="FAC_ID")
    private long id;

    @Column(name="FAC_NAME")
    private String name;
    @Column(name="FAC_CNPJ")
    private String CNPJ;
    @Column(name="FAC_EMAIL")
    private String email;

    @OneToMany(mappedBy = "factory")
    private List<Contact> contacts;
    @OneToMany(mappedBy = "factory")
    private List<Address> addresses;
    @OneToMany(mappedBy = "factory")
    private List<Product> products;
    @Temporal(TemporalType.DATE)
    @Column(name="FAC_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="FAC_MOD_DATE")
    private Date modifiedDate;


    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCNPJ() {
        return CNPJ;
    }

    public String getEmail() {
        return email;
    }


    public String getName() {
        return name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Product> getProducts() {
        return products;
    }

    public long getId() {
        return id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
