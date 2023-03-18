# Simulation-of-the-machine

![](https://github.com/SzDudek/Simulation-of-the-machine/blob/61c9cb87cb865adc16c0830eae2afbf14be0f908/217991739-1b9436e4-0240-4119-87b3-694dc6f21685.png?raw=true) 

### Project for university
The assignment was to create an application with user-friendly graphical user interface based on SWING or JavaFX classes. The application that was to be build was to visualise the operation (movement) of simple machine shown in the picture above. </br>

**Short description of the machine:**</br>

The machine is placed in the x,y coordinate system. The machine consists of two articulated arms. The first is driven and carries out reciprocating motion along the y-axis (the end positions are shown in the figure), while the motion of the second arm is to result from imposed constraints. This second arm passes through an articulated slider. The geometry of the machine is parameterised with the following values: l1 - length of the first arm, l2 - length of the second arm, s - maximum stroke, d and h - respectively, the horizontal and vertical distance of the slider from the origin of the coordinate system. As can be seen, the centre of the slider, and at the same time its axis of rotation, is located at the point ps=(d,h).

**Functionalities of the program:**
- Adjusting machine parameters and running a simulation
- Displaying graphs of the horizontal and vertical components of the velocity of the p2 point (Velocities are calculated by integrating the position of the point. There is a simplification used - the difference between the current and previous position of point p2 in successive steps during the simulation is calculated. The velocity is proportional to this difference.)

**Application Setup:**

The application to be built is intended to be modular. Therefore, appropriate dependency entries should appear in its module-info.java descriptor. If SWING classes are used, this will be: module windows { requires java.desktop; } while if JavaFX classes are used, this will be:
```module SimpleFX { requires javafx.controls; requires javafx.fxml;
opens application to javafx.graphics, javafx.fxml;} 
```
Since the SWING classes belong to the standard JDK distribution the command that launches the application does not need to correct the module path. If you place the compiled classes of the application in the "somethingWindows" directory, the command will take the form:
```
java.exe -p "somethingtamWindowsbin" -m windows/app.Framework 
```
If one builds a jar with the application, this jar is added to the module path:
```
java.exe -p windows.jar -m windows/app.
```
Frame JavaFX is a separate runtime that requires a separate installation. Therefore, the command that starts the application must take into account the location of the modules provided by this runtime. This command usually takes the form of: 

```
2libjavafx.graphics.jar;E:Javajavafx-sdk-17.0.2libjavafx.media.jar;E:Javajavafx-sdk-17.0.2libjavafx. swing.jar;E:Javavafx-sdk-17.0.2libjavafx.web.jar;E:Javavafx-sdk-17.0.2libjavafx-swt.jar" -m SimpleFX/application.Main 
```

In the example above, the paths to all modules are included. In fact, it would be sufficient to indicate the paths only to the required modules. As in the previous case, the built jar could be indicated instead of the path to the compiled application classes. When working with Java modules, it is not possible to use libraries of compiled classes as freely as before. Normally, it is not possible to make a fatjar by including byte codes of classes from different libraries in one root. This is because now we are dealing with modules, each of which has its own module-info descriptor. And according to the specification (https://docs.oracle.com/javase/9/docs/specs/jar/jar.html#Modular), a modular jar can only contain one module descriptor (the module-info.class file) located in the root of the archive. So normally it is not possible to put multiple modules in one jar. There are workarounds for this problem - by implementing a custom class loader that will 'pull' modules packed into some structure inside the jar. But this is a bit of a tricky business.

Typically, one jar contains one module. Selected modules can be linked to create your own runtime environment. Linking is done using the jlink tool (see: https://livebook.manning.com/book/the-java-9-module-system/chapter-4/76 https://www.developer.com/design/how-modules-are-packaged-in-java-9/ ). It can be done like this (with appropriate attributes to the -p, --add-modules option)
```
jlink -p . --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,myfxdemo --launcher runapp=myfxdemo/org.openjfx.MyApp --output app
```
The result will be a directory with everything you need to run the indicated class (by no means a single file). But beware - in the case of native libraries, you still need to copy the dependencies in the form of dynamically loaded libraries into this environment. So if you are building an application entirely modularly, it is not possible to make one fatjar. The remaining details are to be as agreed at the beginning of the class.

Translated with www.DeepL.com/Translator (free version)
