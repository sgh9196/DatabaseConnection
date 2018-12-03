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
			/* 마이바티스 설정 XML 파일 경로 */
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
	
	/* Database Defualt Infomation Insert */
	public void insertDatabase(User user) {
		
		try {
			
			transactionOpen();
			
			sqlSession.insert(parameter + "XMLMapper.userInsert",  user);
			sqlSession.insert(parameter + "XMLMapper.dbInsert",  user);
			sqlSession.update(parameter + "XMLMapper.authorityUpdate",  user);
			sqlSession.update(parameter + "XMLMapper.dbUpdate");
			sqlSession.commit();
			
			transactionClose();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
