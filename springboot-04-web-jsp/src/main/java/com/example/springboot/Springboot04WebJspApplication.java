package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot04WebJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot04WebJspApplication.class, args);
	}

}
/*
 *
 * 十、使用外部servlet容器的步骤
 * 1.创建一个war项目
 * 包括在【项目结构】添加类路径下webapp，和webapp/WEB-INF的web.xml文件
 * 部署，配置本地tomcat
 *
 * 2.将嵌入式的tomcat指定为provided
 * 表示已经另外 提供 外部环境，打包时，不必带上tomcat了
 * <dependency>
 *       <groupId>org.springframework.boot</groupId>
 *       <artifactId>spring-boot-starter-tomcat</artifactId>
 *       <scope>provided</scope>// 表示目标环境，已经提供了，打包时，就不必带上tomcat
 * </dependency>
 *
 * 注1：在properties配置文件中，需要配置如下：（以方便controller层中拼串成视图名称，将视图名称+结果交给model and view,然后前端控制器dispatcher servlet
 * 将其交给视图解析器viewResolver解析）
 * spring.mvc.view.prefix=/WEB-INF/
 * spring.mvc.view.suffix=.jsp
 *
 * 注2：按照以下步骤操作：
 * 项目结构-工件---> springboot-04-web-jsp:war exploded--->输出布局中（lib文件夹拖进 WEB-INF/路径下）
 *
 * 3.必须编写一个ServletInitializer类，没有这个类项目无法启动
 * 这个IDEA 在建立war包的项目时，自动生成了；不需要我们额外编写
 * 伪代码：
 *
 * public class ServletInitializer extends SpringBootServletInitializer {
 *    @Override
 *    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
 *         return application.sources(Springboot04WebJspApplication.class);
 *    }
 *  }
 *
 * 注；继承SpringBootServletInitializer的类里，重写了configure方法，会自动忽略内置的tomcat
 *
 * 4.启动服务器就可以使用了
 *
 * bug: 使用外部的tomcat时，一定要注意版本兼容问题
 * 当前使用spring boot 2.6.13 +tomcat 9.0.89,之前配置的tomcat10会出现controller层不生效的情况：
 * 在controller/文件里，已经确实处理了/abc请求，hello.jsp跳转success.jsp时，始终报404错误
 * 参考文档：
 * https://blog.csdn.net/qq_47901630/article/details/128301561?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-128301561-blog-77425043.235^v43^control&spm=1001.2101.3001.4242.2&utm_relevant_index=4
 *
 * bug解决：卸载tomcat10，安装tomcat9后，问题解决；/abc请求可以正常访问了
 *
 * 十一、外部servlet启动spring boot应用的原理：
 * 之前的jar包方式：创建spring boot应用，执行spring boot主类的main方法，创建ioc容器时，创建一个嵌入式的servlet容器（tomcat）
 * 当前war包方式：启动服务器，服务器启动spring boot应用【SpringBootServletInitializer】，然后再启动ioc容器
 *
 * servlet3.0 以后版本规则：
 * java-pro/文档/servlet-3_1-final.pdf文件：8.2.4章节（Shared libraries / runtimes pluggability）
 * 规则：
 * 1.服务器启动（web应用启动）会创建当前应用的每个jar包里的ServletContainerInitializer实例
 * 2.ServletContainerInitializer的实现放在META-INF/services/文件夹下，该文件夹下有一个文件javax.servlet.ServletContainerInitializer，
 * 文件的内容就是它的实现类的全类名
 * 3.ServletContainerInitializer还可以使用 @HandleTypes注解，在应用加载时，添加我们感兴趣的类
 *
 * 归纳上面的流程：
 * 1.服务器启动
 * 2.在所有jar包里的META-INF/services/下，找javax.servlet.ServletContainerInitializer；
 * 它在这个路径下：jetbrains://idea/navigate/reference?project=springboot-04-web-jsp&path=~\.m2\repository\org\springframework\spring-web\5.3.23\spring-web-5.3.23.jar!\META-INF\services\javax.servlet.ServletContainerInitializer
 * 实际上在外部库里库名为（ Maven:org.springframework:spring-web:53.23）的里面
 * javax.servlet.ServletContainerInitializer这个文件里的内容很简单，就是：org.springframework.web.SpringServletContainerInitializer
 *
 * 查看SpringServletContainerInitializer类，该类的@HandlesTypes注解中有一个全类名（主要分析它）
 * 3.SpringServletContainerInitializer将注解@HandlesTypes({WebApplicationInitializer.class})里面的类传给onStartup方法的Set<class<?>>类型参数，
 * 为这些WebApplicationInitializer创建实例
 * 4.每个WebApplicationInitializer调用onStartup()方法
 * 5.也就是SpringBootServletInitializer会创建对象，并执行onStartup()方法
 * web项目必须写的类ServletInitializer--->继承自SpringBootServletInitializer--->而父类SpringBootServletInitializer实现了接口
 * WebApplicationInitializer
 *
 * 6.SpringBootServletInitializer执行onStartup()方法时，会使用相应的方法（this.createRootApplicationContext(servletContext)）创建容器
 * WebApplicationContext rootApplicationContext = this.createRootApplicationContext(servletContext);
 * 7.Spring的应用就启动了
 *
 * 十二、Docker容器（取代之前的虚拟机，虚拟机启动要好长时间）
 * 12.1 Docker简介
 * Docker是一个开源的容器引擎；基于go语言并遵从apache2.0协议开源
 * 1.Docker支持软件编译成一个镜像
 * 2.然后再镜像中做好配置，再将镜像发布出去，然后，其他使用者可以直接使用这个镜像
 * 3.运行中的镜像叫做容器，容器启动是非常快的
 *
 *
 * 参考文档：
 * https://www.bilibili.com/video/BV1Et411Y7tQ?p=53&spm_id_from=pageDriver&vd_source=2806005ba784a40cae4906d632a64bd6
 * 12.2 Docker核心概念
 * Docker主机（host）:安装了docker程序的机器（在操作系统之上，windows或者linux等等）
 * Docker客户端（client）:连接Docker主机进行操作
 * Docker仓库（repository）：用来保存各种打包好的软件镜像的
 * Docker镜像（images）:软件打包好的镜像，这些镜像放在仓库中
 * Docker容器（container）:镜像启动以后得实例，称之为容器
 *
 * 12.3 使用Docker的步骤：
 * 1.安装Docker
 * 2.去Docker仓库找到这个软件的镜像
 * 3.使用Docker命令运行这个镜像，运行中的镜像称为容器
 * 4.对容器启动的停止，就是对软件的启动停止
 *
 * 12.4 安装Docker的步骤：
 * 1.VMWare、这里用virtual box，免费的虚拟机
 * 2.导入虚拟机文件centos7
 * 3.启动虚拟机，输入用户名和密码（默认是：root 和 123456）
 * 4.使用客户端连接linux进行操作（安装SmarTTY-2.2.msi）
 * 5.设置虚拟机网络
 * 桥接网络-选好网卡-接入网络
 * 6.设置好命令后，重启虚拟机
 * 命令：
 * service network restart
 * 7.查看linux的ip地址
 * 命令：ip addr
 * 8.打开SmartTTY，输入
 * 虚拟机ip地址
 * 用户名：root
 * 密码：123456
 * 保存一下，save key,就弹出了一个界面
 * 9.就可以在当前客户端中输入命令
 *
 * 12.5 在虚拟机上安装Docker
 * 1.检查内核版本，要求必须是3.10及其以上
 * 命令：uname -r
 * 2.安装docker 命令：yum install docker
 * 3.启动docker 命令：systemctl start docker
 *
 * 12.6 Docker常用命令-镜像操作
 * a.搜索镜像 docker search 软件名称
 * 例如：docker search mysql
 *
 * b.拉取镜像 docker pull 软件名称[:tag版本号]，不带版本号，默认拉取最新版本
 * 例如：docker pull mysql:5.7.43
 * docker pull mysql等价于docker pull mysql:latest
 *
 * c.删除指定的镜像 docker rmi image-id
 *
 * bug:
 * docker证书过期，不能pull，解决参考文档：
 * https://blog.csdn.net/qq_29864051/article/details/138305735
 *
 * 12.7 Docker常用命令-容器操作
 * 软件镜像-运行镜像-产生一个容器
 *
 * a.搜索和拉取镜像后，运行镜像（docker search 镜像名、docker pull 镜像名）
 * 运行镜像，产生一个容器
 * docker run --name myTomcat -d tomcat:latest
 * 语法：docker run --name (自己命名) -d 镜像名:版本号 ，其中-d 表示后台运行
 *
 * 【docker常用问题排查步骤】
 * 1.检查docker服务是否正常运行？
 * systemctl status docker
 * 如果服务没有运行？
 * systemctl start docker
 *
 * 2.检查docker镜像是否存在？
 * docker images
 * 如果列表为空，拉取一个镜像
 * docker pull 镜像名
 *
 * 3.检查docker容器是否存在
 * docker ps -a
 *
 * 4.清理无用的容器和镜像
 * docker container prune
 *
 * 清理无用的镜像
 * docker image prune
 *
 * 以上都没解决？
 * 重启服务
 * systemctl restart docker
 * 并运行镜像
 * docker run --name 自己命名 -d 镜像:tag版本号
 *
 *
 *
 *
 *
 *
 *
 * b.
 * c.
 * d.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */