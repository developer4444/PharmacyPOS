package Saad.databaseutilities;

import Saad.classmodel.SalesReceipt;
import Saad.classmodel.Medicine;
import Saad.classmodel.SaadMedicineSaleSystem;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.ucanaccess.jdbc.Execute;
import net.ucanaccess.jdbc.UcanaccessConnection;
import net.ucanaccess.jdbc.UcanaccessResultSet;
import net.ucanaccess.jdbc.UcanaccessStatement;

public class DatabaseWrapper {

    static Connection conn;
    static Statement st;
    public static void intializeConnection()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            st = conn.createStatement();
            
        } catch( Exception ex)
        {
        
        }
    }

    public static void releaseConnection()
    {
        try
        {
            conn.close();
            st.close();
        } catch (Exception ex)
        {
            
        }
    }

    public static boolean removeMedicineFromDatabase(Medicine medicine)
    {
        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            //Statement st = conn.createStatement();
            StringBuffer query = new StringBuffer();
            query.append("delete from medicine where MedicineName='");
            query.append(medicine.medicineName.toString());
            query.append("';");
            st.executeUpdate(query.toString());

            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return true;
    }

    public static boolean updateMedicineInDatabase(Medicine medicine)
    {
            try
            {
                //Statement st = conn.createStatement();
                StringBuffer query = new StringBuffer();
                query.append("Update  medicine set MedicineName = '");
                query.append(medicine.medicineName);
                query.append("',set GenericName='");
                query.append(medicine.genericName);
                query.append("',set CompanyName='");
                query.append(medicine.companyName);
                query.append("',set SalesPrice=");
                query.append(medicine.salesPrice);
                query.append(",set CostPrice=");
                query.append(medicine.costPrice);
                query.append(",set Quantity=");
                query.append(medicine.quantity);
                query.append(", set ExpiryDate='");
                query.append(medicine.expiryDate);
                query.append("', set ManufacturingDate='");
                query.append(medicine.manfacturingDate);
                query.append("', set Rack= '");
                query.append(medicine.rackNumber);
                query.append("', set DistributorName ='");
                query.append(medicine.distributorName);
                query.append("' where MedicineID=");
                query.append(medicine.MedicineID);
                st.executeUpdate(query.toString());

                //st.close();
                //conn.close();
            } catch(Exception ex)
            {
                    return false;
            }
            return true;
    }
    public static ArrayList<String> getMedicineNames(String CompanyNames)
    {   
        ArrayList<String> medicineNames = new ArrayList<String>();
        try
        {
            Statement st = conn.createStatement();
            StringBuffer query = new StringBuffer();
            query.append("select MedicineName from MedicineRepository where companyname = '");
            query.append(CompanyNames);
            query.append("'");
            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();
            while(rs.next())
            {
                String MedicineName = (String)rs.getObject(1);
                medicineNames.add(MedicineName);
            }
        } catch (Exception ex)
        {
            return null;
        }
        return medicineNames;
    }
    public static ArrayList<String> getCompanyNames(String distname)
    {
        ArrayList<String> companyNames = new ArrayList<String>();
        try
        {
            Statement st = conn.createStatement();
            StringBuffer query = new StringBuffer();
            query.append("select companyname from MedicineRepository where DistributorName = '");
            query.append(distname);
            query.append("'");
            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();
            while(rs.next())
            {
                String CompanyName = (String)rs.getObject(1);
                companyNames.add(CompanyName);
            }
        } catch (Exception ex)
        {
            return null;
        }
        return companyNames;
    }
