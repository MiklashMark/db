package com.example.db_project.repo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "bank", name = "client")
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String mailAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    private Branch branch;

    public Client(String name, String surname, String address, String phoneNumber, String mailAddress, Branch branch) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(uuid, client.uuid) && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(mailAddress, client.mailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, surname, address, phoneNumber, mailAddress);
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }
}
