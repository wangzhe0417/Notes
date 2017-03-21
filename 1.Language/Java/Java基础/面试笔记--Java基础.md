---
title: 面试笔记--Java基础
tags: Job
categories: Job
date: 2017-02-14 16:06:13

---
#面试笔记--Java基础

## 1.Java基础

### 什么是Java虚拟机？为什么Java被称作是“平台无关的编程语言”？
Java 虚拟机是一个可以执行 Java 字节码的虚拟机进程。Java 源文件被编译成能被 Java 虚拟机执行的字节码文件。
Java 被设计成允许应用程序可以运行在任意的平台，而不需要程序员为每一个平台单独重写或者是重新编译。
Java 虚拟机让这个变为可能，因为它知道底层硬件平台的指令长度和其他特性。
<!-- more -->

### 面向对象和面向过程的区别
- **面向过程**
    - 优点：性能比面向对象高，因为类调用时需要实例化，开销比较大，比较消耗资源;比如单片机、嵌入式开发、Linux/Unix等一般采用面向过程开发，性能是最重要的因素。
    - 缺点：没有面向对象易维护、易复用、易扩展
- **面向对象**
    - 优点：易维护、易复用、易扩展，由于面向对象有封装、继承、多态性的特性，可以设计出低耦合的系统，使系统更加灵活、更加易于维护
    - 缺点：性能比面向过程低

### Java中的基本类型及其封装类,什么是自动拆装箱
 - 八种基本类型：int,double,long,float,short,byte,char,boolean
 - 对应的封装类型:Integer,Double,Long,Float,Short,Byte,Character,Boolean
 - 自动拆装箱:自动装箱就是将原始类型自动的转换为对应的对象，而拆箱就是将对象类型转换为基本类型。

### static”关键字是什么意思
如果一个类的变量或者方法前面有static修饰，那么表明这个方法或者变量属于这个类，也就是说可以在不创建对象的情况下直接使用

### abstract的方法是否可同时是static,是否可同时是native，是否可同时是synchronized?


**abstract**：用来声明抽象方法，抽象方法没有方法体，不能被直接调用，必须在子类overriding后才能使用。 
**static**：用来声明静态方法，静态方法可以被类及其对象调用。
**native**：用来声明本地方法，该方法的实现由非Java 语言实现，比如C。一般用于java与外环境交互，或与操作系统交互。
**synchronized**：用于防止多个线程同时调用一个对象的该方法，与static连用可防止多个线程同时调用一个类的该方法。

1. static与abstract不能同时使用。用static声明方法表明这个方法在不生成类的实例时可直接被类调用，而abstract方法不能被调用，两者矛盾。

2. native可以与所有其它的java 标识符连用，但是abstract除外。因为 native 暗示这些方法是有实现体的，只不过这些实现体是非java 的，但是abstract却显然的指明这些方法无实现体。

3. abstract与synchronized不能同时使用。从synchronized的功能也可以看出，用synchronized的前提是该方法可以被直接调用，显然和abstract连用。

### Java支持多继承么？如果不支持，如何实现?
在java中是单继承的，也就是说一个类只能继承一个父类。
java中实现多继承有两种方式,一是接口，而是内部类.

### 什么是值传递和引用传递？java中是值传递还是引用传递，还是都有?
**值传递**(形式参数类型是基本数据类型)：方法调用时，实际参数把它的值传递给对应的形式参数，形式参数只是用实际参数的值初始化自己的存储单元内容，是两个不同的存储单元，所以方法执行中形式参数值的改变不影响实际参数的值。
**引用传递**(形式参数类型是引用数据类型参数)：也称为传地址。方法调用时，实际参数是对象(或数组)，这时实际参数与形式参数指向同一个地址，在方法执行中，对形式参数的操作实际上就是对实际参数的操作，这个结果在方法结束后被保留了下来，所以方法执行中形式参数的改变将会影响实际参数。

**java中是值传递还是引用传递**

1. “在Java里面参数传递都是按值传递”这句话的意思是：按值传递是传递的值的拷贝，按引用传递其实传递的是引用的地址值，所以统称按值传递。
2. 在Java里面只有基本类型和按照下面这种定义方式的String是按值传递，其它的都是按引用传递。就是直接使用双引号定义字符串方式：String str = “Java”;

