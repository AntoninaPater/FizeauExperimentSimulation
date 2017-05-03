package pl.edu.pw.fizyka.pojava.tosbert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RunButtonListener implements ActionListener{
    Frame frame;

    public RunButtonListener(Frame frame) {
	this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(this.frame.animation.animationRunning) {
	    this.frame.animation.animationRunning = false;
	    System.out.println("Animation stopped.");
	    this.frame.animation.stopAnimation();
	}
	else{
	    this.frame.animation.animationRunning = true;
	    System.out.println("Animation started.");
	    this.frame.animation.startAnimation();
	}
    }

}