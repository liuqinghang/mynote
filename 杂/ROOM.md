@[TOC]
#Android-Room 数据库
#### 前言:
  Google发布了一个和SQLite相关的库该库能帮助您在运行应用程序的设备上创建应用程序的数据缓存。

  这个缓存是你的应用程序唯一的真实来源，允许用户查看应用程序中关键信息的一致副本，而不管用户是否有Internet连接。
#### 作用：
     Room提供了一个访问SQLite的抽象层，以便在利用SQLite的全部功能的同时进行流畅的数据库访问
    
    方式是要在项目中使用Room需要在程序的build.gradle文件中添加以下依赖
#### 优点：
    ① SQL查询在编译时就会验证 - 在编译时检查每个@Query和@Entity等，这就意味着没有任何运行时错误的风险可能会导致应用程序崩溃（并且它不仅检查语法问题，还会检查是否有该表）
    
    ② 较少的模板代码
    
    ③ 与 LiveData 集成
***
## 使用
####1，添加依赖
在app/build.gradle中添加以下依赖
```java
    implementation 'android.arch.persistence.room:runtime:1.0.0'
    annotationProcessor 'android.arch.persistence.room:compiler:1.0.0'
```
#### 主要构成：
   - Database：

    * 继承RoomDatabase的抽象类
    
    * 在注释中包括与数据库关联的实体列表（@Database(entities ={ })）
    
    * 包含一个无参的抽象方法并返回一个使用@Dao注解的类

   - Entity：对应数据库中的表

   - DAO：包含访问数据库的方法



***
#### 2，创建JavaBean
*Entity：表示数据库内的表。*


```
    @Entity //建表
    public class User {
     
     @PrimaryKey(autoGenerate = true)//主键是否自动增长，默认为false
     private int id;
     private String name;
     private int age;
    
     public int getId() {
     return id;
     }
    
     public void setId(int id) {
     this.id = id;
     }
    
     public String getName() {
     return name;
     }
    
     public void setName(String name) {
     this.name = name;
     }
    
     public int getAge() {
     return age;
     }
    
     public void setAge(int age) {
     this.age = age;
     }

    }
```
- 主键
  
   这里需要使用 @Entity 来注解该类
   
    1. 至少要有一个主键 @PrimaryKey
    如果该实体具有复合主键，则采用以下格式
    *@Entity(primaryKeys = {"Key1", "Key2"}) public ...*
   
    2. 如果需要指定表名：
    @Entity(tableName = "users") ...

- 索引
  
   根据数据的访问方式，可能需要索引数据库中的某些字段以加快查询速度。若要向实体添加索引，在@Entity注释中包含索引属性，列出要包含在索引或复合索引中的列的名称
```android
@Entity(indices = {@Index("name"),
        @Index(value = {"last_name", "address"} unique = true)})
//当某些字段或字段组必须是唯一的时候，拥有Index注解的唯一属性设置为*true* 即unique = true
public class User {
    @PrimaryKey
    public int id;

    public String firstName;
    public String address;

    @ColumnInfo(name = "last_name")//指定列的名字
    public String lastName;

    @Ignore
    Bitmap picture;
}
```
   - 外键
```  
   @Entity(foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = "id",
                                  childColumns = "user_id"))
```


####3，创建DAO
   DAO代表数据访问对象，所以它是告诉我们的数据库如何操作数据的一种方式：

   @Insert , @Update , @Delete , @Query 代表我们常用的 插入 、 更新 、 删除 、 查询 数据库操作
```
    @Dao
    public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();
    
     @Insert
     void insert(User... users);
    
     @Update
     void update(User... users);
    
     @Delete
     void delete(User... users);
    }

```
@Query 也可以返回多种不同的类型

    @Query("SELECT * FROM user")
    List<User> getAllUsers();
    
    @Query("SELECT * FROM user WHERE id=:id")
    User getUser(int id);
    
    @Query("SELECT * FROM user")
    Cursor getUserCursor();

@Embedded 嵌套对象

    public class Address {
    public String street;
    public String state;
    public String city;
    
    @ColumnInfo(name = "post_code")
    public int postCode;
    }
    
    @Entity
    public class User {
        @PrimaryKey
        public int id;
    
    public String firstName;
    
    @Embedded
    public Address address;
    }
#### 4，创建DATABASE
   以下是一个实例：

```java
@Database(entities = { User.class }, version = 1,exportSchema = false)
    public abstract class UserDatabase extends RoomDatabase {
    
     private static final String DB_NAME = "UserDatabase.db";
     private static volatile UserDatabase instance;
    
     static synchronized UserDatabase getInstance(Context context) {
     if (instance == null) {
      instance = create(context);
     }
     return instance;
     }
    
     private static UserDatabase create(final Context context) {
     return Room.databaseBuilder(
      context,
      UserDatabase.class,
      DB_NAME).build();
     }
    
     public abstract UserDao getUserDao(); //抽象使用
    }
```
####5，使用DATABASE
    
   插入数据
    例：
      
    1，
    User user=new User();
    user.setName("name1");
    user.setAge(18);
    UserDatabase
     .getInstance(context)
     .getUserDao()
     .insert(user);
     
    2，
    List<User> allUsers = UserDatabase
     .getInstance(RoomActivity.this)
     .getUserDao()
     .getAllUsers();

***
## 访问数据
>Insert
>Update
>Delete
>Query

请参考链接：http://www.imooc.com/article/279763
来源：慕课网

## 数据库升级
```sql
Room.databaseBuilder(getApplicationContext(), MyDb.class, "database-name")
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();

static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                + "`name` TEXT, PRIMARY KEY(`id`))");
    }
};

static final Migration MIGRATION_2_3 = new Migration(2, 3) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE Book "
                + " ADD COLUMN pub_year INTEGER");
    }
};
```
***
详细内容参考以下

*http://www.imooc.com/article/279763*
*https://www.jb51.net/article/136218.htm*


​    
​    
​    
​    
​    
​    
​    
​    