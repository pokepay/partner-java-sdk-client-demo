import jp.pokepay.partnerapi.request.CreateEcho;
import jp.pokepay.partnerapi.response.Echo;
import jp.pokepay.partnerapi.request.Request;
import jp.pokepay.partnerapi.PartnerAPI;
import java.io.File;

public class App {
    public static void main(String[] args) {
        try {
            jp.pokepay.partnerapi.PartnerAPI client = new jp.pokepay.partnerapi.PartnerAPI(new File("/home/user/.pokepay/config.properties"));
            Request request = new CreateEcho("Hello");
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
