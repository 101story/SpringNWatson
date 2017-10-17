package board.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BoardDAO {
	private static BoardDAO boardDAO  = null;
	
	private String driver = null;
	private String url = null;
	private String username = null;
	private String password = null;
	
	
	private BoardDAO() {
		Properties pr = new Properties();
		String props = this.getClass().getResource("").getPath() + "/database.properties";
		try{
			pr.load(new FileInputStream(props));
			driver = pr.getProperty("driver");
			url = pr.getProperty("url");
			username = pr.getProperty("username");
			password = pr.getProperty("password");
			Class.forName("oracle.jdbc.OracleDriver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance(){
		if(boardDAO==null){
			boardDAO = new BoardDAO();
		}
		

		return boardDAO;
	}
	
	private void dbClose(PreparedStatement ps, Connection cn) {
		if(ps!=null)try{ps.close();}catch(Exception e){}
		if(cn!=null)try{cn.close();}catch(Exception e){}
	}
	
	private void dbClose( ResultSet rs, PreparedStatement ps, Connection cn) {
		if(rs!=null)try{rs.close();}catch(Exception e){}
		if(ps!=null)try{ps.close();}catch(Exception e){}
		if(cn!=null)try{cn.close();}catch(Exception e){}
	}
	
	private Connection getConnection() throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bigdata", "bigdata");
		return cn;
	}
	
	public boolean insertBoard(BoardVO boardVO) {

		Connection cn = null;
		PreparedStatement ps = null;
		boolean result = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO tb_board(no, title, name, content, pwd)");
		sql.append("VALUES(seq_board.nextval, ?,?,?,?)");
		
		try{
			
			cn = getConnection();
			
			ps = cn.prepareStatement(sql.toString());
			ps.setString(1, boardVO.getTitle());
			ps.setString(2, boardVO.getName());
			ps.setString(3, boardVO.getContent());
			ps.setString(4, boardVO.getPwd());
			ps.executeUpdate();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbClose(ps, cn);
		}
		
		return result;
	}
	



	public boolean updateBoard(BoardVO boardVO){

		Connection cn = null;
		PreparedStatement ps = null;
		boolean result = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update tb_board set");
		sql.append("	title=?, name=?, content=?");
		sql.append(" where no=? and pwd=?");
		
		try{
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setString(1, boardVO.getTitle());
			ps.setString(2, boardVO.getName());
			ps.setString(3, boardVO.getContent());
			ps.setLong(4, boardVO.getNo());
			ps.setString(5, boardVO.getPwd());
			if(ps.executeUpdate()>0){
				result = true;		
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbClose(ps, cn);
		}
		return result;
	}
	
	public boolean deleteBoard(BoardVO boardVO) {

		Connection cn = null;
		PreparedStatement ps = null;
		boolean result = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from tb_board");
		sql.append(" where no=? and pwd=?");
		
		try{
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, boardVO.getNo());
			ps.setString(2, boardVO.getPwd());
			
			if(ps.executeUpdate()>0){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbClose(ps, cn);
		}
		
		return result;
	}
	
	public List<BoardVO>  getBoardList(long startnum, long endnum) {

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" select b.*");
		sql.append(" from (select rownum as rnum, a.*");
		sql.append(" from (select  no, title, name, regdate, viewcount");
		sql.append(" from tb_board ");
		sql.append(" order by no desc) a) b");
		sql.append(" where rnum between ? and ? ");
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		try{

			cn = getConnection();

			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, startnum);
			ps.setLong(2, endnum);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BoardVO boardVO = new BoardVO();
				boardVO.setNo(rs.getLong("no"));
				boardVO.setName(rs.getString("name"));
				boardVO.setTitle(rs.getString("title"));
				boardVO.setRegdate(rs.getDate("regdate"));
				boardVO.setViewcount(rs.getInt("viewcount"));
				list.add(boardVO);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbClose(rs, ps, cn);
		}
		return list;
	}
	


	public long getTotalCount(){

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql_totalcount = "select count(*) as cnt from tb_board";
		long result = 0;
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		try{

			cn = getConnection();
			
			ps = cn.prepareStatement(sql_totalcount);
			rs = ps.executeQuery();
			
			if(rs.next()){
				result = rs.getLong("cnt");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbClose(rs, ps, cn);
		}
		return result;
	}
	
	public BoardVO getBoard(long no){

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select no, name, title, regdate, viewcount, content");
		sql.append(" from tb_board");
		sql.append(" where no=?");
		
		BoardVO vo = null;
		
		try{
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()){
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setViewcount(rs.getInt("viewcount"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbClose(rs, ps, cn);
		}
		return vo;
	}
}
