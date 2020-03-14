## 学习Spring-Security笔记

SpringBoot默认BasicErrorController处理错误的请求


一.自定义处理异常

1.定义自己的Exception
2.使用ControllerAdvice  @ExceptionHandler捕捉异常 @@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)自定义HTTP状态码 


二.  Restful  api的拦截

1.过滤器（filter）
    第一步新建一个Filter类，实现Filter接口
    把第三方框架的bean，注入进spring，可以通过Config的方式
    
2.拦截器（interceptor）
   第一步新建一个Interceptor类，实现HandlerInterceptor接口
   第二步，写一个配置类，继承WebMvcConfigurerAdapter，重写addInterceptors方法，把自定义的Interceptor加进去
   
3.切片（Aspect）

    切入点（注解）：1.在那些方法起作用  2.在什么时候起作用
    
    增加（方法）：2.起作用时执行的业务逻辑
    
    
三、异步处理Rest服务

1.使用Runable 和 Callable 异步处理Rest服务
  区别：Runable 没有返回值，Callable有返回值。
  
2. 使用DeferredResult异步处理Rest服务服务
  
    