### 构造器（constructor）是否可被重写（override）
构造方法是不能被子类重写的，但是构造方法可以重载，也就是说一个类可以有多个构造方法。

### hashCode和equals方法的关系
equals相等，hashcode必相等；hashcode相等，equals可能不相等。

### equals与==的区别
==与equals的主要区别是：==常用于比较原生类型，而equals()方法用于检查对象的相等性。另一个不同的点是：如果==和equals()用于比较对象，当两个引用地址相同，==返回true。而equals()可以返回true或者false主要取决于重写实现。

 - **使用==比较有两种情况：**
`比较基础数据类型`(Java中基础数据类型包括八中：short,int,long,float,double,char,byte,boolen)：这种情况下，==比较的是他们的值是否相等。
`引用间的比较`：在这种情况下，==比较的是他们在内存中的地址，也就是说，除非引用指向的是同一个new出来的对象，此时他们使用 == 去比较得到true，否则，得到false。
 - **使用equals进行比较：**
equals追根溯源，是Object类中的一个方法，在该类中，equals的实现也仅仅只是比较两个对象的内存地址是否相等，但在一些子类中，如：String、Integer 等，该方法将被重写。

### Java面向对象的三个特征与含义
 - **继承**：继承是从已有类得到继承信息创建新类的过程。提供继承信息的类被称为父类（超类、基类）；得到继承信息的类被称为子类（派生类）。继承让变化中的软件系统有了一定的延续性，同时继承也是封装程序中可变因素的重要手段。
 - **封装**：通常认为封装是把数据和操作数据的方法绑定起来，对数据的访问只能通过已定义的接口。
 - **多态**：多态性是指允许不同子类型的对象对同一消息作出不同的响应。

### Override和Overload的含义与区别
 - **Overload**：顾名思义，就是Over(重新)——load（加载），所以中文名称是重载。它可以表现类的多态性，可以是函数里面可以有相同的函数名但是参数名、类型不能相同；或者说可以改变参数、类型但是函数名字依然不变。
 - **Override**：就是ride(重写)的意思，在子类继承父类的时候子类中可以定义某方法与其父类有相同的名称和参数，当子类在调用这一函数时自动调用子类的方法，而父类相当于被覆盖（重写）了。

### 接口和抽象类的区别
不同点在于：

1. 接口中所有的方法隐含的都是抽象的。而抽象类则可以同时包含抽象和非抽象的方法。
2. 类可以实现很多个接口，但是只能继承一个抽象类
3. 类如果要实现一个接口，它必须要实现接口声明的所有方法。但是，类可以不实现抽象声明的所有方法，当然，在这种情况下，类也必须得声明成是抽象的。
4. 抽象类可以在不提供接口方法实现的情况下实现接口。
5. Java 接口中声明的变量默认都是 final 的。抽象类可以包含非 final 的变量。
6. Java 接口中的成员函数默认是 public 的。抽象类的成员函数可以是 private，protected 或者是 public 。
7. 接口是绝对抽象的，不可以被实例化(Java 8已支持在接口中实现默认的方法)。抽象类也不可以被实例化，但是，如果它包含 main 方法的话是可以被调用的。

### Object基类有哪些公用方法
- clone方法：保护方法，实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常
- equals方法：在Object中与==是一样的，子类一般需要重写该方法
- hashCode方法：该方法用于哈希查找，重写了equals方法一般都要重写hashCode方法。这个方法在一些具有哈希功能的Collection中用到
- getClass方法：final方法，获得运行时类型
- notify方法：唤醒在该对象上等待的某个线程
- notifyAll方法：唤醒在该对象上等待的所有线程
- toString方法：转换成字符串，一般子类都有重写，否则打印句柄
- finalize方法：该方法用于释放资源。因为无法确定该方法什么时候被调用，很少使用。
- wait方法：使当前线程等待该对象的锁，当前线程必须是该对象的拥有者，也就是具有该对象的锁。wait()方法一直等待，直到获得锁或者被中断。wait(long timeout)设定一个超时间隔，如果在规定时间内没有获得锁就返回。 
调用该方法后当前线程进入睡眠状态，直到以下事件发生： 
    1. 其他线程调用了该对象的notify方法 
    2. 其他线程调用了该对象的notifyAll方法 
    3. 其他线程调用了interrupt中断该线程 
    4. 时间间隔到了,此时该线程就可以被调度了，如果是被中断的话就抛出一个InterruptedException异常

