MainActivity都有说明哪些东西会混淆

proguard-rules.pro里有com.wang.*请改成自己的包路径（Mainfest的packageName）

没混淆正常，开了混淆就崩溃：请看日志！看日志！看日志！一般都是使用了反射出现的xxxNotFound异常，自行忽略

开了混淆没任何作用：查看是否改成了自己的包路径（检查ApplicationId和packageName的一致性），查看release是否开启了混淆，打包时选择release