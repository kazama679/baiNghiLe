package business.model;

import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private int age;
    private boolean status;

    public Student() {
    }

    public Student(int id, String name, int age, boolean status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên:");
        this.name = scanner.nextLine();
        System.out.println("Nhập vào tuổi sinh viên:");
        this.age = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào trạng thái sinh viên:");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Mã SV: " + this.id + " - Tên SV: "
                + this.name + " - Tuổi: " + this.age + " - Trạng thái: " + this.status;
    }
}