/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatroomPackage;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import static java.awt.Color.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.*;

/**
 *
 * @author Ryan1
 */
public class JPanelGradient extends JPanel
{ 
    public void paintComponent(Graphics g)
    {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            Color color1 = new Color(52, 143, 80);
            Color color2 = new Color(86,180, 211);
            GradientPaint gp = new GradientPaint (0,0,color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
    }
} 
