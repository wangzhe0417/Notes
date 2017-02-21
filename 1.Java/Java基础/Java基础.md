---
title: 面试笔记--Java基础
tags: Job
categories: Job
date: 2017-02-14 16:06:13

---
#面试笔记--Java基础

### 1.什么是Java虚拟机？为什么Java被称作是“平台无关的编程语言”？
> Java 虚拟机是一个可以执行 Java 字节码的虚拟机进程。Java 源文件被编译成能被 Java 虚拟机执行的字节码文件。
Java 被设计成允许应用程序可以运行在任意的平台，而不需要程序员为每一个平台单独重写或者是重新编译。
Java 虚拟机让这个变为可能，因为它知道底层硬件平台的指令长度和其他特性。

### 2.Java中的基本类型及其封装类
>八种基本类型：int,double,long,float,short,byte,char,boolean
对应的封装类型:Integer,Double,Long,Float,Short,Byte,Character,Boolean

### 3.equals与==的区别
>==与equals的主要区别是：==常用于比较原生类型，而equals()方法用于检查对象的相等性。另一个不同的点是：如果==和equals()用于比较对象，当两个引用地址相同，==返回true。而equals()可以返回true或者false主要取决于重写实现。
**使用==比较有两种情况：**
>`比较基础数据类型`(Java中基础数据类型包括八中：short,int,long,float,double,char,byte,boolen)：这种情况下，==比较的是他们的值是否相等。
`引用间的比较`：在这种情况下，==比较的是他们在内存中的地址，也就是说，除非引用指向的是同一个new出来的对象，此时他们使用 == 去比较得到true，否则，得到false。
**使用equals进行比较：**
>equals追根溯源，是Object类中的一个方法，在该类中，equals的实现也仅仅只是比较两个对象的内存地址是否相等，但在一些子类中，如：String、Integer 等，该方法将被重写。


### 4.Object基类有哪些公用方法
> - clone方法：保护方法，实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常
- equals方法：在Object中与==是一样的，子类一般需要重写该方法
- hashCode方法：该方法用于哈希查找，重写了equals方法一般都要重写hashCode方法。这个方法在一些具有哈希功能的Collection中用到
- getClass方法：final方法，获得运行时类型
- notify方法：唤醒在该对象上等待的某个线程
- notifyAll方法：唤醒在该对象上等待的所有线程
- toString方法：转换成字符串，一般子类都有重写，否则打印句柄
- finalize方法：该方法用于释放资源。因为无法确定该方法什么时候被调用，很少使用。
- wait方法：使当前线程等待该对象的锁，当前线程必须是该对象的拥有者，也就是具有该对象的锁。wait()方法一直等待，直到获得锁或者被中断。wait(long timeout)设定一个超时间隔，如果在规定时间内没有获得锁就返回。 
调用该方法后当前线程进入睡眠状态，直到以下事件发生： 
1.其他线程调用了该对象的notify方法 
2.其他线程调用了该对象的notifyAll方法 
3.其他线程调用了interrupt中断该线程 
4.时间间隔到了,此时该线程就可以被调度了，如果是被中断的话就抛出一个InterruptedException异常

### 5.Java的四种引用，强弱软虚，用到的场景
>JDK1.2之前只有强引用,其他几种引用都是在JDK1.2之后引入的.
- 强引用（Strong Reference） 最常用的引用类型，如Object obj = new Object(); 。只要强引用存在则GC时则必定不被回收。
- 软引用（Soft Reference） 用于描述还有用但非必须的对象，当堆将发生OOM（Out Of Memory）时则会回收软引用所指向的内存空间，若回收后依然空间不足才会抛出OOM。一般用于实现内存敏感的高速缓存。 当真正对象被标记finalizable以及的finalize()方法调用之后并且内存已经清理, 那么如果SoftReference object还存在就被加入到它的 ReferenceQueue.只有前面几步完成后,Soft Reference和Weak Reference的get方法才会返回null
- 弱引用（Weak Reference） 发生GC时必定回收弱引用指向的内存空间。 和软引用加入队列的时机相同
- 虚引用（Phantom Reference) 又称为幽灵引用或幻影引用，虚引用既不会影响对象的生命周期，也无法通过虚引用来获取对象实例，仅用于在发生GC时接收一个系统通知。 当一个对象的finalize方法已经被调用了之后，这个对象的幽灵引用会被加入到队列中。通过检查该队列里面的内容就知道一个对象是不是已经准备要被回收了. 虚引用和软引用和弱引用都不同,它会在内存没有清理的时候被加入引用队列.虚引用的建立必须要传入引用队列,其他可以没有

### 6.Hashcode的作用
>以Java.lang.Object来理解,JVM每new一个Object,它都会将这个Object丢到一个Hash哈希表中去,这样的话,下次做Object的比较或者取这个对象的时候,它会根据对象的hashcode再从Hash表中取这个对象。这样做的目的是提高取对象的效率。具体过程是这样:
1. new Object(),JVM根据这个对象的Hashcode值,放入到对应的Hash表对应的Key上,如果不同的对象确产生了相同的hash值,也就是发生了Hash key相同导致冲突的情况,那么就在这个Hash key的地方产生一个链表,将所有产生相同hashcode的对象放到这个单链表上去,串在一起。
2. 比较两个对象的时候,首先根据他们的hashcode去hash表中找他的对象,当两个对象的hashcode相同,那么就是说他们这两个对象放在Hash表中的同一个key上,那么他们一定在这个key上的链表上。那么此时就只能根据Object的equal方法来比较这个对象是否equal。当两个对象的hashcode不同的话，肯定他们不能equal.
