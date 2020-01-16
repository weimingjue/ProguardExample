MainActivity都有说明哪些东西会混淆
app/release目录有打包好的apk，拖进Android Studio即可查看混淆情况


**proguard-rules.pro里有!com.wang.\*请改成自己的包路径：**

一般是Mainfest的packageName（如果你的java里面“package com....”不是你的包路径，请自行修改）

**问题1：没混淆正常，开了混淆就崩溃**

请看日志！看日志！看日志！一般都是使用了反射出现的xxxNotFound异常，自行添加忽略

**问题2：开了混淆没任何作用**

1.查看是否改成了自己的包路径（检查ApplicationId和packageName，如果不一致请按照上面修改）

2.查看release是否开启了混淆，打包时选择release，并用AS查看

3.类似“!com.wang.\*”不能写2个，比如你在Proguard里同时写了“!com.wang.\*    ！com.li.\*”，这样肯定会失效

4.确认你的混淆文件和上面基本一致（如不小心写了2个混淆文件，复制了其他人的混淆逻辑）
