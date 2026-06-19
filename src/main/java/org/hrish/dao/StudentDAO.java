package org.hrish.dao;

import org.hrish.model.Student;
import org.hrish.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public boolean insert(Student student) throws SQLException {
        String INSERT_QUERY = "INSERT INTO details (roll_no, name, marks) VALUES (?, ?, ?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {

            ps.setInt(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setFloat(3, student.getMarks());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteByRollNumber(int rollNumber) throws SQLException {
        String DELETE_QUERY = "DELETE FROM details WHERE roll_no = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_QUERY);) {

            ps.setInt(1, rollNumber);

            return ps.executeUpdate() > 0;
        }
    }

    public Student searchByRollNumber(int rollNumber) throws SQLException {
        String SEARCH_QUERY = "SELECT * FROM details WHERE roll_no = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(SEARCH_QUERY);) {

            ps.setInt(1, rollNumber);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                return new Student(rs.getInt("roll_no"), rs.getString("name"), rs.getFloat("marks"));
            }

            return null;
        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        String UPDATE_QUERY = "UPDATE details SET name = ?, marks = ? WHERE roll_no = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);) {

            ps.setString(1, student.getName());
            ps.setFloat(2, student.getMarks());
            ps.setInt(3, student.getRollNo());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Student> showAll() throws SQLException {
        String SHOW_QUERY = "SELECT * FROM details";
        List<Student> std = new ArrayList<>();

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(SHOW_QUERY);
            ResultSet rs = ps.executeQuery();) {

            while(rs.next()) {
                std.add(new Student(rs.getInt("roll_no"), rs.getString("name"), rs.getFloat("marks")));
            }
        }

        return std;
    }
}
