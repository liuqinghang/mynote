
## 导论：

##### 抽象
对事物进行建模 ，将具体的事务抽象出其状态与行为（成员变量及其方法）
##### 接口
对于每一对象能只满足其某些请求，只对类型感兴趣
##### 复用
##### 继承



基类，继承，重写（不可访问基类的私有变量）
类与基类具有相同的类型，可使用父类的接口
可对父类的方法进行覆盖（改变基类的方法）

##### 后期绑定
使用对象时，所使用的具体方法（所调用的代码）直到运行时才能确定
##### 上转型对象
方法的参数为某基类，其子类可调用该方法

### 第二章
所有对象存储在堆中



#### 基本类型
|基本类型|大小|包装器类型||
|---|---|---|---|
|boolean|-|Boolean||
|char|16bit|Character||
|byte|8bit|Byte||
|short|16bit|Short||
|int|32bit|Integer||
|long|64bit|Long||
|float|32bit|Float||
注：boolean的默认值为false

基本类型所具有的包装类，可以在堆中创建一个非基本对象，用于表示对应的基本类
如：
    
    char c = 'x';
    Character ch = new Character(c);
    or
    Charcter ch = new Character('c')

对基础类型的赋值是直接将内容复制到目标处，用对象对具体对象的赋值是将该对象的引用给了对方
P118

#### 5.7 构造器初始化
1，变量定义的先后顺序决定了初始化顺序，且始终在所有方法前初始化
3，显示静态的初始化：
    
```java
static int i;
static{//--仅被执行一次，初始化时
    i = 47;
}
```
```java
class Cups{
    static Cup cup1;
    static Cuo cup2;
    static{//--（a）
        cup1 = new Cup(1);//Cup - 另一个类，会打印出传入的参数
        cup2 = new Cup(2);
    }
    Cups(){
        Print("Cups()")
    }
}

/*
    静态方法/代码（a）仅被执行一次：
    （1）在首次生成该类的一个对象时、
    （2）首次访问该类的静态成员数据
*/
```
#### 5.8 数组的初始化
1，
int a1[]; / int[] a1;
带值：int[] a1 = {1,2,3,4};
new值有效：即 a1 = new int[];//有效
2，在编写程序的过程中，如果不能确定数组中有多少个元素，直接用new在数组里创建元素
```java 
    Random rand = new Random(47);//种子
    int[] a;
    a = new int[rand.nextInt(20)];
    //此时a数据就被分配了一个随机数大小的空间
    //在new完数组后得到的是一个空指针，需要进行初始化，否则运行异常
```
3，花括号括选的列表初始化m对象：

```java
    Integer[] a = {
        new Integer(1),
        new Integer(2),
        3,//逗号可选，维护长列表
    }
//初始化数组
    new String[]{"fidder","apple","huawei"}//直接将该式赋值给某个引用

```
#### 5.9 枚举类型
即enum关键字，参考算法.java书代码
可直接使用`.toString()`方法
编辑器会自动创建一个`ordinal()`方法，表示某个特定的enum常量的申明顺序
enum可用于switch语句，直接进行选择

### 第六章 访问权限控制
##### 6.2 protected：

同一包内的可使用，不同包内只能访问public类、
被继承时可访问

### 第七章 复用
继承与final
### 第八章 多态
在面向对象程序语言中，多态是继抽象、继承之后的第三种基本特征
##### 方法调用绑定：前期绑定（C） 和 后期绑定
即部分方法可重写可不重写
私有方法在被继承时不会被覆盖
方法如果时静态的，则并不具有多态性
用组合和继承的方法创建新类的时候，如果需要有清理工作，就必须在导出类中继承dispose()方法，而后要调用基类版本的dispose方法，否则，基类的清理工作就不会发生
销毁是按声明的逆序进行的
初始化时会偏向于调用被覆盖后继承方法

### 第九章 抽象方法与接口
implements  与  extend 的区别于联系
继承抽象类需要重写所有的抽象方法
接口中的常量默认是static 和 final 的
接口中方法默认是public
而普通继承不需要全部重写
### 第十章 内部类
#### 	可以将一个类的定义放在另一个类的定义内部，称为内部类
#### 10.1 内部类
内部类拥有其外部类所有元素的访问权
想创建内部类对象，不能直接引用外部类的名字创，即

    dn.new DotNew.Inner();//That Wrong！！
    DotNew dn = new DotNew();
    DotNew.Inner dni = dn.new Inner();
