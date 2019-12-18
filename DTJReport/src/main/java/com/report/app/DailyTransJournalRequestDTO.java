package com.report.app;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyTransJournalRequestDTO {

	private String beginDate;
	private String endDate;
	private int level;
	private String transType;
	private int offset;
	private int limit;
	private String totalCountFlag;
	private String sortOrder;
	private String sortColumn;
	private String[] selectedColumnNames;
	private String[] selectedColumnValues;
	private String transDataFileName;

}
