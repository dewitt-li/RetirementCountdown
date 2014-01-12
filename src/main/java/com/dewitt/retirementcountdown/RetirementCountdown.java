package com.dewitt.retirementcountdown;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;


public class RetirementCountdown extends JDialog{
	private int left=0;
	private int top=0;
	private final JLabel text=new JLabel();
	private final Date endDate;
	/**
	 * @param args
	 * @throws ParseException 
	 */
	
	public RetirementCountdown() throws ParseException{
		endDate = new SimpleDateFormat("MM/dd/yy").parse("11/02/2042");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setOpacity(0.5f);
		this.setBackground(Color.red);
		this.setLocation(left, top);
		setLabel();
		text.setFont(new Font("Arial", Font.BOLD, 20));
		text.setForeground(Color.red);
		this.add( text);
		
		this.pack();
		RetirementCountdown.this.relocate();
		this.setVisible( true );
		this.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
				RetirementCountdown.this.relocate();
			}

			public void mouseExited(MouseEvent arg0) {
				RetirementCountdown.this.relocate();
			}

			public void mousePressed(MouseEvent arg0) {	
				RetirementCountdown.this.relocate();
			}

			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		this.addMouseMotionListener(new MouseMotionListener(){

			public void mouseDragged(MouseEvent arg0) {
				RetirementCountdown.this.relocate();
				
			}

			public void mouseMoved(MouseEvent arg0) {
				RetirementCountdown.this.relocate();
				
			}
			
		});
		 new Thread(new Runnable(){
			public void run() {
				while(true){
					
					try {
						RetirementCountdown.this.setLabel();
						Thread.sleep(1000);
					} catch (Throwable e) {
					}
				}	
			}
		 }).start();
	}
	public void setLabel(){
		Calendar now = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		now.setTime(new Date());
		calendar.setTime(new Date());
		int seconds = 60-calendar.get(Calendar.SECOND);
		int minutes = 59-calendar.get(Calendar.MINUTE);
		int hours = 23 - calendar.get(Calendar.HOUR_OF_DAY);
		calendar.clear();
		calendar.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
		long days =(endDate.getTime()-calendar.getTimeInMillis())/(1000 * 60 * 60 * 24);
		text.setText(String.format("%d Days %02d : %02d : %02d",days,hours,minutes,seconds));
		this.relocate();
	}
	public void relocate(){
		this.setAlwaysOnTop(true);
		int mouseX=MouseInfo.getPointerInfo().getLocation().x;
		int mouseY=MouseInfo.getPointerInfo().getLocation().y;
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int x=0;
		int y=0;
		if(mouseY>this.getHeight() || mouseX<=(screenWidth-this.getWidth()-200)){
			x=screenWidth-this.getWidth()-200;
		}else{
			x=mouseX-this.getWidth()-10;
		}
		this.setLocation(x, y);
		this.setModal(true);
		this.setModal(false);
	}
	public static void main(String[] args) throws ParseException {
		new RetirementCountdown();
	}

}
