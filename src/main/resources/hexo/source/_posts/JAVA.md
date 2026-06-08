---
title: JAVA
slug: JAVA
date: 2024-01-01 10:00:00
tags:
  - 后端
  - Java
summary: 这是一篇关于 Java 的入门文章。
cover: https://ll-java-web.oss-cn-chengdu.aliyuncs.com/2025/04/c0a3df8c-aa10-4c41-bfcc-34539acadbe8.jpg
---

# 常用API：[idea](E:\ideacode\myapi)

## Math：

- 是一个帮助我们用于数学计算的工具类
- 私有化构造方法，所有的方法都是静态的

```java
public static int abs(int a);     //获取参数绝对值
public static double ceil(double a);  //向上取整
public static double floor(double a); //向下取整
public static int round(float a); //四舍五入
public static int max(int a,int b); //获取两个int值中的较大值
public static double pow(double a, double b); //返回a的b次幂的值
public static double sqrt(double a); //返回a的平方根
public static double cbrt(double a); //返回a的立方根
public static double random(); //返回值为double的随机值，范围[0.0,1.0)                                             
```

abs有个小bug

以int类型为例子，取值范围为：-214783648~214783647

如果没有正数与负数与之对应，那么结果就有误

可以用absExact：如果有误就会提示

ceil就相当于进1

floor就相当于去尾巴

round四舍五入

max获取两个整数的较大值

min则反之

pow表示a的b次幂

sqrt表示开平方根

cbrt表示开立方根

[Math](E:\ideacode\myapi\src\com\yhr\a01mathdemo1\MathDemo1.java)

两道算法水题

一：MathDemo2

## System

System也是一个工具类，提供了一些与系统相关的方法

```java
public static void exit(int status) 终止当前运行的java虚拟机 (0表示当前虚拟机正常停止，非0则是当前虚拟机异常停止)
public static long currentTimeMillis()  返回当前系统的时间毫秒值形式
public static void arraycopy(数据源数组，起始索引，目的地数组，目的地的起始索引，拷贝个数)  数组拷贝
```

计算机中的事件原点

1970年1月1日 00：00：00

这个算c语言的生日

但这是外国的，我国有时差

实际是1970年1月1日 08：00：00

1秒=1000毫秒，1毫秒==1000微秒，1微秒=1000纳秒

a02systemdemo2

第三个方法的细节：

1.如果数据源数组和目的地数组都是基本数据类型，那么两者的类型必须保持一致，否则会报错

2.在拷贝的时候需要考虑数组的长度，如果超出范围也会报错

3.如果数据源数组和目的地数组都是引用数据类型，那么子类类型可以赋值给父类类型(类似于多态)

SystemDemo3

## Runtime

Runtime表示当前虚拟机的运行环境

```java
public static Runtime getRuntime()  当前系统的运行环境对象
public void exit(int status)  停止虚拟机
public int availableProcessors()  获得CPU的线程数
public long maxMemory() JVM能从系统中获取总内存大小（单位byte）
public long totalMemory() JVM已经从系统中获取总内存大小（单位byte）
public long freeMemory() JVM剩余内存大小（单位byte）
public Process exec(String commad)  运行cmd命令
```

好玩的：

shutdow:关机

-s ：默认1分钟之后关机

-s - t：指定时间：指定关机时间，单位是秒

-a：取消关机操作

-r：关机并重启

## Object

Object是java的顶级父类。所有的类都直接或间接的继承于Object类

Object类中的方法可以被所有子类访问，所有我们要学习Object类和其中的方法

```java
public Object()  空参构造
```

顶级父类中只有无参构造方法

Object的成员方法：

```java
public String toString()   返回对象的字符串表示相等
public boolean equals(Object obj)  比较两个对象是否相等
public Object clone(int a)   对象克隆
```

细节：

System：类名

out：静态变量

System.out：获取打印对象

println（）：方法

参数：表示打印的内容

核心逻辑：

当我们打印一个对象的时候，底层会调用对象的toString方法，把对象变成字符串，然后再打印再控制台上，打印完毕换行处理

思考：默认情况下，因为Object类中的toString方法返回的是地址值。所以默认情况下，打印一个对象的就是地址值，但是地址值对于我们是没有什么意思

处理方案：重写方法

### toString方法的结论：

如果我们打印一个对象，想要看到属性值的话，那么就重写同String方法就可以了

再重写的方法中，把对象的属性值进行拼接

### equals结论：

如果没有重写equals方法，那么默认使用Object中的方法进行比较，比较的是地址值是否相等

一般来讲地址值对于我们意义不大，所以我们会重写，重写之后比较的就是对象内部的属性值了

```java
String s = "abc";
StringBuilder sb = new StringBuilder("abc");

s.equals(sb); false
sb.equals(s); false
```

1.s调用equals方法，s是字符串字符串中的equal方法，先判断参数书否为字符串，如果是字符串，再比较内部的属性，但是如果参数不是字符串，直接返回false

2.sb调用的equals，而sb是StringBuilder，而StringBuilder里面没有重写equals方法，所以用的是Object中的，所以false

### 对象克隆：

把A对象的属性值完全拷贝给B对象，也叫对象拷贝，对象复制

细节：

Cloneable接口

如果一个接口里面没有抽象方法，表示当前接口是一个标记性接口，现在Cloneable表示一旦实现，那么当前类的对象就i可以被克隆

如果没有被实现就不行

克隆出来一般是Object类型的，所以可能要强转一下

书写细节：

1. 重写Object类中的clone方法
2. 让javabean类实现Cloneable接口
3. 创建原对象并调用clone就可以了

对象克隆小结：

浅克隆：不管对象内部的属性是基本数据类型还是引用数据类型，都是完全拷贝过来

内存图：

![](C:\Users\LL\Desktop\Java笔记\浅克隆.jpg)

深克隆：基本数据类型拷贝文件，字符串复用，引用数据类型会重新创建新的

内存图：

![](C:\Users\LL\Desktop\Java笔记\深克隆.jpg)



Object里面的是浅克隆

以后深克隆会用一个第三方工具

gson.jar

浅克隆只有重写方法不用重写内容

深克隆就要重写方法里面的内容

### 总结：

1. Object是java中的顶级父类，所以类都直接或间接继承于Object类
2. toString():一般会重写，打印对象时打印属性
3. equals():比较对象时会重写，比较对象属性值是否相同
4. clone():默认浅克隆，如果需要深克隆需要重写方法或者用第三方工具类

### Objects

Objects是一个工具类，提供了一些方法去完成一些功能

Objects成员方法

```java
public static boolean equals(Object a,Object b) 先做非空判断(是null返回false)，在比较两个对象
public static boolean isNull(Object obj) 判断对象是否为null，为null返回true，反之false
public static boolean nonNull(Object obj) 判断对象是否为null，跟isNull的结果相反
```

细节：
定义了一个Student工具类

1. 方法的底层会判断s1是否为null，如果为null，直接返回false
2. 如果s1不为null，那么就利用s1再次调用equals方法
3. 此时s1是Student类型，所以最终还是会调用Student中的equals方法，如果没有重写，就比较地址值，如果重写了方法，就比较属性值

## BigInteger

在java中，整数有四种类型：byte,short,int,long

在底层占用字节个数：byte：1个字节，short：2，int：4，long：8

```java
BigInteger构造方法：
public BigInteger(int num,Random rnd)  获取随机大整数，范围[0~2的num次方-1]（形参第二个是一个Random对象）
public BigInteger(String val) 获取指定的大整数
public BigInteger(String val,int radix) 获取指定进制的大整数(形参数是一个整数字符串和进制)
静态方法
public static BigInteger valueOf(long val)静态方法获取BigInteger的对象，内部有优化
```

细节：

1.对象一旦创建，内部记录的值不能发生改变(如果进行数值运算，他会创建了一个新的BigInteger对象)

2.字符串中必须是整数，否则会报错

3.字符串中的数字必须要跟进制吻合，比如二进制中只能写0和1写其他就会报错

4.valueOf他能表示的范围比较小，在long的取值范围之内，如果超出long的范围就不行了

5.BigInteger对内部常用数字做了优化：-16~16进行了优化，提前把-16~16创建了BigInteger对象，如果多次获取不会重新创建新的

BigInteger常见成员方法

```java
public BigInteger add(BigInteger val) 加法
public BigInteger subtract(BigInteger val) 减法
public BigInteger multiply(BigInteger val) 乘法
public BigInteger divide(BigInteger val) 除法，获取商
public BigInteger[] divideAndRemainder(BigInteger val) 除法，获取商和余数(0索引是商，1索引是余数)
public BigInteger equals(Object x) 比较是否相同(这里重写了方法，是比较的属性值)
public BigInteger pow(int exponent) 次幂
public BigInteger max/min(BigInteger val) 返回较大值/较小值(这里是返回大的值，不会创建新的对象)
public BigInteger int(long)(double)Value(BigInteger val) 转为int(long)(double)类型整数，超出范围数据有误
```

BigInteger底层存储方式

先变成补码，再以32位为一组的形式存到数组当中

BigInteger存储上限：数组的最大长度是int的最大值：2147483647

数组中最多能存储元素个数：21亿多

数组中每一位能表示的数字：42亿多

BigInteger能表示的最大数字为：42亿的21亿次方

## BigDecimal

作用：

- 用于小数的精确计算
- 用来表示很大的小数

```java
不建议用这个创建，因为会不精确
BigDecimal bd1 = new BigDecimal(0.3);
建议这个，传字符串才是精确的
BigDecimal bd1 = new BigDecimal("0.3");
也可通过静态方法创建
BigDecimal bd1 = BigDecimal.balueOf(0.3);
```

细节：

1. 如果表示的数字不大，没有超出double的取值范围，建议使用静态方法
2. 如果要表示的数字比较大，超出了double的取值范围，建议使用构造方法
3. 如果我们传递的是0~10之间的整数，包含0，包含10，那么方法就会返回已经创建好的对象，不会重新new

BigDecimal的使用

```java
public static BigDecimal valueOf(double val)  获取对象
public BigDecimal add(BigDecimal val) 加法
public BigDecimal subtract(BigDeciaml val) 减法
public BigDecimal multiply(BigDeciaml val) 乘法
public BigDecimal divide(BigDeciaml val) 除法，获取商
public BigDecimal divide(BigDeciaml val,精确几位,舍入模式) 除法
```

舍入模式参考这个RoundingMode：可以去API帮助文档找

UP：远离零方向舍入

DOWN：向零方向舍入

CEILING：向正无穷大舍入

FLOOR：向负无穷大舍入

HALF_UP:四舍五入

BigDecimal底层存储方式：

他会遍历得到每一个字符包括负号和小数点，再把这些字符转换成ASCLL码表对应的数值进行存储

## 正则表达式

正则表达式可以校验字符串是否满足一定的规则，并用来校验数据格式的合法性

作用：

1. 校验字符串的规则
2. 在一段文本中查找满足要求的内容

```正则表达式
字符类（只匹配一个字符）：
[abc] 只能是a，b或c  
[^abc]除了a，b，c之外的任何字符  
[a-zA-Z]a到z和A到Z包括的范围  
[a-d[m-p]]a到d，或m到p
[a-z&&[def]]a-z和def的交集，例：d，e，f  
[a-z&&[^bc]]a-z和非bc的交集，也就是补给（等同于[ad-z]）
[a-z&&[^m-p]]a到z和除了m到p的交集（等同于[a-lq-z]）
预定义字符（只匹配一个字符）
.  任何字符
\d 一个数字[0-9]
\D 非数字[^0-9]
\s 一个空白字符：[\t\n\x0B\f\r]
\S 非空白字符：[^\s]
\w [a-zA-Z_0-9]英文、数字、下划线
\W [^\w]一个非单词字符
数量词
X?  X,一次或0次
X*  X，零次或多次
X+  X，一次或多次
X{n}  X,正好n次
X{n,} X,至少n次
X{n,m} X,至少n但不超过m次
这里的X就是前面两种连着
这个在API帮助文档的Pattern里面有
忽略大小写格式：
例子：(?i)abc  (?i)字母
符号总结：
[]  里面内容出现一次
()  分组
^   取反
&&  交集，不要只写单个&
|   或
.   任意字符  细节：\n回车符号不匹配
\   转义字符
?   0次或1次
*   0次或多次
+   1次或多次
{}  具体次数
(?i)  忽略后面字符的大小写
a((?i)b)c  只忽略b的大小写
```

正则表达式的插件：any-rule

## 爬虫

### 本地爬虫

两个类：（都在regex包里）

Pattern:正则表达式

Matcher：文本匹配器，作用按照正则表达式的规则去读取字符串，从头开始读取

```java
获取正则表达式对象，不能new
Pattern.compile
find()方法  拿着文本匹配器从头开始读取，寻找是否有满足规则的子串，没有返回false，有返回true，在底层会记录字符串的起始索引和结束索引+1
第二次在调用find的时候，会继续读取后面的内容，读取到第二个满足要求的子串，方法会继续返回true
group() 方法 方法底层会根据find方法记录的索引进行字符串的截取，subString(起始索引，结束索引)；包头不包尾
第二次调用group方法的时候，会更具find方法记录的索引再次截取字串
例子：
String str = "Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，" +
                "因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        Pattern p = Pattern.compile("Java\\d{0,2}");
        Matcher m = p.matcher(str);

        while(m.find()){
            String s = m.group();
            System.out.println(s);
        }    
```

### 网络爬虫

混眼熟，先不练习

### 带条件的爬取

```java
String regex = "Java(?=8|11|17)";  这里的问好表示的是前面的数据Java，=表示在Java后面要拼接的数据，但获取的时候，只获取前半部分（也就是？的部分）
String regex3 = "((?i)Java)(8|11|17)";
String regex2 = "((?i)Java)(?:8|11|17)";这两个个上面两个是一样的意思
String regex4 = "((?i)Java)(?!8|11|17)";这里！就表示除了
```

### 贪婪爬取和非贪婪爬取

贪婪爬取：在爬取过程中尽可能多获取数据

非贪婪爬取：在爬取过程中尽可能少获取数据

在Java中默认是贪婪爬取，如果在+或者*加？就是非贪婪

## 正则表达式在字符串方法中的应用

```java
public String[] matches(String regex)  判断字符串是否满足正则表达式的规则
public String replaceAll(String regex,String newStr) 按照正则表达式的规则进行替换  
细节：方法在底层跟之前一样会创建文本解析器的对象，然后从头开始去读取字符串的内容，只要有满足的，那么就用第二个参数去替换
public String[] split(String regex)  按照正则表达式的规则切割字符串
```

this:表示当前调用者的地址值

细节：如果方法中形参里面有regex，就会识别正则表达式

## 分组

分组就是一个小括号

每组是有组号的，也就是序号

规则1：从1开始，连续不间断

规则2：从左括号为基准，最左边的是第一组，其次为第二组，以此类推

### 捕获分组

捕获分组就是把这一组的数据捕获出来，再用一次

```java
后续还要继续使用本组的数据

正则内部使用：\\组号  表示在用一次第几组的数据

正则外部使用：$组号
```

### 非捕获分组

分组之后不需要再用本组的数据，仅仅是把数据括起来

````java
(?:正则)  获取所有                 Java(?:8|11|17)
(?=正则)  获取前面                 Java(?=8|11|17)
(?!正则)  获取不是指定内容的前面部分  Java(?！8|11|17)
````

## JDK7前时间相关类 [时间](E:\ideacode\mydate\src\com\yhr\a01jdk7datedemo\DateDemo1.java)

```java
Date              时间
SimpleDateFormat  格式化时间
Calendar          日历    
```

### 时间的相关知识点

世界标准时间：

-  格林尼治时间/格林威治时间（Greenwich Mean Time）简称GMT
-  目前世界标准时间（UTC）已经替换为：原子钟

中国标准时间 ： 世界标准时间+8小时

时间单位换算：1秒=1000毫秒，1毫秒==1000微秒，1微秒=1000纳秒

### Data时间类

Data类是一个JDK写好的JavaBean类，用来描述时间，精确到毫秒

利用空参构造创建对象，默认表示系统当前时间

利用有参构造创建对象，表示指定的时间

```java
public Date()            创建Date对象，表示当前时间
public Date(long date)   创建Date对象，表示指定时间 
public void setTime(long time)  设置/修改毫秒值
public long getTime()    获取时间对象的毫秒值
```

细节：long类型的数字最后最好加一个L

### SimpleDateFormat类

作用：

格式化：把时间变成我们喜欢的格式

解析：把字符串表示的时间变成Date对象

构造方法：

```java
public SimpleDateFormat()                  构造一个SimpleDateFormat，使用默认格式
public SimpleDateFormat(String pattern)    构造一个SimpleDateFormat，使用指定格式
```

成员方法：

``` java
public final String format(Date date)      格式化（日期对象->字符串）
public Date parse(String source)           解析（字符串->日期对象）
```

格式化的时间形式的常用的模式对应关系如下：

```java
y 年  M 月 d 日  H 时 m 分 s 秒 
例子：2023-11-11 13：27：06  yyyy-MM-dd  HH:mm:ss
```

详细去api帮助文档搜SimpleDateFormat查看

细节：创建对象的格式要和字符串的格式一样

### Calendar

概述

- Calendar代表了系统当前时间的日历对象，可以单独修改、获取时间中的年，月，日
- 细节1：Calendar是一个抽象类，不能直接创建对象
- 细节2：月份：范围为0~11,如果获取出来的是0，就相当于是1月；星期：在老外眼里，星期日是一周中的第一天，那么星期日就是1，2就是星期一，以此类推

获取Calendar日历类对象的方法

```java
public static Calendar getInstance()     获取当前时间的日历对象
```

Calendar常用方法

```java
public fianl Date getTime()  获取日期对象
public final setTime(Date date)  给日历设置日期对象
public long getTimeInMillis()  拿到时间毫秒值
public void setTimeInMillis(long millis)  给日历设置时间毫秒值
public int get(int field)  取日历中的某个字段信息
public void set(int field,int value)  修改日历的某个字段信息
public void add(int field,int amount)  为某个字段增加/减少指定的值
这里的字段表示属性和成员变量的意思
```

底层原理：会根据系统的不同时区来获取不同的日历对象

会把时间中的纪元，年，月，日，时，分，秒，星期，等等都放到一个数组当中

在Canlendar里面有一个数组，0代表纪元，1代表年，2代表月，3代表一年中的第几周，4代表一个月中的第几周，5代表一个月的第几天（日期）...以此类推其他区API里面查就是

查表法：
表：容器，让数据跟索引产生关系

小细节：如果设置的月份超过了11，他会依次往后排，例如12，就是下一年的1月

## JDK8新增的相关时间类

| 代码层面                                             | 安全层面                                       |
| ---------------------------------------------------- | ---------------------------------------------- |
| JDK7:代码麻烦         日期对象计算和比较要换成毫秒值 | JDK7：对线程环境下会导致数据安全的问题         |
| JDK8：简单              有判断方法和时间间隔方法     | JDK8：时间日期对象都是不可变的，解决了这个问题 |



```java
Date类  ZoneId：时区                         日期格式化类：
       Instant：时间戳                       SimpleDateFormat    SimpleDateFormatter：用于时间的格式化和解析  
       ZonedDateTime：带时区的时间
    
日历类：    LocalDate : 年、月、日            工具类： Duration：时间间隔（秒，纳秒）
Calendar   LocalTime ：时、分、秒                    Period：时间间隔（年，月，日）
           LocalDateTime:年、月、日                 ChronoUnit：时间间隔（所有单位）
                         时、分、秒
```

ZoneId时区方法

```java
static Set<String> getAvailableZoneIds()  获取Java中支持的所有时区
static ZoneId systemDefault()  获取系统默认时区
static ZoneId of(String zoneId)  获取一个指定时区
```

Instant时间戳

```java
static Instant now()   获取当前时间的Instance对象（标准时间）
static Instant ofXxxx(long epochMilli) 根据（秒/毫秒/纳秒）获取Instant对象
ZonedDateTime atZone(ZoneId zone)  指定时区
boolean isXxx(Instant ohterInstant)  判断系列的方法
Instant minusXxx(long millisToSubtract)  减少时间系列的方法
Instant plusXxx(long millisToSubtract)  增加时间系列的方法
```

ZonedDateTime带时区的时间

````java
static ZoneDateTime now()  获取当前时间的ZonedDateTime对象
static ZonedDateTime ofXxxx(...)  获取指定时间的ZonedDateTime对象
ZonedDateTime withXxx(时间)    修改时间系列的方法
ZonedDateTime minusXxx(时间)   减少时间系列的方法
ZonedDateTime plusXxx(时间)   增加时间系列的方法
````

细节：JDK8新增的时间对象都是不可变的

