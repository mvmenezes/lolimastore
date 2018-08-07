package br.com.storeadmin.model.Store;

import br.com.storeadmin.model.EnumModel.EGender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="CUSTOMER")
public class Customer extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="CTM_ID")
    private long id;
    @Column(name="CTM_NAME")
    private String name;
    @Column(name="CTM_CPF")
    private String cpf;
    @Column(name="CTM_CNPJ")
    private String CNPJ;
    @Column(name="CTM_GENDER")
    private String gender;
    @Column(name="CTM_EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Contact> contacts;
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Address> addresses;

    @Column(name="CTM_INSTAGRAM")
    private String instagram;
    @Column(name="CTM_FACEBOOK")
    private String facebook;
    @Column(name="CTM_NOTE")
    private String note;
    @Column(name="CTM_CODE")
    private String code;
    @Column(name="CTM_BIRTH_DATE")
    private Date birthDate;
    @Column(name="CTM_TYPE")
    private String type;
    @Temporal(TemporalType.DATE)
    @Column(name="CTM_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="CTM_MOD_DATE")
    private Date modifiedDate;


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Customer()
    {
        gender = "F";
        type = "F";
        contacts = new HashSet<Contact>();
        addresses = new HashSet<Address>();
    }
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getGender()
    {

        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }


}