### Java的四种引用，强弱软虚，用到的场景
JDK1.2之前只有强引用,其他几种引用都是在JDK1.2之后引入的.

- **强引用**（Strong Reference） 最常用的引用类型，如Object obj = new Object(); 。只要强引用存在则GC时则必定不被回收。
- **软引用**（Soft Reference） 用于描述还有用但非必须的对象，当堆将发生OOM（Out Of Memory）时则会回收软引用所指向的内存空间，若回收后依然空间不足才会抛出OOM。一般用于实现内存敏感的高速缓存。 当真正对象被标记finalizable以及的finalize()方法调用之后并且内存已经清理, 那么如果SoftReference object还存在就被加入到它的 ReferenceQueue.只有前面几步完成后,Soft Reference和Weak Reference的get方法才会返回null
- **弱引用**（Weak Reference） 发生GC时必定回收弱引用指向的内存空间。 和软引用加入队列的时机相同
- **虚引用**（Phantom Reference) 又称为幽灵引用或幻影引用，虚引用既不会影响对象的生命周期，也无法通过虚引用来获取对象实例，仅用于在发生GC时接收一个系统通知。 当一个对象的finalize方法已经被调用了之后，这个对象的幽灵引用会被加入到队列中。通过检查该队列里面的内容就知道一个对象是不是已经准备要被回收了. 虚引用和软引用和弱引用都不同,它会在内存没有清理的时候被加入引用队列.虚引用的建立必须要传入引用队列,其他可以没有

### Hashcode的作用
以Java.lang.Object来理解,JVM每new一个Object,它都会将这个Object丢到一个Hash哈希表中去,这样的话,下次做Object的比较或者取这个对象的时候,它会根据对象的hashcode再从Hash表中取这个对象。这样做的目的是提高取对象的效率。具体过程是这样:

1. new Object(),JVM根据这个对象的Hashcode值,放入到对应的Hash表对应的Key上,如果不同的对象确产生了相同的hash值,也就是发生了Hash key相同导致冲突的情况,那么就在这个Hash key的地方产生一个链表,将所有产生相同hashcode的对象放到这个单链表上去,串在一起。
2. 比较两个对象的时候,首先根据他们的hashcode去hash表中找他的对象,当两个对象的hashcode相同,那么就是说他们这两个对象放在Hash表中的同一个key上,那么他们一定在这个key上的链表上。那么此时就只能根据Object的equal方法来比较这个对象是否equal。当两个对象的hashcode不同的话，肯定他们不能equal.

### String、StringBuffer与StringBuilder的区别
 - 可变性：
    String类中使用字符数组保存字符串，private final char value[]，所以string对象是不可变的。
StringBuilder与StringBuffer都继承自AbstractStringBuilder类，在AbstractStringBuilder中也是使用字符数组保存字符串，char[] value，这两种对象都是可变的。
 - 线程安全性：
    String中的对象是不可变的，也就可以理解为常量，线程安全。
AbstractStringBuilder是StringBuilder与StringBuffer的公共父类，定义了一些字符串的基本操作，如expandCapacity、append、insert、indexOf等公共方法。StringBuffer对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。StringBuilder并没有对方法进行加同步锁，所以是非线程安全的。
 - 性能：
    每次对String类型进行改变的时候，都会生成一个新的 String 对象，然后将指针指向新的 String 对象。StringBuffer每次都会对 StringBuffer 对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 StirngBuilder 相比使用 StringBuffer 仅能获得 10%~15% 左右的性能提升，但却要冒多线程不安全的风险。

