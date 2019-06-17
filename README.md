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
- Many to Many 关系的拆分
- Spring 定时器
- 加密用户密码
- 异常与转抛自定义异常
- 全局异常处理
- 错误/异常信息反馈



###### 项目结构

```
  entity  
  |--- Teacher  
  |--- Exam  
  |--- ExamDetail  
  |--- Task  
  |--- TaskDetail  


  repository  
  |--- CustomizedRepository  
  	|---  CustomizedRespoistoryImpl  
  |--- TeacherRepository  
  |--- ExamRepository  
  |--- TaskRepository  
  |--- ExamDetailRepository  
  |--- TaskDetailRepository  


  service  
  |--- initService  
  |--- AddDataService  
  |--- ExamService  
  |--- TaskService  
  |--- UserService 


  component   
  |--- EncryptorComponent  // 加密解密组件   
  |--- Timer   // 定时器    
  |--- TimeUtils  // 时间冲突判断组件  


  配置  
  |--- SoftwareProjectApplication  
  |--- WebMvcConfig  


  interceptor  
  |--- AdminInterceptor  
  |--- LoginInterceptor  


  controller   
  |--- AdminController  
  |--- LoginController  
  |--- ExamController  
  |--- TaskController  
  |--- UserController  
  |--- ExceptionController  
  
  util  
  |--- Invigilation
```




###### 项目总结

1. 明确需求和设计后再开发，可以减少很多工作量，避免频繁的修改；
2. 前后端需要先统一好接口（确定所需要的参数和返回值），然后就可以分开工作了。
3. 通过这次探索和尝试，明白了软件项目过程规范的重要性和“软件危机”为什么会产生。
4. `java` 的命名规范是驼峰式的，用连字符会被笑话的。
5. DTO : data transfer object
