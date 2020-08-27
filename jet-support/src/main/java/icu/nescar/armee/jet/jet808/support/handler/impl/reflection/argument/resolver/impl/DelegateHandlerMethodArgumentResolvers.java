package icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl;



import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.exception.ArgumentResolveException;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.MethodParameter;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author hylexus
 * Created At 2020-02-02 1:21 下午
 * 受委托的处理器参数解析器
 * 所有支持的参数解析器都委托给这个解析器统一对参数进行解析
 */
@BuiltinComponent
public class DelegateHandlerMethodArgumentResolvers implements HandlerMethodArgumentResolver {

    private final List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
    private final ConcurrentMap<MethodParameter, HandlerMethodArgumentResolver> argumentResolverCache = new ConcurrentHashMap<>();

    static void addDefaultHandlerMethodArgumentResolver(DelegateHandlerMethodArgumentResolvers resolvers) {
        resolvers.addResolver(new RequestMsgBodyArgumentResolver());
        resolvers.addResolver(new RequestMsgHeaderArgumentResolver());
        resolvers.addResolver(new RequestMsgMetadataArgumentResolver());
        resolvers.addResolver(new SessionArgumentResolver());
        resolvers.addResolver(new ExceptionArgumentResolver());
    }

    public DelegateHandlerMethodArgumentResolvers() {
        addDefaultHandlerMethodArgumentResolver(this);
    }

    //判断是否支持这个参数 不支持的话是不会进行下一步处理的
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        final HandlerMethodArgumentResolver resolver = getResolver(methodParameter);
        return resolver != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ArgumentContext context) {
        final HandlerMethodArgumentResolver resolver = this.getResolver(parameter);
        if (resolver != null) {
            return resolver.resolveArgument(parameter, context);
        }
        throw new ArgumentResolveException(context);
    }

    //根据参数从数据结构中取出对应的参数解析器
    private HandlerMethodArgumentResolver getResolver(MethodParameter methodParameter) {
        //首先从根据key在hashmap里面找对应的解析器
        final HandlerMethodArgumentResolver resolver = this.argumentResolverCache.get(methodParameter);
        if (resolver != null) {
            return resolver;
        }
        //遍历解析器 并将支持的参数和解析器一一对应放入hashmap中
        for (HandlerMethodArgumentResolver argumentResolver : this.resolvers) {
            if (argumentResolver.supportsParameter(methodParameter)) {
                this.argumentResolverCache.put(methodParameter, argumentResolver);
                return argumentResolver;
            }
        }

        return null;
    }

    public DelegateHandlerMethodArgumentResolvers addResolver(HandlerMethodArgumentResolver resolver) {
        this.resolvers.add(resolver);
        return this;
    }
}
