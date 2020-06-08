package bighomework;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;


//����������������Frame���
public class Windows {
    static {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Throwable ignored) { }
    }

    public static void main(String[] args) {
        new Frame();
        
    }
}


class Frame extends JFrame {
	//���캯��
    public Frame() {
        super("���������ҵ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(200, 150);
        setVisible(true);

        loadPanel(new Homepage(this));
    }
    //���¼���������ķ���
    public void loadPanel(JPanel p) {
        getContentPane().removeAll();
        getContentPane().add(p);
        revalidate();
        repaint();
    }
}


class Homepage extends JPanel {
	public static int X = 10;
	public static int Y = 10;
    private static Font fTitle = new Font("���Ĳ���", Font.PLAIN, 100);
    private static Font fMenu = new Font("����", Font.PLAIN, 24);
    private static Font fMenuItem = new Font("����", Font.PLAIN, 18);
    public static JTextField tname;
    private static JTextField tx;
    private static JTextField ty;
    private final Frame frame;
    public Homepage(Frame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("<html><body>��    ��    �� <br> ��    ��    ҵ</body></html>", JLabel.CENTER);
        title.setFont(fTitle);
        title.setSize(300, 600);
        p1.add(title,JLabel.CENTER);
        JPanel p2 = new JPanel();
        tname = new JTextField("���ڴ�������������");
        tx = new JTextField("���ڴ������Զ������̳��ȣ�ֻ��������");
        ty = new JTextField("���ڴ������Զ������̿�ȣ�ֻ��������");
        p2.add(tname);
        p2.add(tx,BorderLayout.NORTH);
        p2.add(ty,BorderLayout.SOUTH);
        p1.add(p2,BorderLayout.NORTH);
        add(p1);

        frame.setJMenuBar(new HomepageMenubar());
    }

    private class HomepageMenubar extends JMenuBar {
        public HomepageMenubar() {
            super();
            add(new JMenu("�˻�ģʽ") {{
                setFont(fMenu);
                add(new JMenuItem("7x6ģʽ") {{
                    addActionListener(e ->{
                    	X = 7;
                		Y = 6;
                    	Gamefield.com = 1;
                    	frame.loadPanel(new Gamefield(frame));
                    });
                    setFont(fMenuItem);
                }});
                add(new JMenuItem("8x7ģʽ") {{
                	addActionListener(e ->{
                		X = 8;
                		Y = 7;
                		Gamefield.com = 1;
                		frame.loadPanel(new Gamefield(frame));
                    });
                    setFont(fMenuItem);
                }});
                add(new JMenuItem("�Զ���ģʽ") {{
                	addActionListener(e ->{
                		boolean isNumX = tx.getText().matches("[0-9]+"); 
                		boolean isNumY = ty.getText().matches("[0-9]+");
                		if(isNumX == true && isNumY == true) {
	                		X = Integer.parseInt(tx.getText());
	                		Y = Integer.parseInt(ty.getText());
	                		Gamefield.com = 1;
	                		frame.loadPanel(new Gamefield(frame));
                		}else {
                			JOptionPane.showMessageDialog(null, "����������", "����", JOptionPane.DEFAULT_OPTION);
                		}
                    });
                    setFont(fMenuItem);
                }});
            }});

            add(new JMenu("���ģʽ") {{
                setFont(fMenu);
                add(new JMenuItem("7x6ģʽ") {{
                	addActionListener(e ->{
                		X = 7;
                		Y = 6;
                		Gamefield.com = 0;
                        frame.loadPanel(new Gamefield(frame));
                    });
                    setFont(fMenuItem);
                }});
                add(new JMenuItem("8x7ģʽ") {{
                	addActionListener(e ->{
                		X = 8;
                		Y = 7;
                		Gamefield.com = 0;
                        frame.loadPanel(new Gamefield(frame));
                    });
                    setFont(fMenuItem);
                }});
                add(new JMenuItem("�Զ���ģʽ") {{
                	addActionListener(e ->{
                		boolean isNumX = tx.getText().matches("[0-9]+"); 
                		boolean isNumY = ty.getText().matches("[0-9]+");
                		if(isNumX == true && isNumY == true) {
                			X = Integer.parseInt(tx.getText());
                    		Y = Integer.parseInt(ty.getText());
                    		Gamefield.com = 0;
                    		frame.loadPanel(new Gamefield(frame));
                		}else {
                			JOptionPane.showMessageDialog(null, "����������", "����", JOptionPane.DEFAULT_OPTION);
                		}
                    });
                    setFont(fMenuItem);
                }});
            }});
        }
    }
}




class Gamefield extends JPanel implements MouseListener{
	public Core core;
	public static int com = 0;
	private static Random random = new Random();
	private int setChessRandom = random.nextInt(7);
    private int var = 1;
	private static final long serialVersionUID = 1L;
    private static Font fTitle = new Font("���Ĳ���", Font.PLAIN, 100);
    private static Font fMenu = new Font("����", Font.PLAIN, 24);
    private static Font fMenuItem = new Font("����", Font.PLAIN, 18);
    private final Frame frame;
    //���캯��
    public Gamefield(Frame frame) {
    	core = new Core(Homepage.X, Homepage.Y);
        this.frame = frame;
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setSize(300, 600);
        //panel.add(new JLabel("<html><body>��    ��    ��    һ <br> ��    ��    ��    ��</body></html>", JLabel.CENTER));
        add(panel);
        frame.setJMenuBar(new GamefieldMenubar());
        this.addMouseListener(this);
    }
    