#### 10.6 匿名内部类
```java
abstract class Base {
  public Base(int i) {
    print("Base constructor, i = " + i);
  }
  public abstract void f();
}   

public class AnonymousConstructor {
  public static Base getBase(int i) {
    return new Base(i) {
      { print("Inside instance initializer"); }
      public void f() {
        print("In anonymous f()");
      }
    };
  }
  public static void main(String[] args) {
    Base base = getBase(47);
    base.f();
  }
} 
```
#### 10.7 嵌套类
------

- 内部类加`static`方法，
- 创建时不需要外部对象
- 不能从嵌套类的对象中访问非静态的外围类对象
- 普通类中不能用`static`

------

### 第十一章 持有对象

------



- Java类实用库提供一套相对完整的容器类，基本对象是 `List` 、 `Set` 、 `Queue`  和 `Map`。这些对象也称为集合类

#### 11.1  泛型和类型安全的容器

最基本最可靠的容器 -- `ArrayList` 可以自动扩充自身尺寸的数组 

```java
class Apple{
    ///~
}

ArrayList app = new ArrayList();
//注：以上添加新成员时如果是其它Object不会有问题，只是在获取时得到的是Object引用，需要转型
ArrayList<Apple> apps = new ArrayList<Apple>();//已指定类型，不可添加其它Object

app.add(new Apple());//添加成员，插入对象
///~

for(int i = 0; i < app.size(); i++){// .size() 方法获取大小 
	app.get(i)						// .get() 方法获取每个成员
}
for(Apple a : apps)
    print(a.id);//此print方法已引用包
```

当指定某个类型为泛型参数时，也可将其上转型对象也可放置进该容器

#### 11.2  基本概念 

- **Java**容器类类库的作用是保存对象，将其划分为两个概念
  - Collection。一个独立元素的序列。`List`必须按照插入的顺序保存元素，`Set`不能有重复元素，`Queue`按照排队规则确定顺序
  - Map。一组成对的“键值对”对象。`ArrayList`允许使用数字来查找值，将对象与数字关联起来；将某些对象与其他对象关联起来称为**字典**

#### 11.3  添加一组元素

在`java.util`包中的Arrays和Collections类中都有很多实用方法

- `Arrays.asList()` 方法接收一个数组或者是一个由逗号分隔的元素列表（使用可变参数），并将其转换为一个List对象
- `Collections.addAll()`方法接收一个Collections对象，以及一个数组或者是一个由逗号分隔的列表，将元素添加到Collection中
- 具体实例参考以下

```java
Collection<Integer> collection = 
    new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
Integer[] moreInts = { 6, 7, 8, 9, 10 };

collection.addAll(Arrays.asList(moreInts));
Collections.addAll(collection, 11, 12, 13, 14, 15);//两个参数
Collections.addAll(collection, moreInts);
// Produces a list "backed by" an array:
List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
list.set(1, 99); // OK -- modify an element
// list.add(21); // Runtime error because the
                     // underlying array cannot be resized.
```

- `Collection.addAll()`只能接受另一个Collection对象作为参数

- 可直接用`Array.asList()`的输出，将其当作List，其底层是数组，因此不能调整尺寸，即不能使用add() 和delete()方法

  ```java
  class Snow {}
  class Powder extends Snow {}
  class Light extends Powder {}
  class Heavy extends Powder {}
  class Crusty extends Snow {}
  class Slush extends Snow {}
  
  List<Snow> snow1 = Arrays.asList(
        new Crusty(), new Slush(), new Powder());
  
  // Won't compile:
  // List<Snow> snow2 = Arrays.asList(
  //   new Light(), new Heavy());//其中只有Powder类型，不符合Snow
  
  List<Snow> snow3 = new ArrayList<Snow>();
  Collections.addAll(snow3, new Light(), new Heavy());
  
  ```

  #### 11.4  容器的打印

  ```java
  public class PrintingContainers {
    static Collection fill(Collection<String> collection) {
      collection.add("rat");
      collection.add("cat");
      collection.add("dog");
      collection.add("dog");
      return collection;
    }
    static Map fill(Map<String,String> map) {
      map.put("rat", "Fuzzy");
      map.put("cat", "Rags");
      map.put("dog", "Bosco");
      map.put("dog", "Spot");
      return map;
    }	
    public static void main(String[] args) {
      print(fill(new ArrayList<String>()));
      print(fill(new LinkedList<String>()));
      print(fill(new HashSet<String>()));
      print((new TreeSet<String>()));
      print(fill(new LinkedHashSet<String>()));
      print(fill(new HashMap<String,String>()));
      print(fill(new TreeMap<String,String>()));
      print(fill(new LinkedHashMap<String,String>()));
    }
  } /* Output:
  [rat, cat, dog, dog]
  [rat, cat, dog, dog]
  [dog, cat, rat]
  [cat, dog, rat]//TreeSet，去重
  [rat, cat, dog]
  {dog=Spot, cat=Rags, rat=Fuzzy}
  {cat=Rags, dog=Spot, rat=Fuzzy}
  {rat=Fuzzy, cat=Rags, dog=Spot}
  *///:~
  ```

