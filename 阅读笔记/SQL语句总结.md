# 前言
	初学者有时会对语法有点疑问，因为mysql和SQLServer在操作数据库的时候会有一点点差异，本文主要是对两者在语法上的不同进行展示,
	建议直接看目录
# 数据库部分
## 加入数据库
##### MySql
```sql
mysql -uroot -p123123 -h127.0.0.1
//'-u'后的参数是本地数据库的用户名
//'-p'后的参数是对应的密码
```
##### SQLServer
```sql
sqlcmd -S localhost -U sa -P '<123123Aa!@>'
go 	//SQLServer下输完上述命令后回车，然后输入go回车执行
//sqlcmd 是连接数据库必要的语句， 
//localhost 代表从本地连接，sa 是用户名， 
//‘<123123Aa!@>’  是密码
```

**Oracle知识点**

```
定长选char 不然选verchar 2
|| 用于连接

不区分对象名称大小写
采用 xml 存储
运算符、连接符和函数大写
表名大写，列名大小写混合
视图会随着对应数据的更改更新
sql plus 报表
rem 表示remark 以rem开头的内容会被忽略
/*注释*/
ttitle 和 btitle 为标题，标识的内容会被放在每页报表的顶端和底部
换行符号 ！
column 标识每一列的标题
column name format 20 指定列宽为20

break on Name skip 1 on report
和order by Name配合使用，当Name对应值发生变化时空一行
compute avg 和 break on 配合使用

set linesize 80 控制行大小为80
set newpage 0 每个新页前空0行
spool activity.lst 提取所有输出并将其写到该文件
spool off 终止上面的输出

```



# 表的基本用法
### MySql
	    use MyDb;      //MyDb是创建表的目标数据库，use命令打开
	    CREATE TABLE t_user //create
	    (
	        id INT CHECK (SAGE >= 15 AND SAGE <= 40),
	        username VARCHAR(32),
	        password VARCHAR(32),
	        phone VARCHAR(11)
	    );
	
	    show tables;    //展示所有的表
	    **DESC 表名; **//查看该表的基本结构,可替换为'describe'
	    
	    **drop table tableName;**    //删除表
	    //记得加上分号，回车执行

### SQLServer
    //create 的方式与MySql一致 只是语句后不需要加分号
    
    sp_help 表名 //查看该表的基本结构
    drop table tableName    //删除表
    go //SQLServer下输完上述命令后回车，然后输入go回车执行

---
# 表的进阶用法（1）
### 表约束 

主键（PRIMARY KEY）约束
惟一（UNIQUE）约束
外键（FOREIGN KEY）约束
检查（CHECK）约束
说明：非空和默认值也可看成是约束。

### MySql

   #### 1.主键
    添加主键:
    id int primary key,//1.直接在创建时目标元素后加 *primary key*
    primary key(id)     //2.在定义完所有字段后加上
    	例：CREATE TABLE t_user
    		(
    		id INT,
    		username VARCHAR(32),
    		primary key(id)  
    		)

   	多字段联合主键:
    PRIMARY KEY(name,deptId)    //在定义完所有字段后加上
    
   #### 2.外键
    添加外键:
    CONSTRAINT 外键名 FOREIGN KEY 字段名 REFERENCES 主表名(主键名)
        例：给t_student表添加外键约束，外键为classId,外键名称为fk_stu_class1
    constraint fk_stu_class1 foreign key(classId)references t_class(id)  //id为共有的字段
                外键名称                添加的外键       被添加的外表名（关联的字段）    

   #### 3.其他约束:
        1.唯一约束（Unique Constraint）要求该列唯一，允许为空，但是只能有一个空值
            name VARCHAR(22) UNIQUE,//目标字段后直接加
        2.非空约束
            name VARCHAR(22) NOT NULL,//
        3.默认约束：即给字段一个默认值
            sex VARCHAR(2) DEFAULT '男',//
            + 如果是添加字符串型默认值要使用单引号，如果是整型则不需要加任何符号
            - 如果要添加的是中文默认值，则需要加上DEFAULT CHARSET=utf8;使用英文字符则不需要

   #### 4.设置表的属性值自动增加:  
   	系统自动生成字段的主键值           
    id int PRIMARY KEY AUTO_INCREMENT,//
    
    

### SQLServer
    //以上用法一致
    *修改字段类型
    ALTER TABLE t_user1 ALTER COLUMN phone VARCHAR(11)
    go //SQLServer下输完上述命令后回车，然后输入go回车执行

