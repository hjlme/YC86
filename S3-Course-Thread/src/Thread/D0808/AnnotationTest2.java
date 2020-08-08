package Thread.D0808;

import java.lang.reflect.Method;

@Test("测试类")
public class AnnotationTest2 {
	
	@Test("测试方法")
	public void test() {
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		
		AnnotationTest2 a = new AnnotationTest2();
		//获取类的三种方法
		Class<?> cls = AnnotationTest2.class;
		cls = a.getClass();
		cls = Class.forName("Thread.D0808.AnnotationTest2");
		//反射操作
		cls.getFields();
		cls.getMethods();
		cls.getConstructors();
		cls.getAnnotations();
		
		Test test1 = cls.getAnnotation(Test.class);
		System.out.println(test1.value());
		
		Method m = cls.getMethod("test");
		
		Test test2 = m.getAnnotation(Test.class);
		System.out.println(test2.value());
		
	}
}


