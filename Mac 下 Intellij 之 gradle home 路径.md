---
title: Mac 下 Intellij 之 gradle home 路径
tags: Tools
categories: Tools
date: 2017-5-29 17:06:13

---

# Mac 下 Intellij 之 gradle home 路径

Intellij IDEA 自带 gradle 包，如果想使用本地的 gradle 的话，需要自己配置 gradle 路径。如果采用的是 Homebrew 安装工具安装的 gradle 的话，
gradle 的路径的填写正确的形式应该如下图：

![](/media/15008809450399.jpg)

<!-- more -->

填写的路径应该是一个文件夹，文件夹下包括 bin 和 lib 两个文件夹；路径树应该是这样的：

```
/usr/local/Cellar/gradle/2.6
├── INSTALL_RECEIPT.json
├── LICENSE
├── NOTICE
├── bin
│   └── gradle -> ../libexec/bin/gradle
├── changelog.txt
└── libexec
    ├── bin
    └── lib

```

错误的填写形式如下：

![](/media/15008809926429.jpg)

