package Saad.classmodel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaadMedicineSaleSystem {
    public static HashMap<String,Medicine> medicineList = new HashMap<String,Medicine>();
    public static List<String> stringMedicineList = new ArrayList<String>();
    public static HashMap<Long,SalesReceipt> salesReceiptList = new HashMap<Long,SalesReceipt>();
    public static HashMap<Long,SalesReceipt> returnReceiptList = new HashMap<Long,SalesReceipt>();
}
