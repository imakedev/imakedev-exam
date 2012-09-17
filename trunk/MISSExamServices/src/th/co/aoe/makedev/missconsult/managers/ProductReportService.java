package th.co.aoe.makedev.missconsult.managers;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.xstream.ProductReport;

public interface ProductReportService {
	public ProductReport findProductReport(String mode,String year) throws DataAccessException ;	
}
