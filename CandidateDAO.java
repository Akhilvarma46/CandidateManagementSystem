package com.wipro.candidate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.wipro.candidate.util.DBUtil;

import src.com.wipro.candidate.bean.Candidatebean;

public class CandidateDAO {

    
    public String addCandidate(Candidatebean candidate) {
        String status = "FAIL";
        try (Connection con = DBUtil.getDBConnection()) {
            String query = "INSERT INTO Candidate_tbl (id, name, M1, M2, M3, result, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, candidate.getId());
            ps.setString(2, candidate.getName());
            ps.setInt(3, candidate.getM1());
            ps.setInt(4, candidate.getM2());
            ps.setInt(5, candidate.getM3());
            ps.setString(6, candidate.getResult());
            ps.setString(7, candidate.getGrade());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = "SUCCESS";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Get candidates based on result
    public ArrayList<Candidatebean> getByResult(String criteria) {
        ArrayList<Candidatebean> list = new ArrayList<>();
        try (Connection con = DBUtil.getDBConnection()) {
            String query = "";

            if (criteria.equalsIgnoreCase("PASS")) {
                query = "SELECT * FROM Candidate_tbl WHERE result = 'PASS'";
            } else if (criteria.equalsIgnoreCase("FAIL")) {
                query = "SELECT * FROM Candidate_tbl WHERE result = 'FAIL'";
            } else { // ALL
                query = "SELECT * FROM Candidate_tbl";
            }

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Candidatebean cb = new Candidatebean();
                cb.setId(rs.getString("id"));
                cb.setName(rs.getString("name"));
                cb.setM1(rs.getInt("M1"));
                cb.setM2(rs.getInt("M2"));
                cb.setM3(rs.getInt("M3"));
                cb.setResult(rs.getString("result"));
                cb.setGrade(rs.getString("grade"));

                list.add(cb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Generate unique candidate ID
    public String generateCandidateId(String name) {
        String id = "";
        try {
            String prefix = name.substring(0, 2).toUpperCase();
            int num = (int) (Math.random() * 900) + 100; // 3-digit random number
            id = prefix + num; // Example: PA123
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
