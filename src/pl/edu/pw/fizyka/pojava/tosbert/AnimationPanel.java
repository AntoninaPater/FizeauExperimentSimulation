package pl.edu.pw.fizyka.pojava.tosbert;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class AnimationPanel extends JPanel { // Hubert Nowakowski

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ArrayList<Rectangle> wheelTeeth;
    Rectangle wheel;
    Rectangle lightSource;
    Rectangle lightReciever;

    ArrayList<Rectangle> lightBeam;
    Rectangle lightBeamBeforeWheel; //TODO temporary
    Rectangle lightBeamAfterWheel;
    Rectangle lightBeamBack;
    Rectangle lightBeamToReciever;

    Rectangle fullMirror;
    Rectangle partialMirror;

    AnimationPanel(){
	super();
	setMinimumSize(new Dimension(800,650));
	setMaximumSize(new Dimension(800,650));
	setPreferredSize(new Dimension(800,650));
	setBackground(Color.WHITE);

	this.wheel = new Rectangle( 250 , 60 , 11 , 200);

	this.wheelTeeth = new ArrayList<Rectangle>();


	this.wheelTeeth.add(new Rectangle( (int)this.wheel.getX() , (int)this.wheel.getY(), 10, 30) );
	this.wheelTeeth.add(new Rectangle( (int)this.wheel.getX() , (int)this.wheel.getY()+30+20, 10, 50) );
	this.wheelTeeth.add(new Rectangle( (int)this.wheel.getX() , (int)this.wheel.getY()+30+20+50+20, 10, 50) );
	this.wheelTeeth.add(new Rectangle(
		(int)this.wheel.getX() , (int)this.wheel.getY()+30+20+50+20+50+20,10,
		(int) ( this.wheel.getMaxY()-1 - ( this.wheel.getY()+30+20+50+20+50+20 ) )
		));

	int d = 400; //TODO calculate distance from the settings
	this.fullMirror = new Rectangle( (int)(this.wheel.getX()+d) , (int)( this.wheel.getY()) ,15 , 200 );

	this.partialMirror = new Rectangle( (int)(this.wheel.getX()-50) , (int)( this.wheel.getY())-100 ,20 , 100 );

	this.lightSource = new Rectangle( 10 , ((int)( this.wheel.getMaxY() + this.wheel.getY() )/2) -15 ,40 , 30 );

	this.lightReciever = new Rectangle(
		(int)this.wheel.getX()-115,
		(int)this.wheel.getY()+250,
		30 , 45);
	
	this.lightBeamBeforeWheel = new Rectangle(
		(int)this.lightSource.getX(),
		((int)( this.lightSource.getY() + this.lightSource.getWidth()/2 -5)) -5,
		(int)( this.wheel.getX()- this.lightSource.getX() ) ,
		4 );

	this.lightBeam = new ArrayList<Rectangle>();
	
	//light beam after wheel
	for(int ii=(int)(this.wheel.getX()+this.wheel.getWidth())+100;ii<=this.fullMirror.getX();ii+=5){
	    this.lightBeam.add( new Rectangle(ii,(int)( this.wheel.getMaxY() + this.wheel.getY() )/2-5,5,4) );
	}
	//light beam back from mirror
	for(int ii=(int)(this.fullMirror.getX())-5;ii>=(int)(this.lightReciever.getX()+this.lightReciever.getWidth()/2);ii-=5){
	    this.lightBeam.add( new Rectangle(ii,(int)( this.wheel.getMaxY() + this.wheel.getY() )/2,5,4) );
	}
	
	this.lightBeamToReciever = new Rectangle(
		(int)(this.lightReciever.getX()+this.lightReciever.getWidth()/2),
		(int)(this.wheel.getMaxY() + this.wheel.getY() )/2,
		4,
		150
		);


    }

    @Override
    public void paintComponent(Graphics g ){
	Graphics2D g2d = (Graphics2D) g;

	g2d.setColor( Color.white);
	g2d.fill(new Rectangle(getWidth(),getHeight()));

	g2d.setColor(Color.GRAY);
	g2d.draw(this.wheel);
	g2d.fill(this.wheel);

	g2d.setColor(Color.LIGHT_GRAY);
	g2d.draw(this.fullMirror);
	g2d.fill(this.fullMirror);

	Path2D.Double path = new Path2D.Double();
	path.append(this.partialMirror, false);
	AffineTransform t = new AffineTransform();
	t.rotate(Math.PI / 4);
	path.transform(t);
	g2d.fill(path);

	g2d.setColor(Color.RED);
	g2d.fill(this.lightBeamBeforeWheel);

	for(Rectangle r : this.lightBeam){
	    // TODO check if beam is reflected by mirror or stopped by wheel
	    g2d.fill(r);
	}
	
	//g2d.fill(this.lightBeamBack);
	g2d.fill(this.lightBeamToReciever);


	g2d.setColor(new Color(0,59,111));
	g2d.draw(this.lightSource);
	g2d.fill(this.lightSource);

	g2d.setColor(new Color(0,59,111));
	g2d.draw(this.lightReciever);
	g2d.fill(this.lightReciever);

	g2d.setColor(Color.BLACK);
	for(Rectangle r : this.wheelTeeth){
	    // TODO move rectangle and check if left the wheel area -> adjust height
	    g2d.draw(r);
	    g2d.fill(r);
	}

    }

}