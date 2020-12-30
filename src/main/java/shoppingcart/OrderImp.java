package shoppingcart;

import java.util.Vector;

public class OrderImp implements Order {
	private Vector itsItems = new Vector();
	private String itsCustomerId;

	public OrderImp(String cusId) {
		itsCustomerId = cusId;
	}

	@Override
	public String getCustomerId() {
		return itsCustomerId;
	}

	@Override
	public void addItem(Product p, int quantity) {
		Item item = new Item(p, quantity);
		itsItems.add(item);
	}

	@Override
	public int total() {
		try {
			int total = 0;
			for (int i = 0; i < itsItems.size(); i++) {
				Item item = (Item) itsItems.elementAt(i);
				Product p = item.getProduct();
				int qty = item.getQuantity();
				total += p.getPrice() * qty;
			}
			return total;
		} catch (Exception e) {
			throw new Error(e.toString());
		}
	}
}
