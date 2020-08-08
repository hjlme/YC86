package Thread.D0808.Junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class main {
	
	public static void main(String[] args) {
		
		DemoTest dt = new DemoTest();
		
		Class<?> cls = dt.getClass();
		
		Method before = getMethodByAnnotation(cls,Before.class);
		Method after = getMethodByAnnotation(cls,After.class);
		
		for(Method m: cls.getMethods()) {
			if(m.getAnnotation(Test.class) != null) {
				try {
					if(before != null) {
						before.invoke(dt);
					}
					m.invoke(dt);
					if(after != null) {
						after.invoke(dt);
					}
				} catch (IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					//dt的方法出现业务异常也封装在此异常
				}
			}
		}
	}
	/**
	 * 
	 * @param cls1 	测试类
	 * @param cls2    反射类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getMethodByAnnotation(Class<?> cls1,@SuppressWarnings("rawtypes") Class cls2) {
		
		for(Method m: cls1.getMethods()) {
			if(m.getAnnotation(cls2) !=null) {
				return m;
			}
		}
		return null;
	}
}
