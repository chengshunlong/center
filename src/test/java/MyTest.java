import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.*;
import com.zhbit.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.Service;
import java.util.List;


public class MyTest {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("userServiceImpl");
//        List<Users> users = userServiceImpl.queryAllUser();
//        System.out.println(users);
//        List<Users> users = userServiceImpl.queryAllUser();
//        System.out.println(users);
//        userServiceImpl.addUser(new Users(0,"003","123456","admin3","13727080007","admin"));

        Users users = userServiceImpl.queryUserByUserId("223");
        System.out.println(users);
    }

    @Test
    public void test02(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SiteService siteServiceImpl = (SiteService) context.getBean("siteServiceImpl");
//        Sites sites = siteServiceImpl.querySiteBySid(1);
//        Sites sites = siteServiceImpl.querySiteWithtTypeNameBySid(1);
//        System.out.println(sites);
//        System.out.println(sites.getType().getTypeName());
//        List<Sites> sites1 = siteServiceImpl.querySiteWithTypeName();
//        for (Sites site : sites1) {
//            System.out.println(site + "" + site.getType());
//        }

//        siteServiceImpl.updateSite(new Sites(0,"MB504","MB504.jpg","不可用",5));

        List<Sites> sitesList = siteServiceImpl.searchSite("不可用");
        System.out.println(sitesList);
    }

    @Test
    public void test3(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EqpService eqpServiceImpl = (EqpService) context.getBean("eqpServiceImpl");
        List<Eqps> eqps = eqpServiceImpl.queryAllEqp();
        System.out.println(eqps);
    }

    @Test
    public void test4(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderService orderServiceImpl = (OrderService) context.getBean("orderServiceImpl");
//        List<Orders> orders = orderServiceImpl.queryOrderByUid(1);
//        List<Orders> orders = orderServiceImpl.queryAllOrder();
//        System.out.println(orders);

    }

    @Test
    public void test5(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SiteService siteServiceImpl = (SiteService) context.getBean("siteServiceImpl");
        Sites site = siteServiceImpl.querySiteBySiteName("第一饭堂");
        System.out.println(site);
    }

    @Test
    public void test6(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EOrderService eOrderServiceImpl = (EOrderService) context.getBean("EOrderServiceImpl");
//        EOrders eOrders = eOrderServiceImpl.queryEOrderByEOid(1);
//        List<EOrders> eOrders = eOrderServiceImpl.queryAllEOrder();
//        EOrders eOrders = eOrderServiceImpl.queryEOrderWithEqpsByEOid(1);
//        List<EOrders> eOrders = eOrderServiceImpl.queryEOrderWithEqps();
//        List<EOrders> eOrders = eOrderServiceImpl.queryEOrderWithEqpsByUid(2);
//        eOrderServiceImpl.updateEOrders(new EOrders(1,))
//        System.out.println(eOrders);

    }

    @Test
    public void test7(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("userServiceImpl");
//        Users users = userServiceImpl.queryUserByUserId("180021105754");
//        Users teacher1 = userServiceImpl.queryUserByUserName("teacher1");
//        System.out.println(users);
//        System.out.println(teacher1);
        String s = userServiceImpl.queryUserPwdByUserId("180021100001");
        System.out.println(s);
    }

    @Test
    public void test8(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EOrderService eorderServiceImpl = (EOrderService) context.getBean("EOrderServiceImpl");

        List<EOrders> eOrders = eorderServiceImpl.queryAllEOrders();
        System.out.println(eOrders);
    }

    @Test
    public void test9(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TotalOrdersService totalOrdersServiceImpl = (TotalOrdersService) context.getBean("totalOrdersServiceImpl");
//        List<TotalOrders> totalOrders = totalOrdersServiceImpl.queryAllTotalOrders();
//        System.out.println(totalOrders);
//        totalOrdersServiceImpl.addTotalOrder(new TotalOrders("",1,"180021105754","成龙","13727086324",1000,"已付款"));
//        TotalOrders totalOrders = totalOrdersServiceImpl.queryAllTotalOrdersWithEOrdersByTOid("70940d0636574e8dafaf984b54bdab57");
//        List<EOrders> eOrders = totalOrders.geteOrders();
//        System.out.println(totalOrders);
//        System.out.println(eOrders);
        List<TotalOrders> totalOrders = totalOrdersServiceImpl.queryAllTotalOrdersWithEOrders();
        for (TotalOrders totalOrder : totalOrders) {
            List<EOrders> eOrders = totalOrder.geteOrders();
            System.out.println(eOrders);
        }
    }


    @Test
    public void test10(){
//        for (int i = 0; i < 9999; i++) {
//            String uuid = BeanUtils.getUUID();
//            System.out.println(uuid.length());
//        }
    }


    @Test
    public void test11(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TotalOrdersService totalOrdersServiceImpl = (TotalOrdersService) context.getBean("totalOrdersServiceImpl");
        List<TotalOrders> totalOrders = totalOrdersServiceImpl.queryAllTotalOrdersWithEOrdersPageHelper();
        for (TotalOrders totalOrder : totalOrders) {
            String time = totalOrder.getTime();
            System.out.println(time);

        }
//        System.out.println(totalOrders);
    }

    @Test
    public void tets12(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService usreServiceImpl = (UserService) context.getBean("userServiceImpl");
        List<Users> usersList = usreServiceImpl.searchUser("student");
        System.out.println(usersList);
    }

    @Test
    public void test13(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TotalOrdersService totalOrdersServiceImpl = (TotalOrdersService) context.getBean("totalOrdersServiceImpl");
        List<TotalOrders> student = totalOrdersServiceImpl.searchTotalOrders("student");
        System.out.println(student);
    }

    @Test
    public void test14(){
//        String str = "我是中国人a";
//        for (int i = 0; i < str.length(); i++) {
//            boolean chinese = BeanUtils.isChinese(str.charAt(i));
//            System.out.println(chinese);
//        }
//        String str = "AA";
//        System.out.println(BeanUtils.isEnglishAlphabet(str));

        String str = "阿斯顿A";
        System.out.println(BeanUtils.isEnglishAlphabet(str));
    }



    @Test
    public void test15(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SiteService siteServiceImpl = (SiteService) context.getBean("siteServiceImpl");
        Sites sites = siteServiceImpl.querySiteWithtTypeNameBySid(3);
        System.out.println(sites);
        System.out.println(sites.getType());

    }


    @Test
    public void test16(){
        System.out.println(BeanUtils.isDigit("abc123;;") || BeanUtils.isEnglishAlphabet("abc123;;"));
    }


}