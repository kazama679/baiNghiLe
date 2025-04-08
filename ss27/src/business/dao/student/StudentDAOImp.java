package business.dao.student;

import business.config.ConnectionDB;
import business.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {

    @Override
    public int getCountStudentByStatus(boolean status) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            //1. Khởi tạo đối tượng connection làm việc với db
            conn = ConnectionDB.openConnection();
            //2. Khởi tạo đối tượng callSt từ conn và gọi procedure để thao tác dữ liệu
            callSt = conn.prepareCall("{call get_count_student_by_status(?,?)}");
            //3. Set giá trị cho các tham số vào
            callSt.setBoolean(1, status);
            //4. Đăng ký kiểu dữ liệu cho các tham số ra
            callSt.registerOutParameter(2, Types.INTEGER);
            //5. Thực hiện procedure và xử lý kết quả trả về
            /*
             * 1. excecuteQuery(): thực hiện các procedure thực hiện select
             * 2. excecuteUpdate(): Thực hiện các procedure thực hiện insert, update, delete mà không có tham số trả ra (out parameter)
             * 3. excecure():Thực hiện các procedure thực hiện insert, update, delete có tham số trả ra
             * */
            callSt.execute();
            //Lấy dữ liệu tham số trả ra
            return callSt.getInt(2);
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public Student findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_student_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("student_name"));
                student.setAge(rs.getInt("student_age"));
                student.setStatus(rs.getBoolean("student_status"));
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudents = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_student()}");
            ResultSet rs = callSt.executeQuery();
            listStudents = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("student_name"));
                student.setAge(rs.getInt("student_age"));
                student.setStatus(rs.getBoolean("student_status"));
                listStudents.add(student);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudents;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_student(?,?,?)}");
            callSt.setString(1, student.getName());
            callSt.setInt(2, student.getAge());
            callSt.setBoolean(3, student.isStatus());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_student(?,?,?,?)}");
            //set giá trị cho tham số vào
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getName());
            callSt.setInt(3, student.getAge());
            callSt.setBoolean(4, student.isStatus());
            //đăng ký kiểu dữ liệu cho tham số ra
            //Thực hiện procedure và xử lý kết quả
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_student(?)}");
            //set giá trị cho tham số vào
            callSt.setInt(1, student.getId());
            //đăng ký kiểu dữ liệu cho tham số ra
            //Thực hiện procedure và xử lý kết quả
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}