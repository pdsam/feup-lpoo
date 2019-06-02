
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
 - ### Combo Detector
 Detects combos (lines of at least 3 cubes with the same colour) and breaks them.
 - ### Physics Processor
 Detects if the blocks need to fall into their places aka. gravity.
 #- ## "You Lost" and detection
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

### Large Amount of possible commands
#### Problem
The controller will need to interact with the model diferently depending on the input from the user. Therefore having a large amount of possible commands.
#### Solution
 We opted to use the Command Design Pattern. This allows us to: structure a system around high-level operations built on primitive operations by having a Command interface and making the commands implement it.

### Comunication from the board(model) to the controller
#### Problem
We faced a problem where the controller needed to communicate with the board to know what changed so it could process the physics  and check for combos. 
#### Solution:
The observer design pattern came in handy for this as we made the controller observe the board. As a result, the communication between the board and the controller was made in a much simpler way. 

### Adapter
#### Problem

#### Solution

### State
#### Problem

#### Solution

### Swing based view
#### Problem
We had to add a view based on swing, maintining the rest  of code identical. 
#### Solution
Therefore we used the abstract factory design pattern, having an interface as the view. Thus, making the switching between lanterna and Swing much easier.

## Known Code Smells and Refactoring Suggestions

<!--
> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.
-->
A few code smells we can already point out are:

0. The BoardController Class should not be reading input, this should be a responsibility for the view. As we are intending to give the option of using either Swing or Lanterna. Both frameworks have different ways of handling input, so input handling is better suited to be handled by the view that will then send the appropriate messages to the controller. If we choose to keep the input handling in the controller, then the best option can be to create an abstract input handler class that can then be specialized into each of the frameworks, but this can cause the problem of the view not matching the respectie input handler that is being used, other than the fact that it introduces the Parallel Inheritance Hierarchies code smell.
### Switch Statements
#### Solution
### Parallel Inheritance Hierarchies
#### Solution
### Duplicate Code
#### Solution
### Lazy class - null object
The class Useless Controller is as the name says: useless. It only exists to act like a dummy.
#### Solution

### Data class
Position Selector
#### Solution
### Controller Middle Man
#### Solution


## Testing Results

<!--
> This section should contain screenshots of the main results of both the test coverage and mutation testing reports. It should also contain links to those reports in HTML format (you can copy the reports to the docs folder).
-->
Right now no tests have been made to the code as it is still in a very volatile state and many changes are expected.

## Things added after presetation

 - Tests
 - Title Screen
 - "You Lost" Screen
 - State Patern
 - Refactor all around and better package organization (now the Command design pattern is actually useful)

## Self-evaluation
<!--
> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.
>-->

We both worked equally on the project.
Moisés was responsible for the view part of the program and Paulo for the model. The controller part(game logic and such), and the documentation were worked on equally by both.
