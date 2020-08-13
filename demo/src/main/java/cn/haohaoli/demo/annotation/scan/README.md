## @ComponentScan

```java
@ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan")
@ComponentScan(value = "cn.haohaoli.demo.annotation.scan")
```

### 排除

```java
// 排除带有`Controller`、`Service`注解的类
@ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", excludeFilters = {
    @ComponentScan.Filter(classes = {Controller.class, Service.class})
})
```

### 包括

```java
// 包括带有`Service`、`Repository`注解的类.`useDefaultFilters`属性需要标记为`false`,默认为`true`.否则不生效
@ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
    @ComponentScan.Filter(classes = {Service.class, Repository.class})
}, useDefaultFilters = false)
```

### useDefaultFilters 属性

`useDefaultFilters`属性默认为`true`会自动加载带有`@Component`,`@Repository`、`@Service`或`@Controller`注解的类

### Filter

+ FilterType(默认为`ANNOTATION`)
    - `ANNOTATION`
    - `ASSIGNABLE_TYPE`
    - `ASPECTJ`
    - `REGEX`
    - `CUSTOM`

#### ASSIGNABLE_TYPE

指定类型

```java
// 包含带有`Repository`注解的类并且指定的`ServiceExample`类
@ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Repository.class}),
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ServiceExample.class)
}, useDefaultFilters = false)
```

#### REGEX

正则

```java
// 包含类名(开头为C或者S并且以Example结尾)的类
@ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
    @ComponentScan.Filter(type = FilterType.REGEX, pattern = "cn.haohaoli.demo.annotation.scan.*.(C|S)+.*Example")
}, useDefaultFilters = false)
```

#### CUSTOM

自定义过滤器,继承`org.springframework.core.type.filter.TypeFilter`类

```java
@ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomFilter.class)
}, useDefaultFilters = false)
```