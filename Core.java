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
    //���췽��,����Ԫ��Ĭ����0������һ�����̶�ά��������
    public Core(int x,int y) {
        stack = new Stack<>();
        core = new int[x][y];
        this.x=x;
        this.y=y;
    }
    
    //�жϸ��е׶��Ƿ�������
    private boolean checkPut(int x) {
    	if(core[x][y-1] != 0) {
    		return true;
    	}
    	return false;
    }
    
    //�жϸ��ж����Ƿ�������
    public boolean checkTop(int x) {
    	if(core[x][0] != 0) {
    		return false;
    	}
    	return true;
    }
    
    
    //�ж���Ӯ
    private int checkVictory(int x,int y,int var) {
        //�����ж�
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
        //�����ж�
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
        //�����ϵ�����
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
        //�����µ�����
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
    
    //����,����x��λ��
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
    	return -1; //�����²�����
    }
    
    //����
    public boolean RetChess() {
        if(stack.isEmpty()) return false;
        Chess chess = stack.pop();
        core[chess.x][chess.y]= 0;
        return true;
    }
    
    //��ȡ����״̬
    public int[][] getCore(){
        return this.core;
    }
    
    //���¿�ʼ
    public void Restart() {
        for(int i=0;i<this.x;i++) {
            for(int j=0;j<this.y;j++) {
                this.core[i][j]=0;
            }
        }
        this.stack.clear();
    }
}