# Redis

### 配置
```shell
redis-server.exe --service-install redis.windows.conf
```
### 内部数据结构

redis 底层采用SDS数据结构,其中对于 *append* 操作采用了内存优化策略,即在执行*append*时为buf增加了一倍额外空间


```c
struct sdshdr{
	int len;
	//剩余长度
	int free;
	//实际存放字符串数据的地方
	char[] buf;
}
```

watch

```
WATCH 命令用于在事务开始之前监视任意数量的键：当调用 EXEC 命令执行事务时，如果
任意一个被监视的键已经被其他客户端修改了，那么整个事务不再执行，直接返回失败
```

发布和订阅

```
subscribe channel1, channel2  // 本客户端订阅的频道
publish 
unsubcribe 
```

