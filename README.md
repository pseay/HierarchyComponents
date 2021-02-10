HierarchyComponents
---
- This is a project to make initializing and traversing a GUI much simpler. The goal is to shorten multiple lines of code and make the hierarchy easier to navigate.
---
There are many differences before and after Hierarchy Components:

Notably:
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

        button2.addActionListener((e) -> {
            System.out.println("Thanks for clicking me!");
        });        

        panel.add(button1);
        panel.add(button2);
        frame.add(panel);
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
                named("button2", new HButton("No, click me!", (e)-> {
                    System.out.println("Thanks for clicking me!");
                }))
            ))
        );
    }
}
```
