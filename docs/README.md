
# LPOO_71 Crack Attack

> The project goal is to recreate the game [Crack attack](http://www.aluminumangel.org/attack/). The game's goal is to not let the blocks get to the top (like tetris) by making lines with at least 3 colored blocks with the same colour to pop them thus increasing the score. In a nutshell this a game is a mix between tetris and candy crush like games.

The project was developed by the following people:

Paulo Marques- [Github](https://github.com/pdsam)

Moisés Rocha - [Github](https://github.com/MPDR200011)

<!--
> Include here one or two paragraphs explaining the main idea of the project, followed by a sentence identifying who the authors are. 
-->

## Implemented Features

<!--
> This section should contain a list of implemented features and their descriptions. In the end of the section, include two or three screenshots that illustrate the most important features.
-->

- ### Views both in swing and Lanterna

It is possible to use both swing or laterna as the framework for the View.

- ### Start Menu

When the game start you have to press space for it to begin, and not starting instantly when you are not ready.

- ### New lines feeder based on time

A game mechanic where after a certain amount of time a new line appears from the bottom. The frequency of the new line decreases with time.

- ### Score Tracker

Keeping track of the player's score.

- ### Combo Detector and Breaker

Detects combos (lines of at least 3 cubes with the same colour) and breaks them.

- ### Physics Processor

Detects if the blocks need to fall into their places aka. gravity.

- ### "You Lost" and detection

Added a "You Lost" screen and a way to detect when blocks get over the top.

!["Swing GUI"](https://i.imgur.com/b4nuHle.png)
!["Lantera GUI"](https://imgur.com/zy7qwwc.png)

## Planned Features

<!--
> This section is similar to the previous one but should list the features that are not yet implemented. Instead of screenshots you should include GUI mock-ups for the planned features.

-->
!["Original GUI"](http://aluminumangel.org/attack/screen_shot_0.jpg)

- [x] Abstract Factory enabling to switch the view to be implemented both in lanterna and swing.
- [x] Implement the time based element in the game (pushing new lines into the board on a time base).
- [x] Implment the row checker.
- [ ] Blocks that fall from sky(Big red horizontal block in the above picture)

## Design

<!--
> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts: "Problem in Context", "The Pattern", "Implementation" and "Consequences".
-->

### Architectural Pattern

Given the fact that we are doing a game, we opted to use the Model-View-Controller Architectural Pattern.

- Model: Holds all the information to be displayed in the game.
- View: Displays the game with the information of the model, also gathers user input.
- Controller: Responsible for the control of the game.

Since this program has three different screen (Title Screen, Game Screen and Lose Screen), we are going to have a different MVC for each of these.

### Different Graphical Frameworks

#### Problem

In this project we have to accomodate both Lanterna and Swing frameworks for display things to the screen. The problem here that we have multiple MVCs and so we need to switch views once in a while and having to check wether we are using Swing or Lanterna can create a bunch of if statements that lead to some nasty code smells.

#### Solution

We solve this issue by implementing the Abstract Factory design pattern, having an interface that contains methods to create the views for each MVC. These methods return an abstract View that is then implemented for each of the frameworks.

### Large Amount of possible commands

#### Problem

The controller will need to interact with the model diferently depending on the input from the user. Therefore having a large amount of possible commands.

In this project we are working with a sort of event handler that is called by the outside Application class, but we want any model data altering code to be executed by the controller.
This could be resolved by having the handler call methods inside the controller but the real problem starts with the following:
We want the handler to access the controller through the Application in which it works, but the application is only aware of the abstract concept of a controller, so it has no way of knowing which methods this controller has.
This can be resolved by giving the handler the mvc in which it is operating (explained further ahead), but we want each handler to be dependent on as least concrete information as possible (it isn't always possible like it happens on BoardEventHandler).

#### Solution

We opted to use the Command Design Pattern. With this, we can create e "command queue" for controllers to execute, and so when it ticks, it executes the commands that are given to it be the handler.

### Comunication from the board (model) to the controller

#### Problem

We faced a problem where the controller needed to communicate with the board to know what changed so it could process the physics  and check for combos.

#### Solution

The observer design pattern came in handy for this as we made the controller observe the board. As a result, the communication between the board and the controller was made in a much simpler way.

### Unifying Differnt Input Handling Methods

#### Problem

Since in this project we needed to have an option of using either Swing or Lanterna for the display, it introduced a problem in which the way that both frameworks handle input is different.

- Lanterna uses a readInput() function that polls the keyboard and gives you a keystroke for you to do what you want with it.
- Swing uses a callback system.

This poses a problem as the methods are fundamentally different.

#### Solution

The solution to this was an adapter. The View interface, which both the Lanterna and Swing views implement, has a pollEvents() that extracts that extracts events from the view that are usually stored in an "event queue", these events are just an Enum created by us to make things simpler. With this, all we need to do is have the different views populate this queue in the way they want:

- The Lanterna view will have an independent thread that continously reads from te keyboard and send the appropriate event to the queue.
- The Swing view has callbacks that, when the specified key is pressed, will send the events to the queue.

### Contextual Event Handling

#### Problem

As we described above the Application class is the one who governs the execution of the program, and we wanted each MVC to determine its own behaviour in handling events. Initially we had the controllers read the events directly but this posed a problem as we needed the Application to be aware of some events in order to be able to switch the MVCs, we could have the controller report back that such events happened but it seemed quite inefficient to have messages go back and forth like this. So we couldn't have the controllers handle the events.

#### Solution

The solution to this came with implementing an abstract event handler that the Application sends the events to and each MVC would hold a concrete implementation of said event handler.
So, when the application switches the MVC, it also switches the handler. This becomes quite efficient, as in each concrete handler we only need to treat the events that are relevant to the respective MVC.

## Known Code Smells and Refactoring Suggestions

<!--
> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.
-->

### Switch Statements

In the Lanterna input thread and in the Swing keyboard callback, we use switch statements to check which event to send to the queue.

#### Solution

A solution to this is creating map between the keys and the event.

### Duplicate Code

Present in the combo checker

#### Solution

Create a function that generalizes the behaviour of the repetead code.

### Lazy class - null object

The class Useless Controller is as the name says: useless. It only exists to act like a dummy. As some of the screens don't really have or need a behaviour as there is no Model to manipulate.

#### Solution

This isn't really a solvable problem as this is an implementation of the null object pattern. Because every MVC needs to have a controller even if it won't be messing with any data.

### Data class

The Selector class is only holding a Position.

#### Solution

The selector class could easily be merged in the board class but this would make the Board be responsible for more than one thing.

## Testing Results

<!--
> This section should contain screenshots of the main results of both the test coverage and mutation testing reports. It should also contain links to those reports in HTML format (you can copy the reports to the docs folder).
-->
!["Test Coverage"](!["Original GUI"](http://aluminumangel.org/attack/screen_shot_0.jpg))

## Things added after presetation

- Tests
- Title Screen
- "You Lost" Screen
- State Patern
- Refactor all around and better package organization (now the Command design pattern is actually useful)

## UML
It's inside the `uml/`  directory. Due to its size it's not included in the report.


## Self-evaluation

<!--
> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.
>-->

We both worked equally on the project.
Moisés was responsible for the view part of the program and Paulo for the model. The controller part (game logic and such), and the documentation were worked on equally by both.
