### MapReduce

- ##### 介绍：
  - MapReduce是一个分布式运算程序的编程框架， 核心功能是将用户编写的业务逻辑代码和自带默认组件整合成一个完整的分布式运算程序, 并发运行在Hadoop集群上
  - MapReduce设计并提供了统一的计算框架，为程序员隐藏了绝大多数系统层面的处理细节。
    为程序员提供一一个抽象和高层的编程接口和框架。程序员仅需要关心其应用层的具体计算问题，仅需编写少量的处理应用本身计算问题的程序代码。如何具体完成这个并行计算任务所相关的诸多系统层细节被隐藏起来，交给计算框架去处理

- 核心思想：**分而治之**

  - Map负责“分”， 即把复杂的任务分解为若干个“简单的任务”来并行处理。可以进行拆分的
    前提是这些小任务可以并行计算,彼此间几乎没有依赖关系。
  - Reduce负责“合”， 即对map阶段的结果进行全局汇总。
  - MapReduce运行在yarn集群

- 执行流程：

```sequence
Title: 执行流程

Client->ApplicationManager: 提交计算任务(1)

Note over ApplicationManager,ResourceSheduler: ResourceManager

ApplicationManager -> NodeManager :启动AppMastr(2)

NodeManager -> MRAppMastr : 启动（3）

MRAppMastr -> ApplicationManager:向ResourceManager申请资源(4)

ResourceSheduler-> MRAppMastr:回复相应的资源列表(5)

MRAppMastr -> NodeManager2:要求nm分配资源(6)

Note right of NodeManager2: 启动相应的Map Task和Reduce Task(7)

NodeManager2 -> MRAppMastr:返回计算状态和结果(8)

MRAppMastr -> ApplicationManager:汇报计算结果(9)
```