### try catch finally，try里有return，finally还执行么
会执行，在方法 返回调用者前执行。Java允许在finally中改变返回值的做法是不好的，因为如果存在finally代码块，try中的return语句不会立马返回调用者，而是纪录下返回值待finally代码块执行完毕之后再向调用者返回其值，然后如果在finally中修改了返回值，这会对程序造成很大的困扰

### 什么是泛型、为什么要使用以及泛型擦除
泛型，即“参数化类型”。
创建集合时就指定集合元素的类型，该集合只能保存其指定类型的元素，避免使用强制类型转换。
Java编译器生成的字节码是不包涵泛型信息的，泛型类型信息将在编译处理是被擦除，这个过程即类型擦除。 泛型擦除可以简单的理解为将泛型java代码转换为普通java代码，只不过编译器更直接点，将泛型java代码直接转换成普通java字节码。
类型擦除的主要过程如下：

 1. 将所有的泛型参数用其最左边界（最顶级的父类型）类型替换。
 2. 移除所有的类型参数。

-----

## 2.Java集合总结

### Map、Set、List、Queue、Stack的特点与用法 
- **Set集合**类似于一个罐子，”丢进”Set集合里的多个对象之间没有明显的顺序。 
- **List集合**代表元素有序、可重复的集合，集合中每个元素都有其对应的顺序索引。 
- **Stack**是Vector提供的一个子类，用于模拟”栈”这种数据结构(LIFO后进先出) 
- **Queue**用于模拟”队列”这种数据结构(先进先出 FIFO)。 
- **Map**用于保存具有”映射关系”的数据，因此Map集合里保存着两组值

### Array和ArrayList有何区别？什么时候更适合用Array
 1. Array可以容纳基本类型和对象，而ArrayList只能容纳对象。
 2. Array是指定大小的，而ArrayList大小是固定的

### ArrayList、LinkedList、Vector的区别 
- **LinkedList** 是一个双向链表，线程不安全
- **ArrayList** 是基于数组实现的List，线程不安全
- **Vector** 多数方法都被synchronized修饰的List实现，线程安全；一般在遗留代码中被使用，现在不推荐。

### 遍历一个List有哪些不同的方式
for-each和iterator

### fail-fast与fail-safe有什么区别
Iterator的fail-fast属性与当前的集合共同起作用，因此它不会受到集合中任何改动的影响。Java.util包中的所有集合类都被设计为fail->fast的，而java.util.concurrent中的集合类都为fail-safe的。当检测到正在遍历的集合的结构被改变时，Fail-fast迭代器抛出ConcurrentModificationException，而fail-safe迭代器从不抛出ConcurrentModificationException。

### HashMap与HashTable的区别
 1. HashTable基于Dictionary类，而HashMap是基于AbstractMap。Dictionary是任何可将键映射到相应值的类的抽象父类，而AbstractMap是基于Map接口的实现，它以最大限度地减少实现此接口所需的工作。
 2. HashMap的key和value都允许为null，而Hashtable的key和value都不允许为null。HashMap遇到key为null的时候，调用putForNullKey方法进行处理，而对value没有处理；Hashtable遇到null，直接返回NullPointerException。
 3. Hashtable是同步的，而HashMap是非同步的，但是我们也可以通过Collections.synchronizedMap(hashMap),使其实现同步。

### HashMap的工作原理
HashMap内部是通过一个数组实现的，只是这个数组比较特殊，数组里存储的元素是一个Entry实体(jdk 8为Node)，这个Entry实体主要包含key、value以及一个指向自身的next指针。HashMap是基于hashing实现的，当我们进行put操作时，根据传递的key值得到它的hashcode，然后再用这个hashcode与数组的长度进行模运算，得到一个int值，就是Entry要存储在数组的位置（下标）；当通过get方法获取指定key的值时，会根据这个key算出它的hash值（数组下标），根据这个hash值获取数组下标对应的Entry，然后判断Entry里的key，hash值或者通过equals()比较是否与要查找的相同，如果相同，返回value，否则的话，遍历该链表（有可能就只有一个Entry，此时直接返回null），直到找到为止，否则返回null。
HashMap之所以在每个数组元素存储的是一个链表，是为了解决hash冲突问题，当两个对象的hash值相等时，那么一个位置肯定是放不下两个值的，于是hashmap采用链表来解决这种冲突，hash值相等的两个元素会形成一个链表。

