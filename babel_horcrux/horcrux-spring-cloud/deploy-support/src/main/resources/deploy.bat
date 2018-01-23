
:: 编译、打包horcrux-spring-cloud下的所有项目
:: 并将所有war包拷贝到deploy-support\target\dir-war, 所有jar包拷贝到deploy-support\target\dir-jar
echo  ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set disk=E:
set projectDir=E:/svn/code/trunk/babel_horcrux/horcrux-spring-cloud

:: 切换磁盘
%disk%

::进入项目根目录(主要是包含模块管理的pom)，执行install操作，打最新的包
cd %projectDir%

call mvn clean install -Dmaven.test.skip=true

::如果 target 目录下已经有war包的话，则删除
if exist %projectDir%/%projectName%.war (del %projectDir%/%projectName%.war)

::将所有的war包拷贝到deploy-support\target\tarsdir下
echo  :::::::::::::::::::::::::::::::::java:::::::::::::::::::::::::::::::::::::::::
java -jar deploy-support\target\deploy-support-0.0.1-SNAPSHOT.jar

pause
