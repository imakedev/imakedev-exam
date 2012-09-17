package th.co.aoe.makedev.missconsult.managers;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.xstream.EPTNormReport;

public interface EPTNormReportService {
	public EPTNormReport findEPTNormReport(String mode,String maId) throws DataAccessException ;
	public EPTNormReport findCompanies(String query) throws DataAccessException ;
}
