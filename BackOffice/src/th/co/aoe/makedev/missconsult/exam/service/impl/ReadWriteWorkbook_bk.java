/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import th.co.aoe.makedev.missconsult.xstream.MissQuestion;


/**
 * This example demonstrates opening a workbook, modifying it and writing
 * the results back out.
 *
 * @author Glen Stampoultzis (glens at apache.org)
 */

public class ReadWriteWorkbook_bk {
	public static  List<MissQuestion> setQuestion(){
		//หนังสือยินยอมสละสิทธิ์การโอนรถยนต์คันแรกภายใน
		  FileInputStream fileIn = null;
	     //  FileOutputStream fileOut = null;
		  List<MissQuestion> missQuestions=new   ArrayList<MissQuestion>();
	        try
	        {
	            try {
					//fileIn = new FileInputStream("/usr/local/data/Work/PROJECT/MakeDev/Exam/Question_Test/Service_Attitude.xls"); // ok
	            	//fileIn = new FileInputStream("/usr/local/data/Work/PROJECT/MakeDev/Exam/Question_Test/EPT_PLUS_THAI.xls");// ok
	            	//fileIn = new FileInputStream("/usr/local/data/Work/PROJECT/MakeDev/Exam/Question_Test/The4Factors_1.xls"); // ok
	            //	fileIn = new FileInputStream("/usr/local/data/Work/PROJECT/MakeDev/Exam/Question_Test/The4Factors_2.xls"); // ok
	            	//fileIn = new FileInputStream("/usr/local/data/Work/PROJECT/MakeDev/Exam/Question_Test/LeadershipAssessment_1.xls"); // ok
	            	fileIn = new FileInputStream("/usr/local/data/Work/PROJECT/MakeDev/Exam/Question_Test/LeadershipAssessment_2.xls"); // ok
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            POIFSFileSystem fs=null;
				try {
					fs = new POIFSFileSystem(fileIn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            HSSFWorkbook wb=null;
				try {
					wb = new HSSFWorkbook(fs);
					 HSSFSheet sheet = wb.getSheetAt(0);
					 for (Row row : sheet) {
			            	//System.out.println("row id="+row.getRowNum()+"");
						 MissQuestion missQuestion=new MissQuestion();
			            	int rowId=row.getRowNum();
			            	//if(rowId>0){
			            //	ThaiCustomUser user =new ThaiCustomUser();
			                for (Cell cell : row) {
			                	
			                	int columnIndex= cell.getColumnIndex();
			                	String value="";
			                	//System.out.println("  row id="+cell.getRowIndex()+",column id="+columnIndex+"");  
			                	
			                	 switch (cell.getCellType()) {
			                     case Cell.CELL_TYPE_STRING:
			                    	 if(columnIndex==0){
			                    	 value = cell.getStringCellValue();
			                     //	 System.out.println("		CELL_TYPE_STRING="+value);
			                     	missQuestion.setMqId(Long.parseLong((cell.getRowIndex()+1)+""));
				                	missQuestion.setMqNameTh1(value);
				                	missQuestions.add(missQuestion);
			                    	 }
			                         break;
			                     case Cell.CELL_TYPE_NUMERIC:
			                         if (DateUtil.isCellDateFormatted(cell)) {
			                        	// System.out.println("		CELL_TYPE_NUMERIC DATE="+cell.getDateCellValue());
			                         } else {
			                        	 double  valuecel   = cell.getNumericCellValue();
			                             NumberFormat    format  =    NumberFormat.getNumberInstance();
			                            // format.setMaximumIntegerDigits(99);
			                             format.setGroupingUsed(false);
			 
			                            // System.out.println("		CELL_TYPE_NUMERIC="+format.format(valuecel));
			                             value = format.format(valuecel);
			                        	 //System.out.println("		CELL_TYPE_NUMERIC="+Double.toString(cell.getNumericCellValue()));
			                        	// System.out.println("		CELL_TYPE_NUMERIC="+cell.getNumericCellValue());
			                            /* Date    date    = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			                            	 DateFormat  format2  = new     SimpleDateFormat("yyyyMMdd");
			                         		System.out.println("		CELL_TYPE_NUMERIC date="+format2.format(date));
			                         	*/
			                         }
			                         break;
			                     case Cell.CELL_TYPE_BOOLEAN:
			                        // System.out.println(cell.getBooleanCellValue());
			                         break;
			                     case Cell.CELL_TYPE_FORMULA:
			                        // System.out.println("yy="+cell.getCellFormula());
			                         break;
			                     default:
			                       //  System.out.println();
			                 } 
			                } 
			              }
			           // } 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
	        } finally {
	            
	            if (fileIn != null)
					try {
						fileIn.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        }
	        return missQuestions;
	} 
    public static void main(String[] args) throws IOException {
    	//setAnswer();
    	/* List<MissQuestion> miss=setQuestion();
    	 System.out.println(miss.size());*/
    	//getUserFormXLS("");
    	
    }
}


