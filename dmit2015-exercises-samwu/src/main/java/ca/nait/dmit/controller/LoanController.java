package ca.nait.dmit.controller;

import ca.nait.dmit.domain.Loan;
import ca.nait.dmit.domain.LoanSchedule;
import helper.JSFHelper;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.dataset.BarDataset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("cheeseBurger")
@ViewScoped
public class LoanController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Loan currentLoan = new Loan();
	private LoanSchedule[] loanScheduleTable;
	private String loanChart;

	public Loan getCurrentLoan() {
		return currentLoan;
	}
	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}
	public LoanSchedule[] getLoanScheduleTable() {
		return loanScheduleTable;
	}

	public String getLoanChart() {
		return loanChart;
	}
	public void buttonPressed() {
		loanScheduleTable = currentLoan.loanScheduleTable();
		double monthlyPayment = currentLoan.monthlyPayment();
		String message = String.format("Your monthly payment is %f",
				monthlyPayment);
		JSFHelper.addInfoMessage(message);

//		ChartSeries yearSeries = new ChartSeries();
//		yearSeries.setLabel("Years");
		List<String> years = new ArrayList<>();
		List<Number> remainingBalances = new ArrayList<>();

		for( int year = 1; year <= currentLoan.getAmortizationPeriod(); year++ )
		{
//			yearSeries.set(year,
//					loanScheduleTable[year * 12 - 1].getRemainingBalance());
			years.add(String.valueOf(year));
			remainingBalances.add(loanScheduleTable[year * 12 - 1].getRemainingBalance());
		}
//		loanChart.addSeries(yearSeries);

		loanChart = new BarChart()
				.setData(new BarData()
						.addDataset(new BarDataset()
								.setData(remainingBalances)
								.setLabel("My Second Dataset")
								.setBorderWidth(1)
						)
						.setLabels(years)
				).toJson();
	}



}
