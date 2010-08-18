package net.skytelecom.dto;

import java.io.Serializable;
import java.util.Collection;
import net.skytelecom.entity.CustomersPrices;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 18.08.2010
 */
public class PriceTypeDto implements Serializable {

    private static final long serialVersionUID = -7899308407656688764L;
    private Long uid;
    private String name;
    private Collection<CustomersPrices> customersPrices;

    public PriceTypeDto() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return the customersPrices
     */
    public Collection<CustomersPrices> getCustomersPrices() {
        return customersPrices;
    }

    /**
     * @param customersPrices the customersPrices to set
     */
    public void setCustomersPrices(Collection<CustomersPrices> customersPrices) {
        this.customersPrices = customersPrices;
    }
}
