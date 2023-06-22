package partner.java.sdk.client.demo;

import jp.pokepay.partnerapi.request.SendEcho;
import jp.pokepay.partnerapi.response.Echo;
import jp.pokepay.partnerapi.request.Request;
import jp.pokepay.partnerapi.PartnerAPI;
import java.io.File;

public class App {
    public static void main(String[] args) {
        try {
            String homeDir = System.getProperty("user.home");
            jp.pokepay.partnerapi.PartnerAPI client = new jp.pokepay.partnerapi.PartnerAPI(new File(homeDir, ".pokepay/config.properties"));
            Request request = new SendEcho("Hello");
            Echo echo = (Echo) client.send(request);
            System.out.println(echo.getStatus());
            System.out.println(echo.getMessage());
        } catch (jp.pokepay.partnerapi.PartnerRequestError error) {
            System.out.println(error.getType());
            System.out.println(error.getMessage());
            System.out.println(error.getRawJson());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
