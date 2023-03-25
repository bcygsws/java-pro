## 第一章：Java的基本语法

### 一、Java简史

- 1990年，Sun公司的研究人员预料到了嵌入式系统在家用电器领域家用电器领域的前景，想使用C++为电视机、微波炉和电话编写一个通用控制系统
- C++语言没有垃圾自动回收系统、不可移植、不支持分布式和多线程的开发
- Sun研究人员决定放弃C++；1994年随着互联网和浏览器的出现，Sun使用Oak语言开发了自己的浏览器WebRunner
- 由于Oak商标已经被注册，Sun将Oak改名为Java,并放在互联网上，供人免费使用
- 1995年，Sun发布Java语言；第二年，发布JDK1.0
-

2009年4月20日；Sun公司被Oracle以74亿美元的总价收购，Sun公司虽然不在了，但是Java语言却猎猎作响。原因是谷歌在2007年发布了Android系统，Android使用虚拟机Dalvik（达尔维克）,解释.dex文件；Dalvik和JAVA虚拟机JVM类似

### 二、Java的运行机制（编译型语言和解释型语言）

#### 编译型语言

- 编译型语言是编译器将代码译成机器码（机器指令和操作数），再包装成符合在平台上运行的可执行文件格式，可执行文件能够脱离运行环境运行；但是，可执行文件一般不能在其他平台上运行。因此，编译型语言不可抑制
- 编译型语言：C C++ Object-C Swift 和Kotlin(科特林)等高级语言是编译型语言

### 解释型语言

- 解释型语言：通常是编译和解释混合在一起；解释器编译后立即执行。解释一次代码就需要编译一次，因此解释型语言运行效率低
- 解释型语言：Javascript Ruby Python

### Java语言的编译过程

- Java语言是编译型语言，又是解释型语言；Java语言不是纯粹的编译型语言
- Java语言的编译过程：javac编译器将代码编译成字节码（不是普通编译型语言的机器码），字节码再有虚拟机JVM解释执行
- 命令1：javac HelloWorld.java,编译成.class文件（字节码文件）

#### java命令

    - 命令2：java HelloWorld
    - 字节文件与平台无关，java语言具有可移植性了

## 第二章：理解面向对象

### 一、Java是纯面向对象语言

#### 纯粹的面向对象语言

- Java是纯粹的面向对象语言，它完全具备面向对象语言的三个基本特征：封装、继承、多态；Java语言以对象为中心，Java程序的最小程序单位是类
- OOA：面向对象分析
- OOD：面向对象设计
- OOP：面向对象编程
- UML：统一建模语言，UML2.0有多达13种UML图形，例图、类图、组件图、部署图、状态机图等等

#### 软件开发的两种主流方法：结构化程序设计和面向对象程序设计

- 早期的C、visual Basic、Pascal都是结构程序设计语言
- 随着软件开发技术的发展，人们逐渐认识到，面向对象语言能够提供更好的可重用性、可维护性和可扩展性，C++ C# Ruby Java等面向对象的程序设计

### 二、结构化程序设计语言

- 原则:自顶向下、逐步求精、模块化等
- SA:结构化分析
- SD:结构化程序设计
- SP:结构化编程
- 结构化程序设计主张：程序设计按照功能进行逐步细分，因此结构化程序设计方法，也被称为面向功能的程序设计方法

### 三、程序设计的三种结构

- 顺序结构
- 选择结构
- 循环结构

### 四、面向对象简介和面向对象的基本特征

- 面向对象是一种更优秀的编程方式，它利用对象、封装、继承、多态、消息等基本概念来构建软件系统
- 面向对象，是以现实世界中事物（对象）为中心，认识问题。并根据这些事物的本质特点，抽象成系统中的类，作为系统的基本单元

### 五、结合代码体会封装（encapsulation）、继承（inheritance）、多态（polymorphism）

#### 封装-encapsulation

- 参考文档：[体会封装](https://blog.csdn.net/weixin_47556601/article/details/122521852)
- 封装是指将对象的实现细节隐藏起来，而通过一些公共方法暴露对象的功能

#### 继承-inheritance

- 继承是面向对象复用代码的一种手段，当子类继承父类后，子类成为一种特殊的父类，直接获得父类的属性和方法

#### 多态-polymorphism

- 多态是指子类实例对象可以赋值给父类变量，但当代码运行时依然表现出子类的行为特征；这意味着同类型的变量调用同一个方法时，可能表现出多种行为特征

## IDEA创建Java项目过程

- 工具栏->文件->新建项目->Java模块(其中java SDK 选择jdk11)->下一步->下一步->输入项目名称即可
- 问题：项目中【添加配置】出现问题，工具栏中【文件】，项目结构，之后选择【模块】，点击那个 + 号；将项目添加到模块，否则在【添加配置】会报错，提示“找不到主类”

### 六、Java软件开发的七大原则-重点

- [Java软件开发七大原则-参考文档](https://blog.csdn.net/qq_42145271/article/details/105605973)

#### 6.1 开闭原则-OCP

- open-closed principle
- 对添加代码（扩展代码）打开，对修改代码关闭（尽可能少的修改原代码）

#### 6.2 依赖倒置原则-DIP

- dependency inversion principle
- 高层次模块不依赖低层次模块，两者都依赖抽象
- 抽象不依赖细节，细节依赖抽象
- 面向接口编程，而不是面向实现编程

#### 6.3 单一职责原则-SRP

- single responsibility principle
- 一个类、接口和方法只承担一个职责

#### 6.4 接口隔离原则-ISP

- interface segregation principle
- 使用多个单项接口，而不是一个总接口；客户端不依赖不需要的接口

#### 6.5 迪米特法则LoD（或者LKP）

- Law of Demeters
- 一个对象要尽可能少的知道其他对象的细节，有成为最少知道原则（least knowledge principle,LKP）

#### 6.6 里式替换原则-LSP

- Liskov substitution principle
- 定义：如果存在一个T1类型的对象t1,存在一个T2类型的对象t2,使得T1类定义的程序p,当对象t1替换成t2时，程序p不发生变化；那么T2就是T1类型的子类型
- 说的是“尽可能的扩展代码，而不是修改代码”
- 要实现类中的抽象方法，而不是非抽象方法
- 可以为类添加自己的方法
- 当子类重载父类中的方法时，子类重载方法的前置条件（输入/入参）要比父类的输入参数要宽松
- 当子类覆盖父类中的方法时，子类覆盖方法的后置条件（输出/返回值）要和父类中相等，甚至更严格（子类被覆盖方法的返回值相同或子类和异常要少）

#### 6.7 合成复用原则-CARP

- Composite/Aggregate reuse principle
- 尽可能的使用组合/聚合（has-a/contains-a）的方式，实现代码复用，而不是继承的方式实现代码复用

### GIT版本控制工具

#### 概念识记

- 单独的HEAD指的是当前提交
- 变基中git rebase -i HEAD~n
- HEAD~1 ：表示当前提交的上一次提交
- HEAD~2:表示当前提交的之前两次的提交
- HEAD~3:依次类推
