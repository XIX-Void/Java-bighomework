package bighomework;
import java.util.Stack;




public class Core {
	private int[][] core;
	private int x;
	private int y;
	class Chess{
		int x;
		int y;
		Chess(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	Stack<Chess> stack;
    //构造方法,数组元素默认是0，生成一个棋盘二维数组数据
    public Core(int x,int y) {
        stack = new Stack<>();
        core = new int[x][y];
        this.x=x;
        this.y=y;
    }
    
    //判断该列底端是否有棋子
    private boolean checkPut(int x) {
    	if(core[x][y-1] != 0) {
    		return true;
    	}
    	return false;
    }
    
    //判断该列顶端是否无棋子
    public boolean checkTop(int x) {
    	if(core[x][0] != 0) {
    		return false;
    	}
    	return true;
    }
    
    
    //判断输赢
    private int checkVictory(int x,int y,int var) {
        //横向判断
        int trans = 0;
        for(int i=x-4;i<x+5;i++) {
            if(i<0||i>=this.x) continue;
            if(core[i][y]==var) {
                trans++;
            }
            else {
                trans=0;
            }
            if(trans==4) return var;
        }
        //纵向判断
        int longitudinal = 0;
        for(int i=y-4;i<y+5;i++) {
            if(i<0||i>=this.y) continue;
            if(core[x][i]==var) {
                longitudinal++;
            }
            else {
                longitudinal=0;
            }
            if(longitudinal==4) return var;
        }
        //从左上到右下
        int leftUPToDown = 0;
        for(int i=x-4,j=y+4;i<x+5&&j>y-5;i++,j--) {
            if(i<0||i>=this.x||j<0||j>=this.y) continue;
            if(core[i][j]==var) {
                leftUPToDown++;
            }else {
                leftUPToDown=0;
            }
            if(leftUPToDown==4) return var;
        }
        //从左下到右上
        int leftDownToUP = 0;
        for(int i=x+4,j=y+4;i>x-5&&j>y-5;i--,j--) {
            if(i<0||i>=this.x||j<0||j>=this.y) continue;
            if(core[i][j]==var) {
                leftDownToUP++;
            }else {
                leftDownToUP=0;
            }
            if(leftDownToUP==4) return var;
        }
        return 0;
    }
    
    //下棋,共有x个位置
    public int setChess(int x,int var){
    	if(checkTop(x)) {
	    	if(checkPut(x)) {
	    		for(int i = y-1 ; i>=1 ; i--) {
	    			int temp = i;
	    			if((core[x][i] != 0 && core[x][i-1] == 0)) {
	    				core[x][temp-1] = var;
	    				Chess chess = new Chess(x,temp-1);
	    				stack.push(chess);
	    				return checkVictory(x, temp-1, var);
	    			}
	    		}
	    	}else {
	    		core[x][y-1] = var;
	    		Chess chess = new Chess(x,y-1);
	    		stack.push(chess);
	    		return checkVictory(x,y-1,var);
	    	}
    	}else return 9;
    	return -1; //否则下不了棋
    }
    
    //悔棋
    public boolean RetChess() {
        if(stack.isEmpty()) return false;
        Chess chess = stack.pop();
        core[chess.x][chess.y]= 0;
        return true;
    }
    
    //获取棋盘状态
    public int[][] getCore(){
        return this.core;
    }
    
    //重新开始
    public void Restart() {
        for(int i=0;i<this.x;i++) {
            for(int j=0;j<this.y;j++) {
                this.core[i][j]=0;
            }
        }
        this.stack.clear();
    }
}