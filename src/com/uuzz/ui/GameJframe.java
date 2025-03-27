package com.uuzz.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJframe extends JFrame implements KeyListener {
    //二维数组保存图片顺序
    int[][] data = new int[10][10];
    //图片分割后的拼图数量
    int imageNumber = 100;
    //空白位置索引
    int x = 0;
    int y = 0;
    //正确答案的二维数组
    int[][] win = new int[10][10];

    public GameJframe(){
        //初始化界面
        initJFrame();
        //初始化菜单栏
        initJMenuBar();
        //打乱图片
        initData();
        //加载图片
        initImage();
        
        this.setVisible(true);
    }

    private void initData() {
        //创建一维数组并打乱索引顺序
        int[] tempArr = new int[imageNumber];
        for (int i = 0;i < imageNumber;i++){
            tempArr[i] = i;
        }
        Random r = new Random();
        for (int i = 0;i < tempArr.length;i++){
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //将打乱的一维索引存入二维数组
        for (int i = 0;i < tempArr.length;i++){
            if(tempArr[i] == 0){
                x = i / 10;
                y = i % 10;
                System.out.println("空白位置是"+x+","+y);
            }else{
                data[i / 10][i % 10] = tempArr[i];
            }
        }

    }

    private void initImage() {
        //清空所有已加载图片
        this.getContentPane().removeAll();
        //判断是否胜利
        if(victory()){
            System.out.println("你比泰森还牛逼");
        }
        for (int i = 0;i < 10;i++){
            for (int j = 0;j < 10;j++){
                int num = data[i][j];
                //创建JLabel对象(管理容器)
                JLabel jLabel1 = new JLabel(new ImageIcon("image/羽ちゃ/切割后图片/images/"+num+".gif"));
                //设定图片位置
                jLabel1.setBounds(192*i,108*j,192,108);
                //将容器添加到界面中
                this.getContentPane().add(jLabel1);
            }
        }
        //刷新界面
        this.getContentPane();repaint();
    }

    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上的选项对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        //创建菜单选项中的具体条目
        JMenuItem replayItem = new JMenuItem("重新开始");
        JMenuItem reloginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem accountItem = new JMenuItem("公众号");
        //将具体条目添加到菜单选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);
        //将菜单选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //将菜单添加到界面中
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面大小
        this.setSize(1920,1200);
        //设置标题
        this.setTitle("拼图 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置默认位置
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消图片默认位置设置
        this.setLayout(null);
        //添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //按下不松时触发，查看完整图片
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon("image/羽ちゃ/切割后图片/images/all.png"));
            all.setBounds(0,0,1920,1080);
            this.getContentPane().add(all);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，如过游戏胜利，无法继续移动图片
        if(victory()){
            return;
        }
        //判断键盘的方向键输入，并移动图片位置
        int code = e.getKeyCode();
        if(code == 37 && x != 9){
            System.out.println("向左移动");
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
        }else if(code == 38 && y != 9){
            System.out.println("向上移动");
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
        }else if(code == 39 && x != 0){
            System.out.println("向右移动");
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
        }else if(code == 40 && y != 0){
            System.out.println("向下移动");
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
        }else if(code == 65){
            initImage();
        }
        initImage();
    }
    //判断是否完成游戏
    public boolean victory(){
        int count = 1;
        for(int i = 0;i < data.length;i++){
            for(int j = 0;j < data[i].length;j++){
                if(i == data.length-1 && j == data[i].length-1){
                    win[i][j] = 0;
                    break;
                }
                win[i][j] += count;
                count += 1;
            }
        }
        for (int i = 0;i < data.length;i++){
            for(int j = 0;j < data[i].length;j++){
                if (data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
