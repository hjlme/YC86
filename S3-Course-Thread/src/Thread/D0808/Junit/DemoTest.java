package Thread.D0808.Junit;

public class DemoTest {
	
	@Before()
	public void before() {
		System.out.println("Before");
	}
	
	@After()
	public void after() {
		System.out.println("after");
	}
	
	@Test
	public void test1() {
		System.out.println("测试1");
	}
	
	@Test
	public void test2() {
		System.out.println("测试2");
	}
	
	public void test3() {
		System.out.println("测试3");
	}
}
