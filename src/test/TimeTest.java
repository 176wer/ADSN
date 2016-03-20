/**
 * Project Name:ADSN
 * File Name:TimeTest.java
 * Package Name:test
 * Date:2016年1月5日上午9:02:02
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;
/**
 * ClassName:TimeTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月5日 上午9:02:02 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
/**
 * Project Name:ADSN
 * File Name:da.java
 * Package Name:test
 * Date:2016年1月3日下午8:43:24
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

 

/**
 * ClassName:da <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月3日 下午8:43:24 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.RelativeDateFormat;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TimeTest extends ApplicationFrame {
	static TimeSeries timeseries = new TimeSeries("Heart Rate", org.jfree.data.time.Second.class);
	static TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	JPanel jpanel = createDemoPanel();
	public TimeTest(String s) {
		super(s);
		
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset) {
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Exercise Chart", "Elapsed Time", "Beats Per Minute",
				xydataset, true, true, false);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		 
	     
		ValueAxis valueaxis = xyplot.getDomainAxis();       
        //自动设置数据轴数据范围  
        valueaxis.setAutoRange(true);   
         
		valueaxis.setFixedAutoRange(1*60*1000D);  //数据轴固定数据范围 30s   
	        ValueAxis rangeAxis = xyplot.getRangeAxis();  
	        
	        rangeAxis.setRange(0.0D,500D);//设置Y轴的取值范围double		
	   
	 
		timeseriescollection.addSeries(timeseries);
		return jfreechart;
	}

	/**  
	* @Title: createDataset  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @return XYDataset    返回类型  
	* @throws  
	*/
	private static XYDataset createDataset() {
		
		timeseries.add(new Second(45, 6, 9, 1, 10, 2006), 143D);
		timeseries.add(new Second(33, 7, 9, 1, 10, 2006), 167D);
		timeseries.add(new Second(35, 7, 9, 1, 10, 2006), 189D);
		timeseries.add(new Second(40, 7, 9, 1, 10, 2006), 156D);
		timeseries.add(new Second(45, 7, 9, 1, 10, 2006), 176D);
		timeseries.add(new Second(58, 7, 9, 1, 10, 2006), 183D);
		timeseries.add(new Second(4, 8, 9, 1, 10, 2006), 138D);
		timeseries.add(new Second(11, 8, 9, 1, 10, 2006), 102D);
		
		
		return timeseriescollection;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[]) {
		TimeTest relativedateformatdemo1 = new TimeTest(
				"JFreeChart: RelativeDateFormatDemo1.java");
		relativedateformatdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(relativedateformatdemo1);
		relativedateformatdemo1.setVisible(true);
	}
}

