package com.uuzz.ui;

import javax.swing.*;

public class GameJframe extends JFrame {
    public GameJframe(){
        //初始化界面
        initJFrame();
        //初始化菜单栏
        initJMenuBar();
        //初始化图片
        initImage();
        
        this.setVisible(true);
    }

    private void initImage() {
        int number = 1;
        for (int i = 0;i < 10;i++){
            for (int j = 0;j < 10;j++){
                //创建JLabel对象(管理容器)
                JLabel jLabel1 = new JLabel(new ImageIcon("image/羽ちゃ/切割后图片/images/"+number+".gif"));
                //设定图片位置
                jLabel1.setBounds(192*i,108*j,192,108);
                //将容器添加到界面中
                this.getContentPane().add(jLabel1);
                number++;
            }
        }
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
    }

}
