/*
	复合求积公式计算定积分
	（1）ln2 - ln3 = -2∫(2,3)(1/(x^2-1))dx
	（2） e^2 = ∫(1,2)(xe^x)dx
	用龙贝格公式求定积分，要求绝对误差为exp = 1/2 * 10^(-7)，
	将计算结果与精确解做比较，并对计算结果进行分析

	test
		fx = -2.0/(x*x - 1)

		S(8)  = -0.405472
		S(16) =  -0.405466
		S(32) =  -0.405465
		精确值为: -0.4054651081081645
*/
import java.util.Scanner;

class Romberg{
	private double a,b;			//表示积分区间[a,b]
	private double step = 0.0;	//步长
	private double T[][] = new double[10][4];	//T表
	
	public void solve(){//求解
		Scanner scanner = new Scanner(System.in);
		System.out.println("****************************龙贝格公式****************************");
		System.out.println("请输入积分区间a,b:");
		a = scanner.nextDouble();
		b = scanner.nextDouble();
		System.out.println("请输入精度:");
		double esp = scanner.nextDouble();
		
		double T1,T2;			//存储二分前和二分后的Romberg积分值
		double xk,sum = 0.0;	//xk为每次取步长的x值,sum为补偿值
		int n = 1;				//n为结点数
		step = b - a;			//第一次二分
		int i = 1,j,k;
		T2 = step*(fx(a) + fx(b))/2.0;
		T[0][0] = T2;
		do{	//i:行数 j:列数
			sum = 0.0;  T1 = T[i-1][0];  xk = a;
			step /= 2;	//区间二分
			n *= 2;		//结点数翻倍
			for(k = 1; k < n; k += 2){//计算二分后新增结点的函数值之和
				xk = a + k*step;
				sum += fx(xk);
			}
			T2 = T1/2.0 + sum*step;//计算积分结果
			
			T[i][0] = T2;
			for(j = 1; j <= i && j < T[i].length; j++)
				T[i][j] = (Math.pow(4,j)*T[i][j-1] - T[i-1][j-1])/(Math.pow(4,j) - 1);//计算T,S,C,R
			T2 = T[i][j-1];
			T1 = T[i][j-2];
			i++;
		}while(Math.abs(T2 - T1) >= esp && i < T.length);
		
		getT();
		System.out.println("\nn = "+n+" R("+j+") = "+ T2);
		double exact = Math.log(2) - Math.log(3); 	//函数(1)的精确值
		//double exact = Math.E*Math.E;				//函数(2)的精确值
		System.out.println("精确值为: " + exact);  
		
		double exp = Math.abs(exact - T2);	//误差
		System.out.print("误差为: " + String.format("%.3E",exp));
		if(exp < esp)
			System.out.println(" < " + esp);
		else
			System.out.println(" > " + esp);
	}
	
	public void getT(){
		int i = 0,j = 0;
		for(i = 0; i < T.length; i++){
			System.out.print(""+i+"\t");
			for(j = 0; j <= i && j < T[j].length; j++){
				if(T[i][j] == 0.0) return;
				if(j == 0) System.out.print("T("+(i+1)+") = "+ String.format("%12.6f",T[i][j])+"  ");
				else if(j == 1) System.out.print("S("+i+") = "+ String.format("%12.6f",T[i][j])+"  ");
				else if(j == 2) System.out.print("C("+(i-1)+") = "+String.format("%12.6f",T[i][j])+"  ");
				else System.out.print("R("+(i-2)+") = "+ String.format("%12.6f",T[i][j])+"  ");
				
			}
			System.out.println();
			
		}
		
	}
	
	public double fx(double x){//函数f(x)
		return -2.0/(x*x - 1);			//函数(1)
		//return x*Math.pow(Math.E,x);	//函数(2)
	}
}
class RombergTest{
	public static void main(String[] args){
		Romberg r = new Romberg();
		r.solve();
	}
}