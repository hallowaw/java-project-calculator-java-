import java.awt.BorderLayout; // 用于创建边界布局管理器，将组件放置在北、南、东、西、中五个区域
import java.awt.Color; // 用于处理颜色，常用于设置组件的背景色或前景色
import java.awt.GridLayout; // 用于创建网格布局管理器，将容器划分为固定数量的行和列，每个单元格大小相等
import java.awt.event.ActionEvent; // 表示一个动作事件，如按钮点击事件
import java.awt.event.ActionListener; // 事件监听器接口，必须实现 actionPerformed 方法来处理动作事件
import javax.swing.JButton; // 用于创建按钮，按钮可以触发 ActionEvent 事件
import javax.swing.JFrame; // 顶层容器，用于创建应用程序主窗口
import javax.swing.JPanel; // 用于创建面板容器，可以包含其他组件并使用布局管理器
import javax.swing.JTextField; // 用于创建单行文本框，允许用户输入和显示文本


//Calculator 类扩展了（extends） JFrame 类，意味着它继承了创建和管理窗口的能力。
//同时，Calculator 实现（implements）了 ActionListener 接口，这意味着它能够响应用户在界面上执行的动作（如按钮点击）。
//通过实现 ActionListener，Calculator 类必须定义 actionPerformed 方法来处理按钮点击等动作事件。
public class Calculator extends JFrame implements ActionListener {
    //JTextField 是一个用于输入单行文本的Swing组件，Swing 是 Java 中用于创建图形用户界面 (GUI)
    // 的一组库。Swing 组件是用来构建图形用户界面的各种可视化元素，如按钮、文本框、标签、面板等。
    // 它们位于 javax.swing 包中。Swing 组件是纯 Java 实现的轻量级组件，具有跨平台性，
    // 并且基于 Java 基础类 (Java Foundation Classes, JFC) 提供丰富的 GUI 功能。
      private JTextField expressText;
      //JPanel 是一个轻量级的容器，可以包含和组织其他Swing组件。
      //这两个面板用于布局和组织界面中的不同部分：数字按钮和操作按钮。
      private JPanel numPanel, operPanel;
      private String express = "";//用于存储计算器中的输入表达式
    public static void main(String[] args) {
        new Calculator("我的计算器");
    }

    //这是负责初始化对象的，注意它与类的名字相同，但不是函数也不是类
    public Calculator(String title) {
        super(title);//确定你的app的窗口标题
        init();
// 使窗口变为可见。调用这个方法后，窗口会显示在屏幕上。
// 如果不调用这个方法，即使窗口已经创建并设置了其他属性，它仍然不会显示出来。
        this.setVisible(true);

// 设置窗口的大小为 300 像素宽和 300 像素高。
// 这行代码定义了窗口的初始尺寸。
        this.setSize(300, 300);

// 将窗口的位置设置为屏幕的中心。
// 如果传递 null 作为参数，窗口会相对于屏幕居中显示。
// 这个方法的作用是使窗口在显示时居中于屏幕，提供更好的用户体验。
        this.setLocationRelativeTo(null);

// 设置窗口的关闭操作。
// 当用户点击窗口的关闭按钮（X按钮）时，程序将退出。
// JFrame.EXIT_ON_CLOSE 表示关闭窗口时，程序将结束并退出运行。
// 其他选项包括 JFrame.DISPOSE_ON_CLOSE（关闭窗口但不退出程序）等。
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //初始化函数
    private void init() {
        // 设置主窗口的布局管理器为边界布局
        // 边界布局允许将组件放置在东、南、西、北、中五个区域
        this.setLayout(new BorderLayout());

        // 创建一个面板，用于容纳表达式文本框和“Clear”按钮
        // 这个面板的布局管理器设置为边界布局，以便可以将文本框和按钮放置在指定的位置
        JPanel topPanel = new JPanel(new BorderLayout());

        // 创建一个文本框，用于显示和输入数学表达式
        // 设置文本框为不可编辑，背景颜色为黄色
        expressText = new JTextField();
        expressText.setEditable(false);  // 文本框不可编辑
        expressText.setBackground(Color.YELLOW);  // 背景颜色设置为黄色
        topPanel.add(expressText, BorderLayout.CENTER);  // 将文本框添加到面板的中央

        // 创建一个按钮，用于清空表达式文本框
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);  // 添加事件监听器，响应点击事件
        topPanel.add(clearButton, BorderLayout.EAST);  // 将“Clear”按钮添加到面板的右边

        // 将包含表达式文本框和“Clear”按钮的面板添加到主窗口的北边（顶部）
        this.add(topPanel, BorderLayout.NORTH);

        // 创建一个面板，用于放置数字按钮，设置为网格布局
        // 网格布局将面板划分为4行3列
        numPanel = new JPanel();
        numPanel.setLayout(new GridLayout(4, 3));

        // 创建一个面板，用于放置操作按钮，设置为网格布局
        // 网格布局将面板划分为5行1列
        operPanel = new JPanel();
        operPanel.setLayout(new GridLayout(5, 1));

        // 调用addButtons方法，为数字按钮面板和操作按钮面板添加按钮并设置相应的监听器
        addButtons(numPanel, operPanel);

        // 将数字按钮面板添加到主窗口的中央区域
        this.add(numPanel, BorderLayout.CENTER);
        // 将操作按钮面板添加到主窗口的东边（右侧）
        this.add(operPanel, BorderLayout.EAST);
    }

