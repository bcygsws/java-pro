## Java的基本语法

### 一、Java简史

- 1990年，Sun公司的研究人员预料到了嵌入式系统在家用电器领域家用电器领域的前景，想使用C++为电视机、微波炉和电话编写一个通用控制系统
- C++语言没有垃圾自动回收系统、不可移植、不支持分布式和多线程的开发
- Sun研究人员决定放弃C++；1994年随着互联网和浏览器的出现，Sun使用Oak语言开发了自己的浏览器WebRunner
- 由于Oak商标已经被注册，Sun将Oak改名为Java,并放在互联网上，供人免费使用
- 1995年，Sun发布Java语言；第二年，发布JDK1.0
-
2009年4月20日；Sun公司被Oracle以74亿美元的总价收购，Sun公司虽然不在了，但是Java语言却猎猎作响。原因是谷歌在2007年发布了Android系统，Android使用虚拟机Dalvik（达尔维克）,解释.dex文件；Dalvik和JAVA虚拟机JVM类似

### 二、Java的运行机制

#### 编译型语言

- 编译型语言是编译器将代码译成机器码（机器指令和操作数），再包装成符合在平台上运行的可执行文件格式，可执行文件能够脱离运行环境运行；但是，可执行文件一般不能在其他平台上运行。因此，编译型语言不可抑制
- 编译型语言：C C++ Object-C Swift 和Kotlin(科特林)等高级语言是编译型语言

### 解释型语言

- 解释型语言：通常是编译和解释混合在一起；解释器编译后立即执行。解释一次代码就需要编译一次，因此解释型语言运行效率低
- 解释型语言：Javascript Ruby Python