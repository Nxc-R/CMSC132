package processor;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;

public class OrdersProcessor {
	boolean multiThread = false;
	public static ArrayList<Orders> readOrder(StringBuilder multiThread, StringBuilder resultFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Orders> orders = new ArrayList<Orders>();
		ArrayList<Items> items = new ArrayList<Items>();
		System.out.print("Enter item's data file name: ");
		String itemFile = scanner.next();
		Scanner itemScan = new Scanner(new File(itemFile));
		while (itemScan.hasNext()) {
			items.add(new Items(itemScan.next(), itemScan.nextDouble()));
		}
		System.out.print("Enter 'y' for multiple threads, any other character otherwise: ");
		if (scanner.next().equals("y")) {
			multiThread.append("true");
		}
		System.out.print("Enter number of orders to procress: ");
		int numOfOrders = scanner.nextInt();
		System.out.print("Enter order's base filename: ");
		String base = scanner.next();
		System.out.print("Enter result's filename: ");
		resultFile.append(scanner.next());
		for (int i = 1;i <= numOfOrders;i++) {
			Scanner orderScan = new Scanner(new File(base+i + ".txt"));
			orderScan.next();
			int clientId = orderScan.nextInt();
			Orders order = new Orders(clientId);
			orderScan.nextLine();
			while(orderScan.hasNext()) {
				String item = orderScan.next(); 
				if (checkItem(item, items) != null) {
					order.addItem(item, checkItem(item, items));
				}
			}
			orders.add(order);
		}
		scanner.close();
		itemScan.close();
		return orders;
	}
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		StringBuilder multiThread = new StringBuilder(), resultFile = new StringBuilder();
		ArrayList<Orders> orders = readOrder(multiThread, resultFile);
		long startTime = System.currentTimeMillis();
		Income totalIncome = new Income(0);
		TreeMap<Items, Integer> itemsCount = new TreeMap<>();
		if (multiThread.toString().equals("true")) {
			String summary = "***** Summary of all orders *****\n";
			ArrayList<Thread> allThreads = new ArrayList<Thread>();
			ArrayList<StringBuilder> output = new ArrayList<StringBuilder>();
			for (Orders order : orders) {
				allThreads.add(new Thread(new Processor(order, itemsCount, totalIncome, output))); 
			}
			
			for (Thread thread : allThreads) {
				thread.start();
			}
			
			for (Thread thread : allThreads) {
				thread.join();
			}
			for (Map.Entry<Items, Integer> entry : itemsCount.entrySet()) {
				double cost = entry.getKey().getCost();
				summary += "Summary - Item's name:"
						+ " " + entry.getKey().getDescription() + ", " + "Cost per item: " + 
				NumberFormat.getCurrencyInstance().format(cost) + ", " + "Number sold: " + entry.getValue() + ", " + "Item's Total: " + 
						NumberFormat.getCurrencyInstance().format(cost*entry.getValue()) + "\n";
			}
			summary += "Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(totalIncome.getValue());
			Collections.sort(output);
			try {
				FileWriter results = new FileWriter(resultFile.toString());
				for (int i = 0; i < output.size();i++) {
					results.write(output.get(i).toString());
				}
				results.write(summary + "\n");
				results.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Processing time (msec): " + (endTime - startTime));
			System.out.println("Results can be found in the file: " + resultFile);
		} else {
			process(orders, itemsCount, totalIncome, resultFile.toString());
		}
	}
	private static void process(ArrayList<Orders> orders, TreeMap<Items, Integer> itemsCount, Income totalIncome, String resultFile) {
		long startTime = System.currentTimeMillis();
		String orderDetails = new String(), summary = "***** Summary of all orders *****\n";
		for (Orders order : orders) {
			double totalOrder = 0;
			System.out.println("Reading order for client with id: " + order.getId());
			orderDetails += "----- Order details for client with Id: " + order.getId() + " -----" + "\n";
			for (Map.Entry<String, ArrayList<Items>> entry : order.getItems().entrySet()) {
				Items item = entry.getValue().get(0);
				int freq = findFreq(entry.getKey(), order);
				double cost = item.getCost();
				totalOrder += (cost*freq);
				String desc = entry.getKey();
				Integer number = itemsCount.get(item);
				if (number == null) {
					itemsCount.put(item, freq);
				} else {
					itemsCount.put(item, number + freq);
				}
				orderDetails += "Item's name: " + desc + ", " + "Cost per item: " + 
				NumberFormat.getCurrencyInstance().format(cost) + ", " + "Quantity: " + freq + ", " + "Cost: " + NumberFormat.getCurrencyInstance().format(cost*freq) + "\n";
			}
			orderDetails += "Order Total: " + NumberFormat.getCurrencyInstance().format(totalOrder) + "\n";
			totalIncome.add(totalOrder);
		}
		for (Map.Entry<Items, Integer> entry : itemsCount.entrySet()) {
			double cost = entry.getKey().getCost();
			summary += "Summary - Item's name: " + entry.getKey().getDescription() + ", " + "Cost per item: " + 
			NumberFormat.getCurrencyInstance().format(cost) + ", " + "Number sold: " + entry.getValue() + ", " + "Item's Total: " + 
					NumberFormat.getCurrencyInstance().format(cost*entry.getValue()) + "\n";
		}
		summary += "Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(totalIncome.getValue());
		try {
			FileWriter results = new FileWriter(resultFile);
			results.write(orderDetails);
			results.write(summary + "\n");
			results.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Processing time (msec): " + (endTime - startTime));
		System.out.println("Results can be found in the file: " + resultFile);
	}
	private static int findFreq(String item, Orders order) {
		for (Map.Entry<String, ArrayList<Items>> entry : order.getItems().entrySet()) {
			if (entry.getKey().equals(item)) {
				return entry.getValue().size();
			} 
		}
		return 0;
	}
	private static Items checkItem(String item, ArrayList<Items> items) {
		for (int i = 0;i < items.size();i++) {
			if (item.equals(items.get(i).getDescription())) {
				return items.get(i);
			}
		}
		return null;
	}
}