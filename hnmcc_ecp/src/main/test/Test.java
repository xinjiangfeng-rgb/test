import com.xwtech.xwecp.dao.DAOException;

/**
 * Created by 54344 on 2018/8/22.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:cfg/applicationContext-*.xml"})
public class Test {


    @org.junit.Test
    public void test() throws DAOException {

        JSONObject object = new JSONObject();


        object.put("phoneNumber", "13343810910");

        System.out.print(object);

/*<dependency >
  <groupId > com.xwtec </groupId >
  <artifactId > ecp </artifactId >
  <version > 1.9 </version >
</dependency >

<dependency >
  <groupId > com.xwtec </groupId >
  <artifactId > logic_common </artifactId >
  <version > 1.9 </version >
</dependency >*/


    }
}
