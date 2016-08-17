# BonSketch-Android-App

Copyright (c) | 2016 | Shuang Wu | University of Waterloo 

1.OS: OS X El capitan
2.JDK: JAVA SE 1.8
3.IDE: Android Studio Marshmallow 6.0 
The layout is designed for Nexus 7 Tablet.

Couple things that need more explanation:

1.Square Canvas:I used a square canvas because it is the best solution to handle changing orientations. I tried to make my app pretty and the right bottom is the logo for my phone app(clicking it doesn’t do anything:))

2.Tools:I actually want the users to have a default choice when they open the app. So if the user doesn’t choose their shape, line thickness and colour, it will automatically assume that user wants to draw a thickest border blue rectangle. Hold and tap on the eraser tool will clear away the entire canvas. When a specified tool, colour and thickness is chosen, or when a shape is selected, corresponding button changes background colour.

3.Handling overlapping shapes: The shapes are stored in a arraylist in the order of drawing, the later shape gets drawed, more prior it is. When I draw a line and on top of it I draw a circle, when I click the line, the circle is going to get selected.

4.Layout:it is actually a bunch of linear layout, I just change the orientation back and forth between horizontal and vertical for portrait and landscape orientations. 

5. MVC: MVC is used for this application, there are one model and 2 views: toolbar view and canvas view. 

6. Undo and Redo is implemented.The left arrow on the toolbar is undo, and right arrow on the toolbar is redo. It supports unlimited number of undos and redos (as long as it is actually an action).  


4)photo license and credit
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/keyboard-key-b_31787
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/blank-check-box_61221
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/calculation-operations-minus-sign_42977
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/circle_152492
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/circular-shape-silhouette_25444
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/cursor_99188
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/diagonal-line_109604
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/double-sided-eraser_25454
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/fill_106934
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/maths-minus-symbol_9215
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/minus-horizontal-straight-line_59549
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/navigation-arrow_12371
http://www.flaticon.com/download-icon, http://www.flaticon.com/free-icon/undo-arrow_12104

Contains detection: since there is no contains method or intersect method for shapes in android studio. I need to figure out other ways to detect if point is on a line. I used the lineIntersect method on http://stackoverflow.com/questions/20887806/does-a-line-contain-a-point as a reference for mine. Here I give credit for it.

In order to show shape selected, the shape will have a dotted line around it. I used http://stackoverflow.com/questions/6103713/how-do-i-make-a-dotted-dashed-line-in-android as my reference for drawing the dotted line.Here I give credit for it.