
# LPOO_71 Crack Attack

> The project goal is to recreate the game [Crack attack](http://www.aluminumangel.org/attack/). The game's goal is to not let the blocks get to the top(like tetris) by making lines with at least 3 colored blocks with the same colour to pop them thus increasing the score.

<!--
> Include here one or two paragraphs explaining the main idea of the project, followed by a sentence identifying who the authors are. 
-->

## Implemented Features

<!--
> This section should contain a list of implemented features and their descriptions. In the end of the section, include two or three screenshots that illustrate the most important features.
-->
Not much of the way of stand alone features have been implmented yet, in other words, the code we have written consists only in the base structure of the MVC architectural pattern, having the model fully implmented, the controller partially, and the view part is not segregated from the others.

## Planned Features

<!--
> This section is similar to the previous one but should list the features that are not yet implemented. Instead of screenshots you should include GUI mock-ups for the planned features.

-->
![alt text](http://aluminumangel.org/attack/screen_shot_0.jpg "Original GUI")

    1. Abstract Class enabling to switch the view to be implemented both in lanterna and swing.
    2. Implement the time based element in the game (pushing new lines into the board on a time base).
    3. Implment the row checker.

## Design

<!--
> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts: "Problem in Context", "The Pattern", "Implementation" and "Consequences".
-->
Given the fact that we are doing a game, we opted to use the Model-View-Controller Architectural Pattern.
(...)

The controller will need to interact with the model diferently depending on the input from the user. Therefore having a large amount of possible commands, we Opted to use the Command Design Pattern. This allows us to: structure a system around high-level operations built on primitive operations.

Later we see we wil need to use the observer design pattern, with the intent to only check certain areas of the board for patterns, when they were updated.

## Known Code Smells and Refactoring Suggestions

<!--
> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.
-->
A few code smells we can already point out are:

    1. The BoardController Class should not be reading input, this should be a responsibility for the view. As we are intending to give the option of using either Swing or Lanterna. Both frameworks have different ways of handling input, so input handling is better suited to be handled by the view that will then send the appropriate messages to the controller. If we choose to keep the input handling in then the controller, then the best option can be to create an abstract input handler class that can then be specialized into each of the frameworks, but this can cause the problem of the view not matching the respectie input handler that is being used.

## Testing Results

<!--
> This section should contain screenshots of the main results of both the test coverage and mutation testing reports. It should also contain links to those reports in HTML format (you can copy the reports to the docs folder).
-->
Right now no tests have been made to the code as it is still in a very volatile state and many changes are expected.

## Self-evaluation

<!--
> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.
>-->
