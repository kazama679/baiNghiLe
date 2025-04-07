package baiNghiLe.entity;

import baiNghiLe.util.ValidStudent;

import java.util.Scanner;

public class Student extends Person implements IApp{
    private String studentId;
    private double gpa;

    public Student() {
    }

    public Student(String address, int age, String email, String name, String phone, Sex sex, double gpa, String studentId) {
        super(address, age, email, name, phone, sex);
        this.gpa = gpa;
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.studentId = ValidStudent.checkSomeStudentId(scanner, "Nhập id sinh viên: ");
        inputPerson(scanner);
        this.gpa = ValidStudent.validateGPA(scanner);
    }

    @Override
    public String toString() {
        return "--------------------------\n" +
                "Id sinh viên: " + this.studentId + "\n" +
                "Tên: " + getName() + "\n" +
                "Tuổi: " + getAge() + "\n" +
                "Địa chỉ: " + getAddress() + "\n" +
                "Số điện thoại: " + getPhone() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Giới tính: " + (getSex() == Sex.MALE ? "Nam" : getSex() == Sex.FEMALE ? "Nữ" : "Khác") + "\n" +
                "GPA: " + this.gpa;
    }

}