    //给图形界面加入函数的
    private void addButtons(JPanel numPanel, JPanel operPanel) {
        // 数字按钮的标签数组
        // 包含了0-9的数字按钮以及"00"和"^"按钮
        String[] numLabels = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "00", "0", "^"};

        // 操作按钮的标签数组
        // 包含了基本的四则运算操作符和等号
        String[] operLabels = {"+", "-", "*", "/", "="};

        // 遍历数字按钮标签数组
        for (String label : numLabels) {
            // 为每个标签创建一个新的JButton
            JButton button = new JButton(label);
            // 为按钮添加事件监听器，响应按钮点击事件
            button.addActionListener(this);
            // 将按钮添加到数字按钮面板（numPanel）中
            numPanel.add(button);
        }

        // 遍历操作按钮标签数组
        for (String label : operLabels) {
            // 为每个标签创建一个新的JButton
            JButton button = new JButton(label);
            // 为按钮添加事件监听器，响应按钮点击事件
            button.addActionListener(this);
            // 将按钮添加到操作按钮面板（operPanel）中
            operPanel.add(button);
        }
    }

    //@Override 注解用于指示 actionPerformed 方法
    // 是对ActionListener 接口中 actionPerformed 方法的覆盖。
    @Override
// 这个方法是 ActionListener 接口的实现，用于响应用户按下按钮的事件
    //我们点击按钮的操作（action）就是参数ActionEvent e
    public void actionPerformed(ActionEvent e) {
        // 获取触发事件的组件（即被点击的按钮）
        JButton sourceButton = (JButton) e.getSource();

        // 获取按钮上的文本（即按钮的标签）
        String command = sourceButton.getText();

        // 根据按钮的文本来处理不同的操作
        switch (command) {
            case "=":
                // 如果按钮文本是“=”号，调用 calculateResult 方法来计算并显示结果
                calculateResult();
                break;
            case "Clear":
                // 如果按钮文本是“Clear”，清空文本框的内容
                expressText.setText("");
                break;
            default:
                // 对于其他按钮（数字和操作符），将按钮的文本追加到表达式文本框中
                expressText.setText(expressText.getText() + command);
                break;
        }
    }

    // 用于计算最终的结果
    private void calculateResult() {
        try {
            // 获取当前表达式
            // 从文本框中获取用户输入的表达式
            String expression = expressText.getText();

            // 分割数字和操作符
            // 按运算符分割表达式，将其拆分为数字部分
            // 正则表达式 [+\-*/%^] 匹配所有算术运算符
            String[] nums = expression.split("[+\\-*/%^]");

            // 按数字分割表达式，将其拆分为运算符部分
            // 正则表达式 [0-9.]+ 匹配所有数字和小数点
            String[] opers = expression.split("[0-9.]+");

            // 检查分割后是否有足够的操作数和运算符
            // 确保至少有两个操作数和两个运算符
            if (nums.length < 2 || opers.length < 2) {
                // 如果操作数或运算符数量不足，显示“输入错误”信息
                expressText.setText("输入错误");
                return;
            }

            // 解析操作数和运算符
            // 将第一个操作数从字符串转换为 double，并去除多余的空白
            double num1 = Double.parseDouble(nums[0].trim());

            // 将第二个操作数从字符串转换为 double，并去除多余的空白
            double num2 = Double.parseDouble(nums[1].trim());

            // 获取运算符，并去除多余的空白
            String oper = opers[1].trim();

            // 计算结果
            // 使用 switch 语句根据运算符计算结果
            double result = switch (oper) {
                case "+" -> num1 + num2;    // 加法运算
                case "-" -> num1 - num2;    // 减法运算
                case "*" -> num1 * num2;    // 乘法运算
                case "/" -> num2 != 0 ? num1 / num2 : Double.NaN;  // 除法运算，除数不能为零
                case "%" -> num1 % num2;    // 取余运算
                case "^" -> Math.pow(num1, num2);  // 幂运算
                default -> Double.NaN;    // 默认情况，结果为 NaN 表示计算错误
            };

            // 显示结果或错误信息
            // 如果结果为 NaN（计算错误），则显示“计算错误”信息
            // 否则，将计算结果转换为字符串并显示
            expressText.setText(Double.isNaN(result) ? "计算错误" : String.valueOf(result));
        } catch (Exception e) {
            // 捕获所有异常（例如，输入格式错误或解析错误），并显示“输入错误”信息
            expressText.setText("输入错误");
        }

        // 计算完后清空表达式
        // 清空表达式，以便进行新的计算
        express = "";
    }

}
