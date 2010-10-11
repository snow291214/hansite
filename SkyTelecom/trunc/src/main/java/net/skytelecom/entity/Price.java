package net.skytelecom.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 03.07.2010
 */
@Entity
@Table(name = "prices", catalog = "skyteldb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
    @NamedQuery(name = "Price.findByUid", query = "SELECT p FROM Price p WHERE p.uid = :uid"),
    @NamedQuery(name = "Price.findByPhoneCode", query = "SELECT p FROM Price p WHERE p.phoneCode = :phoneCode"),
    @NamedQuery(name = "Price.findByDestination", query = "SELECT p FROM Price p"
    + " WHERE p.destination = :destination and  p.customersPrices.uid = :customersPricesUid"),
    @NamedQuery(name = "Price.findByCurrency", query = "SELECT p FROM Price p WHERE p.currency = :currency"),
    @NamedQuery(name = "Price.findByConnectRatePeak", query = "SELECT p FROM Price p WHERE p.connectRatePeak = :connectRatePeak"),
    @NamedQuery(name = "Price.findByRatePeak", query = "SELECT p FROM Price p WHERE p.ratePeak = :ratePeak"),
    @NamedQuery(name = "Price.findByFreePeak", query = "SELECT p FROM Price p WHERE p.freePeak = :freePeak"),
    @NamedQuery(name = "Price.findByInitPeak", query = "SELECT p FROM Price p WHERE p.initPeak = :initPeak"),
    @NamedQuery(name = "Price.findByQuantPeak", query = "SELECT p FROM Price p WHERE p.quantPeak = :quantPeak"),
    @NamedQuery(name = "Price.findByConnectRateOffpeak", query = "SELECT p FROM Price p WHERE p.connectRateOffpeak = :connectRateOffpeak"),
    @NamedQuery(name = "Price.findByRateOffpeak", query = "SELECT p FROM Price p WHERE p.rateOffpeak = :rateOffpeak"),
    @NamedQuery(name = "Price.findByFreeOffpeak", query = "SELECT p FROM Price p WHERE p.freeOffpeak = :freeOffpeak"),
    @NamedQuery(name = "Price.findByInitOffpeak", query = "SELECT p FROM Price p WHERE p.initOffpeak = :initOffpeak"),
    @NamedQuery(name = "Price.findByQuantOffpeak", query = "SELECT p FROM Price p WHERE p.quantOffpeak = :quantOffpeak"),
    @NamedQuery(name = "Price.findByActivationDate", query = "SELECT p FROM Price p WHERE p.activationDate = :activationDate"),
    @NamedQuery(name = "Price.findByExpiredDate", query = "SELECT p FROM Price p WHERE p.activationDate <"
    + " :activationDate and p.priceIndicator <> 'current' "),
    @NamedQuery(name = "Price.findByChangedIndicators", query = "SELECT p FROM Price "
    + "p WHERE p.customersPrices.uid = :customersPricesUid  and p.priceIndicator <> 'current' "),
    @NamedQuery(name = "Price.findByQos", query = "SELECT p FROM Price p WHERE p.qos = :qos")
})
public class Price implements Serializable {

