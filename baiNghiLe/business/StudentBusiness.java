package baiNghiLe.business;

import baiNghiLe.entity.Student;
import baiNghiLe.presentation.UniversityManager;
import baiNghiLe.util.ValidStudent;
import baiNghiLe.util.Validator;

import java.util.Comparator;
import java.util.Scanner;

public class StudentBusiness {
    public static void menuStudent(Scanner scanner) {
        int choice;
        do{
            System.out.println("************************STUDENT MENU***************************\n" +
                    "1. Danh sách sinh viên được sắp xếp theo tên tăng dần\n" +
                    "2. Thêm mới sinh viên\n" +
                    "3. Cập nhật sinh viên\n" +
                    "4. Xóa sinh viên (chỉ xóa nếu sinh viên chưa đăng ký khóa học nào)\n" +
                    "5. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ",1);
            switch (choice) {
                case 1:
                    displayListStudent();
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    System.out.println("Thoát menu Student!");
                    break;
                default:
                    System.out.println("Nhập từ 1-5");
                    break;
            }
        }while (choice!=5);
    }

    public static void displayListStudent() {
        if(UniversityManager.studentList.isEmpty()){
            System.out.println("Danh sách sinh viên rỗng!");
            return;
        }
        UniversityManager.studentList.sort(Comparator.comparing(Student::getName).reversed());
        UniversityManager.studentList.forEach(System.out::println);
    }

    public static void addStudent(Scanner scanner) {
        int n = Validator.ValidateInt(scanner, "Nhập vào số lượng sinh viên muốn thêm: ", 1);
        for (int i = 0; i < n; i++) {
            Student s = new Student();
            s.inputData(scanner);
            UniversityManager.studentList.add(s);
        }
    }

    public static int indexId(String id) {
        for (int i = 0; i < UniversityManager.studentList.size(); i++) {
            if (id.equals(UniversityManager.studentList.get(i).getStudentId())) {
                return i;
            }
        }
        return -1;
    }

    public static void updateStudent(Scanner scanner) {
        if(UniversityManager.studentList.isEmpty()){
            return;
        }
        System.out.println("Nhập vào id sinh viên cập nhập: ");
        String id = scanner.nextLine();
        int index = indexId(id);
        if(index == -1){
            System.out.println("Không tìm thấy id nào!");
            return;
        }
        int choice;
        do{
            System.out.println("1. Sửa tên sinh viên");
            System.out.println("2. Sửa tuổi sinh viên");
            System.out.println("3. Sửa địa chỉ");
            System.out.println("4. Sửa số điện thoại");
            System.out.println("5. Sửa email");
            System.out.println("6. Sửa giới tính");
            System.out.println("7. Sửa gpa");
            System.out.println("8. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ", 1);
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên cần sửa: ");
                    String name = Validator.ValidateString(scanner, 1, 150);
                    UniversityManager.studentList.get(index).setName(name);
                    break;
                case 2:
                    int age = Validator.ValidateInt(scanner, "Nhập tuổi: ", 18);
                    UniversityManager.studentList.get(index).setAge(age);
                    break;
                case 3:
                    System.out.println("Nhập địa chỉ cần sửa: ");
                    UniversityManager.studentList.get(index).setAddress(Validator.ValidateString(scanner, 1, 255));
                    break;
                case 4:
                    UniversityManager.studentList.get(index).setPhone(Validator.ValidatePhone(scanner, "Nhập vào số điện thoại: ",index));
                    break;
                case 5:
                    UniversityManager.studentList.get(index).setEmail(Validator.ValidateEmail(scanner, "Nhập vào email: ", index));
                    break;
                case 6:
                    UniversityManager.studentList.get(index).setSex(Validator.validSex(scanner, "Nhập vào giới tính: "));
                    break;
                case 7:
                    UniversityManager.studentList.get(index).setGpa(ValidStudent.validateGPA(scanner));
                    break;
                case 8:
                    System.out.println("Sửa thành công");
                    break;
                default:
                    System.out.println("Chọn từ 1-8");
                    break;
            }
        }while (choice!=8);
        displayListStudent();
    }

    public static void deleteStudent(Scanner scanner) {
        if(UniversityManager.studentList.isEmpty()){
            return;
        }
        System.out.println("Nhập vào id sinh viên cập nhập: ");
        String id = scanner.nextLine();
        int index = indexId(id);
        if(index == -1){
            System.out.println("Không tìm thấy id nào!");
            return;
        }
        if(UniversityManager.registerList.stream().anyMatch(c -> c.getStudentId().equals(id))){
            System.out.println("Sinh viên đã đăng ký khóa học, không thể xóa");
            return;
        }
        UniversityManager.studentList.remove(index);
    }
}
