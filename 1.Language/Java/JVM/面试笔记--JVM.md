﻿---
title: 面试笔记--JVM
tags: Job
categories: Job
date: 2017-02-14 16:06:13

---
#面试笔记--JVM


## JVM

### JVM内存分哪几个区，每个区的作用是什么?
Java虚拟机主要分为以下一个区:
![](https://camo.githubusercontent.com/6d3533c08ec53b6b8e23c73d42793af33272f041/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313331323236313531373434323530)
 - **Java堆**
java堆是所有线程所共享的一块内存，在虚拟机启动时创建，几乎所有的对象实例都在这里创建，因此该区域经常发生垃圾回收操作。Java堆可以细分为新生代和老年代。在细致分就是把新生代分为：Eden空间、From Survivor空间、To Survivor空间。
 - **方法区：**
    1. 有时候也成为永久代，在该区内很少发生垃圾回收，但是并不代表不发生GC，在这里进行的GC主要是对方法区里的常量池和对类型的卸载
    2. 方法区主要用来存储已被虚拟机加载的类的信息、常量、静态变量和即时编译器编译后的代码等数据。
    3. 该区域是被线程共享的。
    4. 方法区里有一个运行时常量池，用于存放静态编译产生的字面量和符号引用。该常量池具有动态性，也就是说常量并不一定是编译时确定，运行时生成的常量也会存在这个常量池中。
 - **虚拟机栈:**
    1. 虚拟机栈也就是我们平常所称的栈内存,它为java方法服务，每个方法在执行的时候都会创建一个栈帧，用于存储局部变量表、操作数栈、动态链接和方法出口等信息。
    2. 虚拟机栈是线程私有的，它的生命周期与线程相同。
    3. 局部变量表里存储的是基本数据类型、returnAddress类型（指向一条字节码指令的地址）和对象引用，这个对象引用有可能是指向对象起始地址的一个指针，也有可能是代表对象的句柄或者与对象相关联的位置。局部变量所需的内存空间在编译器间确定
    4.操作数栈的作用主要用来存储运算结果以及运算的操作数，它不同于局部变量表通过索引来访问，而是压栈和出栈的方式
    5.每个栈帧都包含一个指向运行时常量池中该栈帧所属方法的引用，持有这个引用是为了支持方法调用过程中的动态连接.动态链接就是将常量池中的符号引用在运行期转化为直接引用。
 - **本地方法栈**
本地方法栈和虚拟机栈类似，只不过本地方法栈为Native方法服务。
 - **程序计数器**
内存空间小，字节码解释器工作时通过改变这个计数值可以选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理和线程恢复等功能都需要依赖这个计数器完成。该内存区域是唯一一个java虚拟机规范没有规定任何OOM情况的区域。

<!-- more -->
### GC对象的判定方法/如何判断一个对象是否存活
判断一个对象是否存活有两种方法:

1. **引用计数法**
所谓引用计数法就是给每一个对象设置一个引用计数器，每当有一个地方引用这个对象时，就将计数器加1，引用失效时，计数器就减1。当一个对象的引用计数器为0时，说明此对象没有被引用，也就是“死对象”,将会被垃圾回收.
>引用计数法有一个缺陷就是无法解决循环引用问题，也就是说当对象A引用对象B，对象B又引用者对象A，那么此时A,B对象的引用计数器都不为零，也就造成无法完成垃圾回收，所以主流的虚拟机都没有采用这种算法。

2. **可达性算法(引用链法)**
该算法的思想是：从一个被称为GC Roots的对象开始向下搜索，如果一个对象到GC Roots没有任何引用链相连时，则说明此对象不可用。
在java中可以作为GC Roots的对象有以下几种:
    - 虚拟机栈中引用的对象
    - 方法区类静态属性引用的对象
    - 方法区常量池引用的对象
    - 本地方法栈JNI引用的对象

虽然这些算法可以判定一个对象是否能被回收，但是当满足上述条件时，一个对象比不一定会被回收。当一个对象不可达GC Root时，这个对象并 
不会立马被回收，而是出于一个死缓的阶段，若要被真正的回收需要经历两次标记
如果对象在可达性分析中没有与GC Root的引用链，那么此时就会被第一次标记并且进行一次筛选，筛选的条件是是否有必要执行finalize()方法。当对象没有覆盖finalize()方法或者已被虚拟机调用过，那么就认为是没必要的。
如果该对象有必要执行finalize()方法，那么这个对象将会放在一个称为F-Queue的对队列中，虚拟机会触发一个Finalize()线程去执行，此线程是低优先级的，并且虚拟机不会承诺一直等待它运行完，这是因为如果finalize()执行缓慢或者发生了死锁，那么就会造成F-Queue队列一直等待，造成了内存回收系统的崩溃。GC对处于F-Queue中的对象进行第二次被标记，这时，该对象将被移除”即将回收”集合，等待回收。

### 简述java垃圾回收机制
在java中，程序员是不需要显示的去释放一个对象的内存的，而是由虚拟机自行执行。在JVM中，有一个垃圾回收线程，它是低优先级的，在正常情况下是不会执行的，只有在虚拟机空闲或者当前堆内存不足时，才会触发执行，扫面那些没有被任何引用的对象，并将它们添加到要回收的集合中，进行回收。

### java中垃圾收集的方法有哪些?

 1. **标记-清除**
这是垃圾收集算法中最基础的，根据名字就可以知道，它的思想就是标记哪些要被回收的对象，然后统一回收。这种方法很简单，但是会有两个主要问题：1.效率不高，标记和清除的效率都很低；2.会产生大量不连续的内存碎片，导致以后程序在分配较大的对象时，由于没有充足的连续内存而提前触发一次GC动作。
 2. **复制算法**
为了解决效率问题，复制算法将可用内存按容量划分为相等的两部分，然后每次只使用其中的一块，当一块内存用完时，就将还存活的对象复制到第二块内存上，然后一次性清楚完第一块内存，再将第二块上的对象复制到第一块。但是这种方式，内存的代价太高，每次基本上都要浪费一般的内存。
于是将该算法进行了改进，内存区域不再是按照1：1去划分，而是将内存划分为8:1:1三部分，较大那份内存交Eden区，其余是两块较小的内存区叫Survior区。每次都会优先使用Eden区，若Eden区满，就将对象复制到第二块内存区上，然后清除Eden区，如果此时存活的对象太多，以至于Survivor不够时，会将这些对象通过分配担保机制复制到老年代中。(java堆又分为新生代和老年代)
 3. **标记-整理**
该算法主要是为了解决标记-清除，产生大量内存碎片的问题；当对象存活率较高时，也解决了复制算法的效率问题。它的不同之处就是在清除对象的时候现将可回收对象移动到一端，然后清除掉端边界以外的对象，这样就不会产生内存碎片了。
 4. **分代收集** 
现在的虚拟机垃圾收集大多采用这种方式，它根据对象的生存周期，将堆分为新生代和老年代。在新生代中，由于对象生存期短，每次回收都会有大量对象死去，那么这时就采用复制算法。老年代里的对象存活率较高，没有额外的空间进行分配担保，所以可以使用标记-整理 或者 标记-清除。

### java内存模型
java内存模型(JMM)是线程间通信的控制机制.JMM定义了主内存和线程之间抽象关系。线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。Java内存模型的抽象示意图如下：
>JMM内存模型图
![](http://cdn2.infoqstatic.com/statics_s1_20151006-0049u2/resource/articles/java-memory-model-1/zh/resources/11.png)
从上图来看，线程A与线程B之间如要通信的话，必须要经历下面2个步骤：
1. 首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。
2. 然后，线程B到主内存中去读取线程A之前已更新过的共享变量。

### 简述java类加载机制
虚拟机把描述类的数据从Class文件加载到内存，并对数据进行校验，解析和初始化，最终形成可以被虚拟机直接使用的java类型。

### java类加载过程
java类的生命周期需一共是7个过程：
加载、验证、准备、解析、初始化、使用和卸载。其中前五个属于类的加载过程

 1. **加载**
加载时类加载的第一个过程，在这个阶段，将完成一下三件事情：
    1. 通过一个类的全限定名获取该类的二进制流。
    2. 将该二进制流中的静态存储结构转化为方法去运行时数据结构。 
    3. 在内存中生成该类的Class对象，作为该类的数据访问入口。
 2. **验证**
验证的目的是为了确保Class文件的字节流中的信息不回危害到虚拟机.在该阶段主要完成以下四钟验证:
    1. 文件格式验证：验证字节流是否符合Class文件的规范，如主次版本号是否在当前虚拟机范围内，常量池中的常量是否有不被支持的类型.
    2. 元数据验证:对字节码描述的信息进行语义分析，如这个类是否有父类，是否集成了不被继承的类等。
    3. 字节码验证：是整个验证过程中最复杂的一个阶段，通过验证数据流和控制流的分析，确定程序语义是否正确，主要针对方法体的验证。如：方法中的类型转换是否正确，跳转指令是否正确等。
    4. 符号引用验证：这个动作在后面的解析过程中发生，主要是为了确保解析动作能正确执行。
 3. **准备**
准备阶段是为类的静态变量分配内存并将其初始化为默认值，这些内存都将在方法区中进行分配。准备阶段不分配类中的实例变量的内存，实例变量将会在对象实例化时随着对象一起分配在Java堆中。
 4. **解析**
该阶段主要完成符号引用到直接引用的转换动作。解析动作并不一定在初始化动作完成之前，也有可能在初始化之后。
 5. **初始化**
初始化时类加载的最后一步，前面的类加载过程，除了在加载阶段用户应用程序可以通过自定义类加载器参与之外，其余动作完全由虚拟机主导和控制。到了初始化阶段，才真正开始执行类中定义的Java程序代码。

### 类加载器及双亲委派模型

实现通过类的权限定名获取该类的二进制字节流的代码块叫做类加载器。
主要有一下四种类加载器:

 1. **启动类加载器**(Bootstrap ClassLoader)用来加载java核心类库，负责将存放在\lib目录中的，或者被-Xbootclasspath参数所指定的路径中，并且是虚拟机识别的（仅按照文件名识别，如rt.jar，名字不符合的类库即时放在lib目录中也不会被加载）类库加载到虚拟机内存中。启动类加载器无法被java程序直接引用。
 2. **扩展类加载器**(extensions class loader):它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类（\lib\ext目录中的，或者被java.ext.dirs系统变量所指定的路径中的所有类库）开发者可以直接使用该类加载器
 3. **系统类加载器**（system class loader）：负责加载用户路径上所指定的类库，开发者可以直接使用这个类加载器，也是默认的类加载器。根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader()来获取它。
 4. **用户自定义类加载器**，通过继承 java.lang.ClassLoader类的方式实现。

三种加载器的关系：启动类加载器->扩展类加载器->应用程序类加载器->自定义类加载器。
 
这种关系即为类加载器的双亲委派模型。其要求除启动类加载器外，其余的类加载器都应当有自己的父类加载器。这里类加载器之间的父子关系一般不以继承关系实现，而是用组合的方式来复用父类的代码。

**双亲委派模型的工作过程**：如果一个类加载器接收到了类加载的请求，它首先把这个请求委托给他的父类加载器去完成，每个层次的类加载器都是如此，因此所有的加载请求都应该传送到顶层的启动类加载器中，只有当父加载器反馈自己无法完成这个加载请求（它在搜索范围中没有找到所需的类）时，子加载器才会尝试自己去加载。

好处：java类随着它的类加载器一起具备了一种带有优先级的层次关系。例如类java.lang.Object，它存放在rt.jar中，无论哪个类加载器要加载这个类，最终都会委派给启动类加载器进行加载，因此Object类在程序的各种类加载器环境中都是同一个类。相反，如果用户自己写了一个名为java.lang.Object的类，并放在程序的Classpath中，那系统中将会出现多个不同的Object类，java类型体系中最基础的行为也无法保证，应用程序也会变得一片混乱。

实现：在java.lang.ClassLoader的loadClass()方法中，先检查是否已经被加载过，若没有加载则调用父类加载器的loadClass()方法，若父加载器为空则默认使用启动类加载器作为父加载器。如果父加载失败，则抛出ClassNotFoundException异常后，再调用自己的findClass()方法进行加载。


### 简述java内存分配与回收策率以及Minor GC和Major GC
 1. 对象优先在堆的Eden区分配。
 2. 大对象直接进入老年代.
 3. 长期存活的对象将直接进入老年代.
当Eden区没有足够的空间进行分配时，虚拟机会执行一次Minor GC.Minor Gc通常发生在新生代的Eden区，在这个区的对象生存期短，往往发生Gc的频率较高，回收速度比较快;Full Gc/Major GC 发生在老年代，一般情况下，触发老年代GC的时候不会触发Minor GC,但是通过配置，可以在Full GC之前进行一次Minor GC这样可以加快老年代的回收速度。

### 堆中分区及其特点
 1. **Eden区**
Eden区位于Java堆的年轻代，是新对象分配内存的地方，由于堆是所有线程共享的，因此在堆上分配内存需要加锁。而Sun JDK为提升效率，会为每个新建的线程在Eden上分配一块独立的空间由该线程独享，这块空间称为TLAB（Thread Local Allocation Buffer）。在TLAB上分配内存不需要加锁，因此JVM在给线程中的对象分配内存时会尽量在TLAB上分配。如果对象过大或TLAB用完，则仍然在堆上进行分配。如果Eden区内存也用完了，则会进行一次Minor GC（young GC）。
 2. **Survival from to**
Survival区与Eden区相同都在Java堆的年轻代。Survival区有两块，一块称为from区，另一块为to区，这两个区是相对的，在发生一次Minor GC后，from区就会和to区互换。在发生Minor GC时，Eden区和Survival from区会把一些仍然存活的对象复制进Survival to区，并清除内存。Survival to区会把一些存活得足够旧的对象移至年老代。
 3. **年老代**
年老代里存放的都是存活时间较久的，大小较大的对象，因此年老代使用标记整理算法。当年老代容量满的时候，会触发一次Major GC（full GC），回收年老代和年轻代中不再被使用的对象资源。
 
### 对象创建方法
Java对象的创建大致上有以下几个步骤。
 1. **类加载检查**：检查这个指令的参数是否能在常量池中定位到一个类的符号引用，并且检查这个符号引用代表的类是否已被加载、解析和初始化过。如果没有，那必须先执行相应的类的加载过程
 2. **为对象分配内存**：对象所需内存的大小在类加载完成后便完全确定，为对象分配空间的任务等同于把一块确定大小的内存从Java堆中划分出来。由于堆被线程共享，因此此过程需要进行同步处理（分配在TLAB上不需要同步）
 3. **内存空间初始化**：虚拟机将分配到的内存空间都初始化为零值（不包括对象头），内存空间初始化保证了对象的实例字段在Java代码中可以不赋初始值就直接使用，程序能访问到这些字段的数据类型所对应的零值。
 4. **对象设置**：JVM对对象头进行必要的设置，保存一些对象的信息（指明是哪个类的实例，哈希码，GC年龄等）
 5. **init**：执行完上面的4个步骤后，对JVM来说对象已经创建完毕了，但对于Java程序来说，我们还需要对对象进行一些必要的初始化。
 
### 对象的内存分配
Java对象的内存分配有两种情况，由Java堆是否规整来决定（Java堆是否规整由所采用的垃圾收集器是否带有压缩整理功能决定）：
 1. **指针碰撞**(Bump the pointer)：如果Java堆中的内存是规整的，所有用过的内存都放在一边，空闲的内存放在另一边，中间放着一个指针作为分界点的指示器，分配内存也就是把指针向空闲空间那边移动一段与内存大小相等的距离
 2. **空闲列表**(Free List)：如果Java堆中的内存不是规整的，已使用的内存和空闲的内存相互交错，就没有办法简单的进行指针碰撞了。虚拟机必须维护一张列表，记录哪些内存块是可用的，在分配的时候从列表中找到一块足够大的空间划分给对象实例，并更新列表上的记录



### 对象的访问定位
对象的访问形式取决于虚拟机的实现，目前主流的访问方式有使用句柄和直接指针两种：
 1. **使用句柄**
如果使用句柄访问，Java堆中将会划分出一块内存来作为句柄池，引用中存储的就是对象的句柄地址，而句柄中包含了对象实例数据与类型数据各自的具体地址信息：
![](http://img.blog.csdn.net/20160328111540817?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
优势：引用中存储的是稳定的句柄地址，在对象被移动(垃圾收集时移动对象是非常普遍的行为)时只会改变句柄中的实例数据指针，而引用本身不需要修改。
 2. **直接指针**
如果使用直接指针访问对象，那么对象的实例数据中就包含一个指向对象类型数据的指针，引用中存的直接就是对象的地址：
![](http://img.blog.csdn.net/20160328111843433?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
优势：速度更快，节省了一次指针定位的时间开销，积少成多的效应非常可观。

### GC收集器有哪些？CMS收集器与G1收集器的特点。
 - 单线程收集器，使用单线程去完成所有的gc工作，没有线程间的通信，这种方式会相对高效
 - 并行收集器，使用多线程的方式，利用多CUP来提高GC的效率，主要以到达一定的吞吐量为目标
 - 并发收集器，使用多线程的方式,利用多CUP来提高GC的效率，并发完成大部分工作，使得gc pause短

G1具备如下特点：并行与并发、分代收集、空间整合、可预测的停顿 
CMS具备了并发收集、低停顿的优点、CMS收集器对CPU资源非常敏感、CMS收集器无法处理浮动垃圾、收集结束时会产生大量空间碎片

### Minor GC与Full GC分别在什么时候发生？
 - **Minor GC**：通常是指对新生代的回收。指发生在新生代的垃圾收集动作，因为 Java 对象大多都具备朝生夕灭的特性，所以 Minor GC 非常频繁，一般回收速度也比较快 
 - **Major GC**：通常是指对年老代的回收。 
 - **Full GC**：Major GC除并发gc外均需对整个堆进行扫描和回收。指发生在老年代的 GC，出现了 Major GC，经常会伴随至少一次的 Minor GC（但非绝对的，在 ParallelScavenge 收集器的收集策略里就有直接进行 Major GC 的策略选择过程） 。MajorGC 的速度一般会比 Minor GC 慢 10倍以上。

### 几种常用的内存调试工具：jmap、jstack、jconsole。

### 分派：静态分派与动态分派。
静态分派与重载有关，虚拟机在重载时是通过参数的静态类型，而不是运行时的实际类型作为判定依据的；静态类型在编译期是可知的； 动态分派与重写（Override）相关，invokevirtual(调用实例方法)指令执行的第一步就是在运行期确定接收者的实际类型，根据实际类型进行方法调用；