//    public static ArrayList<String> getGenericNames(String GGenricNames)
//    {
//        ArrayList<String> genericNames = new ArrayList<String>();
//        try
//        {
//            Statement st = conn.createStatement();
//            StringBuffer query = new StringBuffer();
//            query.append("select GenericName from MedicineRepository where medicinename = ';");
//            query.append(GGenricNames);
//            query.append("'");
//            ResultSet rs = st.executeQuery(query.toString());
//            ResultSetMetaData rsMetaData = rs.getMetaData();
//            while(rs.next())
//            {
//                String GenericName = (String)rs.getObject(1);
//                genericNames.add(GenericName);
//            }
//           
//        }catch(Exception ex)
//                    {
//                    return null;
//                    }
//        return genericNames;
//    } 
public static ArrayList<String> getDistributorNames()
{
    ArrayList<String> distributorNames = new ArrayList<String>();
    try
    {
        Statement st = conn.createStatement();
        StringBuffer query = new StringBuffer();
        query.append("select distinct(DistributorName) from MedicineRepository" );
        ResultSet rs = st.executeQuery(query.toString());
        ResultSetMetaData rsMetaData = rs.getMetaData();
        while(rs.next())
        {
            String DistributorName = (String)rs.getObject(1);
            distributorNames.add(DistributorName);
        }
    }
    catch(Exception ex)
    {
        return null;
    }
    return distributorNames;
}
    public static ArrayList<String> getUserGroupNames()
    {
        ArrayList<String> userGroupNames = new ArrayList<String>();

        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            Statement st = conn.createStatement();

            StringBuffer query = new StringBuffer();
            query.append("SELECT distinct(UserGroupName) FROM UserGroups");

            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();
            while(rs.next())
            {
                String UserGroupName = (String)rs.getObject(1);
                userGroupNames.add(UserGroupName);
            }

            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
            return null;
        }
        return userGroupNames;
    }

    public static boolean addUsertoUsersList(String userName, String userLoginName, String userPasswordEntry1, String userGroup)
    {
        boolean returnValue = false;
        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            //Statement st = conn.createStatement();

            StringBuffer query = new StringBuffer();
            query.append("insert into Users (UserLoginName, UserName, UserPassword, UserGroup ) values ('");
            query.append(userLoginName);
            query.append("','");
            query.append(userName);
            query.append("','");
            query.append(userPasswordEntry1);
            query.append("','");
            query.append(userGroup);
            query.append("')");
            st.executeUpdate(query.toString());
        } catch(Exception ex)
        {
            return false;
        }
        return true;
    }

    public static boolean checkUserLogin(String userName, String password)
    {
        boolean returnValue = false;
        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            //Statement st = conn.createStatement();

            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM Users where UserLoginName='");
            query.append(userName);
            query.append("' and UserPassword='");
            query.append(password);
            query.append("';");

            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();

            if(rs.next())
            {
                returnValue = true;
            }

            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return returnValue;
    }

    public static boolean insertMedicineInDatabase(Medicine medicine)
    {
        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            //Statement st = conn.createStatement();

            StringBuffer query = new StringBuffer();
            query.append("insert into medicine (MedicineName, GenericName, CompanyName, SalesPrice, CostPrice, Quantity, ExpiryDate, ManufacturingDate, Rack, DistributorName ) values ('");
            query.append(medicine.medicineName.toString());
            query.append("','");
            query.append(medicine.genericName);
            query.append("','");
            query.append(medicine.companyName);
            query.append("',");
            query.append(medicine.salesPrice);
            query.append(",");
            query.append(medicine.costPrice);
            query.append(",");
            query.append(medicine.quantity);
            query.append(",'");
            query.append(Long.toString(medicine.expiryDate.getTime()));
            query.append("','");
            query.append(Long.toString(medicine.manfacturingDate.getTime()));
            query.append("','");
            query.append(medicine.rackNumber);
            query.append("','");
            query.append(medicine.distributorName);
            query.append("')");
            st.executeUpdate(query.toString());

            query.delete(0, query.length());

            query.append("SELECT MedicineID FROM Medicine where MedicineName='");
            query.append(medicine.medicineName);
            query.append("'");

            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();

            if(rs.next())
            {
                Object obj = rs.getObject(1);
                fillMedicineField(1, obj, medicine);
            }

            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return true;
    }

    public static boolean fillReturnsListFromDatabase()
    {
            try
            {
                st = conn.createStatement();
                ResultSet rs = st.executeQuery("select info.SaleReceiptID, info.SalesmanName, info.SalesDate, info.TotalPrice, med.MedicineName, med.CompanyName, med.SalesPrice, med.CostPrice, receiptinfo.Quantity, med.ExpiryDate, med.ManufacturingDate from ReturnsInfo info, ReturnsReceiptInfo receiptinfo, Medicine med where info.SaleReceiptID= receiptinfo.SaleReceiptID and med.MedicineID =receiptInfo.MedicineID");
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int numberOfColumns = rsMetaData.getColumnCount();
                SalesReceipt sReceipt = null;

                while(rs.next())
                {
                    Medicine medicine = new Medicine();
                    for(int i = 1; i <= numberOfColumns;i++)
                    {
                        Object obj = rs.getObject(i);
                        switch(i)
                        {
                            case 1:
                                // Search if this object doesn't exist already in the database then create new object
                                long sReceiptID = Long.parseLong(obj.toString());
                                if(SaadMedicineSaleSystem.returnReceiptList.get(sReceiptID) == null)
                                {
                                    sReceipt = new SalesReceipt();
                                    SaadMedicineSaleSystem.returnReceiptList.put(sReceiptID, sReceipt);
                                }

                                 sReceipt.setSalesReceiptID(sReceiptID);
                                break;
                            case 2:
                                sReceipt.setSalesManName(obj.toString());
                                break;
                            case 3:
                                Long sDate = Long.parseLong((String)obj);
                                sReceipt.setSalesDate(new Date(sDate));
                                break;
                            case 4:
                                float totalPrice = Float.parseFloat(obj.toString());
                                sReceipt.setTotalPrice(totalPrice);
                                break;
                            case 5:
                                medicine.medicineName=obj.toString(); 
                                break;
                            case 6:
                                medicine.companyName=obj.toString(); 
                                break;
                            case 7:
                                medicine.salesPrice=new Double((double)obj).floatValue();; 
                                break;
                            case 8:
                                medicine.costPrice = new Double((double)obj).floatValue();;
                                break;
                            case 9:
                                // TODO : Find out if quantity is actually required and remove if not.
                                medicine.quantity = Long.parseLong(obj.toString());
                                break;
                            case 10:
                                Long eDate = Long.parseLong((String)obj);
                                medicine.expiryDate = new Date(eDate);
                                break;
                            case 11:
                                Long mDate = Long.parseLong((String)obj);
                                medicine.manfacturingDate = new Date(mDate);
                                break;
                        }		
                    }
                    sReceipt.medicineList.add(medicine);
                }
            } catch (Exception ex)
            {
                    ex.printStackTrace();
                    return false;
            }
            return true;
    }

    public static boolean fillReceiptListFromDatabase()
    {
            try
            {
                st = conn.createStatement();
                ResultSet rs = st.executeQuery("select info.SaleReceiptID, info.SalesmanName, info.SalesDate, info.TotalPrice, med.MedicineName, med.CompanyName, med.SalesPrice, med.CostPrice, receiptinfo.Quantity, med.ExpiryDate, med.ManufacturingDate, med.Rack, med.DistributorName from SalesInfo info, SalesReceiptInfo receiptinfo, Medicine med where info.SaleReceiptID= receiptinfo.SaleReceiptID and med.MedicineID =receiptInfo.MedicineID");                             
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int numberOfColumns = rsMetaData.getColumnCount();
                SalesReceipt sReceipt = null;// = new SalesReceipt();

                while(rs.next())
                {
                    Medicine medicine = new Medicine();
                    for(int i = 1; i <= numberOfColumns;i++)
                    {
                        Object obj = rs.getObject(i);
                        switch(i)
                        {
                            case 1:
                                    // Search if this object doesn't exist already in the database then create new object
                                    long sReceiptID = Long.parseLong(obj.toString());
                                    if(SaadMedicineSaleSystem.salesReceiptList.get(sReceiptID) == null)
                                    {
                                            sReceipt = new SalesReceipt();
                                            SaadMedicineSaleSystem.salesReceiptList.put(sReceiptID, sReceipt);
                                    }

                                     sReceipt.setSalesReceiptID(sReceiptID);
                                    break;
                            case 2:
                                    sReceipt.setSalesManName(obj.toString());
                                    break;
                            case 3:
                                    Long sDate = Long.parseLong((String)obj);
                                    sReceipt.setSalesDate(new Date(sDate));
                                    break;
                            case 4:
                                    float totalPrice = new Double((double)obj).floatValue();
                                    sReceipt.setTotalPrice(totalPrice);
                                    break;
                            case 5:
                                    medicine.medicineName=obj.toString(); 
                                    break;
                            case 6:
                                    medicine.companyName=obj.toString(); 
                                    break;
                            case 7:
                                    medicine.salesPrice = new Double((double)obj).floatValue();
                                    break;
                            case 8:
                                    medicine.costPrice = new Double((double)obj).floatValue();
                                    break;
                            case 9:
                                    // TODO : Find out if quantity is actually required and remove if not.
                                    medicine.quantity = Long.parseLong(obj.toString());
                                    break;
                            case 10:
                                    Long eDate = Long.parseLong((String)obj);
                                    medicine.expiryDate = new Date(eDate);
                                    break;
                            case 11:
                                    Long mDate = Long.parseLong((String)obj);
                                    medicine.manfacturingDate = new Date(mDate);
                                    break;
                            case 12:
                                    medicine.rackNumber = obj.toString();
                                    break;
                            case 13:
                                    medicine.distributorName = obj.toString();
                                    break;
                        }		
                    }
                    //sReceipt.medicineList.put(medicine.medicineName, medicine);
                    sReceipt.medicineList.add(medicine);
                }

                //st.close();
                //conn.close();
            } catch (Exception ex)
            {
                    ex.printStackTrace();
                    return false;
            }
            return true;
    }

    
    /*
    private static void fillReturnReceiptField(int i, Object obj,
                    SalesReceipt sReceipt, Medicine medicine) {
            switch(i)
            {
                case 1:
                    // Search if this object doesn't exist already in the database then create new object
                    long sReceiptID = Long.parseLong(obj.toString());
                    if(SaadMedicineSaleSystem.returnReceiptList.get(sReceiptID) == null)
                    {
                        sReceipt = new SalesReceipt();
                        SaadMedicineSaleSystem.returnReceiptList.put(sReceiptID, sReceipt);
                    }

                     sReceipt.setSalesReceiptID(sReceiptID);
                    break;
                case 2:
                    sReceipt.setSalesManName(obj.toString());
                    break;
                case 3:
                    Long sDate = Long.parseLong((String)obj);
                    sReceipt.setSalesDate(new Date(sDate));
                    break;
                case 4:
                    float totalPrice = Float.parseFloat(obj.toString());
                    sReceipt.setTotalPrice(totalPrice);
                    break;
                case 5:
                    medicine.medicineName=obj.toString(); 
                    break;
                case 6:
                    medicine.companyName=obj.toString(); 
                    break;
                case 7:
                    medicine.salesPrice=new Double((double)obj).floatValue();; 
                    break;
                case 8:
                    medicine.costPrice = new Double((double)obj).floatValue();;
                    break;
                case 9:
                    // TODO : Find out if quantity is actually required and remove if not.
                    medicine.quantity = Long.parseLong(obj.toString());
                    break;
                case 10:
                    Long eDate = Long.parseLong((String)obj);
                    medicine.expiryDate = new Date(eDate);
                    break;
                case 11:
                    Long mDate = Long.parseLong((String)obj);
                    medicine.manfacturingDate = new Date(mDate);
                    break;
            }		
    }
*/
    private static void fillSaleReceiptField(int i, Object obj,
                    SalesReceipt sReceipt, Medicine medicine) {
            switch(i)
            {
                case 1:
                        // Search if this object doesn't exist already in the database then create new object
                        long sReceiptID = Long.parseLong(obj.toString());
                        if(SaadMedicineSaleSystem.salesReceiptList.get(sReceiptID) == null)
                        {
                                sReceipt = new SalesReceipt();
                                SaadMedicineSaleSystem.salesReceiptList.put(sReceiptID, sReceipt);
                        }

                         sReceipt.setSalesReceiptID(sReceiptID);
                        break;
                case 2:
                        sReceipt.setSalesManName(obj.toString());
                        break;
                case 3:
                        Long sDate = Long.parseLong((String)obj);
                        sReceipt.setSalesDate(new Date(sDate));
                        break;
                case 4:
                        float totalPrice = new Double((double)obj).floatValue();
                        sReceipt.setTotalPrice(totalPrice);
                        break;
                case 5:
                        medicine.medicineName=obj.toString(); 
                        break;
                case 6:
                        medicine.companyName=obj.toString(); 
                        break;
                case 7:
                        medicine.salesPrice = new Double((double)obj).floatValue();
                        break;
                case 8:
                        medicine.costPrice = new Double((double)obj).floatValue();
                        break;
                case 9:
                        // TODO : Find out if quantity is actually required and remove if not.
                        medicine.quantity = Long.parseLong(obj.toString());
                        break;
                case 10:
                        Long eDate = Long.parseLong((String)obj);
                        medicine.expiryDate = new Date(eDate);
                        break;
                case 11:
                        Long mDate = Long.parseLong((String)obj);
                        medicine.manfacturingDate = new Date(mDate);
                        break;
            }		
    }

    public static boolean insertReceiptInDatabase(SalesReceipt saleReceipt)
    {
        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            //Statement st = conn.createStatement();
            StringBuffer saleInfoQuery = new StringBuffer();

            // TODO : Handling of discount field in the sales receipt
            saleInfoQuery.append("insert into SalesInfo (SaleReceiptID, SalesmanName, SalesDate, Discount, TotalPrice) values (");
            saleInfoQuery.append(saleReceipt.getReceiptID());
            saleInfoQuery.append(",'");
            saleInfoQuery.append(saleReceipt.getSalesManName());
            saleInfoQuery.append("','");
            saleInfoQuery.append(Long.toString(saleReceipt.getSalesDate().getTime()));
            saleInfoQuery.append("',");
            saleInfoQuery.append(saleReceipt.overallDiscount);
            saleInfoQuery.append(",");
            saleInfoQuery.append(saleReceipt.totalPrice);
            saleInfoQuery.append(")");
            st.executeUpdate(saleInfoQuery.toString());

            //HashMap<String, Medicine> medicineList = saleReceipt.medicineList;
            //Set<String> medSet = medicineList.keySet();
            for(Medicine medicineObj: saleReceipt.medicineList)
            {
                StringBuffer saleReceiptInfoQuery = new StringBuffer();
                saleReceiptInfoQuery.append("insert into SalesReceiptInfo (SaleReceiptID, MedicineID, Quantity) values (");
                saleReceiptInfoQuery.append(saleReceipt.getReceiptID());

                saleReceiptInfoQuery.append(",");
                saleReceiptInfoQuery.append(medicineObj.MedicineID);
                saleReceiptInfoQuery.append(",");
                saleReceiptInfoQuery.append(medicineObj.quantity);

                saleReceiptInfoQuery.append(")");
                st.executeUpdate(saleReceiptInfoQuery.toString());
            }

            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return true;
    }

    public static void fillMedicineListFromDatabase()
    {
            try
            {
                //Connection conn = getConnection();
                //Statement st = conn.createStatement();

                st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT MedicineID,MedicineName,"
                        + "GenericName,CompanyName,SalesPrice,CostPrice,Quantity,"
                        + "ExpiryDate,ManufacturingDate,Rack,DistributorName FROM Medicine");
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int numberOfColumns = rsMetaData.getColumnCount();

                while(rs.next())
                {
                    Medicine medicine = new Medicine();
                    for(int i = 1; i <= numberOfColumns;i++)
                    {
                            Object obj = rs.getObject(i);
                            fillMedicineField(i, obj, medicine);
                    }
                    SaadMedicineSaleSystem.medicineList.put(medicine.medicineName, medicine);
                    //SaadMedicineSaleSystem.stringMedicineList.add(medicine.medicineName + " [" + medicine.genericName + "]");
                }

                //st.close();
                //conn.close();
            } catch (Exception ex)
            {
                    ex.printStackTrace();
            }
    }

    private static void fillMedicineField(int i, Object obj, Medicine medicine)
    {
        try
        {
            switch(i)
            {
                case 1:
                    medicine.MedicineID = new Long((int)obj);
                    break;
                case 2:
                    medicine.medicineName = obj.toString();
                    break;
                case 3:
                    medicine.genericName = obj.toString();
                    break;
                case 4:
                    medicine.companyName = obj.toString();
                    break;
                case 5:
                    // TODO int has to be replaced by float
                    medicine.salesPrice = new Double((double)obj).floatValue();
                    break;
                case 6:
                    // TODO int has to be replaced by float
                    medicine.costPrice = new Double((double)obj).floatValue();
                    break;
                case 7:
                    medicine.quantity = new Long((int)obj);
                    break;
                case 8:
                    Long eDate = Long.parseLong((String)obj);
                    medicine.expiryDate = new Date(eDate);
                    break;
                case 9:
                    Long mDate = Long.parseLong((String)obj);
                    medicine.manfacturingDate = new Date(mDate);
                    break;
                case 10:
                    medicine.rackNumber = obj.toString();
                    break;
                case 11:
                    medicine.distributorName = obj.toString();
                    break;
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static Connection getConnection() throws Exception {
                    /*String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
                String url = "jdbc:odbc:medicine";
                String username = "";
                String password = "";
                Class.forName(driver);
                return DriverManager.getConnection(url, username, password);
                */

        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
        return conn;

      }

    public static boolean checkUserGroupOperationRights(String userGroupName, String operation) {
        boolean returnValue = false;
        try
        {
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM UserGroups where UserGroupName='");
            query.append(userGroupName);
            query.append("' and SecurityPrivilege='");
            query.append(operation);
            query.append("';");

            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();

            if(rs.next())
            {
                returnValue = true;
            }

            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return returnValue;
    }

    public static ArrayList<String> getSecurityGroups() {
        String userGroupName = "";
        ArrayList<String> securityGroups = new ArrayList<String>();
        try
        {
            StringBuffer query = new StringBuffer();
            query.append("SELECT distinct(SecurityPrivilege) FROM UserGroups;");

            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();

            while(rs.next())
            {
                String securityGroupName = rs.getObject(1).toString();
                securityGroups.add(securityGroupName);
            }

            //conn.close();
        } catch(Exception ex)
        {
                return null;
        }

        return securityGroups;
    }

    public static String getUserGroupName(String userName) {
        String userGroupName = "";

        try
        {

            StringBuffer query = new StringBuffer();
            query.append("SELECT UserGroup FROM Users where UserLoginName='");
            query.append(userName);
            query.append("';");

            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();

            if(rs.next())
            {
                userGroupName = rs.getObject(1).toString();
            }

            //conn.close();
        } catch(Exception ex)
        {
                return null;
        }
        return userGroupName;
    }

    public static boolean addSecurityPrivilegeForGroup(String userGroupName, String securityPrivilege) {
        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            StringBuffer securityGroupQuery = new StringBuffer();

            // TODO : Handling of discount field in the sales receipt
            securityGroupQuery.append("insert into UserGroups (UserGroupName , SecurityPrivilege) values ('");
            securityGroupQuery.append(userGroupName);
            securityGroupQuery.append("','");
            securityGroupQuery.append(securityPrivilege);
            securityGroupQuery.append("');");
            st.executeUpdate(securityGroupQuery.toString());

            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return true;

    }

    public static List<String> getListOfUsers() {
        List<String> users = new ArrayList<String>();

        try
        {

            StringBuffer query = new StringBuffer();
            query.append("SELECT UserLoginName FROM Users;");

            ResultSet rs = st.executeQuery(query.toString());
            ResultSetMetaData rsMetaData = rs.getMetaData();

            while(rs.next())
            {
                users.add(rs.getObject(1).toString());
            }

            //conn.close();
        } catch(Exception ex)
        {
                return null;
        }
        return users;

    }

    public static boolean removeUser(String userToRemove) {
        boolean returnValue = true;
        try
        {
            //Statement st = conn.createStatement();
            StringBuilder removeUserQuery = new StringBuilder();
            removeUserQuery.append("delete FROM Users where UserLoginName='");
            removeUserQuery.append(userToRemove);
            removeUserQuery.append("'");

            ResultSet executeQuery = st.executeQuery(removeUserQuery.toString());
            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                returnValue = false;
        }
        return returnValue;
    }

    public static List<String> getListOfUserGroups() {
        List<String> userGroups = new ArrayList<String>();

        try
        {
            StringBuffer query = new StringBuffer();
            query.append("SELECT distinct(UserGroupName) FROM UserGroups");

            ResultSet rs = st.executeQuery(query.toString());

            while(rs.next())
            {
                userGroups.add(rs.getObject(1).toString());
            }

            //conn.close();
        } catch(Exception ex)
        {
                return null;
        }

        return userGroups;
    }

    public static boolean removeUserGroupFromUserGroups(String userGroupToRemove) {
        boolean returnValue = true;
        try
        {
            //Statement st = conn.createStatement();
            StringBuilder removeUserQuery = new StringBuilder();
            removeUserQuery.append("delete from UserGroups where UserGroupName='");
            removeUserQuery.append(userGroupToRemove);
            removeUserQuery.append("'");

            ResultSet executeQuery = st.executeQuery(removeUserQuery.toString());
            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                returnValue = false;
        }
        return returnValue;
    }

    public static boolean removeUsersWithinUserGroup(String userGroupToRemove) {
        boolean returnValue = true;
        try
        {
            //Statement st = conn.createStatement();
            StringBuilder removeUserQuery = new StringBuilder();
            removeUserQuery.append("delete from Users where UserGroup='");
            removeUserQuery.append(userGroupToRemove);
            removeUserQuery.append("'");

            ResultSet executeQuery = st.executeQuery(removeUserQuery.toString());
            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                returnValue = false;
        }
        return returnValue;
    }

    public static boolean insertReturnReceiptInDB(SalesReceipt returnReceipt) {
        boolean returnValue = false;

        try
        {
            StringBuffer returnInfoQuery = new StringBuffer();

            // TODO : Handling of discount field in the sales receipt
            returnInfoQuery.append("insert into ReturnsInfo (SaleReceiptID, SalesmanName, SalesDate, Discount, TotalPrice) values (");
            returnInfoQuery.append(returnReceipt.getReceiptID());
            returnInfoQuery.append(",'");
            returnInfoQuery.append(returnReceipt.getSalesManName());
            returnInfoQuery.append("','");
            returnInfoQuery.append(Long.toString(returnReceipt.getSalesDate().getTime()));
            returnInfoQuery.append("',");
            returnInfoQuery.append(returnReceipt.overallDiscount);
            returnInfoQuery.append(",");
            returnInfoQuery.append(returnReceipt.totalPrice);
            returnInfoQuery.append(")");
            st.executeUpdate(returnInfoQuery.toString());

            //HashMap<String, Medicine> medicineList = saleReceipt.medicineList;
            //Set<String> medSet = medicineList.keySet();
            for(Medicine medicineObj: returnReceipt.medicineList)
            {
                StringBuffer returnReceiptInfoQuery = new StringBuffer();
                returnReceiptInfoQuery.append("insert into ReturnsReceiptInfo (SaleReceiptID, MedicineID, Quantity) values (");
                returnReceiptInfoQuery.append(returnReceipt.getReceiptID());

                returnReceiptInfoQuery.append(",");
                returnReceiptInfoQuery.append(medicineObj.MedicineID);
                returnReceiptInfoQuery.append(",");
                returnReceiptInfoQuery.append(medicineObj.quantity);

                returnReceiptInfoQuery.append(")");
                st.executeUpdate(returnReceiptInfoQuery.toString());
            }

            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return true;
    }

    public static boolean updateReceiptInDatabase(SalesReceipt newSalesReceipt) {
        try
        {
            //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Medicine.accdb");
            //Statement st = conn.createStatement();
            StringBuffer saleInfoQuery = new StringBuffer();

            // TODO : Handling of discount field in the sales receipt
            saleInfoQuery.append("Update salesinfo set TotalPrice=");
            saleInfoQuery.append(newSalesReceipt.totalPrice);
            saleInfoQuery.append(" where SaleReceiptID=");
            saleInfoQuery.append(newSalesReceipt.getReceiptID());
            st.executeUpdate(saleInfoQuery.toString());

            StringBuffer deleteQuery = new StringBuffer();
            deleteQuery.append("delete from SalesReceiptInfo where SaleReceiptID=");
            deleteQuery.append(newSalesReceipt.getReceiptID());
            st.executeUpdate(deleteQuery.toString());

            //HashMap<String, Medicine> medicineList = saleReceipt.medicineList;
            //Set<String> medSet = medicineList.keySet();
            for(Medicine medicineObj: newSalesReceipt.medicineList)
            {
                StringBuffer saleReceiptInfoQuery = new StringBuffer();
                saleReceiptInfoQuery.append("insert into SalesReceiptInfo (SaleReceiptID, MedicineID, Quantity) values (");
                saleReceiptInfoQuery.append(newSalesReceipt.getReceiptID());

                saleReceiptInfoQuery.append(",");
                saleReceiptInfoQuery.append(medicineObj.MedicineID);
                saleReceiptInfoQuery.append(",");
                saleReceiptInfoQuery.append(medicineObj.quantity);

                saleReceiptInfoQuery.append(")");
                st.executeUpdate(saleReceiptInfoQuery.toString());
            }

            //st.close();
            //conn.close();
        } catch(Exception ex)
        {
                return false;
        }
        return true;
        
    }

   
}