如果我们修改了，减少了，增加了时间

调用者是不会改变的，他会创建一个新的时间对象

DateTimeFormatter用于时间的格式化和解析

```java
static DateTimeFormatter ofPattern(格式)  获取格式对象
String format(事件对象)    按照指定方式格式化
```

日历：

LocalDate（只能获取年月日）、LocalTime（只能获取时分秒）、LocalDateTime（可以获取到所有）

```java
static xxx now()      获取当前时间的对象
static xxx of(...)    获取指定时间的对象
get开头的方法    获取日历中的年、月、日、时、分、秒等(相获取数字的要用getValue)
isBefore，isAfter  比较两个LocalDate
with开头的   修改时间系列的方法
minus开头的   减少时间的方法
plus开头的    增加时间的方法
```

```java
public LocalDate toLocalDate()   LocalDateTime转换成一个LocalDate对象
public LocalTime toLocalTime()   LOcalDateTime转换成一个LocalTime对象
```

```java
Duration      用于计算两个“时间”间隔（秒。纳秒）
Period        用于计算了两个“日期”间隔（年、月、日）
ChronoUnit    用于计算两个“日期”间隔
```

JDk8的月份范围是1~12比较正常

## 包装类：

就是把基本数据类型对应的引用数据类型

获取Integer对象的方式(了解)

```java
public Integer(int value)  根据传递的整数创建一个Integer对象
public Integer(String s)    根据传递的字符串创建一个Integer对象
public static Integer valueOf(int i)  根据传递的整数创建一个Integer对象
public static Integer ValueOf(String s,int radix) 根据传递的字符串和进制创建一个Integer对象
```

这两种获取对象的区别(了解)

valueOf事先创建好了-128到127范围的一个数组对象，在这个范围就直接从这个数组调

以前计算是把对象先拆箱，变成基本数据类型，在相加最后在装箱

JDK5的时候提出了一个机制：自动装箱和自动拆箱

自动装箱：把基本数据类型会自动的变成其对应的包装类

自动拆箱：把包装类自动的变成其他对象的基本数据类型

JDk5后：int和Integer可以看作同一个东西，因为在内部可以自动转换

以后们获取获取对象不需要new直接赋值就行

Integer成员方法

```java
public static String toBinaryString(int i)  得到二进制
public static String toOctalString(int i)   得到八进制
public static String toHexString(int i)     得到十六进制
public static int parseInt(String s)        将字符串类型的整数转换int类型的整数
```

细节1：在类型转换的时候，括号中的参数中只能是数字不能是其他，否则代码会报错

细节2：8种包装类，除了Character都有对应的parseXxx的方法，进行类型转换

键盘录入弊端：当我使用next，nextInt，nextDouble在接收数据的时候，遇到空格，回车，制表符的时候就停止了

约定：以后要键盘录入，都用nextLine，不管什么类型，这个特点是遇到回车结束，到后面再用类型转换的方法就是

## 两道综合练习题：

### 键盘录入：

键盘录入一些1~100之间的整数，并添加到集合中，直到集合中所有数据和超过200为止

添加集合中有个细节：num：基本数据类型，集合里面的数据是Integer，在添加数据的时候触发了自动装箱

### 算法水题

自己时间parseInt方法的效果，将字符串形式的数据转换成整数

要求：字符串中只能是数字不能有其他字符，最少一位，最多10位，0不能开头

## 三道综合练习题

在mydate模块里面

### 算法水题

1.定义一个方法自己实现toBinaryString方法的效果，就一个十进制整数转换成字符串表示的二进制

基数取余法

不断的除以基数（几进制，基数就是几）得到余数，直到商为0，再将余数倒着拼接起来即可

细节：要先获取余数在除以2

StringBuilder里面有个insert：根据索引插入位置

2.使用代码实现计算你活了多少天，用JDk7和JDK8两种方法实现

3.判断任意的一个年份是闰年还是平年

要求：用JDK7和JDK8两种方式判断

提示：二月有29天是闰年，一年有366天是闰年

JDK8有个方法：isLeapYear()判断是否是闰年，是返回true，不是返回false

# 常见算法

## 关于交换两个变量的值

可以用位运算

```java
char a = 'a'
char b = 'b'
a = a^b
b = a^b
a = a^b
```

加入a = 3，b = 5

![](C:\Users\LL\Desktop\Java笔记\异或运算交换值.jpg)

## 查找算法

search-code

基本查找/顺序查找，二分查找/折半查找，分块查找，插值查找，斐波那契查找，树表查找，哈希查找

### 基本查找

核心：从0索引开始挨个往后查找

心得：如果要返回多个数据的话，可以把这些数据放到数组或者集合中

### 二分查找/折半查找

前提条件：数组中的数据必须是有序的

核心逻辑：每次排除一半的查找范围

过程：mid和max表示当前查找的范围，mid是min和max中间的，如果要查找的元素在mid左边，缩小范围时，min不变，max等于mid减1，如果要查找的元素在mid的右边，缩小范围时，max不变，min等于mid加1

如果数据是乱的，先排序在用二分查找得到的索引没有实际意义，只能确定当前数字在数组中是否存在，因为排序之后数字的位置就可能发生变化了

### 插值查找：

mid = min + (key - arr[min])/(arr[max]-arr[min]) * (max-min)

key就是要查找的数字

前提：分布最好要均匀，不然效率还没二分快

### 斐波那契查找

黄金分割比：  1 : 0.618

mid=min+黄金分割点左半边长度-1

详细见算法笔记

二分和插值还有斐波那契数据都要有顺序才可以

### 分块查找

分块的原则1：前一块中的最大数据，小于后一块中所有的数据(块内无序，块间有序)

分块的原则2：块数数量一般等于数字的个数开根号。比如：16个数字一般分为4块左右

核心思路：先确定要查找的元素在哪一块，然后在块内挨个查找

索引表：详细见idea

![](C:\Users\LL\Desktop\Java笔记\分块查找.jpg)

扩展的分块查找(无规律的数据)

课堂练习

![](C:\Users\LL\Desktop\Java笔记\扩展分块查找1.jpg)

扩展的分块查找（查找的过程中还需要添加数据）

![](C:\Users\LL\Desktop\Java笔记\哈希查找.jpg)

也叫哈希查找

## 排序算法

冒泡排序，选择排序，插入排序，快速排序

课后扩展：希尔排序，归并排序，堆排序，计数排序，桶排序，基数排序

### 冒泡排序

规则：把相邻的数据两两比较，小的放前面，大的放后面

1. 相邻二点元素两两比较，大的放右边，小的放左边
2. 第一轮循环结束，最大值已经找到，在数组的最右边
3. 第二轮循环只要在剩余的元素找最大值就可以了

### 选择排序

核心思想：从0索引开始，拿着每一个索引上的元素跟后面的元素依次比较，小的放前面，大的放后面，大的放后面，以此类推

1. 从0索引开始，跟后面的元素一一比较
2. 小的前面，大的放后面
3. 第一轮循环结束后，最小的数据已经确定
4. 第二循环从索引从1索引开始以此类推
5. 第三轮循环从2索引开始依次类推
6. 第四轮循环从3索引开始依次类推

### 插入排序

核心：将0索引的元素到N索引的元素看做是有序的，把N+1索引的元素到最后一个当成是无序的，遍历无序的数据，将遍历到的元素擦插入有序序列中合适的位置，如遇到相同数据，插到后面

N的范围：0~最大索引

### 递归算法

递归指的是方法中调用方法本身的现象

递归注意点：递归一定要有出口，否则就会出现内存溢出

作用：把一个复杂的问题层层转换为一个与原问题相似的规模较小的问题来求解

递归策略只需要少量的程序就可描述出解题过程所需要的多次重复计算

书写递归的两个核心：

- 找出口：什么时候不在调用方法
- 找规则：如何把大问题变成规模较小的问题

心得：方法内部再次调用的时候，参数必须要更加的靠近出口

内存图：

![](C:\Users\LL\Desktop\Java笔记\递归求阶乘内存图.jpg)

### 快速排序

第一轮：把0索引的数字作为基准数，确定基准数在数组中正确的位置。比基准数小的全部在左边，比基准数大的全部在右边

定义开始索引和结束索引，开始索引找比基准数大的数，结束索引找比基准数小的数，结束索引先开始动，然后再开始索引动，两个找到了就交换位置，然后继续找，知道结束索引和开始索引等于的时候，这个位置就是基准数的位置，这个数一定比基准数小，因为这个数是结束索引找的

最后用递归算法把左边的和右边的排序就是了

### Arrays

操作数组的工具类

```java
public static String toString(数组)                         把数组拼接成一个字符串    
public static int binarySearch(数组，查找的元素)              二分查找法查找元素
public static int[] copyOf(原数组，新数组长度)                拷贝数组
public static int[] copyOfRange(原数组，起始索引，结束索引)    拷贝数组(指定范围)
public static void fill(数组，元素)                         填充数组
public static void sort(数组)                              按照默认方式进行数组排序
public static void sort(数组，排序规则)                      按照指定的规则排序
```

binarySearch的细节：

细节1：前提：数组要有序，且是升序

细节2：如果查找的元素是整数存在的，那么返回的是真实的索引，但是，如果插入的元素是不存在的，返回的是（负的）- 插入点-1

例如int[] arr = {1,2,3,4,5,6,7,8,9};找20，返回的值就是-11，因为应该插入到10索引然后取反最后减1就是-11

疑问：为什么-1

因为我们查找数字0，0不存在，那返回就是-0，为了避免误解，所以-1

copyOf：数组拷贝

参数一：老数组，参数二：新数组长度

如果新数组长度小于老数组长度，会部分拷贝

如果新数组的长度是等于老数组的长度，会完全拷贝

如果新数组的长度是大于老数组的长度，会补上默认初始值

copyOfRange：拷贝数组(指定范围)

细节：包头不包尾

sort：

默认情况下，给基本数据类型进行升序排序，底层使用的是快速排序

sort的重载：很重要，一定要看源码[源码](E:\ideacode\sort-code\src\com\yhr\arraysdemo\MyArraysDemo2.java)

参数一：要排序的数组；参数二：排序的规则

细节：

1. 只能给引用数据类型的数组进行排序
2. 如果数组是基本数据类型的，需要变成其对应的包装类

第二个参数是一个接口，所以我们再调用方法的时候，需要传递这个接口的实现类对象，作为排序的规则，但是这个实现类，我只要用一次，所以就没有必要单独的去写一个类，直接采用匿名内部类就是

底层原理：利用插入排序+二分查找的方式进行排序的，默认把0索引的数据当作是有序的序列，索引到最后认为是无序的序列，遍历无序的序列中进行插入，在插入的时候，是利用二分查找确定A元素的插入点，拿着A元素，跟插入点的元素进行比较，比较的规则就是compare方法的方法体，如果方法返回的是负数，就拿着A继续跟前面的数据进行比较，如果方法返回的是正数，拿着A继续跟后面的数据进行比较，如果方法的返回值是0，也拿着A跟后面的数据进行比较，直到确定A的最终位置为止

简单理解：

01-02：升序排列

02-01：降序排列

## Lambda表达式

### 函数式编程

函数式编程是一种思想特点

函数式编程思想，忽略面向对象的复杂语法。强调做什么，而不是谁去做

而Lambda表达式就是函数式思想的体现

Lambda表达式的标准格式：

Lambda表达式是JDK8开始后的一种新语法形式

```java
() ->{
    
}
```

- ()对应着方法的形参
- ->固定格式
- {}对应着方法的方法体

注意点：

- Lambda表达式可以用来简化匿名内部类的书写
- Lambda表达式只能简化函数式接口的匿名内部类的写法
- 函数式接口：有且仅有一个抽象方法的接口叫做函数式接口，接口上方可以加@FunctionalInterface注解

### Lambda表达式的省略写法

省略核心：可推导，可省略

省略规则：

1. 参数类型可以省略不写
2. 如果只有一个参数，参数类型可以省略，同时()也可以省略
3. 如果Lamdba表达式的方法体只有一行，大括号，分号，return可以省略不写，但是前面要省略的时候需要同时省略

tip：实在写不来Lambda表达式，就先把匿名内部类写出来，在删减，或者Alt+回车在回车，idea会自动帮忙写

## 五道经典算法题

练习1：按照要求排序

定义数组并存储一些女朋友对象，利用Arrays中的sort方法进行排序

要求1：属性有姓名、年龄、身高

要求2：按照年龄的大小进行排序，年龄相等，按照身高排序，身高一样按照姓名的字母进行排序。（姓名不要包含中文或特殊符号，会涉及后面的知识）

```java
String里面有个compareTo方法，这个可以让字符串按照ASCll码表的顺序进行比较，返回的值他们相差多少的int类型的值
```

练习2：不死神兔

有一个很有名的数学逻辑题叫做不死神兔问题，有一对兔子，从出生后第三个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问第十二个月的兔子有几对兔子

也就是斐波那契数列

练习3：猴子吃桃子

有一堆桃子，猴子第一天吃了其中的一半，并多吃了一个！以后每天猴子都吃当前剩下来的一半，然后在多吃一个，第10天的时候（还没吃），发现只剩下一个桃子了，请问，最初总共多少个桃子

练习4：爬楼梯

可爱的小明特别喜欢爬楼梯，他有时候一次爬一个台阶，有的时候一次爬两个台阶，如果这个楼梯有20个台阶，小明一共有多少种爬法呢？

运算结果：

1层台阶：1种爬法

2层台阶：2种爬法

7层台阶：21种爬法

![](\爬楼梯.jpg)

爬20阶楼梯就相当于19和18阶的爬法相加，因为则这两个都包含了以前的爬法

# 集合进阶

## 集合体系结构

Collection：单列集合：意思就是添加数据的时候每次添加一个数据

Map：双列集合：添加数据的时候每次添加一对数据

### Collection

![](\Collection集合.jpg)

List系列集合：添加的元素是有序(存和取的顺序是一样的)、可重复、有索引

Set系列集合：添加的元素是无序、不重复、无索引

Collection是单列集合的祖宗接口，他的功能是全部单列集合都可以继承使用的

```java
public boolean add(E e)              给指定的对象添加到当前集合中
public void clear()                  清空集合中所有的元素
public boolean remove(E e)           把给定的对象在当前集合中删除(E e代表对象)
public boolean contains(object obj)  判断当前集合中是否包含给定的对象
public boolean isEmpty()             判断当前集合是否为空
public int size()                    返回集合中元素的个数/集合的长度0
```

注意点：Collection是一个接口，我们不能直接创建他的对象，所有，学习他的方法的时候，只能创建他实现类的对象

1.添加元素(add):

细节1：往List系列集合添加数据，那么方法永远返回true，因为List系列是允许元素重复

细节2：往Set系列集合添加数据，如果元素不存在，返回true，表示添加成功

​                                                          如果元素存在，返回false，表示添加失败，因为Set不允许重复

2.删除(remove)

细节1：因为Collection里面定义的是共性的方法，所以此时不能通过索引进行删除，只能通过元素的对象进行删除

细节2：方法会有一个布尔类型的返回值，删除成功返回true，删除失败返回false，删除的元素不存在，就会删除失败

3.判断元素是否包含(contains)

细节：底层是依赖equals方法进行判断是否存在，所以，如果集合中存储的是自定义对象，也想通过contains方法来判断是否包含，那么在Javabean类中，一定要重写equals方法，因为contains方法在底层依赖Object方法进行判断，Object的equals方法是判断的内存地址

### Collection的遍历方式

#### 迭代器遍历

迭代器不依赖索引

迭代器在Java中的类是Iterator，迭代器是集合专用的遍历方式

Collection集合获取迭代器

```java
Iterator<E> iterator()     返回迭代器对象，默认指向当前集合的0索引
```

Iterator中的常用方法

```java
boolean hasNext()           判断当前位置是否有元素，有元素返回true，没有元素返回false
E next()                    获取当前位置元素，并将迭代器对象移向下一个位置
void remove()               从迭代器指向的collection中移除迭代器返回的最后一个元素(可选操作)
```

迭代器就好比一个箭头，默认指向集合0索引位置

细节注意点：

1. 如果当前位置没有元素，还要强行获取，会报错NoSuchElementException(没有这个元素异常)
2. 迭代器遍历完毕，指针不会复位
3. 循环中只能用一次next方法(如果我们要第二次遍历，需要再次获取一个新的迭代器对象)
4. 迭代器遍历时，不能用集合的方法进行增加或者删除

#### 增强for遍历

- 增强for的底层就是迭代器，为了简化迭代器的代码书写
- 它是JDK5之后出现的，其内部原理就是一个Iterator迭代器
- 所有的单列集合和数组才能用增强for进行遍历

格式：

```java
for(元素的数据类型 变量名：数组或者集合){
    
}
案例：
for(String s : list){
    sout(s);
}
```

快速生成方式：集合名字+for回车

增强for的细节

- 修改增强for中的变量，不会改变集合中原本的数据，他只是一个第三方变量

#### Lambda表达式遍历

得益于JDK8开始的新技术Lambda表达式，提供了一种更简单、更直接的遍历集合的方式

```java
default void forEach(Consumer<? super T> action):           结合lambda遍历集合
```

底层原理：其实也会自己遍历集合，依次得到每一个元素，把得到的每一个元素，传递给下面的accept方法，s依次表示集合中的每一个数据

forEach方法的底层其实就是一个循环遍历。依次得到及合中得到每一个元素，并把每一个元素传递给下面的accept方法，accept方法的形参s，依次表示集合中的每一个元素

## List集合的特有方法

- Collection的方法List都继承了
- List集合因为有索引，所以多了很多索引操作的方法

```java
void add(int index,E element)       在此集合中的指定位置插入指定的元素
E remove(int index)                 删除指定索引处的元素，返回被删除的元素
E set(int index,E element)          修改指定索引处的元素，返回被修改的元素
E get(int index)                    返回指定索引的元素
```

List也是一个接口

add细节：原来索引会依次往后移

remove细节：如果remove里面是1，list里面0索引的值为1，删除的是索引还是值，因为在调用方法的时候会出现重载现象，优先调用实参和形参类型一致的那个方法，默认是调用索引删除，如果想删除元素，要手动封装，因为remove不会自动装箱

### List集合的遍历方式

迭代器遍历

列表迭代器遍历

增强for遍历

Lambda表达式遍历

普通for遍历(因为List集合存在索引)

列表迭代器

ListIterator

```java
前面迭代器的所有方法
boolean hasPrevious        逆向遍历列表
E previous                 返回列表的前一个元素
int previousIndex          返回列表前一个元素的索引
```

五种遍历方式对比:

迭代器遍历：在遍历过程中需要删除索引，请使用迭代器

列表迭代器：在遍历过程中需要添加元素，请使用列表迭代器

增强for遍历：仅仅想遍历，那么使用增强for或者Lambda表达式

Lamdba表达式：

普通for：如果遍历想操作索引，就用普通for

## 数据结构

数据结构概述

数据结构就是计算机存储、组织数据的方式

是指数据互相之间是以什么方式排列在一起的

数据结构是为了更加方便的管理和使用数据，需要结合具体的业务场景来进行选择

一般情况下，精心选择的数据结构可以带来更高的允许或者存储效率

常见的数据结构

1. 栈
2. 队列
3. 数组
4. 链表
5. 二叉数
6. 二叉查找数
7. 平衡二叉树
8. 红黑树

### 栈

栈的特点：后进先出，先进后出

一端开口：栈顶     一端封闭：栈底

数据进入栈模型的过程称为：压/进栈

数据离开栈模型的过程称为：弹/出栈

最上面的元素称为：栈顶元素

最下面的元素称为：栈底元素

![](C:\Users\LL\Desktop\Java笔记\栈.jpg)

### 队列

队列的特点：先进先出，后进后出

一端开口：后端

一端开头：前端

数据从后端进入队列模型的过程称为：入队列

数据从前端离开队列模型的过程称为：出队列

入队列的过程是从后端进去的

出队列的过程是从前端进去的

![](C:\Users\LL\Desktop\Java笔记\队列.jpg)

笑话：

![](C:\Users\LL\Desktop\Java笔记\栈和队列笑话.jpg)

### 数组

数组是一种查询快，增删慢的模型

- 查询速度块：查询数据通过地址值和索引定位，查询任意数据耗时相同(元素在内存中是连续存储的)
- 删除效率低：要将原始数据删除，同时后面每个数据前移
- 添加效率极低：添加位置后的每个数据后移，再添加元素

### 链表

链表的每一个元素称为结点

![](C:\Users\LL\Desktop\Java笔记\结点.jpg)

第一个创建出来的称为头结点，如果后面没有结点，就存储空地址

链表中的结点是独立的对象，在内存中是不连续的，每个结点包含数据值和下一个结点的地址

