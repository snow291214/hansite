package net.skytelecom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.entity.PriceType;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public class PriceDto implements Serializable {

    private static final long serialVersionUID = 898653213087953738L;
    private Long uid;
    private String phoneCode;
    private String destination;
    private String currency;
    private String priceIndicator;
    private Double connectRatePeak;
    private Double ratePeak;
    private Double freePeak;
    private Double initPeak;
    private Double quantPeak;
    private Double connectRateOffpeak;
    private Double rateOffpeak;
    private Double freeOffpeak;
    private Double initOffpeak;
    private Double quantOffpeak;
    private Date activationDate;
    private String qos;
    private Short lastFieldIgnore;
    private Customer customer;
    private Double newRate;
    private List<String> destinations;
    private Long customersPricesUid;
    private PriceType priceType;

    public PriceDto() {
    }

    public PriceDto(List<String> destinations) {
        this.destinations = destinations;
    }

    public PriceDto(Price price) {
        this.uid = price.getUid();
        this.phoneCode = price.getPhoneCode();
        this.destination = price.getDestination();
        this.currency = price.getCurrency();
        this.priceIndicator = price.getPriceIndicator();
        this.connectRatePeak = price.getConnectRatePeak();
        this.ratePeak = price.getRatePeak();
        this.freePeak = price.getFreePeak();
        this.initPeak = price.getInitPeak();
        this.quantPeak = price.getQuantPeak();
        this.connectRateOffpeak = price.getConnectRateOffpeak();
        this.rateOffpeak = price.getRateOffpeak();
        this.freeOffpeak = price.getFreeOffpeak();
        this.initOffpeak = price.getInitOffpeak();
        this.quantOffpeak = price.getQuantOffpeak();
        this.activationDate = price.getActivationDate();
        this.qos = price.getQos();
        this.lastFieldIgnore = price.getLastFieldIgnore();
        //this.customer = price.getCustomer();
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

    public String getPriceIndicator() {
        return priceIndicator;
    }

    public void setPriceIndicator(String priceIndicator) {
        this.priceIndicator = priceIndicator;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getNewRate() {
        return newRate;
    }

    public void setNewRate(Double newRate) {
        this.newRate = newRate;
    }

    /**
     * @return the destinations
     */
    public List<String> getDestinations() {
        return destinations;
    }

    /**
     * @param destinations the destinations to set
     */
    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    /**
     * @return the customerPriceUid
     */
    public Long getCustomersPricesUid() {
        return customersPricesUid;
    }

    /**
     * @param customerPriceUid the customerPriceUid to set
     */
    public void setCustomersPricesUid(Long customersPricesUid) {
        this.customersPricesUid = customersPricesUid;
    }

    /**
     * @return the priceType
     */
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * @param priceType the priceType to set
     */
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }
}