# 表的进阶用法（2）
   ### 1. 查看表结构与修改表名
    SHOW CREATE TABLE 表名; 
        //
    ALTER TABLE 旧表名 RENAME 新表名;
        //alter table tb_emp rename jd_emp;

   ### 2. 修改字段名与字段数据类型
    ALTER TABLE 表名 MODIFY 字段名 数据类型;
        //把数据表tb_emp的字段Id改名为prod_id，数据类型不变
        //alter table tb_emp change Id prod_id int(11);
    ALTER TABLE 表名 MODIFY 字段名 数据类型;
        //把数据表tb_emp字段Name的数据类型改为varchar(30);
        //alter table tb_emp modify Name varchar(30);

   ### 3. 添加与删除字段
    ALTER TABLE 表名 ADD 字段名 字段类型 (after Name / first);//最后不带参默认加到表的最后
        //alter table tb_emp add Country varchar(20);
        //alter table tb_emp add Country varchar(20) first;   将字段加到第一列
        //alter table tb_emp add Country varchar(20) after Name;  加到'Name'字段后
    alter table 表名 drop 字段名;
        //删除字段

   ### 4. 修改字段位置
    ALTER TABLE 表名 MODIFY prod_price FLOAT FIRST;//改为第一字段
    ALTER TABLE 表名 MODIFY prod_price FLOAT AFTER prod_country;//改到某一字段后

   ### 5. 删除表的外键约束

     ALTER TABLE 表名 DROP FOREIGN KEY 外键约束名;

### 6. 插入数据

```sql
insert into student (sno,sname,sage) values ('132','zhangsan',23)
//字符型、日期型数据要用单引号括起来。
```

### SQLServer

    //以上
    go //SQLServer下输完上述命令后回车，然后输入go回车执行

---
# 基础数据查询
### 0, 查询基本结构

```sql
SELECT            <目标列名序列>    -- 需要哪些列
      FROM         <表名>                -- 来自于哪些表
      [WHERE     <行选择条件>]      -- 根据什么条件
      [GROUP BY <分组依据列>]      --分组依据
      [HAVING     <组选择条件>]      
      [ORDER BY  <排序依据列>] 
```

   ### 1, 空值与重复
```sql
例: WHERE DeptId IS NULL;
例: 使用关键字DISTINCT返回数据表中字段Name不重复的内容
	SELECT DISTINCT Name FROM tb_emp;
```

   ### 2, WHERE IN (NOY IN) 与 AND OR
```sql
例:
    where prod_c in (1,87,39);
    WHERE DeptId = 301 and Salary > 3000;
    WHERE DeptId =301 or DeptId = 303;
注意：OR的优先级小于AND，要改变运算的顺序可以通过加括号的方式实现
```
   ### 3, BETWEEN AND 
```
(NOY BETWEEN AND)
//参数一个是范围的开始值，另一个就是结束值
	where Salary BETWEEN 3000 AND 5000;
```

   ### 4, 通配符
```sql
'^'表示取反; '%'表示全部字符; '_'匹配一个字符;  
[] 模糊匹配数据内容     //指包含符合要求的即可
	WHERE  字段名 LIKE '[数据]' 
例：检索表 Products 中所有不以 B 为起始字符的产品的所有内容
	where prod_name like '[^B]%'
例：检索表 Products 中所有大于等于 10 inch 的产品的所有内容
	where prod_name like '1_%'
```

   ### 5, 条件查询
```sql
1. SELECT TOP 行数 字段名 FROM 表名
    例：从表 Customers 中得到 cust_name 字段的前 3 行内容
    SELECT TOP 3 cust_name FROM Customers
2. 表达式查询
    例: SELECT 包含字段名的表达式 AS 新字段名 FROM 表名
    SELECT cust_id, cust_id+1 as new_cust_id FROM Customers
    例：检索表 Products 中字段 prod_price 的内容，并检索到字段 prod_price 打8折后的价钱，命名该打折后的价格为 discount_price 
    select prod_price, prod_price*0.8 as discount_price from Products
    例: 在Student表中查询学号的倒数第三为位不是1、2、3的学生信息
  	select * from  student where number like '%[^123]__'
```

   ### 6,  函数
