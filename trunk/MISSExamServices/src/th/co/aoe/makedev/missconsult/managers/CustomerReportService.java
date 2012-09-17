package th.co.aoe.makedev.missconsult.managers;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.xstream.CustomerReport;

public interface CustomerReportService {
	public CustomerReport findCustomerReport(String mode,String magId) throws DataAccessException ;
	public CustomerReport findGroups(String query) throws DataAccessException ;
}
