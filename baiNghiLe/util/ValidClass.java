package baiNghiLe.util;

import baiNghiLe.presentation.UniversityManager;

import java.util.Scanner;

public class ValidClass {
    public static String checkSomeClassName(Scanner scanner, String mess, int index) {
        System.out.println(mess);
        String input = Validator.ValidateString(scanner, 15, 50);
        while(true){
            try {
                if(UniversityManager.classList.stream().anyMatch(course -> course.getClassRoomName().equals(input) && (index == -1 || !course.equals(UniversityManager.classList.get(index))))){
                    System.out.println("Tên lớp học không được trùng, vui lòng nhập lại!");
                    continue;
                }
                return input;
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }

    public static int checkSomeClassId(Scanner scanner, String mess) {
        System.out.println(mess);
        while(true){
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if(UniversityManager.classList.stream().anyMatch(c -> c.getClassroomId()==input)){
                    return input;
                }
                System.out.println("Id lớp học k đúng, vui lòng nhập lại");
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }
}
