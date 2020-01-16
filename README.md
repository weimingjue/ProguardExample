MainActivity都有说明哪些东西会混淆
app/release目录有打包好的apk，拖进Android Studio即可查看混淆情况，也可自行编译到app>build>outputs>mapping>release/debug>mapping查看


**proguard-rules.pro里有!com.wang.\*请改成自己的包路径：**

包路径即你java文件里声明“package com.x.x.x;”的前几个，一般情况下和packageName、applicationId相同

举例：你Mainfest的packageName=“com.a.a”，build.gradle的applicationId=“com.b.b”，你的代码实际都是“package com.c.c.activity.MainActivity”及相关“com.c.c”目录下的，那么你应该写“-keep class !com.c.c.** {*;}”


**问题1：没混淆正常，开了混淆就崩溃**

请看日志！看日志！看日志！一般都是使用了反射出现的xxxNotFound异常或者一些bean数据类忘加了，自行添加忽略

**问题2：开了混淆没任何作用**

1.查看是否改成了自己的包路径（检查ApplicationId和packageName，如果不一致请按照上面修改）

2.查看release是否开启了混淆，打包时选择release，并用AS查看

3.类似“!com.wang.\*”不能写2个，比如你在Proguard里同时写了“!com.wang.\*    ！com.li.\*”，这样肯定会失效

4.确认你的混淆文件路径正确，并且和上面基本一致（如混淆文件乱放的、不小心写了2个混淆文件、复制了其他人的混淆逻辑）