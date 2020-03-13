# Intercom


## Introduction
This is a CLI application that calculates great-circle distance with respect to intercom's office location using [this formula](https://en.wikipedia.org/wiki/Great-circle_distance) of the given customer list 
in a txt file. The application writes a new file with the customers name and id which is less than or equal to 100 km. 

## Setup
Prefered IDE for this application is Intellij but other IDEs can be used as well

``New -> Project from existing resources -> Project``

OR

``New -> Project from version control -> https://github.com/kaabus/intercom/new/master -> clone``

#### Note - 
Eclipse does not support test cases


## Run

This is a gradle based java project so gradle commands can be used to build the jar. While creating a jar, all the test cases are also run, and if the 
test cases fail, the jar does not build

#### Create a jar file

``$gradle jar``

#### Run test cases

``$gradle testClasses``

Alternatively it can also be run within the IntelliJ console. 

#### Once the jar is created, following command should be used to run the application

``java -jar intercom-1.0-SNAPSHOT.jar -i <inputfilepath> -o <outputfilepath>``

Console will print out the output file location.


#### Note - 
input.txt has been added to the ``intercom/build/libs`` folder

## Javadoc and Coverage Report 
The documentation for the project is provided in the core directory. 
`` ./javadoc/``
The code coverage can be checked in the ``./coverageReport/`` folder 

## Known Issues
When the user inputs the file path for both output and input, it does not check if it is a valid path. 


