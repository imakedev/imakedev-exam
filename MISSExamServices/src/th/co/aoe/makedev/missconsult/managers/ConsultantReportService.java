package th.co.aoe.makedev.missconsult.managers;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.xstream.ConsultantReport;

public interface ConsultantReportService {
	public ConsultantReport findConsultantReport(String mode,String mcontactId,String month,String year) throws DataAccessException ;
	public ConsultantReport findSales(String query) throws DataAccessException ;
}