    //�����õ��Ļ�ͼ���������ӻ���
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setFont(new Font("����", Font.PLAIN, 18));
    	g.drawRect(690,60, 50, 30);
    	g.drawString("����",700,80);
    	// ��
        for (int i = 0; i < Homepage.Y+1; i++)
        	//x1,y1,x2,y2
            g.drawLine(30, 30 + i * 30, 30*(Homepage.X+1), 30 + i * 30);
        // ����
        for (int i = 0; i < Homepage.X+1; i++)
            g.drawLine(30 + i * 30, 30, 30 + i * 30, 30*(Homepage.Y+1));
        
    	int[][] board = core.getCore();
    	for (int i = 0; i < Homepage.X; i++) {
            for (int j = 0; j < Homepage.Y; j++) {
                if(board[i][j] == 1)
                    g.drawOval(35 + i * 30, 35 + j * 30, 20, 20);
                if(board[i][j] == 2)
                    g.fillOval(35 + i * 30, 35 + j * 30, 20, 20);
            }
        }
    	
    }
    
  
    
    //�˵���
    private class GamefieldMenubar extends JMenuBar {
        public GamefieldMenubar() {
            super();
            add(new JMenu("��Ϸ") {{
                setFont(fMenu);
                add(new JMenuItem("Ͷ��") {{
                    addActionListener(e -> frame.loadPanel(new Homepage(frame)));
                    setFont(fMenuItem);
                }});
            }});
        }
    }


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.repaint();
		if(e.getX() > 40  &&  e.getX() < 30*(Homepage.X+1)) {
			int a = core.setChess(_CgetX(e.getX()),var);
			if (a == 1) {
	            JOptionPane.showMessageDialog(null, Homepage.tname.getText() + "Ӯ��", "��ϲ", JOptionPane.DEFAULT_OPTION);
	        }
	        if(a==2) {
	            JOptionPane.showMessageDialog(null,"�ڵ�Ӯ��", "��ϲ", JOptionPane.DEFAULT_OPTION);;
	        }
	        if(a!=-1) {
	            if(var==1) var=2;
	            else if(var==2) var=1;
	        }
		}else if(e.getX()>690&&e.getX()<760&&e.getY()>60&&e.getY()<90) {
            core.RetChess();
            if(var==1) var=2;
            else if(var==2) var=1;
            this.repaint();
        }
	}
	
	//�����ж�����������ĸ�xλ��
    private int _CgetX(int x) {
        x -= 40; //���̱�Դ
            return x / 30;
    }
    
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int i = 0;
		this.repaint();
		if(com == 1) {
			if(e.getX()>40&&e.getX()<30*(Homepage.X+1)) {
				int b = core.setChess(setChessRandom,var);
				setChessRandom = random.nextInt(Homepage.X);
				if (b == 1) {
		            JOptionPane.showMessageDialog(null,Homepage.tname.getText() + "Ӯ��", "��ϲ", JOptionPane.DEFAULT_OPTION);
		        }
		        if(b == 2) {
		            JOptionPane.showMessageDialog(null,"�ڵ�Ӯ��", "��ϲ", JOptionPane.DEFAULT_OPTION);;
		        }
		        if(b != -1 && b != 9) {
		            if(var==1) var=2;
		            else if(var==2) var=1;
		        }
		        if(b == 9) {
		        	do {
		        		i++;
			        	setChessRandom = random.nextInt(Homepage.X);
			        	b = core.setChess(setChessRandom,var);
			        	if (b == 1) {
				            JOptionPane.showMessageDialog(null,Homepage.tname.getText() + "Ӯ��", "��ϲ", JOptionPane.DEFAULT_OPTION);
				        }
				        if(b == 2) {
				            JOptionPane.showMessageDialog(null,"�ڵ�Ӯ��", "��ϲ", JOptionPane.DEFAULT_OPTION);;
				        }
			        	if(i>500) {
			        		JOptionPane.showMessageDialog(null,"ƽ��", "�ź�", JOptionPane.DEFAULT_OPTION);
			        		break;
			        	}
		        	}while(b == 9);
		        	if(var==1) var=2;
		        	else if(var==2) var=1;
		        }
			}
		}
	}
}