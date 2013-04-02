README
======


Introduction
------------

This is an small example make with javaFx and Maven.
To now, it's juste a SNAPSHOT.You should be able to create an excutable jar and to show a window.

TODO :
* More generic configuration on windows and linux
* Improve documentation.
* Just beginning project ...


How to build and launch
-----------------------


JavaFx was introduced in some version of JDK (e.g : in 1.7.0_17) but in some case you have
to add it even if it exists in JDK (a little bit stange ...).In my case, I have version 1.7.0_17


## Install jfxrt

There is two ways :

### With mvn install :

* mvn install:install-file -Dfile="C:\Program Files\Oracle\JavaFX 2.1.0 SDK\rt\lib\jfxrt.jar" -DgroupId=com.oracle.javafx -DartifactId=javafx -Dversion=2.1 -Dpackaging=jar
* mvn install:install-file -Dfile="/opt/soft/jdk1.7.0_17/jre/lib/jfxrt.jar" -DgroupId=com.oracle.javafx -DartifactId=javafx -Dversion=2.1 -Dpackaging=jar

Then put

		<dependency>
			<groupId>com.oracle.javafx</groupId>
			<artifactId>javafx</artifactId>
			<version>2.1</version>
		</dependency>

### Fill the path for jfxrt.jar include in your jdk.

		<dependency>
			<groupId>com.oracle.javafx</groupId>
			<artifactId>javafx</artifactId>
			<version>2.1</version>
			<scope>system</scope>
			<systemPath>${jdk.home}/jre/lib/jfxrt.jar</systemPath>
		</dependency>

## Fix classpath.

* Execute jfx:fix-classpath before running jfx:build-jar !


## Build an run.

* Execute jfx:build-jar
* java -jar myjar.jar

## Configuration


* Ubuntu 12.10