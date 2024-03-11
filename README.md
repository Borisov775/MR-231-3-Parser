# Parser for MR-231-3

## Available formats of messages received by MR-231-3
- TTM (TrackedTargetMessage)
- RSD (RadarSystemData Messages)
### Additional formats
- VHW (Water Speed and Heading) used in receiver MR-231

## App(main function)

A Papser for the MR-231-3 is implemented in mr-231-3 directory. 
To see the work of main fuction - run the main function in [App.java](path/startApp/src/main/java/org/example/App.java) file

## Docker 
Modules of MR-231-3 works within Docker.
The Dockerfile which is used for creating an image you can sere [here](Code/Dockerfile)

After creating the Dockerfile, build the docker image:

`docker build . --tag mr2313` 

And the run the container

`docker run -it mr2313 bash`

In a bash shell type the following commands for starting an App. That's it:

`cd ../startApp`

And then:

`mvn exec:java -Dexec.mainClass="org.example.App"`

## Tests
In both of modules mr-231 and mr-231-3 implemented tests
- [Tests for MR-231 Parser](Code/mr-231/src/test/java/org/example/searadar/mr231/Mr231Tests.java) 

- [Tests for MR-231-3 Parser](Code/mr-231-3/src/test/java/org/example/searadar/mr2313/Mr2313Tests.java)



