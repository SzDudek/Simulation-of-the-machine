# Simulation-of-the-machine

![](https://github.com/SzDudek/Simulation-of-the-machine/blob/61c9cb87cb865adc16c0830eae2afbf14be0f908/217991739-1b9436e4-0240-4119-87b3-694dc6f21685.png?raw=true) 

### Project for university
The assignment was to create an application with user-friendly graphical user interface based on SWING or JavaFX classes. The application that was to be build was to visualise the operation (movement) of simple machine shown in the picture above. </br>

**Short description of the machine:**</br>

The machine is placed in the x,y coordinate system. The machine consists of two articulated arms. The first is driven and carries out reciprocating motion along the y-axis (the end positions are shown in the figure), while the motion of the second arm is to result from imposed constraints. This second arm passes through an articulated slider. The geometry of the machine is parameterised with the following values: l1 - length of the first arm, l2 - length of the second arm, s - maximum stroke, d and h - respectively, the horizontal and vertical distance of the slider from the origin of the coordinate system. As can be seen, the centre of the slider, and at the same time its axis of rotation, is located at the point ps=(d,h).

**Functionalities of the program:**
- Adjusting machine parameters and running a simulation
- Displaying graphs of the horizontal and vertical components of the velocity of the p2 point (Velocities are calculated by integrating the position of the point. There is a simplification used - the difference between the current and previous position of point p2 in successive steps during the simulation is calculated. The velocity is proportional to this difference.)
