/*
	复合求积公式计算定积分
	（1）ln2 - ln3 = -2∫(2,3)(1/(x^2-1))dx
	（2） e^2 = ∫(1,2)(xe^x)dx
	用复合Simpson公式求定积分，要求绝对误差为exp = 1/2 * 10^(-7)，
	将计算结果与精确解做比较，并对计算结果进行分析

	test
		fx = -2.0/(x*x - 1)

		S(8)  = -0.405472
		S(16) =  -0.405466
		S(32) =  -0.405465
		精确值为: -0.4054651081081645
*/
import java.util.Scanner;

class Simpson{
	private double a,b;			//表示积分区间[a,b]
	private double step = 0.0;	//步长
	private double S = 0.0;		//复合Simpson积分计算结果
	public void solve(){		//求解
		Scanner scanner = new Scanner(System.in);
		System.out.println("****************************复合Simpson公式****************************");
		System.out.println("请输入积分区间a,b:");
		a = scanner.nextDouble();
		b = scanner.nextDouble();
		System.out.println("请输入等分的区间数:");
		int n = scanner.nextInt();
		step = (b - a)/n;
		double xk = a;
		for(int i = 1; i < n; i++){
			xk += step;
			if(i%2 == 0) S += 2*fx(xk);  //偶数结点函数值乘以2
			else S += 4*fx(xk);		     //奇数结点函数值乘以4
		}
		S = (fx(a) + S + fx(b))*step/3.0;//计算复合梯形积分结果
		System.out.println("S(" + n + ") = " + String.format("%10.6f",S));
		
		double exact = Math.log(2) - Math.log(3); //函数(1)的精确值
		//double exact = Math.E*Math.E;				//函数(2)的精确值
		System.out.println("精确值为: " + exact);  
		
		double exp = Math.abs(exact - S);	//误差
		System.out.print("误差为: " + String.format("%.3E",exp));
		if(exp < 0.5E-7)
			System.out.println(" < 0.5E-7");
		else
			System.out.println(" > 0.5E-7");
	}
	public double fx(double x){	//函数f(x)
		return -2.0/(x*x - 1);			//函数(1)
		//return x*Math.pow(Math.E,x);	//函数(2)
	}
}

class SimpsonTest{
	public static void main(String[] args){
		Simpson s = new Simpson();
		s.solve();
	}
}