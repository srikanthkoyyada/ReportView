package com.report.app;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransStreamResponse {
	
	private int transRecordCount;
	private String transFileName;
	private List<DailyTransJournalResponseDTO>  dailyTransJournals;
	private String paginationDataFetchType;

}
