# Interactive-Swing-Canvas

Program Description

This program is an interactive Swing application that combines a graphical user interface, custom 2D drawings, and multiple event listeners to create a simple interactive scene. The main window uses a JFrame that contains several Swing components (such as buttons, labels, and a control panel), along with a custom JPanel that serves as a drawing canvas. The canvas displays 2D shapes that change based on user actions.

The application satisfies all three required technical areas:

1. Swing Components

The interface uses at least three meaningful Swing components (ex: JButton, JLabel, JRadioButton, etc.). These components allow the user to trigger actions such as changing colors, toggling modes, or resetting the drawing. Each component meaningfully affects what is drawn in the program.

2. 2D Graphics on a JPanel

A custom JPanel overrides paintComponent(Graphics g) to draw shapes such as circles, rectangles, lines, or other custom figures. These shapes update dynamically in response to user input.
The canvas acts as the main visual element of the program.

3. Event Listeners

The application includes:
	•	At least one ActionListener attached to a Swing component, which causes a visible change on the drawing panel (e.g., changing shape color, spawning a new shape, switching modes, etc.).
	•	At least one Mouse or Keyboard Listener, which also causes a meaningful graphical update (ex: clicking to draw something new, moving the mouse to animate a reaction, or pressing keys to modify the scene).

Both listeners directly affect the 2D graphics, making the program interactive.

How to Use the Program
	•	Click the GUI buttons to trigger changes on the canvas (details depend on your program’s theme).
	•	Move the mouse or click inside the drawing area to trigger mouse-based interactions.
	•	Press assigned keyboard keys (if included) to modify the display.
	•	The main method simply creates the window and initializes the program; all logic is handled inside your program classes.
