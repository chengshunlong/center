/**
 * @Description
 * @Author livestrong
 * @Date 2021-06-30-1:21
 */
import java.util.* ;
import java.io.* ;

class codeCount {
    private static int count ; //统计总行数
    private static int countCPP ;//CPP
    private static int countJAVA ;//java
    private static int countPY ;//python
    private String path ; //用于接收用户输入保存代码的文件夹的路径
    private int reading(String path) throws Exception { //该函数用来统计一个代码文件的行数
        FileReader reader = new FileReader(path) ;
        BufferedReader buffer = new BufferedReader(reader) ;
        int count = 0 ;
        while(buffer.readLine()!=null) { count ++ ;
        }
        buffer.close() ;
        reader.close() ;
        return count ;
    }
    private void caculate(String nowpath) throws Exception{//计数函数
        File nowfile = new File(nowpath) ;
        if (nowfile.isFile()) { if (nowpath.endsWith(".cpp")) { int sum = reading(nowpath) ; countCPP += sum ; count += sum ; } else if (nowpath.endsWith(".py")) { int sum = reading(nowpath) ; countPY += sum ; count += sum ; } else if (nowpath.endsWith(".java")) { int sum = reading(nowpath) ; countJAVA += sum ; count += sum ; } else { System.out.println(nowpath.substring(nowpath.indexOf("."))+":该类型文件不属于代码文件或该代码文件统计功能正在开发中，敬请期待！"); }
        }
        else { //如果这个路径表示的是一个文件夹，则执行递归操作 String []filesset = nowfile.list() ; for (String i:filesset ) { String newpath = nowpath + nowfile.separator + i ;//合成路径 caculate(newpath) ; }
        }
    }
    public codeCount(String src) {
        path = src ;
    }
    public static int getLinesCPP() {
        return countCPP ;
    }
    public static int getLinesJAVA() {
        return countJAVA ;
    }
    public static int getLinesPY() {
        return countPY ;
    }
    public static int getLines() {
        return count ;
    }
    public void caculator() throws Exception { //外界包装
        this.caculate(path) ;
    }
    public String toString() { //重写toString方法
        return  "统计结果如下:\n" + "cpp行数：\n"+countCPP + "\njava行数：\n"+countJAVA + "\npython行数：\n"+countPY ;
    }
}

public class test {
    public static void main(String []args) throws Exception {
        Scanner cin = new Scanner(System.in) ;
        System.out.println("请输入地址：");
        String path = cin.next() ;
        codeCount machine = new codeCount(path) ;
        machine.caculator(); System.out.println(machine.toString());
        cin.close();
    }
}