/**
 * Project Name:ADSN
 * File Name:we.java
 * Package Name:test
 * Date:2015��12��24������5:16:39
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;
/**
 * ClassName:we <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015��12��24�� ����5:16:39 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
 

import java.awt.Font;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class JFreeChartTest2 extends ApplicationFrame
{
    public JFreeChartTest2(String title)
    {
        super(title);
        this.setContentPane(createPanel()); //���캯�����Զ�����Java��panel���
    }
    
    public static CategoryDataset createDataset() //������״ͼ���ݼ�
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        dataset.setValue(10,"a","������Ա");
        dataset.setValue(20,"b","�г���Ա");
        dataset.setValue(40,"c","������Ա");
        dataset.setValue(15,"d","������Ա");
        Random r=new Random(25);
        int k=6;
        int c;
        for(int i=0;i<100;i++){
        	c=r.nextInt(100);
        	dataset.addValue(c, "S1", "C"+k);
        	k++;
        }
        return dataset;
    }
    
    public static JFreeChart createChart(CategoryDataset dataset) //�����ݼ�����һ��ͼ��
    {
        JFreeChart chart=ChartFactory.createBarChart("hi", "��Ա�ֲ�", 
                "��Ա����", dataset, PlotOrientation.VERTICAL, true, true, false); //����һ��JFreeChart
        chart.setTitle(new TextTitle("ĳ��˾��֯�ṹͼ",new Font("����",Font.BOLD+Font.ITALIC,20)));//�����������ñ��⣬�滻��hi������
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//���ͼ���м䲿�֣���plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//��ú�����
        categoryAxis.setLabelFont(new Font("΢���ź�",Font.BOLD,12));//���ú���������
        return chart;
    }
    
    public static JPanel createPanel()
    {
        JFreeChart chart =createChart(createDataset());
        return new ChartPanel(chart); //��chart�������Panel�����ȥ��ChartPanel���Ѽ̳�Jpanel
    }
    
    public static void main(String[] args)
    {
        JFreeChartTest2 chart=new JFreeChartTest2("ĳ��˾��֯�ṹͼ");
        chart.pack();//�Ժ��ʵĴ�С��ʾ
        chart.setVisible(true);
        
    }
}