```sql
1. count SELECT COUNT(* / 字段名) AS 新列名 FROM 表名 
    例：返回表 Products 中商品价格( prod_price )大于 10 的商品个数
	select count(prod_price) from Products where prod_price > 10;
2. sum //用法与上面一致
    例： Products 中增加了 quantity（数量）字段，请返回所有商品的价格与数量乘积的总和（即总库存的金额），并命名它为 amount
    select sum(quantity * prod_price) as amount from Products;
3. AVG（<列名>）：计算列值的平均值（必须是数值型列）。
4. MAX（<列名>）：得到列值的最大值。
5. MIN（<列名>）：得到列值的最小值。

//除COUNT（*）外，其他函数在计算过程中均忽略NULL值。
//聚合函数不能出现在WHERE子句中
操作：查询学分最高的课程名，如下写法是错误的!
    SELECT Cname FROM Course WHERE Credit = MAX(Credit) //error

    declare @credit int
    select @credit=max(credit) from course
    select cname from course where credit=@credit
```
### 7,  分组函数

```sql
//查询选课门数超过3门的学生的学号和选课门数
SELECT Sno, Count(*) 选课门数 
FROM SC 
GROUP BY Sno 
HAVING COUNT(*) > 3
```

### SQLServer

```sql
用法参考以上，只是不需要添加分号
注：检索不在 New York上   用'<>'    
	WHERE cust_city <> 'New York'
    go //SQLServer下输完上述命令后回车，然后输入go回车执行
```


---
# 视图
### 定义
	视图（view）是一种虚拟存在的表，是一个逻辑表，本身并不包含数据。通过视图，可以展现基表（用来创建视图的表）的部分数据；视图数据来自定义视图的查询表。
### 创建视图
    CREATE [OR REPLACE] [ALGORITHM = {UNDEFINED | MERGE | TEMPTABLE}]
    VIEW view_name [(column_list)]
    AS select_statement
    [WITH [CASCADED | LOCAL] CHECK OPTION]
### 参数说明
	OR REPLACE：表示替换已有视图；
	
	ALGORITHM：表示视图选择算法，默认算法是UNDEFINED(未定义的)： MySQL 自动选择要使用的算法 ；merge合并；temptable临时表；
	
	column_list：可选参数，指定视图中各个属性的名词，默认情况下与select语句中查询的属性相同；
	
	select_statement：表示select语句；
	
	[WITH [CASCADED | LOCAL] CHECK OPTION]：表示视图在更新时保证在视图的权限范围之内；cascade是默认值，表示更新视图的时候，要满足视图和表的相关条件；local表示更新视图的时候，要满足该视图定义的一个条件即可

### 删除视图
	DROP VIEW view_name;

