# MiniLoggr for Android

MiniLoggr for Android is small utility class that extends standard android.util.Log with additional features.

This document is available in other languages: English, Srpski or Српски

## Features

Additional features in this logger implementation are:
+ Tag name is automatically initialized with calling class name.
+ Debug messages will be generated only if the app is built using debug variant.  
  You don't have to check BuildConfig.DEBUG to exclude debug log statements, it is done automatically.
+ Added new overloaded log methods for creating more complex log messages.

   You can enter unlimited number of arguments(objects and primitive types) and the logger will concatenate them in
   receiving order using StringBuilder internally.  
    
   Example: 
   ```java
   logger.d("Numbers ", 3, " and ", 3.44f, " are not equal.(", true, ")") 
    ```
## Install


## Example

Example class with logger usage.


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


## License
*  **Apache 2.0 License**
[![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0)
*  **Android is a trademark of Google LLC.**

## Author

* **Branislav Lungulović** - branislav.lungulovic@gmail.com
