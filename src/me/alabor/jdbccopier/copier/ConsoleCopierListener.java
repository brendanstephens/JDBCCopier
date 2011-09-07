package me.alabor.jdbccopier.copier;

import java.util.List;

import me.alabor.jdbccopier.database.meta.Table;

public class ConsoleCopierListener implements CopierListener {

	private int percentageDone = 0;
	
	@Override
	public void startCopyTable(Table table, long totalRows) {
		System.out.println("Copy table " + table + " (" + totalRows + " rows)");
		percentageDone = 0;
	}

	@Override
	public void copyTableStatus(Table table, long currentPos, long totalRows) {
		int percentage = (int)(currentPos * 100 / totalRows);

		if(percentageDone+10 < percentage) {
			System.out.println("Progress: " + percentageDone + "%");
			percentageDone = percentage;
		}
	}
	
	@Override
	public void error(Table table, Exception exception) {
		System.out.println("Error during copy table " + table + "!");
	}

	@Override
	public void endCopyTable(Table table) {
		System.out.println("Done 100%");
		System.out.println("-------------------------------------------");
	}

	@Override
	public void startCopy(List<Table> tables) {
		System.out.println("Start copy " + tables.size() + " tables");
		System.out.println("===========================================");
	}
	
	@Override
	public void endCopy(int totalSuccess, int totalError) {
		System.out.println("===========================================");
		System.out.println("Finished Process (" + totalSuccess + " suceeded, " + totalError + " errors)");
	}
	
}
