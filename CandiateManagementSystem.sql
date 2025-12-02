CREATE DATABASE IF NOT EXISTS wiproDB;
USE wiproDB;
DROP TABLE IF EXISTS CANDIDATE_TBL;

CREATE TABLE CANDIDATE_TBL (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50),
    M1 INT,
    M2 INT,
    M3 INT,
    result VARCHAR(10),
    grade VARCHAR(5)
);

# To see the stored results in your MySQL database, you can use any of the following methods.

# 1 Select the database
		USE wiproDB;
        
 # 2 View all rows from Candidate_tbl
     SELECT * FROM CANDIDATE_TBL
          
 # 3 To count rows:     
	SELECT COUNT(*) FROM Candidate_tbl;
    
          
  # 4  Using SQL Query in Workbench or Console
    #To see only PASS candidates:
     SELECT * FROM Candidate_tbl WHERE result = 'PASS';
          
  # 5 To see only FAIL candidates:
    SELECT * FROM Candidate_tbl WHERE result = 'FAIL';
        
    
