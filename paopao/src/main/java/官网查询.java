import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.invoke.ConstantCallSite;

/**
 * @author eric
 * @date 2022/9/5 4:18
 **/
public class 官网查询 {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 199 ; i++) {
            String url = "https://bnb.web.sdo.com/web5/news/news_list.asp?Page="+i+"&CategoryID=a";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
            request.setHeader("Cookie", "userinfo=userid=1684757793-1167111448-1660788852&siteid=SDG-08118-01; SNDA_ADRefererSystem_MachineTicket=d8e97c87-87a8-436c-b40a-4a58b6a60ef8; ASPSESSIONIDACCBQCAT=MNPJANNBBCLFPBPGBFEHLMFL; NSC_MC-HX-coc.xfc.tep.dpn-I80=ffffffff09884e5e45525d5f4f58455e445a4a423660; LAT=l=69&l_err=0.98; __wftflow=1441855765=4&1841994281=7&1947414390=6&2031984249=11&2041108315=4; __wftflag=1; RT=ul=1662323306289&r=https%3A%2F%2Fbnb.web.sdo.com%2Fweb5%2Fnews%2Fnews_list.asp%3FPage%3D3%26CategoryID%3Da&hd=1662323302078&cl=1662323306287&nu=https%3A%2F%2Fbnb.web.sdo.com%2Fweb5%2Fnews%2Fnews_list.asp%3FPage%3D4%26CategoryID%3Da");
            request.setHeader("Host","bnb.web.sdo.com");
            request.setHeader("Referer","https://bnb.web.sdo.com/web5/news/news_list.asp?Page=3&CategoryID=a");
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entry = response.getEntity();
            String result = EntityUtils.toString(entry);
            String search = "猪";
            if(result.contains(search)){
                //System.out.println(url);
                int start = result.indexOf(search)-100;
                int end = result.indexOf(search);
                String res = result.substring(start,end+search.length());
                //System.out.println(res);
                start = res.indexOf("href=")+9;
                end = res.indexOf("title")-2;
                res  = res.substring(start,end);
                System.out.println("https://bnb.web.sdo.com/web5/"+res);
            };
        }
    //https://bnb.web.sdo.com/web5/news/news_list.asp?Page=2&CategoryID=a
    }

}
