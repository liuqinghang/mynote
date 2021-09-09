```mermaid
stateDiagram
    [*] --> Still
    Still --> [*]
	
    Still --> Moving
    String --> sda
    Moving --> Still
    Moving --> das
    Moving --> Crash
    Crash --> heee
    heee --> fdsaas
```

```sequence

Alice->Bob: Hello Bob, how are you?
Note right of Bob: Bob thinks
Note left of Alice: Bob thinks
Bob-->Alice: I am good thanks!

```



```sequence

Title: 标题：复杂使用

对象A->对象B: 对象B你好吗?（请求）

Note right of 对象B: 对象B的描述

Note left of 对象A: 对象A的描述(提示)

对象B-->对象A: 我很好(响应)

对象B->小三: 你好吗

小三-->>对象A: 对象B找我了

对象A->对象B: 你真的好吗？

Note over 小三,对象B: 我们是朋友

participant C

Note right of C: 没人陪我玩
```



```flow
st=>start: Start
op=>operation: Your Operation
cond=>condition: Yes or No?
op2=>operation: Your Operation2
e=>end

st->op->cond
cond(yes)->op2
op2->e
cond(no)->op
```

```mermaid
graph LR
A[Hard edge] -->B(Round edge)
    B --> C{Decision}
    C -->|One| D[Result one]
    C -->|Two| E[Result two]
```

```mermaid
classDiagram
      Animal <|-- Duck
      Animal <|-- Fish
      Animal <|-- Zebra
      
      Animal : +int age
      Animal : +String gender
      Animal: +isMammal()
      Animal: +mate()
      class Duck{
          +String beakColor
          +swim()
          +quack()
      }
      class Fish{
          -int sizeInFeet
          -canEat()
      }
      class Zebra{
          +bool is_wild
          +run()
      }
```

```mermaid
pie
	title Pie Chart
	"Dogs" : 186
	"Cats" : 85
	"Rats" : 150
```

