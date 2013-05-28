package th.co.aoe.makedev.missconsult.rest.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptCareer;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptCareerPK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptMessageConfig;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptMessageConfigPK;
import th.co.aoe.makedev.missconsult.managers.ImportTestService;

public class ImportTest {
	public static void main(String[] args) {
		final ApplicationContext springContext = new ClassPathXmlApplicationContext(
                new String[] {
                		 "th/co/aoe/makedev/missconsult/rest/config/applicationContext-common.xml",
                		 "th/co/aoe/makedev/missconsult/rest/config/applicationContext-hibernate.xml"});
                		/* "th/co/aoe/makedev/missconsult/rest/config/applicationContext-missconsult-resource.xml",
                		 "th/co/aoe/makedev/missconsult/rest/config/applicationContext-root-router.xml",
						"th/co/aoe/makedev/missconsult/rest/config/applicationContext-server.xml"});  */
		ImportTestService  importTestService =(ImportTestService)springContext.getBean("importTestService");
		System.out.println(importTestService);
		//String filename="/usr/local/data/Work/PROJECT/IMakeDev/Exam/Report/Message_Code/Message/FJAE.xls";
		String path_message="/usr/local/data/Work/PROJECT/IMakeDev/Exam/Report/Message_Code/Message/"; 
		String[] messages={"IPPI","IPPE","IPAI","IPAE","IJPI","IJPE","IJAI","IJAE","FPPI","FPPE","FPAI","FPAE","FJPI","FJPE","FJAI","FJAE"};
		//String[] messages={"IPPI"};
		for (int i = 0; i < messages.length; i++) {
			List<MissEptMessageConfig>  missEptMessageConfigs=getMessage(path_message+messages[i]+".xls");
			for (MissEptMessageConfig missEptMessageConfig : missEptMessageConfigs) {
			//	System.out.println("code-->"+missEptMessageConfig.getId().getCode());
			//	System.out.println("key-->"+missEptMessageConfig.getMemcKey());
				/*if(missEptMessageConfig.getMemcKey().equals("General Personality"))
					System.out.println("missEptMessageConfig->"+missEptMessageConfig.getMemcMessage());*/
				importTestService.saveMissEptMessageConfig(missEptMessageConfig);
			}
		}
		
		/*String path_code="/usr/local/data/Work/PROJECT/IMakeDev/Exam/Report/Message_Code/Code/"; 
		String[] codes={"IPPI","IPPE","IPAI","IPAE","IJPI","IJPE","IJAI","IJAE","FPPI","FPPE","FPAI","FPAE","FJPI","FJPE","FJAI","FJAE"};
		//String[] codes={"FJAI","FJAE"};
		//filename ="/usr/local/data/Work/PROJECT/IMakeDev/Exam/Report/Message_Code/Code/FJAE.xls";
		for (int i = 0; i < codes.length; i++) {
			List<MissEptCareer> missEptCareers= getCareer(path_code+codes[i]+".xls");
			for (MissEptCareer missEptCareer : missEptCareers) {
				importTestService.saveMissEptCareer(missEptCareer);
			}
		}*/
		
        // Obtain the Restlet component from the Spring context and start it
        //((Component) springContext.getBean("top")).start();
	}
	//private void getCode(byte[] byteArray){
	private static List<MissEptMessageConfig>  getMessage(String filename){
/*		ByteArrayInputStream bais = 
				new ByteArrayInputStream(byteArray);*/
		  List<MissEptMessageConfig> missEptMessageConfigs =null;
		FileInputStream fin=null;
		try {
			fin = new FileInputStream(filename);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	HSSFWorkbook wb =null;
	try {
		//wb= new HSSFWorkbook(bais);
		wb= new HSSFWorkbook(fin);		
		Sheet sheet1_0 = wb.getSheetAt(0);
		String sheetName=sheet1_0.getSheetName();
		String[] codes=sheetName.split("_");
		String code =codes[0];
		int endRow=Integer.parseInt(codes[1]);
		 Row r=null;
		 //MEMC_KEY	MEMC_DESC	MEMC_MESSAGE	MEMC_ORDER	MEMC_LANG
		   NumberFormat    format  =    NumberFormat.getNumberInstance();
	          format.setGroupingUsed(false);
	         missEptMessageConfigs =new ArrayList<MissEptMessageConfig>(endRow-1);
		 for(int i=1;i<endRow;i++){
        	 r= sheet1_0.getRow(i); 
        	String MEMC_KEY= r.getCell(0).getStringCellValue();
        	String MEMC_DESC= r.getCell(1)!=null?r.getCell(1).getStringCellValue():null;
        	String MEMC_MESSAGE=  r.getCell(2)!=null?r.getCell(2).getStringCellValue():null;
        	String MEMC_ORDER= format.format(r.getCell(3).getNumericCellValue());
        	String MEMC_LANG=format.format(r.getCell(4).getNumericCellValue());
        	/*System.out.println("MEMC_KEY->"+MEMC_KEY);
        	System.out.println("MEMC_DESC->"+MEMC_DESC);
        	System.out.println("MEMC_MESSAGE->"+MEMC_MESSAGE);
        	System.out.println("MEMC_ORDER->"+MEMC_ORDER);
        	System.out.println("MEMC_LANG->"+Integer.parseInt(MEMC_LANG+""));
        	System.out.println("<---------------->");*/
        	MissEptMessageConfig missEptMessageConfig =new MissEptMessageConfig();
        	MissEptMessageConfigPK pk =new MissEptMessageConfigPK();
        	pk.setCode(code);
        	pk.setMemcLang(MEMC_LANG);
        	pk.setMemcOrder(Integer.valueOf(MEMC_ORDER));
        	missEptMessageConfig.setId(pk);
        	missEptMessageConfig.setMemcDesc(MEMC_DESC);
        	missEptMessageConfig.setMemcKey(MEMC_KEY);
        	missEptMessageConfig.setMemcMessage(MEMC_MESSAGE);
        	missEptMessageConfigs.add(missEptMessageConfig);
		 }
		System.out.println(sheetName);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(fin!=null)
				fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	return missEptMessageConfigs;
}
	private static List<MissEptCareer> getCareer(String filename){
		/*		ByteArrayInputStream bais = 
						new ByteArrayInputStream(byteArray);*/
		  List<MissEptCareer> missEptCareers =null;
				FileInputStream fin=null;
				try {
					fin = new FileInputStream(filename);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			HSSFWorkbook wb =null;
			try {
				//wb= new HSSFWorkbook(bais);
				wb= new HSSFWorkbook(fin);		
				Sheet sheet1_0 = wb.getSheetAt(0);
				String sheetName=sheet1_0.getSheetName();
				String[] codes=sheetName.split("_");
				String code=codes[0];
				int endRow=Integer.parseInt(codes[1]);
				 Row r=null;
				// MEC_CAREER_NAME	MEC_ORDER	MEC_LANG
				 NumberFormat    format  =    NumberFormat.getNumberInstance();
		          format.setGroupingUsed(false);
		          missEptCareers =new ArrayList<MissEptCareer>(endRow-1);
				 for(int i=1;i<endRow;i++){
		        	 r= sheet1_0.getRow(i); 
		        	String MEC_CAREER_NAME= r.getCell(0).getStringCellValue(); 
		        	String MEMC_ORDER= format.format(r.getCell(1).getNumericCellValue());
		        	String MEMC_LANG= format.format(r.getCell(2).getNumericCellValue());
		        	/*System.out.println("MEC_CAREER_NAME->"+MEC_CAREER_NAME);
		        	System.out.println("MEMC_ORDER->"+MEMC_ORDER);
		        	System.out.println("MEMC_LANG->"+MEMC_LANG);
		        	System.out.println("<---------------->");*/
		        	MissEptCareer missEptCareer =new MissEptCareer();
		        	MissEptCareerPK pk =new MissEptCareerPK();
		        	pk.setMecCode(code);
		        	pk.setMecLang(MEMC_LANG);
		        	pk.setMecOrder(Integer.valueOf(MEMC_ORDER));
		        	missEptCareer.setId(pk);
		        	missEptCareer.setMecCareerName(MEC_CAREER_NAME);
		        	missEptCareers.add(missEptCareer);
				 }
				//System.out.println(sheetName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(fin!=null)
						fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			return missEptCareers;
		}
/*private String setAnswer(Session session, String filePath) {
	FileInputStream fileIn = null;
	//FileOutputStream fileOut = null;
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
//	String[] extensions = filePath.split("\\.");
	String outPut = "";
	try {
		try {
			fileIn = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		POIFSFileSystem fs = null;
		try {
			fs = new POIFSFileSystem(fileIn);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(fs);
		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//byte[] bytes = fs.toByteArray();
		/*
		 * Workbook wb=null; try { wb = WorkbookFactory.create(fs); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		 
		// Write the output to a file
		// outPut=extensions[0]+"_"+msId.intValue()+"_"+meId.intValue()+"_"+mcaId.intValue()+"."+extensions[1];
		/*outPut = extensions[0] + "_" + msId.intValue() + "_"
				+ mcaId.intValue() + "." + extensions[1];
		try {
			fileOut = new FileOutputStream(outPut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			wb.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			wb.write(bos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes=	bos.toByteArray();
		Xls xls =new Xls();
		xls.setDataXls(bytes);
		session.save(xls);
	} finally {
		 if (bos != null)
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		if (fileIn != null)
			try {
				fileIn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	return outPut;
}*/
	}