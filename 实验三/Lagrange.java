/*
	将区间[-5,5] 10等分，有函数
	(1)y = 5/(1 + x^2)  (2)y = arctan x  (3)y = x/(1 + x^4)
	分别对上述函数计算点X(k)上的值，做出插值函数的图形并与y=f(x)的图形比较。
	(1)做拉格朗日插值
*/
import java.util.Scanner;

class Lagrange{
	private int N;//插值次数
	private double a,b;//插值区间左右端点a,b
	private double X[],Y[];//定义节点
	
	public Lagrange(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入插值区间(a b)：");
		a = scanner.nextDouble();
		b = scanner.nextDouble();
		System.out.print("请输入插值次数：");
		N = scanner.nextInt();
		X = new double[N+1];
		Y = new double[N+1];
	}
	
	public void solve(){//求解
		Scanner s = new Scanner(System.in);
		System.out.print("请输入X(k)(范围为 "+a+" ~ "+b+" )的值：");
		double xk = s.nextDouble();
		setNode();//设置节点数据
		getNode();//输出节点信息
		System.out.println("\n\n近似值L("+xk+") = "+lagrangeFun(xk));
		System.out.println("精确值f("+xk+") = "+function(xk));
	}
	
	public double lagrangeFun(double x){//拉格朗日插值函数
		double m = 0.0;//f(x)的近似值
		for(int i = 0; i <= N; i++)
			m += lagrangeBasis(i,x)*Y[i];
		return m;
	}
	
	public double lagrangeBasis(int k,double x){//拉格朗日插值基函数
		double temp = 1;
		for(int i = 0; i <= N; i++)
			if(i != k) 
				temp *= (x - X[i])/(X[k] - X[i]);
		return temp;
	}
	
	public void setNode(){//设置各结点的值
		double temp;
		temp = (b - a)/N;
		for(int i = 0; i <= N; i++){
			X[i] = a + i*temp;
			Y[i] = function(X[i]);
		}
	}
	
	public void getNode(){//输出各结点的值
		System.out.println("输出各结点的值：\nX(i): ");
		for(int i = 0; i <= N; i++){
			System.out.printf(String.format("%10.6f",X[i]));	
		}
		System.out.println();
		System.out.println("Y(i): ");
		for(int j = 0; j <= N; j++){
			System.out.printf(String.format("%10.6f",Y[j]));	
		}
	}
	6r45
	public double function(double x){//函数f(x)
		return 5.0/(1 + x*x);//函数(1)
		//return Math.atan(x); //函数2
		//return x/(1 + x*x*x*x); //函数3
	}
}

class LagrangeTest{
	public static void main(String[] args){
		System.out.println("************************拉格朗日插值*************************");
		Lagrange l = new Lagrange();
		l.solve();
	}
}