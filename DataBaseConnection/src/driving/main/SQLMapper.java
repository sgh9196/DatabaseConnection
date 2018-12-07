package driving.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.User;

public class SQLMapper {
	
	private static final String resource = "resources/mybatis/config-mybatis.xml";
	private static final String parameter = "org.mybatis.persistence.";
	
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession sqlSession;
	
	public SQLMapper() {
		try {
			/* 마이바티스 설정 DB 파일 경로 */
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/* 트랜잭션 Open */
	public void transactionOpen() {
		sqlSession = sqlSessionFactory.openSession();
	}

	/* 트랜잭션 Close */
	public void transactionClose() {
		sqlSession.close();
	}
	
	/* User Overlap Check */
	public int selectIdCheck(String userName) {
		
		int resultSQL = 0;
		
		try {
			
			transactionOpen();
			resultSQL = sqlSession.selectOne(parameter + "LoginMapper.idCheckSelect", userName);
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultSQL;

	}
	
	/* Database Defualt Infomation Insert */
	public void insertDatabase(User user) {
		
		try {
			
			transactionOpen();
			
			sqlSession.insert(parameter + "DBMapper.userInsert",  user);
			sqlSession.insert(parameter + "DBMapper.dbInsert",  user);
			sqlSession.update(parameter + "DBMapper.authorityUpdate",  user);
			sqlSession.update(parameter + "DBMapper.dbUpdate");
			
			sqlSession.commit();
			
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* User Login */
	public String selectLogin(User user) {
		
		String db = "";
		
		try {
			
			transactionOpen();
			
			int count = sqlSession.selectOne(parameter + "LoginMapper.userSelect", user);
			
			if(count>0) { db = sqlSession.selectOne(parameter + "LoginMapper.userDBSelect", user); }
			
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return db;
		
	}
	
}
