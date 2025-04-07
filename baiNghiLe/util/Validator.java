package baiNghiLe.util;

import baiNghiLe.entity.Sex;
import baiNghiLe.entity.Status;
import baiNghiLe.entity.StatusClass;
import baiNghiLe.presentation.UniversityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validator {
    public static int ValidateInt(Scanner scanner, String mess, int min) {
        System.out.println(mess);
        while (true){
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value < min) {
                    System.out.println("Số không được nhỏ hơn "+min);
                    continue;
                }
                return value;
            }catch (NumberFormatException e){
                System.out.println("Không phải số, vui lòng nhập lại!");
            }catch (Exception e){
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static String ValidateString(Scanner scanner, int min, int max) {
        while (true){
            try{
                String input = scanner.nextLine();
                if(input.length()>=min && input.length()<=max){
                    return input;
                }
                System.out.println("Độ dài chuỗi không hợp lệ, vui lòng nhập lại!");
            }catch (Exception e){
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static Boolean ValidateBoolean(Scanner scanner, String mess) {
        System.out.println(mess);
        while (true){
            try{
                String input = scanner.nextLine();
                if(input.equals("true")||input.equals("false")){
                    return Boolean.parseBoolean(input);
                }
                System.out.println("Kiểu dữ liệu nhập không hợp lệ, vui lòng nhập lại!");
            }catch (Exception e){
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static double ValidateDouble(Scanner scanner, String mess) {
        System.out.println(mess);
        while (true){
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            }catch (NumberFormatException e){
                System.out.println("Không phải số, vui lòng nhập lại!");
            }catch (Exception e){
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static String ValidatePhone(Scanner scanner, String mess, int index) {
        System.out.println(mess);
        while (true){
            try {
                String phone = scanner.nextLine();
                if(!phone.matches("^(03[2-9]|05[2689]|07[0-9]|08[1-9]|09[0-9])\\d{7}$")){
                    System.out.println("Không đúng định dạng số điện thoại Việt Nam, vui lòng nhập lại!");
                    continue;
                }
                if(UniversityManager.studentList.stream().anyMatch(s -> s.getPhone().equals(phone) && (index == -1 || !s.equals(UniversityManager.studentList.get(index)))) ||
                UniversityManager.teacherList.stream().anyMatch(t -> t.getPhone().equals(phone) && (index == -1 || !t.equals(UniversityManager.teacherList.get(index))))
                ){
                    System.out.println("Số điện thoại không được trùng, vui lòng nhập lại!");
                    continue;
                }
                return phone;
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static String ValidateEmail(Scanner scanner, String mess, int index) {
        System.out.println(mess);
        while (true){
            try {
                String email = scanner.nextLine();
                if(!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,6})+$")){
                    System.out.println("Không đúng định dạng email, vui lòng nhập lại!");
                    continue;
                }
                if(UniversityManager.studentList.stream().anyMatch(s -> s.getEmail().equals(email) && (index == -1 || !s.equals(UniversityManager.studentList.get(index)))) ||
                        UniversityManager.teacherList.stream().anyMatch(t -> t.getEmail().equals(email) && (index == -1 || !t.equals(UniversityManager.teacherList.get(index))))
                ){
                    System.out.println("Email không được trùng, vui lòng nhập lại!");
                    continue;
                }
                return email;
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static LocalDate ValidateDate(Scanner scanner, String mess) {
        System.out.println(mess);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true){
            try {
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e1) {
                System.out.println("Kiểu dữ liệu ngày tháng năm không hợp lệ, vui lòng nhập lại!");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static LocalDateTime validDateTime(Scanner sc, String mess) {
        System.out.println(mess);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        while (true) {
            try {
                String input = sc.nextLine().trim();
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e1) {
                System.out.println("Định dạng ngày giờ không đúng! Hãy nhập lại.");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu, vui lòng nhập lại!");
            }
        }
    }

    public static Sex validSex(Scanner sc, String mess) {
        System.out.println(mess);
        while (true){
            try {
                String input = sc.nextLine().trim().toUpperCase();
                return Sex.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Giới tính không hợp lệ. Vui lòng nhập lại (MALE/FEMALE/OTHER).");
            }
        }
    }

    public static Status validStatus(Scanner sc, String mess) {
        System.out.println(mess);
        while (true){
            try {
                String input = sc.nextLine().trim().toUpperCase();
                return Status.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Trạng thái không hợp lệ. Vui lòng nhập lại (PENDING/PROGESS/CLOSE).");
            }
        }
    }
    public static StatusClass validStatusClass(Scanner sc, String mess) {
        System.out.println(mess);
        while (true){
            try {
                String input = sc.nextLine().trim().toUpperCase();
                return StatusClass.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Trạng thái không hợp lệ. Vui lòng nhập lại (PENDING, PROGESS, CLOSE).");
            }
        }
    }
}