1. `Collection`容器包括`List`、`Set`、`Queue`三种，默认打印出来内容用方括号括住，用逗号隔开；`Map`则用大括号括住，键与值用等号连接（键左值右）
2. `HashSet`、`TreeSet`、`LinkedHashSet`都是`Set`类型，每个相同的项仅保存一次
   - 如果存储顺序重要，可以使用`TreeSet`，按照比较的结果升序放置元素；
   - `LinkedHashSet`按照添加的顺序放置元素；
   - `HashSet`比较复杂，是最快获取元素的方式，详情见第十七章；
3. `Map`（也称为关联数组），对于每个键只存储一次；无需指定大小-，会自动调整；`Map.get(key)`得到该键对应的值；`Map.put(key,value)`添加新的键值对；
   - `HashMap`与`HashSet`一样；`TreeMap`按照比较的结果保存数据；`LinkedHashMap`按照插入的顺序保存键，同时也具有`HashMap`一样的查询速度

#### 11.5  List

两种类型的List

- `ArrayList`，长于随机访问元素，但在`List`的中间插入和删除元素速度较慢，下标也是从0开始
- `LiskedList`提供了优化的顺序访问，在随机访问中较慢

方法：

1. `listname.size()`:  得到List大小
2. `listname.contains(Object())`：确定某个对象是否在列表中
3. `listname.remove(Object())`：移除某个对象
4. `listname.indexOf(Object())`：确定某个对象在列表中的位置，如果未查找到则为-1
5. `listname.set(int Idx, Object())`:  替换List中指定位置的元素
6. `listname.add(new Object())`:  向队列中添加新的对象
7. `listname.add(int Idx, new Object())`:  向队列指定位置中添加新的对象
8. `listname.addAll(int Idx, sub)`:  向队列指定位置中添加新的List
9. `listname.subList(int fir, int end)`:  得到子列，`fir`：起始下标、`end - fir`：子列长度
10. `listname.containsAll(sub)`:  判断是否含有`sub`子列中所有元素
11. `Collections.sort(listname)`:  对所有成员进行排序
12. `Collections.shuffle(sub, rand)`：  Mix it up
13. `Array.asList(Object, Object, ...)`:  得到的是一个List列表，可用于初始化



#### 11.6 迭代器

- 一种设计模式，不关心类型，能应用于不同类型的容器

- **Iterator**

  - 使用方法`iterator()`要求容器返回一个*Iterator*，将准备好返回第一个元素
  - 用`next()`获得序列中的下一个元素
  - 使用`hasNext()`检查序列中是否还有元素
  - 使用`remove()`将最近返回的元素删除

  ```java
  List<Pet> pets = Pets.arrayList(12);
  Iterator<Pet> it = pets.iterator();
  //遍历的方法
  while(it.hasNext())
      Pet p = it.next();
  	...
  for(int i = 0; i < n; i++)//n < pets.size
  {
      it.next();
      it.remove();
  }
  print(pets);//此式得到的结果是移除原序列前n项，
  //如果只是遍历，foreach语法更合适
  ```

- **ListIterator**

  - 更加强大的*Iterator*的子类型，可用于各种List的访问，支持双向移动
  - 使用`listIterator()`获得一个指向List开始处的`listIterator`，如果指定了参数，就指向对应的列表索引处
  - 使用`set()`方法替换访问过的最后一个元素

  ```java
  List<Pet> pets = Pets.arrayList(12);
  ListIterator<Pet> it = pets.listIterator();
  while(it.hasNext())
        System.out.print(it.next() + ", " + it.nextIndex() 
                         +", " + it.previousIndex() + "; ");
  //此时从List开始处进行遍历，对应的输出结果是 -- 第一个值 ，1 ，0
  
  ```



#### 11.7  LinkedList

- 与*ArrayList*一样实现了基本的List访问接口，在执行插入和删除时更高效，随机访问表现较差
- 添加了可用作栈、队列和双端队列的方法
- 方法：
  - `getFirst()`与`element()`都返回列表的头（第一个元素），为空异常，`peek()`则会返回null
  - `remove()`和`removeFirst()`都移除并返回列表的头，为空异常，`poll()`则会返回null
  - `addFirst()`和`add()`和`addLast()`一样，将某个元素添加到列表的尾端
  - `removeLast()`: 移除并返回列表的最后一个元素

