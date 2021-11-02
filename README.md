# Code Snippets Accumulator

![Logo](https://github.com/melwyncarlo/Code_Snippets_Accumulator/blob/main/CodeSnippetsAccumulator/ui/res/img/icon.png)

#### An app for storing, searching, and retrieving code snippets.

<br>

**Note:**

* Download the [\*.deb file](https://github.com/melwyncarlo/Code_Snippetfs_Accumulator/blob/main/package/linux/deb/codesnippetsaccumulator_1.0-1_amd64.deb?raw=true) to run on Linux. As I am not experienced in packaging java applications for native systems, the \*.deb file has not been tested, and may be subject to errors.

* Download the [\*.jar file](https://github.com/melwyncarlo/Code_Snippets_Accumulator/blob/main/jar/CodeSnippetsAccumulator.jar?raw=true) to run on any operating System (Windows, Linux, MacOS, etc.) having one of the JREs (e.g. [OpenJDK](https://jdk.java.net/17/)) installed. The \*.jar file is guaranteed to work using a Java Runtime Environment (JRE); also referred to as Java Virtual Machine (JVM).

* To compile and build native executables, make sure to download the relevant [OpenJFX Version 17.0.1 SDK/JMod files](https://gluonhq.com/products/javafx/).

* If you're compiling normally, using the `javac` and `java` commands, then make the following changes in the `Model.java` file:

   * *Un-comment* the code at line number 302 :
      `private static String APP_DIR_PATH = "CodeSnippetsAccumulator";` 

   * *Comment* out the code at line number 304 :
      `private static String APP_DIR_PATH = System.getProperty("filepath");` 
      
   * If you're compiling for packaging purposes using `package` or `jpackage`, then reverse the above changes.

<br>

![Screenshot 1](https://github.com/melwyncarlo/Code_Snippets_Accumulator/blob/main/package/0screenshots/1.png)

![Screenshot 2](https://github.com/melwyncarlo/Code_Snippets_Accumulator/blob/main/package/0screenshots/2.png)

![Screenshot 3](https://github.com/melwyncarlo/Code_Snippets_Accumulator/blob/main/package/0screenshots/3.png)

![Screenshot 4](https://github.com/melwyncarlo/Code_Snippets_Accumulator/blob/main/package/0screenshots/4.png)

![Screenshot 5](https://github.com/melwyncarlo/Code_Snippets_Accumulator/blob/main/package/0screenshots/5.png)


