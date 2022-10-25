package processor;

import java.util.*;

public class Orders {
	private int id;
	private Map<String, ArrayList<Items>> cart;
	//private ArrayList<Items> items;

	public Orders(int id) {
		this.id = id;
		cart = new TreeMap<String, ArrayList<Items>>();
	}

	public int getId() {
		return id;
	}

	public void addItem(String description, double cost) {
		ArrayList<Items> items = new ArrayList<Items>();
		items.add(new Items(description, cost));
		if (!cart.containsKey(description)) {
			cart.put(description, items);
		} else {
			cart.get(description).add(new Items(description, cost));
		}
	}
	public void addItem(String key, Items item) {
		ArrayList<Items> items = new ArrayList<Items>();
		items.add(item);
		if (!cart.containsKey(key)) {
			cart.put(key, items);
		} else {
			cart.get(key).add(item);
		}
	}
	public Map<String, ArrayList<Items>> getItems() {
		return cart;
	}
	/*public Iterator<Items> iterator() {
		return cart.iterator();
	}*/
	
	public String toString() {
		if (cart.size() == 0) {
			return "Empty cart";
		} else {
			return cart.toString();
		}
	}
}
