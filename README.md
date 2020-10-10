## SpringBoot整合 Screw 生成数据库文档

### 使用说明

启动test包下的GenDocumentApplicationTests中的run()方法生成数据库文档

### 配置文件说明

#### 数据库配置

```yaml
spring:
  datasource:
    # url
    url: jdbc:mysql://localhost:3306/febs_base?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8
    # 驱动
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 用户名
    username: root
    # 密码
    password: root
    hikari:
      data-source-properties:
        # 开启表注释
        useInformationSchema: true
```

#### 自定义配置

```yaml
zy:
  # 生成文件路径
  fileOutputDir: F:\tmp
  # 生成文档后是否打开文件目录
  openOutputDir: false
  # 生成文件类型 html/word/md
  fileType: md
  # 生成模板实现 freemarker/velocity
  produceType: freemarker
  # 生成文件名的版本
  version: 1.0.0
  # 生成文档信息描述
  description: 生成文档信息描述
  # 两种生成策略，一种忽略指定的表、表前缀、表后缀去生成；二种是是根据指定的表、表前缀、表后缀去生成.
  # 1采用忽略策略，2采用指定策略
  strategy: 1
  # 表名
  tableName:
    - aa
    - test_group
  # 表前缀
  prefix:
    - a
    - b
  # 表后缀
  suffix:
    - _test
    - czb_
```