# 线程

### 状态

- 新建状态(New): 

	线程对象被创建后，就进入了新建状态。例如，Thread thread = new Thread()。
- 就绪状态(Runnable): 
	也被称为“可执行状态”。线程对象被创建后，其它线程调用了该对象的start()方法，从而来启动该线程。例如，thread.start()。处于就绪状态的线程，随时可能被CPU调度执行。
- 运行状态(Running) : 
	线程获取CPU权限进行执行。需要注意的是，线程只能从就绪状态进入到运行状态。
- 阻塞状态(Blocked)  : 
	阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：
	
    (01) 等待阻塞 -- 通过调用线程的wait()方法，让线程等待某工作的完成。
	
    (02) 同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态。
	
    (03) 其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。
	
- 5. 死亡状态(Dead)    : 线程执行完了或者因异常退出了run()方法，该线程结束生命周期。

### 实现多线程的两种方法 Tread and Runnable

- Runnable 是一个接口，该接口中只包含了一个run()方法。它的定义如下：
	```java
				public interface Runnable {
					public abstract void run();
				}
	```
- Thread 是一个类。Thread本身就实现了Runnable接口。它的声明如下：
	```
	public class Thread implements Runnable {}
	```
不同：
Thread 是类，而Runnable是接口；Thread本身是实现了Runnable接口的类。我们知道“一个类只能有一个父类，但是却能实现多个接口”，因此Runnable具有更好的扩展性。
此外，Runnable还可以用于“资源的共享”。即，多个线程都是基于某一个Runnable对象建立的，它们会共享Runnable对象上的资源。
通常，建议通过“Runnable”实现多线程！

接下来用一个例子看出上述两种方式的区别

```java
class MyThread extends Thread{//继承Tread
    private int ticket=10;
    public void run(){
        for(int i=0;i<20;i++){
            if(this.ticket>0){
                System.out.println(this.getName()+" 卖票：ticket"+this.ticket--);
            }
        }
    }
};

class MyThread implements Runnable{//继承Runnable
    private int ticket=10;
    public void run(){
        for(int i=0;i<20;i++){
            if(this.ticket>0){
                System.out.println(Thread.currentThread().getName()+" 卖票：ticket"+this.ticket--);
            }
        }
    }
};

public static void main(String[] args) {  

	//在调用上述方法时的方式不一样
	MyThread t1=new MyThread();//Tread
	MyThread t2=new MyThread();
}
//执行结果就是每个Tread都会卖出10张票

public static void main(String[] args) {  
	MyThread mt=new MyThread();//共用一个Runnable对象
	
	Thread t1=new Thread(mt);//用Tread使用接口
    Thread t2=new Thread(mt);
}
//执行结果就是所有Tread一起卖出10张票
```

### 2.2 Callable\Future和FutureTask

1. 使用*Runnable*方法和*Thread*方法时是没有返回值，JDK提供了以上Callable接口和Future方法

#### 2.2.1 Future接口方法：

```
public abstract interface Future<V> {
public abstract boolean cancel( boolean paramBoolean) ;
public abstract boolean isCancelled() ;
public abstract boolean isDone() ;
public abstract V get() throws InterruptedException, ExecutionException;
public abstract V get(long paramLong, TimeUnit paramTimeUnit)
throws InterruptedException, ExecutionException, TimeoutException;
}

```

#### 2.2.3 FutureTask类

实现 `RunnableFuture` 接口，`RunnableFuture`

```

```

