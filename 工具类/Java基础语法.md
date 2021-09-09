从键盘获得输入
```java
    public static void ScannerTest(){ 
        Scanner sc = new Scanner(System.in); 
    System.out.println("ScannerTest, Please Enter Name:"); 
    String name = sc.nextLine();  //读取字符串型输入 
    System.out.println("ScannerTest, Please Enter Age:"); 
    int age = sc.nextInt();    //读取整型输入 
    System.out.println("ScannerTest, Please Enter Salary:"); 
    float salary = sc.nextFloat(); //读取float型输入 
    System.out.println("Your Information is as below:"); 
    System.out.println("Name:" + name +"\n" + "Age:"+age + "\n"+"Salary:"+salary); 
  } 


str.toCharArray() 方法可以将一个String 转化为一个char数组
原文链接：https://blog.csdn.net/u012249177/article/details/49586383
```
```java
//得到八进制以及十六进制
System.out.print(``"0"``+Integer.toOctalString(``1234``)+``" "``);
    ``String str = (Integer.toHexString(``1234``)).toUpperCase();
    ``System.out.println(``"0X"``+str);
```

```java
十六进制转十进制
String num = ``"ABCDEF"``;
    ``String str = ``new` `BigInteger(num,``16``).toString(``10``);//取十位

String s = Integer.valueOf("ABCDEF", 16).toString();
Integer.parseInt("ABCDEF",16)
```

```
System.out.println()方法的返回值是一个PrintStream对象，C语言返回一个int类型，即所打印的字符串长度
//使用；或，分隔输入数据
        String[] data = scanner.nextLine().split("[;,]"); 
        
```

```java
//格式化输出
Formatter f = new Formatter(System.out);
f.format("%-15.15s %5d %10.2f\n", name, qty, price);
```

```
//一行一行读取int数字
String str = sc.nextLine();
            if (str.isEmpty()) {
                break;
            }
            num = Integer.parseInt(String.valueOf(sc.nextInt()));
```



```java
//创建一个文件并向其写入数据
ArrayList<String> array = new ArrayList<String>();

        array.add("one");
        array.add("two");
        array.add("three");

        //获得当前系统路径
        //System.out.println(System.getProperty("user.dir"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"));
        for (String string : array) {
            bw.write(string);
            bw.newLine();
            bw.flush();
        }
        bw.close();



//从当前项目下的文件中按行获取字符数组

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

		BufferedReader br = new BufferedReader(new FileReader("array.txt"));
        ArrayList<String> arrayList = new ArrayList<String>();

        String line;
        while((line = br.readLine())!=null) {
            arrayList.add(line);
        }
        br.close();

        for (String s: arrayList) {
            System.out.println(s);

        }
```

匿名内部类de工厂方法：

```java
interface Service {
  void method1();
  void method2();
}

interface ServiceFactory {
  Service getService();
}

class Implementation1 implements Service {//工厂2一致
  Implementation1() {} // Package access
  public void method1() {print("Implementation1 method1");}
  public void method2() {print("Implementation1 method2");}
  public static ServiceFactory factory = 
         new ServiceFactory() {
            
            @Override
            public Service getService() {
                // TODO Auto-generated method stub
                return new Implementation1();
            }
        };
}   
//。。。
public class Factories {
  public static void serviceConsumer(ServiceFactory fact) {
    Service s = fact.getService();
    s.method1();
    s.method2();
  }
  public static void main(String[] args) {
    serviceConsumer(new Implementation1Factory());
    // Implementations are completely interchangeable:
    serviceConsumer(new Implementation2Factory());
  }
}

```



