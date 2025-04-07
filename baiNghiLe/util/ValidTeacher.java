package baiNghiLe.util;

import baiNghiLe.presentation.UniversityManager;

import java.util.Scanner;

public class ValidTeacher {
    public static int checkSomeTeacherId(Scanner scanner, String mess) {
        System.out.println(mess);
        while(true){
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if(UniversityManager.teacherList.stream().anyMatch(s -> s.getTeacherId() == input)){
                    return input;
                }
                System.out.println("Id giảng viên k đúng, vui lòng nhập lại");
            }catch (Exception e) {
                System.out.println("Đã có lỗi xảy ra trong quá trình nhập!");
            }
        }
    }
}
