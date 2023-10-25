package edu.nuwm.mongolabs.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Service {
    //private String serviceID;
    private String serviceName;
    private String description;
    private String price;
    //private String hotelID;

    public Service(/*, String hotelID*/) {
        //this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        //this.hotelID = hotelID;
    }

    /*public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }*/

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /*public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        return new EqualsBuilder()/*.append(serviceID, service.serviceID)*/
                .append(serviceName, service.serviceName)
                .append(description, service.description)
                .append(price, service.price)
                //.append(hotelID, service.hotelID)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                /*.append(serviceID)*/
                .append(serviceName)
                .append(description)
                .append(price)
                //.append(hotelID)
                .toHashCode();
    }
}
