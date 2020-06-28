package com.debezium.demo.request;

import com.debezium.demo.model.Person;

public class PersonCreateRequest {

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person convertToPerson() {
        return new Person(this.firstName, this.lastName);
    }

    @Override
    public String toString() {
        return "PersonCreateRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
