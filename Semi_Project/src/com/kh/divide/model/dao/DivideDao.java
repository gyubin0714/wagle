package com.kh.divide.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.divide.model.vo.Divide;
import com.kh.divide.model.vo.Divide_Comment;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductCategory;


public class DivideDao {
	
private Properties prop = new Properties();
	
	public DivideDao() {
		String fileName = DivideDao.class.getResource("/sql/divide/divide_mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
}

	
	public ArrayList<Divide> selectDivideList(Connection conn) {
		
		ArrayList<Divide> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDivideList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Divide d = new Divide();
				
				d.setdNo(rset.getInt("D_NO"));
				d.setTitle(rset.getString("D_TITLE"));
				d.setD_Count(rset.getInt("D_COUNT"));
				d.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(d);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int insertDivideBoard(Connection conn, Divide d) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertDivideBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, d.getTitle());
			pstmt.setString(2, d.getField());
			pstmt.setInt(3, Integer.parseInt(d.getCategory()));
			pstmt.setString(4, d.getDivide_YN());
			pstmt.setString(5, d.getContent());
			pstmt.setInt(6, Integer.parseInt(d.getMem_No()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateDivideBoard(Connection conn, Divide d) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDivideBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, d.getTitle());
			pstmt.setString(2, d.getField());
			pstmt.setInt(3, Integer.parseInt(d.getCategory()));
			pstmt.setString(4, d.getContent());
			pstmt.setInt(5,d.getdNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int insertAttachmentList(Connection conn, ArrayList<Attachment> list)  {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(Attachment at : list) {
				
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileLevel());
			
			result = pstmt.executeUpdate();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		close(pstmt);
		}

		return result;
	}
	
	public int insertNewAttachment(Connection conn, Attachment at) {
	    int result = 1;
	    PreparedStatement pstmt = null;
	    String sql = prop.getProperty("insertNewAttachmentList");

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, at.getRefBno());
	        pstmt.setString(2, at.getOriginName());
	        pstmt.setString(3, at.getChangeName());
	        pstmt.setString(4, at.getFilePath());
	        pstmt.setInt(5, at.getFileLevel());
	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    return result;
	}

	
    public int updateAttachment(Connection conn,ArrayList<Attachment> list) {
        int result = 1;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateAttachment");

        try {
        	pstmt = conn.prepareStatement(sql);
        	
        	for(Attachment at : list) {
	            
	            pstmt.setString(1, at.getOriginName());
	            pstmt.setString(2, at.getChangeName());
	            pstmt.setString(3, at.getFilePath());
	            pstmt.setInt(4, at.getFileLevel());
	            pstmt.setInt(5, at.getFileNo());
	
	            result = pstmt.executeUpdate();
	        }
        	
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }

	public ArrayList<ProductCategory> selectCategoryList(Connection conn) {
		ArrayList<ProductCategory> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			rset =pstmt.executeQuery();
			
			while(rset.next()) {
			ProductCategory c = new ProductCategory();
			c.setCateNo(rset.getInt("CATE_NO"));
			c.setCateName(rset.getString("CATE_NAME"));
			
			
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int increaseCount(Connection conn, int dNo) {
		
		int result = 0;
		String sql= prop.getProperty("increaseCount");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, dNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public Divide selectBoard(Connection conn, int dNo) {
		
		Divide d = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;	
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				d = new Divide();
				d.setdNo(rset.getInt("D_NO"));
				d.setTitle(rset.getString("D_TITLE"));
				d.setContent(rset.getString("D_CONTENT"));
				d.setCategory(rset.getString("CATE_NAME"));
				d.setDivide_YN(rset.getString("DIVIDE_KIND"));
				d.setMem_No(rset.getString("MEM_ID"));
				d.setReg_Date(rset.getDate("D_REG_DATE"));
				d.setField(rset.getString("D_LOCATION"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return d;
	}


	public ArrayList<Attachment> selectAttachment(Connection conn, int dNo) {

		ArrayList<Attachment> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				list.add(at);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int deleteDivide(Connection conn, int dno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteDivide");
		
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public ArrayList<Divide_Comment> selectReplyList(Connection conn, int dno) {
		
		ArrayList<Divide_Comment> list = new ArrayList();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, dno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Divide_Comment(rset.getInt("COMMENT_NO"),
								   rset.getString("MEM_ID"),
								   rset.getString("D_CONTENT"),
								   rset.getString("CREATE_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int insertReply(Connection conn, Divide_Comment d) {

		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, d.getdContent());
			pstmt.setInt(2, d.getdNo());
			pstmt.setInt(3, Integer.parseInt(d.getmNo()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}


	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}


	public ArrayList<Divide> selectList(Connection conn, PageInfo pi) {
		
		ArrayList<Divide> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1)* pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Divide d = new Divide();
				d.setdNo(rset.getInt("D_NO"));
				d.setTitle(rset.getString("D_TITLE"));
				d.setD_Count(rset.getInt("D_COUNT"));
				d.setDivide_YN(rset.getString("DIVIDE_KIND"));
				d.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(conn);
		}
		return list;
	}


	public Divide auctionBoard(Connection conn, int dNo) {
		
		Divide d = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;	
		String sql = prop.getProperty("auctionBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				d = new Divide();
				d.setTitle(rset.getString("D_TITLE"));
				d.setMem_No(rset.getString("MEM_ID"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return d;

	}

		public Product purchaseBoard(Connection conn, int pNo) {
		
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;	
		String sql = prop.getProperty("purchaseBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setMemNo(rset.getString("MEM_ID"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return p;

	}





	


	

	
}
