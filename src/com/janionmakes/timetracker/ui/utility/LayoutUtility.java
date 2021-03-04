package com.janionmakes.timetracker.ui.utility;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class LayoutUtility {
    
    public static HBox separateNodesHorizontally(Node node1, Node node2) {
       Region spacer = new Region();
       HBox.setHgrow(spacer, Priority.ALWAYS);
       HBox hbox = new HBox();
       hbox.getChildren().addAll(node1, spacer, node2);
       
       return hbox;
    }
    
    public static VBox separateNodesVertically(Node node1, Node node2) {
       Region spacer = new Region();
       VBox.setVgrow(spacer, Priority.ALWAYS);
       VBox vbox = new VBox();
       vbox.getChildren().addAll(node1, spacer, node2);
       
       return vbox;
    }

}
