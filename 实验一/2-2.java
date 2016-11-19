/*	
	初值的选取对迭代法的影响
	实验题2 牛顿迭代法求根
	方案2   x^3-x-1=0  在x=1.5附近的根
	X(k+1) = X(k) - f(X(k))/f'(X(k))
	取x0 = 0  迭代10次
*/

import java.util.Scanner;
import java.text.NumberFormat;

class fx2_1{
	
	public void root(double x0,int n,double esp){
		//x0:迭代初值  n:迭代次数  esp:精度
		int s = (esp + "").length() - 2;	//输出结果的有效位数
		double x1 = x0,x2 = x0;	//保存相邻两次的迭代值
		System.out.println("k\tx(k) ");
		System.out.println("0\t" + x0);
		for(int i = 1; i <= n; i++){
			
			x2 = x1 - fx(x1)/f_x(x1);  //迭代过程
			System.out.println(i + "\t" + x2);
			if(Math.abs(x2 - x1) < esp){
				NumberFormat d = NumberFormat.getNumberInstance();
				d.setMinimumFractionDigits(s);
				System.out.println("该非线性方程根为:" + d.format(x2) +", 第" + i +"次迭代");
				return;
			}
			x1 = x2;
		}
		System.out.println("该方程迭代法求根失败！");
	}
	
	public double fx(double x){	//fx: 迭代函数
		return x*x*x-x-1;
	}
	
	public double f_x(double x){//f_x：fx（迭代函数）的导数
		return 3*x*x-1;
	}
}


class test{
	public static void main(String[] args){
		double x0,esp;
		int n;
		fx2_1 f = new fx2_1();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*****************************牛顿迭代法*******************************\n");
		System.out.print(" 请输入初值:");
		x0 = scanner.nextDouble();
		System.out.print(" 请输入迭代次数");
		n = scanner.nextInt();
		System.out.print(" 请输入精度:");
		esp = scanner.nextDouble();	
		f.root(x0,n,esp);
	}
}