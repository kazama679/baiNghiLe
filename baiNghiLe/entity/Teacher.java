package baiNghiLe.entity;

import baiNghiLe.util.Validator;

import java.util.Scanner;

public class Teacher extends Person implements IApp{
    private static int teacherIdAuto=0;
    private final int teacherId;
    private String subject;

    public Teacher() {
        this.teacherId = ++teacherIdAuto;
    }

    public Teacher(String address, int age, String email, String name, String phone, Sex sex, String subject) {
        super(address, age, email, name, phone, sex);
        this.subject = subject;
        this.teacherId = ++teacherIdAuto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTeacherId() {
        return teacherId;
    }

    @Override
    public void inputData(Scanner scanner) {
        inputPerson(scanner);
        System.out.println("Nhập chuyên môn giảng viên: ");
        this.subject = Validator.ValidateString(scanner, 1, 255);
    }

    @Override
    public String toString() {
        return "--------------------------\n" +
                "Id giảng viên: " + this.teacherId + "\n" +
                "Tên: " + getName() + "\n" +
                "Tuổi: " + getAge() + "\n" +
                "Địa chỉ: " + getAddress() + "\n" +
                "Số điện thoại: " + getPhone() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Giới tính: " + (getSex() == Sex.MALE ? "Nam" : getSex() == Sex.FEMALE ? "Nữ" : "Khác") + "\n" +
                "Chuyên môn: " + this.subject;
    }
}
