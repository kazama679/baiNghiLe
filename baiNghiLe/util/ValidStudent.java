package baiNghiLe.util;

import baiNghiLe.presentation.UniversityManager;

import java.util.Scanner;

public class ValidStudent {
    public static String checkSomeStudentId(Scanner scanner, String mess) {
        System.out.println(mess);
        while(true){
            try {
                String input = scanner.nextLine();
                if(!input.matches("^SV\\w{3}$")){
                    System.out.println("Id sinh viên không hợp lệ, vui lòng nhập lại");
                    continue;
                }
                if(UniversityManager.studentList.stream().anyMatch(s -> s.getStudentId().equals(input))){
                    System.out.println("Id sinh viên không được trùng, vui lòng nhập lại");
                    continue;
                }
                return input;
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }
    public static String checkSomeStudentId2(Scanner scanner, String mess) {
        System.out.println(mess);
        while(true){
            try {
                String input = scanner.nextLine();
                if(UniversityManager.studentList.stream().anyMatch(s -> s.getStudentId().equals(input))){
                    return input;
                }
                System.out.println("Id sinh viên k đúng, vui lòng nhập lại");
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }

    public static double validateGPA(Scanner sc) {
        double gpa;
        while (true) {
            try {
                System.out.print("Nhập GPA (0.0 - 4.0): ");
                gpa = Double.parseDouble(sc.nextLine());
                if (gpa >= 0.0 && gpa <= 4.0) {
                    return gpa;
                }
                System.out.println("GPA không hợp lệ!");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực!");
            }
        }
    }
}
