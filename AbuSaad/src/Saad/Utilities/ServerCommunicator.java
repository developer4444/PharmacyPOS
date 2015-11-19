/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Saad.Utilities;
//
//import Saad.classmodel.Medicine;
//import Saad.classmodel.SalesReceipt;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.ProtocolException;
//import static javafx.css.StyleOrigin.USER_AGENT;
//import javax.net.ssl.HttpsURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//
///**
// *
// * @author Ahmad Habib
// */
//public class ServerCommunicator {
//
//    /**
//     *
//     */
//    public static void postReceiptOnServer(SalesReceipt saleReceipt)   
//    {
//        try
//        {
//            String url = "http://localhost:61448/LoginPage/WebService.asmx/HelloWorld";
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            CloseableHttpClient client = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("SaleReceiptID", Long.toString(saleReceipt.getReceiptID())));
//            params.add(new BasicNameValuePair("password", "pass"));
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//            CloseableHttpResponse response = client.execute(httpPost);
//            System.out.println(" Status Code : " + response.getStatusLine().getStatusCode());
//            client.close();
//            add reuqest header
            /*
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "USER_AGENT");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            StringBuffer parameters = new StringBuffer();
            (SaleReceiptID, SalesmanName, SalesDate, Discount, TotalPrice)
            parameters.append("SaleReceiptID=");
            parameters.append(saleReceipt.getReceiptID());
            parameters.append("&SalesmanName=");
            parameters.append(saleReceipt.getSalesManName());
            parameters.append("&SalesDate=");
            parameters.append(saleReceipt.getSalesDate());
            parameters.append("&Discount=");
            parameters.append(saleReceipt.overallDiscount);
            parameters.append("&TotalPrice=");
            parameters.append(saleReceipt.totalPrice);
            parameters.append("&MedicineCount=");
            parameters.append(saleReceipt.medicineList.size());
            int medicineIdIndex = 0;
            int quantityIndex = 0;
            */
            /*
            for(Medicine medicineObj: saleReceipt.medicineList)
            {
                String medicineId = "medicineId" + medicineIdIndex;
                parameters.append("&");
                parameters.append(medicineId);
                parameters.append("=");
                parameters.append(medicineObj.MedicineID);
                String quantity = "quantity" + quantityIndex;
                parameters.append("&");
                parameters.append(quantity);
                parameters.append("=");
                parameters.append(medicineObj.quantity);
            }
            */

            /*con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters.toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + parameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        print result
        System.out.println(response.toString());
        */            
                    
//        }
//        catch(Exception ex)
//        {
//           
//        }
//    }
//
//}
