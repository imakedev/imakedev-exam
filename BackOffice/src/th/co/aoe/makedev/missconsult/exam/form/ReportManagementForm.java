package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.ProductReport;
import th.co.aoe.makedev.missconsult.xstream.ServiceReport;

public class ReportManagementForm  extends CommonForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productReport_mode;
	private String productReport_year;
	private ProductReport productReport;
	private ServiceReport serviceReport;
	public ReportManagementForm(){
		productReport=new ProductReport();
		serviceReport=new ServiceReport();
	}
	public String getProductReport_mode() {
		return productReport_mode;
	}
	public void setProductReport_mode(String productReport_mode) {
		this.productReport_mode = productReport_mode;
	}
	public String getProductReport_year() {
		return productReport_year;
	}
	public void setProductReport_year(String productReport_year) {
		this.productReport_year = productReport_year;
	}
	public ProductReport getProductReport() {
		return productReport;
	}
	public void setProductReport(ProductReport productReport) {
		this.productReport = productReport;
	}
	public ServiceReport getServiceReport() {
		return serviceReport;
	}
	public void setServiceReport(ServiceReport serviceReport) {
		this.serviceReport = serviceReport;
	}

}
