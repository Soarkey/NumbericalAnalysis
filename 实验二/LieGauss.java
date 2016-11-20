/*
	Gaussian column principal component elimination method
	高斯列主元消元法
	1.(1) 用高斯用列主元消元法求解下面的方程组
			1 -1 1 -4  2
			5 -4 3 12  4
			2  1 1 11  3
			2 -1 7 -1  0
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

class lieGauss{
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
				System.out.printf(String.format("%12.6f",matrix[i][j]));
			}
			System.out.println();
		}
	}
	public void getSolve(){						//输出方程的解
		for(int i = 0; i < N - 1; i++){
			changeRow(i,max(i));
			System.out.println("第"+(i+1)+"次交换");
			System.out.println("交换后：");
			getMatrix();
			if(calculate(i) == false){
				System.out.println("该方程组无解！");
				return;
			}
			System.out.println("计算后：");
			getMatrix();
		}
		back();
		System.out.println("回代求解得：");
		for(int j = 1; j <= N; j++){
			System.out.printf("X["+j+"]="+ String.format("%12.6f",x[j]) + "  ");
		}
		System.out.println();
	}
	public int max(int i){						//选择第i列最大列主元,返回最大主元所在行
		double temp = 0.0;
		int flag = 0;
		int j = i;
		while(j < N){
			if(Math.abs(matrix[j][i]) > temp){
				temp = Math.abs(matrix[j][i]);
				flag = j;
			}
			j++;
		}
		return flag;
	}
	public void changeRow(int i,int j){			//交换第i行和第j行
		double[] temp = new double[N + 1];
		for(int m = 0; m < N + 1; m++){
			temp[m] = matrix[i][m];
			matrix[i][m] = matrix[j][m];
			matrix[j][m] = temp[m];
		}
		/*测试用例
		System.out.println("第"+ i +"行和第"+ j +"行交换完成！");
		*/
	}
	public boolean calculate(int i){			//计算选取第i行第i列主元对其他行消元后计算结果
		for(int m = i + 1; m < N; m++){//从第i+1行开始对每一行做运算
				double first = matrix[m][i];//存储正在消元运算的该行的第i个元素，即r2=r2-(first/matrix[i][i])*r1
				for(int n = i; n < N + 1; n++){
					if(Math.abs(matrix[i][i]) < 1E-20)
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

class LieGaussTest{
	public static void main(String[] args){
		lieGauss g = new lieGauss();
		Scanner scanner = new Scanner(System.in);
		System.out.println("************************** 高斯列主元消元法 *************************************\n");
		System.out.println("请输入线性方程组的阶数:");
		lieGauss.N = scanner.nextInt();							//设置方程组阶数
		System.out.println("请输入线性方程组对应的增广矩阵:");
		double[][] m = new double[lieGauss.N][lieGauss.N+1];	//设置临时矩阵获取输入值
		for(int i = 0; i < m.length; i++)
			for(int j = 0; j < m[i].length;j++)
				m[i][j] = scanner.nextDouble();
		g.setMatrix(m);
		g.getSolve();
	}
}