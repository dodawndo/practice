package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AdminNoticeDTO;

public class AdminDAO {
	// 실제 비즈니스 로직(DB 작업)을 처리할 BoardDAO 클래스 정의
	// => 인스턴스를 여러개 생성할 필요가 없으므로 싱글톤 디자인 패턴을 통해
//	    하나의 인스턴스를 생성하여 모두가 공유하도록 할 수 있음
	// --------------- 싱글톤 디자인 패턴을 활용한 BoardDAO 인스턴스 생성 작업 --------------
	// 1. 외부에서 인스턴스 생성이 불가능하도록 생성자 정의 시 private 접근제한자 적용
	// 2. 자신의 클래스 내부에서 직접 인스턴스 생성하여 변수에 저장
	// => 외부에서 변수에 접근이 불가능하도록 private 접근제한자 적용
	// => 클래스 로딩 시점에 인스턴스가 생성되도록 static 멤버변수로 선언
	// 3. 생성된 인스턴스를 외부로 리턴하기 위한 Getter 메서드 정의
	// => 외부에서 인스턴스 생성없이도 호출 가능하도록 static 메서드로 정의
	// => 이 때, 2번에서 선언된 변수도 static 변수로 선언되어야 함
	// (static 메서드 내에서 인스턴스 멤버에 접근 불가능하며, static 멤버만 접근 가능하므로)
	private static AdminDAO instance = new AdminDAO();

	private AdminDAO() {
	}

	public static AdminDAO getInstance() {
		return instance;
	};

	// ---------------------------------------------------------------------------------------
	// 외부(Service 클래스)로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버 변수와 Setter 메서드 정의
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

//--------------------------------------------------------------------------------------
	// 공지사항 게시물 수를 조회 하는 메서드
	public int selectListCount() {
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
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}

		return listCount;
	}

	//------------------------------------------------------------------------------
	public ArrayList<AdminNoticeDTO> selectNoticeList(int pageNum, int listLimit) {
			ArrayList<AdminNoticeDTO> noticeList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// 조회 시작 게시물 번호(행 번호) 계산
			int startRow = (pageNum - 1) * listLimit;
			
			try {
				// 게시물 목록 조회
				// => 참조글번호(board_re_ref) 기준 내림차순, 순서번호(board_re_seq) 기준 오름차순 정렬
				// => 조회 시작 게시물 번호(startRow) 부터 목록의 게시물 수(listLimit) 만큼 조회
				String sql = "SELECT * FROM notice LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				// 전체 게시물을 저장할 ArrayList<BoardDTO> 타입 객체 생성
				noticeList = new ArrayList<AdminNoticeDTO>();
				
				// while 문을 사용하여 조회 결과에 대한 반복 작업 수행
				while(rs.next()) {
					// 1개 게시물 정보를 저장할 BoardDTO 객체 생성 후 조회 결과 저장
					AdminNoticeDTO notice = new AdminNoticeDTO();
					notice.setNotice_idx(rs.getString("notice_idx"));
					notice.setAdmin_id(rs.getString("admin_id"));
					notice.setNotice_subject(rs.getString("notice_subject"));
					notice.setNotice_content(rs.getString("notice_content"));
					notice.setNotice_date(rs.getString("notice_date"));
					
					// 1개 게시물 정보를 다시 전체 게시물 정보 저장 객체(articleList)에 추가
					noticeList.add(notice);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - selectNoticeList()");
				e.printStackTrace();
			} finally {
				// DB 자원 반환(주의! Connection 객체 반환 금지!)
				close(pstmt);
				close(rs);
			}
			
			return noticeList;
		}

}
