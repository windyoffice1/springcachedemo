import com.alibaba.fastjson.JSON;
import com.windyoffice.springcachedemo.entity.User;
import com.windyoffice.springcachedemo.util.HttpClientUtils;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static class  MyThread extends Thread{

        private String str;
        private String url;

        public  MyThread(String url,String str){
            this.str=str;
            this.url=url;
        }
        @Override
        public void run(){
            HttpClientUtils.httpPost(url,str);
        }
    }


    public static void main(String[] args) throws Exception {
        String url="http://127.0.0.1:8080/ifservicedemo/user/addusers";
        List<User> userList=new ArrayList<>();
      for (int i=0;i<1000;i++){
          User user=new User();
          user.setUserName("测试"+i);
          user.setAddress("中文（汉语）有标准语和方言之分，其标准语即汉语普通话，是规范后的汉民族共同语 [1]  ，也是中国的国家通用语言。现代汉语方言一般可分为：官话方言、吴方言、湘方言、客家方言、闽方言、粤方言、赣方言等。 [3-4]  汉字是中文的书写体系，现存最早可识的汉字是殷商的甲骨文和稍后的金文，在西周时演变成籀文，到秦朝发展出小篆和秦隶，至汉魏时隶书盛行，到了汉末隶书隶变楷化为正楷，楷书盛行于魏晋南北朝，至今通行。现代汉字是指楷化后的汉字正楷字形，包括繁体中文和简体中文"+i);
          user.setUserId(i);
          userList.add(user);
      }
     for (int i=0;i<1000;i++){
        Thread thread=new  MyThread(url, JSON.toJSONString(userList));
        thread.start();
      }
    //HttpPost httpPost= HttpClientUtils.httpPost(url, JSON.toJSONString(userList));
     // System.out.println(JSON.toJSONString(userList));

    }
}
