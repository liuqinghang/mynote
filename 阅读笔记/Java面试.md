#### 什么是面向对象

相比于面向过程关注具体的步骤过程,面向对象更加关注有哪些参与者(对象)的性质和以及行为

面向对象的三大性质 封装,继承和多态 (抽象)

封装:内部细节对外部调用是透明的,外部调用不关注内部具体如何实现

​	orm框架:操作数据库的框架,例如mybatis ,不需要考虑具体连接数据库和执行sql语句的细节,只需要关心sql语句的编写和调用

继承:子类继承基类的方法,然后对基类进行修改和拓展

多态:基于继承,对方法进行重写,然后外部调用相同方法时实际执行的逻辑不同

#### ==和equals

对于基本数据类型,==比较和equals无区别

对于引用数据类型,比如自定义的对象和String ,==仅比较对应的引用值是否相等,而equals方法会被重写,其中会先比较引用地址是否相等,如果相等,返回true,不相等就接着比较实际内容是否相等



#### final

修饰类,方法和变量

#### 重载和重写

重载:一般发生在同一个类中

重写:发生在父类和子类中

#### 接口和抽象类

- 抽象类中可以存在普通成员函数,接口中只能有抽象方法函数
- 成员变量的类型,抽象类中无限制,接口中只能是public static final 
- 抽象类只能继承一个,但可以实现多个接口

抽象类是为了代码复用,接口是为了对对象行为进行限制,规范

抽象类对一类事物本质的抽象,接口是对行为的抽象

#### List和Set

List有序,可重复,可以有多个null数据,支持迭代器访问数据

Set无序,不可重复,最多有一个null,只能用迭代器访问

#### HashCode和equals

hashCode 散列码,获取对象在哈希表中的位置,在哈希表中通过键值对的方式快速检索对象

hashSet检查重复,先计算对象的哈希值判断位置,如果对应位置无对象就添加,否则就调用equals方法检查对象是否相同,相同则加入失败,不同就重新散列

#### Arraylist和LinkedList

ArrayList :基于动态数组,在内存中连续存储,支持下标访问,适合查找 扩容机制 增加为原来的1.5倍

LinkedList :基于链表,可以存储在分散的内存中,查询性能较低

#### 字节码

.class文件就是字节码文件,在Java文件编译之后就会产生对应的字节码文件,字节码文件能够在jvm中解释执行翻译成机器码运行,这样的话,不针对特定的机器,只要安装有java虚拟环境的都能运行,达到编译一次就可以在多个环境下运行

#### GC 判断对象能否被回收

引用计数法:对象有一个引用计数属性,当对象的引用增加时+1,计数为0 时进行回收

可达性分析法:对象与GC Roots没有引用相连时判断是可回收对象

#### sleep(),wait(),join()和yield()

- sleep()是Thread类的静态方法,wait是Object类的本地方法
- sleep()不会释放锁,wait会释放锁(notify 和notifyAll唤醒)
- sleep一般用于当前线程休眠来让出时间片,wait用于多线程通信

yield 执行后线程进入就绪状态,释放cpu使用权,可以竞争cpu

join执行后线程进入堵塞状态

#### 线程安全

内存安全,放在堆中可以被所有线程访问的对象是安全的,隐患

栈每个线程独有的,保存线程的状态和局部变量 是线程安全的

#### 实现线程的方式

继承实现Thread类(有线程相关复杂操作的),或者实现Runnable 接口(简单执行线程任务) 以及 Callable接口(有返回值)和Future方法

#### 有关锁



#### Spring是什么

轻量级开源框架,也是一个容器,用来装javaBean,可以对java对象进行管理,对对象进行配置和生命周期, 作为一个中间层,可以来连接Struct和hibernate 或者mybatis

#### IOC 和AOP

控制反转:为了松耦合,获取或者使用对象时,由IOC容器注入对象,而不用主动创建或者将目标对象以传参的方式传入当前对象

面向切面:分离业务逻辑和系统服务 ,(常见注解,@Before @After @Around)

#### BeanFactory 和ApplicationContext

都是spring中的Bean容器 

applictionContext是BeanFactory的子接口

支持加载多个配置文件

BeanFactory 延迟加载注入Bean,使用到Bean时才会对Bean进行加载实例化

ApplicationContext会直接对Bean进行实例初始化

#### Spring Boot 和Spring MVC和Spring 

spring mvc 是spring关于web框架的一个架构,提供了控制器Servlet,定义一系列映射规则,以及执行的handler ,将handler的结果通过视图解析生成视图返回给前端

spring boot :spring的一个快速开发的工具包,简化开发流程,使用了大量的默认配置,

#### Spring MVC 的工作流程

1. 用户发生请求到前端的DispatcherServlet
2. DispatcherServlet调用HandlerMapping处理器
3. 处理器查找在配置文件生成处理器和处理拦截器给Servlet
4. Servlet调用handlerAdapter 
5. handlerAdapter调用后端对应的具体的controller
6. controller 执行返回ModelAndView
7. handlerAdapter 将返回结果发送给视图解析器viewReslover
8. 解析器返回具体的View
9. Servlet根据view进行渲染
10. Servlet 响应用户



#### java中流

按流向：输入流，输出流

按数据单位：

　　字节流（一次读入或读出是8位二进制，即一个字节）

​		字符流（一次读入或读出是16位二进制，即一个字符）

#### Java意外的类型

错误和异常,错误是程序本身无法解决的错误,包括编译时的文件错误以及系统错误 比如栈溢出内存泄漏等

异常 

​		**Checked Exception** 可预测,需要在代码中手动处理

​		**Unchecked Exception**  运行时发生,无法预先处理

