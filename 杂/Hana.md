# Hana

### 1 背景

![image-20220726203256213](C:\Users\路瞳\AppData\Roaming\Typora\typora-user-images\image-20220726203256213.png)

![image-20220726203308422](C:\Users\路瞳\AppData\Roaming\Typora\typora-user-images\image-20220726203308422.png)



### 2 什么是HANA？

High‐Performance ANalytic Appliance
定位：软硬件一体化设备
软件：内存计算引擎 / 内存数据库( SAP In‐Memory Database, IMDB )
硬件：认证的预配置的硬件系统( HP / CISCO / IBM / DELL / FUJITSU / HITACHI )
( 注意：没有SUN哦 )

行存储：每行数据放一起，适合交易型应用（OLTP）

列存储列存储：：每列数据放每列数据放 起一起，，适合分析型应用适合分析型应用（OLAP）

### 3 优势

快速

多任务并行处理多任务并行处理

减少数据传输减少数据传输

对于 SAP/ERP 系统,实时获取数据库中数据

ABAP 编程语言

对于sap系统,可以通过SLT和Data Services工具复制数据,也支持csv文件进行数据导入

Index Server 是HANA最核心的组件, 承担着内存管理、事务管理和元数据管理权限认证等  

XS Server 是 Extended Application Services 的缩写,在HANA系统和用户之间充当桥梁的作用,内置了一个轻量级的web服务器, 预置大量 JS API,   



### 4 基本语法