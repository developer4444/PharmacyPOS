package Saad.classmodel;

import Saad.classmodel.Medicine;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class SalesReceipt {

	private long salesReceiptID = 0;
	private Date salesDate = new Date();
	private String salesmanName;
	//public HashMap<String,Medicine> medicineList = new HashMap<String,Medicine>();
        public ArrayList<Medicine> medicineList = new ArrayList<Medicine>();

	// TODO : Should we maintain any field for per medicine discount
	public float totalPrice = 0;
	public float overallDiscount = 0;

        /*
	public SalesReceipt(Date sDate, String smanName, ArrayList<Medicine> mList, long sReceiptID)
	{
		salesDate = sDate;
		salesmanName = smanName;
		medicineList = mList;
		salesReceiptID = sReceiptID;
	}
        */
	public SalesReceipt(Date sDate, String smanName, long sReceiptID)
	{
		salesDate = sDate;
		salesmanName = smanName;
		salesReceiptID = sReceiptID;
	}

	public SalesReceipt()
	{
		
	}

	public void addMedicineToList(Medicine saleMedicine)
	{
		//medicineList.put(saleMedicine.medicineName, saleMedicine);
                medicineList.add(saleMedicine);
	}

	public void setTotalPrice(float totPrice)
	{
		totalPrice = totPrice;
	}

	public void setSalesDate(Date sDate)
	{
		salesDate = sDate;
	}

	public void setSalesManName(String smanName)
	{
		salesmanName = smanName;
	}

	public void setSalesReceiptID(long sReceiptID)
	{
		salesReceiptID = sReceiptID;
	}

	public Date getSalesDate()
	{
		return salesDate;
	}

	public String getSalesManName()
	{
		return salesmanName;
	}
	
	public long getReceiptID()
	{
		return salesReceiptID;
	}
        
        public float getTotalSales()
        {
            return totalPrice;
        }
        
        public String getJSONForSalesReceipt()
        {
            if(medicineList.size() <= 0)
            {
                return null;
            }

            // Have to add actual values
            // 1. Test the case for no medicine
            // 2. Test the case for last medicine comma removal
            StringBuilder jsonString = new StringBuilder();
            jsonString.append("{\"salesReceiptID\":\"");
            jsonString.append(Long.toString(salesReceiptID));
            jsonString.append("\",");

            jsonString.append("{\"salesDate\":\"");
            jsonString.append(Long.toString(salesReceiptID));
            jsonString.append("\",");
            
            jsonString.append("{\"salesmanName\":\"");
            jsonString.append(Long.toString(salesReceiptID));
            jsonString.append("\",");

            jsonString.append("{\"totalPrice\":\"");
            jsonString.append(Long.toString(salesReceiptID));
            jsonString.append("\",");

            jsonString.append("{\"overallDiscount\":\"");
            jsonString.append(Long.toString(salesReceiptID));
            jsonString.append("\",");

            jsonString.append("{\"customerName\":\"");
            jsonString.append(Long.toString(salesReceiptID));
            jsonString.append("\",");

            jsonString.append("\"Medicines\":[");
            int index = 0;
            for(Medicine med:medicineList)
            {
                jsonString.append("{\"costPrice\":\"");
                jsonString.append(Float.toString(med.costPrice));
                jsonString.append("\",\"");

                jsonString.append("salesPrice\":\"");
                jsonString.append(Float.toString(med.salesPrice));
                jsonString.append("\",\"");

                jsonString.append("medicineName\":\"");
                jsonString.append(med.medicineName);
                jsonString.append("\",\"");

                jsonString.append("genericName\":\"");
                jsonString.append(med.genericName);
                jsonString.append("\",\"");

                jsonString.append("expiryDate\":\"");
                jsonString.append(med.expiryDate);
                jsonString.append("\",\"");
                
                jsonString.append("manfacturingDate\":\"");
                jsonString.append(med.manfacturingDate);
                jsonString.append("\",\"");

                jsonString.append("quantity\":\"");
                jsonString.append(med.quantity);
                jsonString.append("\",\"");

                jsonString.append("companyName\":\"");
                jsonString.append(med.companyName);
                jsonString.append("\",\"");
                
                jsonString.append("rackNumber\":\"");
                jsonString.append(med.rackNumber);
                jsonString.append("\",\"");

                jsonString.append("MedicineID\":\"");
                jsonString.append(med.MedicineID);
                jsonString.append("\",\"");

                jsonString.append("discount\":\"");
                jsonString.append(med.discount);
                jsonString.append("\",\"");

                jsonString.append("distributorName\":\"");
                jsonString.append(med.distributorName);
                index++;

                if(index == medicineList.size())
                {
                    jsonString.append("\"}\"");
                }
                else
                {
                    jsonString.append("\"},\"");
                }
            }
            jsonString.append("]}");

            return jsonString.toString();
        }
}


