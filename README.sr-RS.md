# Android MiniLoggr

Android MiniLoggr je mala pomoćna klasa za logovanje poruka koja proširuje standardnu android.util.Log klasu.

Dokument možete pročitati i na drugim jezicima: English, Srpski ili Српски

## Funkcionalnosti

Dodatne funkcionalnisti koje ova implementacija loga donosi su:
- Instanciranje logera na jednom mestu sa automatskim generisanjem taga sa nazivom klase.
- Izostavljanje debug poruka ukoliko je aplikacija bildovana u debug modu. To znaci da je nepotrebno korististi proveru tipa if(BuildConfig.DEBUG) sto je najbolja praksa kod logovanja.
- Dodate nove metode za logovanje koje omogucavaju da se unese niz vrednosti koje mogu biti ili objekti ili primitivni tipovi odvojeni zarezom. Interno, metoda koristi StringBuilder za njihovu efikasnu konkatenaciju. Primer: logger.d("Numbers ", 3, " and ", 3.44f, " are not equal.(", true, ")")

## Instaliranje


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
