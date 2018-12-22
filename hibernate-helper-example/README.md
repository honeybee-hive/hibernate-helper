# hibernate-helper

## 环境
- JDK1.8
- Hibernate5.0以上版本

## 开发文档说明
pom.xml
```yaml
<!--基于JPA-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>2.1.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>com.github.hibernate.helper</groupId>
    <artifactId>hibernate-helper</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

```java
// 初始化
public class BaseDao {

    // 加载JPA数据源
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    
    // 定义工具类
    protected HibernateHelper helper;

    @PostConstruct
    public void init() {
        // 需要初始化SessionFactory
        helper = new HibernateHelper(entityManager.getEntityManagerFactory().unwrap(SessionFactory.class));
    }

}
```

JPA事务开启及设置Hibernate事务管理
```java
@SpringBootApplication(scanBasePackages = {"com.mnt.health.common", "com.mnt.health.example"})
// 开启事务管理
@EnableTransactionManagement
public class HealthExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthExampleApplication.class, args);
    }

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    // 设置Hibernate事务管理
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(entityManager.getEntityManagerFactory().unwrap(SessionFactory.class));
        return transactionManager;
    }
}
```

例子表结构如下：

班级表：
```mysql
CREATE TABLE `sch_grade` (
  `grade_id` varchar(64) NOT NULL COMMENT '班级编号',
  `grade_name` varchar(100) NOT NULL COMMENT '班级名称',
  `grade_state` varchar(30) NOT NULL COMMENT '班级状态',
  `grade_type` varchar(30) DEFAULT NULL,
  `grade_remark` varchar(500) DEFAULT NULL COMMENT '班级信息',
  `teacher_id` varchar(64) NOT NULL COMMENT '班主任',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

教师表：
```mysql
CREATE TABLE `sch_teacher` (
  `teacher_id` varchar(64) NOT NULL COMMENT '主键',
  `teacher_code` varchar(20) NOT NULL COMMENT '工号',
  `teacher_name` varchar(60) NOT NULL COMMENT '姓名',
  `teacher_state` varchar(30) DEFAULT NULL COMMENT '教师状态',
  `teacher_sex` varchar(30) NOT NULL COMMENT '性别',
  `teacher_birthday` date NOT NULL COMMENT '出生日期',
  `teacher_phone` varchar(22) NOT NULL COMMENT '手机号码',
  `teacher_desc` varchar(500) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

1.1 不带条件的单表实体类查询

实体类定义如下：
```java
@Entity
@Table(name = "sch_grade")
public class Grade{
    private String gradeId;
    private String gradeName;
    private String gradeState;
    private String gradeType;
    private String gradeRemark;
    private String teacherId;
    
    //... 省略get/set
}
```

查询方法如下：
```java
public List<Grade> find() {
    return helper.find(Grade.class);
}
```

以上方法生成的SQL语句显示如下，
- 默认别名是类名
    例如：Grade.java生成的别名是Grade
- 实体类字段命名必须与表的字段匹配按照驼峰式命名规则"_"区分大写
    例如：gradeId生成的SQL字段名是grade_id
```mysql
SELECT
    Grade.grade_id AS gradeId,
    Grade.grade_name AS gradeName,
    Grade.grade_state AS gradeState,
    Grade.grade_type AS gradeType,
    Grade.grade_remark AS gradeRemark,
    Grade.teacher_id AS teacherId       
FROM
    sch_grade AS Grade
```
1.2 分页查询班级信息

查询方法如下：
```java
public Page<Grade> findPage(int page, int size) {
    // 查询的实体类及分页信息
    return helper.findPage(Grade.class, page, size);
}
```

```mysql
    SELECT
        Grade.grade_id AS gradeId,
        Grade.grade_name AS gradeName,
        Grade.grade_state AS gradeState,
        Grade.grade_type AS gradeType,
        Grade.grade_remark AS gradeRemark,
        Grade.teacher_id AS teacherId       
    FROM
        sch_grade AS Grade limit ?
```

1.3 使用表实体类作为条件查询班级信息

查询方法如下：
```java
public List<Grade> findCondition(Grade grade) {
    return helper.findCondition(Grade.class, grade);
}
```
实体类如下：
```java
@Entity
@Table(name = "sch_grade")
public class Grade{
    
    private String gradeId;
    // 注：当是Null的时候此字段被过滤不进行条件组合
    // LIKE搜索方式LIKE/LIKE_BEFORE/LIKE_AFTER三种搜索
    @QueryConditionAnnotation(symbol = SymbolConstants.LIKE)
    private String gradeName;
    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String gradeState;
    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String gradeType;
    @QueryConditionAnnotation(symbol = SymbolConstants.LIKE)
    private String gradeRemark;
    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String teacherId;
    
    //... 省略get/set
}
```
设置gradeName、gradeState生成的SQL语句如下：
```mysql
    SELECT
        Grade.grade_id AS gradeId,
        Grade.grade_name AS gradeName,
        Grade.grade_state AS gradeState,
        Grade.grade_type AS gradeType,
        Grade.grade_remark AS gradeRemark,
        Grade.teacher_id AS teacherId       
    FROM
        sch_grade AS Grade 
    WHERE
        Grade.grade_name LIKE? 
        AND Grade.grade_state=?
```
1.4 使用自定义类作为条件查询班级信息，类的名称必须同实体类或表字段相同；

查询方法如下：
```java
public List<Grade> findByGrade(C_Grade grade) {
    return helper.findCondition(Grade.class, grade);
}
```
自定义类如下：
```java
public class C_Grade {

    @QueryConditionAnnotation(symbol = SymbolConstants.LIKE)
    private String gradeName;

    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String gradeState;

    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String gradeType;
    
    //... 省略get/set
}
```
生成的SQL语句如下：
```mysql
    SELECT
        Grade.grade_id AS gradeId,
        Grade.grade_name AS gradeName,
        Grade.grade_state AS gradeState,
        Grade.grade_type AS gradeType,
        Grade.grade_remark AS gradeRemark,
        Grade.teacher_id AS teacherId       
    FROM
        sch_grade AS Grade 
    WHERE
        Grade.grade_name LIKE?
```

1.5 多表关联条件查询并指定返回的字段集合，使用别名对应自定义类

使用方法如下：
```java
public List<GradeTeacherDTO> findGradeTeacherDTO(C_GradeTeacher findGradeTeacher) {
    // SQL工具类：getSQLFields获取指定类的字段（使用驼峰式命名方法与返回的别名一致），
    // 其中grade是别名，如果不定义则默认是返回类的名，优先获取类定义注解的属性别名
    // 别名使用优先级：注解属性名称 > 参数 > 默认
    String sqlFields = SQLHelper.getSQLFields(GradeTeacherDTO.class, "grade");
    // SQL工具类：getQueryCondition通过注解配置获取条件语句及排序语句，
    // 动态加载机制：当被注解的值是NULL则不生成SQL会被过滤
    QueryCondition queryCondition = SQLHelper.getQueryCondition(findGradeTeacher, "grade");

    // 可以不使用工具直接写SQL语句
    String sql = "SELECT " + sqlFields + " "
            + "     FROM sch_grade AS grade"
            + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
            + queryCondition.getQueryString()
            + queryCondition.getOrderString();
    
    return helper.findBySQL(
            sql, // 自定义的SQL语句
            GradeTeacherDTO.class, // 返回的类 
            queryCondition.getQueryParams()); // 查询条件参数
}
```
设置gradeName、gradeState属性生成的SQL语句如下：
```mysql
    SELECT
        grade.grade_name AS gradeName,
        grade.grade_state AS gradeState,
        grade.grade_type AS gradeType,
        grade.grade_remark AS gradeRemark,
        teacher.teacher_id AS teacherId,
        teacher.teacher_code AS teacherCode,
        teacher.teacher_name AS teacherName,
        teacher.teacher_birthday AS teacherBirthday,
        teacher.teacher_phone AS teacherPhone      
    FROM
        sch_grade AS grade         
    INNER JOIN
        sch_teacher AS teacher 
            ON grade.teacher_id = teacher.teacher_id 
    WHERE
        grade.grade_name LIKE? 
        AND grade.grade_state=?
```
1.6 多表关联条件分页查询并指定返回的字段集合，使用别名对应自定义类

使用方法如下：
```java
   public Page<GradeTeacherDTO> findPageByGradeTeacherDTO(C_GradeTeacher findGradeTeacher, int page, int size) {
        String sqlFields = SQLHelper.getSQLFields(GradeTeacherDTO.class, "grade");
        QueryCondition queryCondition = SQLHelper.getQueryCondition(findGradeTeacher, "grade");

        String sql = "SELECT " + sqlFields + " "
                + "     FROM sch_grade AS grade"
                + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
                + queryCondition.getQueryString()
                + queryCondition.getOrderString();

        String countSql = "SELECT COUNT(grade.grade_id) "
                + "     FROM sch_grade AS grade"
                + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
                + queryCondition.getQueryString();

        return helper.findPageBySQL(
                sql,// 自定义的sql
                countSql, // 自定义的sql统计语句
                GradeTeacherDTO.class, // 返回的类型
                queryCondition.getQueryParams(), // 查询条件参数
                page, // 当前页数
                size // 返回记录数
                ); 
    }
```
定义返回类及注解定义别名，因为多表查询所以别名不同需要根据定义设置
```java
public class GradeTeacherDTO implements Serializable {
    private static final long serialVersionUID = -4752759310731060916L;

    private String gradeName;
    private String gradeState;
    private String gradeType;
    private String gradeRemark;

    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherId;
    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherCode;
    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherName;
    @QueryFieldAnnotation(aliasName = "teacher")
    private Date teacherBirthday;
    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherPhone;

    //... 省略get/set
}
```

设置gradeName、gradeState属性生成的SQL语句如下：
```mysql
## 查询语句
    SELECT
        grade.grade_name AS gradeName,
        grade.grade_state AS gradeState,
        grade.grade_type AS gradeType,
        grade.grade_remark AS gradeRemark,
        teacher.teacher_id AS teacherId,
        teacher.teacher_code AS teacherCode,
        teacher.teacher_name AS teacherName,
        teacher.teacher_birthday AS teacherBirthday,
        teacher.teacher_phone AS teacherPhone      
    FROM
        sch_grade AS grade         
    INNER JOIN
        sch_teacher AS teacher 
            ON grade.teacher_id = teacher.teacher_id 
    WHERE
        grade.grade_name LIKE? 
        AND grade.grade_state=? limit ?
        
## 分页统计语句        
    SELECT
        COUNT(grade.grade_id)      
    FROM
        sch_grade AS grade         
    INNER JOIN
        sch_teacher AS teacher 
            ON grade.teacher_id = teacher.teacher_id 
    WHERE
        grade.grade_name LIKE? 
        AND grade.grade_state=?
```

1.7 updatePatch方法当实体类字段是Null的时候不进行更新
使用方法如下：
```java
public void updatePatch(Grade updateGrade) {
    helper.updatePatch(updateGrade);
}
```
设置更新内容：
```json
{
  "gradeId": "402880ee675951690167595b3f1d0008",
  "gradeName": "三年四班",
  "gradeRemark": "不被修改的备注",
  "gradeState": "2",
  "gradeType": "2"
}
```
生成的SQL语句如下：
```mysql
    UPDATE
        sch_grade 
    SET
        grade_name=?,
        grade_state=?,
        grade_id=?,
        grade_type=?,
        grade_remark=? 
    WHERE
        grade_id=?
```
同时也支持自定义条件方法如下：
```java
public void updatePatch(Grade updateGrade) {
    List<SQLCondition> conditionParams = ImmutableList.of(SQLCondition.builder("grade_id", SQLSymbol.EQ.toString(), updateGrade.getGradeId()));
    helper.updatePatch(updateGrade,conditionParams);
}
```

#### 附录：支持的动态条件如下
```java
    // %?%
    public static final String LIKE = "LIKE";
    // %?
    public static final String LIKE_BEFORE = "LIKE_BEFORE";
    // ?%
    public static final String LIKE_AFTER = "LIKE_AFTER";
    /** 等于=? */
    public static final String EQ = "EQ";
    /** 大于>? */
    public static final String GT = "GT";
    /** 大于等于 */
    public static final String GE = "GE";
    /** 小于 */
    public static final String LT = "LI";
    /** 小于等于 */
    public static final String LE = "LE";
    /** 不等于 */
    public static final String NEQ = "NEQ";
    /** 在范围 */
    public static final String IN = "IN";
    /** 为空 */
    public static final String IS_EMPTY = "IS_EMPTY";
    /** 不为空 */
    public static final String NOT_EMPTY = "NOT_EMPTY";
```

