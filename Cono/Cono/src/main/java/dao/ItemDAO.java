package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CategoryDTO;
import vo.ImgDTO;
import vo.ItemDTO;
import static db.JdbcUtil.*;

public class ItemDAO {
	private static ItemDAO instance = new ItemDAO();
		
		private ItemDAO() {}
	
		public static ItemDAO getInstance() {
			return instance;
		}
		Connection con;
	
		public void setConnection(Connection con) {
			this.con = con;
		}
		
		// ----------------------------------------------------------------------------------------
		// 상품 등록
		public int[] insertItem(ItemDTO item, ArrayList<ImgDTO> imgList, CategoryDTO categoryDTO, String member_id) {
//			System.out.println("dao - insertItem");
			
			int[] insertCount = new int[2]; // 각각의 sql 구문 실행 성공 여부를 담기 위한 배열
											// [0] -> item 테이블 INSERT 작업 성공 여부
											// [1] -> category 테이블 INSERT 작업 성공 여부
											// img 테이블의 경우 이미지가 없는 상태로 상품을 올려도 등록 가능하므로 여부를 따지지 않음 
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// --------------------------- item 테이블 insert 작업 ---------------------------------
				int item_idx = 1; 
				
				String sql = "SELECT COUNT(item_idx) FROM item";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					item_idx = Integer.parseInt(rs.getString(1)) + 1;
				}
				
				close(pstmt);
				
				sql = "INSERT INTO item "
					+ "VALUES (?, "
						   + "(SELECT shop_idx FROM shop WHERE member_id=?)"
						    + ",?,?,?,?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, item_idx);
				pstmt.setString(2, member_id);
				pstmt.setString(3, item.getItem_title());
				pstmt.setString(4, item.getItem_content());
				pstmt.setString(5, item.getItem_price());
				pstmt.setString(6, null);
				pstmt.setString(7, "0");
				pstmt.setString(8, "N");
				pstmt.setString(9, "N");
				pstmt.setString(10, "0");
				pstmt.setString(11, item.getItem_region());
				pstmt.setString(12, item.getItem_quantity());
				
				insertCount[0] = pstmt.executeUpdate();
				
				close(pstmt);
				// ---------------------------------------------------------------------------------------
				
				// ----------------------------- img 테이블 insert 작업 ----------------------------------
		
				sql = "INSERT INTO img VALUES (?,?,(SELECT shop_name FROM shop WHERE member_id=?),?,?)";
				int imgNum = 1;
				for(int i = 0; i < imgList.size(); i++) {
					
					ImgDTO img = imgList.get(i);
					if(img.getImg_name() == null || img.getImg_real_name() == null) {
						continue;
					}
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, imgNum);
					pstmt.setInt(2, item_idx);
					pstmt.setString(3, member_id);
					pstmt.setString(4, img.getImg_real_name());
					pstmt.setString(5, img.getImg_name());
					
