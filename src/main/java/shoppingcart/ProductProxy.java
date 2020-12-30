package shoppingcart;

public class ProductProxy implements Product {
	private String itsSku;

	public ProductProxy(String sku) {
		itsSku = sku;
	}

	@Override
	public int getPrice() throws Exception {
		ProductData pd = DB.getProductData(itsSku);
		return pd.price;
	}

	@Override
	public String getName() throws Exception {
		ProductData pd = DB.getProductData(itsSku);
		return pd.name;
	}

	@Override
	public String getSku() throws Exception {
		ProductData pd = DB.getProductData(itsSku);
		return pd.sku;
	}
}
