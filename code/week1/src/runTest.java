package src;
import src.algorithms.SocialNetwork;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//import java.util.concurrent.TimeUnit;

public class runTest {
	public static void main(String[] args){
		runTest socTest= new runTest();
/*		SocialNetwork myNetwork= new SocialNetwork(10);
		int[][] raw_data= socTest.read_log_data("filename.txt");
		int time_when_connected = myNetwork.solve(myNetwork.convert_log_data(raw_data));
		System.out.println(time_when_connected);
*/
//		performanceData[] performance_log = new performanceData[N_max];
		int N_max=500;
		long[][] pdata= new long[N_max][2];
		for(int N=1;N<=N_max;N++){
			SocialNetwork soc=new SocialNetwork(N);
			for(int m=N*10;m<=N*10;m++){
				long t_10=0;
				int n_succ=0;
				for(int i=0;i<1500;i++){
					int[][] raw_data = socTest.generate_log_data(N,m);
					SocialNetwork.log_data[] log=soc.convert_log_data(raw_data);
					long start_time=System.nanoTime();
					int time_when_connected = soc.solve(log);
					if(time_when_connected!=-1)n_succ++;
					long end_time=System.nanoTime();
					t_10+=end_time-start_time;
				}
				long t_avg=t_10/1500;
				pdata[N-1][0]=m;
				pdata[N-1][1]=t_avg;
//System.out.println("N="+ String.valueOf(N)+", m=" + String.valueOf(m) + ", time_avg="+ String.valueOf(t_avg)+", n_succ="+ String.valueOf(n_succ));
			}
		}
		for(int i=0;i<N_max;i++){
			System.out.println(String.valueOf(i+1)+" "+String.valueOf(pdata[i][0])+" "+String.valueOf(pdata[i][1]));
		}
	}
	/*
	private class performanceData{
		private String[] params;
		private int execution_time;
		public performanceData(String[] params){
			this.execution_time = new int;
			this.params=params;
		}
	}*/
	private int[][] generate_log_data(int N, int m){
		Random rand=new Random();
		int[][] log = new int[m][3];
		for(int i=0; i<m; i++){
			log[i][0]=rand.nextInt(N);
			log[i][1]=rand.nextInt(N);
			log[i][2]=i;
		}
		return log;
	}
	private int[][] read_log_data(String filename){
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		ArrayList<Integer> t = new ArrayList<Integer>();
		try {
			File dataFile = new File("filename.txt");
			Scanner dataReader = new Scanner(dataFile);
			while (dataReader.hasNextLine()) {
				String dataRow = dataReader.nextLine();
				String[] dataEntries=dataRow.trim().split(" ", 3);
				a.add(Integer.parseInt(dataEntries[0]));
				b.add(Integer.parseInt(dataEntries[1]));
				t.add(Integer.parseInt(dataEntries[2]));
			}
			int[][] raw_log_data = new int[a.size()][3];

			for(int i=0; i<a.size(); i++){
				raw_log_data[i][0]=a.get(i);
				raw_log_data[i][1]=b.get(i);
				raw_log_data[i][2]=t.get(i);
			}
			/*
			for(int[] i : raw_log_data){
				for(int j : i){
					System.out.print(Integer.toString(j));
				}
				System.out.println(" ");
			}*/
			dataReader.close();
			return raw_log_data;
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
		}
		return new int[0][0];
	}
}