    private static final long serialVersionUID = -8195825319193929010L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;
    @Basic(optional = false)
    @Column(name = "PhoneCode", nullable = false, length = 50)
    private String phoneCode;
    @Column(name = "Destination", length = 200)
    private String destination;
    @Column(name = "Currency", length = 20)
    private String currency;
    @Column(name = "PriceIndicator", length = 20)
    private String priceIndicator;
    @Column(name = "ConnectRatePeak", precision = 4, scale = 4)
    private Double connectRatePeak;
    @Column(name = "RatePeak", precision = 4, scale = 4)
    private Double ratePeak;
    @Column(name = "FreePeak", precision = 4, scale = 4)
    private Double freePeak;
    @Column(name = "InitPeak", precision = 4, scale = 4)
    private Double initPeak;
    @Column(name = "QuantPeak", precision = 4, scale = 4)
    private Double quantPeak;
    @Column(name = "ConnectRateOffpeak", precision = 4, scale = 4)
    private Double connectRateOffpeak;
    @Column(name = "RateOffpeak", precision = 4, scale = 4)
    private Double rateOffpeak;
    @Column(name = "FreeOffpeak", precision = 4, scale = 4)
    private Double freeOffpeak;
    @Column(name = "InitOffpeak", precision = 4, scale = 4)
    private Double initOffpeak;
    @Column(name = "QuantOffpeak", precision = 4, scale = 4)
    private Double quantOffpeak;
    @Column(name = "ActivationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationDate;
    @Column(name = "Qos", length = 255)
    private String qos;
    @Column(name = "LastFieldIgnore")
    private Short lastFieldIgnore;
    @Lob
    @Column(name = "Routing", length = 65535)
    private String routing;

//    @ForeignKey(name = "fk_prices_customers")
//    @JoinColumn(name = "CustomerUid", referencedColumnName = "Uid")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Customer customer;
//
    @ForeignKey(name = "fk_price_customers_prices")
    @JoinColumn(name = "CustomersPricesUid", referencedColumnName = "Uid", columnDefinition = "INTEGER(11)")
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomersPrices customersPrices;

    public Price() {
    }

    public Price(Long uid) {
        this.uid = uid;
    }

    public Price(Long uid, String phoneCode) {
        this.uid = uid;
        this.phoneCode = phoneCode;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getConnectRatePeak() {
        return connectRatePeak;
    }

    public void setConnectRatePeak(Double connectRatePeak) {
        this.connectRatePeak = connectRatePeak;
    }

    public Double getRatePeak() {
        return ratePeak;
    }

    public void setRatePeak(Double ratePeak) {
        this.ratePeak = ratePeak;
    }

    public Double getFreePeak() {
        return freePeak;
    }

    public void setFreePeak(Double freePeak) {
        this.freePeak = freePeak;
    }

    public Double getInitPeak() {
        return initPeak;
    }

    public void setInitPeak(Double initPeak) {
        this.initPeak = initPeak;
    }

    public Double getQuantPeak() {
        return quantPeak;
    }

    public void setQuantPeak(Double quantPeak) {
        this.quantPeak = quantPeak;
    }

    public Double getConnectRateOffpeak() {
        return connectRateOffpeak;
    }

    public void setConnectRateOffpeak(Double connectRateOffpeak) {
        this.connectRateOffpeak = connectRateOffpeak;
    }

    public Double getRateOffpeak() {
        return rateOffpeak;
    }

    public void setRateOffpeak(Double rateOffpeak) {
        this.rateOffpeak = rateOffpeak;
    }

    public Double getFreeOffpeak() {
        return freeOffpeak;
    }

    public void setFreeOffpeak(Double freeOffpeak) {
        this.freeOffpeak = freeOffpeak;
    }

    public Double getInitOffpeak() {
        return initOffpeak;
    }

    public void setInitOffpeak(Double initOffpeak) {
        this.initOffpeak = initOffpeak;
    }

    public Double getQuantOffpeak() {
        return quantOffpeak;
    }

    public void setQuantOffpeak(Double quantOffpeak) {
        this.quantOffpeak = quantOffpeak;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public String getQos() {
        return qos;
    }

    public void setQos(String qos) {
        this.qos = qos;
    }

    public Short getLastFieldIgnore() {
        return lastFieldIgnore;
    }

    public void setLastFieldIgnore(Short lastFieldIgnore) {
        this.lastFieldIgnore = lastFieldIgnore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Price)) {
            return false;
        }
        Price other = (Price) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.skytelecom.entity.Price[uid=" + uid + "]";
    }

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public String getPriceIndicator() {
        return priceIndicator;
    }

    public void setPriceIndicator(String priceIndicator) {
        this.priceIndicator = priceIndicator;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public CustomersPrices getCustomersPrices() {
        return customersPrices;
    }

    public void setCustomersPrices(CustomersPrices customersPrices) {
        this.customersPrices = customersPrices;
    }

//    public PriceType getPriceType() {
//        return priceType;
//    }
//
//    public void setPriceType(PriceType priceType) {
//        this.priceType = priceType;
//    }
}
