package icu.nescar.armee.jet.annotation;

import java.lang.annotation.*;

/**
 * @author hylexus
 * Created At 2019-08-28 12:06 上午
 */
@Target(ElementType.TYPE) //说明注解的使用范围是 type类、接口等
@Retention(RetentionPolicy.RUNTIME)//定义了注解被保留的时间长短 这说明是在运行期间；source就是在源文件保留；class是在class文件中有效
@Documented//可用来被javadoc此类工具文档化
public @interface DebugOnly {
}
