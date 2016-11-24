/*
	将区间[-5,5] 10等分，有函数
	(1)y = 5/(1 + x^2)  (2)y = arctan x  (3)y = x/(1 + x^4)
	分别对上述函数计算点X(k)上的值，做出插值函数的图形并与y=f(x)的图形比较。
	(2)做牛顿插值
*/
import java.util.Scanner;

class Newton{
	private int N;//插值次数
	private double a,b;//插值区间左右端点a,b
	private double X[],Y[];//定义节点
	
	public Newton(){
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
		setNode();
		getNode();
		System.out.println("\n\n近似值L("+xk+") = "+NewtonFun(xk));
		System.out.println("精确值f("+xk+") = "+function(xk));
	}
	
	public double NewtonFun(double x){//牛顿插值函数
		int i,j,k;
		double s = Y[0];
		double d = 1;
		double df[][] = new double[N+1][N+2];//保存插商
		for(i = 0; i <= N; i++){
			df[i][0] = X[i];//填入X的值
			df[i][1] = Y[i];//计算0阶插商
		}
		//for(j = 1; j < N+1; j++)
			//for(k = j + 1; k < N+2; k++)
		for(k = 2; k < N + 2; k++)
			for(j = k - 1; j < N + 1; j++)
				df[j][k] = (df[j][k-1] - df[j-1][k-1])/(df[k-1][0] - df[0][0]);//计算1~N阶插商
		
		//输出1~N阶插商
		System.out.printf("   x\t    f(x) ");
		for(i = 1; i <= N; i++)
			System.out.print("\t   "+i+"阶");
		System.out.println();
		for(i = 0; i <= N; i++){
			for(j = 0; j <= i+1;j++)
				System.out.print(String.format("%10.5f",df[i][j]));
			System.out.println();
		}
		
		//计算牛顿插值
		for(i = 1; i <= N; i++){
			d *= (x - X[i-1]);
			s += df[i][i+1]*d;
		}
		return s;
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
		System.out.println("\n");
	}
	
	public double function(double x){//函数f(x)
		return 5.0/(1 + x*x);//函数(1)
		//return Math.atan(x); //函数2
		//return x/(1 + x*x*x*x); //函数3
	}
}
class NewtonTest{
	public static void main(String[] args){
		System.out.println("************************牛顿插值*************************");
		Newton n = new Newton();
		n.solve();
	}
}