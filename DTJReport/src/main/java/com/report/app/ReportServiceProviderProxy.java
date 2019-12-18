package com.report.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "APIGATEWAY", configuration = FeignClinetConfiguration.class)
public interface ReportServiceProviderProxy {

	@RequestMapping("/api-auth/report/dailyTrans/getTransJournal")
	public TransStreamResponse getAllTrans(@RequestBody DailyTransJournalRequestDTO request);

	@RequestMapping(value = "/api-auth/report/dailyTrans/printDTJReport", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PrintResponse> printDTJReport(@RequestBody DailyTransJournalRequestDTO request);

	@RequestMapping(value = "/api-auth/report/dailyTrans/getTransJournalExcel", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getTransJournalExcel(HttpServletResponse response, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("level") int level,
			@RequestParam("transType") String transType, @RequestParam("sortColumn") String sortColumn,
			@RequestParam("sortOrder") String sortOrder);

}
