package ca.nait.dmit.domain;

public class Loan {

	private double mortgageAmount;
	private double annualInterestRate;
	private int amortizationPeriod;
	
	 /**
	   * Calculates the monthly payment for a Canadian amortized loan using the formula:
	   * 
	   * 									1/6
	   * 						( 1 +  i  )
	   * 							  ---			-  1
	   * 							  200					
	   * monthlyPayment = p * -----------------------------------
	   * 									     1/6	-12 * n	
	   * 						1 - [( 1 +   i  )	 ]
	   * 					  				---		
	   * 					  				200					
	   * where:
	   * 		p = principal outstanding 
	   *  		i = annual interest rate percentage
	   *  		n = number of years
	   * 
	   * @return The monthly payment in dollars rounded to 2 decimal places for a
	   *         loan.
	   */
	  public double monthlyPayment()
	  {
		double mpr = Math.pow(1 + (annualInterestRate / 200.0), 1.0 / 6);
		double monthlyPayment = mortgageAmount 
				* (mpr - 1)
				/ (1 - 1 / Math.pow(1 + (annualInterestRate / 200.0), 2.0 * amortizationPeriod));

		return roundTo2Decimals(monthlyPayment);
	  }

	  /**
	   * Build and return the loan schedule table showing the interest paid,
	   * principal paid, and balance remaining for each payment in the life of a
	   * loan.
	   * 
	   * @return the loan schedule table
	   */
	  public LoanSchedule[] loanScheduleTable()
	  {
		int numberOfPayments = amortizationPeriod * 12;
		LoanSchedule[] loanScheduleArray = new LoanSchedule[numberOfPayments];
		double mpr = Math.pow(1 + (annualInterestRate / 200.0), 1.0 / 6) - 1;
		double interestPaid, principalPaid, remainingBalance = mortgageAmount;
		double monthlyPayment = monthlyPayment();
		for (int paymentNumber = 1; paymentNumber <= numberOfPayments; paymentNumber++)
		{
		  interestPaid = roundTo2Decimals( mpr * remainingBalance );
		  principalPaid = roundTo2Decimals( monthlyPayment - interestPaid );
		  if (remainingBalance <= monthlyPayment )
		  {
			principalPaid = roundTo2Decimals(remainingBalance);
		  }
		  remainingBalance = roundTo2Decimals( remainingBalance - principalPaid );
		  loanScheduleArray[paymentNumber - 1] = new LoanSchedule(paymentNumber, interestPaid, principalPaid, remainingBalance);
		}
		return loanScheduleArray;
	  }

	  /**
	   * Rounds a double value to 2 decimal places
	   * 
	   * @param valueToRound
	   *          the value to round
	   * @return the value rounded to 2 decimal places
	   */
	  public static double roundTo2Decimals(double valueToRound)
	  {
		return Math.round( valueToRound * 100 ) / 100.0;
	  }
	  
	public Loan(double mortgageAmount, double annualInterestRate, int amortizationPeriod) {
		super();
		this.mortgageAmount = mortgageAmount;
		this.annualInterestRate = annualInterestRate;
		this.amortizationPeriod = amortizationPeriod;
	}
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public double getMortgageAmount() {
		return mortgageAmount;
	}
	public void setMortgageAmount(double mortgageAmount) {
		this.mortgageAmount = mortgageAmount;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public int getAmortizationPeriod() {
		return amortizationPeriod;
	}
	public void setAmortizationPeriod(int amortizationPeriod) {
		this.amortizationPeriod = amortizationPeriod;
	}
	
	
}
