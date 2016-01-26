package ca.nait.dmit.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import ca.nait.dmit.domain.Loan;
import ca.nait.dmit.domain.LoanSchedule;
import helper.JSFHelper;

@Named("cheeseBurger")
@ViewScoped
public class LoanController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Loan currentLoan = new Loan();
	private LoanSchedule[] loanScheduleTable;
	private BarChartModel loanChart = new BarChartModel();
	
	public Loan getCurrentLoan() {
		return currentLoan;
	}
	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}
	public LoanSchedule[] getLoanScheduleTable() {
		return loanScheduleTable;
	}
	
	public BarChartModel getLoanChart() {
		return loanChart;
	}
	public void buttonPressed() {
		loanScheduleTable = currentLoan.loanScheduleTable();
		double monthlyPayment = currentLoan.monthlyPayment();
		String message = String.format("Your monthly payment is %f", 
				monthlyPayment);
		JSFHelper.addInfoMessage(message);
		
		loanChart.clear();
		ChartSeries yearSeries = new ChartSeries();
		yearSeries.setLabel("Years");
		for( int year = 1; year <= currentLoan.getAmortizationPeriod(); year++ ) 
		{
			yearSeries.set(year, 
					loanScheduleTable[year * 12 - 1].getRemainingBalance());
		}
		loanChart.addSeries(yearSeries);
	}
	
	

}
