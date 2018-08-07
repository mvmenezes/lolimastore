package br.com.storeadmin.model.Store;

import br.com.storeadmin.model.EnumModel.EYesNo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {


    @Transient
    public static final String LINK_WPP = "https://api.whatsapp.com/send?phone=";
    @Transient
    private boolean favorite;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="CON_ID")
    private long id;
    @Column(name="CON_NUMBER")
    private String number;
    @OneToOne
    @JoinColumn(name="CTT_ID")
    private ContactType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTM_ID")
    private Customer customer;
    @Column(name="CON_IND_MAIN")
    private String main;
    @Column(name="CON_MASK")
    private String mask;
    @Column(name="CON_NOTE")
    private String note;
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
    @Column(name="CON_CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name="CON_MOD_DATE")
    private Date modifiedDate;

    @Column(name="CON_LINK_WPP")
    private String linkWpp;


    public Contact()
    {

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

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public String getMask() {
        return mask;
    }

    public void setMain(String main) {

        this.main = main;
        if(main.equals(EYesNo.YES))
        {
            setFavorite(true);
        }else
        {
            setFavorite(false);
        }
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkWpp() {
        return linkWpp;
    }

    public void setLinkWpp(String linkWpp) {
        this.linkWpp = linkWpp;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
