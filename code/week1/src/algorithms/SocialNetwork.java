package src.algorithms;
public class SocialNetwork {//weighted quick union
	private int N;
	private int[] id;
	private int[] size;
	private int maxSize;
	private boolean debug=false;
	public SocialNetwork(int N){
		this.N = N;
		this.id = new int[N];
		this.size = new int[N];
		this.maxSize=1;
	}
	public SocialNetwork(int N, boolean debug){
		this.N = N;
		this.id = new int[N];
		this.size = new int[N];
		this.maxSize=1;
		this.debug=debug;
	}
	public class log_data{
		/*
		 * log_data -> { a, b, time} 
		 */
		private int member_a_id;
		private int member_b_id;
		private int timestamp;
	}

	public int solve(log_data[] log){
		for(int i=0;i<this.N;i++){
			this.id[i]=i;
			this.size[i]=1;
		}
		int m=log.length;	
		int time=-1;
		for(int i=0;i<m;i++){
			if(debug)System.out.println(">timestamp " + String.valueOf(log[i].timestamp));
			this.union(log[i].member_a_id, log[i].member_b_id);
			if(this.tree_is_connected()){
				time=log[i].timestamp;
				break;
			}
		}
		return time;

	}
	private void union(int a, int b){
		if(! this.connected(a,b)){
			int root_a=this.root(a);
			int root_b=this.root(b);
			int dbg_id_a=-1, dbg_id_root_a=-1, dbg_id_b=-1, dbg_id_root_b=-1;
			if(debug){
				System.out.println("union(" + String.valueOf(a) + ", " + String.valueOf(b) + "):");
				System.out.println("	root("+ String.valueOf(a) + ")=" + String.valueOf(root_a));	
				System.out.println("	root("+ String.valueOf(b) + ")=" + String.valueOf(root_b));	
				dbg_id_a=id[a];
				dbg_id_root_a=id[root_a];
				dbg_id_b=id[b];
				dbg_id_root_b=id[root_b];
				
			}
			if(this.size[root_a]<=this.size[root_b]){
				this.id[root_a]=root_b;
				int dbg_tmp=this.size[root_b];
				this.size[root_b]+=this.size[root_a];
				if(this.size[root_b]>this.maxSize)this.maxSize=this.size[root_b];
				if(debug){
					System.out.println("	id[root_a]<-root_b");	
					System.out.println("	id("+ String.valueOf(root_a) + ")=" + String.valueOf(dbg_id_root_a) + " -> " + String.valueOf(id[root_a]));	
					System.out.println("	id("+ String.valueOf(root_b) + ")=" + String.valueOf(id[root_b]));	
					System.out.println("	size[root_a="+ String.valueOf(root_a) + "]=" + String.valueOf(size[root_a]) );
					System.out.println("	size[root_b="+ String.valueOf(root_b) + "]=" + String.valueOf(dbg_tmp) + " -> " + String.valueOf(size[root_b]));
				}
			}
			else{
				this.id[root_b]=root_a;
				int dbg_tmp=this.size[root_a];
				this.size[root_a]+=this.size[root_b];
				if(this.size[root_a]>this.maxSize)this.maxSize=this.size[root_a];
				if(debug){
					System.out.println("	id[root_b]<-root_a");	
					System.out.println("	id("+ String.valueOf(root_a) + ")=" + String.valueOf(id[root_a]));	
					System.out.println("	id("+ String.valueOf(root_b) + ")=" + String.valueOf(dbg_id_root_b) + " -> " + String.valueOf(id[root_b]));	
					System.out.println("	size[root_b="+ String.valueOf(root_b) + "]=" + String.valueOf(size[root_b]));
					System.out.println("	size[root_a="+ String.valueOf(root_a) + "]=" + String.valueOf(dbg_tmp) + " -> " + String.valueOf(size[root_a]));
				}
			}
		}
		else{
			if(debug)System.out.println("already connected; no union operation executed");
		}	
	}
	private int root(int a){
		int i=a;
		while(this.id[i]!=i){
			i=this.id[i];
		}
		return i;
	}
	private boolean connected(int a, int b){
		return root(a)==root(b);
	}
	private boolean tree_is_connected(){
		if(this.maxSize==N)return true;
		return false;
	}
	public log_data[] convert_log_data(int[][] raw_data){
		int s=raw_data.length;
		log_data[] log=new log_data[s];
		for(int i=0;i<s;i++){
			log[i]=new log_data();
			log[i].member_a_id=raw_data[i][0];
			log[i].member_b_id=raw_data[i][1];
			log[i].timestamp=raw_data[i][2];
		}
		return log;
	}
}
