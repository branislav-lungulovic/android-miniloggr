# Android MiniLoggr

Android MiniLoggr je mala pomoćna biblioteka za logovanje poruka koja proširuje standardnu android.util.Log klasu.

Dokument možete pročitati i na drugim jezicima: [English](README.md), [Srpski](README.sr-Latn-RS.md) ili [Српски](README.sr-RS.md)

## Funkcionalnosti

Dodatne funkcionalnisti koje ova implementacija loga donosi su:
- Instanciranje logera na jednom mestu sa automatskim generisanjem taga sa nazivom klase.
- Izostavljanje debug poruka ukoliko je aplikacija bildovana u debug modu.  
  To znači da je nepotrebno korististi proveru tipa if(BuildConfig.DEBUG), što je najbolja praksa kod logovanja.
- Dodate nove metode za logovanje koje omogućavaju da se unese niz vrednosti koje mogu biti ili objekti ili primitivni tipovi odvojeni zarezom.   
  Interno, metoda koristi StringBuilder za njihovu efikasnu konkatenaciju. 
  
  Primer: 
  ```
     logger.d("Numbers ", 3, " and ", 3.44f, " are not equal.(", true, ")"); 
  ``` 
## Instaliranje

MiniLoggr je testiran sa Android Pluginom za Gradle, verzija 2.3.3.  

**Preporučeni** način uključivanja biblioteke je dodavanjem sledećih instrukcija u fajl **build.gradle**:

```
releaseCompile('info.androidminiloggr:lib:1.0.0:release@aar') {
   transitive=true
}
debugCompile('info.androidminiloggr:lib:1.0.0:debug@aar') {
   transitive=true
}
```

Na ovaj način, debug poruke će biti logovane samo ako je aplikacija bildovana u debug modu.

**Drugi način** uključivanja biblioteke je dodavanjem:
 
 ```
compile 'info.androidminiloggr:lib:1.0.0:release@aar'
 ```
u **build.gradle**.U ovom slučaju je neophodna dodatna inicijalizacija biblioteke.
 
Primer inicijalizacije:

 ```java
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(BuildConfig.DEBUG);
    }
}
 ```



##### Napomena ukoliko želite da koristite source code biblioteke u Android Studio projektu
Ukoliko vam kompajler prijavi grešku: "Error:Plugin with id 'com.github.dcendents.android-maven' not found.",
biće neophodno da dodate sledeće dve linije u **build.gradle** fajl.  
```
classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.3'
classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
```

## Korišćenje

Primer klase u kojoj se koristi loger.

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


## Licenca
#### Apache 2.0 License
[![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0)

## Autor

* **Branislav Lungulović** - branislav.lungulovic@gmail.com
