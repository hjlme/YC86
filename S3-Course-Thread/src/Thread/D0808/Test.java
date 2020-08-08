package Thread.D0808;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= {ElementType.METHOD,ElementType.LOCAL_VARIABLE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	
	String value() default "";
}
/**
 * 属性定义是一个数组类型，那么如果使用注解时只赋值一个元素，可以使用单变量
 * 所有的注解属性都必须赋值，如果不赋值，就要设置默认值
 * 属性名为value是默认属性，如果只写该属性，其他属性不写。可以省略value关键字
 */
@Target(value= {ElementType.METHOD,ElementType.LOCAL_VARIABLE})
@interface Select {
	
	String[] value();
	
	int age() default 100;
}
