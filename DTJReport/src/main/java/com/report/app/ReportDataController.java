package com.report.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportDataController {
	
	@Autowired
	private ReportServiceProviderProxy serviceProxy;

	@RequestMapping("/getTransJournal")
	public TransStreamResponse getAllTrans(@RequestBody DailyTransJournalRequestDTO request) {
		return serviceProxy.getAllTrans(request);
	}
	
	@RequestMapping(value = "/printDTJReport", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PrintResponse> printDTJReport(@RequestBody DailyTransJournalRequestDTO request){
		return serviceProxy.printDTJReport(request);
	}
	
	
	@RequestMapping(value = "/getTransJournalExcel", method = RequestMethod.GET)
	public void getTransJournalExcel(HttpServletResponse response, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("level") int level,
			@RequestParam("transType") String transType, @RequestParam("sortColumn") String sortColumn,
			@RequestParam("sortOrder") String sortOrder) {
		ResponseEntity<byte[]> entity=serviceProxy.getTransJournalExcel(response, startDate, endDate, level, transType, sortColumn, sortOrder);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
		String excelFileName = "DTJReport_" + formatter.format(LocalDateTime.now()) + ".xlsx";
		try {
			
			byte[] outArray =entity.getBody();
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
