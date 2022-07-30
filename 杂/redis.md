# Redis

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

