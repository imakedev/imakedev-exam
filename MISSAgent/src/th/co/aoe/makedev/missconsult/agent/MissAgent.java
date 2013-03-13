package th.co.aoe.makedev.missconsult.agent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MissAgent {
	static Logger logger = Logger.getLogger(MissAgent.class.getName());
	public int checkMissTestResult(){
		int index=0;
		int executeUpdate = 0;
		Connection con=null;
		try {
			Class.forName(DB_JDBC_DRIVER_CLASS_NAME).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(DB_JDBC_URL, DB_USERNAME,
					DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			StringBuffer query = new StringBuffer("SELECT  account.ma_clear_test  FROM "+DB_SCHEMA+".MISS_TEST_RESULT result inner join " +
					" "+DB_SCHEMA+".MISS_CANDIDATE candidate on result.mca_id = candidate.mca_id inner join" +
					" "+DB_SCHEMA+".MISS_ACCOUNT account on candidate.ma_id = account.ma_id" +
					"  where ( result.MTR_HIDE_STATUS='1' or result.MTR_HIDE_STATUS is null )and account.ma_clear_test  is not null and account.ma_clear_test !='' and account.ma_clear_test !='0'" +
					"  GROUP BY account.ma_clear_test order by account.ma_clear_test asc" );
			 
			ResultSet rs = null;
			PreparedStatement pst1 =null;
			if (con != null) {
				try {
					pst1= con.prepareStatement(query.toString()); 
					rs = pst1.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			String ma_clear_test=null;
			List list = new ArrayList(); 
			try {
				while (rs.next()) {
					ma_clear_test=rs.getString("ma_clear_test");
					logger.debug(ma_clear_test);
					list.add(ma_clear_test);
				}
				
				logger.debug(list.size() );
				String value="";
			int size=list.size();
			for (int i = 0; i < size; i++) {
				value= (String)list.get(i);
				query.setLength(0);
				query.append("UPDATE "+DB_SCHEMA+".MISS_TEST_RESULT result inner join " +
						" "+DB_SCHEMA+".MISS_CANDIDATE candidate on result.mca_id = candidate.mca_id inner join " +
						" "+DB_SCHEMA+".MISS_ACCOUNT account on candidate.ma_id = account.ma_id " +
						" set  result.MTR_HIDE_STATUS='0' where ( result.MTR_HIDE_STATUS='1' or result.MTR_HIDE_STATUS is null ) " +
						"  and account.ma_clear_test  is not null and account.ma_clear_test !='' and account.ma_clear_test !='0'" +
						"   and   account.ma_clear_test='"+value+"' " +
						"and  DATEDIFF(CURDATE(),result.mtr_test_date) > ("+value+"*31) " +
					//	" and  result.mtr_id=21" +
						"");
				 

				pst1= con.prepareStatement(query.toString()); 
				executeUpdate = executeUpdate+pst1.executeUpdate();
				index++;
				if(index%100==0 && index!=0){ 
					try {
						//Thread.currentThread().sleep(3000);
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
			}finally{
				if(pst1!=null)
					try {
						pst1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				if(rs!=null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(con!=null)
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		return executeUpdate;
	} 
	public int checkMissCandidate(String type){
		Connection con=null;
		int index=0;
		int executeUpdate = 0;
		try {
			Class.forName(DB_JDBC_DRIVER_CLASS_NAME).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(DB_JDBC_URL, DB_USERNAME,
					DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// type=ma_clear_candidate_1
		StringBuffer query = new StringBuffer("SELECT  account."+type+"  FROM" +
					"	"+DB_SCHEMA+".MISS_CANDIDATE candidate   inner join " +
					" "+DB_SCHEMA+".MISS_ACCOUNT account on candidate.ma_id = account.ma_id " +
					" where ( candidate.MCA_HIDE_STATUS='1' or candidate.MCA_HIDE_STATUS is null  ) and account."+type+"  is not null and account."+type+" !='' " +
					" and account."+type+" !='0'" +
					"  GROUP BY account."+type+" order by account."+type+" asc" );
			 
			ResultSet rs = null;
			PreparedStatement pst1 =null;
			if (con != null) {
				try {
					pst1= con.prepareStatement(query.toString()); 
					rs = pst1.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			String ma_clear_candidate=null;
			List list = new ArrayList(); 
			try {
				while (rs.next()) {
					ma_clear_candidate=rs.getString(type);
					logger.debug(ma_clear_candidate);
					list.add(ma_clear_candidate);
				}
				
				logger.debug(list.size() );
				String value="";
				int size=list.size();
				for (int i = 0; i < size; i++) {
					value= (String)list.get(i);
					query.setLength(0); 
					query.append("update "+DB_SCHEMA+".MISS_CANDIDATE candidate inner join " +
							" "+DB_SCHEMA+".MISS_ACCOUNT account on candidate.ma_id = account.ma_id " +
							" set  candidate.MCA_HIDE_STATUS='0'" +
							"  where ( candidate.MCA_HIDE_STATUS='1' or candidate.MCA_HIDE_STATUS is null ) and account."+type+"  is not null " +
							" and account."+type+" !='' and account."+type+" !='0'" +
							" and   account."+type+"='"+value+"' " +
							" and  DATEDIFF(CURDATE(),candidate.mca_updated_date) > ("+value+"*31)" +
						//	" and  result.mtr_id=21" +
							"");
					 

					pst1= con.prepareStatement(query.toString()); 
					executeUpdate = executeUpdate+pst1.executeUpdate();
					index++;
					if(index%100==0 && index!=0){ 
						try {
							//Thread.currentThread().sleep(3000);
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
			}finally{
				if(pst1!=null)
					try {
						pst1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				if(rs!=null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(con!=null)
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		

		return executeUpdate;
	}
	private static String DB_SCHEMA=""; 
	private static String DB_JDBC_URL="";
	private static String DB_JDBC_DRIVER_CLASS_NAME="";
	private static String DB_USERNAME="";
	private static String DB_PASSWORD="";   
	public static void main(String[] args) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("/opt/agent/MC/mc_config.properties")); 
			DB_SCHEMA=prop.getProperty("DB_SCHEMA",  "MISS_CONSULT_EXAM");
			DB_JDBC_URL=prop.getProperty("DB_JDBC_URL", "jdbc:mysql://localhost:3306/MISS_CONSULT_EXAM");
			DB_JDBC_DRIVER_CLASS_NAME=prop.getProperty("DB_JDBC_DRIVER_CLASS_NAME", "com.mysql.jdbc.Driver");
			DB_USERNAME=prop.getProperty("DB_USERNAME", "root");
			DB_PASSWORD=prop.getProperty("DB_PASSWORD", "xx"); 
			MissAgent agent =new MissAgent();
			
			logger.info(" Start call agent ");
			int record_update=agent.checkMissTestResult();
			logger.info("  checkMissTestResult updated ="+record_update);
			String[] ma_clear_candidate = {"ma_clear_candidate_1","ma_clear_candidate_2","ma_clear_candidate_3"};
			for(int i=0;i<ma_clear_candidate.length;i++){ 
				record_update=agent.checkMissCandidate(ma_clear_candidate[i]);
				logger.info("  checkMissCandidate "+ma_clear_candidate[i]+" updated ="+record_update);
			}
			logger.info(" End call agent ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
