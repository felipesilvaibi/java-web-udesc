package com.udesc.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDto {

    @NotBlank
    @Size(min = 3, max = 32)
    private String name;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Size(min = 5, max = 64)
    private String email;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phone;

    @NotBlank
    @Size(min = 5, max = 64)
    private String address;

    @NotBlank
    @Size(min = 10, max = 10)
    private String birthDate;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
