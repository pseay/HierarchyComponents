# HierarchyComponents

This is a project to make initializing and traversing a GUI much simpler. The goal is to shorten multiple lines of code and make the hierarchy easier to navigate.

Benefits of hierarchy components:
* You can easily tell which components are children of which other components
* You don't have to make a component & add it to a container
  * Adding is handled automatically

Initializing a Swing form before Hierarchy Components:
```java
class Swing{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame Title");
        JPanel panel = new JPanel();
        JButton button1 = new JButton("Click me!");
        JButton button2 = new JButton("No, click me!");        
        JButton button3 = new JButton("CLICK ME!");        

        button2.addActionListener((e) ->
            System.out.println("Thanks for clicking me!")
        );        

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        frame.add(panel);
        //showing it
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
```

Initializing a Swing form after Hierarchy Components:
```java
class Hierarchy {
    public static void main(String[] args) {
        HFrame frame = new HFrame("Frame Title",
            notNamed(new HPanel(
                named("button1", new HButton("Click me!")),
                named("button2", new HButton("No, click me!", (e) -> 
                    System.out.println("Thanks for clicking me!")
                )),
                named("button3", new HButton("CLICK ME!"))
            ))
        );
        //showing it
        frame.open(WindowConstants.EXIT_ON_CLOSE, true);
    }
}
```

You may be wondering how to access the variables after you made them.
When you make a new HFrame, it automatically creates a Navigator with all the components according to their name.

Here is an example:
```java
class NavEx {
    public static void main(String[] args){
      //...code above
      Navigator nav = frame.getNavigator();
      
      //SINGLES
      //getting one component by name
      JComponent button1 = nav.getSingle("button1");
      //getting one component by name and class
      HButton button1asButton = nav.getSingleByClass("button1", HButton.class);
      
      //LISTS
      //getting a list by name
      List<JComponent> labels = nav.getList("label");
      //getting list by class
      List<JLabel> jLabels = nav.getListByClass(JLabel.class);
      //getting list by name and class
      List<JLabel> jLabelsNamedLabel = nav.getListByClass("label", JLabel.class);
    }
}
```