### CorrentHashMap的工作原理
jdk 1.6版: ConcurrenHashMap可以说是HashMap的升级版，ConcurrentHashMap是线程安全的，但是与Hashtablea相比，实现线程安全的方式不同。Hashtable是通过对hash表结构进行锁定，是阻塞式的，当一个线程占有这个锁时，其他线程必须阻塞等待其释放锁。ConcurrentHashMap是采用分离锁的方式，它并没有对整个hash表进行锁定，而是局部锁定，也就是说当一个线程占有这个局部锁时，不影响其他线程对hash表其他地方的访问。
具体实现: ConcurrentHashMap内部有一个Segment数组, 该Segment对象可以充当锁。Segment对象内部有一个HashEntry数组，于是每个Segment可以守护若干个桶(HashEntry),每个桶又有可能是一个HashEntry连接起来的链表，存储发生碰撞的元素。
每个ConcurrentHashMap在默认并发级下会创建包含16个Segment对象的数组，每个数组有若干个桶，当我们进行put方法时，通过hash方法对key进行计算，得到hash值，找到对应的segment，然后对该segment进行加锁，然后调用segment的put方法进行存储操作，此时其他线程就不能访问当前的segment，但可以访问其他的segment对象，不会发生阻塞等待。
jdk 1.8版 在jdk 8中，ConcurrentHashMap不再使用Segment分离锁，而是采用一种乐观锁CAS算法来实现同步问题，但其底层还是“数组+链表->红黑树”的实现。

### HashSet的底层实现是什么
通过看源码知道HashSet的实现是依赖于HashMap的，HashSet的值都是存储在HashMap中的。在HashSet的构造法中会初始化一个HashMap对象，HashSet不允许值重复，因此，HashSet的值是作为HashMap的key存储在HashMap中的，当存储的值已经存在时返回false。

### LinkedHashMap的实现原理
LinkedHashMap也是基于HashMap实现的，不同的是它定义了一个Entry header，这个header不是放在Table里，它是额外独立出来的。LinkedHashMap通过继承hashMap中的Entry,并添加两个属性Entry before,after,和header结合起来组成一个双向链表，来实现按插入顺序或访问顺序排序。LinkedHashMap定义了排序模式accessOrder，该属性为boolean型变量，对于访问顺序，为true；对于插入顺序，则为false。一般情况下，不必指定排序模式，其迭代顺序即为默认为插入顺序。


-----

## 3.Java异常

