/*	
	迭代函数对收敛性的影响
	实验题1 不动点迭代法求根
	方案2    x = 2*(x^3) - 1
	初值 x0 = 0  迭代10次
*/

import java.util.Scanner;
import java.text.NumberFormat;

class fx1_2{
	
	public void root(double x0,int n,double esp){
		
		//x0:迭代初值  n:迭代次数  esp:精度
		int s = (esp + "").length() - 2;	//输出结果的有效位数
		double x1 = x0,x2 = x0;	//保存相邻两次的迭代值
		System.out.println("k\tx(k) ");
		System.out.println("0\t" + x0);
		for(int i = 1; i <= n; i++){
			
			x2 = fx(x1);  //迭代过程
			System.out.println(i + "\t" + x2);
			if(Math.abs(x2 - x1) < esp){
				NumberFormat d = NumberFormat.getNumberInstance();
				//d.setMaximumFractionDigits(s);
				d.setMinimumFractionDigits(s);//设置输出小数位数
				System.out.println("该非线性方程根为:" + d.format(x2) +", 第" + i +"次迭代");
				return;
			}
			x1 = x2;
		}
		System.out.println("该方程迭代法求根失败！");
	}
	
	public double fx(double x){	//fx: 迭代函数
		return 2*x*x*x-1;
	}
}
class test{
	public static void main(String[] args){
		int n;
		double x0,esp;
		fx1_2 f = new fx1_2();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*****************************不动点迭代法*******************************\n");
		System.out.print(" 请输入初值:");
		x0 = scanner.nextDouble();
		System.out.print(" 请输入迭代次数:");
		n = scanner.nextInt();
		System.out.print(" 请输入精度:");
		esp = scanner.nextDouble();	
		f.root(x0,n,esp);
	}
}