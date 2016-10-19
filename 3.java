/*
	求方程f(x) = x^3 - sinx - 12x + 1的全部实根,esp = 10^(-6)
	方案1.用牛顿法求解
	方案2.用简单迭代法
	方案3.用埃特金迭代加速法
	取相同迭代法初值，比较各方法的收敛速度
*/

import java.util.Scanner;
import java.text.NumberFormat;

class fx3_1{	//牛顿迭代法
	
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
		return x*x*x - Math.sin(x) - 12*x + 1;
	}
	
	public double f_x(double x){//f_x：fx（迭代函数）的导数
		return 3*x*x - Math.cos(x) - 12;
	}
}

class fx3_2{	//简单迭代法
	
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
				d.setMinimumFractionDigits(s);//设置输出小数位数
				System.out.println("该非线性方程根为:" + d.format(x2) +", 第" + i +"次迭代");
				return;
			}
			x1 = x2;
		}
		System.out.println("该方程迭代法求根失败！");
	}
	
	public double fx(double x){	//fx: 迭代函数
		return x*x*x - Math.sin(x) - 12*x + 1;
	}
}

/*
  Aitken迭代加速
  迭代:		x(k+1) = g(x(k));
  再迭代:   x(k+2) = g(x(k+1));
  加速:		^x = x(k+2)-[(x(k+2)-x(k+1))^2]/[x(k+2)-2*x(k+1)+x(k)]
*/

class fx3_3{	//埃特金迭代加速法   
	
	public void root(double x0,int n,double esp){
		//x0:迭代初值  n:迭代次数  esp:精度
		int s = (esp + "").length() - 2;	//输出结果的有效位数
		double x1 = x0,x2 = x0,x3,x4;
		System.out.println("k\tx(k)");
		System.out.println("0\t" + x0);
		for(int i = 1; i <= n; i++){
			//迭代过程
			x2 = fx(x1);
			x3 = fx(x2);
			x4 = x3 - Math.pow((x3-x2),2)/(x3-2*x2+x1);
			System.out.println(i + "\t" + x4); 
			if(Math.abs(x4 - x1) < esp){
				NumberFormat d = NumberFormat.getNumberInstance();
				d.setMinimumFractionDigits(s);//设置输出小数位数
				System.out.println("该非线性方程根为:" + d.format(x4) +", 第" + i +"次迭代");
				return;
			}
			x1 = x4;
		}
		System.out.println("该方程迭代法求根失败！");
	}
	
	public double fx(double x){	//fx: 迭代函数
		return x*x*x - Math.sin(x) - 12*x + 1;
	}
}

class test{
	public static void main(String[] args){
		double x0,esp;
		int n;
		fx3_1 f1 = new fx3_1();
		fx3_2 f2 = new fx3_2();
		fx3_3 f3 = new fx3_3();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*****************************迭代法求根*******************************\n");
		System.out.print(" 请输入初值:");
		x0 = scanner.nextDouble();
		System.out.print(" 请输入迭代次数:");
		n = scanner.nextInt();
		System.out.print(" 请输入精度:");
		esp = scanner.nextDouble();	
		
		System.out.println("\n方案一：用牛顿法求解");
		f1.root(x0,n,esp);
		
		System.out.println("\n方案2：用简单迭代法求解");
		f2.root(x0,n,esp);
		
		System.out.println("\n方案3.用埃特金迭代加速法");
		f3.root(x0,n,esp);
	}
}