链表查询慢，无论查询哪个数据都要从头开始找

链表的增删相对快

特点：链表是一个增删比较快的模型(对比数组)

总结：链表中的结点是独立的对象，在内存中是不连续的，每个结点包含数据值和下一个结点的地址。链表查询慢，无论查询哪个数据都要从头开始找

![双链表](C:\Users\LL\Desktop\Java笔记\双链表.jpg)

## ArrayList

底层原理：

1. 利用空参创建集合，在底层创建一个默认长度为0的数组(这俄格数组名字叫elementDate)
2. 添加第一个元时，底层会创建一个新的长度为10的数组
3. 存满时，会扩容1.5倍
4. 如果一次添加多个元素，1.5倍还放不下，则新创建数组的长度以时机为准(例：添加100个元素，新数组长度：110)

alt+7：大纲视图

![](C:\Users\LL\Desktop\Java笔记\ArrayList添加长度1.jpg)

![](C:\Users\LL\Desktop\Java笔记\ArrayList2添加长度2.jpg)

## LinkedList集合

- 底层数据结构是双链表，查询慢，增删快，但是如果操作的是首尾元素，速度也是极快的
- LinkedList本身多了很多直接操作首位元素的特有API 

````java
public void addFirst(E e)                  在该列表开头插入指定的元素
public void addLast(E e)                   将指定的元素追加到此列表的末尾
public E getFirst()                        返回此列表中的第一个元素
public E getLast()                         返回此列表中的最后一个元素
public E removeFirst()                     从此列表中删除并返回第一个元素
public E removeLast()                      从此列表中删除并返回最后一个元素
````

LinkedList底层源码

![](C:\Users\LL\Desktop\Java笔记\LinkedList.jpg)

## 迭代器底层源码：

![](C:\Users\LL\Desktop\Java笔记\集合迭代器.jpg)

## 泛型深入

泛型：是JDK5中引入的特性，可以在编译阶段约束操作的数据类型，并进行检查

泛型的格式：<数据类型>

注意：泛型只支持引用数据类型

 没有泛型的时候，集合如何存储数据

结论：

如果我们没有集合指定类型，默认认为所有的数据都是Object类型，此时可以往集合添加任意的数据类型，但是带来一个坏处：我们在获取数据的时候，无法使用他的特有行为

泛型的好处

- 统一数据类型
- 把允许期间的问题提前到了编译期间，避免了强制类型转换可能出现的异常，因为在编译器阶段类型就能确定下来

扩展知识点：Java中的泛型是伪泛型(只在编译时期有效)

假设泛型是String，当添加数据的时候，java只是在门口检查了一下数据是否为String类型，是就添加成功，当添加到里面的时候，java还是会把他当作Object类，只不过当我们获取的时候java会按照泛型把Object强转

当Java文件编译成class文件的时候，泛型就会消失，这个叫泛型的擦除

泛型的细节：

- 泛型中不能写基本数据类型(因为基本数据类型不能转换成Object)
- 指定泛型的具体类型后，传递数据时，可以传入该类类型或者子类类型
- 如果不写泛型，类型默认是Object

### 泛型可以在很多地方进行定义

类后面        泛型类

方法上面    泛型方法

接口后面    泛型接口

#### 泛型类

使用场景：当一个类中，某个变量的数据类型不确定时，就可以定义带有泛型的类

```java
修饰符 class 类名<类型>{              例子：public class ArrayList<E>{
                                           创建该类对象时，E就确定类型
}                                       }
```

此处E可以理解为变量，但是不是用来记录数据的，而是记录数据的类型，可以写成:T、E、K、V等，这里E算一个数据类型，但则会个数据类型就相当于一个万能钥匙，什么都能传

在MyArrayList里面有参考

#### 泛型方法

方法中形参不确定时

方案1：使用类名后面定义的泛型                   所有方法都能用

方案2：在方法申明上定义自己的泛型           只有本方法能用

格式：

```java
修饰符<类型>返回值类型 方法名(类型 变量名){
    
}
例子：
public <T>void show(T t){
    
}
```

泛型接口

格式：

```java
修饰符 interface 接口名<类型>{
    
}

例子：
public interface List<E>{
    
}
```

重点：如何使用一个带泛型的接口

方式1：实现类给出具体类型

方式2：实现类延续泛型，创建对象时在确定

### 泛型的继承和通配符

- 泛型不具备继承性，但是数据具备继承性

弊端：利用泛型方法有一个小弊端，此时他可以接收任意的数据类型

但是，本方法虽然不确定类型，但是以后我希望只能传递Ye Fu Zi

此时需要用到泛型的通配符：

？表示不确定的类型(形参里面用了？前面修饰符后面就不需要加了)

他也可以进行类型的限定

？ extends E:表示可以传递E或者E所有的子类类型

？ super E:表示可以传递E或者E所有的父类类型

应用场景：

1. 如果我们在定义类、方法、接口的时候，如果类型不确定，就可以定义泛型类、泛型方法、泛型接口
2. 如果类型不确定，但是能知道以后只能传递某个继承体系中的，就可以泛型的通配符

## Vector

不用学了，早就被市场淘汰了，加纳

## 数据结构(树)

树里面的元素也叫节点(结点)(Node)

父节点，左子节点，右子节点

![](C:\Users\LL\Desktop\Java笔记\树的节点.jpg)

每个节点都是独立的对象，他会存储父节点地址，值，左子节点地址，右子节点地址，如果没有父节点或者子节点，地址就记为null

这个就称为二叉树，二叉树中，任意节点的<=2

度：每一个节点的子节点数量

树高：树的总层数

根节点：最顶层的节点

左子节点：左下方的节点

右子节点：右下方的节点

根节点的左子树：就是根节点左边部分的所有子节点

根节点的右子树：就是根节点右边部分的所有子节点

![](C:\Users\LL\Desktop\Java笔记\二叉数节点.jpg)

普通的二叉树的弊端：没有规律，查找起来效率慢

### 二叉查找树

二叉查找树，又称二叉排序树或者二叉搜索树

特点：

- 每一个节点上最多有两个子节点
- 任意节点左子树上的值都小于当前节点
- 任意节点右子树上的值都大于当前节点

#### 添加节点：

规则：小的存左边，大的存右边，一样的不存

#### 查找结点：

根据大小查找左右节点，比他小查左，大查右

#### (二叉树)遍历方式

1. 前序遍历
2. 中序遍历
3. 后序遍历
4. 层序遍历

##### 前序遍历

从根节点开始，然后按照当前节点，左子节点，右子节点的顺序遍历

![](C:\Users\LL\Desktop\Java笔记\前序遍历.jpg)

##### 中序遍历(重要)

从最左边的子节点开始，然后按照左子节点，当前节点，右子节点的顺序遍历(获取出来的数据是从小到大的顺序)

![](C:\Users\LL\Desktop\Java笔记\中序遍历.jpg)

##### 后序遍历

从最左边的子节点开始，然后按照左子节点，右子节点，当前节点的顺序遍历

![](C:\Users\LL\Desktop\Java笔记\后序遍历.jpg)

##### 层序遍历

从根节点开始一层一层的遍历

![](C:\Users\LL\Desktop\Java笔记\层序遍历.jpg)

##### 总结

![](C:\Users\LL\Desktop\Java笔记\二叉树遍历总结.jpg)

### 平衡二叉树

规则：任意节点左右子树高度差不超过1

true

![](C:\Users\LL\Desktop\Java笔记\平衡二叉树true.jpg)

false

![](C:\Users\LL\Desktop\Java笔记\平衡二叉树false.jpg)

### (树)的演变

![](C:\Users\LL\Desktop\Java笔记\树的演变.jpg)

#### (平衡二叉树)旋转机制

规则1：左旋

规则2：右旋

触发时机：当添加一个节点之后，该树不再是一颗平衡二叉树

如果添加节点后，他还是一颗平衡二叉树，他就不会触发旋转

##### 左旋

确定支点：从添加的节开始，不断的往父节点找不平衡的节点

简单的左旋，支点没有左子节点时候

步骤：

1. 以不平衡的点作为支点
2. 把支点左旋降级，变成左子节点
3. 晋升原来的右子节点

有左子节点的左旋：

步骤(重要)

1. 以不平衡的点作为支点
2. 将根节点的右侧往左拉
3. 原先的右子节点变成新的父节点，并把多余的左子节点出让，给已经降级的根节点当右子节点

##### 右旋

和左旋差不多就是反过来

简单的步骤：

1. 以不平衡的点作为节点
2. 把支点右旋降级，变成右子节点
3. 晋升原来的左子节点

完整步骤

1. 以不平衡的点作为支点
2. 就是将根节点的左侧往右拉
3. 原先的左子节点变成新的父节点，并把多余的右子节点出让，给已经降级的根节点当左子节点

##### (平衡二叉树)需要旋转的四种情况

1. 左左
2. 左右 
3. 右右   
4. 右左

###### 左左

当根节点左子树的左子树有节点插入，导致二叉树不平衡

只需要一次右旋即可

###### 左右

当根节点左子树的右子树有节点插入，导致二叉树不平衡

先局部左旋，在整体右旋

###### 右右

当根节点右子树的右子树有节点插入，导致二叉树不平衡

只需要右旋一侧即可

###### 右左

当根节点右子树的左子树有节点插入，导致二叉树不平衡

先局部右旋，在整体左旋

### 红黑树

- 红黑树是一种平衡的二叉查找树，是计算机科学中用到的一种数据结构
- 1972年出现，当时被称之为平衡二叉B树，后来，1978年被修改为如今的“红黑树”
- 它是一种特殊的二叉查找树，红黑树的每一个节点上都要存储位表示节点的颜色
- 每一节点可以是红或者黑；红黑树不是高度平衡的，它的平衡是通过"红黑规则"进行实现的

简单理解：

- 是一个二叉查找树；
- 但是不是高度平衡的
- 条件：特有的红黑规则

#### 红黑规则

1. 每一个节点或是红色的，或者是黑色的
2. 根节点必须是黑色
3. 如果一个节点没有子节点或者父节点，则该节点相应的指针属性值为Nil，这些Nil视为叶节点，每个叶节点(Nil)是黑色的
4. 如果某一个节点是红色，那么它的子节点必须是黑色的(不能出现两个红色节点相连的情况)
5. 对每一个节点，从该节点到其所有后代(也就是它的子树)叶节点的简单路径(也就是只能往前走，不能后退)上，均包含相同数目的黑色节点

节点里面记录：父节点地址，值，左子节点地址，右子节点地址，颜色；(如果没有右子节点或者左子节点，就记录Nil)，者个NIl就表示空，但是也会在这个节点挂两个节点，这两个节点就叫叶节点，只不过这些叶节点是没有数据的

#### 添加节点规则

默认颜色：添加节点默认是红色的(效率高)

![](C:\Users\LL\Desktop\Java笔记\红黑树添加节点规则.jpg)

在进行判断是个坑，一定要记住是以当前节点进行判断，根据前面条件把谁设成当前节点，无论这个节点变在哪去了，后面进行判断的时候，一定是用他来判断

例子：

添加20，18，23，22，17，24，19，15，14

叔叔就是和你父亲同一个父亲(祖父/爷爷)的节点

在红黑树里面左旋和右旋不需要考虑叶节点

![](C:\Users\LL\Desktop\Java笔记\红黑树添加节点例子1.jpg)

例子：

添加20，18，23，22，17，24，19，15，16

![](C:\Users\LL\Desktop\Java笔记\红黑树添加节点例子2.jpg)

最后：

红黑树增删改查的性能都很好

## Set系列集合

- 无序：存取顺序不一致
- 不重复：可以去除重复
- 无索引：没有带索引的方法，所以不能使用普通for循环遍历，也不能通过索引来获取元素

Set集合的实现类

- HashSet：无序、不重复、无索引
- LinkedHashSet：有序、不重复、无索引
- TreeSet：可排序、不重复、无索引

Set接口中的方法上基本上与Collection的API一致

### HashSet

底层原理：

- HashSet集合底层采取哈希值存储数据
- 哈希表是一种对于增删改查数据性能都较好的结构

哈希表组成

- JDK8之前：数组+链表
- JDK8之后，数据+链表+红黑树

#### 哈希值

哈希值：对象的整数表现形式

哈希表添加值公式

int index = (数组长度-1) & 哈希值;

- 根据hashCode方法算出来的int类型的整数
- 该方法定义在Object类中，所有对象都可以调用，默认使用地址值进行计算
- 一般情况下，会重写hashCode方法，利用对象内部的属性值计算哈希值

对象的哈希值特点

- 如果没有重写hashCode方法，不同对象计算出的哈希值是不同的
- 如果已经重写了hashCode方法，不同的对象只要属性值相同，计算出的哈希值就是一样的
- 在小部分情况下，不同的属性值或者不同的地址值计算出来的哈希值也有可能一样(哈希碰撞)(概率不高)

#### HashSet底层原理

- 1. 创建一个默认长度16，默认加载因子0.75(扩容时机)的数组，数组名table

  2. 根据元素的哈希值根数组长度计算出应存入的位置（int index = (数组长度-1) & 哈希值）

  3. 判断当前位置书否为null，如果null直接存入

  4. 如果位置不为null，表示有元素，则调用equals方法比较属性值

  5. 一样：不存     不一样：存入数组，形成链表

     JDK8以前：新元素存入数组，老元素挂在新元素下面

     JDK8以后：新元素直接挂在老元素下面

- 当数组存储16*0.75 = 12元素的时候，数组就会扩容，扩成原先的两倍

- JDK8以后，当链表长度大于8而且数组长度大于等于64的时候，链表会自动转成红黑树

- 如果集合中存储的是自定义对象，必须要重写hashCode和equals方法

#### HashCSet的三个问题

问题1：HashSet为什么存和取的顺序不一样

HashSet是从链表0索引开始一个链表一个链表开始遍历的，添加的时候存和取的顺序是不一样的

问题2：HashSet为什么没有索引

因为HashSet里面包含链表，所以没有所索引

问题3：HashSet是利用什么机制保证数据去重的

利用HashCode方法的到哈希值和equals方法比较对象内部数据

案例：利用HashSet集合去除重复元素在A03_HashSetDemo2

#### LinkedHashSet

底层原理：

- 有序、不重复、无索引
- 这里的有序指的是保证存储和取出的元素顺序一致
- 原理：底层数据结构依然是哈希表，只是每个元素又额外的多了一个双链表的机制记录存储的顺序

LinkedHashSet遍历是遍历双向链表

![](C:\Users\LL\Desktop\Java笔记\LinkedHashSet底层原理.jpg)

LinkedHashSet链表长度大于8而且数组长度大于等于64的时候，链表并**不会**自动转成红黑树

### TreeSet

特点：

- 不重复、无索引、可排序
- 可排序：按照元素的默认规则(从小到大)排序
- TreeSet集合底层是基于红黑树的数据结构实现排序的，增删改查性能都较好

TreeSet对象排序练习题

#### TreeSet集合默认的规则

- 对于数值类型：Integer，Double，默认按照从小到大的顺序进行排序
- 对于字符、字符串类型：按照字符在ASCll码表中的数字升序进行排序的

#### TreeSet的两种比较方式

##### 方式一：

默认排序/自然排序：Javabean类实现Comparable接口指定比较规则

![](C:\Users\LL\Desktop\Java笔记\TreeSet比较方式1.jpg)

##### 方式二：

比较器排序：创建TreeSet对象时候，传递比较器Comparator指定规则

使用原则：默认使用第一种，如果第一种不能满足当前需求，就使用第二种

[案例](E:\ideacode\myvessel\src\com\yhr\a06myset\A06_TreeSetDemo3.java)

细节：如果方式一和方式二同时存在，以方式二为准

返回值的特点

- 负数：表示当前要添加的元素是小的，存左边
- 正数：表示当前要添加的元素是大的，存右边
- 0：表示当前要添加的元素已经存在，舍弃

## 使用场景

1. 如果想要集合的元可重复
   - 用ArrayList集合，基于数组的（用的最多）
2. 如果想要集合中的元素可重复，而且当前的增删操作明显多于查询
   - 用LinkedList集合，基于链表的
3. 如果相对集合中的元素去重
   - 用HashSet集合，基于哈希表的（用的最多）
4. 如果想对集合的元素去重，而且保证存取顺序
   - 用LinkedHashSet集合，基于哈希表和双链表，效率低于HashSet
5. 如果想对集合中的元素进行排序
   - 用TreeSet集合，基于红黑树。后续也可以用List集合实现排序

## 双列集合

左边一列称为键，右边称为值

![](\双列集合的特点.jpg)

特点：

1. 双列集合一次需要存一对数据，分别为键和值
2. 键不能重复，值可以重复
3. 键和值是一一对应的，每一个键只能找到自己对应的值
4. 键+值这个整体我们称之为“键值对”或者“键值对对象”，在Java中叫做“Entry对象”

双列集合体系结构：

Map

1. HashMap
   - LinkedHashMap
2. Hashtable
   - Properties
2. TreeMap

### Map的常见API

Map是双列集合的顶层接口，它的功能是全部双列集合都可以继承使用

```java
V put (K key,V value)                  添加元素
V remove(Object key)                   根据键删除键值对元素
void clear()                           移除所有的键值对元素
boolean containsKey(Object key)        判断集合是否包含指定的键
boolean containsValue(Object value)    判断集合是否包含指定的值 
boolean isEmpty()                      判断集合是否为空
int size()                             集合的长度，也就是集合中键值对的个数
V get(Object key)                      返回指定键所映射的值；如果此映射不包含该键的映射关系，则返回 null。 
```

put细节：

他有两个功能，添加/覆盖

在添加数据的时候，如果键不存在，那么直接把键值对对象添加到map集合中，方法返回null

在添加数据的时候，如果键存在，那么会把原有的键值对对象覆盖，会把被覆盖的值进行返回

### Map的遍历方式

1. 键找值
2. 键值对
3. Lambda表达式

在mymap里面

#### 键找值

1. 获取所有的键，把这些键放到一个单列集合当中
2. 遍历单列集合，得到每一个键
3. 利用map集合中的键获取对应的值(get)

#### 键值对

通过键值对对象进行遍历的

Map.entrySet

```java
boolean equals(Object o)           比较指定对象与此项的相等性。 
K getKey()           返回与此项对应的键。 
V getValue()           返回与此项对应的值。 
int hashCode()           返回此映射项的哈希码值。 
V setValue(V value)           用指定的值替换与此项对应的值（可选操作）
```

1. 通过一个方法获取所有的键值对对象，返回一个Set集合   Set<Map.Entry<String,String>> entries = map.entrySet();(Entry前面的Map可以省略，但是要导包import java.util.Map.Entry)
2. 遍历entries这个集合，去得到里面的每一个键值对对象
3. 利用entry调用get方法获取键和值

#### Lambda表达式

```java
default void forEach(BiConsumer<? super k,? super v> action)         结合lambda遍历Map集合
```

底层逻辑：

1. forEach其实就是用第二种方式键值对进行遍历，依次得到每一个键和值
2. 在调用accept方法

### HashMap

#### 特点

1. HashMap是Map里面的一个实现类
2. 没有额外需要学习的特有方法，直接使用Map里面的方法就可以了
3. 特点都是由键决定的：无序、不重复、无索引值得都是键
4. HashMap根HashSet底层原理是一摸一样的，都是哈希表结构

#### 底层原理

1. 底层创建和HashSet一样的数组
2. 利用put方法添加数据
3. put底层首先会创建一个Entry对象，Entry对象里面记录的是键和值，利用键计算哈希值，跟值无关(只要键的哈希值)
4. 然才存入计算的索引，如果为null，就直接存入，如果不为null，会调用equals方法比较**键**的属性值，如果一样就覆盖，不一样就和HashSet一样JDk7以前和JDK8以后，这个满足规则也会变成红黑树

#### 总结：

1. HashMap底层是哈希表结构

2. 依赖HashCode方法和equals方法保证**键**的唯一

3. 如果键存储的是自定义对象，需要重写hashCode和equals方法

   如果值存储自定义对象，不需要重写hashCode和equals方法

#### 练习一

存储学生对象并遍历

参考A05_MapDemo5

核心点：

HasMap的键位置如果存储的是自定义对象，需要重写hashCode和equals方法

#### 练习二

Map集合案例-统计投票人数

参考A06_MapDemo6

### LinkedHashMap

- 由键决定：有序、不重复、无索引
- 这里的有序指的是保证存储和取出的元素顺序一样
- 原理：底层数据结构依然是哈希表，只是每个键值对元素又额外的多了一个双链表机制记录存储的顺序

### TreeMap

