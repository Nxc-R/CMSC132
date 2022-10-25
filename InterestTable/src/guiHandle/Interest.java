package guiHandle;
import java.text.NumberFormat;
public class Interest {
	public static double computeSimpleInterest(double principal, double rate, int years) {
		return principal + (principal * (rate/100) * years);
	}
	
	public static double computeCompoundInterest(double principal, double rate, int years) {
		return principal * Math.pow((1 + rate/100), years);
	}
	
	public static String simpleTable(double principal, double rate, int years) {
		String formatCurr  = NumberFormat.getCurrencyInstance().format(principal);
		String simpleTable = "Principal: " + formatCurr + ", Rate: " + rate 
				+ "\n" + "Year, Simple Interest Amount";
		
		for(int i = 1; i <= years; i++) {
			simpleTable += "\n" + i + "-->" + 
			NumberFormat.getCurrencyInstance().format(computeSimpleInterest(principal, rate, i));
		}
		
		return simpleTable;
	}
	
	public static String compoundTable(double principal, double rate, int years) {
		String formatCurr  = NumberFormat.getCurrencyInstance().format(principal);
		String comTable = "Principal: " + formatCurr + ", Rate: " + rate 
				+ "\n" + "Year, Compound Interest Amount";
		
		for(int i = 1; i <= years; i++) {
			comTable += "\n" + i + "-->" +
			NumberFormat.getCurrencyInstance().format(computeCompoundInterest(principal, rate, i));
		}
		return comTable;
	}
	
	public static String bothTable(double principal, double rate, int years) {
		String formatCurr  = NumberFormat.getCurrencyInstance().format(principal);
		String bothTable = "Principal: " + formatCurr + ", Rate: " + rate 
				+ "\n" + "Year, Simple Interest Amount, Compound Interest Amount";
		
		for(int i = 1; i <= years; i++) {
			bothTable += "\n" + i + "-->" + 
			NumberFormat.getCurrencyInstance().format(computeSimpleInterest(principal, rate, i))
			+ "-->" + 
			NumberFormat.getCurrencyInstance().format(computeCompoundInterest(principal, rate, i));
		}
		return bothTable;
	}
}
