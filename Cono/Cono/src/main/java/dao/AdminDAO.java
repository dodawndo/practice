package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.AdminNoticeDTO;
import vo.AdminQNADTO;
import vo.AdminReportDTO;
import vo.MemberDTO;

public class AdminDAO {

	private static AdminDAO instance = new AdminDAO();

	public static AdminDAO getInstance() {
		return instance;
	}

	// ---------------------------------------------------------------------------------------
	// 외부(Service 클래스)로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버 변수와 Setter 메서드 정의
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// ---------------------------------------------------------------------------------------

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// --------------------------------------------------------------------------------------
	// 관리자 공지사항 게시물 수를 조회 하는 메서드
	public int selectNoticeListCount() {
		System.out.println("AdminDAO - selectListCount()");

		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(*) FROM notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectListCount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return listCount;
	}

	// ------------------------------------------------------------------------------
	// 관리자 공지사항 목록 조회
	public ArrayList<AdminNoticeDTO> selectNoticeList(int pageNum, int listLimit) {
		ArrayList<AdminNoticeDTO> noticeList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startRow = (pageNum - 1) * listLimit;

		try {
			String sql = "SELECT * FROM notice  ORDER BY notice_idx+0 DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt.executeQuery();

			noticeList = new ArrayList<AdminNoticeDTO>();

			while (rs.next()) {
				AdminNoticeDTO notice = new AdminNoticeDTO();
				notice.setNotice_idx(rs.getString("notice_idx"));
				notice.setAdmin_id(rs.getString("admin_id"));
				notice.setNotice_subject(rs.getString("notice_subject"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_date(rs.getString("notice_date"));

				noticeList.add(notice);
			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectNoticeList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return noticeList;
	}

	// ---------------------------------------------------------------
	// 관리자 공지사항 글쓰기

	public int insertArticle(AdminNoticeDTO adminNoticeDTO) {
		System.out.println("AdminDAO - insertArticle()");

		int insertCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 0; // 새 글 번호를 저장할 변수
		String notice_date ="";
		try {
//			String sql = "SELECT COUNT(notice_idx) FROM notice";
			String sql = "SELECT IFNULL(MAX(CAST(notice_idx as unsigned)), 0) + 1 AS num, LEFT(REPLACE(NOW(), '-', ''), 8) AS notice_date FROM notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

//			if (rs.next() && rs.getString(1) != null) {
//				System.out.println(rs.getString(1));
//				num = Integer.parseInt(rs.getString(1)) + 1;
//			}

			rs.next();
			num = Integer.parseInt(rs.getString("num"));
			notice_date = rs.getString("notice_date");
			close(pstmt);

			sql = "INSERT INTO notice VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, adminNoticeDTO.getAdmin_id());
			pstmt.setString(3, adminNoticeDTO.getNotice_subject());
			pstmt.setString(4, adminNoticeDTO.getNotice_content());
			pstmt.setString(5, notice_date);

			insertCount = pstmt.executeUpdate();

			commit(con);

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return insertCount;
	}

	// ------------------------------------------------------------------------------
	// 관리자 공지사항 상세페이지 조회
	public AdminNoticeDTO selectArticle(String notice_idx) {
		AdminNoticeDTO adminNoticeDTO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			String sql = "SELECT * FROM notice WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice_idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				adminNoticeDTO = new AdminNoticeDTO();
				adminNoticeDTO.setNotice_idx(rs.getString("notice_idx"));
				adminNoticeDTO.setAdmin_id(rs.getString("admin_id"));
				adminNoticeDTO.setNotice_subject(rs.getString("notice_subject"));
				adminNoticeDTO.setNotice_content(rs.getString("notice_content"));
				adminNoticeDTO.setNotice_date(rs.getString("notice_date"));

			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectArticle()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return adminNoticeDTO;
	}

	// --------------------------------------------------------
	// 관리자 공지사항 삭제
	public int deleteArticle(String notice_idx) {
		System.out.println("deleteArticle");
		System.out.println(notice_idx);
		int deleteCount = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM notice WHERE notice_idx=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice_idx);
			deleteCount = pstmt.executeUpdate();
			System.out.println("deleteCount" + deleteCount);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - deleteArticle()");
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	// ---------------------------------------------------
	// 관리자 공지사항 수정
	public int modifyNotice(AdminNoticeDTO notice) {
		int modifyCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE notice SET admin_id=?,notice_subject=?,notice_content=? WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAdmin_id());
			pstmt.setString(2, notice.getNotice_subject());
			pstmt.setString(3, notice.getNotice_content());
			pstmt.setString(4, notice.getNotice_idx());

			modifyCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - updateArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return modifyCount;
	}

	// ------------------------------------------------------------
	// 관리자 공지사항 검색

	public ArrayList<AdminNoticeDTO> searchNoticeList(String search, String searchType, String notice_idx) {
		ArrayList<AdminNoticeDTO> noticeList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT * FROM notice WHERE";
			if (searchType.equals("subject")) { // 제목으로 검색
				sql += " notice_subject ";
			} else if (searchType.equals("name")) { // 작성자
				sql += " admin_id ";
			}
			sql += "LIKE ? ORDER BY notice_idx+0 DESC";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();

			noticeList = new ArrayList<AdminNoticeDTO>();
			while (rs.next()) {

				AdminNoticeDTO notice = new AdminNoticeDTO();
				notice.setNotice_idx(rs.getString("notice_idx"));
				notice.setAdmin_id(rs.getString("admin_id"));
				notice.setNotice_subject(rs.getString("notice_subject"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_date(rs.getString("notice_date"));

				noticeList.add(notice);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - searchNoticeList()");
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}

		return noticeList;

	}

	// -------------------------------------------------------------
	// 관리자 qna 목록 갯수
	public int selectQNAListCount() {
		System.out.println("AdminDAO - selectQNAListCount()");

		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(*) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectQNAListCount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return listCount;
	}

	// -------------------------------------------------------------
	// 관리자 qna 리스트 조회
	public ArrayList<AdminQNADTO> selectQNAList(int pageNum, int listLimit) {
		ArrayList<AdminQNADTO> qnaList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startRow = (pageNum - 1) * listLimit;

		try {
//			String sql = "SELECT * FROM qna ORDER BY qna_re_ref+0 DESC LIMIT ?,?";
//			String sql = "SELECT * FROM qna ORDER BY qna_idx+0 DESC LIMIT ?,?";
			String sql = "SELECT @rownum:=@rownum+1 AS num" + ", A.qna_idx" + ", A.member_id" + ", A.qna_type"
					+ ", A.qna_subject" + ", A.qna_content" + ", A.qna_date" + ", A.qna_status" + ", A.qna_re_ref"
					+ ", A.qna_re_lev" + ", A.qna_re_seq" + ", A.qna_status"+ " FROM qna A" + ", (SELECT @rownum:=0) TMP"
					+ " ORDER BY qna_re_ref+0 DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt.executeQuery();

			qnaList = new ArrayList<AdminQNADTO>();

			while (rs.next()) {
				AdminQNADTO qna = new AdminQNADTO();
				qna.setQna_idx(rs.getString("qna_idx"));
				qna.setMember_id(rs.getString("member_id"));
				qna.setQna_type(rs.getString("qna_type"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				qna.setQna_re_seq(rs.getInt("qna_re_seq"));
				qna.setQna_date(rs.getString("qna_date"));
				qna.setQna_status(rs.getString("qna_status"));
				qnaList.add(qna);

			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectNoticeList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return qnaList;
	}

	// -----------------------------------------------------------------
	// 관리자 qna 글쓰기

	public int insertArticle(AdminQNADTO adminQNADTO, String sId) {
		System.out.println("AdminDAO - insertArticle()");

		int insertCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 0; // 새 글 번호를 저장할 변수
		String qna_date = "";
		try {
			String sql = "SELECT IFNULL(MAX(CAST(qna_idx as unsigned)), 0) + 1 AS num, LEFT(REPLACE(NOW(), '-', ''), 8) AS qna_date FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			num = Integer.parseInt(rs.getString("num"));
			qna_date = rs.getString("qna_date");
//			if (rs.next() && rs.getString(1) != null) {
//				System.out.println(rs.getString(1));
//				num = Integer.parseInt(rs.getString(1));
//				
//			}

			close(pstmt);

			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 새 글 번호
			pstmt.setString(2, sId);
			pstmt.setString(3, adminQNADTO.getQna_type());
			pstmt.setString(4, adminQNADTO.getQna_subject());
			pstmt.setString(5, adminQNADTO.getQna_content());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setString(9, qna_date);
			pstmt.setString(10, "답변대기");

			insertCount = pstmt.executeUpdate();

			commit(con);

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return insertCount;
	}

	// ------------------------------------------------------------------------------
	// 관리자 qna 상세페이지 조회
	public AdminQNADTO selectQNAArticle(String qna_idx, String sId) {
		AdminQNADTO adminQNADTO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			String sql = "SELECT * FROM qna WHERE qna_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna_idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				adminQNADTO = new AdminQNADTO();
				adminQNADTO.setQna_idx(rs.getString("qna_idx"));
				adminQNADTO.setQna_type(rs.getString("qna_type"));
				adminQNADTO.setQna_subject(rs.getString("qna_subject"));
				adminQNADTO.setMember_id(rs.getString("member_id"));
				adminQNADTO.setQna_content(rs.getString("qna_content"));
				adminQNADTO.setQna_date(rs.getString("qna_date"));
				adminQNADTO.setQna_re_ref(rs.getInt("qna_re_ref"));
				adminQNADTO.setQna_re_lev(rs.getInt("qna_re_lev"));
				adminQNADTO.setQna_re_seq(rs.getInt("qna_re_seq"));

			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectQNAArticle()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return adminQNADTO;
	}

	// --------------------------------------------------------
	// 관리자 qna 삭제
	public int deleteQNAArticle(String qna_idx) {
		System.out.println("deleteQNAArticle");
		int deleteCount = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM qna WHERE qna_idx=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna_idx);
			deleteCount = pstmt.executeUpdate();
			System.out.println("deleteCount" + deleteCount);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - deleteArticle()");
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	// ---------------------------------------------------
	// 관리자 qna 수정
	public int modifyQNA(AdminQNADTO qna) {
		System.out.println("modifyQNA - dao");
		int modifyCount = 0;
		System.out.println(qna.getQna_idx());
		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE qna SET qna_type=?,qna_subject=?,qna_content=? WHERE qna_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getQna_type());
			pstmt.setString(2, qna.getQna_subject());
			pstmt.setString(3, qna.getQna_content());
			pstmt.setString(4, qna.getQna_idx());

			modifyCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - modifyQNA()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return modifyCount;
	}

	// ---------------------------------------------------
	// 관리자 qna 검색
	public ArrayList<AdminQNADTO> searchQNAList(String search, String searchType, String qna_idx) {
		ArrayList<AdminQNADTO> qnaList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT * FROM qna WHERE";
			if (searchType.equals("subject")) { // 제목으로 검색
				sql += " qna_subject ";
			} else if (searchType.equals("name")) { // 작성자
				sql += " member_id ";
			}
			sql += "LIKE ? ORDER BY qna_idx+0 DESC";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();

			qnaList = new ArrayList<AdminQNADTO>();
			while (rs.next()) {

				AdminQNADTO qna = new AdminQNADTO();
				qna.setQna_idx(rs.getString("qna_idx"));
				qna.setMember_id(rs.getString("member_id"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_date(rs.getString("qna_date"));

				qnaList.add(qna);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - searchNoticeList()");
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}

		return qnaList;
	}
	// ---------------------------------------------------
	// 관리자 qna 답글

	public int insertReplyArticle(AdminQNADTO qna) {
		System.out.println("insertReplyArticle - dao");
		int insertCount = 0;

		PreparedStatement pstmt = null // 자동증가값, 현재시각 SELECT
				, pstmt4 = null // re_seq MAX값 SELECT
				, pstmt2 = null // 답글 INSERT
				, pstmt3 = null;// 답변완료 UPDATE
		ResultSet rs = null;

		int num = 0; // idx
		int seq_num = 0; // re_seq
		String qna_date = ""; // 현재시각

		try {
			String sql = "SELECT IFNULL(MAX(CAST(qna_idx as unsigned)), 0) + 1 AS num, LEFT(REPLACE(NOW(), '-', ''), 8) AS qna_date FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			num = Integer.parseInt(rs.getString("num"));
			qna_date = rs.getString("qna_date");

			sql = "SELECT IFNULL(MAX(qna_re_seq), 0) + 1 AS seq_num" + "  FROM qna" + " WHERE qna_re_ref = ?"
					+ "   AND qna_re_lev = ?";
			pstmt4 = con.prepareStatement(sql);
			pstmt4.setInt(1, qna.getQna_re_ref());
			pstmt4.setInt(2, qna.getQna_re_lev() + 1);
			rs = pstmt4.executeQuery();
			rs.next();
			seq_num = Integer.parseInt(rs.getString("seq_num"));

			// TODO UPDATE
//			sql = "UPDATE qna SET qna_re_seq=qna_re_seq+1 " + "WHERE qna_re_ref=? AND qna_re_seq>?";
//			pstmt2 = con.prepareStatement(sql);
//			pstmt2.setInt(1, qna.getQna_re_ref()); // 참조글번호
//			pstmt2.setInt(2, qna.getQna_re_seq()); // 순서번호
//			pstmt2.executeUpdate();
//			pstmt2.close();			

			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, num); // 새 글 번호
			pstmt2.setString(2, qna.getMember_id());
			pstmt2.setString(3, ""); // qna_type
			pstmt2.setString(4, qna.getQna_subject());
			pstmt2.setString(5, qna.getQna_content());
//			// -----------------------------------------------------------------------------
//			
			pstmt2.setInt(6, Integer.parseInt(qna.getQna_idx()));
			pstmt2.setInt(7, qna.getQna_re_lev() + 1);
			pstmt2.setInt(8, seq_num);
			pstmt2.setString(9, qna_date); // qna_date
			pstmt2.setString(10, "");

			sql = "UPDATE qna SET qna_status='답변완료' WHERE qna_idx = ?";
			pstmt3 = con.prepareStatement(sql);
//			sql = "select case when qna_re_lev>0 then '답변대기' else '답변완료' end as qna_";
			pstmt3.setString(1, qna.getQna_re_ref() + "");
			// -----------------------------------------------------------------------------

			insertCount = pstmt2.executeUpdate();
			pstmt3.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertReplyArticle()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(pstmt2);
			close(pstmt3);
		}

		return insertCount;

	}

	// ===================================================
	// 관리자 신고하기 리스트 갯수

	public int selectReportListCount() {
		System.out.println("AdminDAO - selectReportListCount()");

		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(*) FROM report";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectReportListCount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return listCount;
	}

	// 관리자 신고하기 리스트 조회
	public ArrayList<AdminReportDTO> selectReportList(int pageNum, int listLimit) {
		ArrayList<AdminReportDTO> reportList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startRow = (pageNum - 1) * listLimit;

		try {
			String sql = "SELECT * FROM report ORDER BY report_idx+0 DESC LIMIT ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt.executeQuery();

			reportList = new ArrayList<AdminReportDTO>();

			while (rs.next()) {
				AdminReportDTO report = new AdminReportDTO();
				report.setReport_idx(rs.getString("report_idx"));
				report.setMember_id(rs.getString("member_id"));
				report.setReport_type(rs.getString("report_type"));
				report.setReport_subject(rs.getString("report_subject"));
				report.setReport_content(rs.getString("report_content"));
				report.setReport_date(rs.getString("report_date"));

				reportList.add(report);

			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectNoticeList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return reportList;
	}

	// 관리자 신고하기 글쓰기
	public int insertArticle(AdminReportDTO adminReportDTO, String sId) {
		System.out.println("AdminDAO - insertArticle()");

		int insertCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 0; // 새 글 번호
		String report_date = "";
		try {
			String sql = "SELECT IFNULL(MAX(CAST(report_idx as unsigned)), 0) + 1 AS num, LEFT(REPLACE(NOW(), '-', ''), 8) AS report_date FROM report";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			num = Integer.parseInt(rs.getString("num"));
			report_date = rs.getString("report_date");
//			if (rs.next() && rs.getString(1) != null) {
//				System.out.println(rs.getString(1));
//				num = Integer.parseInt(rs.getString(1));
//				
//			}

			close(pstmt);

			sql = "INSERT INTO report VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 새 글 번호
			pstmt.setString(2, sId);
			pstmt.setString(3, adminReportDTO.getReport_type());
			pstmt.setString(4, adminReportDTO.getReport_subject());
			pstmt.setString(5, adminReportDTO.getReport_content());
			pstmt.setString(6, report_date);

			insertCount = pstmt.executeUpdate();

			commit(con);

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return insertCount;
	}

	// 관리자 신고하기 상세페이지 조회
	public AdminReportDTO selectReportArticle(String report_idx, String sId) {
		AdminReportDTO adminReportDTO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			String sql = "SELECT * FROM report WHERE report_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, report_idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				adminReportDTO = new AdminReportDTO();
				adminReportDTO.setReport_idx(rs.getString("report_idx"));
				adminReportDTO.setMember_id(rs.getString("member_id"));
				adminReportDTO.setReport_type(rs.getString("report_type"));
				adminReportDTO.setReport_subject(rs.getString("report_subject"));
				adminReportDTO.setReport_content(rs.getString("report_content"));
				adminReportDTO.setReport_date(rs.getString("report_date"));

			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectReportArticle()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return adminReportDTO;
	}

	// 관리자 신고하기 삭제
	public int deleteReportArticle(String report_idx) {
		System.out.println("deleteReportArticle");
		int deleteCount = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM report WHERE report_idx=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, report_idx);
			deleteCount = pstmt.executeUpdate();
			System.out.println("deleteCount" + deleteCount);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - deleteReportArticle()");
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	// 관리자 신고하기 수정
	public int modifyReport(AdminReportDTO report) {
		System.out.println("modifyReport - dao");
		int modifyCount = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE report SET report_type=?,report_subject=?,report_content=? WHERE report_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, report.getReport_type());
			pstmt.setString(2, report.getReport_subject());
			pstmt.setString(3, report.getReport_content());
			pstmt.setString(4, report.getReport_idx());

			modifyCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - modifyReport()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return modifyCount;
	}

	public ArrayList<AdminReportDTO> searchReportList(String search, String searchType, String report_idx) {
		ArrayList<AdminReportDTO> reportList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT * FROM report WHERE";
			if (searchType.equals("subject")) { // 제목으로 검색
				sql += " report_subject ";
			} else if (searchType.equals("name")) { // 작성자
				sql += " member_id ";
			}
			sql += "LIKE ? ORDER BY report_idx+0 DESC";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();

			reportList = new ArrayList<AdminReportDTO>();
			while (rs.next()) {

				AdminReportDTO qna = new AdminReportDTO();
				qna.setReport_idx(rs.getString("report_idx"));
				qna.setMember_id(rs.getString("member_id"));
				qna.setReport_subject(rs.getString("report_subject"));
				qna.setReport_content(rs.getString("report_content"));
				qna.setReport_date(rs.getString("report_date"));

				reportList.add(qna);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - searchReportList()");
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}

		return reportList;
	}
	// =================================================================================================================

	// 전체 회원 정보
	public ArrayList<MemberDTO> selectAllMember(int pageNum, int listLimit) {
		ArrayList<MemberDTO> memberList = null;

		int startRow = (pageNum - 1) * listLimit;

		try {
			String sql = "SELECT * FROM member ORDER BY member_date DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt.executeQuery();

			// 전체 게시물을 저장하는 객체 생성
			memberList = new ArrayList<MemberDTO>();

			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setMember_id(rs.getString("member_id"));
				member.setMember_nick(rs.getString("member_nick"));
				member.setMember_email(rs.getString("member_email"));
				member.setMember_phone(rs.getString("member_phone"));
				member.setMember_birth(rs.getString("member_birth"));
				member.setMember_date(rs.getString("member_date"));
				member.setMember_status(rs.getString("member_status"));

				memberList.add(member);
				System.out.println(memberList.toString());
			}

		} catch (SQLException e) {
			System.out.println("SQL 구문오류 - selectAllMember");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return memberList;
	}

	// 총 회원 수 조회
	public int selectListCount() {
		int listCount = 0;

		try {
			String sql = "SELECT COUNT(*) FROM member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectListCount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return listCount;
	}

	// 회원 탈퇴 본인/관리자 판별
	public boolean isExitMember(String member_id) {
		boolean isExitMember = false;

		try {
			String sql = "SELECT * FROM member WHERE member_id=? OR member_id=admin";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isExitMember = true;
			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - isExitMember()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return isExitMember;
	}

	// 회원 탈퇴 수행
	public int exitMember(String member_id) {
		int exitCount = 0;

		try {
			String sql = "DELETE FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);

			exitCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return exitCount;
	}

}