- TreeMap跟TreeSet底层原理一样，都是红黑树结构的
- 由键决定特性：不重复，无索引、可排序
- 可排序：对键进行排序
- 注意：默认按照键的从小打到进行排序，也可以自己规定键的排序规则

代码书写两种排序规则

- 实现Comparable接口，指定比较规则
- 创建集合时传递Comparator比较器对象，指定对象规则

#### TreeMap综合练习

[练习](E:\ideacode\mymap\src\com\yhr\a04mytreemap\A08_TreeMapDemo8.java)

### Java源码小细节

1. 蓝色的圈c(class)表示一个类
2. 红色的圈m(method)表示方法：判断是构造还是成员：名字和类名相同的就是构造，不是就成员
3. 有的方法有箭头和灰色的字体，向上的箭头就是重写的父类或接口的方法，箭头后面灰色的字体就是他父类或者箭头的名称
4. 有的方法本身都是灰色的，就表示我们用鼠标点击这个方法，他就会跳转他的父类，向右箭头就表示这个方法就表示来自于哪个类或接口
5. 黄色圆圈f(field)，表示它的属性，有可能是成员变量，也有可能是常量
6. 绿色的i(interface)就是接口

### HashMap源码分析

HashMap每一个原素都是个Node对象，他实现了Entry接口，所以我们常说键值对是一个Entry对象

![](C:\Users\LL\Desktop\Java笔记\HashMap源码1.jpg)

这里next就代表链表的下一个地址值

当链表变为红黑树后，就是TreeNode了，他是继承Entry，然后继承Node的

HashMap的底层数组的数组源码叫table，用空参构造创建HashMap对象，他只会把默认加载因子0.75赋值给loadFactor，底层数组并没有创建，他只会在put的时候创建，put方法会调用putVal，他有五个参数，第一个是计算出的哈希值，第二个是键，第三个是值，第四个表示当前数据是否保留，第五个现在不用知道

详细见Map文件夹中的HashMap文件，最好用Notepad++打开

数组里面的对象是分情况的，原本是链表，达到规则后就是红黑树

### Map集合用谁

1. 默认：HashMap（效率最高）
2. 如果要保证存取有序：LinkedHashMap
3. 如果要进行排序：TreeMap

## 可变参数

JDK5提出的

方法形参的个数是可以发生变化的

格式

属性类型...名字

例子：int...args(名字，随便什么都行)

小细节：

1. 在方法中的形参最多只能写一个可变参数
2. 方法中，如果出了可变参数以外，还有其他的形参，那么可变参数要写在最后

## Collections

- java.util.Collections:是集合工具类
- 作用：Collections不是集合，而是集合的工具类

Collections常用的API

```java
public static <T> boolean addAll(Collection<T> c,T...elements)   批量添加元素(只能单列集合)
public static void shuffle(List<?> list)                         打乱List集合元素的顺序(只能list)
public static <T> void sort(List<T> list)                        排序
public static <T> void sort(List<T> list, Comparator<T> c)       根据指定的规则进行排序
public static <T> int binarySearch(List<T> list,T key)           以二分查找法查找元素
public static <T> void copy(List<T> dest,List<T> stc)            拷贝集合中的元素(把stc拷贝到dest)
public static <T> int fill(List<T> list,T obj)                   使用指定的元素填充集合
public static <T> void max/min(Collection<T> coll)               根据默认的自然安排徐获取最大/小值
public static <T> swap(List<?> list, int i, int j)               交换集合中指定位置的集合
```

sort:默认从大到小，可以自己实现Comparable接口和CompareTo方法

binarySearch:查找的集合要有序

copy：如果list1>list2的长度，方法会报错，所以我们可以把list2全赋值为0，保证长度一样

max/min:可以自己指定规则

## 综合练习

[练习](E:\ideacode\mymap\src\com\yhr\a08test\Test1.java)

Random是随机点，如何随机面，参考上面的链接

### 微服务技术：

![](C:\Users\LL\Desktop\Java笔记\微服务器技术.jpg)

### 斗地主：

[源码]()

步骤：(控制台)

1. 准备牌
2. 洗牌
3. 发牌
4. 给牌排序
   - 方式1：用序号排
     - 如果原始数据的规律非常复杂，我们可以手动排序让每一个数据跟唯一的序号产生对应关系
     - 序号就是数字，规律非常简单，后续的所以操作，我们以序号为准
     - 当真正需要操作原始数据时，在通过序号找出原始数据即可
   - 方式二给每一张牌计算价值(积分/得分)

## 创建不可变的集合

不可变集合：不能被修改的集合(包括长度和内容)

### 应用场景

- 如果某个数据不能被修改，把它防御性的拷贝到不可变集合中是个很好的实践
- 当集合对象被不可信的库调用时，不可变形式是安全的

简单理解：不想让别人修改集合中的内容

### 创建不可变集合的格式

在LIst,Set,Map接口中，都存在静态的of方法，可以获取一个不可变的集合

```java
static<E> List<E> of(E...elements)      创建一个具有指定索引的List集合对象
static<E> Set<E> of(E...elements)       创建一个具有指定元素的Set集合对象
static<K,V> Set<K,V> of(E...elements)   创建一个具有指定元素的Map集合对象(上限元素20，对象10)
static <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries)   创建一个具有指定元素的Map集合元素(上限多于对象10)
```

注意：

1. (所有集合)这个集合不能添加，不能删除，不能修改
2. (Set)当我们获取一个不可变的Set集合时，里面的参数要保证数据的唯一性
3. (Map)Map集合键是不能重复的
4. (Map)Map的of是有上限的，最多可以传递20个参数，10个键值对对象
5. (Map)如果我们要传递多个键值对对象，数量大于10，在Map接口中还有一个方法
6. (Map的entrySet)toArray方法在底层会比较集合的长度，如果集合的长度>数组的长度：数据在数组中放不下，此时会根据实际的数据个数11，更新创建数组，如果集合的长度<=数组的长度：数据在数组中放的下，此时不会创建新的数组，而是直接用([代码](E:\ideacode\mycontainer\src\com\yhr\a01immutable\a04ImmutableDemo4.java))

有个更简单的Map不可变集合，JKD10以后出现的

```java
copyOf()
例子：Map<String, String> map = Map.copyOf(hm);
```

# 集合补充

## 线程安全

线程安全的集合是指在多线程环境下，能够保证数据一致性和正确性的集合类。Java 提供了多种线程安全的集合，主要分为以下几类：

### 同步集合类（早期实现）

- **Vector**
  - 线程安全的动态数组，所有方法都使用 `synchronized` 关键字同步，锁住整个对象。
  - 适用于读多写少的场景，但性能较低。

- **Stack**
  - 继承自 `Vector`，线程安全的栈，方法同步机制与 `Vector` 相同。

- **Hashtable**
  - 线程安全的哈希表，所有方法使用 `synchronized` 同步，锁住整个对象。
  - 性能在多线程环境下较低。

### 并发包中的高效线程安全集合（`java.util.concurrent`）

- **ConcurrentHashMap**
  - 线程安全的哈希表，采用分段锁（Java 7）或 `CAS + synchronized`（Java 8+）优化锁粒度。
  - 支持高并发的读写操作，性能优于 `Hashtable`。

- **CopyOnWriteArrayList**
  - 线程安全的列表，采用写时复制机制。
  - 读操作无需加锁，写操作会复制一份新的数组进行修改，适用于读多写少的场景。

- **ConcurrentLinkedQueue**
  - 线程安全的无界队列，基于链表实现，使用 `CAS` 操作保证线程安全。
  - 高并发下性能优异，适用于高并发的队列操作。

- **ArrayBlockingQueue**
  - 线程安全的有界阻塞队列，基于数组实现，遵循先进先出（FIFO）原则。
  - 队列满时，添加操作阻塞；队列空时，移除操作阻塞。

- **LinkedBlockingQueue**
  - 线程安全的可选有界/无界阻塞队列，基于链表实现。
  - 默认无界，性能优于 `ArrayBlockingQueue`，适用于生产者和消费者模式。

- **PriorityBlockingQueue**
  - 线程安全的无界阻塞队列，支持优先级排序。
  - 元素按照优先级出队，适用于需要按优先级处理任务的场景。

- **SynchronousQueue**
  - 特殊的阻塞队列，不存储元素。
  - 每个插入操作必须等待另一个线程的移除操作，反之亦然，适用于线程池任务传递。

### 同步包装器（`Collections` 工具类）

- **`Collections.synchronizedList(List list)`**
  - 将非线程安全的列表包装成线程安全的列表，所有方法使用 `synchronized` 同步。

- **`Collections.synchronizedMap(Map map)`**
  - 将非线程安全的映射表包装成线程安全的映射表，所有方法同步。

### 使用建议

- **性能考虑**：在高并发环境下，优先选择并发包中的集合类，如 `ConcurrentHashMap`、`CopyOnWriteArrayList`，它们通过优化锁机制或采用无锁算法，提供更好的性能。

- **场景匹配**：根据应用场景选择合适的集合类，例如读多写少选择 `CopyOnWriteArrayList`，需要阻塞队列选择 `ArrayBlockingQueue` 或 `LinkedBlockingQueue`。

- **线程安全级别**：注意同步集合类的锁粒度，避免因锁竞争导致性能下降。

- **组合操作**：使用同步包装器时，需要注意组合操作（如遍历、条件判断）的线程安全性，可能需要显式加锁。

---

通过选择合适的线程安全集合类，可以有效避免多线程环境下的数据竞争和不一致问题，提高程序的稳定性和性能。

# Stream流

## 作用：

结合了Lambda表达式，简化集合、数组的操作

Stream流的使用步骤：

1. 先得到一条Stream流(流水线),并把数据放上去
2. 利用Stream流中的API进行各种操作
   - 过滤  转换   中间方法 : 方法调用完毕之后，还可以调用其他方法
   - 统计  打印   终结方法 : 最后一步，调用完毕之后，不能调用其他方法

## 操作步骤：

1. 先得到一条Stream流(流水线)，并把数据放上去

   ```java
   单列集合         default Stream<E>stream()                   Collection中的默认方法
   双列集合         无(需要通过KeySet()或者entrySet()来获取)       无法直接使用stream流
   数组            public static<T>Stream<T>stream(T[]array)   Arrays工具类中的静态方法
   一堆零散的数据    public static<T>Stream<T>of(T...values)     Stream接口中的静态方法(前提：需要同种数据类型)
   ```

   注意：

   Stream接口中静态方法of的细节

   方法的新参是一个可变参数，可以传递一些零散的数据，也可以传递数组

   但是数组必须是引用数据类型，如果传递基本数据类型，是会把整个数组当作一个元素，放到Stream当中

2. 使用中间方法对流水线上的数据进行操作

   ```java
   Stream<T>filter(Predicate<? super T>predicate)            过滤(函数式接口Predicate)
   Stream<T>limit(long maxSize)                              获取前几个元素
   Stream<T>skip(long n)                                     跳过前几个元素
   Stream<T>distinct()                                       元素去重，依赖(hashCode和equals方法,记得重写这两个方法)
   static<T>Stream<T>concat(Stream a,Stream b)               合并a和b两个流为一个流(数据类型尽可能保持一致，如果不一致，那么大类上的数据类型就是他们两个共同的父类)_
   Stream<R>map(Function<T,R> mapper)                        转换流中的数据类型
   ```

   注意1：中间方法，返回新的Stream流，原来的Stream流只能使用一次，建议使用链式编程,也就是不能复用，所以没必要用变量来记录

   注意2：修改Stream流中的数据，不会影响原来集合或者数组中的数据

   map的注释

   ```java
   第一个类型：流中原本的数据类型
   第二个类型：要转成之后的类型
   apply的新参s：依次表示流里面的每一个数据
   返回值：表示转换之后的数据
   当map执行完之后，流上面的数据就便成了整数，所以在下面forEach当作，s依次表示流里面的每一个数据，这个数据现在是整数了
   list.stream().map(new Function<String, Integer>() {
               @Override
               public Integer apply(String s) {
                   String[] arr = s.split("-");
                   String ageString = arr[1];
                   int age = Integer.parseInt(ageString);
                   return age;
               }
           }).forEach(s -> System.out.print(s+"\t"));
   //简化
   list.stream().map(s -> Integer.parseInt(s.split("-")[1])).forEach(s -> System.out.print(s+"\t"));
   ```

3. 使用终结方法对流水线上的数据进行操作

```java
void forEach(Consumer action)                    遍历
long count()                                     统计
toArray()                                        收集流中的数据，放到数组中
collect(Collector collector)                     收集流中的数据，放到集合中
```

```java
注解：
IntFunction的泛型：具体类型的数组(就是把Object变成我们想要的数据类型)
apply的形参：流中数据的个数，要更数组的长度保持一致
apply的返回值：具体类型的数组
方法体：就是创建数组
toArray方法的参数的作用：负责创建一个指定类型的数组
toArray方法的底层，会依次得到流里面的每一个数据，并把数据放到数组当中
toArray的返回值：是一个装着流里面所有数据的数组
list.stream().toArray(new IntFunction<? extends Object[]>() {
            @Override
            public Object[] apply(int value) {
                return new Object[0];
            }
        });
例子：
String[] arr1 = list.stream().toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });
Lambda表达式：
String[] arr2 = list.stream().toArray(value -> new String[value]);
```

## 收集方法：

collect：手机流中的数组，放到集合中(List，Set，Map)

[案列](E:\ideacode\mystream\src\com\yhr\a01mystream\StreamDemo10.java)

```java
 ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌-男-15","周芷若-女-14","赵明-女-20","张强-男-20","张三丰-男-100","张翠山-女-40","张良-男-35","王林-男-37","谢广坤-男-49");
//收集List集合当中
List<String> newList = list.stream().filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toList());
        System.out.println(newList);
//收集Set集合当中        
Set<String> newSet = list.stream().filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toSet());
        System.out.println(newSet);
//收集Map集合当中
/*
toMap:参数一表示键的生成规则
      参数二表示值的生成规则
参数一：
     Function泛型一：表示流中每一个数据的类型
             泛型二：表示Map集合中键的数据类型
     方法apply形参：依次表示流里面的每一个数据
            方法体：生成键的代码
            返回值：已经生成的键
参数二：
     Function泛型一：表示流中每一个数据的类型
             泛型二：表示Map集合中值的数据类型
     方法apply形参：依次表示流里面的每一个数据
            方法体：生成值的代码
            返回值：已经生成的值
*/
Map<String, Integer> newMap = list.stream().filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toMap(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.split("-")[0];
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s.split("-")[2]);
                    }
                }));
修改成Lambda表达式
Map<String, Integer> newMap1 = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2])
                ));
        System.out.println(newMap1);    
```

注意点：如果要收集到Map集合中，键是不能重复的，不然会报错

##  总结：

1. Stream流的作用

   结合Lambda表达式，简化集合、数组的操作

2. Stream的使用步骤

   - 获取Stream流对象
   - 使用中间方法处理数据
   - 使用终结方法处理数据

3. 如何获取Stream流对象

   - 单列集合：Collection中的默认方法stream
   - 双列集合：不能直接获取
   - 数组：Arrays工具类型中的静态方法stream
   - 一堆零散的数组：Stream接口中的静态方法of

4. 常见方法

   中间方法：filter，limit，skip，distinct，concat，map

   终结方法：forEach，count，collect

## 练习

[参考](E:\ideacode\mystream\src\com\yhr\a01test\Test1.java)

# 方法的引用

把已经有的方法拿过来用，当作函数式接口中抽象方法的方法体

引用条件：

1. 引用处必须是函数式接口
2. 被引用的方法必须已经存在
3. 被引用方法的形参和返回值需要跟抽象方法保持一致
4. 被引用方法的功能要满足当前需求

被引用的方法可以是Java写好的，也可以是一些第三方的工具类

例子：

```java
Arrays.sort(arr,FunctionDemo1::subtraction);
```

方法引用符号是 ::

## 方法引用的分类

1. 引用静态方法
2. 引用成员方法
   - 引用其他类的成员方法
   - 引用本来的成员方法
   - 引用父类的成员方法
3. 引用构造方法
4. 其他调用方法
   - 使用类名引用成员方法
   - 引用数组的构造方法

## 引用静态方法

格式：类名::静态方法

范例：Integer::parseInt

## 引用成员方法

格式：对象::成员方法

1. 其他类：其他类对象::方法名
2. 本类：this::方法名(细节：静态方法里面不能引用，因为静态方法里面没有this)
3. 父类：super::方法名(细节：引用处不能是静态方法)

之所以this和super不能被静态引用，因为静态不依赖于对象是本身的方法

## 引用构造方法

格式：类名::new

范例：Student::new

## 使用类名引用成员方法

格式：类名::成员方法

范例：String::substring

方法引用的规则：(这个独有的)

1. 需要有函数式接口
2. 被引用的方法必须已经存在
3. 被引用方法的形参，需要跟抽象方法的第二个形参到最后一个形参保持一致，返回值需要保持一致
4. 被引用方法的功能需要满足当前的需求

抽象方法形参的详解(3)：

第一个参数：表示被引用方法的调用者，决定了可以引用哪些类中的方法

在Stream流当中，第一个参数一般都表示流里面的每一个数据，假设流里面的数据是字符串，那么使用这种方式进行方法引用，只能引用String这个类中的方法

第二个参数到最后一个参数：跟被引用方法的形参保持一致，如果没有第二个参数，说明被引用的方法需要是无参的成员方法

[参考](E:\ideacode\myfunction\src\com\yhr\a01myfunction\FunctionDemo5.java)

例子：map(String::toUpperCase)拿着流里面的每一个数据，去调用String类中的toUpperCase方法，方法的返回值就是转换之后的结果

细节：不能引用所以类中的成员方法，如果抽象方法的第一个参数是A类型的，只能引用A类中的方法

## 引用数组的构造方法

格式：数据类型[]::new

范例：int[]::new

细节：数组中的数据需要跟流中的数据类型保持一致

## 练习

[参考](E:\ideacode\myfunction\src\com\yhr\a01myfunction\FunctionDemo7.java)

# 异常

定义：异常就是代表程序出现的问题

误区：不是让我们以后不出现异常，而是程序出了异常之后，该如何处理

java.lang.Throwable

1. Error(错误)
2. Exception(异常)
   - RuntimeException
     - .......
   - 其他异常

Error：代表的系统级别错误(属于严重问题)

系统一旦出现问题，sun公司会把这些错误封装成Error对象，Error是给sun公司自己用的，不是给我们程序员用的，因此我们开发人员不用管它

Exception：叫做异常，代表程序可能出现的问题，我们通常会用Exception以及他的子类来封装程序出现的问题

运行时异常：RuntimeException及其子类，编译阶段不会出现异常提醒，运行时出现的异常(如：数组索引越界异常)

编译时异常：编译阶段就会出现异常提醒的(如：日期解析异常)

异常的父类是Exception

为什么要区分异常？

解答：参考视频下的53的5分

Exception有很多异常，具体参考API帮助文档

## 区别

- 编译时异常：除了RuntimeException和他的子类，其他都是编译时异常，编译阶段需要进行处理，作用于提醒程序员
- 运行时异常：RuntimeException本身和所有子类，都是运行时异常，一般是由于参数传递错误导带来的问题

## 异常的作用

作用一：异常用来查询bug的关键参数信息

作用二：异常可以作为方法内部的一种特殊返回值，以便通知调用者底层的执行情况

## 异常处理方式

1. JVM(虚拟机)默认的处理方式
2. 自己处理
3. 抛出异常

### JVM默认的处理方式

- 把异常的名称，异常原因及异常的位置等信息输出在了控制台
- 程序停止执行，下面的代码不会在执行了

### 自己处理异常(捕获异常)

格式：

```java
try{
    可能出现异常的代码;
}catch(异常类名 变量名){
    异常的处理代码;
}
```

目的：当代码出现异常时，可以让程序继续往下执行

```java
例子
int[] arr = {1,23,4,5};
        try{
            //可能出现异常的代码
            System.out.println(arr[10]);//此处出现了异常，程序就会在这里创建一个ArrayIndexOutOfBoundsException对象
                                        //new ArrayIndexOutOfBoundsException();
                                        //拿着这个对象到catch的小括号中对比，看括号中的变量是否可以接收到这个对象
                                        //如果能被接收，就表示该异常就被捕获(抓住),执行catch里面对象的代码
                                        //当catch里面所有的代码执行完毕，继续执行try...catch体系下面的其他代码
        }catch (IndexOutOfBoundsException e){
            //如果出现了ArrayIndexOutOfBoundsException异常，我该如何处理
            System.out.println("索引越界异常");
        }
        System.out.println("看看我执行了吗");
```

### 捕获异常灵魂四问

#### 灵魂一问：

如果try中没有遇到问题，怎么执行？

会把try里面的代码全部执行完毕，不会执行catch里面的代码

#### 灵魂二问：

如果try中可能会遇到多个问题，怎么执行？

会写多个catch与之对应，用多个catch捕获多个异常，会在第一个异常跳出

细节：如果我们要捕获多个异常，这些异常中如果存在父子关系的话，那么父类一定要写在下面

因为所有异常都会被Exception接收，后面的异常都捕获不到

扩展：

JDK7以后，我们可以在catch中同时捕获多个异常，中间用|进行分隔，单个|哦，

表式如果出现了A异常或者B异常的话，采取同一个处理方案

#### 灵魂三问：

如果try中遇到的问题没有被捕获，怎么执行？

相当于try...catch的代码白写了，最终还是会交给虚拟机进行的

#### 灵魂四问：

如果try中遇到了问题，那么try下面的其他代码还会执行吗？

下面的代码就不会执行了，直接跳转到对应的catch当中，执行catch里面的语句体，但是如果没有对应的catch与之匹配，那么还是会交给虚拟机进行处理

![](C:\Users\LL\Desktop\Java笔记\异常的4问.jpg)

## 异常的常见方法

```java
public String getMessage()              返回此throwable的详细信息
public String toString()                返回此可抛出的简短描述
public void printStackTrace()           把异常的错误信息输出在控制台(细节：仅仅打印信息，不会停止虚拟机)
```

错误的输出语句(用来打印错误信息的)

```java
System.err.println();
```

## 抛出处理

1. throws

   注意：写在方法定义处，表示声明一个异常

   告诉调用者，使用本方法可能会有哪些异常

   ```java
   public void 方法() throws 异常类名1,异常类名2...{
       ...
   }
   ```

   - 编译时异常：必须要写
   - 运行时异常：可以不写

2. throw

   注意：写在方法内，结束方法，手动抛出异常对象，交给调用者

   方法中下面的代码不在执行了

   ```java
   public void 方法(){
       throw new NullPointterException();
   }
   ```

抛出：告诉调用者出错了

捕获：不让程序停止

## 自定义异常

1. 定义异常信息
2. 写继承关系
3. 空参构造
4. 带参构造

意义：就是为了让控制台的报错信息更加的见名知意

起名字技巧：
例子：NameFormatException

NameFormat：当前异常的名字，表示姓名格式问题

Exception：表示当前类是一个异常类

所有以后取名字尽量在后面加一个Exception的后缀

1. 运行时异常继承RuntimeException
2. 编译时异常继承Exception

# File

- File对象就表示一个路径，可以是文件的路径，也可以是文件夹的路径
- 这个路径可以数存在的，也允许是不存在的

```java
public File(String pathname)                          根据文件路径创建文件对象
public File(String parent, String child)              根据父路径名字符串和子路径名字符串创建文件对象
public File(File parent, String child)                根据父路径对应文件对象和子路径名字符串创建文件对象
```

例子：

C:\Users\alienware\Desktop\a.txt

父级路径：C:\Users\alienware\Desktop

子级路径：a.txt

绝对路径：带盘符的路径

相对路径：不带盘符的路径，默认到当前项目下去找

## File的常见方法

### 判断、获取

```java
public boolean isDirectory()                     判断此路径名表示的File是否为文件夹
public boolean isFile()                          判断此路径名表示File是否为文件
public boolean exists()                          判断此路径名表示的File是否存在
public long length()                             返回文件的大小(字节数量)(细节1：这个方法只能获取文件的大小，单位字节，细节2：这个方法无法获取文件夹的大小，不同操作系统返回不一样，有的返回0有的返回4096)
public String getAbsolutePath()                  返回文件的绝对路径
public String getPath()                          返回定义文件时使用的路径
public String get Name()                         返回文件的名称，带后缀
public long lastModified()                       返回文件的最后修改时间(时间毫秒值)
```

### 创建、删除

```java
public boolean createNewFile()                   创建一个新的空的文档
public boolean mkdir()                           创建单级文件夹
public boolean mkdirs()                          创建多级文件夹
public boolean delete()                          删除文件、空文件夹
```

重点：delete方法默认只能删除文件和空文件夹，delete方法直接删除不走回收站

createNewFile的细节：

1. 如果当前路径表示的文件是不存在的，则创建成功，方法返回true，如果当前路径表示的文件不存在，则创建失败，返回false
2. 如果父级路径不存在，那么方法会有异常IOException
3. createNewFilef方法创建的一定是文件，如果路径中不包含后缀名，那么会创建一个没有后缀名的文件

mkdir方法细节

1. windows当中路径是唯一的，如果当前路径已经存在，则创建失败，返回false
2. makdir方法只能创建单级文件夹，无法创建多级文件夹

mkdirs方法细节

1. 既可以创建多级文件夹，也可以创建单级文件夹

### 获取并遍历

```java
public File[] listFiles()                  获取当前路径下所有内容
```

重点：

- 当调用者File表示的路径不存在时，返回null
- 当调用者File表示的路径是文件时，返回null
- 当调用者File表示的路径是一个空文件夹时，返回一个长度为0的数组
- 当调用者FIle表示的路径是一个有内容的文件夹时，将里面所有文件和文件夹的路径放在File数组中返回
- 当调用者FIle表示的路径是一个有隐藏文件的文件夹时，将里面所有文件和文件夹放在File数组中返回，包含隐藏文件
- 当调用者File表示的路径是需要权限才能访问的文件或文件夹时，返回null

### 获取并遍历扩展

```java
public static File() listRoots()                              列出可以的文件系统根
public String[] list()                                        获取当前该路径下所有内容(仅仅获取名字) 
public String[] list(FilenameFiler filter)                    利用文件名过滤器获取当前该路径下所有内容
public File[] listFiles()(重点掌握)                            获取当前该路径下所有内容
public File[] listFiles(FileFilter filter)                    利用文件名过滤器获取当前该路径下所有内容
public File[] listFiles(FilenameFilter filter)                利用文件名过滤器获取当前该路径下所有内容
```

public String[] list(FilenameFiler filter) 细节：

accept方法的形参：依次表示aaa文件夹里面每一个文件或者文件夹的路径

参数一：父级路径

参数二：子级路径

返回值：如果返回true，就表示当前路径保留，如果返回false，就表示当前路径舍弃不要

## 综合练习

[案例](E:\ideacode\myfile\src\com\yhr\a02test\Test1.java)

思想：把大问题层层拆分，拆到某一个文件夹中不包含其他文件夹为止，也就是递归啦

套路：

1. 进入文件夹
2. 遍历数组
3. 判断
4. 判断

# IO流

## 概念

IO流：存储和读取数据的解决方案（i就是input，o就是output）

用于读写文件中的数据(可以读写文件，或网络中的数据)

## 分类

- 流的方向

  IO流

  1. 输入流（读取）
  2. 输出流（写出）

- 操作文件类型

  IO流

  1. 字节流（所有类型的文件）
  2. 字符流（纯文本文件）

纯文本文件：Windows自带的记事本打开能读懂

## IO流的体系结构

IO流体系

![](C:\Users\LL\Desktop\Java笔记\IO流体系.jpg)

![](C:\Users\LL\Desktop\Java笔记\IO流体系进一步.jpg)

## FileOutputStream

操作本地文件的字节输出流，可以把程序中的数据写到本地文件中

书写步骤：

1. 创建字节输出流对象
2. 写数据
3. 释放资源

细节：

1. 创建字节输出流对象

   细节1：参数是字符串表示的路径或者是File对象都是可以的

   细节2：如果文件不存在会创建一个新的文件，但是要保证父级路径是存在的

   细节3：如果文件已经存在，则会清空文件

2. 写数据

   细节：write方法的参数是整数，但是实际上写到本地文件夹中的是整数在ASCII上对应的字符

3. 释放资源

   每次使用完流之后都要释放资源

### FileOutputStream写数据的3中方式

| 方法名称                                                | 说明                         |
| ------------------------------------------------------- | ---------------------------- |
| void write(int b)                                       | 一次写一个                   |
| void write(byte[] b)                                    | 一次写一个字节数组数据       |
| void write(byte[] b , int off(起始索引), int len(个数)) | 一次写一个字节数组的部分数据 |

### FileOutputStream写数据的两个小问题

- 换行写

  再次写一个换行符就可以了

  windows：\r\n

  Linux：\n

  Mac：\r

  细节：

  在Windows操作系统中，java对回车换行进行了优化，虽然完整的是\r\n，但是我们写其中一个\r或者\n，java也可以实现换行，因为java在底层会补全

  建议：

  不要省略，还是写全更好

- 续写

  如果想要续写，打开续写开关即可，开关位置：创建对象的第二个参数

  默认false：表示关闭续写，此时创建对象会清空文件，手动传递true：表示打开续写，此时创建对象不会清空文件

## FileInputStream

操作本地文件的字节输入流，可以把本地文件中的数据读取到程序中来

书写步骤：

1. 创建字节输入流对象
2. 度数据
3. 释放资源

### FileInputStream细节

1. 创建字节输入流对象
   1. 细节1：如果文件不存在，就直接报错
2. 读取数据
   1. 细节1：一次读一个字节，读出来的是数据在ASCII上对应的数字
   2. 细节2：读到文件末尾了，read方法返回-1
3. 释放资源
   1. 细节1：每次使用完流要记得释放资源

### FileInputStream循环读取

参考代码

## 文件拷贝的基本代码

释放资源的规则：先开的流最后关闭

### 文件拷贝的弊端和解决方案

弊端：一次读写一个字节

一次读取多个

| 方法名称                                                     | 说明                   |
| ------------------------------------------------------------ | ---------------------- |
| public int read()                                            | 一次读一个字节数据     |
| public int read(byte[] buffer)(返回值：本次读取多少个字节数据) | 一次读一个字节数组数据 |

注意：一次读一个字节数组的数据，每次读取会尽可能把数组装满

1024的整数倍

习惯：1024 * 1024 * 5

读取后会覆盖前面的数据，如果每读满数组，就不会覆盖，就会那个位置就还是前一次读取的数据，如果不想要，就在形参里面加个0和len就是

读取细节参考视频下期的83

## IO流中不同JDK版本捕获异常的方式

try···catch异常处理

后面多个finally

try···catch···finally

finally特点：finally里面的代码一定被执行，除非虚拟机(JVM)停止

了解就行

简化：

接口：AutoCloseable

特点：特定的情况下，可以自动释放资源

![](C:\Users\LL\Desktop\Java笔记\IO流捕获异常.jpg)

注意：只有实现了AutoCloseable接口的类，才能在小括号中创建对象

## 字符集详解(ASCII,GBK)

### ASCII字符集

存储英文，一个字节就足以

ASCII编码规则：前面补0，补齐8位

ASCII编码规则：直接转成十进制

1. GB2312字符集
2. BIG5字符集
3. GBK字符集
4. Unicode字符集

4个详细意思见视频下期的86

widows系统默认使用的就是GBK     系统显示：ANSI

### GBK

#### 英文

要求：英文用一个字节存储，完全兼容ASCII码

#### 中文

规则1：汉字两个字节存储

前面8位是高位字节，后面则是低位字节

规则2：高位字节二进制一定以1开头，转成十进制之后是一个负数

核心1：GBK中，英文字母一个字节，二进制第一位为0

核心2：GBK中，英文字母两个字节，二进制第一位为1

### Unicode

兼容ASCII

UTF-16编码规则：用2~4个字节保存

UTF-32编码规则：固定使用四个字节保存

UTF-8编码规则：用1~4个字节保存

ASCII通常一个字节

简体中文3个字节

![](C:\Users\LL\Desktop\Java笔记\UTF-8编码规则.jpg)

![](C:\Users\LL\Desktop\Java笔记\UTF-8编码案例.jpg)

### 为什么有乱码

原因1：读取数据时未读完整个汉字

原因2：编码和解码的方式不统一

如何不产生乱码

1. 不要采用字节流读取文本文件
2. 编码解码时使用同一个码表，同一个编码方式

扩展

疑问：字节流读取中文会乱码，但是为什么拷贝不会乱码

## Java中编码和解码的代码实现

Java中编码的方法

| String类中的方法                           | 说明                 |
| ------------------------------------------ | -------------------- |
| public byte[] getBytes()                   | 使用默认方式进行编码 |
| public byte[] getBytes(String charsetName) | 使用指定方式进行编码 |

Java中解码的方法

| String类中的方法                        | 说明                 |
| --------------------------------------- | -------------------- |
| String(byte[] bytes)                    | 使用默认方式进行解码 |
| String(byte[] bytes,String charsetName) | 使用指定方式进行解码 |

## 字符流

字符流的底层就是字节流

字节流=字节流+字符集

特点：

输入流：一次读一个字节，遇到中文时，一次读多个字节

输出流：底层会把数据按照指定的编码方式进行编码，变成字节在写到文件中

使用场景

对于纯文本文件进行读写操作

![](C:\Users\LL\Desktop\Java笔记\字符流体系.jpg)

### FileReader

1.创建字符输入流对象

| 构造方法                           | 说明                       |
| ---------------------------------- | -------------------------- |
| public FileReader(File file)       | 创建字符输入流关联本地文件 |
| public FileReader(String pathname) | 创建字符输入流关联本地文件 |

细节1：如果文件不存在，就直接报错

2.读取数据

| 成员方法                       | 说明                         |
| ------------------------------ | ---------------------------- |
| public int read()              | 读取数据，读到末尾返回-1     |
| public int read(char[] buffer) | 读书多个数据，读到末尾返回-1 |

细节1：按字节进行读取，遇到中文，一次读多个字节，读取后解码，返回一个整数

细节2：读到文件末尾，read方法返回-1

3.释放资源

也就是close方法也叫关流

#### 空参read方法详解

read()细节

1. read()默认也是一个字节一个字节的读取的，如果遇到中文就会一次读多个

2. 在读取之后，方法的底层还会进行解码并转换成十进制，最终把这个十进制返回

   如果想看到中文，就强转就行

#### 带参read方法

底层：

把读取数据，解码，强转三步合并了，并强转之后的字符放到数组当中

相当于空参的read+强转类型转换

### FileWriter

#### 构造方法

| 构造方法                                          | 说明                             |
| ------------------------------------------------- | -------------------------------- |
| public FileWriter(File file)                      | 创建字符输出流关联本地文件       |
| public FileWriter(String pathname)                | 创建字符输出流关联本地文件       |
| public FileWriter(File file,boolean append)       | 创建字符输出流关联本地文件，续写 |
| public FileWriter(String pathname,boolean append) | 创建字符输出流关联本地文件，续写 |

#### 成员方法

| 成员方法                                | 说明                   |
| --------------------------------------- | ---------------------- |
| void write(int c)                       | 写出一个字符           |
| void write(String str)                  | 写出一个字符串         |
| void write(String str,int off,int len)  | 写出一个字符串的一部分 |
| void write(char[] cbuf)                 | 写出一个字符数组       |
| void write(char[] cbuf,int off,int len) | 写出字符数组的一部分   |

#### FIleWrite书写细节

1. 创建字符输出流对象

   细节1：参数是字符串表示的路径或者是File对象都是可以的

   细节2：如果文件不存在会创建一个新的文件，但是要保证父级路径是存在的

   细节3：如果文件已经存在，则会清空文件，如果不想清理文件可以打开续写开关

2. 写数据

   细节：如果write方法的参数是整数，但是实际上写道本地文件中的是整数在字符集上对应的字符

3. 释放资源

   细节：每次使用完流后都要释放资源

### 字符流底层原理

了解，建议看视频下期93和94集

#### 字符输入流原理

1. 创建字符输入流对象

   底层：关联文件，并创建缓冲区（长度8192的字节数据）（字节流是没有缓冲区的）

2. 读取数据

   底层：

   1. 判断缓冲区中是否有数据可以读取

   2. 缓冲区没有数据：就从文件中获取数据，装到缓冲区中，每次尽可能装满缓冲区，如果文件中也没有数据了，返回-1

   3. 缓冲区有数据：就从缓冲区读取

      空参的read方法：一次读取一个字节，遇到中文一次读多个字节，把字节解码并转成十进制返回

      有参的read方法：把读取字节，解码，强转三部合并了，强转之后的字符放到数组中

   如果数据超出8192，就会覆盖，重新从0索引开始读，并且覆盖前面的数据

#### 字符输出流原理

flush和close方法

| 成员方法            | 说明                             |
| ------------------- | -------------------------------- |
| public void flush() | 将缓冲区的数据，刷新到本地文件中 |
| public void close() | 书房资源/关流                    |

flush刷新：刷新之后，还可以继续往文件中写出数据

close关流：断开通道后，无法在往文件在写出数据

## 字节流和字符流的使用场景

字节流

拷贝任意文件

字符流

读取纯文本文件在的数据

往纯文本文件中写出数据

## 综合练习

### 拷贝文件夹

总文件开始从文件结束

### 加密和解密文件

一个数字异或一个数字两次，答案就是自己

### 修改文件中的数据

sorted是Stream流里面的排序

bom头

## 缓冲流

### 字节缓冲流

![](C:\Users\LL\Desktop\Java笔记\缓冲流.jpg)

原理：底层自带了长度为8192的缓冲区提高性能

| 方法名称                                     | 说明                                   |
| -------------------------------------------- | -------------------------------------- |
| public BufferedInputStream(InputStream is)   | 把基流包装成高级流，提高读取数据的性能 |
| public BufferedOutputStream(OutputStream os) | 把基流包装成高级流，提高写出数据的性能 |

### 字节缓冲提高效率的原理

参考视频下期100

输入和输出的缓冲流不是同一个

### 字符缓冲流

| 方法名称                        | 说明               |
| ------------------------------- | ------------------ |
| public BufferedReader(Reader r) | 把基本流变成高级流 |
| public BufferedWriter(Writer r) | 把基本流变成高级流 |

#### 特有方法

| 字符缓冲输入流特有方法   |                     说明                     |
| ------------------------ | :------------------------------------------: |
| public String readLine() | 读取一行数据，如果没有数据可读了，会返回null |

细节：readLine方法在读取的时候，一次读一整行，遇到回车换行结束，但是他不会把回车换行读到内存当中

| 字符缓冲输出流特有方法 |     说明     |
| ---------------------- | :----------: |
| public void newLine()  | 跨屏平台换行 |

细节：

字节缓冲流是byte类型的，长度为8k=8192

字符缓冲流是char类型的，长度是16k=16384

### 综合练习

#### 拷贝文件

统计各自有用时

#### 回复出师表的位置

#### 软件允许次数

创建IO流原则：什么时候用什么时候创建，什么时候不用什么时候关闭

## 转换流

是字符类和字节流之间的桥梁

作用1：指定字符集读写(JDK11淘汰了)

作用2：字节流想要使用字符流中的方法

## 序列化流

也叫对象操作输出流

![](C:\Users\LL\Desktop\Java笔记\序列化流.jpg)

可以把Java中的对象写到本地文件中

| 构造方法                                    | 说明                 |
| ------------------------------------------- | -------------------- |
| public ObjectOutputStream(OutputStream out) | 把基本流包装成高级流 |

| 成员方法                                  | 说明                         |
| ----------------------------------------- | ---------------------------- |
| public final void writeObject(Object obj) | 把对象序列化(写出)到文件中去 |

小细节

使用对象输出流对象保存到文件时会出现NotSerializableException异常

解决方案：需要让JavaBean类实现Serializable接口

Serializable接口里面是没有抽象方法的，这种叫做标记接口

一旦实现了这个接口，那么就表示当前的Serializable类可以被序列化

理解：一个物品的合格证

## 反序列化流

也叫对象操作输入流

可以把对象序列化到本地文件中的对象，读取到程序中来

| 构造方法                                  | 说明               |
| ----------------------------------------- | ------------------ |
| public ObjectInputStream(InputStream out) | 把基本流变成高级流 |

| 成员方法                   | 说明                                       |
| -------------------------- | ------------------------------------------ |
| public Object readObject() | 把序列化到本地文件中的对象，读取到程序中来 |

## 序列化流和反序列化流的使用细节

在允许过程中，会计算一个javabean的序列号，如果我们修改了javabean，版本号就会不一样

原因：文件中的版本号，根Javabean的版本号不匹配

解决方案：自己定义一个版本号

定义格式

```java
private static final long serialVersionUID = 任意数字L
```

transient：瞬态关键字

作用：不会把当前属性序列化到本地文件当中

### 细节汇总

![](C:\Users\LL\Desktop\Java笔记\序列化流细节.jpg)

## 打印流

不能读只能写

![](C:\Users\LL\Desktop\Java笔记\打印流.jpg)

分类：打印流一般是指：PrintStream，PrintWriter两个类

