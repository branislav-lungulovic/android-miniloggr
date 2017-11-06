# MiniLoggr за Андроид
  
Андроид _MiniLoggr_ је мала помоћна библиотека за логовање порука која проширује стандардну _android.util.Log_ класу.

Документ можете прочитати и на другим језицима: [English](README.md), [Srpski](README.sr-Latn-RS.md) или [Српски](README.sr-RS.md)

## Функционалности

Додатне функционалнисти које ова имплементација лога доноси су:
- Инстанцирање логера на једном месту са аутоматским генерисањем тага са називом класе.
- Изостављање _debug_ порука уколико је апликација билдована у _debug_ моду.  
  То значи да је непотребно користисти проверу типа _if(BuildConfig.DEBUG)_, што је најбоља пракса код логовања.
- Додате нове методе за логовање које омогућавају да се унесе низ вредности које могу бити или објекти или примитивни типови одвојени зарезом.   
  Интерно, метода користи _StringBuilder_ за њихову ефикасну конкатенацију.   
  
  Пример: 
  ```
     logger.d("Numbers ", 3, " and ", 3.44f, " are not equal.(", true, ")"); 
  ``` 
## Инсталирање

_MiniLoggr_ је тестиран са Андроид Плугином за _Gradle_, верзија 2.3.3.  

**Препоручени** начин укључивања библиотеке је додавањем следећих инструкција у фајл __build.gradle__:

```
releaseCompile('info.androidminiloggr:lib:1.0.0:release@aar') {
   transitive=true
}
debugCompile('info.androidminiloggr:lib:1.0.0:debug@aar') {
   transitive=true
}
```

На овај начин, _debug_ поруке ће бити логоване само ако је апликација билдована у _debug_ моду.

**Други начин** укључивања библиотеке је додавањем:
 
 ```
compile 'info.androidminiloggr:lib:1.0.0:release@aar'
 ```
у __build.gradle__. У овом случају је неопходнa додатнa иницијализација библиотеке.
 
Пример иницијализације:

 ```java
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(BuildConfig.DEBUG);
    }
}
 ```



##### Напомена уколико желите да користите _source code_ библиотеке у Андроид Студио пројекту
 Уколико вам компајлер пријави грешку: "_Error:Plugin with id 'com.github.dcendents.android-maven' not found._",
 биће неопходно да додате следеће две линије у __build.gradle__ фајл. 
```
classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.3'
classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
```

## Коришћење

Пример класе у којој се користи _loger_.

```java

package info.androidminiloggr.examples;

import info.androidminiloggr.Logger;

/**
 * Example class for demonstrating MiniLoggr functionalities
 */
public class ExampleClass {

    //Instantiate logger. Tag will be automatically set to class name.
    private static Logger logger = Logger.getLogger();

    /* Alternatively, you could instantiate logger with custom tag name.
     * Like this:
     *
     * private static Logger logger = Logger.getLogger("Some text");
     *
    */

    public void exampleSimpleDebug(){

        logger.d("This is debug message");
        //Log message should be: D/info.androidminiloggr.examples.ExampleClass: This is debug message

    }

    public void exampleDebugWithArrayOfArguments(){

        logger.d("Numbers ", 3, " and ", 3.44f, " are not equal.(", true, ")");
        //Log message should be: D/info.androidminiloggr.examples.ExampleClass: Numbers 3 and 3.44 are not equal.(true)

    }

}

```


## Лиценца
#### Apache 2.0 License
[![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0)

## Аутор

* **Бранислав Лунгуловић** - branislav.lungulovic@gmail.com
