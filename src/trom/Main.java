package trom;
//import java.io.*;
import javax.swing.*;
//import java.util.*;

/*
 * Main class: Initializes Menu object and creates a main window.
 */

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Menu initialize = new Menu();
		
		JFrame frame = new JFrame();
		frame.setTitle("TROM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1000,800);
		frame.setVisible(true);
	}
}
