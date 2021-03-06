package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.render.points.PointCube;

import net.minecraft.client.renderer.Tessellator;
import static org.lwjgl.opengl.GL11.*;

/**
 * Draws the circles around a cylindrical region
 * 
 * @author yetanotherx
 */
public class RenderCylinderCircles
{
	
	protected LineColour colour;
	protected double radX = 0;
	protected double radZ = 0;
	protected int minY;
	protected int maxY;
	protected double centerX;
	protected double centerZ;
	
	public RenderCylinderCircles(LineColour colour, PointCube center, double radX, double radZ, int minY, int maxY)
	{
		this.colour = colour;
		this.radX = radX;
		this.radZ = radZ;
		this.minY = minY;
		this.maxY = maxY;
		this.centerX = center.getPoint().getX() + 0.5;
		this.centerZ = center.getPoint().getZ() + 0.5;
	}
	
	public void render()
	{
		Tessellator tess = Tessellator.instance;
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			double twoPi = Math.PI * 2;
			for (int yBlock = this.minY + 1; yBlock <= this.maxY; yBlock++)
			{
				tess.startDrawing(GL_LINE_LOOP);
				tempColour.prepareColour();
				
				for (int i = 0; i <= 75; i++)
				{
					double tempTheta = i * twoPi / 75;
					double tempX = this.radX * Math.cos(tempTheta);
					double tempZ = this.radZ * Math.sin(tempTheta);
					
					tess.addVertex(this.centerX + tempX, yBlock, this.centerZ + tempZ);
				}
				tess.draw();
			}
		}
	}
}
