package baiNghiLe.util;

import baiNghiLe.presentation.UniversityManager;

import java.util.Scanner;

public class ValidCourse {
    public static String checkSomeCourseId(Scanner scanner, String mess) {
        System.out.println(mess);
        while(true){
            try {
                String input = scanner.nextLine();
                if(!input.matches("^C\\w{4}$")){
                    System.out.println("Id khóa học không hợp lệ, vui lòng nhập lại");
                    continue;
                }
                if(UniversityManager.courseList.stream().anyMatch(course -> course.getCourseId().equals(input))){
                    System.out.println("Id khóa học không được trùng, vui lòng nhập lại");
                    continue;
                }
                return input;
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }

    public static String checkSomeCourseId2(Scanner scanner, String mess) {
        System.out.println(mess);
        while(true){
            try {
                String input = scanner.nextLine();
                if(UniversityManager.courseList.stream().anyMatch(course -> course.getCourseId().equals(input))){
                    return input;
                }
                System.out.println("Id khóa học k đúng, vui lòng nhập lại");
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }

    public static String checkSomeCourseName(Scanner scanner, String mess, int index) {
        System.out.println(mess);
        String input = Validator.ValidateString(scanner, 20, 100);
        while(true){
            try {
                if(UniversityManager.courseList.stream().anyMatch(course -> course.getCourseName().equals(input) && (index == -1 || !course.equals(UniversityManager.courseList.get(index))))){
                    System.out.println("Tên khóa học không được trùng, vui lòng nhập lại!");
                    continue;
                }
                return input;
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }
}
