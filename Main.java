import java.awt.Font;

class Main{
	public static void washer(){
		//read inputs for quadratic
		System.out.println("Input coefficients of quadratic (a, b, and c) where y = ax^2+ bx + c");
		System.out.print("a: ");
		double a = StdIn.readDouble();
		System.out.print("b: ");
		double b = StdIn.readDouble();
		System.out.print("c: ");
		double c = StdIn.readDouble();
		
		//read inputs for line
		System.out.println("Input slope(m) and y-intercept(b) of line where y = mx+b ");
		System.out.print("m: ");
		double m = StdIn.readDouble();
		System.out.print("y-intercept: ");
		double b2 = StdIn.readDouble();

    //check if line and quadratic intersect with discriminant
		double intersectB = b-m;
		double intersectC = c-b2;
		double discriminant = Math.pow(intersectB,2)-4*a*intersectC;
		if ((discriminant<=0)||(a==0)){
			System.out.println("The quadratic and line do not intersect at two points.");
		}

		//if the quadratic and line intersect at 2 points
		if ((discriminant > 0)&&(a!=0)){
			//x-coordinate of a point of intersection
			double x1 =Math.min((-intersectB+Math.sqrt(discriminant))/(2*a),(-intersectB-Math.sqrt(discriminant))/(2*a));
			
			double y1 = a*Math.pow(x1,2)+b*x1+c;
			
			//x-coordinate of the second point of intersection
			double x2 = Math.max((-intersectB+Math.sqrt(discriminant))/(2*a),(-intersectB-Math.sqrt(discriminant))/(2*a));
			
			double y2 = a*Math.pow(x2,2)+b*x2+c;
			double minX = Math.min(x1,x2);	//lower bound of x-interval 
			double maxX = Math.max(x1,x2);	//upper bound of x-interval
			
			//initialize largestY and smallestY
			double largestY = Math.max(y1,y2);
			double smallestY = Math.min(y1,y2);
			
			//find y-cor of the quadratic's vertex if the vertex is in the interval of intersection
			double xVertex = -b / (2*a);
			if ((xVertex <= maxX)&&(xVertex>=minX)){
				double yVertex = a*Math.pow(-b/(2*a),2)+b*-b/(2*a)+c;
				largestY = Math.max(largestY,yVertex);
				smallestY = Math.min(smallestY,yVertex);
			}
			System.out.println("\nWe are going to rotate the bounded region about a horizontal line.");
			System.out.println("Please type a number greater than "+largestY+" or less than "+smallestY);
			System.out.print("y= ");
			double axisValue = StdIn.readDouble();

      //return error if axis of rotation is in the y-interval of intersecting functions
			if ((axisValue<=largestY) && (axisValue>=smallestY)){
				throw new RuntimeException("The axis of rotation is not a number greater than "+largestY+" or less than "+smallestY);
			}

      //Cover Page
			Font font = new Font("Arial", Font.BOLD, 24);
			StdDraw.setFont(font);
			StdDraw.text(0.5,0.5,"Finding Volume with");
			StdDraw.text(0.5, 0.45, "the Washer Method");
			StdDraw.pause(2000);
			StdDraw.clear();

      //set scale of window
			StdDraw.setXscale(minX,maxX);
			StdDraw.setYscale(smallestY,largestY);
		
			StdDraw.setPenRadius(0.005); //set size of points
			
			//graph line
			StdDraw.line(x1,y1,x2,y2);
			font = new Font("Arial", Font.BOLD, 12);
			StdDraw.setFont(font);

			//graph quadratic
			for (double i = Math.min(x1,x2); i<Math.max(x1,x2); i+=(Math.max(x1,x2)-Math.min(x1,x2))/1000){
				double quadraticY = a*Math.pow(i,2)+b*i+c;
				double lineY = m*i+b2;
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(i,lineY,i,quadraticY);
			}
			StdDraw.show();	//show graphs
      
      //save image of graphs
			StdDraw.save("graph.png");
			
      StdDraw.pause(1000);
			StdDraw.clear();
			
      //set scale of window
      StdDraw.setXscale(0,1);
			StdDraw.setYscale(0,1);

      //show graphs and their equations
			StdDraw.picture(0.5,0.5,"graph.png",0.5,0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5,0.9,"Here is the region bounded by the quadratic and line you provided");
			StdDraw.pause(2000);
			StdDraw.text(0.5,0.2, "Quadratic: "+a+"x^2 + "+b+"x + "+c);
			StdDraw.pause(2000);
			StdDraw.text(0.5,0.1,"Line: "+m+"x + "+b2);
			StdDraw.pause(1000);
			StdDraw.clear();

      //show axis of rotation and graphs
			StdDraw.text(0.5, 0.9, "We are going to rotate this region about ");

      //coordinates of the center of the axis of rotation.  Adjust based on if axis is above or below the bounded region
			double centerX = 0.5;
			double centerY = 0.5;
			if (axisValue>largestY){
				centerY-=0.15;
			}
			else if (axisValue<smallestY){
				centerY+=0.05;
			}
			StdDraw.picture(centerX,centerY,"graph.png",0.4,0.4);
			StdDraw.pause(1000);
			StdDraw.setPenColor(StdDraw.RED);
			double middleY = Math.abs(a*Math.pow((maxX-minX)/2.0,2)+b*(maxX-minX)/2.0+c);
			if (axisValue>largestY){
				centerY=0.65;
			}
			else if (axisValue<smallestY){
				centerY=0.25;
			}
			StdDraw.text(0.5, 0.85,"y= "+axisValue);
			StdDraw.line(0.25,centerY,0.75,centerY);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5,centerY,"<");
			StdDraw.text(0.5075,centerY-0.01,")");
			StdDraw.pause(2000);
			StdDraw.clear();
			
			
			//find y-coordinates of the endpoints of the bounded region's reflection over axis of rotation
			double rY1 = 2*axisValue-y1;
			double rY2 = 2*axisValue-y2;

      //adjust x-scale of window showing 3-dimensional view of volume
			double addToMinX = 0;
			double addToMaxX = 0;
			if ((maxX-minX)/Math.abs(y1-rY1)<0.15*(maxX-minX)){
				addToMinX = -(maxX-minX)/(Math.abs(y1-rY1));
			}
			
			if ((maxX-minX)/Math.abs(y2-rY2)<0.15*(maxX-minX)){
				addToMaxX = (maxX-minX)/(Math.abs(y2-rY2));
			}
			StdDraw.setXscale(minX+addToMinX,maxX+addToMaxX);
      //if the reflection is above the bounded region 
			if (rY1>largestY){
        //if the quadratic opens up
				if (a>0){
          //if the vertex is in the interval of intersection
					if ((xVertex <= maxX)&&(xVertex>=minX)){
						largestY = Math.max(largestY,-a*Math.pow(-b/(2*a),2)-b*-b/(2*a)-c+2*axisValue);
						StdDraw.setYscale(smallestY,largestY);
					}
				}
        //if the quadratic opens down
				else if (a<0){
					StdDraw.setYscale(smallestY,Math.max(rY1,rY2));
				}
			}
     //if the reflection is below the bounded region 
			else if (rY1<smallestY){
        //if the quadratic opens down
				if (a<0){
          //if the vertex is in the interval of intersection
					if ((xVertex <= maxX)&&(xVertex>=minX)){
						smallestY = Math.min(smallestY,-a*Math.pow(-b/(2*a),2)-b*-b/(2*a)-c+2*axisValue);
						StdDraw.setYscale(smallestY,largestY);
					}
				}
        //if the quadratic opens up
				else if (a>0){
					StdDraw.setYscale(Math.min(rY1,rY2),largestY);
				}
			}

      //draw axis of rotation
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(minX,axisValue,maxX,axisValue);
			
      //graph quadratic
			StdDraw.setPenColor(StdDraw.BLUE);
			for (double i = Math.min(x1,x2); i<Math.max(x1,x2); i+=(Math.max(x1,x2)-Math.min(x1,x2))/1000){
				double quadraticY = a*Math.pow(i,2)+b*i+c;
				double lineY = m*i+b2;
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(i,lineY,i,quadraticY);
			}

			//graph line
			StdDraw.line(x1,y1,x2,y2);

      //make volume look 3-dimensional
			if ((maxX-minX)/Math.abs(y1-rY1)<0.15*(maxX-minX)){
				StdDraw.ellipse(minX,axisValue,(maxX-minX)/(Math.abs(y1-rY1)),Math.abs(y1-rY1)/2.0);
			}
			else {
				StdDraw.line(minX,y1,minX,rY1);
			}
			if ((maxX-minX)/Math.abs(y2-rY2)<0.15*(maxX-minX)){
				StdDraw.ellipse(maxX,axisValue,(maxX-minX)/(Math.abs(y2-rY2)),Math.abs(y2-rY2)/2.0);
			}
			else {
				StdDraw.line(maxX,y2,maxX,rY2);
			}
      //graph quadratic reflected over the axis of rotation
			StdDraw.setPenColor(StdDraw.BLUE);
			for (double i = Math.min(x1,x2); i<Math.max(x1,x2); i+=(Math.max(x1,x2)-Math.min(x1,x2))/1000){
				double quadraticY = -a*Math.pow(i,2)-b*i-c+2*axisValue;
				double lineY = -m*i-b2+2*axisValue;
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(i,lineY,i,quadraticY);
			}

			//graph line reflected over the axis of rotation
			StdDraw.line(x1,2*axisValue-y1,x2,2*axisValue-y2);
		
      //calculate radius of washer
			double radius = 0;
			if (((rY1<y1)&&(a<0))||((rY1>y1)&&(a>0))){
				radius=Math.abs((rY1+rY2)/2.0-(y1+y2)/2.0)/2.0;
			}
			else {
				radius=Math.abs(a*Math.pow((minX+maxX)/2.0,2)+b*((minX+maxX)/2.0)+c-axisValue);
			}

			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.setPenRadius(0.01);
			StdDraw.line((maxX+minX)/2.0,axisValue,(maxX+minX)/2.0,axisValue+radius);

			if (((rY1<y1)&&(a<0))||((rY1>y1)&&(a>0))){
				radius=Math.abs(a*Math.pow((minX+maxX)/2.0,2)+b*((minX+maxX)/2.0)+c-axisValue);
			}
			else {
				radius=Math.abs((rY1+rY2)/2.0-(y1+y2)/2.0)/2.0;
			}
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.line((maxX+minX)/2.0,axisValue,(maxX+minX)/2.0,axisValue-radius);
			StdDraw.save("volume.png");
			StdDraw.pause(500);
			StdDraw.clear();
			StdDraw.setXscale(0,1);
			StdDraw.setYscale(0,1);
			StdDraw.picture(0.25,0.5,"graph.png",0.35,0.35);
			StdDraw.picture(0.75,0.5,"volume.png",0.35,0.35);
			StdDraw.setPenRadius(0.005);
			StdDraw.setPenColor(StdDraw.BLACK);

      //explain parts of formulas: radius, area of the washer's face, volume of a washer
			StdDraw.text(0.5,0.9,"For a value ");
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.6,0.9,"x");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.6,0.9,"    , ");
			StdDraw.pause(1000);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(0.25,0.25,0.25,0.75);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.pause(1000);
			StdDraw.text(0.25,0.85,"there is a washer with an");
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.text(0.45,0.85,"                 inner radius");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.625,0.85,"   and");
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.text(0.75,0.85,"  outer radius"); 
			StdDraw.pause(3500);			
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(0.2,0.2,"Area of Washer's Face: ");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.65,0.2,"\u03C0*(outer radius)^2 - \u03C0*(inner radius)^2");
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(0.2,0.15,"Volume of a Washer: ");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.6,0.15,"Area of Washer's Face * depth dx");
			StdDraw.pause(3500);
			
			StdDraw.clear();
			StdDraw.picture(0.5,0.75,"volume.png",0.4,0.4);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.text(0.15,0.5,"Inner Radius: ");
			String innerRadius = "";
			String innerRadiusFormula = "";
			String outerRadius = "";
			String outerRadiusFormula = "";
			if (((rY1>y1)&&(a>0))||((rY1<y1)&&(a<0))){
				innerRadius = "Distance between the line and axis of rotation";
				innerRadiusFormula = axisValue+" - ("+m+"x + "+b2+")";
				outerRadius = "Distance between the quadratic and axis of rotation";
				outerRadiusFormula = axisValue+" - ("+a+"x^2 + "+b+"x + "+c+")";
			}
			else {
				innerRadius = "Distance between the quadratic and axis of rotation";
				innerRadiusFormula = axisValue+" - ("+a+"x^2 + "+b+"x + "+c+")";
				outerRadius = "Distance between the line and axis of rotation";
				outerRadiusFormula = axisValue+" - ("+m+"x + "+b2+")";
			}

      //\u03C0 is the math pi symbol
			String area = "\u03C0*(outer radius)^2 - \u03C0*(inner radius)^2";
			String areaFormula  = "\u03C0 * "+"("+outerRadiusFormula+")^2 - \u03C0 * "+"("+innerRadiusFormula+")^2";
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.6,0.5,innerRadius);
			StdDraw.pause(3500);
			StdDraw.text(0.5,0.45,"|"+innerRadiusFormula+"|");
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.text(0.125,0.4,"Outer Radius: ");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.6,0.4,outerRadius);
			StdDraw.pause(3500);
			StdDraw.text(0.5,0.35,"|"+outerRadiusFormula+"|");
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(0.2,0.3,"Area of Washer's Face: ");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.65,0.3,area);
			StdDraw.pause(3500);
			StdDraw.text(0.5,0.25,areaFormula);
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.2,0.2,"Volume of a Washer: ");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.6,0.2,"Area of Washer's Face * depth dx");
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5,0.15,areaFormula+" dx");
			StdDraw.pause(4500);
			StdDraw.clear();
			StdDraw.picture(0.5,0.75,"volume.png",0.4,0.4);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.25,0.5,"Volume of Entire Figure: ");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.6,0.5,"Sum of washer volume");
			StdDraw.text(0.5,0.45,"for each x-value between the left and right points of intersection"); 
			StdDraw.pause(5000);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(0.2,0.4,"Points of Intersection: ");
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.6,0.4,"("+(Math.round(x1*Math.pow(10,3))/Math.pow(10,3))+", "+(Math.round(y1*Math.pow(10,3))/Math.pow(10,3))+") and "+"("+(Math.round(x2*Math.pow(10,3))/Math.pow(10,3))+", "+(Math.round(y2*Math.pow(10,3))/Math.pow(10,3))+")");
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text (0.25,0.35,"Thus, the volume is ");
			font = new Font("Arial", Font.BOLD, 28);
			StdDraw.setFont(font);
			StdDraw.pause(3500);
			StdDraw.setPenColor(StdDraw.BLACK);

      //\u222B is the integral symbol
			StdDraw.text(0.1,0.25,"\u222B");
			font = new Font("Arial", Font.BOLD, 10);
			StdDraw.setFont(font);
			StdDraw.pause(1000);
			StdDraw.text(0.1,0.3,""+(Math.round(maxX*Math.pow(10,3))/Math.pow(10,3)));
			StdDraw.text(0.1,0.2,""+(Math.round(minX*Math.pow(10,3))/Math.pow(10,3)));
			StdDraw.pause(1000);
			StdDraw.text(0.5,0.25,areaFormula+" dx");
		}	
	}
	
	public static void main(String [] args){
		washer();
	}
}

