package shoppingcart;

import junit.framework.TestCase;

public class TestProxy extends TestCase {
	public TestProxy(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		DB.init();
		DB.clear();
		DB.store(new ProductData("TestName1", 456, "ProxyTest1"));
	}

	public void tearDown() throws Exception {
		DB.close();
	}

	public void testProductProxy() throws Exception {
		Product p = new ProductProxy("ProxyTest1");
		assertEquals(456, p.getPrice());
		assertEquals("TestName1", p.getName());
		assertEquals("ProxyTest1", p.getSku());
	}

	public void testOrderProxyCustomerId() throws Exception {
		OrderData od = DB.newOrder("testOrderProxyCustomerId");
		Order op = new OrderProxy(od.orderId);
		assertEquals(od.customerId, op.getCustomerId());
	}

	public void testOrderProxyTotal() throws Exception {
		DB.store(new ProductData("Wheaties", 349, "wheaties"));
		DB.store(new ProductData("Crest", 258, "crest"));
		ProductProxy wheaties = new ProductProxy("wheaties");
		ProductProxy crest = new ProductProxy("crest");
		OrderData od = DB.newOrder("testOrderProxy");
		OrderProxy order = new OrderProxy(od.orderId);
		order.addItem(crest, 1);
		order.addItem(wheaties, 2);
		assertEquals(956, order.total());
	}
}
