package edu.nuwm.mongolabs.persistence.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "service")
public class Service {
    @Id
    private String serviceName;
    private String description;
    private String price;

    public Service() {
    }

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

   /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        return new EqualsBuilder()
                .append(serviceName, service.serviceName)
                .append(description, service.description)
                .append(price, service.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(serviceName)
                .append(description)
                .append(price)
                .toHashCode();
    }*/
}
