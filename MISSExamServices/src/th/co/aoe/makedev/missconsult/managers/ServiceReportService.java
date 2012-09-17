package th.co.aoe.makedev.missconsult.managers;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.xstream.ServiceReport;

public interface ServiceReportService {
	public ServiceReport findServiceReport(String mode,String month,String year) throws DataAccessException ;
}
