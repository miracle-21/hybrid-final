package project04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project04.vo.*;

public class DAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public void setConn() {
		try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/mysql");
            con = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	// 1 로그인 성공, 0 로그인 실패
	public int login(String id, String pw) {
		int result = 0;
		try {
			setConn();
			String sql = "SELECT * FROM Account WHERE id = ? AND pw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("일반 예외 : "+e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result; // 예외발생
	}

	public Account getAccountNo(int accno) {
		Account acc = null;
		try {
			setConn();
			String sql = "SELECT * FROM Account WHERE accno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accno);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				acc = new Account(
						rs.getInt("accno"),
						rs.getString("name"),
						rs.getString("id"),
						rs.getString("pw"),
						rs.getDate("birthday"),
						rs.getString("mnum"),
						rs.getString("pnum"),
						rs.getString("email"),
						rs.getString("address"),
						rs.getDate("regdate"),
						rs.getInt("admin"));
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("일반 예외 : "+e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return acc;
	}

	public Account getAccountId(String id) {
		Account acc = null;
		try {
			setConn();
			String sql = "SELECT * FROM Account WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				acc = new Account(
						rs.getInt("accno"),
						rs.getString("name"),
						rs.getString("id"),
						rs.getString("pw"),
						rs.getDate("birthday"),
						rs.getString("mnum"),
						rs.getString("pnum"),
						rs.getString("email"),
						rs.getString("address"),
						rs.getDate("regdate"),
						rs.getInt("admin"));
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("일반 예외 : "+e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return acc;
	}

	public void insertAccount(Account acc) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "INSERT INTO Account (accno, name, id, pw, birthday, mnum, pnum, email, address, regdate, admin) VALUES ("
        			+ "null, " // 기본키 accno는 자동 증가로 처리되므로 null로 입력합니다.
        			+ "?, " // 이름
     				+ "?, " // id
    			    + "?, " // pw
    			    + "STR_TO_DATE(?, '%Y%m%d'), " // 생년월일, MySQL에서는 TO_DATE 대신 STR_TO_DATE를 사용합니다.
     				+ "?, " // 휴대폰번호
    			    + "?, " // 집전화번호
    			    + "?, " // 이메일
   				    + "?, " // 주소
   				    + "NOW(), " // 가입일, MySQL에서는 sysdate 대신 NOW()를 사용합니다.
    				+ "?)"; // 권한 0 일반, 1 관리자
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, acc.getName());
			pstmt.setString(2, acc.getId());
			pstmt.setString(3, acc.getPw());
			pstmt.setString(4, acc.getBirthdayS());
			pstmt.setString(5, acc.getMnum());
			pstmt.setString(6, acc.getPnum());
			pstmt.setString(7, acc.getEmail());
			pstmt.setString(8, acc.getAddress());
			pstmt.setInt(9, acc.getAdmin());
			pstmt.executeUpdate();
			
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("일반 예외 : "+e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void updateAccount(Account acc) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "UPDATE Account SET "
					+ "pw = ?, "
					+ "birthday = ? "
					+ "mnum = ? "
					+ "pnum = ? "
					+ "email = ? "
					+ "address = ? "
					+ "WHERE accno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, acc.getPw());
			pstmt.setDate(2, acc.getBirthday());
			pstmt.setString(3, acc.getMnum());
			pstmt.setString(4, acc.getPnum());
			pstmt.setString(5, acc.getEmail());
			pstmt.setString(6, acc.getAddress());
			pstmt.setInt(7, acc.getAccno());
			pstmt.executeUpdate();
			
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("일반 예외 : "+e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteAccount(Account acc) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "DELETE FROM Account WHERE accno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, acc.getAccno());
			pstmt.executeUpdate();
			
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("일반 예외 : "+e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//0628수정 (갤러리 dao)
	//전체 게시글 목록 조회
	public ArrayList<Board> getBdList() {
		ArrayList<Board> bdList = new ArrayList<Board>();
		try {
			setConn();
			String sql = "SELECT * FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Board(int postid, int accno, String ptype)
				bdList.add(new Board(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("ptype")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bdList;
	}
	


	//전체 게시글 목록 조회 (계정번호를 이용해서)
	public ArrayList<Board> getBdListAccno(int accno) {
		ArrayList<Board> bdList = new ArrayList<Board>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM board\r\n"
					+ "WHERE accno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Board(int postid, int accno, String ptype)
				bdList.add(new Board(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("ptype")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bdList;
	}

	//전체 게시글 목록 조회 (게시글유형을 이용해서)
	public ArrayList<Board> getBdListPtype(String ptype) {
		ArrayList<Board> bdList = new ArrayList<Board>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM board\r\n"
					+ "WHERE ptype=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ptype);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Board(int postid, int accno, String ptype)
				bdList.add(new Board(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("ptype")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bdList;
	}

	//전체 게시글 목록에서 삭제 (게시글번호 입력)
	public void deleteBdList(int postid) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "DELETE FROM board\r\n"
					+ "WHERE postid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);
			
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 전체 게시글 목록 등록
	public void insertBdList(Board bd) {
		try {
			setConn();
			con.setAutoCommit(false);
			//Board(int postid, int accno, String ptype)
			String sql = "INSERT INTO board (accno, ptype) VALUES (?, ?)" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bd.getAccno());
			pstmt.setString(2, bd.getPtype());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//포토갤러리 조회
	public ArrayList<Photog> getPgList() {
		ArrayList<Photog> pgList = new ArrayList<Photog>();
		try {
			setConn();
			String sql = "SELECT * from photog";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Photog(int postid, int accno, String title, Date uploaddate, String content, String imgurl)
				pgList.add(new Photog(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content"),
							rs.getString("imgurl")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pgList;
	}
	
	//제목으로 포토갤러리 조회
	public ArrayList<Photog> getPgListTitle(String title) {
		ArrayList<Photog> pgList = new ArrayList<Photog>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM photog\r\n"
					+ "WHERE title LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Photog(int postid, int accno, String title, Date uploaddate, String content, String imgurl)
				pgList.add(new Photog(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content"),
							rs.getString("imgurl")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pgList;
	}

	//내용으로 포토갤러리 조회
	public ArrayList<Photog> getPgListContent(String content) {
		ArrayList<Photog> pgList = new ArrayList<Photog>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM photog\r\n"
					+ "WHERE content LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Photog(int postid, int accno, String title, Date uploaddate, String content, String imgurl)
				pgList.add(new Photog(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content"),
							rs.getString("imgurl")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pgList;
	}

	//제목,내용으로 포토갤러리 조회
	public ArrayList<Photog> getPgListTitle_Content(String title, String content) {
		ArrayList<Photog> pgList = new ArrayList<Photog>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM photog\r\n"
					+ "WHERE title LIKE '%'||?||'%'"
					+ "AND content LIKE '%'||?||'%' ORDER BY uploaddate desc ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Photog(int postid, int accno, String title, Date uploaddate, String content, String imgurl)
				pgList.add(new Photog(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content"),
							rs.getString("imgurl")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pgList;
	}

	//제목,내용으로 포토갤러리 조회
	public ArrayList<Photog> getPgList_All(String input) {
		ArrayList<Photog> pgList = new ArrayList<Photog>();
		try {
			setConn();
			String sql = "SELECT * FROM photog\r\n"
					+ "WHERE title LIKE '%'||?||'%'\r\n"
					+ "OR content LIKE '%'||?||'%' ORDER BY uploaddate desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, input);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Photog(int postid, int accno, String title, Date uploaddate, String content, String imgurl)
				pgList.add(new Photog(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content"),
							rs.getString("imgurl")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pgList;
	}

	

	//포토갤러리 게시글번호로 조회 (단일데이터)
	public Photog getPgList_Postid(int postid) {
		Photog pg = new Photog();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM photog\r\n"
					+ "WHERE postid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//Photog(int postid, int accno, String title, Date uploaddate, String content, String imgurl)
				pg = new Photog(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content"),
							rs.getString("imgurl")
							);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pg;
	}

	//포토갤러리 데이터 수
	public int getPgList_Count() {
		int cnt=0;
		try {
			setConn();
			String sql = "SELECT count(*) c \r\n"
					+ "FROM photog";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt("c");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	//포토갤러리 게시글번호 이용해서 작성자 이름 반환
	public String getPgList_Name(int postid) {
		String name = "";
		try {
			setConn();
			String sql = "SELECT name\r\n"
					+ "FROM Account\r\n"
					+ "WHERE accno IN (\r\n"
					+ "	SELECT accno\r\n"
					+ "	FROM photog\r\n"
					+ "	WHERE postid=?\r\n"
					+ ")";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);
			rs = pstmt.executeQuery();
			rs.next();
			name=rs.getString("name");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return name;
	}

	//포토갤러리 사용자번호(accno)이용해서 최근에 등록한 전체 게시글목록에서 postid반환
	public int getBdList_postid(int accno) {
		int postid = 0;
		try {
			setConn();
			String sql = "SELECT * FROM board WHERE accno=? ORDER BY postid desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accno);
			rs = pstmt.executeQuery();
			rs.next();
			postid=rs.getInt("postid");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return postid;
	}

	// 포토갤러리 등록
	// 수정필요
	public void insertPgList(Photog pg) {
		try {
			setConn();
			con.setAutoCommit(false);
			//Photog(int accno, String title, String content, String imgurl)
			String sql = "INSERT INTO photog (postid, accno, title, uploaddate, content, imgurl) VALUES (?, ?, ?, sysdate(), ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pg.getPostid());
			pstmt.setInt(2, pg.getAccno());
			pstmt.setString(3, pg.getTitle());
			pstmt.setString(4, pg.getContent());
			pstmt.setString(5, pg.getImgurl());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 포토갤러리 수정
	public void updatePgList(Photog pg) {
		try {
			setConn();
			con.setAutoCommit(false); 
			String sql = "UPDATE photog\r\n"
					+ "	SET title = ?,\r\n"
					+ "		content = ?,\r\n"
					+ "		imgurl = ?\r\n"
					+ "	WHERE postid = ?\r\n"
					+ "	AND accno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pg.getTitle());
			pstmt.setString(2, pg.getContent());
			pstmt.setString(3, pg.getImgurl());
			pstmt.setInt(4, pg.getPostid());
			pstmt.setInt(5, pg.getAccno());
			
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//포토갤러리 삭제
	public void deletePgList(int postid) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "DELETE FROM photog\r\n"
					+ "WHERE postid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);

			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}

		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//생태볼거리 조회
	public ArrayList<Ecog> getEgList() {
		ArrayList<Ecog> egList = new ArrayList<Ecog>();
		try {
			setConn();
			String sql = "SELECT * from ecog";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Ecog(int postid, int accno, String mm, String exspace, String lcategory, String mcategory, String scategory,
				//String sname, String kname, String distribution, String content, String imgurl)
				egList.add(new Ecog(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("mm"),
						rs.getString("exspace"),
						rs.getString("lcategory"),
						rs.getString("mcategory"),
						rs.getString("scategory"),
						rs.getString("sname"),
						rs.getString("kname"),
						rs.getString("distribution"),
						rs.getString("content"),
						rs.getString("imgurl")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return egList;
	}

	//생태볼거리 조회 (조건 : 월정보(필수), 관정보
	public ArrayList<Ecog> getEgListM_Space(String mm, String space) {
		ArrayList<Ecog> egList = new ArrayList<Ecog>();
		try {
			setConn();
			String sql = "SELECT * \r\n"
					+ "FROM ecog\r\n"
					+ "WHERE mm=?\r\n"
					+ "AND exspace LIKE '%'||?||'%'\r\n"
					+ "ORDER BY EXSPACE desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mm);
			pstmt.setString(2, space);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Ecog(int postid, int accno, String mm, String exspace, String lcategory, String mcategory, String scategory,
				//String sname, String kname, String distribution, String content, String imgurl)
				egList.add(new Ecog(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("mm"),
						rs.getString("exspace"),
						rs.getString("lcategory"),
						rs.getString("mcategory"),
						rs.getString("scategory"),
						rs.getString("sname"),
						rs.getString("kname"),
						rs.getString("distribution"),
						rs.getString("content"),
						rs.getString("imgurl")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return egList;
	}

	//생태볼거리 조회 (조건 : 학명)
	public ArrayList<Ecog> getEgListKname(String kname) {
		ArrayList<Ecog> egList = new ArrayList<Ecog>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM ecog\r\n"
					+ "WHERE kname LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kname);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Ecog(int postid, int accno, String mm, String exspace, String lcategory, String mcategory, String scategory,
				//String sname, String kname, String distribution, String content, String imgurl)
				egList.add(new Ecog(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("mm"),
						rs.getString("exspace"),
						rs.getString("lcategory"),
						rs.getString("mcategory"),
						rs.getString("scategory"),
						rs.getString("sname"),
						rs.getString("kname"),
						rs.getString("distribution"),
						rs.getString("content"),
						rs.getString("imgurl")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return egList;
	}

	//간행물 게시글번호로 조회 (단일데이터)
	public Ecog getEgList_Postid(int postid) {
		Ecog eg = new Ecog();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM ecog\r\n"
					+ "WHERE postid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//Ecog(int postid, int accno, String mm, String exspace, String lcategory, String mcategory, String scategory,
				//		String sname, String kname, String distribution, String content, String imgurl)
				eg = new Ecog(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("mm"),
							rs.getString("exspace"),
							rs.getString("lcategory"),
							rs.getString("mcategory"),
							rs.getString("scategory"),
							rs.getString("sname"),
							rs.getString("kname"),
							rs.getString("distribution"),
							rs.getString("content"),
							rs.getString("imgurl")
							);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return eg;
	}
	// 생태볼거리 등록
	public void insertEgList(Ecog eg) {
		try {
			setConn();
			con.setAutoCommit(false);
			//Ecog(int postid, int accno, String mm, String exspace, String lcategory, String mcategory, String scategory,
			//String sname, String kname, String distribution, String content, String imgurl)
			String sql = "INSERT INTO ecog (postid, accno, mm, exspace, lcategory, mcategory, scategory, sname, kname, distribution, content, imgurl) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, eg.getPostid());
			pstmt.setInt(2, eg.getAccno());
			pstmt.setString(3, eg.getMm());
			pstmt.setString(4, eg.getExspace());
			pstmt.setString(5, eg.getLcategory());
			pstmt.setString(6, eg.getMcategory());
			pstmt.setString(7, eg.getScategory());
			pstmt.setString(8, eg.getSname());
			pstmt.setString(9, eg.getKname());
			pstmt.setString(10, eg.getDistribution());
			pstmt.setString(11, eg.getContent());
			pstmt.setString(12, eg.getImgurl());

			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 생태볼거리 수정
	public void updateEgList(Ecog eg) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "UPDATE ecog\r\n"
					+ "	SET mm=?,\r\n"
					+ "		exspace=?,\r\n"
					+ "		LCATEGORY =?,\r\n"
					+ "		MCATEGORY =?,\r\n"
					+ "		SCATEGORY =?,\r\n"
					+ "		SNAME = ?,\r\n"
					+ "		KNAME = ?,\r\n"
					+ "		DISTRIBUTION = ?,\r\n"
					+ "		CONTENT = ?,\r\n"
					+ "		IMGURL = ?\r\n"
					+ "	WHERE POSTID = ?\r\n"
					+ "	AND ACCNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, eg.getMm());
			pstmt.setString(2, eg.getExspace());
			pstmt.setString(3, eg.getLcategory());
			pstmt.setString(4, eg.getMcategory());
			pstmt.setString(5, eg.getScategory());
			pstmt.setString(6, eg.getSname());
			pstmt.setString(7, eg.getKname());
			pstmt.setString(8, eg.getDistribution());
			pstmt.setString(9, eg.getContent());
			pstmt.setString(10, eg.getImgurl());
			pstmt.setInt(11, eg.getPostid());
			pstmt.setInt(12, eg.getAccno());

			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}

		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//생태볼거리 삭제
	public void deleteEgList(int postid) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "DELETE FROM ecog\r\n"
					+ "WHERE postid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);

			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}

		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//공모전캠페인 조회
	public ArrayList<Campaign> getCpList() {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT * from campaign";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}

	//진행중인 공모전캠페인 조회
	public ArrayList<Campaign> getCpListNow() {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT * FROM campaign WHERE sysdate BETWEEN sdate AND edate ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}

	//예정 공모전캠페인 조회
	public ArrayList<Campaign> getCpListFuture() {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT * FROM campaign WHERE sysdate<sdate ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}

	//예정 공모전캠페인 조회
	public ArrayList<Campaign> getCpListPast() {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT * FROM campaign WHERE sysdate>edate ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}

	//제목으로 공모전캠페인 조회
	public ArrayList<Campaign> getCpListTitle(String title) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT * FROM campaign WHERE title LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}

	//내용으로 공모전캠페인 조회
	public ArrayList<Campaign> getCpListContent(String content) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT * FROM campaign WHERE content LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}

	//제목 + 내용으로 공모전캠페인 조회
	public ArrayList<Campaign> getCpListTitle_Content(String title, String content) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title LIKE '%'||?||'%'\r\n"
					+ "AND content LIKE '%'||?||'%'\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//제목 + 내용으로 진행중인 공모전캠페인 조회
	public ArrayList<Campaign> getCpListTitle_Content_Now(String title, String content) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title LIKE '%'||?||'%'\r\n"
					+ "AND content LIKE '%'||?||'%'\r\n"
					+ "AND sysdate BETWEEN sdate AND edate\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//제목 + 내용으로 진행중인 공모전캠페인 조회
	public ArrayList<Campaign> getCpListTitle_Content_Future(String title, String content) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title LIKE '%'||?||'%'\r\n"
					+ "AND content LIKE '%'||?||'%'\r\n"
					+ "AND sysdate<sdate\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//제목 + 내용으로 진행중인 공모전캠페인 조회
	public ArrayList<Campaign> getCpListTitle_Content_Past(String title, String content) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title LIKE '%'||?||'%'\r\n"
					+ "AND content LIKE '%'||?||'%'\r\n"
					+ "AND sysdate>edate\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//제목 or 내용(전체내용)으로 공모전캠페인 조회
	public ArrayList<Campaign> getCpListAll(String input) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title LIKE '%'||?||'%'\r\n"
					+ "OR content LIKE '%'||?||'%'\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, input);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//제목 or 내용(전체내용)으로 진행중인 공모전캠페인 조회
	public ArrayList<Campaign> getCpListAll_Now(String input) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE (title LIKE '%'||?||'%' OR content LIKE '%'||?||'%')\r\n"
					+ "AND sysdate BETWEEN sdate AND edate\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, input);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//제목 or 내용(전체내용)으로 예정 공모전캠페인 조회
	public ArrayList<Campaign> getCpListAll_Future(String input) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE (title LIKE '%'||?||'%' OR content LIKE '%'||?||'%')\r\n"
					+ "AND sysdate<sdate\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, input);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//제목 or 내용(전체내용)으로 종료 공모전캠페인 조회
	public ArrayList<Campaign> getCpListAll_Past(String input) {
		ArrayList<Campaign> cpList = new ArrayList<Campaign>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE (title LIKE '%'||?||'%' OR content LIKE '%'||?||'%')\r\n"
					+ "AND sysdate>edate\r\n"
					+ "ORDER BY edate DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, input);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				cpList.add(new Campaign(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("title"),
						rs.getString("poster"),
						rs.getString("link"),
						rs.getString("content"),
						rs.getDate("sdate"),
						rs.getDate("edate")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpList;
	}
	//캠페인 데이터 수
	public int getCpList_Count() {
		int cnt=0;
		try {
			setConn();
			String sql = "SELECT count(*) c \r\n"
					+ "FROM campaign WHERE title IS NOT null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt("c");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	//캠페인 데이터 수(현재)
	public int getCpList_Count_Now() {
		int cnt=0;
		try {
			setConn();
			String sql = "SELECT count(*) c\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title IS NOT NULL\r\n"
					+ "AND sysdate BETWEEN sdate AND edate";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt("c");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	//캠페인 데이터 수(예정)
	public int getCpList_Count_Future() {
		int cnt=0;
		try {
			setConn();
			String sql = "SELECT count(*) c\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title IS NOT NULL\r\n"
					+ "AND sysdate<sdate";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt("c");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	//캠페인 데이터 수(종료)
	public int getCpList_Count_Past() {
		int cnt=0;
		try {
			setConn();
			String sql = "SELECT count(*) c\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE title IS NOT NULL\r\n"
					+ "AND sysdate>edate";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt("c");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	//간행물 게시글번호로 조회 (단일데이터)
	public Campaign getCpList_Postid(int postid) {
		Campaign c = new Campaign();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM campaign\r\n"
					+ "WHERE postid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
				//Date edate)
				c = new Campaign(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getString("poster"),
							rs.getString("link"),
							rs.getString("content"),
							rs.getDate("sdate"),
							rs.getDate("edate")
							);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return c;
	}
	// 공모전캠페인 등록
	public void insertCpList(Campaign cp) {
		try {
			setConn();
			con.setAutoCommit(false);
			//Campaign(int postid, int accno, String title, String poster, String link, String content, String sdate_s,
			//String edate_s)
			String sql = "INSERT INTO campaign values(?,?,?,?,?,?,to_date(?,'YYYY-MM-DD'),to_date(?,'YYYY-MM-DD'))";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cp.getPostid());
			pstmt.setInt(2, cp.getAccno());
			pstmt.setString(3, cp.getTitle());
			pstmt.setString(4, cp.getPoster());
			pstmt.setString(5, cp.getLink());
			pstmt.setString(6, cp.getContent());
			pstmt.setString(7, cp.getSdate_s());
			pstmt.setString(8, cp.getEdate_s());

			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 공모전캠페인 수정
	public void updateCpList(Campaign cp) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "UPDATE campaign \r\n"
					+ "	SET title=?,\r\n"
					+ "		poster=?,\r\n"
					+ "		link=?,\r\n"
					+ "		content=?,\r\n"
					+ "		sdate=to_date(?,'YYYY-MM-DD'),\r\n"
					+ "		edate=to_date(?,'YYYY-MM-DD')\r\n"
					+ "	WHERE POSTID = ?\r\n"
					+ "	AND ACCNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cp.getTitle());
			pstmt.setString(2, cp.getPoster());
			pstmt.setString(3, cp.getLink());
			pstmt.setString(4, cp.getContent());
			pstmt.setString(5, cp.getSdate_s());
			pstmt.setString(6, cp.getEdate_s());
			pstmt.setInt(7, cp.getPostid());
			pstmt.setInt(8, cp.getAccno());

			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}

		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//공모전캠페인 삭제
	public void deleteCpList(int postid) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "DELETE FROM campaign\r\n"
					+ "WHERE postid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);

			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//간행물 조회
	public ArrayList<Posting> getPtList() {
		ArrayList<Posting> ptList = new ArrayList<Posting>();
		try {
			setConn();
			String sql = "SELECT * from posting";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Posting(int postid, int accno, String subtype, String title, Date uploaddate, String pfile, String content)
				ptList.add(new Posting(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("subtype"),
						rs.getString("title"),
						rs.getDate("uploaddate"),
						rs.getString("pfile"),
						rs.getString("content")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ptList;
	}

	//제목으로 간행물 조회
	public ArrayList<Posting> getPtListTitle(String title) {
		ArrayList<Posting> ptList = new ArrayList<Posting>();
		try {
			setConn();
			String sql = "SELECT * FROM posting WHERE title LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Posting(int postid, int accno, String subtype, String title, Date uploaddate, String pfile, String content)
				ptList.add(new Posting(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("subtype"),
						rs.getString("title"),
						rs.getDate("uploaddate"),
						rs.getString("pfile"),
						rs.getString("content")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ptList;
	}

	//내용으로 간행물 조회
	public ArrayList<Posting> getPtListContent(String content) {
		ArrayList<Posting> ptList = new ArrayList<Posting>();
		try {
			setConn();
			String sql = "SELECT * FROM posting WHERE content LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Posting(int postid, int accno, String subtype, String title, Date uploaddate, String pfile, String content)
				ptList.add(new Posting(
						rs.getInt("postid"),
						rs.getInt("accno"),
						rs.getString("subtype"),
						rs.getString("title"),
						rs.getDate("uploaddate"),
						rs.getString("pfile"),
						rs.getString("content")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ptList;
	}

	//제목,내용으로 포토갤러리 조회
	public ArrayList<Posting> getPListTitle_Content(String title, String content) {
		ArrayList<Posting> pList = new ArrayList<Posting>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM posting\r\n"
					+ "WHERE title LIKE '%'||?||'%'"
					+ "AND content LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Posting(int postid, int accno, String subtype, String title, 
				//Date uploaddate, String pfile, String content)
				pList.add(new Posting(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("subtype"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("pfile"),
							rs.getString("content")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pList;
	}
	//간행물 데이터 수
	public int getPList_Count() {
		int cnt=0;
		try {
			setConn();
			String sql = "SELECT count(*) c FROM posting";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt("c");
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	//제목,내용으로 포토갤러리 조회
	public ArrayList<Posting> getPList_All(String input) {
		ArrayList<Posting> pList = new ArrayList<Posting>();
		try {
			setConn();
			String sql = "SELECT * FROM posting\r\n"
					+ "WHERE title LIKE '%'||?||'%'\r\n"
					+ "OR content LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, input);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Posting(int postid, int accno, String subtype, String title, 
				//Date uploaddate, String pfile, String content)
				pList.add(new Posting(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("subtype"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("pfile"),
							rs.getString("content")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pList;
	}
	//간행물 게시글번호로 조회 (단일데이터)
	public Posting getPList_Postid(int postid) {
		Posting p = new Posting();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM posting\r\n"
					+ "WHERE postid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//Posting(int postid, int accno, String subtype, String title, Date uploaddate, String pfile, String content)
				p = new Posting(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("subtype"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("pfile"),
							rs.getString("content")
							);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return p;
	}
	//간행물 게시글번호 이용해서 작성자 이름 반환
		public String getPList_Name(int postid) {
			String name = "";
			try {
				setConn();
				String sql = "SELECT name\r\n"
						+ "FROM Account\r\n"
						+ "WHERE accno IN (\r\n"
						+ "	SELECT accno\r\n"
						+ "	FROM posting\r\n"
						+ "	WHERE postid=?\r\n"
						+ ")";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, postid);
				rs = pstmt.executeQuery();
				rs.next();
				name=rs.getString("name");
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("DB 에러 : "+e.getMessage());
			} catch ( Exception e ) {
				System.out.println("일반 예외 : "+e.getMessage());
			} finally {
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return name;
		}
	// 간행물 등록
	public void insertPList(Posting pt) {
		try {
			setConn();
			con.setAutoCommit(false);
			//Posting(int postid, int accno, String subtype, String title, String uploaddate_s, String pfile, String content)
			String sql = "INSERT INTO posting values(?,?,?,?,sysdate,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pt.getPostid());
			pstmt.setInt(2, pt.getAccno());
			pstmt.setString(3, pt.getSubtype());
			pstmt.setString(4, pt.getTitle());
			pstmt.setString(5, pt.getPfile());
			pstmt.setString(6, pt.getContent());
			
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 간행물 수정
	public void updatePList(Posting pt) {
		try {
			setConn();
			con.setAutoCommit(false); 
			String sql = "UPDATE posting\r\n"
					+ "	SET subtype=?,\r\n"
					+ "		title=?,\r\n"
					+ "		pfile=?,\r\n"
					+ "		content=?\r\n"
					+ "	WHERE POSTID = ?\r\n"
					+ "	AND ACCNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pt.getSubtype());
			pstmt.setString(2, pt.getTitle());
			pstmt.setString(3, pt.getPfile());
			pstmt.setString(4, pt.getContent());
			pstmt.setInt(5, pt.getPostid());
			pstmt.setInt(6, pt.getAccno());
			
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//간행물 삭제
	public void deletePList(int postid) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "DELETE FROM posting\r\n"
					+ "WHERE postid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postid);
			
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public ArrayList<Search> searchTitle(String searchStr) {
		ArrayList<Search> searchTitle = new ArrayList<Search>();
		try {
			setConn();
			String sql = "SELECT ptg.postid, ptg.accno, title, uploaddate, content, b.PTYPE  FROM photog ptg, board b\r\n"
					+ "WHERE ptg.POSTID = b.POSTID\r\n"
					+ "AND title LIKE '%' || ? || '%'\r\n"
					+ "UNION\r\n"
					+ "SELECT psg.postid, psg.accno, title, uploaddate, content, b.PTYPE  FROM posting psg, board b\r\n"
					+ "WHERE psg.POSTID = b.POSTID\n"
					+ "AND title LIKE '%' || ? || '%'\r\n"
					+ "UNION\r\n"
					+ "SELECT c.postid, c.accno, title, sdate AS uploaddate, content, b.PTYPE  FROM campaign c, board b\r\n"
					+ "WHERE c.POSTID = b.POSTID\r\n"
					+ "AND title LIKE '%' || ? || '%'\r\n"
					+ "UNION\r\n"
					+ "SELECT e.postid, e.accno, kname AS title, NULL uploaddate, content, b.PTYPE  FROM ecog e, board b\r\n"
					+ "WHERE e.POSTID = b.POSTID\r\n"
					+ "AND kname LIKE '%' || ? || '%'";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchStr);
			pstmt.setString(2, searchStr);
			pstmt.setString(3, searchStr);
			pstmt.setString(4, searchStr);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				searchTitle.add(new Search(
							rs.getString("ptype"),
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return searchTitle;
	}
	
	//제목으로 통합검색
	public ArrayList<Search> searchContent(String searchStr) {
		ArrayList<Search> searchContent = new ArrayList<Search>();
		try {
			setConn();
			String sql = "SELECT ptg.postid, ptg.accno, title, uploaddate, content, b.PTYPE  FROM photog ptg, board b\n"
					+ "WHERE ptg.POSTID = b.POSTID \n"
					+ "AND content LIKE '%' || ? || '%'\n"
					+ "UNION\n"
					+ "SELECT psg.postid, psg.accno, title, uploaddate, content, b.PTYPE  FROM posting psg, board b\n"
					+ "WHERE psg.POSTID = b.POSTID\n"
					+ "AND content LIKE '%' || ? || '%'\n"
					+ "UNION\n"
					+ "SELECT c.postid, c.accno, title, sdate AS uploaddate, content, b.PTYPE  FROM campaign c, board b\n"
					+ "WHERE c.POSTID = b.POSTID\n"
					+ "AND content LIKE '%' || ? || '%'\n"
					+ "UNION\n"
					+ "SELECT e.postid, e.accno, kname AS title, NULL uploaddate, content, b.PTYPE  FROM ecog e, board b\n"
					+ "WHERE e.POSTID = b.POSTID\n"
					+ "AND content LIKE '%' || ? || '%'";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchStr);
			pstmt.setString(2, searchStr);
			pstmt.setString(3, searchStr);
			pstmt.setString(4, searchStr);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				searchContent.add(new Search(
							rs.getString("ptype"),
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return searchContent;
	}

	//제목, 내용으로 통합검색
	public ArrayList<Search> searchAll(String searchStr) {
		ArrayList<Search> searchAll = new ArrayList<Search>();
		try {
			setConn();
			String sql = "SELECT ptg.postid, ptg.accno, title, uploaddate, content, b.PTYPE  FROM photog ptg, board b\n"
					+ "WHERE ptg.POSTID = b.POSTID\n"
					+ "AND title LIKE '%'|| ? ||'%'\n"
					+ "UNION\n"
					+ "SELECT psg.postid, psg.accno, title, uploaddate, content, b.PTYPE  FROM posting psg, board b\n"
					+ "WHERE psg.POSTID = b.POSTID\n"
					+ "AND title LIKE '%'|| ? ||'%'\n"
					+ "UNION\n"
					+ "SELECT c.postid, c.accno, title, sdate AS uploaddate, content, b.PTYPE  FROM campaign c, board b\n"
					+ "WHERE c.POSTID = b.POSTID\n"
					+ "AND title LIKE '%'|| ? ||'%'\n"
					+ "UNION\n"
					+ "SELECT e.postid, e.accno, kname AS title, NULL uploaddate, content, b.PTYPE  FROM ecog e, board b\n"
					+ "WHERE e.POSTID = b.POSTID\n"
					+ "AND kname LIKE '%'|| ? ||'%'\n"
					+ "UNION\n"
					+ "SELECT ptg.postid, ptg.accno, title, uploaddate, content, b.PTYPE  FROM photog ptg, board b\n"
					+ "WHERE ptg.POSTID = b.POSTID\n"
					+ "AND content LIKE '%'|| ? ||'%'\n"
					+ "UNION\n"
					+ "SELECT psg.postid, psg.accno, title, uploaddate, content, b.PTYPE  FROM posting psg, board b\n"
					+ "WHERE psg.POSTID = b.POSTID\n"
					+ "AND content LIKE '%'|| ? ||'%'\n"
					+ "UNION\n"
					+ "SELECT c.postid, c.accno, title, sdate AS uploaddate, content, b.PTYPE  FROM campaign c, board b\n"
					+ "WHERE c.POSTID = b.POSTID\n"
					+ "AND content LIKE '%'|| ? ||'%'\n"
					+ "UNION\n"
					+ "SELECT e.postid, e.accno, kname AS title, NULL uploaddate, content, b.PTYPE  FROM ecog e, board b\n"
					+ "WHERE e.POSTID = b.POSTID\n"
					+ "AND content LIKE '%'|| ? ||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchStr);
			pstmt.setString(2, searchStr);
			pstmt.setString(3, searchStr);
			pstmt.setString(4, searchStr);
			pstmt.setString(5, searchStr);
			pstmt.setString(6, searchStr);
			pstmt.setString(7, searchStr);
			pstmt.setString(8, searchStr);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				searchAll.add(new Search(
							rs.getString("ptype"),
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("title"),
							rs.getDate("uploaddate"),
							rs.getString("content")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return searchAll;
	}


	//전체 게시글 목록 조회 (게시글유형을 이용해서)
	public Board getBdPtype(String ptype) {
		Board bd = new Board();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "FROM board\r\n"
					+ "WHERE ptype=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ptype);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//Board(int postid, int accno, String ptype)
				bd = new Board(
							rs.getInt("postid"),
							rs.getInt("accno"),
							rs.getString("ptype")
							);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bd;
	}

	//프로그램 조회
	public ArrayList<Programs> getPrgList() {
		ArrayList<Programs> prgList = new ArrayList<Programs>();
		try {
			setConn();
			String sql = "SELECT * from Programs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//String pname, String category, String target, String days, int capacity,
				//date span, date ptime, String loc, String contents
				prgList.add(new Programs(
							rs.getString("pname"),
							rs.getString("category"),
							rs.getString("target"),
							rs.getString("days"),
							rs.getInt("capacity"),
							rs.getDate("span1"),
							rs.getDate("span2"),
							rs.getString("ptime"),
							rs.getString("loc"),
							rs.getString("contents")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return prgList;
	}

	//예약 목록 조회
	public ArrayList<Rez> getRezList(int rezid) {
		ArrayList<Rez> rezList = new ArrayList<Rez>();
		try {
			setConn();
			String sql = "select * from rez where rezid=?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rezid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//int rezid, int accno, String pname
				rezList.add(new Rez(
						rs.getInt("rezid"),
						rs.getString("rezdate"),
						rs.getString("pname"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("pay")
				));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rezList;
	}

	public ArrayList<Rez> getRezList3(String name) {
		ArrayList<Rez> rezList = new ArrayList<Rez>();
		try {
			setConn();
			String sql = "SELECT *\r\n"
					+ "from rez\r\n"
					+ "WHERE name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rezList.add(new Rez(
						rs.getInt("rezid"),
						rs.getString("rezdate"),
						rs.getString("pname"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("pay")
				));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("DB 에러: "+e.getMessage());
		} catch(Exception e) {
			System.out.println("일반 예외: "+e.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rezList;
	}

	public void insertRez_2(Rez ins) {
		try {
			setConn();
			con.setAutoCommit(false);
			//rezid, rezdate, pname, name, email, phone, pay
			String sql = "INSERT INTO rez (rezdate, pname, name, email, phone, pay) VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ins.getDate());
			pstmt.setString(2, ins.getPname());
			pstmt.setString(3, ins.getName());
			pstmt.setString(4, ins.getEmail());
			pstmt.setString(5, ins.getPhone());
			pstmt.setString(6, ins.getPay());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("DB 에러: "+e.getMessage());
			// commit 전에 예외가 발생하면 rollback 처리
			try {
				con.rollback();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("일반 예외: "+e.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteRez(int rezid) {
		try {
			setConn();
			con.setAutoCommit(false);
			String sql = "DELETE FROM rez\r\n"
					+ "WHERE rezid = ?";
			pstmt = con.prepareStatement(sql);
			System.out.println(rezid);
			pstmt.setInt(1, rezid);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}

		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//프로그램 조회
	public ArrayList<Programs> getPrgList(String pname) {
		ArrayList<Programs> prgList = new ArrayList<Programs>();
		try {
			setConn();
			String sql = "SELECT * from Programs where pname = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//String pname, String category, String target, String days, int capacity,
				//date span, date ptime, String loc, String contents
				prgList.add(new Programs(
							rs.getString("pname"),
							rs.getString("category"),
							rs.getString("target"),
							rs.getString("days"),
							rs.getInt("capacity"),
							rs.getDate("span1"),
							rs.getDate("span2"),
							rs.getString("ptime"),
							rs.getString("loc"),
							rs.getString("contents")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return prgList;
	}

	//프로그램 조회
	public ArrayList<Programs> getPrgCtgList() {
		ArrayList<Programs> ctgList = new ArrayList<Programs>();
		try {
			setConn();
			String sql = "SELECT DISTINCT category from Programs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//String pname, String category, String target, String days, int capacity,
				//date span, date ptime, String loc, String contents
				ctgList.add(new Programs(
							rs.getString("category")
							));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 에러 : "+e.getMessage());
		} catch ( Exception e ) {
			System.out.println("일반 예외 : "+e.getMessage());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ctgList;
	}
}