package baiNghiLe.entity;

import baiNghiLe.util.Validator;

import java.util.Scanner;

public class Person {
    private String name;
    private int age;
    private String address;
    private String phone;
    private String email;
    private Sex sex;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Person(String address, int age, String email, String name, String phone, Sex sex) {
        this.address = address;
        this.age = age;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
    }

    public Person() {
    }

    public void inputPerson(Scanner scanner) {
        System.out.println("Nhập vào tên: ");
        this.name = Validator.ValidateString(scanner, 1, 150);
        this.age = Validator.ValidateInt(scanner, "Nhập vào tuổi: ", 18);
        System.out.println("Nhập vào địa chỉ: ");
        this.address = Validator.ValidateString(scanner, 1, 255);
        this.phone = Validator.ValidatePhone(scanner, "Nhập vào số điện thoại: ",-1);
        this.email = Validator.ValidateEmail(scanner, "Nhập vào email: ",-1);
        this.sex = Validator.validSex(scanner, "Nhập vào giới tính: ");
    }
}
