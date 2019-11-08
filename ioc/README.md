# Spring IoC

## IoC的概念

> [维基百科中对控制反转的介绍](https://zh.wikipedia.org/wiki/%E6%8E%A7%E5%88%B6%E5%8F%8D%E8%BD%AC)
>
> **控制反转(Inversion of Control,缩写为IoC),是面向对象编程中的一种设计原则,可以用来减低计算机代码之间的耦合度**.
>
> **它把传统上由程序代码直接操控的对象的调用权交给容器,通过容器来实现对象组件的装配和管理**
>
> **所谓的"控制反转"概念就是对组件对象控制权的转移.从程序代码本身转移到了外部容器(由主动获取变为被动接受)**
>
> **其中最常见的方式叫做依赖注入(Dependency Injection,简称DI),还有一种方式叫"依赖查找"(Dependency Lookup)**
>
> **通过控制反转,对象在被创建的时候,由一个调控系统内所有对象的外界实体.将其所依赖的对象的引用传递给它.**

那么控制反转究竟是指哪些`控制`被`反转`了呢?

- 是依赖对象的获得被反转了,因为大多数应用程序都是有两个或者更多的类通过彼此的合作来实现业务逻辑,这使得每个对象都需要获取与其合作的对象(也就是它所依赖的对象)的引用.
    如果这个获取过程要靠自身实现,那么这将导致代码高度耦合并且难以维护和调试

**IOC只是一种设计思想**,而主要的实现由:

 - **依赖注入 (Dependency Injection) 简称DI**
  
 - **依赖查找 (Dependency Lookup)**

**就类似ORM(对象关系映射)一样它是一种程序设计技术,而Hibernate和MyBaits等框架就是它的实现**

## Spring IoC容器和bean

`beans`和`context`是Spring框架的IoC容器的基础包

- `org.springframework.beans`
- `org.springframework.context`

在Spring中,由`Spring IoC容器`管理的对象称为`bean`.`bean`是由`Spring IoC容器`实例化,组装和管理的对象,`bean`及其之间的依赖关系反映在容器使用的配置元数据中

## 容器

`org.springframework.context.ApplicationContext`接口代表`Spring IoC容器`.并负责实例化,配置和组装`Bean`

容器通过读取配置元数据来实例化,配置和组装对象

可以使用三种方式当时来配置元数据

- **XML**
- **Java annotations**
- **Java code**

它使您能够表达组成应用程序的对象以及这些对象之间的丰富相互依赖关系
