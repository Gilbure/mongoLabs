package edu.nuwm.mongolabs.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Staff {
    private String FirstName;
    private String LastName;
    private String Position;
    private String Salary;
    private String Email;
    private String Phone;
    //private Hotel hotel; // Зв'язок з готелем

    public Staff(String firstName, String lastName, String position, String salary, String email, String phone/*, Hotel hotel*/) {
        FirstName = firstName;
        LastName = lastName;
        Position = position;
        Salary = salary;
        Email = email;
        Phone = phone;
        //this.hotel = hotel;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    /*public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        return new EqualsBuilder()
                .append(FirstName, staff.FirstName)
                .append(LastName, staff.LastName)
                .append(Position, staff.Position)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(FirstName)
                .append(LastName)
                .append(Position)
                .toHashCode();
    }
}
