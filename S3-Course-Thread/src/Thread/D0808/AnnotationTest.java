package Thread.D0808;


public class AnnotationTest {
	//属性定义是一个数组类型，那么如果使用注解时只赋值一个元素，可以使用单变量
	@Test
	@Select("select * from book")
	public void test() {
		@Test
		int a;
	}
	
	@Select(value="select * from a",age = 1)
	public void test1() {
		@Test
		int a;
	}
	//属性名为value是默认属性，如果只写该属性，其他属性不写。可以省略value关键字
	@Select(value="select * from a")
	public void test2() {
		@Test
		int a;
	}

}
