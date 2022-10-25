package processor;

import java.text.NumberFormat;
import java.util.*;
public class Processor implements Runnable {
	private TreeMap<Items, Integer> itemsCount;
	private Income totalIncome;
	private Orders order;
	private String orderDetails;
	private StringBuilder allOrderDetails;
	private ArrayList<StringBuilder> output;
	public Processor(Orders order, TreeMap<Items, Integer> itemsCount, Income totalIncome, ArrayList<StringBuilder> output) {
		this.itemsCount = itemsCount;
		this.totalIncome = totalIncome;
		this.order = order;
		allOrderDetails = new StringBuilder();
		this.output = output;
	}
	@Override
	public void run() {
		System.out.println("Reading order for client with id: " + order.getId());
		orderDetails = "----- Order details for client with Id: " + order.getId() + " -----" + "\n";
		double totalOrder = 0;
		for (Map.Entry<String, ArrayList<Items>> entry : order.getItems().entrySet()) {
			Items item = entry.getValue().get(0);
			int freq = findFreq(entry.getKey(), order);
			double cost = item.getCost();
			totalOrder += (cost*freq);
			String desc = entry.getKey();
			orderDetails += "Item's name: " + desc + ", " + "Cost per item: " + 
			NumberFormat.getCurrencyInstance().format(cost) + ", " + "Quantity: " + freq + ", " + "Cost: " + NumberFormat.getCurrencyInstance().format(cost*freq) + "\n";
			synchronized (itemsCount) {
				Integer number = itemsCount.get(item);
				if (number == null) {
					itemsCount.put(item, freq);
				} else {
					itemsCount.put(item, number + freq);
				}
			}
		}
		orderDetails += "Order Total: " + NumberFormat.getCurrencyInstance().format(totalOrder) + "\n";
		synchronized(allOrderDetails) {
			allOrderDetails.append(orderDetails);
		}
		synchronized(totalIncome) {
			output.add(allOrderDetails);
		}
		synchronized(totalIncome) {
			totalIncome.add(totalOrder);
		}
	}
	private int findFreq(String item, Orders order) {
		for (Map.Entry<String, ArrayList<Items>> entry : order.getItems().entrySet()) {
			if (entry.getKey().equals(item)) {
				return entry.getValue().size();
			} 
		}
		return 0;
	}
}