### Java异常结构
http://blog.csdn.net/hguisu/article/details/6155636
![](http://oawztil0a.bkt.clouddn.com/Blog/Job/java%E5%BC%82%E5%B8%B8.jpg)
 - **Throwable** Throwable是 Java 语言中所有错误或异常的超类。 Throwable包含两个子类: Error 和 Exception 。它们通常用于指示发生了异常情况。 Throwable包含了其线程创建时线程执行堆栈的快照，它提供了printStackTrace()等接口用于获取堆栈跟踪数据等信息。
 - **Exception** Exception及其子类是 Throwable 的一种形式，它指出了合理的应用程序想要捕获的条件。
 - **RuntimeException** RuntimeException是那些可能在 Java 虚拟机正常运行期间抛出的异常的超类。 编译器不会检查RuntimeException异常。 例如，除数为零时，抛出ArithmeticException异常。RuntimeException是ArithmeticException的超类。当代码发生除数为零的情况时，倘若既"没有通过throws声明抛出ArithmeticException异常"，也"没有通过try...catch...处理该异常"，也能通过编译。这就是我们所d说的"编译器不会检查RuntimeException异常"！ 如果代码会产生RuntimeException异常，则需要通过修改代码进行避免。 例如，若会发生除数为零的情况，则需要通过代码避免该情况的发生！
 - **Error** 和Exception一样， Error也是Throwable的子类。 它用于指示合理的应用程序不应该试图捕获的严重问题，大多数这样的错误都是异常条件。 和RuntimeException一样， 编译器也不会检查Error。

### Java异常类型
Java将可抛出(Throwable)的结构分为三种类型： 被检查的异常(Checked Exception)，运行时异常(RuntimeException)和错误(Error)。

 - **运行时异常** 定义 : RuntimeException及其子类都被称为运行时异常。 特点 : Java编译器不会检查它。 也就是说，当程序中可能出现这类异常时，倘若既"没有通过throws声明抛出它"，也"没有用try-catch语句捕获它"，还是会编译通过。例如，除数为零时产生的ArithmeticException异常，数组越界时产生的IndexOutOfBoundsException异常，fail-fail机制产生的ConcurrentModificationException异常等，都属于运行时异常。 虽然Java编译器不会检查运行时异常，但是我们也可以通过throws进行声明抛出，也可以通过try-catch对它进行捕获处理。 如果产生运行时异常，则需要通过修改代码来进行避免。 例如，若会发生除数为零的情况，则需要通过代码避免该情况的发生！
 - **被检查的异常** 定义 : Exception类本身，以及Exception的子类中除了"运行时异常"之外的其它子类都属于被检查异常。 特点 : Java编译器会检查它。 此类异常，要么通过throws进行声明抛出，要么通过try-catch进行捕获处理，否则不能通过编译。例如，CloneNotSupportedException就属于被检查异常。当通过clone()接口去克隆一个对象，而该对象对应的类没有实现Cloneable接口，就会抛出CloneNotSupportedException异常。 被检查异常通常都是可以恢复的。
 - **错误** 定义 : Error类及其子类。 特点 : 和运行时异常一样，编译器也不会对错误进行检查。 当资源不足、约束失败、或是其它程序无法继续运行的条件发生时，就产生错误。程序本身无法修复这些错误的。例如，VirtualMachineError就属于错误。 按照Java惯例，我们是不应该是实现任何新的Error子类的！

>对于上面的3种结构，我们在抛出异常或错误时，到底该哪一种？《Effective Java》中给出的建议是： 对于可以恢复的条件使用被检查异常，对于程序错误使用运行时异常。

### 可能出现OOM的情况有哪些
**OutOfMemoryError异常**
除了程序计数器外，虚拟机内存的其他几个运行时区域都有发生OutOfMemoryError(OOM)异常的可能，
Java Heap 溢出
一般的异常信息：java.lang.OutOfMemoryError:Java heap spacess
java堆用于存储对象实例，我们只要不断的创建对象，并且保证GC Roots到对象之间有可达路径来避免垃圾回收机制清除这些对象，就会在对象数量达到最大堆容量限制后产生内存溢出异常。
出现这种异常，一般手段是先通过内存映像分析工具(如Eclipse Memory Analyzer)对dump出来的堆转存快照进行分析，重点是确认内存中的对象是否是必要的，先分清是因为内存泄漏(Memory Leak)还是内存溢出(Memory Overflow)。
如果是内存泄漏，可进一步通过工具查看泄漏对象到GC Roots的引用链。于是就能找到泄漏对象时通过怎样的路径与GC Roots相关联并导致垃圾收集器无法自动回收。
如果不存在泄漏，那就应该检查虚拟机的参数(-Xmx与-Xms)的设置是否适当。

**虚拟机栈和本地方法栈溢出**
如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。
如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常
这里需要注意当栈的大小越大可分配的线程数就越少。

**运行时常量池溢出**
异常信息：java.lang.OutOfMemoryError:PermGen space
如果要向运行时常量池中添加内容，最简单的做法就是使用String.intern()这个Native方法。该方法的作用是：如果池中已经包含一个等于此String的字符串，则返回代表池中这个字符串的String对象；否则，将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用。由于常量池分配在方法区内，我们可以通过-XX:PermSize和-XX:MaxPermSize限制方法区的大小，从而间接限制其中常量池的容量。

**方法区溢出**
方法区用于存放Class的相关信息，如类名、访问修饰符、常量池、字段描述、方法描述等。
异常信息：java.lang.OutOfMemoryError:PermGen space
方法区溢出也是一种常见的内存溢出异常，一个类如果要被垃圾收集器回收，判定条件是很苛刻的。在经常动态生成大量Class的应用中，要特别注意这点。


