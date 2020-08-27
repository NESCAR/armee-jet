package icu.nescar.armee.jet.jet808.support.support;

/**
 * @author hylexus
 * Created At 2020-02-02 4:56 下午
 * add by whale
 * 这个组件主要是用来处理各种处理器和转换器之前的逻辑，
 * 当手动实现、注解实现、内置都实现了相同的处理器时，其使用的优先级就通过这个组件处理
 * 手动实现优先级最高，注解实现第二，内置最低。
 */
public interface OrderedComponent {

    int DEFAULT_ORDER = 0;

    int ANNOTATION_BASED_DEV_COMPONENT_ORDER = 100;

    int BUILTIN_COMPONENT_ORDER = 200;

    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;


    default int getOrder() {
        return DEFAULT_ORDER;
    }

    default boolean shouldBeReplacedBy(icu.nescar.armee.jet.jet808.support.support.OrderedComponent another) {
        // 数字越小优先级越高
        // 数字小的覆盖数字大的
        return this.getOrder() > another.getOrder();
    }
}
