/*
	高斯顺序消元法(不选主元消元法)
	1.(2) 分别用列主元消元法与不选主元消元法求解，
		  分析对结果的影响。
			0.3E-15  59.14   3 1   59.17
			  5.291 -6.130  -1 2   46.78
			   11.2     9    5 2   1
				  1     2    1 1   2
		test:
				1 -1 1 -4  -2
				5 -4 3 12  -6
				2  1 1 11   3
				2 -1 7  1  -7
				
			    x1=1 x2=2 x3=-1 x4=0
*/

import java.util.Scanner;

class Gauss{
	static int N;				//线性方程组阶数
	private double[][] matrix;	//增广矩阵
	private double[] x;			//存放x的解(x[0]不存放元素)
	
	public void setMatrix(double[][] args){		//设置增广矩阵
		matrix = new double[N][N+1];
		x = new double[N + 1];
		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix[i].length;j++)
				matrix[i][j] = args[i][j];
	}
	public void getMatrix(){					//输出增广矩阵
		for(int i = 0; i < matrix.length; i++){		
			for(int j = 0; j < matrix[i].length;j++){
				System.out.printf(String.format(" %12.6f",matrix[i][j]));//%e
			}
			System.out.println();
		}
	}
	public void getSolve(){						//输出方程的解
		for(int i = 0; i < N - 1; i++){//
			if(calculate(i) == false){
				System.out.println("该方程组无解！");
				return;
			}
			System.out.println("第"+(i+1)+"次计算后：");
			getMatrix();
		}
		back();
		System.out.println("回代求解得：");
		for(int j = 1; j <= N; j++){
			System.out.printf("X["+j+"]="+String.format("%12.6f",x[j]) + "  ");
		}
		System.out.println();
	}
	public boolean calculate(int i){			//计算第i行为主行消元后计算结果
		for(int m = i + 1; m < N; m++){//从第i+1行开始对每一行做运算,m为行数
				
				double first = matrix[m][i];//存储作为主行的第i个元素，即r2=r2-(first/matrix[i][i])*r1
				for(int n = i; n < N + 1; n++){//n为列数
					if(matrix[i][i] < 1E-17)
							return false;
					matrix[m][n] = matrix[m][n] - matrix[i][n]*(first/matrix[i][i]);
					/*分析输出用例
					System.out.println("matrix[m][n] = " + (matrix[m][n] - matrix[i][n]*(first/matrix[i][i])));
					System.out.println(matrix[m][n] +"=" +(matrix[m][n] +"-"+ matrix[i][n]+"*"+(first+"/"+matrix[i][i])));
					System.out.println("**************\n" + "("+m+ "," +n+ ")");
					getMatrix();
					System.out.println("**************\n");
					*/
				}	
			}
		return true;
	}
	public void back(){							//回代求解方程组
		x[N] = matrix[N-1][N]/matrix[N-1][N-1];	//第一步回代，求出X(n)的解
		
		for(int i = N-2; i >= 0; i--){//i为行数
			double num = 0.0;						//存储系数矩阵系数乘以x(i)的和
			for(int j = i+1; j < N; j++){//j为列数
				num = num + matrix[i][j]*x[j+1];	
			}
			/*测试用例
				System.out.println("num = "+num);
			*/
			x[i+1] = (matrix[i][N] - num)/matrix[i][i];
			/*测试用例
				System.out.println("-----------x["+(i+1)+"]--"+x[i+1]+"---------------");
			*/
		}
	}
}

class GaussTest{
	public static void main(String[] args){
		Gauss g = new Gauss();
		Scanner scanner = new Scanner(System.in);
		System.out.println("***************************** 高斯顺序消元法 **********************************\n");
		System.out.println("请输入线性方程组的阶数:");
		Gauss.N = scanner.nextInt();							//设置方程组阶数
		System.out.println("请输入线性方程组对应的增广矩阵:");
		double[][] m = new double[Gauss.N][Gauss.N+1];				//设置临时矩阵获取输入值
		for(int i = 0; i < m.length; i++)
			for(int j = 0; j < m[i].length;j++)
				m[i][j] = scanner.nextDouble();
		g.setMatrix(m);
		g.getSolve();
	}
}