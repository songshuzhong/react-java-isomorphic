## 能力开放平台--门户互联网化项目

### 一、项目结构图
````````````````````````````````````````````````````````````````````````
├─build                             （存放编译后文件）
│  └─static                        （存放编译后文件资源）
│      ├─css                       （存放编译后cs文件）
│      ├─js                        （存放编译后js文件）
│      └─media                     （存放编译后字体文件）
├─client                            （前端开发目录）
│  ├─images                        （image存放目录）
│  ├─media                         （媒体文件存放目录）
│  ├─scripts                       （页面与组件存放目录）
│  │  ├─components                （组件存放目录）
│  │  │  ├─commons               （通用组件存放目录）
│  │  │  ├─data                  （数据组件存放目录）
│  │  │  ├─login                 （登录组件存放目录）
│  │  │  ├─products              （产品组件存放目录）
│  │  │  ├─documentation         （文档组件存放目录）
│  │  │  └─home                  （home组件存放目录）
│  │  ├─config                    （前端路由跳转配置文件）
│  │  ├─pages                     （页面存放目录）
│  │  │  ├─data                  （数据页面存放目录）
│  │  │  ├─login                 （登录页面存放目录）
│  │  │  ├─products              （产品页面存放目录）
│  │  │  ├─documentation         （文档页面存放目录）
│  │  │  └─home                  （home页面存放目录）
│  │  └─utilities                 （前端开发工具库）
│  └─styles                        （样式开发目录）
│      └─index
├─config                            （webpack配置文件）
├─node_modules                      （前端依赖）
├─public                            （静态资源存放目录）
└─server                            （前端服务器模拟restful接口）
    └─controllers
````````````````````````````````````````````````````````````````````````

### 二、环境搭建
````````````````````````````````````````````````````````````````````````
1.安装nodeJS，最新版本为好
2.安装git
3.clone项目
4.开发工具统一webstorm
````````````````````````````````````````````````````````````````````````

### 三、项目运行
````````````````````````````````````````````````````````````````````````
1.npm install
2.npm run build
3.npm run start
4.访问http://localhost:3000

注：每次更新接口路由或less文件时，需要重新npm run build
````````````````````````````````````````````````````````````````````````
### 四、其他问题
````````````````````````````````````````````````````````````````````````
如在开发中遇到有关EPM-UI问题，请联系：
[yangdonglei@bonc.com.cn]研发五部-杨东磊
[zhangfeiyu@bonc.com.cn] 研发五部-张飞宇
[songshuzhong@bonc.com.cn]联通事业部 研发二部-产品宋述重
````````````````````````````````````````````````````````````````````````