特点1：打印流只操作文件目的地，不操作数据源

特点2：特有的写出方法可以实现，数据原样写出

例如：打印97  文件中97

特点3：特有的写出方法，可以实现自动刷新，自动换行

打印一次数据=写出+换行+刷新

### 字节打印流

| 构造方法                                                     | 说明                         |
| :----------------------------------------------------------- | :--------------------------- |
| public PrintStream(OutputStream/File/String)                 | 关联字节输出流/文件/文件路径 |
| public PrintStream(String fileName,Charset charset)          | 指定字符编码                 |
| public PrintStream(OutputStream out ,boolean autoFlush(自动刷新)) | 自动刷新                     |
| public PrintStream(OutputStream out,boolean autoFlush,String encoding(字符编码)) | 指定字符编码且自动刷新       |

字节流底层没有缓冲区，开不开自动刷新都一样

| 成员方法                                        | 说明                                       |
| ----------------------------------------------- | ------------------------------------------ |
| public void write(int b)                        | 常规方法：规则根之前一样，将指定的字节写出 |
| public void println(Xxx xx)                     | 特有方法：打印任意数据，自动刷新，自动换行 |
| public void print(Xxx xx)                       | 特有方法：打印任意数据，不换行             |
| public void printf(String format,Object...args) | 特有方法：带有占位符的打印语句，不换行     |

字节流底层没有缓冲区，开不开自动刷新都一样

后面三个成员方法都是<b>数据原样写出</b>

### 字符打印流

字符流底层有缓冲区，想要自动刷新需要开启

| 构造方法                                                     | 说明                         |
| ------------------------------------------------------------ | ---------------------------- |
| public PrintWriter(Writer/File/String)                       | 关联字节输出流/文件/文件路径 |
| public PrintWriter(String fileName,Charset charset)          | 指定字符编码                 |
| public PrintWriter(Writer w,boolean autoFlush)               | 自动刷新                     |
| public PrintWriter(OutputStream out,boolean autoFlush,Charset charset) | 指定字符编码且自动刷新       |

字符流底层有缓冲区，想要自动刷新需要开启

| 成员方法                                       | 说明                                         |
| ---------------------------------------------- | -------------------------------------------- |
| public void write(...)                         | 常规方法：规则跟之前一样，写出字节或者字符串 |
| public void println(Xxx xx)                    | 特有方法：打印任意类型的数据并且换行         |
| public void print(Xxx xx)                      | 特有方法：打印任意类型的数据，不换行         |
| public void printf(String format,Object..args) | 特有方法：带有占位符的打印语句               |

### 打印了的应用场景

```java
//获取打印流的对象，打印流在虚拟机启动的时候，由虚拟机创建，默认指向控制台
//特殊的打印流，系统中的标准输出流，是不能关闭的，在系统中是唯一的
PrintStream ps = System.out;
//调用打印流中的方法println
//写出数据，自动换行，自动刷新
ps.println("123");
```

## 解压缩流/压缩流

![](C:\Users\LL\Desktop\Java笔记\压缩流.jpg)

### 解压缩流

压缩包里面的每一个文件都是ZipEntry对象

解压本质：把每一个ZipEntry按照层级拷贝到本地另一个文件夹中

看案例，里面有构造方法和成员方法

[案例](E:\ideacode\myio\src\com\yhr\myzipstream\ZipStreamDemo1.java)

### 压缩流

压缩包里面的每一个文件或文件夹都是一个zipEntry对象

压缩本质：把每一个(文件/文件夹)看出ZipEntry对象放到压缩包中

单个文件

[案例](E:\ideacode\myio\src\com\yhr\myzipstream\ZipStreamDemo2.java)

小细节：zipentry里面的参数是压缩包里面的路径

多个文件

## 常用工具包

### Commons-io

Commons-io是apache开源基金组织提高的一组有关IO操作的开源工具包

作用：提高IO流的开发效率

![](C:\Users\LL\Desktop\Java笔记\commons-io.jpg)

#### 使用步骤

1. 在项目中创建一个文件夹：lib
2. 将jar包赋值粘贴到lib文件夹中
3. 右键点击jar包，选择Add as Library->点击OK
4. 在类中导包使用

常见方法

[参考](C:\Users\LL\Desktop\Java笔记\commons-io整理的文档.md)

FileUtils方法

![](C:\Users\LL\Desktop\Java笔记\FileUtils方法.jpg)

IOUtils方法

![](C:\Users\LL\Desktop\Java笔记\IOUtils方法.jpg)

### Hutool

也称胡涂包

![](C:\Users\LL\Desktop\Java笔记\Hutool目录.jpg)

![](C:\Users\LL\Desktop\Java笔记\HutoolIO相关.jpg)

后面两个和java里面一样，所以要注意导包

FileUtil类：

| 成员方法      | 说明                                                   |
| ------------- | ------------------------------------------------------ |
| file          | 根据参数创建一个file对象                               |
| touch         | 根据参数创建文件(如果父级路径不存在，会把父级创建出来) |
| writeLines    | 把集合中的数据写出到文件中，覆盖模式                   |
| appendLines   | 把集合中的数据写出到文件中，续写模式                   |
| readLines     | 指定字符编码，把文件中的数据，读到集合中               |
| readUtf8Lines | 按照UTF-8的形式，把文件中的数据，读到集合中            |
| copy          | 拷贝文件或者文件夹                                     |

## 综合练习

### 网络爬虫

#### 制造假数据：

需求：制造假数据也是开发中的一个能力，在各个网上爬取数据，是其中一个方法

创建一个URL，然后链接这个网址 

```java
URL url = new URL(net);
URLConnection conn = url.openConnection();
```

[案例](E:\ideacode\myiotest\src\com\yhr\myiotest1\Test1.java)

### 利用胡涂包生成假数据

细节：糊涂包的相对文件不是相对于当前项目下的，而是当前class文件下的

### 随机点名器

4个Test包

![](C:\Users\LL\Desktop\Java笔记\随机点名器.jpg)

### 带权重的随机算法

[案例](E:\ideacode\myiotest\src\com\yhr\myiotest6\Test.java)

### 登录注册

### 拼图游戏

### 游戏配置

好处：

1. 可以把软件的设置永久化存储
2. 如果我们要修改参数，不需要改动代码，直接修改配置文件就可以了

#### 常见配置文件

1. XML
2. ini
3. properties
4. YAM

#### properties配置文件

后缀名：.properties

文件中都是按照键值对存储的

![](C:\Users\LL\Desktop\Java笔记\properties.jpg)

properties是一个双列集合，拥有Map集合所有的特点

重点：有一些特有的方法，可以把集合中的数据，按照键值对的形式写道配置文件当中，也可以把配置文件中的数据读取到集合中来

[案例](E:\ideacode\myiotest\src\com\yhr\myiotest9\Test1.java)

细节：虽然我们往properties当中添加任意数据类型，但是一般会往里面添加字符串类型的数据

特有方法参考myiotest9里面的Test2和Test3

### 每日一记

IO阶段大作业(未作)

# 多线程

## 什么是多线程

线程：线程是操作系统能够进行运算调度的最小单位，他被包含在进程之中，是进程中的实际运作单位

进程：进程是程序的基本执行实体

简单理解：应用软件中互相独立，可以同时允许的功能

### 应用场景

1. 软件中的耗时操作
2. 所有的聊天软件
3. 所有的后台服务器
4. 拷贝、迁移大文件
5. 加载大量的资源文件

## 并发和并行

并发：在同一时刻，有多个指令在单个CPU上<b>交替</b>执行

并行：在同一时刻，有多个指令在多个CPU上<b>同时</b>执行

## 多线程实现方式

1. 继承Thread类的方式进行实现
2. 实现Runnable接口的方式进行实现
3. 利用Callable接口和Future接口方式实现

### 多线程第一种实现方式

继承Thread类的方式进行实现

1. 自己定义一个类继承Thread
2. 重写run方法
3. 创建子类的对象，并启动线程

[案例](E:\ideacode\mythread\src\com\yhr\a01threacase1\ThreadDemo.java)

### 多线程第二种实现方式

实现Runnable接口的方式进行实现

1. 自己定义一个类实现Runnable接口
2. 重写里面的run方法
3. 创建自己类的对象
4. 创建一个Thread类的对象，并开启线程

### 多线程第三种实现方法

利用Callable接口和Future接口方式实现

1. 创建一个类MyCallable实现Callable接口
2. 重写call（是有返回值的，表示多线程要执行的任务）
3. 创建MyCallable的对象（表示多线程要执行的任务）
4. 创建FutureTask的对象（作用管理多线程运行的结果）
5. 创建Thread类的对象，并启动（表示线程）

### 总结

实现方法对比

|                  | 优点                                         | 缺点                                       |
| ---------------- | -------------------------------------------- | ------------------------------------------ |
| 继承Thread类     | 编程比较简单，可以直接使用Thread类中的方法   | 可以扩展性比较差，不能再继承其他的类       |
| 实现Runable接口  | 扩展性强，实现该接口的同时还可以继承其他的类 | 编程相对复杂，不能直接使用Thread类中的方法 |
| 实现Callable接口 | 扩展性强，实现该接口的同时还可以继承其他的类 | 编程相对复杂，不能直接使用Thread类中的方法 |

## 常见成员方法

| 方法名称                         | 说明                                   |
| -------------------------------- | -------------------------------------- |
| String getName()                 | 返回线程名字                           |
| void setName(String name)        | 设置线程的名字(构造方法也可以设置名字) |
| static Thread currentThread()    | 获取当前线程的对象                     |
| static void sleep(long time)     | 让线程休眠指定的时间，单位为毫秒       |
| setPriority(int newPriority)     | 设置线程优先级                         |
| final int getPriority()          | 获取线程的优先级                       |
| final void setDaemon(boolean on) | 设置守护线程(备胎线程)                 |
| public static void yield()       | 出让线程\礼让线程                      |
| public static void join()        | 插入线程\插队线程                      |

细节：

1. 如果我们没有给线程设置名字，线程也是有默认的名字的

   格式：Thread-X(X序号，从0开始)

2. (setName)如果我们要给线程设置名字，可以用set方法。也可以用构造方法

3. (currentThread)当JVM虚拟机启动之后，会自动的启动多条线程，其中有一条线程就叫做main线程，他的作用

   就是去调用main方法，并执行里面的代码，再以前，我们写的所有的代码，其实都是允许再main线程当中的

4. (sleep)哪条线程线程执行到这个方法，那么这条线程就会再这里停留

5. 方法的参数：就表示睡眠的时间，单位毫秒

6. 当时到了之后，线程就会自动醒来，继续执行下面的代码

### 线程的优先级

1. 抢占式调度(随机)
2. 非抢占式调度

java就是抢占式调度

随机性，在java中线程的优先级分为十挡，最小是1，最大为10，默认为5

### 守护线程

细节：当其他的非守护线程执行完毕之后，守护线程会陆续结束

通俗易懂：当女神线程结束了，那么备胎也没有存在的必要了

应用场景：参考下期142

### 出让线程\礼让线程

尽可能让结果均匀，非绝对均匀

### 插入线程\插队线程

表示把这个线程，插入到当前线程之前，这个线程执行完毕，才执行当前线程

## 线程的生命周期

![](C:\Users\LL\Desktop\Java笔记\线程的生命周期.jpg)

答：不会

## 线程的安全问题

线程执行时，有随机性

参考视频：下期147

### 同步代码块

把操作共享数据的代码锁起来

格式：

```java
synchronized(锁){
    操作共享数据的代码
}
```

特点1：锁默认是打开的，有一个线程进去了，锁自动关闭

特点2：里面的代码全部执行完毕，线程出来，锁自动打开

锁对象是任意对象都行，Object都可以，但是一定要是唯一的，前面加个static就行

这种叫做同步代码块

[案例](E:\ideacode\mythread\src\com\yhr\a09threadsafe1\MyThread.java)

#### 同步代码块的两个小细节

1. 锁不能写在循环外面
2. 一定要唯一

### 同步方法

就是把synchronized关键字加到方法上

格式：

```java
修饰符synchronized返回值类型 方法名(方法参数){.....}
```

特点1：同步方法是锁住方法里面所有的代码

特点2：锁对象不能自己指定

非静态：this

静态：当前类的字节码文件

技巧：可以先写同步代码块，再写同步方法

[案例](E:\ideacode\mythread\src\com\yhr\a10threadsafe2\MyRunnable.java)

#### StringBuffer

这里面的方法和StringBuilder一摸一样，但是他比StringBulider安全

如何选择这两个呢，如果是单线程，不考虑安全就选择StringBuilder，多线程，需要考虑安全，就选择StringBuffer

### lock锁

虽然我们可以理解同步代码块和同步方法的锁对象问题

但是我们并没有直接看到在哪里加上了锁，在哪里是方法了锁，

为了更清晰的表达如何加锁和释放锁，JDK5以后提供了一个新的对象Lock

Lock实现了提供比使用snychronized方法和语句可以获得更广泛的锁定操作

Lock中提供了获得锁和释放锁的方法

手动上锁，手动释放锁：

void lock()：获得锁

void unlock()：释放锁

Lock是接口不能直接实例化，这里采用它的实现类ReentrantLock来实例化

ReentrantLock的构造方法

ReentrantLock()：创建一个ReentrantLock的实例

## 死锁

这不是一个知识点，而是错误

记住，以后写锁的时候不要让锁嵌套起来就行

[案例](E:\ideacode\mythread\src\com\yhr\a12deadlock\MyThread.java)

## 生产者和消费者(等待唤醒机制)

生产者消费者模式是一个十分经典的多线程协作的模式

生产者：生产数据

消费者：消费数据

核心思想：利用东西来控制线程的执行

### 消费者等待

wait

notify:唤醒

例子：

消费者：

1. 判断桌子上是否食物
2. 如果没有就等待

生产者：

1. 制作食物
2. 把食物放在桌子上
3. 叫醒等待的消费者开吃

### 生产者等待

生产者：

1. 判断桌子上是否有食物
2. 有：等待
3. 没有：制作食物
4. 制作食物
5. 把食物放在桌子上
6. 叫醒等待的消费者开吃

消费者：

1. 判断桌子上是否食物
2. 如果没有就等待
3. 如果有就开吃
4. 吃完之后，唤醒厨师继续做

### 完整过程

生产者：

1. 判断桌子上是否有食物
2. 有：等待
3. 没有：制作食物
4. 制作食物
5. 把食物放在桌子上
6. 叫醒等待的消费者开吃

消费者：

1. 判断桌子上是否食物
2. 如果没有就等待
3. 如果有就开吃
4. 吃完之后，唤醒厨师继续做

### 常见方法

| 方法名称    | 说明                             |
| ----------- | -------------------------------- |
| void wait   | 当前线程等待，直到被其他线程唤醒 |
| void notify | 随机唤醒单个线程                 |
| void notify | 唤醒所以线程                     |

### 多线程书写思路

1. 循环
2. 同步代码块
3. 判断共享数据是否到了末尾(推荐先写到了末尾)
4. 判断共享数据是否到了末尾(没有到末尾，执行核心逻辑)

[案例](E:\ideacode\mythread\src\com\yhr\a13waitandnotify\ThreadDemo.java)

## 等待唤醒机制(阻塞队列方式实现)

put数据时：放不进去，会等着，也叫做堵塞

take数据时：取出第一个数据，取不到会等着，也叫做堵塞

![](C:\Users\LL\Desktop\Java笔记\阻塞队列继承结构.jpg)

细节:生产者和消费者必须使用同一个阻塞队列

[案例](E:\ideacode\mythread\src\com\yhr\a14waitandnotify\ThreadDemo.java)

## 线程的状态

![](C:\Users\LL\Desktop\Java笔记\多线程的状态.jpg)

细节：Java的虚拟机是没有定义允许状态的

可以去API帮助文档看Thead State

- 新建状态(NEW)---------创建线程对象
- 就绪状态(RUNNABLE)-----------start方法
- 阻塞状态(BLOCKED)------------无法获得对象
- 等待状态(TIMED)----------------wait方法
- 计时等待(TIMED_WAITING)------------sleep方法
- 结束状态(TERMINATED)--------------全部代码运行完毕

## 综合练习

[案例](E:\ideacode\mythreadtest1\src\com\yhr\test1\Test.java)

### 卖电影票

一共有1000张电影票，可以在两个窗口领取，假设每次领取的时间为3000毫秒，

要求：请用多线程模拟卖票过程并打印剩余电影票的数量

### 送礼品

有100份礼品，两人同时发送，当剩下的礼品小于10份的时候则不再送出

利用多线程模拟该过程并将线程的名字和礼物的剩余数量打印出来

### 打印奇数数字

同时开启两个线程，同时获取1-100之间的所有数字

要求：输出所有的奇数

### 抢红包

抢红包也用到了多线程

假设：100块，分成了3个包，现在有5个人去强

其中，红包是共享数据

5个人是5条线程

打印结果如下:

XXX抢到了XXX元

XXX抢到了XXX元

XXX抢到了XXX元

XXX没抢到

XXX没抢到

### 抽奖箱抽奖

有一个抽奖池，该抽奖池存放了奖励的金额，该抽奖池中的奖项为{10,5,20,50,100,200,500,800,2,80,300,700}

创建两个抽奖箱（线程）设置线程名称分别为“抽奖箱1”，“抽奖箱2”，随机从抽奖池中获取奖项元素并打印在控制台上，格式如下：

每次抽出一个奖项就打印一个(随机)

抽奖箱1又产生了一个10元大奖

抽奖箱1又产生了一个100元大奖

抽奖箱1又产生了一个200元大奖

抽奖箱1又产生了一个800元大奖

抽奖箱1又产生了一个700元大奖

....

### 多线程统计并求最大值

在上一题继承上继续完成如下需求：

每次抽的过程中，不打印，抽完时一次性打印(随机)

在此次抽奖过程中，抽奖箱1总共产生了6个奖项

分别为：10，20，100，500，2，300最高奖项为300元，总计金额为932元

在此次抽奖过程中，抽奖箱2总共产生了6个奖项

分别为：5，50，200，800，80，700最高奖项为800元，总计额为1835元

### 多线程之间的比较

在上一题基础上继续完成如下需求：

在此次抽奖过程中，抽奖箱1总共产生了6个奖项，分别为：10，20，100，500，2，300

最高奖项为300，总计额为932元

在此次抽奖过程中，抽奖箱1总共产生了6个奖项，分别为：5，50，200，800，80，700

最高奖项为800，总计额为1835元

在此次抽奖过程中，抽奖箱2中产生了最大奖项，该奖项金额为800元

### 多线程阶段大作业

![](C:\Users\LL\Desktop\Java笔记\多线程大作业.jpg)

### 线程栈的内存图

![](C:\Users\LL\Desktop\Java笔记\线程栈的内存图.jpg)

## 线程池

### 以前写多线程的弊端

弊端1：用到线程的时候就创建

弊端2：用完之后线程消失

### 线程池主要核心逻辑

1. 创建一个池子，池子中是空的
2. 提交仍无时，池子会创建新的线程对象，任务执行完毕，线程归还给池子下回再次提交任务时，不需要创建新的线程，直接复用已有的线程即可
3. 但是如果提交任务时，池子中没有空闲线程，也无法创建新的线程，任务就会排队等待

### 代码实现

1. 创建线程池
2. 提交任务
3. 所有的任务全部执行完毕，关闭线程池

Executors：线程池的工具类通过调用方法返回不同类型的线程池对象

| 方法名称                                                     | 说明                                    |
| ------------------------------------------------------------ | --------------------------------------- |
| public static ExecutorService newCachedThreadPool()          | 创建一个没有上限的线程池(int类型的上限) |
| public static ExecutorService newFixedThreadPool(int nThreads) | 创建有上限的线程池                      |

[案例](E:\ideacode\mythreadpool\src\com\yhr\a01threadpool1\MyThreadPoolDemo.java)

### 自定义线程

饭店的故事

核心元素一：正式员工数量-------------------核心线程数量(不能小于0)

核心元素二：餐厅最大员工数----------------线程池中最大线程的数量（最大数量>=核心线程数量）

核心元素三：临时员工空闲多长时间被辞退（值）------------空闲时间（值）（不能小于0）

核心元素四：临时员工空闲多长时间被辞退（单位）-----------空闲时间（单位）(用TimeUnit指定)

核心元素五：排队的客户-------------------阻塞队列(不能为null)

核心元素六：从哪里招人-------------------创建线程的方式(不能为null)

核心元素七：当排队人数过多，超出顾客请下次再来（拒绝服务）---------------要执行的任务过多时的解决方案(不能为null)

参考视频下期164

细节：当核心线程和队伍排满后，才会创建临时线程

