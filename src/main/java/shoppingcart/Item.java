package shoppingcart;

public class Item {
	private Product itsProduct;
	private int itsQuantity;

	public Item(Product p, int quantity) {
		itsProduct = p;
		itsQuantity = quantity;
	}

	public Product getProduct() {
		return itsProduct;
	}

	public int getQuantity() {
		return itsQuantity;
	}
}
