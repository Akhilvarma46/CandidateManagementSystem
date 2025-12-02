package com.wipro.candidate.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import com.wipro.candidate.util.DBUtil;

	import src.com.wipro.candidate.bean.Candidatebean;

	public class CandidateDAO1 {

	    // 1️Add candidate to database
		public String addCandidate(Candidatebean studentBean) {
		    String status = "FAIL";
		    Connection con = null;
		    PreparedStatement ps = null;

		    try {
		        con = DBUtil.getDBConnection();
		        String sql = "INSERT INTO CANDIDATE_TBL (ID, NAME, M1, M2, M3, RESULT, GRADE) VALUES (?, ?, ?, ?, ?, ?, ?)";
		        ps = con.prepareStatement(sql);

		        ps.setString(1, studentBean.getId());
		        ps.setString(2, studentBean.getName());
		        ps.setInt(3, studentBean.getM1());
		        ps.setInt(4, studentBean.getM2());
		        ps.setInt(5, studentBean.getM3());
		        ps.setString(6, studentBean.getResult());
		        ps.setString(7, studentBean.getGrade());

		        int rows = ps.executeUpdate();
		        if (rows > 0) {
		            status = "SUCCESS";
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (ps != null) ps.close();
		            if (con != null) con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return status;
		}

	    // Get candidates based on result
		public ArrayList<Candidatebean> getByResult(String criteria) {
		    ArrayList<Candidatebean> list = new ArrayList<>();
		    Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try {
		        con = DBUtil.getDBConnection(); // Always get a fresh connection

		        String sql = "";
		        if ("PASS".equalsIgnoreCase(criteria)) {
		            sql = "SELECT * FROM CANDIDATE_TBL WHERE RESULT='PASS'";
		        } else if ("FAIL".equalsIgnoreCase(criteria)) {
		            sql = "SELECT * FROM CANDIDATE_TBL WHERE RESULT='FAIL'";
		        } else if ("ALL".equalsIgnoreCase(criteria)) {
		            sql = "SELECT * FROM CANDIDATE_TBL";
		        } else {
		            return null;
		        }

		        ps = con.prepareStatement(sql);
		        rs = ps.executeQuery();

		        while (rs.next()) {
		            Candidatebean cb = new Candidatebean();
		            cb.setId(rs.getString("ID"));
		            cb.setName(rs.getString("NAME"));
		            cb.setM1(rs.getInt("M1"));
		            cb.setM2(rs.getInt("M2"));
		            cb.setM3(rs.getInt("M3"));
		            cb.setResult(rs.getString("RESULT"));
		            cb.setGrade(rs.getString("GRADE"));
		            list.add(cb);
		        }

		        return list.isEmpty() ? null : list;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (ps != null) ps.close();
		            if (con != null) con.close(); // Close the connection here
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

	    // 3 Generate unique candidate ID
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

