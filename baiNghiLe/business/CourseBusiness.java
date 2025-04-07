package baiNghiLe.business;

import baiNghiLe.entity.Course;
import baiNghiLe.presentation.UniversityManager;
import baiNghiLe.util.ValidCourse;
import baiNghiLe.util.Validator;

import java.util.Comparator;
import java.util.Scanner;

public class CourseBusiness {
    public static void menuCourse(Scanner scanner) {
        int choice;
         do{
            System.out.println("************************* COURSE MENU *************************\n" +
                    "1. Danh sách khóa học sắp xếp theo tên tăng dần\n" +
                    "2. Thêm mới khóa học\n" +
                    "3. Cập nhật khóa học\n" +
                    "4. Xóa khóa học theo ID (Chỉ xóa được khóa học nếu khóa học chưa có lớp học)\n" +
                    "5. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ",1);
            switch (choice) {
                case 1:
                    displayListCourse();
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    displayListCourse();
                    updateCourse(scanner);
                    break;
                case 4:
                    deleteCourse(scanner);
                    break;
                case 5:
                    System.out.println("Thoát menu Course!");
                    break;
                default:
                    System.out.println("Nhập từ 1-5");
                    break;
            }
        }while (choice!=5);
    }

    public static void displayListCourse() {
        if(UniversityManager.courseList.isEmpty()){
            System.out.println("Danh sách khóa học rỗng!");
            return;
        }
        UniversityManager.courseList.sort(Comparator.comparing(Course::getCourseName).reversed());
        UniversityManager.courseList.forEach(System.out::println);
    }

    public static void addCourse(Scanner scanner) {
        int n = Validator.ValidateInt(scanner, "Nhập vào số lượng khóa học muốn thêm: ", 1);
        for (int i = 0; i < n; i++) {
            Course coures = new Course();
            coures.inputData(scanner);
            UniversityManager.courseList.add(coures);
        }
    }

    public static int indexId(String id) {
        for (int i = 0; i < UniversityManager.courseList.size(); i++) {
            if (id.equals(UniversityManager.courseList.get(i).getCourseId())) {
                return i;
            }
        }
        return -1;
    }

    public static void updateCourse(Scanner scanner) {
        if(UniversityManager.courseList.isEmpty()){
            return;
        }
        System.out.println("Nhập vào id khóa học muốn cập nhập: ");
        String id = scanner.nextLine();
        int index = indexId(id);
        if(index == -1){
            System.out.println("Không tìm thấy id nào!");
            return;
        }
        int choice;
        do{
            System.out.println("1. Sửa tên khóa học");
            System.out.println("2. Sửa trạng thái khóa học");
            System.out.println("3. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ", 1);
            switch (choice) {
                case 1:
                    String name = ValidCourse.checkSomeCourseName(scanner, "Nhập vào tên khóa học muốn sửa: ", index);
                    UniversityManager.courseList.get(index).setCourseName(name);
                    break;
                case 2:
                    UniversityManager.courseList.get(index).setStatus(!UniversityManager.courseList.get(index).getStatus());
                    System.out.println("Đã sửa trạng thái thành công");
                    break;
                case 3:
                    System.out.println("Sửa thành công");
                    break;
                default:
                    System.out.println("Chọn từ 1-3");
                    break;
            }
        }while (choice!=3);
        displayListCourse();
    }
    
    public static void deleteCourse(Scanner scanner) {
        if(UniversityManager.courseList.isEmpty()){
            return;
        }
        System.out.println("Nhập vào id khóa học muốn cập nhập: ");
        String id = scanner.nextLine();
        int index = indexId(id);
        if(index == -1){
            System.out.println("Không tìm thấy id nào!");
            return;
        }
        if(UniversityManager.classList.stream().anyMatch(c -> c.getCourseId().equals(id))){
            System.out.println("Khóa học đã có lớp, không thể xóa");
            return;
        }
        UniversityManager.courseList.remove(index);
    }
}