#### 11.8  Stack（LIFO）

- 可将*LinkedList*直接作为栈使用

  ```java
  private LinkedList<T> storage = new LinkedList<T>();
  public void push(T v) { storage.addFirst(v); }
  public T peek() { return storage.getFirst(); }
  public T pop() { return storage.removeFirst(); }
  public boolean empty() { return storage.isEmpty(); }
  public String toString() { return storage.toString(); }
  ```

  

#### 11.9  Set

- 不保存重复元素，基于对象的值确定归属性

- TreeSet将元素存储在红黑树中，HashSet使用了散列函数

- 如果需要对结果进行排序，使用TreeSet代替HashSet

  ```java
  Set<String> set1 = new HashSet<String>();
  Collections.addAll(set1,
        "A B C D E F G H I J K L".split(" "));
  //此外，还有add()方法，remove(),removeAll()
  ```

#### 11.10  Map

- `map.containsKey()`: 查找是否含有某个键
- `map.containsValue()`:  查找是否含有某值
- `set(key,value)`  与 `put(key,value)`: 设置和添加键值对
- `map.keySet()`: 获取所有的键
- `map.values()`: 得到所有的值
- `map.get(key)`: 获取对应的值

#### 11.11  Queue

- 队列是典型的先进先出容器，*LinkedList*实现的*Queue*接口，可将*LinkedList*上转型为*Queue*

- 方法：

  - `Queue<Integer> queue = new LinkedList<Integer>()`: 初始化
  - `queue.offer()`: 将一个元素插入队尾
  - `queue.peek()`  和`queue.element()`: 在不移除的情况下返回队头，在队列为空时peek()返回*null*、element()抛出异常
  -  `queue.poll()`  和`queue.remove()`: 移除并返回队头，在队列为空时poll()返回*null*、remove()抛出异常

  ##### 11.11.1 PriorityQueue

  1. 优先队列，下一个弹出的元素具有最高的优先级

  2. 用`offer()`方法添加元素时会对插入的元素自动进行排序（取决于选择的算法）

  3. `PriorityQueue<Integer> priorityQueue  = new PriorityQueue<Integer>();`//可带参数

  4. ```java
     priorityQueue = new PriorityQueue<Integer>(//默认数值小优先级高
             ints.size(), Collections.reverseOrder());//改变了优先级
         priorityQueue.addAll(ints);
     ```

#### 11.12  Collection和Iterator

- **Collection**是描述所有序列容器共性的根接口 -- java.util.AbstractCollection 类
- 



### 第十二章  通过异常处理错误

##### Formatter转换

| d    | 整数型（十进制）     |
| ---- | -------------------- |
| c    | Unicode字符          |
| b    | Boolean值            |
| s    | String               |
| f    | 浮点型（十进制）     |
| e    | 浮点型（科学计数法） |
| x    | 整数（十六进制）     |
| h    | 散列码（十六进制）   |
| %    | 字符“%”              |

```java
//import java.util.*;
//格式化输出
Formatter f = new Formatter(System.out);
char u = 'a';
f.format("s: %s\n",u);


String.format("~...");//返回一个String对象
```





```java
System.out.println("-1234".matches("-?\\d+"));//result : true
//	表示前面可能有一个符号、后面有一个或多个数字
//	(-|\\+)?\\d+		 表示前面可能会有一个加号或者一个减号开头
//	? 表示可能没有
```





#### 13.7 扫描输入

```java
Scanner sc = new Scanner(SimpleRead.input); 
sc.useGelimiter("\\s*,\\s*");//指定界定符，本指使用逗号（包括逗号前后任意的空白字符）
String s = sc.nextLine();

System.out.println(System.getProperty("user.dir"));//获得当前系统路径

```



#### 字节流与字符流

```java
//数据如果可以用Windows自带的记事本打开，且能被读懂，则使用字符流，否则用字节流
FileOutputStream(String name):创建文件的输出流以指定的名称写入文件

//字符流复制java文件
FileReader fr = new FileReader("filename");//是路径，一般是当前项目的下的与src同一级的文件
FileWriter fw = new FileWriter("newAddress");
char[] chs = new char[1024];
int len;
while((len = fr.read(chs)) != -1)
    fw.write(chs,0,len);
fw.close();
fr.close();
    
```

#### 对象序列化