### 实例
![创建视图](https://img-blog.csdnimg.cn/20200419131016222.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDcwODIzMw==,size_16,color_FFFFFF,t_70)
![删除](https://img-blog.csdnimg.cn/20200419131449987.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDcwODIzMw==,size_16,color_FFFFFF,t_70)
student表数据结构：
stu_id    | name | math | chinese
-------- | ----- | --------| -------
1| Tom | 78 | 80 |
2  | Jack | 70| 80
3  | Lucy | 97| 95
stu_info表数据结构：
stu_id    | classes	|city
-------- | ----- | --------| 
1| 1633	|长沙
2  |	1632	|重庆
3  | 1633	|成都

1. 在student表上创建视图stu_view，查询math、chinese字段并定义一个math+chinese成绩之和字段，值为两者之和；

2. 在student表和stu_info表上，创建stu_classes视图，查询出stu_id、姓名和班级，查询条件为两表中的stu_id字段相同

		```sql
	-- 1 创建单表视图
	CREATE view stu_view AS
	select math,chinese, math+chinese from student;
	-- 2 创建多表视图
	CREATE view stu_classes(stu_id,name,classes) AS
	select student.stu_id,student.name,stu_info.classes 
	from student,stu_info
	where student.stu_id=stu_info.stu_id;
	```
	
	



产生死锁的原因主要是：
（1） 因为系统资源不足。
（2） 进程运行推进的顺序不合适。
（3） 资源分配不当等。

产生死锁的四个必要条件：
（1） 互斥条件：一个资源每次只能被一个进程使用。
（2） 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
（3） 不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
（4） 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。



# 存储过程

>  创建存储过程
> 语法：
> CREATE PROC[EDURE] 存储过程名   [ { @参数名  数据类型 } [ = default ] [OUTPUT] ] [ , … n ]  
> AS      SQL语句 [ … n ]
>
> 执行存储过程
> 语法：
> [ EXEC [ UTE ] ] 存储过程名       [实参 [, OUTPUT] [, … n] ]

```mysql
-- mysql 存储过程实例
drop procedure if exists `hzdq_imp`.`first_pro`;

create definer=`root`@`localhost` procedure `first_pro`(IN depId BIGINT)
BEGIN
	declare de_name varchar(10) default('')
	declare d_count int default(0)
	select parent_id, dept_name
	from sys.dept
	where dept_id = depId;
END;

-- 通过 INTO 将查询到的值赋值给定义的对象
select count(*) into d_count

-- if-else 语法
	if `day` = 0 THEN
	select `星期天`;
	elseif `day` = 1 THEN
	select `星期一`;
	else 
	select *;
	end if;
	
-- 条件控制语法
case
	when num < 0 then
		select 'aa';
	when num = 0 then
		select 'bb';
	else
		select 'null';
	end case;

-- while循环
	set sum = 0;
	while num < 10 do -- 循环开始
		set num = num + 1;
		set sum = sum + num;
		end while; -- 循环结束
		
-- 在mybatis中调用存储过程
<parameterMap type="savemap" id="usermap">
	<paramter property="name" jdbcType="varchar" mode="IN">
	<paramter property="sex" jdbcType="varchar" mode="out">
</parameterMap>

<insert id="saveUserDemo" parameterMap="savemap" statementType="CALLABLE">
	{call saveuser(?,?,?)}
</insert>

```



```sql
-- 以下为 Oracle 的存储过程
-- 操作：创建并执行带输入参数的存储过程p_xsqk，查询指定学号(作为输入参数)的学生姓名、课程号、成绩。
create    procedure   p_xsqk    @xh   char(6)
    as    select   姓名，课程号，成绩    
    from    xsqk , xs_kc   
    where   xsqk.学号=xs_kc.学号  and  xsqk.学号= @xh
go
-- 执行
exec  p_xsqk   ‘020102‘         --（1）按位置传递参数
exec  p_xsqk   @xh=‘020103‘    --（2）通过参数名传递参数

-- 操作：创建1个带有输入参数和输出的存储过程p_kh，返回指定教师(作为输入参数)所授课程的课程号(作为输出参数)
create    procedure   p_kh
    @teacher  char(8) ,    @kch   char(3)    output
    as   
    select     @kch =  课程号   from   kc   where    授课教师= @teacher
go
-- 执行
declare    @teacher  varchar(8),  @kch  char(3)
set     @teacher='赵怡'	
exec   p_kh   @teacher,  @kch  output
print    @teacher + ‘教师所受课程的课程号为:’ + @kch

-- 操作：创建并执行存储过程p_find，用于查找指定的学生，如果找到，则返回数字1，否则返回0。
create    procedure   p_find
   @findname    char(8)
 as   
 if  exists (select   *  from   xsqk
        where  姓名=@findname)
     return   1
 else
     return  0
-- 执行
declare    @result    int
exec   @result=p_find   ‘陈伟‘
if   @result =1
	   print   ‘有这个人！‘
     else
        print   ‘ 没有这个人！	
```

[查找第n高的薪水](https://leetcode-cn.com/problems/nth-highest-salary/)

```mysql
语法:
select ifnull(tar, null) as 'sss'
实例
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      select ifnull(
      (select distinct salary
      from Employee 
      order by salary desc
      limit N,1), null
      ) as "getNthHighestSalary"
  );
END
```



# 触发器

> 语法：
>
> create   trigger    触发器名    
> on   表名| 视图名    
> for   |  after   |  instead   of      [ insert ,  update,  delete ]           
> as       SQL语句 
>
> 注意：1个表上可有多个触发器。  每个触发器只能作用在一个表上。这是一个一对多的关系

### insert 触发器

```sql
-- 例: 在xscj库的xs_kc表上创建1个名为tr_insert_cj的触发器，当向xs_kc表进行插入操作时激发该触发器，并给出提示信息“有新成绩插入到xs_kc表中!”
create    trigger    tr_insert_cj
     on   xs_kc      after   insert
     as    print   ‘有新成绩信息插入到xs_kc表! ’
go
```

### update 触发器

```sql
-- 例: 在student表上创建名为tr_update_xsqk2的触发器，当对该表的“姓名”列修改时激发该触发器，使用户不能修改“姓名”列。
create   trigger   tr_update_xsqk2   on   student   after   update
as
     if   update(姓名)
            begin
                  rollback   transaction         -- 撤消修改操作
                  raiserror(‘不能修改学生姓名！’ , 16 ,1) 
             end
go
 
```

### delete 触发器

```sql
-- 例: 在xscj库的xsqk表上创建1个名为tr_delete_xsqk的触发器，当要删除指定学号的行时，激发该触发器，撤消删除操作，并给出提示信息“不能删除xsqk表中的信息
  create   trigger   tr_delete_xsqk
     on   xsqk
     after   delete
as
      rollback   transaction
      print    ‘不能删除xsqk表中的信息!’
go
```