细节：任务执行顺序不一定是按照提交顺序，原理参考视频

#### 自定义线程池（任务拒绝策略）

| 任务拒绝策略                           | 说明                                                   |
| -------------------------------------- | ------------------------------------------------------ |
| ThreadPoolExecutor.AbortPolicy         | 默认策略：丢弃任务并抛出RejectedExecutionException异常 |
| ThreadPoolExecutor.DiscardPolicy       | 丢弃任务，但是不抛出异常，这是不推荐的做法             |
| ThreadPoolExecutor.DiscardOldestPokicy | 抛弃队列中等待最近的任务，然后把当前任务加入队列当中   |
| ThreadPoolExecutor.CallerRunsPolicy    | 调用任务的run()方法绕过线程池直接执行                  |

[案例](E:\ideacode\mythreadpool\src\com\yhr\a02threadpool2\MyThreadPoolDemo1.java)

### 最大并行数

最大并行数：就比如4核8线程，最大并行数就是8

### 线程池多大合适

- CPU密集型运算          最大并行数+1
- I/O密集型运算            最大并行数*期望CPU利用率 * (总时间(CPU计算时间+等待时间)/CPU计算时间)

thread dump工具可以用来计算时间

### 多线程额外扩展内容

[参考](C:\Users\LL\Desktop\Java笔记\juc额外扩展资料\多线程（额外扩展）.md)

# 网络编程

网络编程就是在网络通讯协议下，不同计算机上运行的程序。进行的数据传输

- 应用场景：即使时通信、网友对战、金融证券、国际贸易、邮件、等等。

  不管是什么场景，都是计算机跟计算机之间通过网络进行数据传输

- Java中可以使用java.net包下的技术轻松开发出场景的网络应用程序

## 常见软件架构

- B/S：Browser/Server：只需要一个浏览器，用户通过不同的网址。客户访问不同的服务器
- C/S：Client\Server：在用户本地需要下载并安装客户端程序，在远程有一个服务器端程序

## BS架构的优缺点

1. 不需要开发客户端，只需要页面+服务端
2. 用户不需要下载，打开浏览器就能使用
3. 如果应用过大，用户体验收到影响

## CS架构的优缺点

1. 画面可以做的非常精美，用户体验好
2. 需要开发客户端，也需要开发服务端
3. 用户需要下载和更新的时候太麻烦

## 网络编程三要素

确定对方电脑在互联网上的地址，也就是IP地址

确定结束数据的软件，也叫端口号

确定网络传输规则，也就是协议

- IP：设备在网络中的地址，是唯一的标识
- 端口号：应用程序在设备中唯一的标识
- 协议：数据在网络中传输的规则，常见的协议有UDP、TCP、http、https、ftp

## IP

全称：Internet Protocol，是互联网协议地址，也称IP地址，是分配给上网设备的数字标签

通俗理解：上网设备在网络中的地址，是唯一的

分类：

- IPv4
- IPv6

### IPv4

全称：Internet Protocol version 4,互联网协议第四版

采用32位地址长度，分成四组

点分十进制

每一组取值：0~255

### IPv6

全称：Internet Protocol version 6,互联网协议第六版

由于互联网的蓬勃发展，IP地址的需求量愈来愈大，而IPv4的模式下IP的总数是有限的

采用128位地址长度，分成8组

冒分16进制

特殊情况：如果计算出的16进制表示形式中间有多个连续的0

例如：FF01:0:0:0:0:0:0:1101   0位压缩法后   FF01::1101

### IPv4小细节

IPv4的地址分类形式

- 公用地址（万维网使用）和私有地址（局域网使用）
- 192.168.开头的就是私有地址，范围即为192.168.0.0--192.168.255.255，专门为组织机构内部使用，以此节省IP

### 特殊IP地址

127.0.0.1，也可以是localhost：是回送地址也称本地回环地址，也称本机IP，永远只会寻找当前所在本机

疑问：假设192.168.1.100是我电脑的IP，那么这个IP跟127.0.0.1是一样的吗？

不一样，建议参考下期视频动画171

建议：如果自己给自己发送数据，就用127.0.0.1

### 常用CMD命令

- ipconfig：查看本机IP地址
- ping：检查网络是否连通

## InetAddress的使用

IP的对象，也就是一台电脑的对象

getHostName有个细节，如果没有这台电脑，是获取不到主机名的，会以IP地址的形态体现

[案例](E:\ideacode\mysocketnet\src\com\yhr\a01InetAddressdemo\MyInetAddressDemo1.java)

## 端口号

应用程序在设备中唯一的标识

端口号：由两个字节表示的整数，取值范围：0~65535

其中0~1023之间的端口号用于一些知名的网络服务或者应用

我们自己使用1024以上的端口号就可以了

注意：一个端口号只能被一个应用程序使用

## 协议

计算机网络中，连接和通信的规则被称为网络通信协议

- OSI参考模型：世界互联协议标准，全球通信规范，但模型过于理想化，未能在英特网上进行广泛推广
- TCP/IP参考模型（或TCP/IP协议）：事实上的网际标准

![](C:\Users\LL\Desktop\Java笔记\网络协议参考模型.jpg)

### UDP协议

- 用户数据报协议(User Datagram Protocol)

- UDP是面向无连接通信协议

  速度快，有大小限制一次最多发送64KB，数据不安全，易丢失数据

面向无连接意思就是不管是否已经连接成功，都会发送

### TCP协议

- 传输控制协议TCP(Transmission Control Protocol)

- TCP协议是面向连接的通信协议

  速度慢，没有大小显示，数据安全

面向连接就是确保连接成功才会发送

UDP多应用于在线视频

TCP多用于下载文件，发送协议

### UDP(发送数据)

1. 创建发送端的DatagramSocket对象
2. 数据打包(DatagramPacket)
3. 发送数据
4. 释放资源

细节：

DatagramSocket：空参：所有可用的端口中随机一个进行使用   有参：指定端口号进行绑定

[案例](E:\ideacode\mysocketnet\src\com\yhr\a02udpdemo\SendMessageDemo.java)

### UDP(接收数据)

1. 创建接收端的DatagramSocket对象
2. 就收打包好的数据
3. 解析数据包
4. 释放资源

细节：

在接受的时候，一定要绑定端口，而且绑定的端口一定要跟发送的端口保持一致

receive这个方法是阻塞的，程序执行到这一步的时候，会在这里死等，等发送端发送消息

### UDP练习(聊天室)

UDP发送数据：数据来自于键盘录入，直到输入的数据是886，发送数据结束

UDP接收数据：因为接收端不知道发送端什么时候停止发送，故采用死循环接收 

### UDP的三种通信方式

1. 单播：也就是一对一
2. 组播：给一组电脑发送数据
3. 广播：给局域网所有的电脑发送数据

#### 代码实现

1. 单播：以前的代码就是单播

2. 组播：组播地址：224.0.0.0~239.255.255.255，其中224.0.0.0~224.0.0.255为预留的组播地址

   [案例](E:\ideacode\mysocketnet\src\com\yhr\a04udpdemo3\SendMessageDemo.java)

3. 广播：广播地址：255.255.255.255

[案例](E:\ideacode\mysocketnet\src\com\yhr\a04udpMessageDemo\ReceiveMessageDemo.java)

### TCP协议

TCP通信协议是一种可靠的网络协议，它在通信的两端各建立一个Socket对象，通信之前要保证连接已经建立，通过Socket产生IO流来进行网络通信

客户端创建流程：

1. 创建客户端的Socket对象(Socket)与指定服务端连接

   Socket(String host,int port)(细节：在创建对象的同时会连接服务器，如果连接不上，代码会报错)

2. 获取输出流，写数据

   OutputStream getOutputStream()

3. 释放资源

   void close()

服务器创建流程：

1. 创建服务端的Socket对象(ServerSocket)

   ServerSocket(int port)端口号要与客户端一致

2. 监听客户端连接，返回一个Socket对象

   Socket accept()

3. 获取输入流，读数据，并把数据显示在控制台

   InputStream getInputStream()

4. 释放资源

   void close()

[案例](E:\ideacode\mysocketnet\src\com\yhr\a05tcpdemo1\Client.java)

#### 中文乱码问题

用转换流就是

### TCP协议代码细节

参考下期181

### 三次握手

作用：确保连接建立

1. 客户端向服务器发出连接请求，等待服务器确认
2. 服务器向客户端返回一个响应，告诉客户端收到了请求
3. 客户端向服务器再次发出确认信息，连接建立

### 四次挥手

作用：确保连接断开，且数据处理完毕

1. 客户端向服务器发出取消连接请求
2. 服务器向客户端返回一个响应，表示收到客户端取消请求
3. 服务器向客户端发出确认取消信息
4. 客户端再次发送确认消息，连接取消

## 综合练习

题干参考下期183

细节：read方法会从连接通道中读取数据，但是，需要有一个结束标记，此处的循环才会停止。否则，程序就会一直停在read方法这里，等待读取下面的数据

[案例](E:\ideacode\mysocketnet\src\com\yhr\a09test3\Client.java)这个挺难的，细节多

### UUID

表示一些通用唯一的标识符类，具体参考API标识文档

### 网络编程课后大作业

(未作)

# 反射

反射允许对封装类的字段，成员方法和构造方法的信息进行编程访问

获取是从class字节码文件获取

## 获取class对象的三种方式

1. Class.forName("全类名");
2. 类名.class
3. 对象.getClass();

全类名：包名+类名；

第一种最为常用，第二种作为参数传递，第三种当我们已经有这个类的对象时才可以使用

## 反射获取构造方法

获取：

1. 获取Class对象
2. 构造方法是Constructor的对象
3. 字段(成员变量)Field的对象
4. 成员方法Methodd的对象

### 利用反射获取构造方法

Class类中用于获取构造方法的方法

Constructor<?>[] getConstructors():返回所有公共构造方法对象的数组

Constructor<?>[] getDeclaredConstructors():返回所有构造方法对象的数组

Constructor<T> getConstructor(Class<?>...parameterTypes):返回单个公共构造方法对象

Constructor<T> getDeclaredConstructor(Class<?>...parameterTypes):返回单个构造方法对象



Constructor类中用于创建对象的方法

T newInstance(Object..initargs):根据指定的构造方法创建对象

setAccessible(boolean flag):设置为true，表示取消访问检查



```java
getModifiers()：可以获取权限修饰符，只不过是返回int类型的，各个权限修饰符值为多少可以去API帮助文档里面找  搜常量字段值
getParameters()：获取参数
getParametersCount()：获取参数个数
getParametersType()：获取参数类型
setAccessible(true)：临时取消权限校验，这种也叫暴力反射
newInstance()：创建对象
```

 [案例](E:\ideacode\myreflect\src\com\yhr\myreflect2\MyReflect.java)

### 利用反射获取成员变量

Class类中用于获取成员变量的方法

Field[] getFields()：返回所有公共成员变量对象的数组

Field[] getDeclaredFileds()：返回所有成员变量对象的数组

Field getField(Strring name)：返回单个公共成员变量对象

Field getDeclaredField(String name)：返回单个成员变量对象



Field类中用于创建对象的方法

void set(Object obj,Object value)：赋值

Object get(Object obj)获取值

```java
getName：获取成员变量的名字
getType：获取成员变量的数据类型
get：获取成员变量记录的值
set：修改对象里面记录的值
```

[案例](E:\ideacode\myreflect\src\com\yhr\myreflect3\MyReflectDemo3.java)

### 利用反射获取成员方法

Class类中用于获取成员方法的方法

Method[] getMethods()：返回所有公共成员方法对象的数组，包括继承的

Method[] getDeclaredMethods()：返回所有成员方法对象的数组，不包括继承的

Method getMethod(String name,Class<?>...parameterTypes)：返回单个公共成员方法对象

Method getDeclaredMethod(String name,Class<?>...parameterTypes)：返回单个成员方法对象



Method类中用于创建对象的方法

Object invoke(Object obj,Object...args)：运行方法

参数一：用obj对象调用该方法

参数二：调用方法的传递的参数(如果没有就不写)

返回值：方法的返回值（如果没有就不写）

```java
getName：获取方法名字
getParameters：获取方法的形参
getExceptionTypes：获取方法的抛出异常
Object invoke(Object obj,Objec...args)：运行方法
    参数一：用Obj对象调用该方法
    参数二：调用方法的传递的参数（如果没有就不写）
    返回值：方法的返回值（如果没有就不写）
```

[案例](E:\ideacode\myreflect\src\com\yhr\myreflect4\MyReflectDemo4.java)

## 反射的作用

### 综合练习1

1. 获取一个类里面的所有的信息，获取到了之后，在执行其他的业务逻辑
2. 结合配置文件，动态的创建对象并调用方法

[案例](E:\ideacode\myreflect\src\com\yhr\myreflect5\MyReflect.java)

### 综合练习2

![](C:\Users\LL\Desktop\Java笔记\反射总结.jpg)

# 动态代理

特点：无侵入式的给代码增加额外的功能

程序为什么需要代理？代理长什么样？

1. 对象如果嫌身上干的事太多的话，可以通过代理来转移部分职责
2. 对象有什么方法想被取代，代理就一定要有对应的方法

中介是如何直到要派从唱歌和跳舞的方法代理的？

这就需要通过接口来实现

代理和鸡哥都是先这个接口就可以了

所有被代理的方法都写在接口当中

## 动态代理代码实现

- Java.lang.reflect.Proxy类：提供了为对象产生代理对象的方法：

```java
public static Object newProxyInstance(ClassLoader,Class<?>[] interface,InvocationHandler h(这个是个接口))
    参数一：用于指定用哪个类加载器，去加载生成的代理类
    参数二：指定接口，这些接口用于指定生成的代理长什么，也就是有哪些方法
    参数三：用来指定生成的代理对象要干什么事情
```

(返回值就是那个代理对象)

[案例](E:\ideacode\mydynamicproxy\src\com\yhr\mydynamicproxy1\Test.java)

一定要看案例，非常重要

通过invokehandler的invoke方法指定代理干的事时，这个invoke会被谁调用？要接哪几个参数

这个invoke方法会被测试类里面的proxy调用，他会把proxy所调用的方法传到invoke里面sing里面的参数就是args

# log日志

## 概述

程序中的日志：可以用来记录程序运行过程中的信息，并可以进行永久存储

日志技术具备的优势：

- 可以将系统的信息选择性的记录到指定的位置（控制台、文件中、数据库中）
- 可以随时开关的形式控制自己是否记录日志，无需修改源代码

##  体系结构

日志规范接口：

- Commons  Logging  简称：JCL
- Simple Logging  Facade for Java   简称：slf4j



- 日常规范：一些接口，提供给日志的实现框架设计的标准
- 日志框架：牛人或者第三方公司已经做好的日志记录实现代码，后来者可以直接使用
- 因为对Commons Logging不满意，有人就搞了SLF4J。因为对Log4j的性能不满意，有人就搞了Logback

一个小细节：

以前一个程序员起名字，infoToFile偷懒写出info2File，这个习惯后面保存下来

1. To：2
2. For：4

读：2 To  4 For

![](C:\Users\LL\Desktop\Java笔记\日志体系结构.png)

总结：

![日志体系总结](C:\Users\LL\Desktop\Java笔记\日志体系总结.png)

## Logback日志框架

- Logback是基于slf4j的日志规范实现的框架，性能比之前使用的log4j要好
- 官方网站：https://logback.qos.ch/index.html

Logback主要分为三个技术模块：

- logback-core：该模块为其他两个模块提供基础代码，必须有
- logback-classic：完整实现了slf4jAPI的模块
- logback-access：模块与Tomact和Jetty等Servlet容器集成，已提供http访问日志功能
- logback-api：日志模块

### 使用步骤：

* 把第三方的代码导入到当前的项目当中

  新建lib文件夹，把jar粘贴到lib文件夹当中，全选后右键点击选择add as a ....

  检测导入成功：导入成功后jar包可以展开。在项目重构界面可以看到导入的内容

* 把配置文件粘贴到src文件夹下

* 在代码中获取日志对象

  ```java
  public static final Logger LOGGER = LoggerFactory.getLogger("类对象");
  ```

* 调用方法打印日志

Logback日志系统的特性都是通过核心配置文件logback.xml控制的

Logback日志输出配置、格式设置

- 通过logback.xml中的<appender>标签可以设置输出位置和日志信息的详细格式
- 通常可以设置2个日志输出位置：一个是控制台、一个是系统文件中

输出控制台的配置标志

```xml
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
```

输出到系统文件的配置标志

```xml
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
```

System.err.println：错误输出(控制台颜色变成红色)

## 日志级别

- 级别程度依次是：TACKE<DEBUG<INFO<WARN<ERROR;默认级别是debug(忽略大小写)，对应其方法
- 作用：用于控制系统中哪些日志级别是可以输出的，是输出级别不低于设定级别的日志信息
- ALL和OFF分别是打开全部日志信息，及关闭全部日志信息

具体在<root level="INFO">标签的level属性中设置日志级别

```xml
<root level="INFO">
    <appender-ref ref="CONSOLE"/>
    <appender-ref ref="FILE"/>
</root>
```

# 类加载器

作用：负责将.class文件(存储的物理文件)加载到内容中

## 类加载器的时机

类在什么时候加载到内存当中？

类加载时机

有以下的几种情况：

+ 创建类的实例（对象）
+ 调用类的类方法
+ 访问类或者接口的类变量，或者为该类变量赋值
+ 使用反射方式来强制创建某个类或接口对应的java.lang.Class对象
+ 初始化某个类的子类
+ 直接使用java.exe命令来运行某个主类

总结而言：用了就加载，不用不加载

## 类加载的过程

![](C:\Users\LL\Desktop\Java笔记\类加载的过程.png)

### 加载：

- 通过一个类的全限名来获取定义此类的二进制字节流(通过包名+类名，获取这个类，准备用流进行传输)
- 将这个字节流所代表的静态存储结构转化为运行时数据结构(在这个类加载到内存中)
- 在内存中生成一个代表这个类的java.lang.Class对象，任何类被使用时，系统都会为之建立一个java.lang.Class对象(加载完毕创建一个Class对象)

![](C:\Users\LL\Desktop\Java笔记\类加载器\img\02_类加载过程加载.png)

### 链接：

#### 验证：

链接阶段的第一步，这一阶段为了确保Class文件字节流中，包含的信息符号当前虚拟机的要求，并且不会危害虚拟机自身安全(简单理解：文件中的信息是否符合虚拟机规范，有没有安全隐患)

#### 准备：

负责为类的类变量(被static修饰的变量)分配内存，并设置初始化值(初始化静态变量)

![](C:\Users\LL\Desktop\Java笔记\类加载器\img\04_类加载过程准备.png)

#### 解析：

将类的二进制数据流中的符号引用替换为直接引用(本类中如果用到了其他类，此时就需要找到对应的类)

![](C:\Users\LL\Desktop\Java笔记\类加载过程的解析.png)

### 初始化：

根据程序员通过程序制定的主观计划去初始化类变量和其他资源(静态变量赋值以及初始化其他资源)

static String school = "传智大学";

### 小结：

- 当一个类被使用的时候，才会加载到内存

- 类加载的过程如下：

  加载、验证、准备、解析、初始化

符号引用：就是在加载过程中，还没解析的时候，用符号代替引用的类型

## 类加载器的分类

- 启动类加载器(BootstrapClassLoader)：虚拟机内置的类加载器(底层是C++)
- 平台类加载器(PlatformClassLoader)：负载加载JDK中一些特殊的模块
- 系统类加载器(SystemClassloader)：负责加载用户类路径上所指定的类库(一般情况用这个)
- 自定义类加载器(UserClassloader)

## 双亲委派模型

这个里面要求除了启动类加载器，另外几个类加载器都有父类加载器，自定义继承系统继承平台继承启动(逻辑上的继承)

![](C:\Users\LL\Desktop\Java笔记\类加载器\img\07_双亲委派模型.png)

getParent()

getParent()

getSystemClassLoader()： 获得系统类加载器

[案例](E:\ideacode\myclassloader\src\com\yhr\myclassloader\ClassLoaderDemo1.java)

## 常用方法

都定义在ClassLoader里面中的

| 方法名                                              | 说明               |
| --------------------------------------------------- | ------------------ |
| public static ClassLoader getSystemClassLoader()    | 获取系统类加载器   |
| public InputStream getResourceAsStream(String name) | 加载某一个资源文件 |

这个文件要建在src里面

[案例](E:\ideacode\myclassloader\src\com\yhr\myclassloader\ClassLoaderDemo2.java)

# XML

## 配置文件

用来保存程序在运行时需要的一些参数

1. TXT文件：

   优点：没有优点

   缺点：不利于阅读

