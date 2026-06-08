---
title: javaweb
slug: javaweb
date: 2025-04-01 10:00:00
tags:
  - 后端
  - javaweb
summary: 这是一篇关于 javaweb 的入门文章。
cover: https://ll-java-web.oss-cn-chengdu.aliyuncs.com/2025/04/c0a3df8c-aa10-4c41-bfcc-34539acadbe8.jpg
---
# JavaWeb

[讲义地址](https://heuqqdmbyk.feishu.cn/wiki/space/7413668442156498972?ccm_open_type=lark_wiki_spaceLink&open_tab_from=wiki_home)

# CSS

详细见CSSmd文档

打

# JS

详细见JSmd文档，这个只是简略

## 引入方式

内部脚本：将JS代码定义在HTML页面中

- JavaScript代码必须位于< script >  < /script >
- 在HTML文档中，可以在任意地方，放置任意数量的<script>
- 一般会把脚本置于<body>元素的底部，可改善显示速度

外部脚本：将JS代码定义的外部JS文件中，然后引入到HTML页面中

## 基本语法

### 变量

- JS中用let关键字来声明变量(弱类型语言，变量可以存放不同类型的值)
- 变量名需要遵循如下规则：
  - 只能用字母、数字、下划线_、美元符号$组成，且数字不能开头
  - 变量名严格区分大小写，如name和Name是不同的变量
  - 不能使用关键字，如：let、var、if、for等

```js
<script>
	let a = 20;
	a = "Hellow";
	alter(a);
</script>
```

- JS中用const关键字来声明常量
- 一旦声明，常量的值就不能改变（不可以重新赋值）

```js
<script>
    const PI = 3.14;
	console.log(PI);
</script>
```

- window.alert:弹出提示框
- console.log：输出到控制台
- document.write：输出到body区域（不常用）

注意：在早期的js中，声明变量还可以使用var，但是并不严谨(不推荐)

### 数据类型

分为基本数据类型和引用数据类型

- 基本数据类型：
  - number：数字（整数、小数、NaN(Not a Number)）
  - boolean：布尔。true，false
  - null：对象为空。JS是大小写敏感的，因此null、Null、NULL是完全不同的
  - undefined：当声明的变量未初始化时，改变量的默认值是undefined
  - string：字符串，单引号、双引号、反引号(就是tab上面那个键)皆可，推荐使用单引号

使用typeof运算符可以获取数据类型

- 模板字符串语法：

  - ``（反引号）
  - 内容拼接变量时，使用${}包住变量

  ```js
  <script>
      let name = 'Tom';
  	let age = 18;
  	console.log('大家好，我是新入职的'+name+',今年'+age+'岁了，请多多关照');
  	console.log(`大家好，我是新入职的${name},今年${age}岁了，请多多关照`);
  </script>
  ```

### 函数

- 介绍：函数就是java的方法

- 定义：js中的函数通过function关键字进行定义，语法为：

  ```js
  function functionName(参数1，参数2...){
      //执行的代码
  }
  
  function add(a,b){
      return a+b;
  }
  ```

- 调用：函数名称（实际参数列表）

  ```js
  let result = add(10,20);
  alert(result);
  ```

注意：由于JS是弱类型语言、新参、返回值都不需要指定类型，在调用函数时，实参个数与形参个数可以不一致，但是建议一致

#### 匿名函数

匿名函数是指一种没有名称的函数，可以通过两种定义方式定义：函数表达式和箭头函数

```js
1.函数表达式
let add = function(a,b){
    return a+b;
}
2.箭头函数
let add = (a,b) => {
    return a+b;
}
```

调用的话可以直接通过变量名直接调用

```js
let result = ad(10,20);
alert(ersult);
```

注意：JS是弱类型语言，定义函数时，形参、返回值都无需指定类型

### 自定义对象

也就是javabean

- 定义格式

```js
let 对象名 = {
    属性名1:属性值1,
    属性名2:属性值2,
    属性名3:属性值3,
    方法名：function（形参列表）{}
}


let user = {
    name: 'Tom',
    age: 20,
    gender: '男',
    sing: function(){
        alert(this.name+'唱着最炫的名族风')
    }
}
省略：
let user = {
    name: 'Tom',
    age: 20,
    gender: '男',
    sing(){
        alert(this.name+'唱着最炫的名族风')
    }
}
```

- 定义格式

  ```js
  对象名.属性名;
  对象名.方法名();
  
  console.log(user.name);
  user.sing();
  ```

注意：在对象里面不要定义箭头函数，不然this会出问题，因为在箭头函数中，this指向的是当前对象的父级

### json

- 概念：JavaScript Object Notation，JavaScript对象标记法（JS对象标记法书写的文本）
- 由于语法简单，层次结构鲜明，先多用于作为数据载体，在网络中进行数据传输

例子：

```js
{
    "name":"Tom",
    "age":20,
    "gender":"男"
}
除了数字和布尔，其他都要用双引号引起来
```

- JSON.stringify(...)：将js对象转换成json文本对象
- JSON.parse(..)：将json文本转换成js对象

例子：

```js
let person = {
    name: 'ityhr',
    age: 18,
    gender: '男'
}
alert(JSON.stringify(person));
let personJson = '{"name":"ityhr","age":18,"gender":"男"}';
alert(JSON.parse(personJson).name);
```

### DOM

- 概念：Document Object Model，文档对象模型
- 将标记语言的各个组成部分封装为对应的对象：
  - Document：整个文档对象
  - Element：元素对象
  - Attribute：属性对象
  - Text：文本对象
  - Comment：注释对象

DOM树：
![](C:\Users\LL\Desktop\javaweb\DOM树.jpg)

- Js通过DOM，就能够对HTML进行操作：
  - 改变HTML元素的内容
  - 改变HTML元素的样式（CSS）
  - 对HTML DOM 事件作出反应
  - 添加和删除HTML元素

#### DOM操作

- DOM操作核心思想：将网页中所有的元素当做对象来处理。（标签的所有属性在改对象上都可以找到）

- 操作步骤：

  - 获取要操作的DOM元素对象
  - 操作DOM对象的属性或方法（查文档或AI）

- 获取DOM对象

  - 根据CSS选择器来获取DOM元素，获取匹配到的第一个元素：document.querySelector('选择器') 

  - 根据CSS选择器来获取DOM元素，获取匹配到的所有元素：document.querySelectorAll('选择器')

    注意：得到的是一个NodeList节点集合，是一个伪数组（有长度、有索引的数组）

  - 其他方式（了解，这几个过时了）

    - document.getElementById('id');
    - document.getElementsByTagName('div');
    - document.getElementByClassName('cls');

### 事件监听

js可以在事件触发时，就立即调用一个函数做出响应，也称为事件绑定或注册事件

- 语法：事件源.addEventListener('事件类型'，事件触发执行的函数)

- 事件监听三要素

  - 事件源：哪个dom元素触发了事件，要获取dom元素
  - 事件类型：用什么方式触发，比如：鼠标单机 click
  - 事件触发执行的函数：要做什么事

  ```js
  <input id="btn" type="button" value="点我一下试试2">
  <script>
      document.querySelector('#bin').addEventListener('click',()=>{
      alert('试试就试试');
  })
  </script>
  ```

- 早期版本写法(了解)：事件源.on事件 = function(){....}

  ```javascript
  <input id="btn" type="button" value="点我一下试试">
  <script>
      document.querySelector('#btn').onclick = function(){
      	alert('试试就试试');
  	}
  </script>
  ```

addEventiListener可以多次绑定同一 事件

onclick如果多次绑定同一事件，后面会覆盖前面的

区别：on方式会覆盖，addEventListener方式可以绑定多次，拥有更多的特性，推荐使用

#### 常见事件

- 鼠标事件 click：鼠标点击  mouseenter：鼠标移入  mouseleave：鼠标移出
- 键盘事件 keydown：键盘按下触发   keyup：键盘抬起触发
- 焦点事件 focus：获得焦点触发   blur：失去焦点触发
- 表单事件 input：用户输入时触发  submit：表单提交时触发

# Vue

vue是一款用于构建用户界面的渐进式的js框架。(官方：https://cn.vuejs.org/)

## 快速入门

- 准备
  - 引入Vue模块（官方提供）
  - 创建Vue程序的应用实例，控制视图的元素
  - 准备元素（id），被Vue控制
- 数据驱动视图
  - 准备数据
  - 通过插值表达式渲染页面

```vue
<div id="app">
    <h1>{{message}}</h1>
</div>
<script type="module">
    import {createApp,ref} from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js';
    createApp({
        data(){
            return {
                message:"Hello Vue"
            }
        }
    }).mount("#app");
</script>
```

## 常用指令

- 指令：HTML标签上带有v-前缀的特殊属性，不同的指令具有不同的含义，可以实现不同的功能

- 常用指令

  | 指令                  | 作用                                                |
  | --------------------- | --------------------------------------------------- |
  | v-for                 | 列表渲染，遍历容器的元素或者对象的属性              |
  | v-bind                | 为HTML标签绑定属性值，如设置href，css样式等         |
  | v-if/v-else-if/v-else | 条件性的渲染某元素，判定为true时渲染，否则不渲染    |
  | v-show                | 根据条件展示某元素，区别在于切换的是display属性的指 |
  | v-model               | 在表单上创建双向数据绑定                            |
  | v-on                  | 为HTML标签绑定事件                                  |

### v-for

- 作用：列表渲染，遍历容器的元素或者对象的属性

- 语法：

  ```vue
  <tr v-for="(item,index) in item":key="item.id"> {{item}}</tr>
  ```

- 参数说明：

  - items为遍历的数组
  - item为遍历出来的元素
  - index为索引/下表，从0开始：可以省略，省略index语法：v-for="item in items"

- key

  - 作用：给元素添加的唯一标识，便于vue进行列表项的正确排序复用，提升渲染性能
  - 推荐使用id作为key（唯一），不推荐使用index作为key（会变化，不对应）

注意：遍历的数组，必须在data中定义；要想让哪个标签循环展示多次，就在哪个标签上使用 v-for指令

[]:是在js中定义的数组

注意：插值表达式不能写在标签内部

### v-bind

- 作用：动态为HTML标签绑定属性值，如设置href，src，style样式

- 语法：v-bind:属性名="属性值"

  ```vue
  <img v-bind:src="item.image" width="30px">
  ```

- 简化： :属性名="属性值"

  ```vue
  <img :src="item.image" width="30px">
  ```

注意：动态的为标签的属性绑定值，不能使用插值表达式，得使用v-bind指令。且绑定的数据，必须在data中定义

### v-if&v-show

- 作用：这两类指令，都是用来控制元素的显示与隐藏的
- v-if
  - 语法：v-if="表达式"，表达式值为 true，显示；false，隐藏
  - 原理：基于条件判判断，来控制创建或移除元素节点（条件渲染）
  - 场景：要么显示，要么不显示，不频繁切换的场景
  - 其他：可以配合v-else-if/v-else进行链式调用条件判断
- v-show
  - 语法：v-show="表达式"，表达式值为 true，显示；false，隐藏
  - 原理：基于CSS样式display来控制显示与隐藏
  - 场景：频繁切换显示隐藏的场景

注意：v-else-if必须出现在v-if之后，可以出现多个；v-else必须出现在v-if/v-else-if之后

### v-model

- 作用：在表单元素上使用，双向数据绑定，可以方便的获取或设置表单数据
- 语法：v-model="变量名"

```vue
createApp({
	data(){
		return{
		searchForm:{
			name:'',
			gender:'',
			job:''
			}
		}
	}
}).mount('#container')
```

```vue
<input type="text" id="name" v-model="searchForm.name">
```

注意：v-model中绑定的变量，必须在data中定义

### v-on

- 作用：为html标签绑定事件（添加事件监听）
- 语法
  - v-on：事件名="方法名"
  - 简写为 @事件名="..."

```vue
<div id="app">
    <button type="button" v-on:click="handle">点我</button>
    //简写
    <button type="button" @click="handle">再点我</button>
</div>
```

```vue
const app = createApp({
	data(){
		//...
	},
	methods:{
		handle(){
			console.log('试试就试试');
		}
	},
}).mount("app");
```

## Ajax

- 介绍：Asynchronous JavaScript And XML，异步的JavaScript和XML

- 作用：

  - 数据交换：通过Ajax可以给服务器发送请求，并获取服务器响应的数据
  - 异步交互：可以再不重新加载整个页面的情况下，与服务器交换数据并更新部分网页的技术，如：搜索联想、用户名是否可用的校验等等

  ![](C:\Users\LL\Desktop\javaweb\异步和同步.jpg)

### Axios

- 介绍：Axios对原生的Ajax进行了封装，简化了书写，快速开发

- 官网：https://www.axios-http.cn/

- 步骤：

  - 引入Axios的js文件（参考官网）

  - 使用Axios发送请求，并获取响应结果

    ```js
    <script scr="https://unpkg.com/axios/dist/axios.min.js"></script>
    ```

    ```javascript
    axios({
        method: 'GET',
        url: 'https://web-server.ityhr.net/emps/list'
    }).then((result) => {//这两行为成功回调函数
        console.log(result.data);
    }).catch((err) => {//这个为失败回调函数
        alert(err);
    });
    ```

    method：请求方式，GET/POST

    url：请求路径

    data：请求数据(POST)

    params：发送请求时携带的url参数 如：...?key=val

- 为了方便起见，Axios已经为所有支持的请求方法提供了别名

- 格式：axios.请求方法(url[,data[,config]])

  ```vue
  axios.get('https://mock.apifox.cn/m1/3083103-0-default/emps/list').then((result) => {
  	console.log(result.log);
  }).catch((err) => {
  	console.log(err);
  });
  ```

  ```vue
  axios.get('https://mock.apifox.cn/m1/3083103-0-default/emps/update').then((result) => {
  	console.log(result.log);
  }).catch((err) => {
  	console.log(err);
  });
  ```

  快捷键thenc

### async&await

- 可以通过async、await可以让异步变为同步操作。async就是来声明一个异步方法，await是用来等待异步任务执行

  ```vue
  methods: {
  	async search(){
  		//根据用户输入的搜索条件，基于axios发送异步请求(https://web-server.itheima.net/emps/list)到服务端...
  		let result = await axios.get('https://web-server.itheima.net/emps/list?name=xxx&gender=xxx&job=xxx');
  		this.employees = result.data.data;
  	}
  }
  ```

注意：await关键字只在async函数内有效，await关键字取代then函数，等待获取到请求成功的结果值

## Vue的生命周期

- 生命周期：指一个对象从创建到销毁的整个过程
- 生命周期的八个阶段：每触发一个生命周期事件，会自动执行一个生命周期方法(钩子)。

![](C:\Users\LL\Desktop\javaweb\Vue生命周期.jpg)

| 状态          | 阶段周期   |
| ------------- | ---------- |
| beforeCreate  | 创建前     |
| created       | 创建后     |
| beforeMount   | 载入前     |
| mounted       | 挂载完成   |
| beforeUpdate  | 数据更新前 |
| update        | 数据更新后 |
| beforeUnmount | 组件销毁前 |
| unmounted     | 组件销毁后 |

```vue
<script type="module">
	import {createApp} from 'https://.../vue.esm-browser.js'
    const app = createApp({
        data(){
            return {
                message: "Hello Vue"
            }
        },
        //生命周期-钩子函数mounted
        mounted() {
            console.log('Vue挂载完成，发送请求获取数据...');
        }
    }),mount("#app");
</script>
```

# Maven

- Maven是一款用于管理和构建Java项目的工具，是apache旗下的一个开源项目

Apache官网：https://www.apache.org/index.html#projects-list

作用：

- 依赖管理：方便快捷的管理项目依赖的资源(jar包)
- 项目构建
- 统一项目结构

![](C:\Users\LL\Desktop\javaweb\Maven结构.jpg)

仓库：用于存储资源，管理各种jar包

- 本地仓库：自己计算机上的一个目录
- 中央仓库：由Maven团队维护的全球唯一的。仓库地址：https://repo1.maven.org/maven2/
- 远程仓库(私服)：一般由公司团队搭建的私有仓库

安装步骤看视频32

cmd输入mvn -v能看到版本号就可以

## Maven的坐标

- Maven中的坐标是资源（jar）的唯一标识，通过该坐标可以唯一定位资源位置
- 使用坐标来定义项目或引入项目中需要的依赖

### Maven坐标主要组成

- groupID：定义当前maven项目隶属于组织名称（通常域名反写，例如：com.ityhr）
- artifactID：定义当前Maven项目名称（通常是模块名称，例如：order-service、goods-service）
- version：定义当前项目版本号
  - SNAPSHOT：功能不稳定，尚处于开发者的版本，即快照版本
  - RELEASE：功能趋于稳定、当前更新停止，可以用于发行的版本

```xml
<groupId>com.ityhr</groupId>
<artifactId>java-study</artifactId>
<version>1.0-SNAPSHOT</version>
```

导入Maven项目注意

- 建议将要导入的maven项目复制到你的项目目录下
- 建议选择maven项目的pom.xml文件进行导入

## 依赖管理

- 依赖：指当前项目允许所需要的jar包，一个项目中可以引入多个依赖
- 配置：
  1. 在pom.xml中编写<dependencies>标签
  2. 在<dependencies>标签中，使用<dependency>引入坐标
  3. 定义坐标groupId，artifactId，version
  4. 点击刷新按钮，引入最新加入的坐标

```XML
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.4</version>
</dependency>
```

注意：如果不知道依赖的坐标信息，可以到https://mvnrepository.com/中搜索

### 排除依赖

指主动断开依赖的资源，被排除的资源无需指定版本

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.4</version>
    
    <exclusions>
        <exclusion>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-observation</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

注意：

- 一旦依赖配置变更了，急得重新加载
- 引入的依赖本地仓库不存在，记得联网

## 生命周期

Maven的生命周期就是为了所有的maven项目构建过程进行抽象和统一

Maven中有3套相互独立的生命周期

- clean：清理工具
- default：核心工作，如：编译、测试、打包、安装、部署等
- site：生成报告、发布站点等

![](C:\Users\LL\Desktop\javaweb\Vue的生命周期阶段.jpg)

- clean：移除上一次构建生成的文件
- complie：编译项目源代码
- test：使用合适的单元测试框架运行测试(junit)
- package：将编译后的文件打包、如：jar、war等
- install：安装项目到本地仓库

注意：在<b>同一套</b>>生命周期中，当运行后面的阶段时，前面的阶段都会运行

执行指定生命周期的两种方式：

- 在idea中，右侧的maven工具栏，选中对应的生命周期，双击执行
- 在命令行中，通过命令执行

也可以用cmd来执行命令：mvn后面加要运行的东西，例如：mvn clean

## 测试

- 测试：是一种用来促进鉴定软件的正确性、完整性、安全性和质量的过程
- 阶段划分：单元测试、集成测试、系统测试、验收测试
- 测试方法：白盒测试、黑盒测试及灰盒测试

阶段划分介绍：

- 单元测试(白盒测试)
  - 介绍：对软件的基本组成单位进行测试
  - 目的：验收软件基本组成单位的正确性
  - 测试人：开发人员
- 集成测试(灰盒测试)
  - 介绍：将已分别通过测试的单元，按设计要求组合系统或子系统，再进行的测试
  - 目的：检查单元之间的协作是否正确
- 系统测试(黑盒测试)
  - 介绍：对已经集成好的软件系统进行彻底的测试
  - 目的：验证软件系统的正确性、性能是否满足指定的要求
  - 测试人员：测试人员
- 验收测试(黑盒测试)
  - 介绍：交付测试，是争对用户需求、业务流程进行的正式的测试
  - 目的：验证软件系统是否满足验收标准
  - 测试人员：客户/需求方

单元测试划分：

- 白盒：
  - 清楚软件内部结构、代码逻辑
  - 用于验收代码、逻辑正确性
- 黑盒
  - 不清楚软件内部结构、代码逻辑
  - 用于验证软件的功能、兼容性等方面
- 灰盒
  - 结合了白盒测试和黑盒测试的特点，既关注软件的内部结构又考虑外部表现（功能）

### 单元测试

再上个javase笔记里面有详解

注意：JUnit单元测试类名命名规范为：XxxxxTest[规范]。JUnit单元测试的方法，必须声明为public void [规定]

### 断言

JUnit提供了一些辅助方法，用来帮我们确定被测试的方法是否按照预期的效果正常工作，这种方式称为断言

| 断言方法                                                     | 描述                                     |
| ------------------------------------------------------------ | ---------------------------------------- |
| Assertions.assertEquals(Object exp,Object act,[String msg])  | 检查两个值是否相等，不相等就报错         |
| Assertions.assertNotEquals(Object exp,Object act,[String msg]) | 检查两个值是否不相等，相等就报错         |
| Assertions.assertNull(Object act,[String msg])               | 检查对象是否为null，不为null，就报错     |
| Assertions.assertNotNull(Object act,[String msg])            | 检查对象是否不为null，为null，就报错     |
| Assertions.assertTrue(boolean condition,[String msg])        | 检查条件是否为true，不为true，就报错     |
| Assertions.assertFalse(boolean condition,[String msg])       | 检查条件是否为false，不为false，就报错   |
| Asserttions.assertThrows(Class expType,Executable exec,S[tring msg]) | 检查两个对象引用是否相等，不相等，就报错 |

里面的String msg就是报错所提示的信息，可以不指定（有对应的重载方法）

### JUnit常见注解

| 注解               | 说明                                                         | 备注                            |
| ------------------ | ------------------------------------------------------------ | ------------------------------- |
| @Test              | 测试类中方法用它修饰才能成为测试方法，才能启动执行           | 单元测试                        |
| @ParameterizedTest | 参数化测试的注解(可以让单个测试运行多次，每次运行时仅参数不同) | 用了该注解，就不需要@Test注解了 |
| @ValueSoure        | 参数化测试的参数来源，赋予测试方法参数                       | 与参数化测试注解配合使用        |
| @DisplayName       | 指定测试类、测试方法显示的名称（默认为类名、方法名）         |                                 |
| @BeforeEach        | 用来修饰一个实例方法，该方法会在每一个测试方法执行执行之前执行一次 | 初始化资源(准备工作)            |
| @AfterEach         | 用来修饰一个实例方法，该方法会在每一个测试方法执行执行之后执行一次 | 释放资源(清理工作)              |
| @BeforeAll         | 用来修饰一个静态方法，该方法会在所有测试方法之前只执行一次   | 初始化资源(准备工作)            |
| @AfterAll          | 用来修饰一个静态方法，该方法会在所有测试方法之后只执行一次   | 释放资源(清理工作)              |

### 企业开发规则

原则：编写测试方法时，要尽可能 的覆盖业务方法中所可能的情况（尤其是边界值）

### Maven依赖范围

- 依赖的jar包，默认情况下，可以再任何地方使用。可以通过<scope>...</scope>设置其作用范围
- 作用范围：
  - 主程序范围有效。（main文件夹范围内）
  - 测试程序范围有效。（test文件夹范围内）
  - 是否参与打包运行。（package指令范围内）

````xml
<dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.1</version>
    		<scope>test</scope>
</dependency>
````

| scope值       | 主程序 | 测试程序 | 打包(运行) | 范例        |
| ------------- | ------ | -------- | ---------- | ----------- |
| compile(默认) | Y      | Y        | Y          | log4j       |
| test          | -      | Y        | -          | junit       |
| provided      | Y      | Y        | -          | servlet-api |
| runtime       | -      | Y        | Y          | jdbc驱动    |

## Maven常见问题解决方案

![](C:\Users\LL\Desktop\javaweb\Maven常见问题解决方案.jpg)

注意：重新加载依赖，以来下载了之后，maven面板可能还会报红，此时可以关闭IDEA，重新打开IDEA加载此项目即可

# Web基础

## SpringBootWeb入门

### Spring

- 官网：spring.io
- Spring发展到今天已经形成了一种开发生态圈，Spring提供了若干个子项目，每个项目用于完成特定的功能

### SpringBoot

SpringBoot可以帮助我们非常快速的构建应用程序，简化开发，提高效率

### SpringBootWeb入门程序

1. 创建springboot工程，并勾选web开发相关依赖
2. 定义HelloController类，添加方法hello，并添加注解

```java
@RestController //标识一个请求类
public class HelloController {
    @RequestMapping("/hello")//标识请求路径
    public String hello(String name){
        System.out.println("HelloController...Hello:"+ name)
        return "Hello" + name + "~";
    }
}
```

### Spring官方脚手架连接不上解决方案

把Server URL从：start.spring.io换成start.aliyun.com

为什么一个main方法就将web应用启动

起步依赖：

- spring-boot-starter-web：包含了web应用开发所需要的常见依赖
- spring-boot-starter-test：包含了单元测试所需要的常见依赖
- 官方提供的starter：https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/#using.build-systems.starters

## HTTP协议

概述：超文本传输协议。规定了浏览器和服务器之间数据传输的规则

特点：

1. 基于TCP协议：面向连接、安全
2. 基于请求-响应模型的：一次请求对应一次响应
3. HTTP协议是无状态的协议：对于事务处理没有记忆能力。每次请求-响应都是独立的
   - 缺点：多次请求间不能共享数据
   - 优点：速度快

### 请求协议

![](C:\Users\LL\Desktop\javaweb\HTTP请求数据格式.jpg)

请求行：请求数据第一行（请求方式、格式key：value）

请求头：第二行开始，格式（key：value）

请求体：POST请求，存放请求参数：也就是最后一行那段，(请求头和请求体是通过空格隔开)

常见host(请求头)：

| Host            | 请求的主机名                                                 |
| --------------- | ------------------------------------------------------------ |
| User-Agent      | 浏览器版本，例如Chrome浏览器的标识类似于Mozilla/5.0...Chrome/97，IE浏览器的标识类似Mozilla/5.0(Windows NT ...)like Gecko |
| Accept          | 表示浏览器能接收的资源类型，如text/*，image / * 或者 * / *表示所有 |
| Accept-Language | 表示浏览器偏好的语言，服务器可以据此返回不同语言的网页       |
| Accept-Encoding | 表示浏览器可以支持的压缩类型，例如gzip，deflate等            |
| Content-Type    | 请求主体的数据类型                                           |
| Content-Length  | 请求主体的大小(单位：字节)                                   |

- 请求方式-GET：请求参数的请求行中，没有请求体，如：/brand/findAll?name=OPPO&status=1.GET请求大小在浏览器中是有限制的
- 请求方式-POST：请求参数在请求中，POST请求大小是没有限制的

### 请求数据获取

- Web服务器(Tomact)对HTTP协议的请求数据进行解析，并进行了封装(HttpServletRequest)，调用了Controller方法的时候传递给了该方法，这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷

````java
@RequestMapping("/request")
public String request(HttpServletRequest request){
    //获取请求参数name，age
    String name = request.getParameter("name");//Tom
    //获取请求路径uri和url
	String uri = request.getRequestURI();//request
	String url = request.getRequestURL().toString();//http://localhost:8080/request
    //获取请求头User-Agent
	String userAgent = request.getHeader("User-Agent");//Mozilla/5.0(Windows NT 10.0;Win64;x64)
    //获取请求方式
	String method = request.getMethod();//GET
    //获取请求的查询字符串
	String queryString = request.getQueryString();//name=Tomcat&age=10
    return "request success";
}
````

### 响应数据格式

![](C:\Users\LL\Desktop\javaweb\响应数据格式.jpg)

常见响应状态码：

| 1xx  | 响应中-临时状态码，表示请求已经接收，告诉客户端应该继续请求或者如果它已经完成则忽略它 |
| ---- | ------------------------------------------------------------ |
| 2xx  | 成功-表示请求已经被成功接收，处理已完成                      |
| 3xx  | 重定向-重定向到其他地方：让客户端在发送一次请求已完成整个处理 |
| 4xx  | 客户端错误-处理发生错误，责任在客户端，如：请求了不存在的资源(404)、客户端未被授权、禁止访问等 |
| 5xx  | 服务器错误-处理发生错误，责任在服务端。如：程序抛出异常等    |

![](C:\Users\LL\Desktop\javaweb\重定向.jpg)

 

[状态码大全](https://heuqqdmbyk.feishu.cn/wiki/space/7413668442156498972?ccm_open_type=lark_wiki_spaceLink&open_tab_from=wiki_home)

常见状态码：

| 状态码 | 英文描述                        | 解释                                                         |
| ------ | ------------------------------- | ------------------------------------------------------------ |
| 200    | OK                              | 客户端请求成功，即处理成功，这是我们最想看到的状态码         |
| 302    | Found                           | 指示所请求的资源已移动到由Location响应头给定的 URL，浏览器会自动重新访问到这个页面 |
| 304    | Not Modified                    | 告诉客户端，你请求的资源至上次取得后，服务端并未更改，你直接用你本地缓存吧。隐式重定向 |
| 400    | Bad Request                     | 客户端请求有语法错误，不能被服务器所理解                     |
| 403    | Forbidden                       | 服务器收到请求，但是拒绝提供服务，比如：没有权限访问相关资源 |
| 404    | Not Found                       | 请求资源不存在，一般是URL输入有误，或者网站资源被删除了      |
| 405    | Method Not Allowed              | 请求方式有误，比如应该用GET请求方式的资源，用了POST          |
| 428    | Precondition Required           | 服务器要求有条件的请求，告诉客户端要想访问该资源，必须携带特定的请求头 |
| 429    | Too Many Requests               | 指示用户在给定时间内发送了太多请求（“限速”），配合 Retry-After(多长时间后可以请求)响应头一起使用 |
| 431    | Request Header Fields Too Large | 请求头太大，服务器不愿意处理请求，因为它的头部字段太大。请求可以在减少请求头域的大小后重新提交。 |
| 500    | Internal Server Error           | 服务器发生不可预期的错误。服务器出异常了，赶紧看日志去吧     |
| 503    | Service Unavailable             | 服务器尚未准备好处理请求，服务器刚刚启动，还未初始化好       |

常见响应头：

| Content-Type     | 表示响应内容的类型，例如text/html，application/json        |
| ---------------- | ---------------------------------------------------------- |
| Content-Length   | 表示响应内容的长度（字节数）                               |
| Content-Encoding | 表示该响应压缩算法，例如gzip                               |
| Cache-Control    | 只是客户端应如何缓冲，例如max-age=300表示可以最多缓存300秒 |
| Set-Cookie       | 告诉浏览器为当前页面所在的域设值cookie                     |

主要掌握这三个

| 状态码 | 描述                                              |
| ------ | ------------------------------------------------- |
| 200    | 客户端成功                                        |
| 404    | 请求资源不存在，URL输入由误，或者网站资源被删除了 |
| 500    | 服务器发生不可预期的错误                          |

### 响应数据设置

Web服务器(Tomact)对HTTP协议的请求数据进行解析，并进行了封装(HttpServletResponse)，调用了Controller方法的时候传递给了该方法，这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷

方式一：基于HttpServletResponse封装

```java
@RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //1.设置响应状态码
        response.setStatus(401);
        //2.设置响应头
        response.setHeader("name","itcast");
        //3.设置响应体
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("<h1>hello response</h1>");
    }
```

方式二：基于ResponseEntity封装

```java
@RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //1.设置响应状态码
        response.setStatus(401);
        //2.设置响应头
        response.setHeader("name","itcast");
        //3.设置响应体
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("<h1>hello response</h1>");
    }
```

注意：响应状态码和象响应头如果没有特殊要求的话，通常不手动设定。服务器会根据请求处理的逻辑，自动设置相应状态码和响应头

## SpringBootWeb案例

细节:

获取流时候的路径，可以用字节码文件来获取

```java
InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
```

核心代码

```java
import cn.hutool.core.io.IoUtil;
import com.ityhr.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @RequestMapping("/list")
    public List<User> list(){
        //1.记载并读取user.txt文件，获取用户数据
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());

        //2.解析用户信息，封装为User对象 -> list集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).toList();

        //3.返回数据(json)
        return userList;
    }

}
```

## 分层解耦

### 三层架构

- Controller：控制层，接收前端发送的请求，对请求进行处理，并响应数据
- service：业务逻辑层，处理具体的业务逻辑
- dao：数据访问层（Data Access Object）(持久层)，负责数据访问的操作，包括数据的增、删、改、查

浏览器从上面三层从上到下访问

### 分层解耦

- 耦合：衡量软件中各个层/各个模块的依赖关系关联程度
- 内聚：软件中各个功能模块内部的功能联系

spring里面两个非常重要的概念

- 控制反转：Inversion Of Control，简称IOC。对象创建控制权由程序自身转移到外部(容器)，这种思想称为控制反转
- 依赖注入：Dependency Injection，简称DI。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入
- Bean对象：IOC容器中创建、管理的对象，称之为Bean

### IOC&DI

1. 将Dap及Service层的实现类，交给IOC容器管理(在上面加个@Component注解)
2. 为Controller及Service注入运行时所依赖的对象(在上面加上@Autowired注解)

参考：

```java
@Autowired
    private UserService userService;
@Component
public class UserDaoImpl implements UserDao {
    .....
}
```

### IOC详解

要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解之一：

| 注解        | 说明                 | 位置                                          |
| ----------- | -------------------- | --------------------------------------------- |
| @Component  | 声明bean的基础注解   | 不属于一下三类时，用此注解                    |
| @Controller | @Component的衍生注解 | 标注在控制层类上                              |
| @Service    | @Component的衍生注解 | 标注在业务层类上                              |
| @Repository | @Component的衍生注解 | 标注在数据访问层类上(由于mybatis整合，用的少) |

注意：声明bean的时候，可以通过注解的value书写指定bean的名字，如果没有指定，默认为类名首字母小写

- 注解声明bean的四大注解，要想生效，还需要被组件扫描注解@ComponentScan扫描
- 该注解虽然没有显示配置，但是实际上已经包含在了启动类声明注解 @SpringBootApplication中，默认扫描的范围是启动类所在包及其子包

### DI详解

基于@Autowired进行依赖注入的常见方式有如下三种：

1. 属性注入

   ```java
   @RestController
   public class UserController {
   
       //方式一: 属性注入
       @Autowired
       private UserService userService;
       
     }
   ```

   - 优点：代码简洁、方便快速开发。
   - 缺点：隐藏了类之间的依赖关系、可能会破坏类的封装性。

2. 构造函数注入

   ```java
   @RestController
   public class UserController {
   
       //方式二: 构造器注入
       private final UserService userService;
       
       @Autowired //如果当前类中只存在一个构造函数, @Autowired可以省略
       public UserController(UserService userService) {
           this.userService = userService;
       }
       
    }   
   ```

   - 优点：能清晰地看到类的依赖关系、提高了代码的安全性。
   - 缺点：代码繁琐、如果构造参数过多，可能会导致构造函数臃肿。
   - 注意：如果只有一个构造函数，@Autowired注解可以省略。（通常来说，也只有一个构造函数）

3. setter注入

   ```java
   /**
    * 用户信息Controller
    */
   @RestController
   public class UserController {
       
       //方式三: setter注入
       private UserService userService;
       
       @Autowired
       public void setUserService(UserService userService) {
           this.userService = userService;
       }
       
   }    
   ```

   - 优点：保持了类的封装性，依赖关系更清晰。
   - 缺点：需要额外编写setter方法，增加了代码量。

tip:在项目开发中，基于@Autowired进行依赖注入时，基本都是第一种和第二种方式。（官方推荐第二种方式，因为会更加规范）但是在企业项目开发中，很多的项目中，也会选择第一种方式因为更加简洁、高效（在规范性方面进行了妥协）。

- @Autowired注解，默认是按照类型进行注入的

- 如果存在多个相同类型的bean，将会报出如下错误：

  ![](C:\Users\LL\Desktop\javaweb\DI报错信息.png)

解决方案

- 方案一：@Primary

  ```java
  @Primary
  @Service
  public class UserServiceImpl implements UserService {
  }
  ```

- 方案二：@Qualifier

  ```java
  @RestController
  public class UserController {
  
      @Qualifier("userServiceImpl")
      @Autowired
      private UserService userService;
  ```

- 方案三：@Resource

  ```java
  @RestController
  public class UserController {
          
      @Resource(name = "userServiceImpl")
      private UserService userService;
  ```

面试题：@Autowird 与 @Resource的区别

- @Autowired 是spring框架提供的注解，而@Resource是JDK提供的注解
- @Autowired 默认是按照类型注入，而@Resource是按照名称注入

# MySQL

详细间MySQL数据库笔记

# JDBC

- JDBC：(Java DataBase Connectivity)，就是使用Java语言操作关系型数据库的一套API
- 本质：
  - sun公司官方定义的一套操作所有关系型数据库的规范，即接口
  - 各个数据库厂商去实现这套接口，提供数据库驱动jar包
  - 我们可以使用这套接口(JDBC)编程，真正执行的代码是驱动jar包中的实现类

步骤：

- 准备工作：创建maven项目，引入依赖，并准备数据表user

````java
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>
````

- 代码实现：编写JDBC程序，操作数据库

## 入门程序

````java
//1.注册驱动
Class.forName("com.mysql.cj.jdbc.Driver");
//2.获取数据库连接
String url = "jdbc:mysql://localhost:3306/web01";
String username = "root";
String password = "123456";
Connection connection = DriverManager.getConnection(url,username,password);
//3.获取SQL语句执行对象
Statement statement = connection.createStatement();
//4.执行SQL
int i = statement.executeUpdate("update user set age = 25 where id = 1");
System.out.println("SQL执行完毕影响的记录数为：" + i);
//5.释放资源
connection.close();
````

ResultSet（结果集对象）：封装了DQL查询语句查询的结果。

- next()：将光标从当前位置向前移动一行，并判断当前行是否为有效行，返回值为boolean。
  - true：有效行，当前行有数据
  - false：无效行，当前行没有数据
- getXxx(…)：获取数据，可以根据列的编号获取，也可以根据列名获取（推荐）。

## 执行DQL语句

````java
@Test
    public void testSelect(){
        String URL = "jdbc:mysql://localhost:3306/web01"; // 替换为你的数据库名称
        String USER = "root"; // 替换为你的数据库用户名
        String PASSWORD = "123456";


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;//封装查询结果

        try {
            // 1. 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 获取数据库连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. 定义 SQL 查询语句
            String sql = "SELECT id, username, password, name, age FROM user WHERE username = ? AND password = ?";//预编译SQL语句

            // 4. 创建 PreparedStatement 对象
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "daqiao"); // 设置参数 1
            preparedStatement.setString(2, "123456"); // 设置参数 2

            // 5. 执行查询并获取结果集
            resultSet = preparedStatement.executeQuery();

            // 6. 封装结果集到 User 对象中
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");

                // 创建 User 对象并添加到列表中
                User user = new User(id, username, password, name, age);
                userList.add(user);
            }

            // 7. 输出 User 对象的数据到控制台
            for (User user : userList) {
                System.out.println(user);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection or query failed!");
            e.printStackTrace();
        } finally {
            // 8. 关闭资源
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
````

## 预编译SQL

- 优势一：可以防止SQL注入，更安全

  SQL：通过控制输入来修改事先定义好的SQL语句，以达到执行代码对服务器进行攻击的方法

- 优势二：性能更高

代码实现

```java
String sql = "SELECT id, username, password, name, age FROM user WHERE username = ? AND password = ?";//预编译SQL语句

// 4. 创建 PreparedStatement 对象
preparedStatement = connection.prepareStatement(sql);
preparedStatement.setString(1, "daqiao"); // 设置参数 1
preparedStatement.setString(2, "123456"); // 设置参数 2

// 5. 执行查询并获取结果集
resultSet = preparedStatement.executeQuery();
```

# Mybatis

MyBatis是一款优秀的持久层框架，用于简化JDBC的开发

[官网](https://mybatis.org/mybatis-3/zh_CN/index.html)

## 入门程序

- 准备工作：
  1. 创建SpringBoot工程，引入Mybatis相关依赖
  2. 准备数据库表user、实体类User
  3. 配置Mybatis(在application.properties中数据库连接信息)
- 编写Mybatis程序：编写Mybatis的持久层接口，定义SQL(注解/XML)

tip：Mybatis的持久层接口命名规范为XxxMapper，也称为Mapper接口

注意：测试类所在的包需要于引导类包名相同(或放在引导所在包的子包下)

## 辅助配置

- 默认在Mybatis中配置的SQL语句是不识别的。可以做如下配置

![](C:\Users\LL\Desktop\javaweb\Mybatis辅助配置.jpg)

### 配置日志输出

默认情况下，在Mybatis中，SQL语句执行时，我们并看不到SQL语句的执行日志。 在`application.properties`加入如下配置，即可查看日志： 

```java
#mybatis的配置
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

### 数据库连接池

- 官方(sun)提供了数据库连接池标准（javax.sql.DataSource接口）
- 功能：获取连接 
  - ```Java
    public Connection getConnection() throws SQLException;
    ```
- 第三方组织必须按照DataSource接口实现

Hikari：springboot默认

| 符号 | 说明                                               | 场景                       |
| ---- | -------------------------------------------------- | -------------------------- |
| #{…} | 占位符。执行时，会将#{…}替换为?，生成预编译SQL     | 参数值传递                 |
| ${…} | 拼接符。直接将参数拼接在SQL语句中，存在SQL注入问题 | 表名、字段名动态设置时使用 |

@param注解的作用是为接口的方法形参起名字的。（由于用户名唯一的，所以查询返回的结果最多只有一个，可以直接封装到一个对象中）

**说明：**基于官方骨架创建的springboot项目中，接口编译时会保留方法形参名，@Param注解可以省略 (#{形参名})。

再Mybatis中，如果是简答的增删改查就用注解，复杂就用xml

官方说明：https://mybatis.net.cn/getting-started.html

**在Mybatis中使用XML映射文件方式开发，需要符合一定的规范：**

1. XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）
2. XML映射文件的namespace属性为Mapper接口全限定名一致
3. XML映射文件中sql语句的id与Mapper接口中的方法名一致，并保持返回类型一致。

### 辅助配置

配置XML映射文件的位置：

```xml
#指定XML映射配置文件的配置
mybatis.mapper-locations=classpath:mapper/*.xml
```

### SpringBoot项目配置文件

#### yml配置文件

- 大小写敏感
- 数值前边必须有空格，作为分隔符
- 使用缩进表示层级关系，缩进时，不允许使用Tab键，只能用空格（idea中会自动将Tab转换为空格）
- 缩进的空格数目不重要，只要相同层级的元素左侧对齐即可
- `#`表示注释，从这个字符一直到行尾，都会被解析器忽略

对象/Map集合

```YAML
user:
  name: zhangsan
  age: 18
  password: 123456
```

数组/List/Set集合

```YAML
hobby: 
  - java
  - game
  - sport
```

在yml格式的配置文件中，如果配置项的值是以 0 开头的，值需要使用 '' 引起来，因为以0开头在yml中表示8进制的数据。

idea里面改utf-8：settings -> editor -> file encoding：都改为 UTF-8





