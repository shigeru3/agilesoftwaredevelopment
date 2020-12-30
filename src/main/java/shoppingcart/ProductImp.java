package shoppingcart;

public class ProductImp implements Product {
	private int itsPrice;
	private String itsName;
	private String itsSku;

	public ProductImp(String sku, String name, int price) {
		itsPrice = price;
		itsName = name;
		itsSku = sku;
	}

	@Override
	public int getPrice() throws Exception {
		return itsPrice;
	}

	@Override
	public String getName() throws Exception {
		return itsName;
	}

	@Override
	public String getSku() throws Exception {
		return itsSku;
	}
}