2. properties文件：

   优点：键值对形式易于阅读，解析简单

   缺点：无法配置一组一组的数据

3. XML文件

   优点：易于阅读，可以配置成组出现的数据

   缺点：解析比较复杂

数量较少用properties

数据多用XML

## XML概述

- XML的全称为(EXtensible Markup Language)，是一种可扩展的标记语言
- 标记语言：通过标签来描述数据的一门语言(标签有时我们也将其称之为元素)
- 可扩展：标签的名字是可以自己定义的

## XML作用：

- 用于进行存储数据和传输数据
- 作为软件的配置文件

## XML的创建

- 就是创建一个XML类型的文件，要求文件的后缀必须使用XML，如hello_world.xml

## XML的语法规则

- XML文件的后缀名为：XML
- 文档声明必须是第一行的第一格

```xml
<?xml version="1.0" encoding="UTF-8"?>
version:XML默认的版本号码、该属性是必须存在的
encoding:本XML文件的编码
```

## XML的标签(元素)规则

- 标签由一对尖括号和合法标识符组成：<name> </name>，必须存在一个根标签，有且只能有一个
- 标签必须成对出现，有开始，有结束：<name> </name>
- 特殊的标签可以不成对，但是必须有结束标记，如：<br/>
- 标签中可以定义属性，属性和标签名空格隔开，属性值必须用引号引起来<student id = "1"></student>
- 标签需要正确的嵌套

## XML的其他组成

- XML文件中可以定义注释信息：<!- 注释内容 -->

- XML文件中可以存在以下特殊字符

  ![](C:\Users\LL\Desktop\Java笔记\XML文件特殊符号.png)

- XML文件中可以存在CDAT区：<![CDATA[......内容]]>

## XML文档约束方式一-DTD约束[了解]

什么是约束文档？

问题：由于XML文件可以自定义标签，导致XML文件可以随意定义，程序在解析的时候可能出现问题

- 文档约束：是用来限定xml文件中的标签以及属性应该怎么写(以强制约束程序员必须按照文档约束的规则来编写xml文件)

### 文档约束的分类

- DTD
- schema

需求：利用DTD文档约束，约束一个XML文件的编写

分析：

1. 编写DTD文档约束，后缀必须是.dtd

   ```dtd
   <!ELEMENT 书架(书+)>
   <!ELEMENT 书(书名,作者,售价)>
   <!ELEMENT 书名(#PCDATA)>
   <!ELEMENT 作者(#PCDATA)>
   <!ELEMENT 售价(#PCDATA)>
   ```

2. 在需要编写的XML文件中导入该DTD约束文档

3. 按照约束的规定编写XML文件的内容

### 导入dtd

- 导入本地dtd(掌握)

  | <!DOCTYPE 根元素名称 SYSTEM'DTD文件的路径'> |
  | ------------------------------------------- |

- 在xml文件内部引入(了解)

  | <!DOCTYPE 根元素名称 [dtd文件内容]> |
  | ----------------------------------- |

- 引入网络dtd(了解)

  | <!DOCTYPE 根元素名称 PUBLIC"DTD文件名称" "DTD文档的URL"> |
  | -------------------------------------------------------- |

XML的文档约束-DTD的作用和问题

- 可以约束XML文件的编写
- 不能约束具体的数据类型

## XML文档约束方式二-schema约束[了解]

- schema可以约束具体的数据类型，约束能力上更强大
- schema本身也是一个xml文件，本身也受到其他约束文件的要求，所以编写的更加严谨

### schema的使用

需求：利用schema文档约束，约束一个XML文件的编写

分析：

1. 编写schema约束文档，后缀必须是.xsd，具体的形式到代码中观看
2. 在需要编写的XML文件中导入该schema约束文档
3. 按照约束内容编写XML文件的标签

![](C:\Users\LL\Desktop\Java笔记\schame约束.png)

![](C:\Users\LL\Desktop\Java笔记\schema书写.png)

### schema约束文件步骤

步骤：

1. 在更标签上定义属性

   xmlns="https://www.w3.org/2001/XMLSchema-instance"

2. 写第二个xmlns表示被哪个schema文件约束

3. 给某一个xmlns属性添加一个标识，用于区分

   格式为：xmlns:标识="名称空间地址"。

   ​	标识可以是任意的，但是一般取值都是xsi

4. 通过xsi:schemaLocation指定名称空间所对应的约束文件路径格式为：xsi:schemaLocation="名称空间 文件路径"

### 关于schema需要掌握的

[案例](E:\ideacode\myxml\src\com\yhr\myschemademo\a.xml)

## XML的解析

两种方式：

- SAX：
- DOM：

### 优缺点：

- SAX：不会把整体的xml文件都加载到内存，而是从上往下逐行进行扫描

  缺点：只能读取，不能添加，不能删除

  优点：因为他是逐行扫描不需要把整体的xml文件都加载到内存，所以他可以解析比较大的xml文件

- DOM：会把整体的xml文件都加载到内存

  会把这个整体在内存中形成一个树形结构，我们可以通过这个树形结构去解析xml文件

  优点：可以读取，可以添加，可以删除，可以做任何事情

  缺点：需要xml文件全部加载到内容，所以不能解析非常大的xml文件(现在可以忽略不计)

## Dom常见的解析工具

| 名称  | 说明                                                         |
| ----- | ------------------------------------------------------------ |
| JAXP  | SUN公司提供的一套XML的解析的API                              |
| JDOM  | JDOM是一个开源项目，它基于树型结构，利用纯JAVA的技术对XML文档实现解析、生成、序列化以及多种操作 |
| Dom4j | 是JDOM的升级品，用来读写XML文件的，具有性能优异、功能强大和及其易使用的特点，它的性能超过sun公司官方的dom技术，同时它也是一个开放源代码的软件，Hibernate也用它来读写配置文件 |
| jsoup | 功能强大DOM方式的XML解析开发包，尤其对HTML解析更加方便       |

## Dom解析思想

![](C:\Users\LL\Desktop\Java笔记\Dom解析思想.png)

- Document对象：整个xml文档
- Element对象：标签
- Attribute对象：属性
- Text对象：文本内容

后面三个的父类是Node对象

## Dom4j解析出XML文件

需求：使用Dom4j把一个XML文件的数据进行解析

分析：

1. 下载Dom4j框架，官网下载[官网](http://dom4jgithub.io/)
2. 在项目中创建一个文件夹：lib
3. 将dom4j-2.1.1.jar文件复制到lib文件夹
4. 在jar文件上点右键，选择add as library->点击OK
5. 在类中导包使用

## Dom4j解析XML-得到Document对象

### SAXReader类

| 构造方法                  | 说明                        |
| ------------------------- | --------------------------- |
| public SAXReader()        | 创建Dom4J的解析器对象       |
| Document read(String url) | 加载XML文件成为Document对象 |

### Document类

| 方法名                   | 说明           |
| ------------------------ | -------------- |
| Element getRootElement() | 获得根元素对象 |

## Dom4j解析XML的元素、属性、文本

| 方法名                              | 说明                                                       |
| ----------------------------------- | ---------------------------------------------------------- |
| List<Element> elements()            | 得到当前元素所以子元素                                     |
| List<Element> elements(String name) | 得到当前元素下指定名字的子元素返回集合                     |
| Element element(String name)        | 得到元素下指定名字的子元素，如果有很多名字相同的返回第一个 |
| String getName()                    | 得到元素名字                                               |
| String attributeValue(String name)  | 通过属性名直接得到属性值                                   |
| String elementText(子元素名)        | 得到指定名称的子元素的文本                                 |
| String getText()                    | 得到文本                                                   |

## 解析XML文件并登录

小细节：布尔类型写Javabean的时候他不是get方法，时is方法

[案例](E:\ideacode\myxml\src\com\yhr\mydom4j\User.java)

## XPath和绝对路径检索

如果需要从XML文件中检索需要的某个信息(如name)怎么解决

- Dom4j需要进行文件的全部解析，然后再寻找数据
- Xpath技术更加适合做信息检索

### XPath介绍

- XPath再解析XML文档方面提供了一独树一帜的路径思想，更加优雅，高效
- XPath使用路口表达式来定位XML文档中的元素节点或属性节点

示例(users/user/username)

- /元素/子元素/孙元素
- //子元素//孙元素

### 使用Xpath检索出XML文件

需求：使用Dom4j把一个XML文件的数据进行解析

分析：

1. 导入jar包(dom4j和jaxen-1.1.2.jar)，Xpath的技术依赖Dom4j技术
2. 通过dom4j的SAXReader获取Document对象
3. 利用XPath提供的API，结合XPath的语法完成先去XML文档元素节点进行解析操作
4. Document中与Xpath相关的API如下：

| 方法名                           | 说明                     |
| -------------------------------- | ------------------------ |
| Node selectSingNode("表达式")    | 获取符合表达式的唯一元素 |
| List<Node> selectNodes("表达式") | 获取符合表达式的元素集合 |

### XPath的四大检索方案

- 绝对路径
- 相对路径
- 全文检索
- 属性查找

#### XPath：绝对路径

- 采用绝对路径获取从根节点开始逐层的查找/contactList/contactList/contact/name节点列表并打印信息

| 方法名                | 说明                                   |
| --------------------- | -------------------------------------- |
| /根元素/子元素/孙元素 | 从根元素开始，一级一级向下查找不能跨级 |

#### XPath：相对路径

- 先得到根节点contactList
- 再采用相对路径获取下一级contact节点的name节点并打印信息

| 方法名          | 说明                                       |
| --------------- | ------------------------------------------ |
| ./子元素/孙元素 | 从当前元素开始，一级一级向下查找，不能跨级 |

#### XPath：全文搜索(检索)

- 直接全文搜索所有的name元素并打印

| 方法名          | 说明                                                       |
| --------------- | ---------------------------------------------------------- |
| //contact       | 找contact元素，无论元素在哪里                              |
| //contact/name  | 找contact，无论在哪一段，但name一定是contact的子节点       |
| //contact//name | contact无论在哪一种，name只要是contact的子孙元素都可以找到 |

总结：

- 路径里面如果只有一个/表示单级路径
- 路径里面如果有//表示，单级的可以，多级的也可以

#### XPath：属性查找

- 在全文中搜索属性，或者带属性的元素

| 方法名               | 说明                                                     |
| -------------------- | -------------------------------------------------------- |
| //@属性名            | 查找属性对象，无论哪个元素，只要有这个属性即可           |
| //元素[@属性名]      | 查找元素对象，全文搜索指定元素名和属性名                 |
| //元素[@属性名="值"] | 查找元素对象，全文搜索指定元素名和属性名，并且属性值相等 |

语法

``` java
//属性名             举例：//@id                   查的是属性
//元素[@属性名]       举例：//person[@id]           查标签，带有指定属性的标签
//元素[@属性名=值]     举例：//person[@id='1']       查标签，带有指定属性和属性值的标签
```

## XPath小结

1. Xpath底层依赖于dom4。在刚开始导包的时候一定要把dom4j的jar一起导入

2. 有两个检索方法

   selectNodes("路径")：查询所有

   selectSingleNode("路径")：查询单个的，如果结果有多个，默认返回第一个

3. 四种检索方式

   /：单级路径

   //：多级路径

   @：属性

   绝对路径：一定是从根节点开始

   相对路径：从现在自己节点开始的

   ​                   用根节点调用selectNodes，那么此时就是相当于name标签而言

   全文检索：只要xml文件中存在就可以找到

   ​                 //name 在整个xml中找name这个标签

   ​                 扩展用法：

   ​                 //person/name：先找person，在找person的子标签name

   ​                 //person//name：先找person，再找person的子标签name，但是此时name可以是孙标签

   带属性的查询：

   ​                  查属性：//@属性名 在全文中找指定的属性

   ​                 查带有指定属性的标签：//标签名[@属性名]

   ​                 查带有指定属性的标签：//标签名[@属性名]

   ​                 查带有指定属性值的标签：//标签名[@属性名='值']

# 单元测试

- 单元测试就是争对最小的功能单元编写测试代码，Java程序最小的功能单元是方法，因此，单元测试就是争对java方法的测试，进而检查方法的正确性

目前测试方法是怎么进行的，存在什么问题？

- 只有一个main方法，如果一个方法的测试失败了，其他方法测试会收到影响
- 需要程序员自己去观察测试是否成功

## junit单元测试框架

- JUnit是使用Java语言实现的单元测试框架，它是开源的，Java开发者都应当学习并使用JUnit编写单元测试
- 此外，几乎所有的IDE工具都集成了JUnit，这样我们就可以直接在IDE中编写并允许JUnit测试，JUnit目前最新版本是5

## JUnit优点

- JUnit可以灵活的选择执行哪些测试方法，可以一间执行全部测试方法
- 单元测试中的某个方法测试失败了，不会影响其他测试方法的测试
- 允许成功是绿色，允许失败是红色

##  单元测试快速入门

需求：使用单元测试进行业务方法预期结果，正确性测试的快速入门

分析：

1. 将JUnit的jar包导入到项目中
   - IDEA通常整合好了JUnit框架，一般不需要导入
   - 如果IDEA没有整合好，需要自己手工导入如下2个JUnit的jar包到模块
2. 编写测试方法：该测试方法必须是公共的无参数返回值的非静态方法
3. 在测试方法上使用@Test注解：标注该方法是一个 测试方法

## Junit在实际开发中的作用

以后实际开发中，如果想要测试一个方法是否正确，并不是直接在当前方法的上面写@Test的，而是，自己独立编写一个测试类。（不要写main方法）

在这个类中，编写一些方法

在方法里面调用要被测试的方法即可

举例：测试JunitDemo1类中的add方法[例子](E:\ideacode\myjunit\src\com\yhr\a01junitdemo1\JunitDemo2.java)

 扩展点：在单元测试中，有个东西叫断言

断言：也就是拿预计结果和实际结果作比较

参数一：当两个结果一样的时候，出现的提示消息

参数二：实际允许的结果

参数三：预计结果

```java
Assert.assertEquals(参数一,参数二,参数三);
```

### Junit常用注解(Junit4.xxxx版本)

| 注解         | 说明                                                       |
| ------------ | ---------------------------------------------------------- |
| @Test        | 调试方法                                                   |
| @Before      | 用来修饰实例方法，该方法会在每一个测试方法执行之前执行一次 |
| @After       | 用来修饰实例方法，该方法会在每一个测试方法执行之后执行一次 |
| @BeforeClass | 用来静态修饰方法，该方法会在所有测试方法之前只执行一次     |
| @AfterClass  | 用来静态修饰方法，该方法会在所有测试方法之后只执行一次     |

- 开始执行方法：初始化资源
- 执行完之后的方法：释放资源

在实际开发中，真正的完整的单元测试该怎么写

前提：

以后在工作的时间，测试代码不能污染原视频。(修改、篡改)

1. 利用before去对数据做一些初始化的动作
2. 利用Test真正的去测试方法
3. 利用After去还原数据

## 扩展点单元测试中的相对路径

细节：单元测试的相对路径，是相对当前模块的，而不是当前项目

在不同情况下，相对路径是不一样的

# 注解

就是在方法上面的哪些@

Annotation表示注解，是JDK1.5的新特性

注解的主要作用：对我们的程序进行标准，通过注解可以给类增加额外的信息

注解是给编译器或JVM看的，编译器或JVM可以根据注解来完成对应的功能

例如：@Override：检测方法是否是重写方法，如果不是会报错，@Deprecated表示这个方法以及过时

已经过时的方法还可以使用，但是一定会有解决的方法

有些有黄色波浪线警告信息，不想它显示可以用@SuppressWarnnings("all")

总结：

@Override：表示方法的重写

@Deprecated：表示修饰的方法已过时

@SuppressWarnings("all")：压制警告

除此之外，还需要掌握第三方框架中提供的注解

比如：Junit的

@Test：表示允许测试方法

@Before：表示在Test之前允许的，进行数据的初始化

@After：表示在Test之后允许的，进行数据的还原

## 自定义注解和使用(了解)

自定义注解单独存在是没有什么意义的，一般会根反射结合起来使用，会有发射去解析注解

针对于注解，只要掌握会使用别人已经写好的注解即可

关于注解的解析，一般是在框架的底层已经写好了

### 自定义注解---格式

- 自定义注解就是自己做一个注解来使用

```java
public @interface 注解名称 {
    public 属性类型 属性名() default 默认值 ;
}
```

数据类型可以是：

基本数据类型，String，Class，注解，枚举，以上类型的一维数组

### 使用自定义注解格式

@主注解名(属性名=值1,属性名2=值2)

注意事项：

- 使用自定义注解时要保证注解每个属性都有值
- 注解可以使用默认值

####  特殊属性

- value属性，如果只有一个value属性的情况下，使用value属性的时候可以省略value名称不写
- 但是如果有多个属性，且多个属性没有默认值，那么value名称是不能省略的

## 元注解

元注解：就是注解注解的注解(写在注解上面的注解)

元注解有两个

- @Target：约束自定义注解只能在哪些地方使用
- @Retention：申明注解的生命周期

### @Target

@Target中可使用的值定义在ElementType中，常用值如下

- TYPE，类，接口
- FIEL，成员变量
- METHOD，成员方法
- PARAMETER，方法参数
- CONSTRUCTOR，构造器
- LOCAL_VARIABLE，局部变量

### @Retention

@Retention中可使用的值定义在RetentionPolicy枚举类中，常用值如下

- SOURCE：注解只作用在源码阶段，生成的字节码文件中不存在
- CLASS：注解作用在源码阶段，字节码文件阶段，运行阶段不存在，默认值
- RUNTIME：注解作用在源码阶段，字节码文件阶段，运行阶段（开发常用）

## 注解解析

模拟Junit框架

需求：

- 定义若干个方法，只要加了MyTest注解，就可以在启动时被触发执行

分析

1. 定义一个自定义注解MyTest，只能注解方法，存活范围是一直都在
2. 定义若干个方法，只要有@MyTest注解的方法就能在启动时被除法执行，没有这个注解的方法不能执行

注解解析

- 注解的操作中经常需要进行解析，注解的解析就是判断是否存在注解，存在注解就解析出内容

与注解解析相关的接口

- Annotation：注解的顶级项目
- 可以利用反射解析注解

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Annotation[] getDeclaredAnnotations()                        | 获得当前对象上使用的所有注解，返回注解数组                   |
| T getDeclaredAnnotation(Class<T> annotationClass)            | 根据注解类型获得对应注解对象                                 |
| boolean isAnnotationPresent(Class<Annotation> annotationClass) | 判断当前对象是否使用了指定的注解，如果使用了则返回true，否在false |

- 所有的类成分Class，Method，Field，Constructor，都实现了AnnotatedElement接口他们都用于解析注解的能力：

### 解析注解的技巧

- 注解在哪个成分上，我们就先拿哪个成分对象
- 比如注解作用成员方法，则要获得该成员方法对应的Method对象，再来拿上面的注解
- 比如注解作用在类上，则要该类的Class对象，再来拿上面的注解
- 比如注解作用在成员变量上，则要获得该成员变量对应的Field对象，再来拿上面的注解

[案例](E:\ideacode\myannotation\src\com\yhr\myannotation2\MyAnnoDemo.java)

# 枚举

## 枚举类

JDK1.5出现每个枚举值都需要调用一次构造函数

## 需求

- 创建一个季节类：spring，summer，autumn，winter
- 只读，不需要修改

热点：这个类的对象是固定的->春夏秋冬->利用枚举可以方便的解决

## 自定义枚举

1. 构造器私有化
2. 本类内部创建一组对象 四个春夏秋冬
3. 对外暴露对象->通过为对象添加public final static 修饰符
4. 可以提供get方法，但是不要提供set方法

[案例](E:\ideacode\myenumeration\src\com\yhr\testEnum.java)

## eumn枚举类注意事项

1. eumn枚举类，默认会继承Enum类，而且一个final类型的

   通过反编译javap我们能看到

2. enum枚举类不能再继承其他

   - 因为enum会隐式继承Eunm-Java是继承机制
   - 枚举类和普通类一样，可以实现接口

3. 通过反编译我们可以看到enum枚举类，编译时会字段给我们台添加一些代码

   - final
   - 隐式继承eunm
   - 隐式添加values方法->甚至我们再父类Enum中都找不到values方法

4. 命名规范

   - 规则和类一样
   - 要遵循常量命名规则，要大写，且枚举类名要与类名有一些关联性

## 常用方法

1. name；
2. toString
3. ordinal(查看索引)
4. values(获取枚举的所有字段)
5. valueOf(返回指定的枚举类的那个字段)
6. compareTo(两个索引相减)

[案例](E:\ideacode\myenumeration\src\com\yhr\Season.java)

# 小细节

如果记事本编写

![](1.png)