					pstmt.executeUpdate();
					close(pstmt);
					imgNum++;
				}
				// ---------------------------------------------------------------------------------------
				
				// ---------------------- category 테이블 insert 작업 ------------------------------------
				sql = "INSERT INTO category VALUES (?,?,?,'0')";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, categoryDTO.getCategory_big());
				pstmt.setString(2, categoryDTO.getCategory_small());
				pstmt.setInt(3, item_idx);
				
				insertCount[1] = pstmt.executeUpdate();
				
				// ---------------------------------------------------------------------------------------
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생 - insertItem()");
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return insertCount;
		}
			
		// 상품 관리 페이지 (상품 조회 - 검색 기능, 필터링 기능)
		public ArrayList<String[]> selectItemList(String member_id, String keyword, String sell_status) {
//			System.out.println("DAO - selectItemList");
			ArrayList<String[]> itemList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// keyword가 null인 상태 (= 검색어 입력을 하지 않은 상태)
			// null인 상태를 고려하려면 sql 구문이 6개가 나와야함
			// 경우의 수를 줄이기 위해 keyword 값이 null일 때 널스트링으로 바꿔줌
			// => 똑같은 WHERE절 구문 사용 가능 (경우의 수 6 -> 3)
			if(keyword == null) {
				keyword = "";
			}
			
			// 상품 관리 페이지에 처음 들어갔을 때 sell_status가 null
			// => NullPointerException 예외 발생!
			// 예외 발생을 막기 위해 sell_status가 null일 경우 "0"(전체)로 바꿔줌
			if(sell_status == null) {
				sell_status = "0";
			}
			
			
			String sql = null;
			
			if(sell_status.equals("0")) {
				sql = "SELECT i.item_idx, i.item_title, i.item_price, i.item_date, i.item_status, COUNT(w.member_id), i.item_readCnt, i.item_hide "
					+ "FROM item AS i "
					+ "LEFT OUTER JOIN wish AS w "
					  + "ON i.item_idx = w.item_idx "
					+ "JOIN shop AS s "
			  		  + "ON i.shop_idx = s.shop_idx "
				   + "WHERE s.member_id = ? "
					 + "AND i.item_title LIKE ? "
				+ "GROUP BY i.item_idx "
				+ "ORDER BY i.item_idx+0 ASC";
				
			} else if(sell_status.equals("1")) {
				sql = "SELECT i.item_idx, i.item_title, i.item_price, i.item_date, i.item_status, COUNT(w.member_id), i.item_readCnt, i.item_hide "
						+ "FROM item AS i "
						+ "LEFT OUTER JOIN wish AS w "
						  + "ON i.item_idx = w.item_idx "
						+ "JOIN shop AS s "
				  		  + "ON i.shop_idx = s.shop_idx "
					   + "WHERE s.member_id = ? "
						 + "AND i.item_title LIKE ? "
						 + "AND i.item_status = 'N' "
					+ "GROUP BY i.item_idx "
					+ "ORDER BY i.item_idx+0 ASC";
				
			} else {
				sql = "SELECT i.item_idx, i.item_title, i.item_price, i.item_date, i.item_status, COUNT(w.member_id), i.item_readCnt, i.item_hide "
						+ "FROM item AS i "
						+ "LEFT OUTER JOIN wish AS w "
						  + "ON i.item_idx = w.item_idx "
						+ "JOIN shop AS s "
				  		  + "ON i.shop_idx = s.shop_idx "
					   + "WHERE s.member_id = ? "
						 + "AND i.item_title LIKE ? "
						 + "AND i.item_status = 'Y' "
					+ "GROUP BY i.item_idx "
					+ "ORDER BY i.item_idx+0 ASC";
			}
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_id);
				pstmt.setString(2, "%" + keyword + "%");
				
				rs = pstmt.executeQuery();
				
				itemList = new ArrayList<String[]>();
				
				while(rs.next()) {
					String[] item = new String[8];
					
					item[0] = rs.getString(1);
					item[1] = rs.getString(2);
					item[2] = rs.getString(3);
					item[3] = rs.getString(4);
					item[4] = rs.getString(5);
					item[5] = rs.getString(6);
					item[6] = rs.getString(7);
					item[7] = rs.getString(8);
					
					
					itemList.add(item);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생 - selectItemList()");
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return itemList;
		}

		public int updateItemHide(String item_hide, String item_idx) {
//			System.out.println("DAO - updateItemHide");
			int updateCount = 0;
			
			PreparedStatement pstmt = null;
			
			if(item_hide.equals("N")) {
				item_hide = "Y";
			} else if(item_hide.equals("Y")) {
				item_hide = "N";
			}
			
			String sql = "UPDATE item SET item_hide=? WHERE item_idx=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, item_hide);
				pstmt.setString(2, item_idx);
				
				updateCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생 - updateItemHide()");
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return updateCount;
		}

		public ArrayList<ImgDTO> selectImgList(String item_idx) {
			// TODO Auto-generated method stub
			return null;
		}

}
