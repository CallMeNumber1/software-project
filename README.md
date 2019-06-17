##### 系统程序课程设计后端

软件工程专业工作管理系统

基于 SpringMVC Spring JPA 框架设计，使用 Vue 前后端分离技术。

###### 用例分析

![](https://raw.githubusercontent.com/zhanyeye/Figure-bed/img/img/20190617135955.png)

###### ER图

![](https://raw.githubusercontent.com/zhanyeye/Figure-bed/img/img/20190616214131.png)

###### 



###### 技术实现

- 拦截器，实现登录验证，实现请求权限验证，允许基于请求路径，或自定义注释+反射实现

- 请求权限验证

- 理解 Many to Many 关系的拆分

- Spring 定时器
- 加密用户密码
- 异常与转抛自定义异常
- 全局异常处理
- 错误/异常信息反馈



###### 项目结构

- [x] **entity**  
  |--- Teacher  
  |--- Exam  
  |--- ExamDetail  
  |--- Task  
  |--- TaskDetail  


- [x] **repository**  
  |--- CustomizedRepository  
  	|---  CustomizedRespoistoryImpl  
  |--- TeacherRepository  
  |--- ExamRepository  
  |--- TaskRepository  
  |--- ExamDetailRepository  
  |--- TaskDetailRepository  


- [x] **service**  
  |--- initService  
  |--- AddDataService  
  |--- ExamService  
  |--- TaskService  
  |--- UserService 


- [x] **component**   
  |--- EncryptorComponent  // 加密解密组件   
  |--- Timer   // 定时器    
  |--- TimeUtils  // 时间冲突判断组件  


- [x] 配置  
  |--- SoftwareProjectApplication  
  |--- WebMvcConfig  


- [x] **interceptor**  
  |--- AdminInterceptor  
  |--- LoginInterceptor  


- [x] **controller**   
  |--- AdminController  
  |--- LoginController  
  |--- ExamController  
  |--- TaskController  
  |--- UserController  
  |--- ExceptionController  
  
- [x] **util**  
  |--- Invigilation

