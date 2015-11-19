/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Saad.gui;


import Saad.Utilities.Configuration;
import Saad.databaseutilities.DatabaseWrapper;
import Saad.classmodel.Security.SecurityManager;
import java.util.Properties;
import Saad.Utilities.DateLabelFormatter;
//import Saad.Utilities.ServerCommunicator;
import Saad.classmodel.Medicine;
import java.text.SimpleDateFormat;
import java.util.Date;
import Saad.classmodel.SaadMedicineSaleSystem;
import Saad.classmodel.SalesReceipt;
import Saad.classmodel.Security.User;
import com.sun.glass.ui.Screen;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javafx.scene.control.DatePicker;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author Pasha
 */
public class MainFrame extends javax.swing.JFrame {


    /**
     * Creates new form MainFrame
     */
    public MainFrame() {

        super(vendorName);

        DatabaseWrapper.fillMedicineListFromDatabase();
        DatabaseWrapper.fillReceiptListFromDatabase();
        DatabaseWrapper.fillReturnsListFromDatabase();

        ImageIcon printIcon = new ImageIcon("receipt.png");
        ImageIcon stockIcon = new ImageIcon("box.png");

        appIcon = Toolkit.getDefaultToolkit().createImage("house.png");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenDimension = screenSize;

        initComponents();

        medicineNameDropDownField.addItemListener(new ItemChangeListener("medicineNameDropDownField"));
        updateStockMedicineNameDropDownField.addItemListener(new ItemChangeListener("updateStockMedicineNameDropDownField"));

        ReceiptJFrame.setPreferredSize(screenSize);
        constructSearchableTermList();
        intializeMedicineDropDownString();

        // TODO : This should be part of configurable layout options.
        receiptTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        medicineNameDropDownField.setBorder(new EmptyBorder(3,10,3,3));

        userGroupNames = DatabaseWrapper.getUserGroupNames();
        mainDesktopPane.show();
        currentOnTopFrame = ReceiptJFrame;
        //ReceiptJFrame.show();
        AddStockJFrame.setVisible(false);
        EasyStockAdditionJFrame.setVisible(false);
        UpdateStockJFrame.setVisible(false);
        TotalSalesInDateRange.setVisible(false);
        ShortItemsCompanyJFrame.setVisible(false);
        TotalMedicineSaleJFrame.setVisible(false);
        TotalCompanySaleJFrame.setVisible(false);
        TotalSalesManSalesJFrame.setVisible(false);
        TotalReturnsByDateJFrame.setVisible(false);
        TotalReturnsByMedicineJFrame.setVisible(false);
        TotalReturnsByCompanyJFrame.setVisible(false);
        TotalReturnsSalesManJFrame.setVisible(false);
        ShortItemsCompanyJFrame.setVisible(false);
        ShortItemsOverallJFrame.setVisible(false);
        ShortItemsOnRackJFrame.setVisible(false);
        AddUserJFrame.setVisible(false);
        AddUserGroupJFrame.setVisible(false);
        RemoveUserJFrame.setVisible(false);
        RemoveUserGroupJFrame.setVisible(false);

        ((javax.swing.plaf.basic.BasicInternalFrameUI) RemoveUserJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) RemoveUserGroupJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) AddUserGroupJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) AddUserJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) AddStockJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) EasyStockAdditionJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) UpdateStockJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ReceiptJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalSalesInDateRange.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ShortItemsCompanyJFrame.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalMedicineSaleJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalCompanySaleJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalSalesManSalesJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalReturnsByDateJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalReturnsByMedicineJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalReturnsByCompanyJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) TotalReturnsSalesManJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ShortItemsCompanyJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ShortItemsOverallJFrame.getUI()).setNorthPane(null);        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ShortItemsOnRackJFrame.getUI()).setNorthPane(null);        


        JButton printReceiptToolbarButton = new JButton(printIcon);
        printReceiptToolbarButton.setToolTipText("Print Receipt");

        printReceiptToolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

                if(!loggedUser.hasRightsOnOperation("PrintReceipts"))
                {
                    JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
                    return;
                }

                cancelButtonPressedOnForm();
            }

        });

    JButton stockToolbarButton = new JButton(stockIcon);
    stockToolbarButton.setToolTipText("Add Stock");
    stockToolbarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

            if(!loggedUser.hasRightsOnOperation("PrintReceipts"))
            {
                JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
                return;
            }

                SwingUtilities.invokeLater( new Runnable() {
                        public void run() {
                        clearCurrentOnTopFrame(currentOnTopFrame);
                    }
                });

            currentOnTopFrame.hide();
            currentOnTopFrame = AddStockJFrame;
            AddStockJFrame.show();
        }
    });

    mainToolbar.add(printReceiptToolbarButton);
    mainToolbar.add(stockToolbarButton);

    mainReciptChangeQuantityButton.setVisible(false);
    
    receiptTable.getModel().addTableModelListener(new TableModelListener() {
      public void tableChanged(TableModelEvent e) {
        try{

            int rowChanged = e.getFirstRow();
            int colChanged = e.getColumn();

            // TODO : Replace with a constant
            if(colChanged == 5 && state != ROW_DELETED && state != MEDICINE_ADDED)
            {
                String quantityValue = (String)receiptTable.getValueAt(rowChanged, colChanged);
                if(Long.parseLong(quantityValue) <= 0)
                {
                    return;
                }

                ArrayList<String> arrList = medicineInReceiptList.get(rowChanged);//.get(colChanged -1) = tableValue;
                long oldQuantityValue = Long.parseLong(arrList.get(colChanged));
                arrList.set(colChanged , quantityValue);
                float medicinePrice = Float.parseFloat(arrList.get(3));

                String totalPriceText = totalPriceTextField.getText();
                int decimalIndex = totalPriceText.indexOf(".");
                if(decimalIndex + 2 < totalPriceText.length())
                {
                    totalPriceText = totalPriceText.substring(0, decimalIndex + 2);
                }

                float mTotalPrice = Float.parseFloat(totalPriceText);
                mTotalPrice = mTotalPrice + (float)(medicinePrice * (float)(Long.parseLong(quantityValue) - oldQuantityValue));
                float mOverallDiscount = (mTotalPrice * ((overallDiscountPercentage)/100));

                receiptJFrameOverallDiscount.setText(Float.toString(mOverallDiscount));
                //receiptJFrameOverallDiscount.disable();
                totalPriceTextField.disable();
                totalPriceTextField.setText(Float.toString(mTotalPrice  - mOverallDiscount));
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
      }
    });    

    }

    private void cancelButtonPressedOnForm() {
        clearButtonPressedOnForm();

        currentOnTopFrame.hide();
        currentOnTopFrame = ReceiptJFrame;
        ReceiptJFrame.show();
    }

    public static void enterPressedOnUpdateStockFrame()
    {
        String medicineName = MainFrame.updateStockMedicineNameDropDownField.getSelectedItem().toString();
        int medicineNameEntryIndex = updateStockMedicineNameDropDownField.founds.get(medicineName);

        int startIndex = medicineName.indexOf("[");
        int secondIndex = medicineName.indexOf("[", startIndex);
        int secondEndIndex = medicineName.indexOf("]", secondIndex);

        if(1 == medicineNameEntryIndex)     // Medicine name is the first name in the search result entry
        {
            medicineName = medicineName.substring(0, startIndex - 1);
        }
        else if(2 == medicineNameEntryIndex || 3 == medicineNameEntryIndex)
        {
            medicineName = medicineName.substring(secondIndex + 2, secondEndIndex - 1);
        }

        Medicine medicineObject = Saad.classmodel.SaadMedicineSaleSystem.medicineList.get(medicineName);
        // Get sales price of medicine
        if(null == medicineObject)
        {
            JOptionPane.showMessageDialog(null, "Medicine is not in stock");
            return;
        }

        MainFrame.updateStockMedicineNameTextField.setText(medicineObject.medicineName);
        MainFrame.updateStockMedicineGenericNameTextField.setText(medicineObject.genericName);
        MainFrame.updateStockCompanyNameTextField.setText(medicineObject.companyName);
        MainFrame.updateStockDistributorNameTextField.setText(medicineObject.distributorName);
        MainFrame.updateStockRackNumberTextField.setText(medicineObject.rackNumber);
        MainFrame.updateStockSalePriceTextField.setText(Float.toString(medicineObject.salesPrice));
        MainFrame.updateStockManufacturingDateTextField.setDate(medicineObject.manfacturingDate);//setText(medicineObject.manfacturingDate.toString());
        MainFrame.updateStockExpiryDateTextField.setDate(medicineObject.expiryDate);//setText(medicineObject.expiryDate.toString());
        MainFrame.updateStockCostPriceTextField.setText(Float.toString(medicineObject.costPrice));
        MainFrame.updateStockQuantityTextField.setText(Long.toString(medicineObject.quantity));
    }
    
    public static void enterPressedOnReceiptFrame()
    {
        MainFrame.state = MainFrame.MEDICINE_ADDED;
        String medicineName = MainFrame.medicineNameDropDownField.getSelectedItem().toString();
        int medicineNameEntryIndex = medicineNameDropDownField.founds.get(medicineName);

        int startIndex = medicineName.indexOf("[");
        int secondIndex = medicineName.indexOf("[", startIndex);
        int secondEndIndex = medicineName.indexOf("]", secondIndex);

        if(1 == medicineNameEntryIndex)     // Medicine name is the first name in the search result entry
        {
            medicineName = medicineName.substring(0, startIndex - 1);
        }
        else if(2 == medicineNameEntryIndex || 3 == medicineNameEntryIndex)
        {
            medicineName = medicineName.substring(secondIndex + 2, secondEndIndex - 1);
        }

        float medicinePrice = 0;
        Medicine medicineObject = Saad.classmodel.SaadMedicineSaleSystem.medicineList.get(medicineName);

        if(medicineObject == null)
        {
            JOptionPane.showMessageDialog(null, "Medicine name is incorrect. Please select from drop down or type again.");
            MainFrame.state = MainFrame.RECEIPT_IDLE;
            return;
        }

        if(medicineObject.quantity <= 0)
        {
            JOptionPane.showMessageDialog(null, "Medicine is finished in stock. Please update stock.");
            MainFrame.state = MainFrame.RECEIPT_IDLE;            
            return;
        }

        // Get sales price of medicine
        if(null != medicineObject)
        {
            medicinePrice = medicineObject.salesPrice;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Medicine is not in stock");
            MainFrame.state = MainFrame.RECEIPT_IDLE;            
            return;
        }

        for(int i = 0; i< MainFrame.medicineInReceiptList.size();i++)
        {
            String newMedicineName = MainFrame.medicineInReceiptList.get(i).get(0);
            if(newMedicineName.equals(medicineName))
            {
                JOptionPane.showMessageDialog(null, "This medicine has already been added. Please use quantity to specify additional amount");
                MainFrame.state = MainFrame.ITEM_SELECTED;               
                return;
            }
        }

        ArrayList<String> medicineInfo = new ArrayList<String>();
        medicineInfo.add(medicineName);
        medicineInfo.add(medicineObject.companyName);
        medicineInfo.add(medicineObject.rackNumber);
        medicineInfo.add(Float.toString(medicinePrice));
        medicineInfo.add(Float.toString(medicineObject.discount));
        medicineInfo.add(Long.toString(1)); //For quantity
        MainFrame.medicineInReceiptList.add(medicineInfo);

        // TODO : Table data should be incremented in configurable chunks
        if(MainFrame.medicineInReceiptList.size() > MainFrame.receiptTable.getRowCount())
        {
            MainFrame.generateTableModelForTableData(MainFrame.receiptTable, MainFrame.medicineInReceiptList);
        }

        float receiptPrice = 0;
        for(int i = 0; i< MainFrame.medicineInReceiptList.size();i++)
        {
            for(int j = 0 ; j < MainFrame.medicineInReceiptList.get(0).size();j++)
            {
                MainFrame.receiptTable.setValueAt(MainFrame.medicineInReceiptList.get(i).get(j), i, j);
                // Get medicine price and add to receipt price
                // TODO Devize some logic to avoid hard-coded numbers
                if(j == 3)
                {
                    receiptPrice = receiptPrice + Float.parseFloat(MainFrame.medicineInReceiptList.get(i).get(j));
                }
            }
        }
        MainFrame.receiptTable.repaint();

        MainFrame.overallDiscount = (float) (receiptPrice * (((float)MainFrame.overallDiscountPercentage)/100));
        MainFrame.receiptJFrameOverallDiscount.setText(Float.toString(MainFrame.overallDiscount));
        MainFrame.totalPriceTextField.disable();
        MainFrame.totalPriceTextField.setText(Float.toString(receiptPrice  - MainFrame.overallDiscount));
        MainFrame.medicineNameDropDownField.removeAllItems();
        MainFrame.state = MainFrame.RECEIPT_IDLE;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainToolbar = new javax.swing.JToolBar();
        mainDesktopPane = new javax.swing.JDesktopPane();
        ReceiptJFrame = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        receiptTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalPriceTextField = new javax.swing.JTextField();
        receiptJFrameOverallDiscount = new javax.swing.JTextField();
        printReceipt = new javax.swing.JButton();
        removeSelectedItemButton = new javax.swing.JButton();
        mainReciptChangeQuantityButton = new javax.swing.JButton();
        clearMedicineDropDownButton = new javax.swing.JButton();
        medicineNameDropDownField = new Saad.gui.AutocompleteJComboBox(searchable);
        orderOnline = new javax.swing.JButton();
        TotalSalesInDateRange = new javax.swing.JInternalFrame();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        showReceiptsButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        totalSalesInDateRangeReceiptTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        totalSalesInDateRangeCancelButton = new javax.swing.JButton();
        salesReceiptTotalTextField = new javax.swing.JTextField();
        totalSalesInDateRangeClearButton = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        salesReceiptNetProfitTextField = new javax.swing.JTextField();
        receiptsStartDateTextField = new com.toedter.calendar.JDateChooser();
        receiptsEndDateTextField = new com.toedter.calendar.JDateChooser();
        TotalMedicineSaleJFrame = new javax.swing.JInternalFrame();
        jLabel16 = new javax.swing.JLabel();
        totalMedicineSaleMedicineNameTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        showMedicineSalesButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        totalmedicineSaleTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        totalMedicineSaleTextField = new javax.swing.JTextField();
        totalMedicineSaleNetProfitTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        showMedicineSalesCancelButton = new javax.swing.JButton();
        showMedicineSalesClearButton = new javax.swing.JButton();
        totalMedicineSaleStartDateTextField = new com.toedter.calendar.JDateChooser();
        totalMedicineSaleEndDateTextField = new com.toedter.calendar.JDateChooser();
        TotalCompanySaleJFrame = new javax.swing.JInternalFrame();
        jLabel19 = new javax.swing.JLabel();
        companySalesDistributorTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        companySalesCompanyTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        companySalesMedicineNameTextField = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        totalCompanySaleTable = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        companySalesTotalSalesTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        companySalesNetProfitTextField = new javax.swing.JTextField();
        showComanySalesShowSales = new javax.swing.JButton();
        showCompanySalesCancelButton = new javax.swing.JButton();
        showCompanySalesClearButton = new javax.swing.JButton();
        TotalSalesManSalesJFrame = new javax.swing.JInternalFrame();
        jLabel26 = new javax.swing.JLabel();
        totalSalesmanSaleSalemanNameTextField = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        totalSalesManSaleTable = new javax.swing.JTable();
        totalSalemanSaleTotalSalesTextField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        showSalesmanSaleShowSalesButton = new javax.swing.JButton();
        showSalesmanSaleCancelButton = new javax.swing.JButton();
        showSalesmanSaleClearButton = new javax.swing.JButton();
        totalSalesmanSaleStartDateTextField = new com.toedter.calendar.JDateChooser();
        totalSalemanSalesEndDateTextField = new com.toedter.calendar.JDateChooser();
        TotalReturnsByDateJFrame = new javax.swing.JInternalFrame();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        totalReturnsShowReturnsButton = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        totalReturnsByDateReturnsTable = new javax.swing.JTable();
        totalReturnsTotalTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        totalReturnsCancelButton = new javax.swing.JButton();
        totalReturnsClearButton = new javax.swing.JButton();
        totalReturnsStartDateTextField = new com.toedter.calendar.JDateChooser();
        totalReturnsEndDateTextField = new com.toedter.calendar.JDateChooser();
        AddStockJFrame = new javax.swing.JInternalFrame();
        medicineLabelTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addStockRackNoTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        salesPriceTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addStockCancelButton = new javax.swing.JButton();
        saveMedicineInStock = new javax.swing.JButton();
        costPriceTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        addStockDistributorNameTextField = new javax.swing.JTextField();
        addStockCompanyNameTextField = new javax.swing.JTextField();
        addStockLoosePurchasePerItemRadioButton = new javax.swing.JRadioButton();
        addStockLoosePurchasePerBoxRadioButton = new javax.swing.JRadioButton();
        addStockLoosePurchasePerPackRadioButton = new javax.swing.JRadioButton();
        jLabel62 = new javax.swing.JLabel();
        addStockItemsPerPackBoxTextField = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        medicineGenericNameTextField = new javax.swing.JTextField();
        manufacturingDateTextField = new com.toedter.calendar.JDateChooser();
        expiryDateTextField = new com.toedter.calendar.JDateChooser();
        TotalReturnsByCompanyJFrame = new javax.swing.JInternalFrame();
        jLabel36 = new javax.swing.JLabel();
        totalReturnsByCompanyDistributorNameTextField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        totalReturnsByCompanyCompanyNameTextField = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        totalReturnsByCompanyMedicineNameTextField = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        totalReturnsByCompanyReturnsTable = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        totalReturnsByCompanyReturnsTextField = new javax.swing.JTextField();
        totalReturnsByCompanyShowReturnsButton = new javax.swing.JButton();
        totalReturnsByCompanyCancelButton = new javax.swing.JButton();
        totalReturnsByCompanyClearButton = new javax.swing.JButton();
        TotalReturnsSalesManJFrame = new javax.swing.JInternalFrame();
        jLabel35 = new javax.swing.JLabel();
        totalReturnsBySalesmanNameTextField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        totalReturnsBySalesmanTable = new javax.swing.JTable();
        totalReturnsBySalesmanReturnTextField = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        totalReturnsBySalesManShowReturnsButton = new javax.swing.JButton();
        totalReturnsBySalesManCancelButton = new javax.swing.JButton();
        totalReturnsBySalesManClearButton = new javax.swing.JButton();
        totalReturnsBySalesManStartDateTextField = new com.toedter.calendar.JDateChooser();
        totalReturnsBySalesmanEndDateTextField = new com.toedter.calendar.JDateChooser();
        ShortItemsCompanyJFrame = new javax.swing.JInternalFrame();
        jLabel45 = new javax.swing.JLabel();
        shortItemsCompanyDistributorNameTextField = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        shortItemsCompanyNameTextField = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        shortItemsCompanySpinner = new javax.swing.JSpinner();
        shortItemsByCompanyShowShortMedicineButton = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        shortItemsByCompanyShortItemsTable = new javax.swing.JTable();
        shortItemsByCompanyShowShortMedicineClearButton = new javax.swing.JButton();
        shortItemsByCompanyShowShortMedicineCancelButton = new javax.swing.JButton();
        ShortItemsOverallJFrame = new javax.swing.JInternalFrame();
        jLabel49 = new javax.swing.JLabel();
        shortItemsOverallSpinner = new javax.swing.JSpinner();
        shortItemsOverallShowShortMedicineButton = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        shortItemsOverAllMedicineTable = new javax.swing.JTable();
        shortItemsOverallCancelButton = new javax.swing.JButton();
        shortItemsOverallClearButton = new javax.swing.JButton();
        ShortItemsOnRackJFrame = new javax.swing.JInternalFrame("", true ,true, true, true);
        jLabel52 = new javax.swing.JLabel();
        shortItemsOnRackDistributorNameTextField = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        shortItemsOnRackCompanyNameTextField = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        shortItemsByRackSpinner = new javax.swing.JSpinner();
        shortItemsOnRackShowShortMedicineButton = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        shortItemsOnRackTable = new javax.swing.JTable();
        jLabel55 = new javax.swing.JLabel();
        shortItemsRackNoTextField = new javax.swing.JTextField();
        shortItemsOnRackClearButton = new javax.swing.JButton();
        shortItemsOnRackCancelButton = new javax.swing.JButton();
        UpdateStockJFrame = new javax.swing.JInternalFrame();
        jLabel44 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        updateStockRackNumberTextField = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        updateStockSalePriceTextField = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        updateStockCancelButton = new javax.swing.JButton();
        updateStockJFrameSaveMedicineInStockButton = new javax.swing.JButton();
        updateStockCostPriceTextField = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        updateStockDistributorNameTextField = new javax.swing.JTextField();
        updateStockCompanyNameTextField = new javax.swing.JTextField();
        updateStockMedicineNameTextField = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        updateStockMedicineGenericNameTextField = new javax.swing.JTextField();
        updateStockLoosePurchasePerItemRadioButton = new javax.swing.JRadioButton();
        updateStockLoosePurchasePerPackRadioButton = new javax.swing.JRadioButton();
        updateStockLoosePurchasePerBoxRadioButton = new javax.swing.JRadioButton();
        jLabel71 = new javax.swing.JLabel();
        updateStockQuantityTextField = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        updateStockItemsPerPackBoxTextField = new javax.swing.JTextField();
        updateStockMedicineNameDropDownField = new Saad.gui.AutocompleteJComboBox();
        updateStockManufacturingDateTextField = new com.toedter.calendar.JDateChooser();
        updateStockExpiryDateTextField = new com.toedter.calendar.JDateChooser();
        AddUserJFrame = new javax.swing.JInternalFrame();
        addUserUserLoginNameTextField = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        addUserUserPasswordTextField = new javax.swing.JPasswordField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        addUserReenterUserPasswordTextField = new javax.swing.JPasswordField();
        addUserUserGroups = new javax.swing.JComboBox();
        jLabel67 = new javax.swing.JLabel();
        addUserSaveUserButton = new javax.swing.JButton();
        addUserUserNameTextField = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        addUserCancelButton = new javax.swing.JButton();
        addUserClearButton = new javax.swing.JButton();
        AddUserGroupJFrame = new javax.swing.JInternalFrame();
        jLabel69 = new javax.swing.JLabel();
        addUserGroupAddUserGroupButton = new javax.swing.JButton();
        addUserGroupUserGroupNameTextField = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addUserGroupPrivilesgesList = new javax.swing.JList();
        addUserGroupsCancelButton = new javax.swing.JButton();
        RemoveUserJFrame = new javax.swing.JInternalFrame();
        removeUserButton = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        removeUserCancelButton = new javax.swing.JButton();
        usersToRemoveCombo = new javax.swing.JComboBox();
        RemoveUserGroupJFrame = new javax.swing.JInternalFrame();
        removeUserGroupButton = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        removeGroupsCancelButton = new javax.swing.JButton();
        userGroupsToRemoveCombo = new javax.swing.JComboBox();
        TotalReturnsByMedicineJFrame = new javax.swing.JInternalFrame();
        jLabel31 = new javax.swing.JLabel();
        totalReturnsByMedicineMedicineNameTextField = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        totalReturnsByMedicineClearButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        totalReturnsByMedicineReturnsTable = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        totalMedicineReturnsByMedicineReturnsTextField = new javax.swing.JTextField();
        totalReturnsByMedicineCancelButton = new javax.swing.JButton();
        totalReturnsByMedicineShowReturnsButton = new javax.swing.JButton();
        totalReturnsByMedicineStartDateTextField = new com.toedter.calendar.JDateChooser();
        totalReturnsByMedicineEndDateTextField = new com.toedter.calendar.JDateChooser();
        EasyStockAdditionJFrame = new javax.swing.JInternalFrame();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        distributorNameComboBox = new javax.swing.JComboBox();
        jLabel89 = new javax.swing.JLabel();
        easyStockAdditionMedicineNameComboBox = new javax.swing.JComboBox();
        jLabel90 = new javax.swing.JLabel();
        easyStockAdditionGenericNameTextField = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        easyStockAdditionTradePriceTextField = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        easyStockAdditionRackNumberTextField = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        easyStockAdditionRetailPriceTextField = new javax.swing.JTextField();
        easyStockAdditionRadioButtonLoosePurchasePerPack = new javax.swing.JRadioButton();
        easyStockAdditionRadioButtonLoosePurchasePerBox = new javax.swing.JRadioButton();
        easyAddStockAdditionRadioButtonLoosePurchasePerItem = new javax.swing.JRadioButton();
        jLabel96 = new javax.swing.JLabel();
        easyStockAdditionItemsPerPackOrBoxTextField = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        easyStockAdditionNumberOfItemsTextField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        easyStockAdditionCompanyNameComboBox = new javax.swing.JComboBox();
        easyStockAdditionmanufacturingDateTextField = new com.toedter.calendar.JDateChooser();
        easyStockAdditionExpiryDateTextField = new com.toedter.calendar.JDateChooser();
        mainMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        printReceiptMenu = new javax.swing.JMenuItem();
        configureDiscountMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        addStock = new javax.swing.JMenuItem();
        updateStockMenu = new javax.swing.JMenuItem();
        processReturnMenu = new javax.swing.JMenuItem();
        easyStockMenuItem = new javax.swing.JMenuItem();
        salesInDateRangeMenu = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        totalSalesInDateRangeMenu = new javax.swing.JMenuItem();
        totalMedicineSaleMenu = new javax.swing.JMenuItem();
        totalCompanySaleMenu = new javax.swing.JMenuItem();
        totalSalesmanSaleMenu = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        totalReturnsByDateMenu = new javax.swing.JMenuItem();
        totalReturnsByMedicineMenu = new javax.swing.JMenuItem();
        totalReturnsByCompanyMenu = new javax.swing.JMenuItem();
        totalReturnsBySalesmanMenu = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        overallShortItemsMenu = new javax.swing.JMenuItem();
        shortItemsByCompanyMenu = new javax.swing.JMenuItem();
        shortItemsOnRackMenu = new javax.swing.JMenuItem();
        securityMenu = new javax.swing.JMenu();
        addRemoveUsersMenu = new javax.swing.JMenuItem();
        addRemoveUserGroupsMenu = new javax.swing.JMenuItem();
        removeUsersMenu = new javax.swing.JMenuItem();
        removeUserGroupsMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(appIcon);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        mainToolbar.setRollover(true);

        ReceiptJFrame.setBorder(null);
        ReceiptJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ReceiptJFrame.setTitle("receiptjframe");
        ReceiptJFrame.setAlignmentX(0.0F);
        ReceiptJFrame.setAlignmentY(0.0F);
        ReceiptJFrame.setVisible(true);
        ReceiptJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                ReceiptJFrameComponentShown(evt);
            }
        });

        receiptTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        receiptTable.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        receiptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        receiptTable.setName("receiptTable"); // NOI18N
        receiptTable.setRowHeight(25);
        receiptTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receiptTableMouseClicked(evt);
            }
        });
        receiptTable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                receiptTableInputMethodTextChanged(evt);
            }
        });
        receiptTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                receiptTableKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(receiptTable);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Overall Discount");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Total");

        totalPriceTextField.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        totalPriceTextField.setDisabledTextColor(new java.awt.Color(255, 0, 0));

        receiptJFrameOverallDiscount.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        receiptJFrameOverallDiscount.setDisabledTextColor(new java.awt.Color(0, 204, 51));
        receiptJFrameOverallDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptJFrameOverallDiscountActionPerformed(evt);
            }
        });

        printReceipt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        printReceipt.setText("PRINT RECEIPT");
        printReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptActionPerformed(evt);
            }
        });

        removeSelectedItemButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        removeSelectedItemButton.setText("REMOVE SELECTED ITEM");
        removeSelectedItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSelectedItemButtonActionPerformed(evt);
            }
        });

        mainReciptChangeQuantityButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mainReciptChangeQuantityButton.setText("CHANGE QUANTITY");
        mainReciptChangeQuantityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainReciptChangeQuantityButtonActionPerformed(evt);
            }
        });

        clearMedicineDropDownButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        clearMedicineDropDownButton.setText("Clear");
        clearMedicineDropDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearMedicineDropDownButtonActionPerformed(evt);
            }
        });

        orderOnline.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        orderOnline.setText("ORDER ONLINE");
        orderOnline.setName(""); // NOI18N
        orderOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderOnlineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReceiptJFrameLayout = new javax.swing.GroupLayout(ReceiptJFrame.getContentPane());
        ReceiptJFrame.getContentPane().setLayout(ReceiptJFrameLayout);
        ReceiptJFrameLayout.setHorizontalGroup(
            ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
                            .addComponent(medicineNameDropDownField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240))))
                    .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                        .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                                .addGap(663, 663, 663)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReceiptJFrameLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(orderOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(printReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(totalPriceTextField)
                                .addComponent(receiptJFrameOverallDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(removeSelectedItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(mainReciptChangeQuantityButton, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(clearMedicineDropDownButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1192, Short.MAX_VALUE))
        );
        ReceiptJFrameLayout.setVerticalGroup(
            ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearMedicineDropDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineNameDropDownField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(removeSelectedItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mainReciptChangeQuantityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(receiptJFrameOverallDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ReceiptJFrameLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)))
                .addGap(28, 28, 28)
                .addGroup(ReceiptJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(883, Short.MAX_VALUE))
        );

        TotalSalesInDateRange.setBorder(null);
        TotalSalesInDateRange.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalSalesInDateRange.setTitle("totalsalesindaterange");
        TotalSalesInDateRange.setAlignmentX(0.0F);
        TotalSalesInDateRange.setAlignmentY(0.0F);
        TotalSalesInDateRange.setVisible(true);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Start Date");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("End Date");

        showReceiptsButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showReceiptsButton.setText("SHOW RECEIPTS");
        showReceiptsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showReceiptsButtonActionPerformed(evt);
            }
        });

        totalSalesInDateRangeReceiptTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalSalesInDateRangeReceiptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sales ID", "Sales Date", "Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalSalesInDateRangeReceiptTable.setName("totalSalesInDateRangeReceiptTable"); // NOI18N
        totalSalesInDateRangeReceiptTable.setRowHeight(25);
        totalSalesInDateRangeReceiptTable.getTableHeader().setReorderingAllowed(false);
        totalSalesInDateRangeReceiptTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalSalesInDateRangeReceiptTableMouseClicked(evt);
            }
        });
        totalSalesInDateRangeReceiptTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalSalesInDateRangeReceiptTableKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(totalSalesInDateRangeReceiptTable);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Total Sales");

        totalSalesInDateRangeCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalSalesInDateRangeCancelButton.setText("CANCEL");
        totalSalesInDateRangeCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSalesInDateRangeCancelButtonActionPerformed(evt);
            }
        });

        salesReceiptTotalTextField.setEditable(false);
        salesReceiptTotalTextField.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        salesReceiptTotalTextField.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        salesReceiptTotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesReceiptTotalTextFieldActionPerformed(evt);
            }
        });

        totalSalesInDateRangeClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalSalesInDateRangeClearButton.setText("CLEAR");
        totalSalesInDateRangeClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSalesInDateRangeClearButtonActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel74.setText("Net Profit");

        salesReceiptNetProfitTextField.setEditable(false);
        salesReceiptNetProfitTextField.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        salesReceiptNetProfitTextField.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        salesReceiptNetProfitTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesReceiptNetProfitTextFieldActionPerformed(evt);
            }
        });

        //javax.swing.plaf.basic.BasicInternalFrameUI bDailySalesJFrame = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalSalesInDateRange.getUI();
        //bDailySalesJFrame.setNorthPane(null);

        javax.swing.GroupLayout TotalSalesInDateRangeLayout = new javax.swing.GroupLayout(TotalSalesInDateRange.getContentPane());
        TotalSalesInDateRange.getContentPane().setLayout(TotalSalesInDateRangeLayout);
        TotalSalesInDateRangeLayout.setHorizontalGroup(
            TotalSalesInDateRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                .addGroup(TotalSalesInDateRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                        .addContainerGap(1261, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salesReceiptTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salesReceiptNetProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(receiptsStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(receiptsEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(260, 260, 260))
            .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                .addGroup(TotalSalesInDateRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                        .addGap(587, 587, 587)
                        .addComponent(showReceiptsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                        .addGap(445, 445, 445)
                        .addComponent(totalSalesInDateRangeClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(totalSalesInDateRangeCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1531, Short.MAX_VALUE))
            .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        TotalSalesInDateRangeLayout.setVerticalGroup(
            TotalSalesInDateRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalSalesInDateRangeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(TotalSalesInDateRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(receiptsStartDateTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(receiptsEndDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(showReceiptsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TotalSalesInDateRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesReceiptTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesReceiptNetProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TotalSalesInDateRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalSalesInDateRangeCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalSalesInDateRangeClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TotalMedicineSaleJFrame.setBorder(null);
        TotalMedicineSaleJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalMedicineSaleJFrame.setTitle("totalmedicinesalejframe");
        TotalMedicineSaleJFrame.setAlignmentX(0.0F);
        TotalMedicineSaleJFrame.setAlignmentY(0.0F);
        TotalMedicineSaleJFrame.setVisible(true);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Medicine Name");

        totalMedicineSaleMedicineNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMedicineSaleMedicineNameTextFieldActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("End Date");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Start Date");

        showMedicineSalesButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showMedicineSalesButton.setText("SHOW SALES");
        showMedicineSalesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMedicineSalesButtonActionPerformed(evt);
            }
        });

        totalmedicineSaleTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalmedicineSaleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sales ID", "Sales Date", "Medicine Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalmedicineSaleTable.setName("totalmedicineSaleTable"); // NOI18N
        totalmedicineSaleTable.setRowHeight(25);
        totalmedicineSaleTable.getTableHeader().setReorderingAllowed(false);
        totalmedicineSaleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalmedicineSaleTableMouseClicked(evt);
            }
        });
        totalmedicineSaleTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalmedicineSaleTableKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(totalmedicineSaleTable);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Total Sales");

        totalMedicineSaleTextField.setEditable(false);
        totalMedicineSaleTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMedicineSaleTextFieldActionPerformed(evt);
            }
        });

        totalMedicineSaleNetProfitTextField.setEditable(false);
        totalMedicineSaleNetProfitTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMedicineSaleNetProfitTextFieldActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Net Profit");

        showMedicineSalesCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showMedicineSalesCancelButton.setText("CANCEL");
        showMedicineSalesCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMedicineSalesCancelButtonActionPerformed(evt);
            }
        });

        showMedicineSalesClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showMedicineSalesClearButton.setText("CLEAR");
        showMedicineSalesClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMedicineSalesClearButtonActionPerformed(evt);
            }
        });

        //javax.swing.plaf.basic.BasicInternalFrameUI bTotalMedicine = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalMedicineSaleJFrame.getUI();
        //bTotalMedicine.setNorthPane(null);

        javax.swing.GroupLayout TotalMedicineSaleJFrameLayout = new javax.swing.GroupLayout(TotalMedicineSaleJFrame.getContentPane());
        TotalMedicineSaleJFrame.getContentPane().setLayout(TotalMedicineSaleJFrameLayout);
        TotalMedicineSaleJFrameLayout.setHorizontalGroup(
            TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                        .addGap(595, 595, 595)
                        .addComponent(showMedicineSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                                .addComponent(totalMedicineSaleMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(totalMedicineSaleStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalMedicineSaleEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                                        .addGap(606, 606, 606)
                                        .addComponent(totalMedicineSaleNetProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalMedicineSaleJFrameLayout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                                                .addComponent(showMedicineSalesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(58, 58, 58)
                                                .addComponent(showMedicineSalesCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                                                .addComponent(totalMedicineSaleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(240, 240, 240)))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(1257, Short.MAX_VALUE))
        );
        TotalMedicineSaleJFrameLayout.setVerticalGroup(
            TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalMedicineSaleMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(totalMedicineSaleStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalMedicineSaleJFrameLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(totalMedicineSaleEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(showMedicineSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalMedicineSaleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalMedicineSaleNetProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(TotalMedicineSaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showMedicineSalesCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showMedicineSalesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(891, Short.MAX_VALUE))
        );

        TotalCompanySaleJFrame.setBorder(null);
        TotalCompanySaleJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalCompanySaleJFrame.setTitle("totalcompanysalejframe");
        TotalCompanySaleJFrame.setAlignmentX(0.0F);
        TotalCompanySaleJFrame.setAlignmentY(0.0F);
        TotalCompanySaleJFrame.setVisible(true);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Select Distributor");

        companySalesDistributorTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companySalesDistributorTextFieldActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Select Company");

        companySalesCompanyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companySalesCompanyTextFieldActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("Select Medicine");

        companySalesMedicineNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companySalesMedicineNameTextFieldActionPerformed(evt);
            }
        });

        totalCompanySaleTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalCompanySaleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sales ID", "Sales Date", "Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalCompanySaleTable.setName("totalCompanySaleTable"); // NOI18N
        totalCompanySaleTable.setRowHeight(25);
        totalCompanySaleTable.getTableHeader().setReorderingAllowed(false);
        totalCompanySaleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalCompanySaleTableMouseClicked(evt);
            }
        });
        totalCompanySaleTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalCompanySaleTableKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(totalCompanySaleTable);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Total Sales");

        companySalesTotalSalesTextField.setEditable(false);
        companySalesTotalSalesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companySalesTotalSalesTextFieldActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Net Profit");

        companySalesNetProfitTextField.setEditable(false);
        companySalesNetProfitTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companySalesNetProfitTextFieldActionPerformed(evt);
            }
        });

        showComanySalesShowSales.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showComanySalesShowSales.setText("SHOW SALES");
        showComanySalesShowSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showComanySalesShowSalesActionPerformed(evt);
            }
        });

        showCompanySalesCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showCompanySalesCancelButton.setText("CANCEL");
        showCompanySalesCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCompanySalesCancelButtonActionPerformed(evt);
            }
        });

        showCompanySalesClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showCompanySalesClearButton.setText("CLEAR");
        showCompanySalesClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCompanySalesClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TotalCompanySaleJFrameLayout = new javax.swing.GroupLayout(TotalCompanySaleJFrame.getContentPane());
        TotalCompanySaleJFrame.getContentPane().setLayout(TotalCompanySaleJFrameLayout);
        TotalCompanySaleJFrameLayout.setHorizontalGroup(
            TotalCompanySaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalCompanySaleJFrameLayout.createSequentialGroup()
                .addGroup(TotalCompanySaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalCompanySaleJFrameLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(companySalesDistributorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companySalesCompanyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companySalesMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalCompanySaleJFrameLayout.createSequentialGroup()
                        .addGap(617, 617, 617)
                        .addComponent(showComanySalesShowSales, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalCompanySaleJFrameLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addGroup(TotalCompanySaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TotalCompanySaleJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(companySalesTotalSalesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(companySalesNetProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalCompanySaleJFrameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(showCompanySalesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(showCompanySalesCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(358, 358, 358))
        );
        TotalCompanySaleJFrameLayout.setVerticalGroup(
            TotalCompanySaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalCompanySaleJFrameLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(TotalCompanySaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companySalesDistributorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companySalesCompanyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companySalesMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(showComanySalesShowSales, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TotalCompanySaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companySalesTotalSalesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companySalesNetProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TotalCompanySaleJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showCompanySalesCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showCompanySalesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //javax.swing.plaf.basic.BasicInternalFrameUI bTotalCompanySale = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalCompanySaleJFrame.getUI();
        //bTotalCompanySale.setNorthPane(null);

        TotalSalesManSalesJFrame.setBorder(null);
        TotalSalesManSalesJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalSalesManSalesJFrame.setTitle("totalsalesmansalesjframe");
        TotalSalesManSalesJFrame.setAlignmentX(0.0F);
        TotalSalesManSalesJFrame.setAlignmentY(0.0F);
        TotalSalesManSalesJFrame.setVisible(true);

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("Select Salesman Name");

        totalSalesmanSaleSalemanNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSalesmanSaleSalemanNameTextFieldActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("Select Start Date");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setText("Select End Date");

        totalSalesManSaleTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalSalesManSaleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sales ID", "Sales Date", "Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalSalesManSaleTable.setName("totalSalesManSaleTable"); // NOI18N
        totalSalesManSaleTable.setRowHeight(25);
        totalSalesManSaleTable.getTableHeader().setReorderingAllowed(false);
        totalSalesManSaleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalSalesManSaleTableMouseClicked(evt);
            }
        });
        totalSalesManSaleTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalSalesManSaleTableKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(totalSalesManSaleTable);

        totalSalemanSaleTotalSalesTextField.setEditable(false);
        totalSalemanSaleTotalSalesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSalemanSaleTotalSalesTextFieldActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setText("Total Sales");

        showSalesmanSaleShowSalesButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showSalesmanSaleShowSalesButton.setText("SHOW SALESMAN SALE");
        showSalesmanSaleShowSalesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSalesmanSaleShowSalesButtonActionPerformed(evt);
            }
        });

        showSalesmanSaleCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showSalesmanSaleCancelButton.setText("CANCEL");
        showSalesmanSaleCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSalesmanSaleCancelButtonActionPerformed(evt);
            }
        });

        showSalesmanSaleClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showSalesmanSaleClearButton.setText("CLEAR");
        showSalesmanSaleClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSalesmanSaleClearButtonActionPerformed(evt);
            }
        });

        //javax.swing.plaf.basic.BasicInternalFrameUI bTotalSalesman = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalSalesManSalesJFrame.getUI();
        //bTotalSalesman.setNorthPane(null);

        javax.swing.GroupLayout TotalSalesManSalesJFrameLayout = new javax.swing.GroupLayout(TotalSalesManSalesJFrame.getContentPane());
        TotalSalesManSalesJFrame.getContentPane().setLayout(TotalSalesManSalesJFrameLayout);
        TotalSalesManSalesJFrameLayout.setHorizontalGroup(
            TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalSalesmanSaleSalemanNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalSalesmanSaleStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalSalemanSalesEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalSalesManSalesJFrameLayout.createSequentialGroup()
                        .addGap(0, 1354, Short.MAX_VALUE)
                        .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalSalesManSalesJFrameLayout.createSequentialGroup()
                                .addComponent(showSalesmanSaleClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(showSalesmanSaleCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(229, 229, 229))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalSalesManSalesJFrameLayout.createSequentialGroup()
                                .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                                            .addComponent(jLabel29)
                                            .addGap(45, 45, 45)
                                            .addComponent(totalSalemanSaleTotalSalesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                                        .addGap(393, 393, 393)
                                        .addComponent(showSalesmanSaleShowSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(148, 148, 148))))))
        );
        TotalSalesManSalesJFrameLayout.setVerticalGroup(
            TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalSalesmanSaleSalemanNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(totalSalesmanSaleStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(TotalSalesManSalesJFrameLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(totalSalemanSalesEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addComponent(showSalesmanSaleShowSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalSalemanSaleTotalSalesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TotalSalesManSalesJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showSalesmanSaleCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showSalesmanSaleClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TotalReturnsByDateJFrame.setBorder(null);
        TotalReturnsByDateJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalReturnsByDateJFrame.setTitle("totalreturnsbydatejframe");
        TotalReturnsByDateJFrame.setAlignmentX(0.0F);
        TotalReturnsByDateJFrame.setAlignmentY(0.0F);
        TotalReturnsByDateJFrame.setVisible(true);

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setText("Start Date");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("End Date");

        totalReturnsShowReturnsButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsShowReturnsButton.setText("SHOW RETURNS");
        totalReturnsShowReturnsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsShowReturnsButtonActionPerformed(evt);
            }
        });

        totalReturnsByDateReturnsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalReturnsByDateReturnsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalReturnsByDateReturnsTable.setName("totalReturnsByDateReturnsTable"); // NOI18N
        totalReturnsByDateReturnsTable.setRowHeight(25);
        totalReturnsByDateReturnsTable.getTableHeader().setReorderingAllowed(false);
        totalReturnsByDateReturnsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalReturnsByDateReturnsTableMouseClicked(evt);
            }
        });
        totalReturnsByDateReturnsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalReturnsByDateReturnsTableKeyTyped(evt);
            }
        });
        jScrollPane7.setViewportView(totalReturnsByDateReturnsTable);

        totalReturnsTotalTextField.setEditable(false);
        totalReturnsTotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsTotalTextFieldActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setText("Total Returns");

        totalReturnsCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsCancelButton.setText("CANCEL");
        totalReturnsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsCancelButtonActionPerformed(evt);
            }
        });

        totalReturnsClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsClearButton.setText("CLEAR");
        totalReturnsClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsClearButtonActionPerformed(evt);
            }
        });

        //javax.swing.plaf.basic.BasicInternalFrameUI bTotalReturns = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalReturnsByDateJFrame.getUI();
        //bTotalReturns.setNorthPane(null);

        javax.swing.GroupLayout TotalReturnsByDateJFrameLayout = new javax.swing.GroupLayout(TotalReturnsByDateJFrame.getContentPane());
        TotalReturnsByDateJFrame.getContentPane().setLayout(TotalReturnsByDateJFrameLayout);
        TotalReturnsByDateJFrameLayout.setHorizontalGroup(
            TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalReturnsByDateJFrameLayout.createSequentialGroup()
                .addGroup(TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalReturnsByDateJFrameLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TotalReturnsByDateJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalReturnsStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(413, 413, 413)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalReturnsEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))
                            .addGroup(TotalReturnsByDateJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(totalReturnsTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TotalReturnsByDateJFrameLayout.createSequentialGroup()
                        .addGap(556, 556, 556)
                        .addComponent(totalReturnsShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TotalReturnsByDateJFrameLayout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(totalReturnsClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(totalReturnsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        TotalReturnsByDateJFrameLayout.setVerticalGroup(
            TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalReturnsByDateJFrameLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalReturnsEndDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(totalReturnsStartDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addComponent(totalReturnsShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TotalReturnsByDateJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalReturnsClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddStockJFrame.setBorder(null);
        AddStockJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AddStockJFrame.setTitle("addstockjframe");
        AddStockJFrame.setAlignmentX(0.0F);
        AddStockJFrame.setAlignmentY(0.0F);
        AddStockJFrame.setVisible(true);
        AddStockJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                AddStockJFrameComponentShown(evt);
            }
        });

        medicineLabelTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineLabelTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Medicine Name");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Company Name");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Rack Number");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Retail Price");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Trade Price");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Manufacturing Date");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Expiry Date");

        addStockCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addStockCancelButton.setText("CANCEL");
        addStockCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockCancelButtonActionPerformed(evt);
            }
        });

        saveMedicineInStock.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        saveMedicineInStock.setText("SAVE");
        saveMedicineInStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMedicineInStockActionPerformed(evt);
            }
        });

        costPriceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costPriceTextFieldActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("No. of Items");

        quantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextFieldActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel43.setText("Distributor Name");

        addStockDistributorNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockDistributorNameTextFieldActionPerformed(evt);
            }
        });

        addStockCompanyNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockCompanyNameTextFieldActionPerformed(evt);
            }
        });

        buttonGroup1.add(addStockLoosePurchasePerItemRadioButton);
        addStockLoosePurchasePerItemRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addStockLoosePurchasePerItemRadioButton.setText("Loose Purchase (Per Item)");
        buttonGroup1.add(addStockLoosePurchasePerItemRadioButton);
        addStockLoosePurchasePerItemRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockLoosePurchasePerItemRadioButtonItemStateChanged(evt);
            }
        });
        addStockLoosePurchasePerItemRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockLoosePurchasePerItemRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(addStockLoosePurchasePerBoxRadioButton);
        addStockLoosePurchasePerBoxRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addStockLoosePurchasePerBoxRadioButton.setText("Bulk Purchase (Per Box)");
        buttonGroup1.add(addStockLoosePurchasePerBoxRadioButton);
        addStockLoosePurchasePerBoxRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockLoosePurchasePerBoxRadioButtonItemStateChanged(evt);
            }
        });
        addStockLoosePurchasePerBoxRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockLoosePurchasePerBoxRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(addStockLoosePurchasePerPackRadioButton);
        addStockLoosePurchasePerPackRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addStockLoosePurchasePerPackRadioButton.setText("Loose Purchase (Per Pack)");
        buttonGroup1.add(addStockLoosePurchasePerPackRadioButton);
        addStockLoosePurchasePerPackRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockLoosePurchasePerPackRadioButtonItemStateChanged(evt);
            }
        });
        addStockLoosePurchasePerPackRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockLoosePurchasePerPackRadioButtonActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel62.setText("Items per pack or box");

        addStockItemsPerPackBoxTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockItemsPerPackBoxTextFieldActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel63.setText("Generic Name");

        medicineGenericNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineGenericNameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddStockJFrameLayout = new javax.swing.GroupLayout(AddStockJFrame.getContentPane());
        AddStockJFrame.getContentPane().setLayout(AddStockJFrameLayout);
        AddStockJFrameLayout.setHorizontalGroup(
            AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddStockJFrameLayout.createSequentialGroup()
                        .addGap(688, 688, 688)
                        .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addStockItemsPerPackBoxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddStockJFrameLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddStockJFrameLayout.createSequentialGroup()
                        .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(costPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addStockRackNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(216, 216, 216)
                        .addComponent(salesPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddStockJFrameLayout.createSequentialGroup()
                        .addComponent(addStockLoosePurchasePerItemRadioButton)
                        .addGap(162, 162, 162)
                        .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(saveMedicineInStock, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(addStockCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(addStockLoosePurchasePerPackRadioButton)
                                .addGap(137, 137, 137)
                                .addComponent(addStockLoosePurchasePerBoxRadioButton))))
                    .addGroup(AddStockJFrameLayout.createSequentialGroup()
                        .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addStockCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(manufacturingDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(54, 54, 54)
                        .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(expiryDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addStockDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(AddStockJFrameLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(medicineLabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(medicineGenericNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 1484, Short.MAX_VALUE))
        );
        AddStockJFrameLayout.setVerticalGroup(
            AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddStockJFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddStockJFrameLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medicineLabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineGenericNameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expiryDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manufacturingDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addStockCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addStockDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(salesPriceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(costPriceTextField))
                .addGap(18, 18, 18)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStockRackNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStockLoosePurchasePerItemRadioButton)
                    .addComponent(addStockLoosePurchasePerPackRadioButton)
                    .addComponent(addStockLoosePurchasePerBoxRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStockItemsPerPackBoxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveMedicineInStock, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStockCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        TotalReturnsByCompanyJFrame.setBorder(null);
        TotalReturnsByCompanyJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalReturnsByCompanyJFrame.setTitle("totalreturnsbycompanyjframe");
        TotalReturnsByCompanyJFrame.setAlignmentX(0.0F);
        TotalReturnsByCompanyJFrame.setAlignmentY(0.0F);
        TotalReturnsByCompanyJFrame.setVisible(true);

        jLabel36.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel36.setText("Select Distributor");

        totalReturnsByCompanyDistributorNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyDistributorNameTextFieldActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel37.setText("Select Company");

        totalReturnsByCompanyCompanyNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyCompanyNameTextFieldActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel38.setText("Select Medicine");

        totalReturnsByCompanyMedicineNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyMedicineNameTextFieldActionPerformed(evt);
            }
        });

        totalReturnsByCompanyReturnsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalReturnsByCompanyReturnsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalReturnsByCompanyReturnsTable.setName("totalReturnsByCompanyReturnsTable"); // NOI18N
        totalReturnsByCompanyReturnsTable.setRowHeight(25);
        totalReturnsByCompanyReturnsTable.getTableHeader().setReorderingAllowed(false);
        totalReturnsByCompanyReturnsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalReturnsByCompanyReturnsTableMouseClicked(evt);
            }
        });
        totalReturnsByCompanyReturnsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalReturnsByCompanyReturnsTableKeyTyped(evt);
            }
        });
        jScrollPane9.setViewportView(totalReturnsByCompanyReturnsTable);

        jLabel40.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel40.setText("Total Return");

        totalReturnsByCompanyReturnsTextField.setEditable(false);
        totalReturnsByCompanyReturnsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyReturnsTextFieldActionPerformed(evt);
            }
        });

        totalReturnsByCompanyShowReturnsButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsByCompanyShowReturnsButton.setText("SHOW RETURNS");
        totalReturnsByCompanyShowReturnsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyShowReturnsButtonActionPerformed(evt);
            }
        });

        totalReturnsByCompanyCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsByCompanyCancelButton.setText("CANCEL");
        totalReturnsByCompanyCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyCancelButtonActionPerformed(evt);
            }
        });

        totalReturnsByCompanyClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsByCompanyClearButton.setText("CLEAR");
        totalReturnsByCompanyClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TotalReturnsByCompanyJFrameLayout = new javax.swing.GroupLayout(TotalReturnsByCompanyJFrame.getContentPane());
        TotalReturnsByCompanyJFrame.getContentPane().setLayout(TotalReturnsByCompanyJFrameLayout);
        TotalReturnsByCompanyJFrameLayout.setHorizontalGroup(
            TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalReturnsByCompanyJFrameLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalReturnsByCompanyJFrameLayout.createSequentialGroup()
                        .addComponent(totalReturnsByCompanyDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalReturnsByCompanyShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TotalReturnsByCompanyJFrameLayout.createSequentialGroup()
                                .addComponent(totalReturnsByCompanyCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalReturnsByCompanyMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(TotalReturnsByCompanyJFrameLayout.createSequentialGroup()
                            .addComponent(totalReturnsByCompanyClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addGroup(TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(totalReturnsByCompanyCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(TotalReturnsByCompanyJFrameLayout.createSequentialGroup()
                                    .addComponent(jLabel40)
                                    .addGap(32, 32, 32)
                                    .addComponent(totalReturnsByCompanyReturnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TotalReturnsByCompanyJFrameLayout.setVerticalGroup(
            TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalReturnsByCompanyJFrameLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsByCompanyDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalReturnsByCompanyCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalReturnsByCompanyMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalReturnsByCompanyShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsByCompanyReturnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TotalReturnsByCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsByCompanyCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalReturnsByCompanyClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //javax.swing.plaf.basic.BasicInternalFrameUI bTotalRetCompany = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalReturnsByCompanyJFrame.getUI();
        //bTotalRetCompany.setNorthPane(null);

        TotalReturnsSalesManJFrame.setBorder(null);
        TotalReturnsSalesManJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalReturnsSalesManJFrame.setTitle("totalreturnssalesmanjframe");
        TotalReturnsSalesManJFrame.setAlignmentX(0.0F);
        TotalReturnsSalesManJFrame.setAlignmentY(0.0F);
        TotalReturnsSalesManJFrame.setVisible(true);

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setText("Select Salesman Name");

        totalReturnsBySalesmanNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsBySalesmanNameTextFieldActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel39.setText("Select Start Date");

        jLabel41.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel41.setText("Select End Date");

        totalReturnsBySalesmanTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalReturnsBySalesmanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalReturnsBySalesmanTable.setName("totalReturnsBySalesmanTable"); // NOI18N
        totalReturnsBySalesmanTable.setRowHeight(25);
        totalReturnsBySalesmanTable.getTableHeader().setReorderingAllowed(false);
        totalReturnsBySalesmanTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalReturnsBySalesmanTableMouseClicked(evt);
            }
        });
        totalReturnsBySalesmanTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalReturnsBySalesmanTableKeyTyped(evt);
            }
        });
        jScrollPane10.setViewportView(totalReturnsBySalesmanTable);

        totalReturnsBySalesmanReturnTextField.setEditable(false);
        totalReturnsBySalesmanReturnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsBySalesmanReturnTextFieldActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel42.setText("Total Return");

        totalReturnsBySalesManShowReturnsButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsBySalesManShowReturnsButton.setText("SHOW RETURNS");
        totalReturnsBySalesManShowReturnsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsBySalesManShowReturnsButtonActionPerformed(evt);
            }
        });

        totalReturnsBySalesManCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsBySalesManCancelButton.setText("CANCEL");
        totalReturnsBySalesManCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsBySalesManCancelButtonActionPerformed(evt);
            }
        });

        totalReturnsBySalesManClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsBySalesManClearButton.setText("CLEAR");
        totalReturnsBySalesManClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsBySalesManClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TotalReturnsSalesManJFrameLayout = new javax.swing.GroupLayout(TotalReturnsSalesManJFrame.getContentPane());
        TotalReturnsSalesManJFrame.getContentPane().setLayout(TotalReturnsSalesManJFrameLayout);
        TotalReturnsSalesManJFrameLayout.setHorizontalGroup(
            TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalReturnsSalesManJFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TotalReturnsSalesManJFrameLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalReturnsBySalesmanNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalReturnsBySalesManStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel41))
                    .addGroup(TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TotalReturnsSalesManJFrameLayout.createSequentialGroup()
                            .addGap(459, 459, 459)
                            .addComponent(totalReturnsBySalesManShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TotalReturnsSalesManJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(45, 45, 45)
                                .addComponent(totalReturnsBySalesmanReturnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(TotalReturnsSalesManJFrameLayout.createSequentialGroup()
                            .addGap(294, 294, 294)
                            .addComponent(totalReturnsBySalesManClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(70, 70, 70)
                            .addComponent(totalReturnsBySalesManCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(totalReturnsBySalesmanEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1165, Short.MAX_VALUE))
        );
        TotalReturnsSalesManJFrameLayout.setVerticalGroup(
            TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalReturnsSalesManJFrameLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalReturnsBySalesmanEndDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(totalReturnsBySalesmanNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalReturnsBySalesManStartDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addComponent(totalReturnsBySalesManShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsBySalesmanReturnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TotalReturnsSalesManJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsBySalesManCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalReturnsBySalesManClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //javax.swing.plaf.basic.BasicInternalFrameUI bTotalRetSalesman = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalReturnsSalesManJFrame.getUI();
        //bTotalRetSalesman.setNorthPane(null);

        ShortItemsCompanyJFrame.setBorder(null);
        ShortItemsCompanyJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ShortItemsCompanyJFrame.setTitle("shortitemscompanyjframe");
        ShortItemsCompanyJFrame.setAlignmentX(0.0F);
        ShortItemsCompanyJFrame.setAlignmentY(0.0F);
        ShortItemsCompanyJFrame.setVisible(true);

        jLabel45.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel45.setText("Select Distributor");

        shortItemsCompanyDistributorNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsCompanyDistributorNameTextFieldActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel46.setText("Show Medicine with Count Lesser Than");

        shortItemsCompanyNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsCompanyNameTextFieldActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel47.setText("Select Company");

        shortItemsCompanySpinner.setValue(1);

        shortItemsByCompanyShowShortMedicineButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsByCompanyShowShortMedicineButton.setText("SHOW SHORT MEDICINE");
        shortItemsByCompanyShowShortMedicineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsByCompanyShowShortMedicineButtonActionPerformed(evt);
            }
        });

        shortItemsByCompanyShortItemsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        shortItemsByCompanyShortItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        shortItemsByCompanyShortItemsTable.setName("shortItemsByCompanyShortItemsTable"); // NOI18N
        shortItemsByCompanyShortItemsTable.setRowHeight(25);
        shortItemsByCompanyShortItemsTable.getTableHeader().setReorderingAllowed(false);
        shortItemsByCompanyShortItemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shortItemsByCompanyShortItemsTableMouseClicked(evt);
            }
        });
        shortItemsByCompanyShortItemsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shortItemsByCompanyShortItemsTableKeyTyped(evt);
            }
        });
        jScrollPane11.setViewportView(shortItemsByCompanyShortItemsTable);

        shortItemsByCompanyShowShortMedicineClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsByCompanyShowShortMedicineClearButton.setText("CLEAR");
        shortItemsByCompanyShowShortMedicineClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsByCompanyShowShortMedicineClearButtonActionPerformed(evt);
            }
        });

        shortItemsByCompanyShowShortMedicineCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsByCompanyShowShortMedicineCancelButton.setText("CANCEL");
        shortItemsByCompanyShowShortMedicineCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsByCompanyShowShortMedicineCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShortItemsCompanyJFrameLayout = new javax.swing.GroupLayout(ShortItemsCompanyJFrame.getContentPane());
        ShortItemsCompanyJFrame.getContentPane().setLayout(ShortItemsCompanyJFrameLayout);
        ShortItemsCompanyJFrameLayout.setHorizontalGroup(
            ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShortItemsCompanyJFrameLayout.createSequentialGroup()
                .addGroup(ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShortItemsCompanyJFrameLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addGroup(ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ShortItemsCompanyJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane11)
                                    .addGroup(ShortItemsCompanyJFrameLayout.createSequentialGroup()
                                        .addComponent(shortItemsCompanyDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(shortItemsCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(ShortItemsCompanyJFrameLayout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(shortItemsCompanySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(shortItemsByCompanyShowShortMedicineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ShortItemsCompanyJFrameLayout.createSequentialGroup()
                        .addGap(528, 528, 528)
                        .addComponent(shortItemsByCompanyShowShortMedicineClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(shortItemsByCompanyShowShortMedicineCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShortItemsCompanyJFrameLayout.setVerticalGroup(
            ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShortItemsCompanyJFrameLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shortItemsCompanyDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsCompanySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsByCompanyShowShortMedicineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(ShortItemsCompanyJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shortItemsByCompanyShowShortMedicineClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsByCompanyShowShortMedicineCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //javax.swing.plaf.basic.BasicInternalFrameUI bShortItemsCom = (javax.swing.plaf.basic.BasicInternalFrameUI)ShortItemsCompanyJFrame.getUI();
        //bShortItemsCom.setNorthPane(null);

        ShortItemsOverallJFrame.setBorder(null);
        ShortItemsOverallJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ShortItemsOverallJFrame.setTitle("shortitemsoveralljframe");
        ShortItemsOverallJFrame.setVisible(true);

        jLabel49.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel49.setText("Show Medicine with Count Lesser Than");

        shortItemsOverallSpinner.setValue(1);

        shortItemsOverallShowShortMedicineButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsOverallShowShortMedicineButton.setText("SHOW SHORT MEDICINE");
        shortItemsOverallShowShortMedicineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOverallShowShortMedicineButtonActionPerformed(evt);
            }
        });

        shortItemsOverAllMedicineTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        shortItemsOverAllMedicineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        shortItemsOverAllMedicineTable.setName("shortItemsOverAllMedicineTable"); // NOI18N
        shortItemsOverAllMedicineTable.setRowHeight(25);
        shortItemsOverAllMedicineTable.getTableHeader().setReorderingAllowed(false);
        shortItemsOverAllMedicineTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shortItemsOverAllMedicineTableMouseClicked(evt);
            }
        });
        shortItemsOverAllMedicineTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shortItemsOverAllMedicineTableKeyTyped(evt);
            }
        });
        jScrollPane12.setViewportView(shortItemsOverAllMedicineTable);

        shortItemsOverallCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsOverallCancelButton.setText("CANCEL");
        shortItemsOverallCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOverallCancelButtonActionPerformed(evt);
            }
        });

        shortItemsOverallClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsOverallClearButton.setText("CLEAR");
        shortItemsOverallClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOverallClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShortItemsOverallJFrameLayout = new javax.swing.GroupLayout(ShortItemsOverallJFrame.getContentPane());
        ShortItemsOverallJFrame.getContentPane().setLayout(ShortItemsOverallJFrameLayout);
        ShortItemsOverallJFrameLayout.setHorizontalGroup(
            ShortItemsOverallJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShortItemsOverallJFrameLayout.createSequentialGroup()
                .addGroup(ShortItemsOverallJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShortItemsOverallJFrameLayout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addGroup(ShortItemsOverallJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ShortItemsOverallJFrameLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(shortItemsOverallSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(shortItemsOverallShowShortMedicineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ShortItemsOverallJFrameLayout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(shortItemsOverallClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(shortItemsOverallCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShortItemsOverallJFrameLayout.setVerticalGroup(
            ShortItemsOverallJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShortItemsOverallJFrameLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(ShortItemsOverallJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsOverallSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsOverallShowShortMedicineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(ShortItemsOverallJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shortItemsOverallCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsOverallClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //javax.swing.plaf.basic.BasicInternalFrameUI bShortItemsOverAll = (javax.swing.plaf.basic.BasicInternalFrameUI)ShortItemsOverallJFrame.getUI();
        //bShortItemsOverAll.setNorthPane(null);

        ShortItemsOnRackJFrame.setBorder(null);
        ShortItemsOnRackJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ShortItemsOnRackJFrame.setTitle("shortitemsonrackjframe");
        ShortItemsOnRackJFrame.setAlignmentX(0.0F);
        ShortItemsOnRackJFrame.setAlignmentY(0.0F);
        ShortItemsOnRackJFrame.setVisible(true);

        jLabel52.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel52.setText("Select Distributor");

        shortItemsOnRackDistributorNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOnRackDistributorNameTextFieldActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel53.setText("Show Medicine with Count Lesser Than");

        shortItemsOnRackCompanyNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOnRackCompanyNameTextFieldActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel54.setText("Select Company");

        shortItemsByRackSpinner.setValue(1);

        shortItemsOnRackShowShortMedicineButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsOnRackShowShortMedicineButton.setText("SHOW SHORT MEDICINE");
        shortItemsOnRackShowShortMedicineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOnRackShowShortMedicineButtonActionPerformed(evt);
            }
        });

        shortItemsOnRackTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        shortItemsOnRackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        shortItemsOnRackTable.setName("shortItemsOnRackTable"); // NOI18N
        shortItemsOnRackTable.setRowHeight(25);
        shortItemsOnRackTable.getTableHeader().setReorderingAllowed(false);
        shortItemsOnRackTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shortItemsOnRackTableMouseClicked(evt);
            }
        });
        shortItemsOnRackTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shortItemsOnRackTableKeyTyped(evt);
            }
        });
        jScrollPane13.setViewportView(shortItemsOnRackTable);

        jLabel55.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel55.setText("Select Rack");

        shortItemsRackNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsRackNoTextFieldActionPerformed(evt);
            }
        });

        shortItemsOnRackClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsOnRackClearButton.setText("CLEAR");
        shortItemsOnRackClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOnRackClearButtonActionPerformed(evt);
            }
        });

        shortItemsOnRackCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shortItemsOnRackCancelButton.setText("CANCEL");
        shortItemsOnRackCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOnRackCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShortItemsOnRackJFrameLayout = new javax.swing.GroupLayout(ShortItemsOnRackJFrame.getContentPane());
        ShortItemsOnRackJFrame.getContentPane().setLayout(ShortItemsOnRackJFrameLayout);
        ShortItemsOnRackJFrameLayout.setHorizontalGroup(
            ShortItemsOnRackJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShortItemsOnRackJFrameLayout.createSequentialGroup()
                .addGroup(ShortItemsOnRackJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShortItemsOnRackJFrameLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(ShortItemsOnRackJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ShortItemsOnRackJFrameLayout.createSequentialGroup()
                                .addGap(231, 231, 231)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ShortItemsOnRackJFrameLayout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(shortItemsByRackSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(shortItemsOnRackShowShortMedicineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ShortItemsOnRackJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shortItemsOnRackDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shortItemsOnRackCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shortItemsRackNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ShortItemsOnRackJFrameLayout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(shortItemsOnRackClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(shortItemsOnRackCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShortItemsOnRackJFrameLayout.setVerticalGroup(
            ShortItemsOnRackJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShortItemsOnRackJFrameLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(ShortItemsOnRackJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shortItemsOnRackDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsOnRackCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsRackNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ShortItemsOnRackJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsByRackSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsOnRackShowShortMedicineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(ShortItemsOnRackJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shortItemsOnRackClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shortItemsOnRackCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //javax.swing.plaf.basic.BasicInternalFrameUI bShortItemsOnRack = (javax.swing.plaf.basic.BasicInternalFrameUI)ShortItemsOnRackJFrame.getUI();
        //bShortItemsOnRack.setNorthPane(null);

        UpdateStockJFrame.setBorder(null);
        UpdateStockJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        UpdateStockJFrame.setTitle("updatestockjframe");
        UpdateStockJFrame.setAlignmentX(0.0F);
        UpdateStockJFrame.setAlignmentY(0.0F);
        UpdateStockJFrame.setVisible(true);
        UpdateStockJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                UpdateStockJFrameComponentShown(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel44.setText("Enter Medicine Name to be updated");

        jLabel48.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel48.setText("Company Name");

        jLabel50.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel50.setText("Rack Number");

        jLabel51.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel51.setText("Retail Price");

        jLabel56.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel56.setText("Trade Price");

        jLabel57.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel57.setText("Manufacturing Date");

        jLabel58.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel58.setText("Expiry Date");

        updateStockCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        updateStockCancelButton.setText("CANCEL");
        updateStockCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockCancelButtonActionPerformed(evt);
            }
        });

        updateStockJFrameSaveMedicineInStockButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        updateStockJFrameSaveMedicineInStockButton.setText("SAVE");
        updateStockJFrameSaveMedicineInStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockJFrameSaveMedicineInStockButtonActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel60.setText("Distributor Name");

        updateStockMedicineNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockMedicineNameTextFieldActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel61.setText("Medicine Name");

        jLabel70.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel70.setText("Generic Name");

        updateStockMedicineGenericNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockMedicineGenericNameTextFieldActionPerformed(evt);
            }
        });

        buttonGroup1.add(updateStockLoosePurchasePerItemRadioButton);
        updateStockLoosePurchasePerItemRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        updateStockLoosePurchasePerItemRadioButton.setSelected(true);
        updateStockLoosePurchasePerItemRadioButton.setText("Loose Purchase (Per Item)");
        buttonGroup1.add(addStockLoosePurchasePerItemRadioButton);
        updateStockLoosePurchasePerItemRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                updateStockLoosePurchasePerItemRadioButtonItemStateChanged(evt);
            }
        });
        updateStockLoosePurchasePerItemRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockLoosePurchasePerItemRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(updateStockLoosePurchasePerPackRadioButton);
        updateStockLoosePurchasePerPackRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        updateStockLoosePurchasePerPackRadioButton.setText("Loose Purchase (Per Pack)");
        buttonGroup1.add(addStockLoosePurchasePerPackRadioButton);
        updateStockLoosePurchasePerPackRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                updateStockLoosePurchasePerPackRadioButtonItemStateChanged(evt);
            }
        });
        updateStockLoosePurchasePerPackRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockLoosePurchasePerPackRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(updateStockLoosePurchasePerBoxRadioButton);
        updateStockLoosePurchasePerBoxRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        updateStockLoosePurchasePerBoxRadioButton.setText("Bulk Purchase (Per Box)");
        buttonGroup1.add(addStockLoosePurchasePerBoxRadioButton);
        updateStockLoosePurchasePerBoxRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                updateStockLoosePurchasePerBoxRadioButtonItemStateChanged(evt);
            }
        });
        updateStockLoosePurchasePerBoxRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockLoosePurchasePerBoxRadioButtonActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel71.setText("No. of Items");

        updateStockQuantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockQuantityTextFieldActionPerformed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel72.setText("Items per pack or box");

        updateStockItemsPerPackBoxTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockItemsPerPackBoxTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UpdateStockJFrameLayout = new javax.swing.GroupLayout(UpdateStockJFrame.getContentPane());
        UpdateStockJFrame.getContentPane().setLayout(UpdateStockJFrameLayout);
        UpdateStockJFrameLayout.setHorizontalGroup(
            UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(updateStockJFrameSaveMedicineInStockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateStockCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addComponent(updateStockMedicineNameDropDownField, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1325, Short.MAX_VALUE))
            .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpdateStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateStockManufacturingDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpdateStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateStockCostPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpdateStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateStockCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpdateStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateStockMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpdateStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateStockRackNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(96, 96, 96)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpdateStockJFrameLayout.createSequentialGroup()
                                    .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(updateStockMedicineGenericNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                        .addComponent(updateStockExpiryDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                    .addComponent(jLabel60)
                                    .addGap(30, 30, 30)
                                    .addComponent(updateStockDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateStockSalePriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpdateStockJFrameLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateStockItemsPerPackBoxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                        .addComponent(updateStockLoosePurchasePerItemRadioButton)
                                        .addGap(159, 159, 159)
                                        .addComponent(updateStockLoosePurchasePerPackRadioButton))
                                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addComponent(updateStockLoosePurchasePerBoxRadioButton))
                                    .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addComponent(updateStockQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        UpdateStockJFrameLayout.setVerticalGroup(
            UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateStockMedicineNameDropDownField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(368, 368, 368)
                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateStockJFrameLayout.createSequentialGroup()
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateStockMedicineGenericNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateStockExpiryDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateStockDistributorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateStockSalePriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateStockJFrameLayout.createSequentialGroup()
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(updateStockMedicineNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addComponent(updateStockManufacturingDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateStockCompanyNameTextField)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateStockCostPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateStockRackNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateStockLoosePurchasePerItemRadioButton)
                            .addComponent(updateStockLoosePurchasePerPackRadioButton)
                            .addComponent(updateStockLoosePurchasePerBoxRadioButton))))
                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateStockQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateStockJFrameLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateStockJFrameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateStockItemsPerPackBoxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(UpdateStockJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateStockJFrameSaveMedicineInStockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateStockCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(500, 500, 500))
        );

        AddUserJFrame.setBorder(null);
        AddUserJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AddUserJFrame.setTitle("adduserjframe");
        AddUserJFrame.setAlignmentX(0.0F);
        AddUserJFrame.setAlignmentY(0.0F);
        AddUserJFrame.setVisible(true);
        AddUserJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                AddUserJFrameComponentShown(evt);
            }
        });

        addUserUserLoginNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserUserLoginNameTextFieldActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel64.setText("Enter User Login Name");

        jLabel65.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel65.setText("Enter User Password");

        jLabel66.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel66.setText("Select User Group");

        addUserReenterUserPasswordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserReenterUserPasswordTextFieldActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel67.setText("Re-Enter User Password");

        addUserSaveUserButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addUserSaveUserButton.setText("SAVE USER");
        addUserSaveUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserSaveUserButtonActionPerformed(evt);
            }
        });

        addUserUserNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserUserNameTextFieldActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel68.setText("Enter User Name");

        addUserCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addUserCancelButton.setText("CANCEL");
        addUserCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserCancelButtonActionPerformed(evt);
            }
        });

        addUserClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addUserClearButton.setText("CLEAR");
        addUserClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddUserJFrameLayout = new javax.swing.GroupLayout(AddUserJFrame.getContentPane());
        AddUserJFrame.getContentPane().setLayout(AddUserJFrameLayout);
        AddUserJFrameLayout.setHorizontalGroup(
            AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserJFrameLayout.createSequentialGroup()
                .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddUserJFrameLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(AddUserJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addUserUserGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddUserJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addUserUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddUserJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addGap(18, 18, 18)
                                .addComponent(addUserUserPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(jLabel64))
                        .addGap(38, 38, 38)
                        .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addUserUserLoginNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addUserReenterUserPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddUserJFrameLayout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(addUserSaveUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addUserClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(addUserCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddUserJFrameLayout.setVerticalGroup(
            AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserJFrameLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserUserLoginNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddUserJFrameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddUserJFrameLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addUserUserPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addUserReenterUserPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserUserGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(AddUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserSaveUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(340, Short.MAX_VALUE))
        );

        AddUserGroupJFrame.setBorder(null);
        AddUserGroupJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AddUserGroupJFrame.setTitle("addusergroup");
        AddUserGroupJFrame.setAlignmentX(0.0F);
        AddUserGroupJFrame.setAlignmentY(0.0F);
        AddUserGroupJFrame.setVisible(true);
        AddUserGroupJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                AddUserGroupJFrameComponentShown(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel69.setText("Assign Privileges");

        addUserGroupAddUserGroupButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addUserGroupAddUserGroupButton.setText("ADD USER GROUP");
        addUserGroupAddUserGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserGroupAddUserGroupButtonActionPerformed(evt);
            }
        });

        addUserGroupUserGroupNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserGroupUserGroupNameTextFieldActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel73.setText("Enter User Group Name");

        addUserGroupPrivilesgesList.setToolTipText("");
        jScrollPane1.setViewportView(addUserGroupPrivilesgesList);

        addUserGroupsCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addUserGroupsCancelButton.setText("CANCEL");
        addUserGroupsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserGroupsCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddUserGroupJFrameLayout = new javax.swing.GroupLayout(AddUserGroupJFrame.getContentPane());
        AddUserGroupJFrame.getContentPane().setLayout(AddUserGroupJFrameLayout);
        AddUserGroupJFrameLayout.setHorizontalGroup(
            AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserGroupJFrameLayout.createSequentialGroup()
                .addGroup(AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddUserGroupJFrameLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69)
                            .addComponent(jLabel73))
                        .addGap(46, 46, 46)
                        .addGroup(AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addUserGroupUserGroupNameTextField)
                            .addComponent(jScrollPane1)))
                    .addGroup(AddUserGroupJFrameLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(addUserGroupAddUserGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addUserGroupsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        AddUserGroupJFrameLayout.setVerticalGroup(
            AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserGroupJFrameLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserGroupUserGroupNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(AddUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserGroupAddUserGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserGroupsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RemoveUserJFrame.setBorder(null);
        RemoveUserJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        RemoveUserJFrame.setTitle("removeuserjframe");
        RemoveUserJFrame.setAlignmentX(0.0F);
        RemoveUserJFrame.setAlignmentY(0.0F);
        RemoveUserJFrame.setVisible(true);
        RemoveUserJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                RemoveUserJFrameComponentShown(evt);
            }
        });

        removeUserButton.setText("REMOVE USER");
        removeUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserButtonActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel75.setText("Select User to Remove");

        removeUserCancelButton.setText("CANCEL");
        removeUserCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RemoveUserJFrameLayout = new javax.swing.GroupLayout(RemoveUserJFrame.getContentPane());
        RemoveUserJFrame.getContentPane().setLayout(RemoveUserJFrameLayout);
        RemoveUserJFrameLayout.setHorizontalGroup(
            RemoveUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoveUserJFrameLayout.createSequentialGroup()
                .addGroup(RemoveUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RemoveUserJFrameLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(removeUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(removeUserCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RemoveUserJFrameLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel75)
                        .addGap(33, 33, 33)
                        .addComponent(usersToRemoveCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        RemoveUserJFrameLayout.setVerticalGroup(
            RemoveUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoveUserJFrameLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(RemoveUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usersToRemoveCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(RemoveUserJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeUserCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(425, Short.MAX_VALUE))
        );

        RemoveUserGroupJFrame.setBorder(null);
        RemoveUserGroupJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        RemoveUserGroupJFrame.setTitle("removeusergroupjframe");
        RemoveUserGroupJFrame.setAlignmentX(0.0F);
        RemoveUserGroupJFrame.setAlignmentY(0.0F);
        RemoveUserGroupJFrame.setVisible(true);
        RemoveUserGroupJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                RemoveUserGroupJFrameComponentShown(evt);
            }
        });

        removeUserGroupButton.setText("REMOVE GROUP");
        removeUserGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserGroupButtonActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel76.setText("Select User Group to Remove");

        removeGroupsCancelButton.setText("CANCEL");
        removeGroupsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGroupsCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RemoveUserGroupJFrameLayout = new javax.swing.GroupLayout(RemoveUserGroupJFrame.getContentPane());
        RemoveUserGroupJFrame.getContentPane().setLayout(RemoveUserGroupJFrameLayout);
        RemoveUserGroupJFrameLayout.setHorizontalGroup(
            RemoveUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoveUserGroupJFrameLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(RemoveUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeUserGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76))
                .addGap(25, 25, 25)
                .addGroup(RemoveUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userGroupsToRemoveCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeGroupsCancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                .addContainerGap(534, Short.MAX_VALUE))
        );
        RemoveUserGroupJFrameLayout.setVerticalGroup(
            RemoveUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoveUserGroupJFrameLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(RemoveUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userGroupsToRemoveCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(RemoveUserGroupJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeUserGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeGroupsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(318, Short.MAX_VALUE))
        );

        TotalReturnsByMedicineJFrame.setBorder(null);
        TotalReturnsByMedicineJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TotalReturnsByMedicineJFrame.setTitle("totalreturnsbymedicinejframe");
        TotalReturnsByMedicineJFrame.setAlignmentX(0.0F);
        TotalReturnsByMedicineJFrame.setAlignmentY(0.0F);
        TotalReturnsByMedicineJFrame.setVisible(true);

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("Medicine Name");

        totalReturnsByMedicineMedicineNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByMedicineMedicineNameTextFieldActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setText("End Date");

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setText("Start Date");

        totalReturnsByMedicineClearButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsByMedicineClearButton.setText("CLEAR");
        totalReturnsByMedicineClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByMedicineClearButtonActionPerformed(evt);
            }
        });

        totalReturnsByMedicineReturnsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalReturnsByMedicineReturnsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalReturnsByMedicineReturnsTable.setName("totalReturnsByMedicineReturnsTable"); // NOI18N
        totalReturnsByMedicineReturnsTable.setRowHeight(25);
        totalReturnsByMedicineReturnsTable.getTableHeader().setReorderingAllowed(false);
        totalReturnsByMedicineReturnsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalReturnsByMedicineReturnsTableMouseClicked(evt);
            }
        });
        totalReturnsByMedicineReturnsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalReturnsByMedicineReturnsTableKeyTyped(evt);
            }
        });
        jScrollPane8.setViewportView(totalReturnsByMedicineReturnsTable);

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setText("Total Returns");

        totalMedicineReturnsByMedicineReturnsTextField.setEditable(false);
        totalMedicineReturnsByMedicineReturnsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMedicineReturnsByMedicineReturnsTextFieldActionPerformed(evt);
            }
        });

        totalReturnsByMedicineCancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsByMedicineCancelButton.setText("CANCEL");
        totalReturnsByMedicineCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByMedicineCancelButtonActionPerformed(evt);
            }
        });

        totalReturnsByMedicineShowReturnsButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        totalReturnsByMedicineShowReturnsButton.setText("SHOW RETURNS");
        totalReturnsByMedicineShowReturnsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByMedicineShowReturnsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TotalReturnsByMedicineJFrameLayout = new javax.swing.GroupLayout(TotalReturnsByMedicineJFrame.getContentPane());
        TotalReturnsByMedicineJFrame.getContentPane().setLayout(TotalReturnsByMedicineJFrameLayout);
        TotalReturnsByMedicineJFrameLayout.setHorizontalGroup(
            TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                .addGap(432, 1595, Short.MAX_VALUE)
                .addComponent(totalReturnsByMedicineClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(totalReturnsByMedicineCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(352, 352, 352))
            .addGroup(TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                        .addGroup(TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 2222, Short.MAX_VALUE)
                            .addGroup(TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(totalMedicineReturnsByMedicineReturnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(208, 208, 208))
                    .addGroup(TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                        .addGroup(TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                                .addGap(496, 496, 496)
                                .addComponent(totalReturnsByMedicineShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(totalReturnsByMedicineMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalReturnsByMedicineStartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalReturnsByMedicineEndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        TotalReturnsByMedicineJFrameLayout.setVerticalGroup(
            TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalReturnsByMedicineJFrameLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalReturnsByMedicineEndDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalReturnsByMedicineMedicineNameTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(totalReturnsByMedicineStartDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(35, 35, 35)
                .addComponent(totalReturnsByMedicineShowReturnsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalMedicineReturnsByMedicineReturnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TotalReturnsByMedicineJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalReturnsByMedicineCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalReturnsByMedicineClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //javax.swing.plaf.basic.BasicInternalFrameUI bTotRetMedicine = (javax.swing.plaf.basic.BasicInternalFrameUI)TotalReturnsByMedicineJFrame.getUI();
        //bTotRetMedicine.setNorthPane(null);

        EasyStockAdditionJFrame.setBorder(null);
        EasyStockAdditionJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        EasyStockAdditionJFrame.setTitle("easystockadditionjframe");
        EasyStockAdditionJFrame.setAlignmentX(0.0F);
        EasyStockAdditionJFrame.setAlignmentY(0.0F);
        EasyStockAdditionJFrame.setVisible(true);
        EasyStockAdditionJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                EasyStockAdditionJFrameComponentShown(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel87.setText("Company Name");

        jLabel88.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel88.setText("Distributor Name");

        distributorNameComboBox.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                distributorNameComboBoxComponentShown(evt);
            }
        });
        distributorNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distributorNameComboBoxActionPerformed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel89.setText("Medicine Name");

        easyStockAdditionMedicineNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyStockAdditionMedicineNameComboBoxActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel90.setText("Expiry Date");

        jLabel91.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel91.setText("Generic Name");

        jLabel92.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel92.setText("Manufacturing Date");

        jLabel93.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel93.setText("Retail Price");

        jLabel94.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel94.setText("Trade Price");

        jLabel95.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel95.setText("Rack Number");

        easyStockAdditionRadioButtonLoosePurchasePerPack.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        easyStockAdditionRadioButtonLoosePurchasePerPack.setText("Loose Purchase (Per Pack)");
        easyStockAdditionRadioButtonLoosePurchasePerPack.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                easyStockAdditionRadioButtonLoosePurchasePerPackItemStateChanged(evt);
            }
        });
        easyStockAdditionRadioButtonLoosePurchasePerPack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyStockAdditionRadioButtonLoosePurchasePerPackActionPerformed(evt);
            }
        });

        easyStockAdditionRadioButtonLoosePurchasePerBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        easyStockAdditionRadioButtonLoosePurchasePerBox.setText("Bulk Purchase (Per Box)");
        easyStockAdditionRadioButtonLoosePurchasePerBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                easyStockAdditionRadioButtonLoosePurchasePerBoxItemStateChanged(evt);
            }
        });
        easyStockAdditionRadioButtonLoosePurchasePerBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyStockAdditionRadioButtonLoosePurchasePerBoxActionPerformed(evt);
            }
        });

        easyAddStockAdditionRadioButtonLoosePurchasePerItem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        easyAddStockAdditionRadioButtonLoosePurchasePerItem.setText("Loose Purchase (Per Item)");
        easyAddStockAdditionRadioButtonLoosePurchasePerItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                easyAddStockAdditionRadioButtonLoosePurchasePerItemItemStateChanged(evt);
            }
        });
        easyAddStockAdditionRadioButtonLoosePurchasePerItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyAddStockAdditionRadioButtonLoosePurchasePerItemActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel96.setText("Items Per Pack OR Box");

        jLabel97.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel97.setText("Number Of Items");

        cancelButton.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        easyStockAdditionCompanyNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyStockAdditionCompanyNameComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EasyStockAdditionJFrameLayout = new javax.swing.GroupLayout(EasyStockAdditionJFrame.getContentPane());
        EasyStockAdditionJFrame.getContentPane().setLayout(EasyStockAdditionJFrameLayout);
        EasyStockAdditionJFrameLayout.setHorizontalGroup(
            EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96)
                            .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                                        .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel92)
                                            .addComponent(jLabel88)
                                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel89))
                                        .addGap(46, 46, 46)
                                        .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(distributorNameComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(easyStockAdditionMedicineNameComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(easyStockAdditionTradePriceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                            .addComponent(easyStockAdditionmanufacturingDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EasyStockAdditionJFrameLayout.createSequentialGroup()
                                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                        .addComponent(easyStockAdditionRackNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(78, 78, 78)
                                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel97))
                        .addGap(84, 84, 84)
                        .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(easyStockAdditionCompanyNameComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 253, Short.MAX_VALUE)
                            .addComponent(easyStockAdditionGenericNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(easyStockAdditionRetailPriceTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(easyStockAdditionNumberOfItemsTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(easyStockAdditionItemsPerPackOrBoxTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(easyStockAdditionExpiryDateTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(easyAddStockAdditionRadioButtonLoosePurchasePerItem)
                        .addGap(137, 137, 137)
                        .addComponent(easyStockAdditionRadioButtonLoosePurchasePerPack)
                        .addGap(163, 163, 163)
                        .addComponent(easyStockAdditionRadioButtonLoosePurchasePerBox))
                    .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(286, 286, 286)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1481, Short.MAX_VALUE))
        );
        EasyStockAdditionJFrameLayout.setVerticalGroup(
            EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(distributorNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(easyStockAdditionCompanyNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(easyStockAdditionMedicineNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(easyStockAdditionGenericNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                        .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(easyStockAdditionmanufacturingDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(EasyStockAdditionJFrameLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                    .addComponent(easyStockAdditionExpiryDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(30, 30, 30)
                        .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(easyStockAdditionTradePriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(easyStockAdditionRetailPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(easyStockAdditionRackNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(easyAddStockAdditionRadioButtonLoosePurchasePerItem)
                    .addComponent(easyStockAdditionRadioButtonLoosePurchasePerPack)
                    .addComponent(easyStockAdditionRadioButtonLoosePurchasePerBox))
                .addGap(37, 37, 37)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(easyStockAdditionNumberOfItemsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(easyStockAdditionItemsPerPackOrBoxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EasyStockAdditionJFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        mainDesktopPane.setLayer(ReceiptJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalSalesInDateRange, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalMedicineSaleJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalCompanySaleJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalSalesManSalesJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalReturnsByDateJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(AddStockJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalReturnsByCompanyJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalReturnsSalesManJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(ShortItemsCompanyJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(ShortItemsOverallJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(ShortItemsOnRackJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(UpdateStockJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(AddUserJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(AddUserGroupJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(RemoveUserJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(RemoveUserGroupJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(TotalReturnsByMedicineJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(EasyStockAdditionJFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainDesktopPaneLayout = new javax.swing.GroupLayout(mainDesktopPane);
        mainDesktopPane.setLayout(mainDesktopPaneLayout);
        mainDesktopPaneLayout.setHorizontalGroup(
            mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReceiptJFrame)
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AddStockJFrame)
                .addComponent(EasyStockAdditionJFrame))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalSalesInDateRange)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalMedicineSaleJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalCompanySaleJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalSalesManSalesJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalReturnsByDateJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TotalReturnsByCompanyJFrame))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalReturnsSalesManJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(ShortItemsCompanyJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(ShortItemsOverallJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(ShortItemsOnRackJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(UpdateStockJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(AddUserJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AddUserGroupJFrame))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(RemoveUserJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(RemoveUserGroupJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TotalReturnsByMedicineJFrame))
        );
        mainDesktopPaneLayout.setVerticalGroup(
            mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                .addComponent(ReceiptJFrame)
                .addContainerGap())
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(AddStockJFrame)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(EasyStockAdditionJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalSalesInDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TotalMedicineSaleJFrame))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalCompanySaleJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalSalesManSalesJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalReturnsByDateJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalReturnsByCompanyJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalReturnsSalesManJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(ShortItemsCompanyJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(ShortItemsOverallJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(ShortItemsOnRackJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(UpdateStockJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AddUserJFrame))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AddUserGroupJFrame))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(RemoveUserJFrame)
                    .addContainerGap()))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(RemoveUserGroupJFrame))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addComponent(TotalReturnsByMedicineJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jMenu1.setText("Sales");

        printReceiptMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printReceiptMenu.setText("Print Receipt");
        printReceiptMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptMenuActionPerformed(evt);
            }
        });
        jMenu1.add(printReceiptMenu);

        configureDiscountMenu.setText("Configure Discount");
        configureDiscountMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureDiscountMenuActionPerformed(evt);
            }
        });
        jMenu1.add(configureDiscountMenu);

        mainMenuBar.add(jMenu1);

        jMenu3.setText("Stock");

        addStock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        addStock.setText("Add Stock");
        addStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockActionPerformed(evt);
            }
        });
        jMenu3.add(addStock);

        updateStockMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        updateStockMenu.setText("Update Stock");
        updateStockMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockMenuActionPerformed(evt);
            }
        });
        jMenu3.add(updateStockMenu);

        processReturnMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        processReturnMenu.setText("Process Return");
        processReturnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processReturnMenuActionPerformed(evt);
            }
        });
        jMenu3.add(processReturnMenu);

        easyStockMenuItem.setText("Easy Stock Addition");
        easyStockMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyStockMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(easyStockMenuItem);

        mainMenuBar.add(jMenu3);

        salesInDateRangeMenu.setText("Reports");

        jMenu7.setText("Daily Sales");

        totalSalesInDateRangeMenu.setText("Total Sales in Date Range");
        totalSalesInDateRangeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSalesInDateRangeMenuActionPerformed(evt);
            }
        });
        jMenu7.add(totalSalesInDateRangeMenu);

        totalMedicineSaleMenu.setText("Total Sales of Medicine");
        totalMedicineSaleMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMedicineSaleMenuActionPerformed(evt);
            }
        });
        jMenu7.add(totalMedicineSaleMenu);

        totalCompanySaleMenu.setText("Total Sales of Company");
        totalCompanySaleMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCompanySaleMenuActionPerformed(evt);
            }
        });
        jMenu7.add(totalCompanySaleMenu);

        totalSalesmanSaleMenu.setText("Total Sales of Salesman");
        totalSalesmanSaleMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSalesmanSaleMenuActionPerformed(evt);
            }
        });
        jMenu7.add(totalSalesmanSaleMenu);

        salesInDateRangeMenu.add(jMenu7);

        jMenu6.setText("Daily Returns");

        totalReturnsByDateMenu.setText("Total Returns by Date Range");
        totalReturnsByDateMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByDateMenuActionPerformed(evt);
            }
        });
        jMenu6.add(totalReturnsByDateMenu);

        totalReturnsByMedicineMenu.setText("Total Returns by medicine");
        totalReturnsByMedicineMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByMedicineMenuActionPerformed(evt);
            }
        });
        jMenu6.add(totalReturnsByMedicineMenu);

        totalReturnsByCompanyMenu.setText("Total Returns by company");
        totalReturnsByCompanyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsByCompanyMenuActionPerformed(evt);
            }
        });
        jMenu6.add(totalReturnsByCompanyMenu);

        totalReturnsBySalesmanMenu.setText("Total Returns by Salesman");
        totalReturnsBySalesmanMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalReturnsBySalesmanMenuActionPerformed(evt);
            }
        });
        jMenu6.add(totalReturnsBySalesmanMenu);

        salesInDateRangeMenu.add(jMenu6);

        jMenu8.setText("Daily Stock");

        overallShortItemsMenu.setText("Overall Short Items");
        overallShortItemsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overallShortItemsMenuActionPerformed(evt);
            }
        });
        jMenu8.add(overallShortItemsMenu);

        shortItemsByCompanyMenu.setText("Short Items By Company");
        shortItemsByCompanyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsByCompanyMenuActionPerformed(evt);
            }
        });
        jMenu8.add(shortItemsByCompanyMenu);

        shortItemsOnRackMenu.setText("Short Items On Rack");
        shortItemsOnRackMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortItemsOnRackMenuActionPerformed(evt);
            }
        });
        jMenu8.add(shortItemsOnRackMenu);

        salesInDateRangeMenu.add(jMenu8);

        mainMenuBar.add(salesInDateRangeMenu);

        securityMenu.setText("Security");

        addRemoveUsersMenu.setText("Add Users");
        addRemoveUsersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRemoveUsersMenuActionPerformed(evt);
            }
        });
        securityMenu.add(addRemoveUsersMenu);

        addRemoveUserGroupsMenu.setText("Add User Groups");
        addRemoveUserGroupsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRemoveUserGroupsMenuActionPerformed(evt);
            }
        });
        securityMenu.add(addRemoveUserGroupsMenu);

        removeUsersMenu.setText("Remove Users");
        removeUsersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUsersMenuActionPerformed(evt);
            }
        });
        securityMenu.add(removeUsersMenu);

        removeUserGroupsMenu.setText("Remove User Groups");
        removeUserGroupsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserGroupsMenuActionPerformed(evt);
            }
        });
        securityMenu.add(removeUserGroupsMenu);

        mainMenuBar.add(securityMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainDesktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainDesktopPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printReceiptMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceiptMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("PrintReceipts"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

    // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        currentOnTopFrame.hide();
        currentOnTopFrame = ReceiptJFrame;
        ReceiptJFrame.show();
    }//GEN-LAST:event_printReceiptMenuActionPerformed

    private void addStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("AddUpdateStock"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = AddStockJFrame;
        AddStockJFrame.show();
    }//GEN-LAST:event_addStockActionPerformed

    public void clearUpdateStockJFrame()
    {
        updateStockMedicineNameDropDownField.removeAllItems();
        updateStockMedicineGenericNameTextField.setText("");
        updateStockMedicineNameTextField.setText("");
        updateStockCompanyNameTextField.setText("");
        updateStockDistributorNameTextField.setText("");
        updateStockRackNumberTextField.setText("");
        updateStockSalePriceTextField.setText("");
        Date mDate = new Date();
        updateStockManufacturingDateTextField.setDate(mDate);
        updateStockExpiryDateTextField.setDate(mDate);
        updateStockCostPriceTextField.setText("");
        updateStockQuantityTextField.setText("");
    }

    public void clearAddStockJFrame()
    {
        medicineLabelTextField.setText("");
        salesPriceTextField.setText("");
        costPriceTextField.setText("");
        addStockCompanyNameTextField.setText("");
        quantityTextField.setText("");
        addStockItemsPerPackBoxTextField.setText("");
        medicineGenericNameTextField.setText("");

        // TODO : Find out if the clearAddStockJFrame cleanup is actually working
        //manufacturingDateTextField.setDateFormatString("");
        //expiryDateTextField.setDateFormatString("");
        
        //manufacturingDateTextField.setText("");
        //expiryDateTextField.setText("");
        addStockRackNoTextField.setText("");
        addStockDistributorNameTextField.setText("");

        Date mDate = new Date();
        manufacturingDateTextField.setDate(mDate);
        expiryDateTextField.setDate(mDate);
    }

    public static void constructPrintString(SalesReceipt saleReceipt)
    {
        int totalReceiptWidth = 40;
        String vendorName = Configuration.configurations.getProperty("vendorname");
        String vendorAddress = Configuration.configurations.getProperty("vendoraddress");
        String vendorCity = Configuration.configurations.getProperty("vendorcity");
        String vendorPhone = Configuration.configurations.getProperty("vendorphoneno");

        int vendorNameLength = totalReceiptWidth - vendorName.length();
        int vendorNamePreSpaceLength = vendorNameLength/2;

        // Vendor Name
        for(int i=0;i<vendorNamePreSpaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append(vendorName);
        for(int i=0;i<vendorNamePreSpaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append("\n");

        // Vendor address
        int vendorAddressLength = totalReceiptWidth - vendorAddress.length();
        int vendorAddressPrespaceLength = vendorAddressLength/2;

        for(int i=0;i<vendorAddressPrespaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append(vendorAddress);
        for(int i=0;i<vendorAddressPrespaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append("\n");

        // Vendor City
        int vendorCityLength = totalReceiptWidth - vendorCity.length();
        int vendorCityPrespaceLength = vendorCityLength/2;
        for(int i=0;i<vendorCityPrespaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append(vendorCity);
        for(int i=0;i<vendorCityPrespaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append("\n");

        // Vendor Phone Number
        int vendorPhoneLength = totalReceiptWidth - (vendorPhone.length() + new String("Phone # :").length());
        int vendorPhonePrespaceLength = vendorPhoneLength/2;
        for(int i=0;i<vendorPhonePrespaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append("Phone # :");
        printString.append(vendorPhone);
        for(int i=0;i<vendorPhonePrespaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append("\n");
        printString.append("\n");

        // Receipt No and Date Line
        Calendar dateTime = Calendar.getInstance();
        int receiptMiddleSpaceLength = totalReceiptWidth - ( Float.toString(saleReceipt.getReceiptID()).length() + new String("Receipt No. ").length() + dateTime.getTime().toString().length());
        printString.append("Receipt No. ");
        printString.append(saleReceipt.getReceiptID());

        for(int i=0;i<receiptMiddleSpaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append(dateTime.getTime());

        // Remarks and Reference line
        int remarksMiddleSpaceLength = totalReceiptWidth - (new String("Remarks:").length() + printReceiptRemarks.length() + new String("Ref: ").length() + printReceiptReference.length());
        printString.append("\n");

        printString.append("Remarks:");
        printString.append(printReceiptRemarks);
        for(int i=0;i<remarksMiddleSpaceLength;i++)
        {
            printString.append(" ");
        }
        printString.append("Ref: ");
        printString.append(printReceiptReference);
        printString.append("\n");

        for(int i = 0;i< totalReceiptWidth;i++)
        {
            printString.append("-");
        }
        printString.append("\n");

        // Sale Header Line
        int itemNameFieldWidth = (int)((totalReceiptWidth/2) * 0.8);
        printString.append("Item Name");
        int itemNameSpaces = itemNameFieldWidth - new String("Item Name").length();
        for(int i = 0;i< itemNameSpaces;i++)
        {
            printString.append(" ");
        }

        int quantityFieldWidth = (int)((totalReceiptWidth/2) * 0.2);
        printString.append("Qty");
        int quantitySpaces = quantityFieldWidth - new String("Qty").length();
        for(int i = 0;i< quantitySpaces;i++)
        {
            printString.append(" ");
        }

        int priceFieldWidth = (int)((totalReceiptWidth/2) * 0.8);
        printString.append("Price");
        int priceSpaces = priceFieldWidth - new String("Price").length();
        for(int i = 0;i< priceSpaces;i++)
        {
            printString.append(" ");
        }

        int totalFieldWidth = (int)((totalReceiptWidth/2) * 0.2);
        printString.append("Total");
        int totalSpaces = totalFieldWidth - new String("Total").length();
        for(int i = 0;i< totalSpaces;i++)
        {
            printString.append(" ");
        }

        printString.append("\n");
        for(int i = 0;i< totalReceiptWidth;i++)
        {
            printString.append("-");
        }
        printString.append("\n");

        String medicineQuantity = null;
        String medicinePrice = null;
        String medicineName = null;
        float totalPrice = 0;
        // TODO : If receipt columns are modified, then this code should be generic enough to print receipts
        for(int i = 0; i< medicineInReceiptList.size();i++)
        {
            for(int j = 0 ; j < medicineInReceiptList.get(0).size();j++)
            {
                if(j == 0)
                {
                    medicineName = medicineInReceiptList.get(i).get(j);
                }
                else if(j == 5)
                {
                    medicineQuantity = medicineInReceiptList.get(i).get(j);
                    
                }
                else if(j == 3)
                {
                    medicinePrice = medicineInReceiptList.get(i).get(j);
                }
            }

            float medQuantity = Float.parseFloat(medicineQuantity);
            float medPrice = Float.parseFloat(medicinePrice);
            float medTotal = medQuantity * medPrice;

            // Medicine Name
            int medicineNameWidth = (int)((totalReceiptWidth/2) * 0.8);
            int totalMedicineNameSpaces = medicineNameWidth - medicineName.length();
            if(totalMedicineNameSpaces > 0)
            {
                printString.append(medicineName);
                for(int j = 0;j< totalMedicineNameSpaces;j++)
                {
                    printString.append(" ");
                }
            }
            else
            {
                medicineName = medicineName.substring(0, (int)((totalReceiptWidth/2) * 0.8));
                printString.append(medicineName);
            }

            // Medicine Quantity
            int medicineQuantityWidth = (int)((totalReceiptWidth/2) * 0.2);
            int medicineQuantitySpaces = medicineQuantityWidth - medicineQuantity.length();
            if(medicineQuantitySpaces > 0)
            {
                printString.append(medicineQuantity);
                for(int j = 0;j< medicineQuantitySpaces;j++)
                {
                    printString.append(" ");
                }
            }
            else
            {
                medicineQuantity = medicineQuantity.substring(0, (int)((totalReceiptWidth/2) * 0.2));
                printString.append(medicineQuantity);
            }

            // Medicine Price
            int medicinePriceWidth = (int)((totalReceiptWidth/2) * 0.8);
            int medicinePriceSpaces = medicinePriceWidth - medicinePrice.length();
            if(medicinePriceSpaces > 0)
            {
                printString.append(medicinePrice);
                for(int j = 0;j< medicinePriceSpaces;j++)
                {
                    printString.append(" ");
                }
            }
            else
            {
                medicinePrice = medicinePrice.substring(0, (int)((totalReceiptWidth/2) * 0.8));
                printString.append(medicinePrice);
            }

            // Medicine Total
            int medicineTotalWidth = (int)((totalReceiptWidth/2) * 0.2);
            int medicineTotalSpaces = medicineTotalWidth - new Float(medTotal).toString().length();
            if(medicineTotalSpaces > 0)
            {
                printString.append(medTotal);
                for(int j = 0;j< medicineTotalSpaces;j++)
                {
                    printString.append(" ");
                }
            }
            else
            {
                String medTotString = new Float(medTotal).toString().substring(0, (int)((totalReceiptWidth/2) * 0.2));
                printString.append(medTotString);
            }
            totalPrice = totalPrice + medTotal;

            printString.append("\n");
        }
        for(int i = 0;i< totalReceiptWidth;i++)
        {
            printString.append("-");
        }
        printString.append("\n");

        printString.append("Total Items : " + medicineInReceiptList.size());
        printString.append("\n");

        int grossTotalSpaces = totalReceiptWidth - (new String("Gross Total : ").length() + new Float(totalPrice).toString().length());
        for(int i = 0;i< grossTotalSpaces;i++)
        {
            printString.append(" ");
        }

        printString.append("Gross Total : " + totalPrice);
        printString.append("\n");
        String salesManName = SecurityManager.getCurrentlyLoggedUser().userName;
        printString.append(salesManName);
        printString.append("\n");
        printString.append("\n");
        printString.append("For Software Contact : 0321-4937947");
        printString.append("\n");
        printString.append("\n");
        printString.append("For Software Contact : 0304-2348887 ");
    }

    public static void printPrintString()
    {
        String outputString = printString.toString();
        String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
        System.out.println("Default printer: " + defaultPrinter);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        InputStream is=null;
        try {
            outputString+="\f";
            System.out.println(outputString);
            is = new ByteArrayInputStream(outputString.getBytes("UTF8"));                    
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       PrintRequestAttributeSet  pras = new HashPrintRequestAttributeSet();
       pras.add(new Copies(1));

       DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
       Doc doc = new SimpleDoc(is, flavor, null);
       DocPrintJob job = service.createPrintJob();

       //PrintJobWatcher pjw = new PrintJobWatcher(job);
       try {
           job.print(doc, pras);
       } catch (PrintException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       //pjw.waitForDone();
       try {
           is.close();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }		
    }

    public void updateStockFromReturnReceipt(SalesReceipt saleReceipt)
    {
        //Set<String> keySet = saleReceipt.medicineList.keySet();
        for(Medicine medicineObject:saleReceipt.medicineList)
        {
            //Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName.toLowerCase());
            // TODO : Finding out if more than one item could be returned in one go.
            medicineObject.quantity++;

            // TODO: Replace this removal and insertion call with a single update call.
            boolean removeFromDatabaseResult = DatabaseWrapper.removeMedicineFromDatabase(medicineObject);
            boolean databaseInsertionResult = DatabaseWrapper.insertMedicineInDatabase(medicineObject);
        }
    }

    public void updateStockFromReceipt(SalesReceipt saleReceipt)
    {
        for(Medicine medicineObject:saleReceipt.medicineList)
        {
            Medicine medicine = SaadMedicineSaleSystem.medicineList.get(medicineObject.medicineName);

            // TODO : Finding out if more than one item could be returned in one go.
            medicine.quantity = medicine.quantity - medicineObject.quantity;

            // TODO: Replace this removal and insertion call with a single update call.
            boolean result = DatabaseWrapper.updateMedicineInDatabase(medicine);
        }
    }

    public SalesReceipt constructReturnReceipt(SalesReceipt newSalesReceipt)
    {
        SalesReceipt originalReceipt = SaadMedicineSaleSystem.salesReceiptList.get(newSalesReceipt.getReceiptID());
        Calendar dateTime = Calendar.getInstance();
        long returnReceiptNumber = SaadMedicineSaleSystem.returnReceiptList.size();

        SalesReceipt returnReceipt = null;
        // TODO : Get the name of the sales man for keeping record of returns by salesman

        //Set<String> keySet = originalReceipt.medicineList.keySet();
        //for(String medicineName:keySet)
        // TODO : If return items contain multiple over-lapping items with original list, how is this scenario to be handled.

        boolean anyMedicineToReturn = false;

        for(Medicine medicineObject:originalReceipt.medicineList)
        {
            boolean medicineFound = false;
            for(Medicine nMedicineObject:newSalesReceipt.medicineList)
            {
                if(medicineObject.medicineName.equals(nMedicineObject.medicineName) && 
                        medicineObject.quantity == nMedicineObject.quantity)
                {
                    medicineFound = true;
                }
            }
            if(false == medicineFound)
            {
                if(null == returnReceipt)
                {
                    returnReceipt = new SalesReceipt(dateTime.getTime(), "", returnReceiptNumber);
                }
                returnReceipt.medicineList.add(medicineObject);
                returnReceipt.totalPrice = returnReceipt.totalPrice + medicineObject.salesPrice;
                returnReceipt.setSalesManName(SecurityManager.currentlyLoggedUser.userName);
                anyMedicineToReturn = true;
            }
        }

        if(false == anyMedicineToReturn)
        {
            JOptionPane.showMessageDialog(null, "No medicine was removed from original receipt. Please remove some item and again press the print receipt button.");
        }

        return returnReceipt;
    }

    public SalesReceipt constructSalesReceipt()
    {
        long receiptID = 0;
        float saleReceiptPrice = 0;
        if(true == receiptReturnFlag)
        {
            receiptID = returnReceiptID;
        }
        else
        {
            receiptID = SaadMedicineSaleSystem.salesReceiptList.size();
        }

        Calendar dateTime = Calendar.getInstance();

        // TODO : Get the name of the sales man for 
        String salesmanName = SecurityManager.getCurrentlyLoggedUser().userName;
        SalesReceipt saleReceipt = new SalesReceipt(dateTime.getTime(), salesmanName, receiptID);

        for(int i = 0; i< medicineInReceiptList.size();i++)
        {
            String medicineName = medicineInReceiptList.get(i).get(0);
            String medicineQuantity = medicineInReceiptList.get(i).get(5);
            Medicine medicine = SaadMedicineSaleSystem.medicineList.get(medicineName);

            // TODO : Find if there is any efficient way to copy
            Medicine medicineObject = new Medicine();
            medicineObject.quantity = Long.parseLong(medicineQuantity);
            medicineObject.medicineName = medicine.medicineName;
            medicineObject.companyName = medicine.companyName;
            medicineObject.MedicineID = medicine.MedicineID;
            medicineObject.costPrice = medicine.costPrice;
            medicineObject.discount = medicine.discount;
            medicineObject.distributorName = medicine.distributorName;
            medicineObject.salesPrice = medicine.salesPrice;
            medicineObject.expiryDate = medicine.expiryDate;
            medicineObject.manfacturingDate = medicine.manfacturingDate;
            medicineObject.rackNumber = medicine.rackNumber;
            saleReceiptPrice = saleReceiptPrice + (medicineObject.salesPrice * medicineObject.quantity);

            saleReceipt.addMedicineToList(medicineObject);
        }

        float overallDiscount = Float.parseFloat(receiptJFrameOverallDiscount.getText());
        saleReceipt.totalPrice = saleReceiptPrice - overallDiscount;
        return saleReceipt;
    }

    private void processReturnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processReturnMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ProcessReturns"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        
        // TODO add your handling code here:
        try
        {
            String receiptID = JOptionPane.showInputDialog("Enter the Receipt ID");
            SalesReceipt sReceipt = SaadMedicineSaleSystem.salesReceiptList.get(Long.parseLong(receiptID));
            if(null == sReceipt)
            {
                JOptionPane.showMessageDialog(null, "Sale Receipt ID is not Valid");
            }
            else
            {
            receiptReturnFlag = true;
            returnReceiptID = sReceipt.getReceiptID();
            showReceiptInMainFrame(sReceipt);
        } }catch (Exception ex)
        {
            
        }
    }//GEN-LAST:event_processReturnMenuActionPerformed

    public void showReceiptInMainFrame(SalesReceipt receipt)
    {
        try
        {
            clearReceiptTable();
            float returnReceiptTotalPrice = 0;
            for(Medicine medicine:receipt.medicineList)
            {
                ArrayList<String> medicineInfo = new ArrayList<String>();
                medicineInfo.add(medicine.medicineName);
                medicineInfo.add(medicine.companyName);
                medicineInfo.add(medicine.rackNumber);
                medicineInfo.add(Float.toString(medicine.salesPrice));
                medicineInfo.add(Float.toString(medicine.discount));
                medicineInfo.add(Long.toString(medicine.quantity));
                returnReceiptTotalPrice = returnReceiptTotalPrice + (medicine.salesPrice * medicine.quantity);
                medicineInReceiptList.add(medicineInfo);

                if(medicineInReceiptList.size() > receiptTable.getRowCount())
                {
                    generateTableModelForTableData(receiptTable, medicineInReceiptList);
                }

                for(int i = 0; i< medicineInReceiptList.size();i++)
                {
                    for(int j = 0 ; j < medicineInReceiptList.get(0).size();j++)
                    {
                        receiptTable.setValueAt(medicineInReceiptList.get(i).get(j), i, j);
                    }
                }

                receiptTable.repaint();
                totalPriceTextField.disable();
                totalPriceTextField.setText(Float.toString(returnReceiptTotalPrice  - overallDiscount));
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void showReceiptsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showReceiptsButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            totalSalesInDateRangeSalesReceipt.clear();
            cleartotalSalesInDateRangeReceiptTable();
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            Date startDate = null;
            Date endDate = null;
            startDate = receiptsStartDateTextField.getDate();
            endDate = receiptsEndDateTextField.getDate();

            float totalSales = 0;
            float netProfit = 0;
            for(Long sreceiptID : SaadMedicineSaleSystem.salesReceiptList.keySet())
            {
                SalesReceipt sreceipt = SaadMedicineSaleSystem.salesReceiptList.get(sreceiptID);
                long sreceiptTime = sreceipt.getSalesDate().getTime();
                if(
                    sreceipt.getSalesDate().after(startDate) &&
                    sreceipt.getSalesDate().before(endDate)
                  )
                {
                    for(Medicine med:sreceipt.medicineList)
                    {
                        netProfit = netProfit + ((med.salesPrice -  med.costPrice) * med.quantity);
                    }

                    ArrayList<String> saleReceiptInfo = new ArrayList<String>();
                    saleReceiptInfo.add(Long.toString(sreceipt.getReceiptID()));
                    saleReceiptInfo.add(sreceipt.getSalesDate().toString());
                    saleReceiptInfo.add(Float.toString(sreceipt.getTotalSales()));
                    totalSalesInDateRangeSalesReceipt.add(saleReceiptInfo);
                    totalSales = sreceipt.totalPrice + totalSales;
                }
            }
            salesReceiptTotalTextField.setText(Float.toString(totalSales));
            salesReceiptNetProfitTextField.setText(Float.toString(netProfit));

            if(totalSalesInDateRangeSalesReceipt.size() > totalSalesInDateRangeReceiptTable.getRowCount())
            {
                generateTableModelForTableData(totalSalesInDateRangeReceiptTable, totalSalesInDateRangeSalesReceipt);
            }

            for(int i = 0; i< totalSalesInDateRangeSalesReceipt.size();i++)
            {
                for(int j = 0; j < totalSalesInDateRangeSalesReceipt.get(0).size();j++)
                {
                    totalSalesInDateRangeReceiptTable.setValueAt(totalSalesInDateRangeSalesReceipt.get(i).get(j), i, j);
                }
            }
        }catch(Exception ex)
        {
                ex.printStackTrace();
        }
    }//GEN-LAST:event_showReceiptsButtonActionPerformed

    private void totalSalesInDateRangeReceiptTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalSalesInDateRangeReceiptTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalSalesInDateRangeReceiptTableMouseClicked

    private void totalSalesInDateRangeReceiptTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalSalesInDateRangeReceiptTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalSalesInDateRangeReceiptTableKeyTyped

    private void totalSalesInDateRangeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSalesInDateRangeMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ViewReports"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = TotalSalesInDateRange;
        TotalSalesInDateRange.show();
    }//GEN-LAST:event_totalSalesInDateRangeMenuActionPerformed

    private void totalMedicineSaleMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMedicineSaleMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ViewReports"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                    
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = TotalMedicineSaleJFrame;
        TotalMedicineSaleJFrame.show();
    }//GEN-LAST:event_totalMedicineSaleMenuActionPerformed

    private void totalMedicineSaleMedicineNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMedicineSaleMedicineNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMedicineSaleMedicineNameTextFieldActionPerformed

    private void showMedicineSalesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMedicineSalesButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearTotalmedicineSaleTable();
            totalMedicineSaleTableData.clear();
            String medicineName = totalMedicineSaleMedicineNameTextField.getText();

            float medicineSalesTotal = 0;
            float netProfit = 0;

            Date startDate = totalMedicineSaleStartDateTextField.getDate();
            Date endDate = totalMedicineSaleEndDateTextField.getDate();

            // TODO : Finding out which is more optimal (database search or list search)
            Set<Long> keyset = SaadMedicineSaleSystem.salesReceiptList.keySet();
            for(Long ln:keyset)
            {
                SalesReceipt sReceipt = SaadMedicineSaleSystem.salesReceiptList.get(ln);
                if(sReceipt.getSalesDate().after(startDate) && sReceipt.getSalesDate().before(endDate))
                {
                    // Set<String> mNames = sReceipt.medicineList.keySet();
                    // for(String mName:mNames)
                    // {
                    for(Medicine medicine:sReceipt.medicineList)
                    {
                        if(medicineName.toLowerCase().equalsIgnoreCase(medicine.medicineName))
                        {
                            // TODO : Remove the following null check
                            ArrayList<String> tableRow = new ArrayList<String>();
                            tableRow.add(Long.toString(sReceipt.getReceiptID()));
                            tableRow.add(sReceipt.getSalesDate().toString());
                            tableRow.add(Float.toString((medicine.salesPrice * medicine.quantity)));
                            totalMedicineSaleTableData.add(tableRow);
                            medicineSalesTotal = medicineSalesTotal + (medicine.salesPrice * medicine.quantity);
                            netProfit = netProfit + ((medicine.salesPrice - medicine.costPrice) * medicine.quantity);
                        }
                    }
                }
            }

            totalMedicineSaleTextField.setText(Float.toString(medicineSalesTotal));
            totalMedicineSaleNetProfitTextField.setText(Float.toString(netProfit));

            if(totalMedicineSaleTableData.size() > totalmedicineSaleTable.getRowCount())
            {
                generateTableModelForTableData(totalmedicineSaleTable, totalMedicineSaleTableData);
            }

            for(int i = 0; i< totalMedicineSaleTableData.size();i++)
            {
                for(int j = 0; j < totalMedicineSaleTableData.get(0).size();j++)
                {
                    totalmedicineSaleTable.setValueAt(totalMedicineSaleTableData.get(i).get(j), i, j);
                }
            }
        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_showMedicineSalesButtonActionPerformed

    private void totalmedicineSaleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalmedicineSaleTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalmedicineSaleTableMouseClicked

    private void totalmedicineSaleTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalmedicineSaleTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalmedicineSaleTableKeyTyped

    private void totalMedicineSaleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMedicineSaleTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMedicineSaleTextFieldActionPerformed

    private void totalMedicineSaleNetProfitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMedicineSaleNetProfitTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMedicineSaleNetProfitTextFieldActionPerformed

    private void printReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceiptActionPerformed
        // TODO add your handling code here:
//        if(false == postOnServer)
//        {
//           
//        }
//        else
//        {
           // SalesReceipt saleReceipt = constructSalesReceipt();
//            ServerCommunicator.postReceiptOnServer(saleReceipt);
        //}
         printReceiptFromMainFrame();
    }//GEN-LAST:event_printReceiptActionPerformed

    private void printReceiptFromMainFrame()
    {
        MainFrame.state = RECEIPT_PRINTED;
        if(medicineInReceiptList.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "No medicine added to receipt. Please add medicine and then press \"Print Receipt\" button" );
            return;
        }
        if(false == receiptReturnFlag)
        {
            // TODO : Sales Receipt ID is to be printed on the print string also.
            SalesReceipt saleReceipt = constructSalesReceipt();
            constructPrintString(saleReceipt);
            printPrintString();
            SaadMedicineSaleSystem.salesReceiptList.put(saleReceipt.getReceiptID(), saleReceipt);
            DatabaseWrapper.insertReceiptInDatabase(saleReceipt);
            updateStockFromReceipt(saleReceipt);
        }
        else
        {
            SalesReceipt newSalesReceipt = constructSalesReceipt();
            SalesReceipt returnReceipt = constructReturnReceipt(newSalesReceipt);
            if(null == returnReceipt)
            {
                return;
            }

            SaadMedicineSaleSystem.salesReceiptList.remove(newSalesReceipt.getReceiptID());
            SaadMedicineSaleSystem.salesReceiptList.put(newSalesReceipt.getReceiptID(), newSalesReceipt);
            SaadMedicineSaleSystem.returnReceiptList.put(returnReceipt.getReceiptID(), returnReceipt);

            // TODO : 
            DatabaseWrapper.updateReceiptInDatabase(newSalesReceipt);
            DatabaseWrapper.insertReturnReceiptInDB(returnReceipt);
            receiptReturnFlag = false;
        }

        clearReceiptJFrame();
        MainFrame.state = RECEIPT_IDLE;    
    }
    
    private void clearReceiptJFrame()
    {
        medicineNameDropDownField.removeAllItems();
        totalPriceTextField.setText("");
        receiptJFrameOverallDiscount.setText("");
        medicineInReceiptList.clear();
        clearReceiptTable();
    }

    private void receiptTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_receiptTableKeyTyped
        if(evt.getKeyChar() == Event.DELETE)
        {
            receiptTableRowDeleted();
        }
    }//GEN-LAST:event_receiptTableKeyTyped

    private void receiptTableRowDeleted()
    {
        state = ROW_DELETED;
        float medicinePrice = 0;
        long medicineQuantity = 0;

        float rTotalPrice = Float.parseFloat(totalPriceTextField.getText());
        if(rTotalPrice <= 0.0)
        {
            return;
        }

        if(-1 != receiptTableFocusedRow)
        {
            medicinePrice = Float.parseFloat(medicineInReceiptList.get(receiptTableFocusedRow).get(3));
            medicineQuantity = Long.parseLong(medicineInReceiptList.get(receiptTableFocusedRow).get(5));
            medicineInReceiptList.remove(receiptTableFocusedRow);

            clearReceiptTable();
            // TODO : Duplicated code to be removed
            for(int i = 0; i< medicineInReceiptList.size();i++)
            {
                for(int j = 0 ; j < medicineInReceiptList.get(0).size();j++)
                {
                    receiptTable.setValueAt(medicineInReceiptList.get(i).get(j), i, j);
                }
            }
        }

        // TODO : Duplicated code to be removed
        receiptTable.repaint();
        rTotalPrice = rTotalPrice - (medicinePrice * medicineQuantity);
        if(rTotalPrice <= 0 || medicineInReceiptList.size() == 0)
        {
            rTotalPrice = 0;
        }
        totalPriceTextField.setText(Float.toString(rTotalPrice  - overallDiscount));
        state = RECEIPT_IDLE;
    
    }
    private void receiptTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receiptTableMouseClicked
        // TODO add your handling code here:
        java.awt.Point p = new java.awt.Point();
        p = evt.getPoint();
        receiptTableFocusedRow = receiptTable.rowAtPoint(p);
    }//GEN-LAST:event_receiptTableMouseClicked

    private void companySalesDistributorTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companySalesDistributorTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companySalesDistributorTextFieldActionPerformed

    private void companySalesCompanyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companySalesCompanyTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companySalesCompanyTextFieldActionPerformed

    private void companySalesMedicineNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companySalesMedicineNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companySalesMedicineNameTextFieldActionPerformed

    private void totalCompanySaleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalCompanySaleTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCompanySaleTableMouseClicked

    private void totalCompanySaleTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalCompanySaleTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCompanySaleTableKeyTyped

    private void companySalesTotalSalesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companySalesTotalSalesTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companySalesTotalSalesTextFieldActionPerformed

    private void companySalesNetProfitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companySalesNetProfitTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companySalesNetProfitTextFieldActionPerformed

    private void totalSalesmanSaleSalemanNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSalesmanSaleSalemanNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalSalesmanSaleSalemanNameTextFieldActionPerformed

    private void totalSalesManSaleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalSalesManSaleTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalSalesManSaleTableMouseClicked

    private void totalSalesManSaleTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalSalesManSaleTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalSalesManSaleTableKeyTyped

    private void totalSalemanSaleTotalSalesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSalemanSaleTotalSalesTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalSalemanSaleTotalSalesTextFieldActionPerformed

    private void totalReturnsShowReturnsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsShowReturnsButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearTotalReturnsByDateReturnsTable();
            totalReturnsByDateRangeTableData.clear();
            float totalReturns = 0;

            // TODO : Discuss if adding date to GUI would be useful.
            Date startDate = totalReturnsStartDateTextField.getDate();
            Date endDate = totalReturnsEndDateTextField.getDate();

            // TODO : Finding out which is more optimal (database search or list search)
            Set<Long> keyset = SaadMedicineSaleSystem.returnReceiptList.keySet();
            for(Long ln:keyset)
            {
                SalesReceipt sReceipt = SaadMedicineSaleSystem.returnReceiptList.get(ln);
                if(sReceipt.getSalesDate().after(startDate) && sReceipt.getSalesDate().before(endDate))
                {
                    ArrayList<String> sDate = new ArrayList<String>();
                    sDate.add(Long.toString(sReceipt.getReceiptID()));
                    sDate.add(sReceipt.getSalesDate().toString());
                    sDate.add(Float.toString(sReceipt.getTotalSales()));
                    totalReturnsByDateRangeTableData.add(sDate);
                    totalReturns = totalReturns + sReceipt.totalPrice;
                        //netProfit = netProfit + (medicineObject.costPrice - medicineObject.salesPrice);
                }
            }

            if(totalReturnsByDateRangeTableData.size() > totalReturnsByDateReturnsTable.getRowCount())
            {
                generateTableModelForTableData(totalReturnsByDateReturnsTable, totalReturnsByDateRangeTableData);                
            }

            for(int i = 0; i< totalReturnsByDateRangeTableData.size();i++)
            {
                for(int j = 0; j < totalReturnsByDateRangeTableData.get(0).size();j++)
                {
                    totalReturnsByDateReturnsTable.setValueAt(totalReturnsByDateRangeTableData.get(i).get(j), i, j);
                }
            }
            totalReturnsTotalTextField.setText(Float.toString(totalReturns));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_totalReturnsShowReturnsButtonActionPerformed

    private void totalReturnsByDateReturnsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalReturnsByDateReturnsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByDateReturnsTableMouseClicked

    private void totalReturnsByDateReturnsTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalReturnsByDateReturnsTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByDateReturnsTableKeyTyped

    private void totalReturnsTotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsTotalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsTotalTextFieldActionPerformed

    private void totalReturnsByMedicineMedicineNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByMedicineMedicineNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByMedicineMedicineNameTextFieldActionPerformed

    private void totalReturnsByMedicineClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByMedicineClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_totalReturnsByMedicineClearButtonActionPerformed

    private void totalReturnsByMedicineReturnsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalReturnsByMedicineReturnsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByMedicineReturnsTableMouseClicked

    private void totalReturnsByMedicineReturnsTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalReturnsByMedicineReturnsTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByMedicineReturnsTableKeyTyped

    private void totalMedicineReturnsByMedicineReturnsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMedicineReturnsByMedicineReturnsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMedicineReturnsByMedicineReturnsTextFieldActionPerformed

    private void totalReturnsByCompanyDistributorNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyDistributorNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByCompanyDistributorNameTextFieldActionPerformed

    private void totalReturnsByCompanyCompanyNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyCompanyNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByCompanyCompanyNameTextFieldActionPerformed

    private void totalReturnsByCompanyMedicineNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyMedicineNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByCompanyMedicineNameTextFieldActionPerformed

    private void totalReturnsByCompanyReturnsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyReturnsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByCompanyReturnsTableMouseClicked

    private void totalReturnsByCompanyReturnsTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyReturnsTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByCompanyReturnsTableKeyTyped

    private void totalReturnsByCompanyReturnsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyReturnsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsByCompanyReturnsTextFieldActionPerformed

    private void totalReturnsBySalesmanNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsBySalesmanNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsBySalesmanNameTextFieldActionPerformed

    private void totalReturnsBySalesmanTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalReturnsBySalesmanTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsBySalesmanTableMouseClicked

    private void totalReturnsBySalesmanTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalReturnsBySalesmanTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsBySalesmanTableKeyTyped

    private void totalReturnsBySalesmanReturnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsBySalesmanReturnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalReturnsBySalesmanReturnTextFieldActionPerformed

    private void shortItemsCompanyDistributorNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsCompanyDistributorNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsCompanyDistributorNameTextFieldActionPerformed

    private void shortItemsCompanyNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsCompanyNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsCompanyNameTextFieldActionPerformed

    private void shortItemsByCompanyShowShortMedicineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsByCompanyShowShortMedicineButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearShortItemsByCompanyShortItemsTable();
            shortItemsByCompanyShortMedicineTableData.clear();

            String distributorName = shortItemsCompanyDistributorNameTextField.getText();
            String companyName = shortItemsCompanyNameTextField.getText();
            long itemCount = Long.parseLong(shortItemsCompanySpinner.getValue().toString());

            // TODO : Finding out which is more optimal (database search or list search)
            Set<String> keyset = SaadMedicineSaleSystem.medicineList.keySet();
            for(String medicineName:keyset)
            {
                Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName);
                if(medicineObject.quantity < itemCount && 
                        medicineObject.companyName.equalsIgnoreCase(companyName) &&
                                medicineObject.distributorName.equalsIgnoreCase(distributorName)
                    )
                {
                    ArrayList<String> mDate = new ArrayList<String>();
                    mDate.add(medicineObject.medicineName);
                    mDate.add(medicineObject.companyName);
                    mDate.add(medicineObject.rackNumber);
                    mDate.add(Float.toString(medicineObject.salesPrice));
                    mDate.add(Float.toString(medicineObject.discount));

                    shortItemsByCompanyShortMedicineTableData.add(mDate);
                }
            }

            generateTableModelForTableData(shortItemsByCompanyShortItemsTable, shortItemsByCompanyShortMedicineTableData);

            for(int i = 0; i< shortItemsByCompanyShortMedicineTableData.size();i++)
            {
                for(int j = 0; j < shortItemsByCompanyShortMedicineTableData.get(0).size();j++)
                {
                    shortItemsByCompanyShortItemsTable.setValueAt(shortItemsByCompanyShortMedicineTableData.get(i).get(j), i, j);
                }
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_shortItemsByCompanyShowShortMedicineButtonActionPerformed

    private void shortItemsByCompanyShortItemsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shortItemsByCompanyShortItemsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsByCompanyShortItemsTableMouseClicked

    private void shortItemsByCompanyShortItemsTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shortItemsByCompanyShortItemsTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsByCompanyShortItemsTableKeyTyped

    private void shortItemsOverallShowShortMedicineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOverallShowShortMedicineButtonActionPerformed

        try
        {
            // TODO add your handling code here:
            shortItemsOverallTableData.clear();
            clearShortItemsOverAllMedicineTable();

            long itemCount = Long.parseLong(shortItemsCompanySpinner.getValue().toString());

            long medicineSalesTotal = 0;
            long netProfit = 0;

            // TODO : Finding out which is more optimal (database search or list search)
            Set<String> keyset = SaadMedicineSaleSystem.medicineList.keySet();
            for(String medicineName:keyset)
            {
                Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName);
                if(medicineObject.quantity < itemCount)
                {
                    ArrayList<String> mDate = new ArrayList<String>();
                    mDate.add(medicineObject.medicineName);
                    mDate.add(medicineObject.companyName);
                    mDate.add(medicineObject.rackNumber);
                    mDate.add(Float.toString(medicineObject.salesPrice));
                    mDate.add(Float.toString(medicineObject.discount));

                    shortItemsOverallTableData.add(mDate);
                }
            }

            if(shortItemsOverallTableData.size() > shortItemsOverAllMedicineTable.getRowCount())
            {
                generateTableModelForTableData(shortItemsOverAllMedicineTable, shortItemsOverallTableData);
            }

            for(int i = 0; i< shortItemsOverallTableData.size();i++)
            {
                for(int j = 0; j < shortItemsOverallTableData.get(0).size();j++)
                {
                    shortItemsOverAllMedicineTable.setValueAt(shortItemsOverallTableData.get(i).get(j), i, j);
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_shortItemsOverallShowShortMedicineButtonActionPerformed

    private void shortItemsOverAllMedicineTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shortItemsOverAllMedicineTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsOverAllMedicineTableMouseClicked

    private void shortItemsOverAllMedicineTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shortItemsOverAllMedicineTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsOverAllMedicineTableKeyTyped

    private void shortItemsOnRackDistributorNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOnRackDistributorNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsOnRackDistributorNameTextFieldActionPerformed

    private void shortItemsOnRackCompanyNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOnRackCompanyNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsOnRackCompanyNameTextFieldActionPerformed

    private void shortItemsOnRackShowShortMedicineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOnRackShowShortMedicineButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearShortItemsOnRackTable();
            shortItemsOnRackTableData.clear();
            String distributorName = shortItemsCompanyDistributorNameTextField.getText();
            String companyName = shortItemsCompanyNameTextField.getText();
            String rackNo = shortItemsRackNoTextField.getText();
            long itemCount = Long.parseLong(shortItemsByRackSpinner.getValue().toString());

            // TODO : Finding out which is more optimal (database search or list search)
            Set<String> keyset = SaadMedicineSaleSystem.medicineList.keySet();
            for(String medicineName:keyset)
            {
                Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName);
                if(medicineObject.quantity < itemCount && 
                        medicineObject.companyName.equalsIgnoreCase(companyName) &&
                                medicineObject.distributorName.equalsIgnoreCase(distributorName) &&
                                    medicineObject.rackNumber.equalsIgnoreCase(rackNo)
                    )
                {
                    ArrayList<String> mDate = new ArrayList<String>();
                    mDate.add(medicineObject.medicineName);
                    mDate.add(medicineObject.companyName);
                    mDate.add(medicineObject.rackNumber);
                    mDate.add(Float.toString(medicineObject.salesPrice));
                    mDate.add(Float.toString(medicineObject.discount));

                    shortItemsOnRackTableData.add(mDate);
                }
            }

            if(shortItemsOnRackTableData.size() > shortItemsOnRackTable.getRowCount())
            {
                generateTableModelForTableData(shortItemsOnRackTable, shortItemsOnRackTableData);
            }

            for(int i = 0; i< shortItemsOnRackTableData.size();i++)
            {
                for(int j = 0; j < shortItemsOnRackTableData.get(0).size();j++)
                {
                    shortItemsOnRackTable.setValueAt(shortItemsOnRackTableData.get(i).get(j), i, j);
                }
            }

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_shortItemsOnRackShowShortMedicineButtonActionPerformed

    private void shortItemsOnRackTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shortItemsOnRackTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsOnRackTableMouseClicked

    private void shortItemsOnRackTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shortItemsOnRackTableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsOnRackTableKeyTyped

    private void shortItemsRackNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsRackNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shortItemsRackNoTextFieldActionPerformed

    private void totalCompanySaleMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCompanySaleMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ViewReports"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = TotalCompanySaleJFrame;
        TotalCompanySaleJFrame.show();
    }//GEN-LAST:event_totalCompanySaleMenuActionPerformed

    private void totalSalesmanSaleMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSalesmanSaleMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ViewReports"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = TotalSalesManSalesJFrame;
        TotalSalesManSalesJFrame.show();
    }//GEN-LAST:event_totalSalesmanSaleMenuActionPerformed

    private void showComanySalesShowSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showComanySalesShowSalesActionPerformed
        // TODO add your handling code here:
        try
        {
            // To remove previous entries from table
            clearTotalCompanySaleTable();
            totalCompanySaleTableData.clear();
            String distributorName = companySalesDistributorTextField.getText();
            String companyName = companySalesCompanyTextField.getText();
            String medicineName = companySalesMedicineNameTextField.getText();

            float companySalesTotal = 0;
            float netProfit = 0;

            // TODO : Finding out which is more optimal (database search or list search)
            Set<Long> keyset = SaadMedicineSaleSystem.salesReceiptList.keySet();
            for(Long ln:keyset)
            {
                SalesReceipt sReceipt = SaadMedicineSaleSystem.salesReceiptList.get(ln);
                for(Medicine medicineObject:sReceipt.medicineList)
                {
                    if( 
                        (null != distributorName && distributorName.equals(medicineObject.distributorName)) ||
                        (null != companyName && companyName.equals(medicineObject.companyName)) || 
                        (null != medicineName && medicineName.toLowerCase().equals(medicineObject.medicineName))
                      )
                    {
                        ArrayList<String> tableRow = new ArrayList<String>();
                        tableRow.add(Long.toString(sReceipt.getReceiptID()));
                        tableRow.add(sReceipt.getSalesDate().toString());
                        tableRow.add(Float.toString(medicineObject.salesPrice));
                        totalCompanySaleTableData.add(tableRow);
                        companySalesTotal = companySalesTotal + medicineObject.salesPrice;
                        netProfit = netProfit + (medicineObject.salesPrice - medicineObject.costPrice);
                    }
                }
            }

            if(totalCompanySaleTableData.size() > totalCompanySaleTable.getRowCount())
            {
                generateTableModelForTableData(totalCompanySaleTable, totalCompanySaleTableData);
            }

            for(int i = 0; i< totalCompanySaleTableData.size();i++)
            {
                for(int j = 0; j < totalCompanySaleTableData.get(0).size();j++)
                {
                    totalCompanySaleTable.setValueAt(totalCompanySaleTableData.get(i).get(j), i, j);
                }
            }

            companySalesTotalSalesTextField.setText(Float.toString(companySalesTotal));
            companySalesNetProfitTextField.setText(Float.toString(netProfit));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_showComanySalesShowSalesActionPerformed

    private void showSalesmanSaleShowSalesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSalesmanSaleShowSalesButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearTotalSalesManSaleTable();
            totalSalesManSalesTableData.clear();

            String salesmanName = totalSalesmanSaleSalemanNameTextField.getText();

            SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
            float salesmanSalesTotal = 0;
            // TODO : Discuss if adding date to GUI would be useful.
            Date startDate = totalSalesmanSaleStartDateTextField.getDate();
            Date endDate = totalSalemanSalesEndDateTextField.getDate();

            // TODO : Finding out which is more optimal (database search or list search)
            Set<Long> keyset = SaadMedicineSaleSystem.salesReceiptList.keySet();
            for(Long ln:keyset)
            {
                SalesReceipt sReceipt = SaadMedicineSaleSystem.salesReceiptList.get(ln);
                if(sReceipt.getSalesDate().after(startDate) && sReceipt.getSalesDate().before(endDate))
                {
                    if(null != salesmanName && salesmanName.equals(sReceipt.getSalesManName()))
                    {
                        ArrayList<String> sDate = new ArrayList<String>();
                        sDate.add(Long.toString(sReceipt.getReceiptID()));
                        sDate.add(sReceipt.getSalesDate().toString());
                        sDate.add(Float.toString(sReceipt.getTotalSales()));
                        totalSalesManSalesTableData.add(sDate);
                        salesmanSalesTotal = salesmanSalesTotal + sReceipt.totalPrice;
                        //netProfit = netProfit + (medicineObject.costPrice - medicineObject.salesPrice);
                    }
                }
            }

            if(totalSalesManSalesTableData.size() > totalSalesManSaleTable.getRowCount())
            {
                generateTableModelForTableData(totalSalesManSaleTable, totalSalesManSalesTableData);
            }

            for(int i = 0; i< totalSalesManSalesTableData.size();i++)
            {
                for(int j = 0; j < totalSalesManSalesTableData.get(0).size();j++)
                {
                    totalSalesManSaleTable.setValueAt(totalSalesManSalesTableData.get(i).get(j), i, j);
                }
            }

            totalSalemanSaleTotalSalesTextField.setText(Float.toString(salesmanSalesTotal));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //companySalesNetProfitTextField.setText(Long.toString(netProfit));
    }//GEN-LAST:event_showSalesmanSaleShowSalesButtonActionPerformed

    private void totalReturnsByCompanyShowReturnsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyShowReturnsButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearTotalReturnsByCompanyReturnsTable();
            totalReturnsByCompanyReturnsTableData.clear();
            String distributorName = totalReturnsByCompanyDistributorNameTextField.getText();
            String companyName = totalReturnsByCompanyCompanyNameTextField.getText();
            String medicineName = totalReturnsByCompanyMedicineNameTextField.getText();

            SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
            float companyReturnsTotal = 0;
            float netProfit = 0;
            // TODO : Discuss if adding date to GUI would be useful.

            // TODO : Finding out which is more optimal (database search or list search)
            Set<Long> keyset = SaadMedicineSaleSystem.returnReceiptList.keySet();
            for(Long ln:keyset)
            {
                SalesReceipt sReceipt = SaadMedicineSaleSystem.returnReceiptList.get(ln);
                for(Medicine medicineObject:sReceipt.medicineList)
                {
                    if( (null != distributorName && !distributorName.equalsIgnoreCase("") && distributorName.equals(medicineObject.distributorName)) ||
                            null != companyName && !companyName.equalsIgnoreCase("") && companyName.equals(medicineObject.companyName) ||
                                medicineName != null && !medicineName.equalsIgnoreCase("") && medicineName.toLowerCase().equals(medicineObject.medicineName)
                       )
                    {
                        ArrayList<String> mDate = new ArrayList<String>();
                        mDate.add(Long.toString(sReceipt.getReceiptID()));
                        mDate.add(sReceipt.getSalesDate().toString());
                        mDate.add(Float.toString(medicineObject.salesPrice));
                        totalReturnsByCompanyReturnsTableData.add(mDate);
                        companyReturnsTotal = companyReturnsTotal + medicineObject.salesPrice;
                    }
                }
            }

            if(totalReturnsByCompanyReturnsTableData.size() > totalReturnsByCompanyReturnsTable.getRowCount())
            {
                generateTableModelForTableData(totalReturnsByCompanyReturnsTable, totalReturnsByCompanyReturnsTableData);
            }

            for(int i = 0; i< totalReturnsByCompanyReturnsTableData.size();i++)
            {
                for(int j = 0; j < totalReturnsByCompanyReturnsTableData.get(0).size();j++)
                {
                    totalReturnsByCompanyReturnsTable.setValueAt(totalReturnsByCompanyReturnsTableData.get(i).get(j), i, j);
                }
            }

            totalReturnsByCompanyReturnsTextField.setText(Float.toString(companyReturnsTotal));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_totalReturnsByCompanyShowReturnsButtonActionPerformed

    private void totalReturnsBySalesManShowReturnsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsBySalesManShowReturnsButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearTotalReturnsBySalesmanTable();
            totalReturnsBySalesmanTableData.clear();
            String salesmanName = totalReturnsBySalesmanNameTextField.getText();

            float salesmanReturnTotal = 0;

            // TODO : Discuss if adding date to GUI would be useful.
            Date startDate = totalReturnsBySalesManStartDateTextField.getDate();
            Date endDate = totalReturnsBySalesmanEndDateTextField.getDate();

            // TODO : Finding out which is more optimal (database search or list search)
            Set<Long> keyset = SaadMedicineSaleSystem.returnReceiptList.keySet();
            for(Long ln:keyset)
            {
                SalesReceipt sReceipt = SaadMedicineSaleSystem.returnReceiptList.get(ln);
                if(sReceipt.getSalesDate().after(startDate) && sReceipt.getSalesDate().before(endDate))
                {
                    if(null != salesmanName && salesmanName.equals(sReceipt.getSalesManName()))
                    {
                        ArrayList<String> sDate = new ArrayList<String>();
                        sDate.add(Long.toString(sReceipt.getReceiptID()));
                        sDate.add(sReceipt.getSalesDate().toString());
                        sDate.add(Float.toString(sReceipt.getTotalSales()));
                        totalReturnsBySalesmanTableData.add(sDate);
                        salesmanReturnTotal = salesmanReturnTotal + sReceipt.totalPrice;
                    }
                }
            }

            if(totalReturnsBySalesmanTableData.size() > totalReturnsBySalesmanTable.getRowCount())
            {
                generateTableModelForTableData(totalReturnsBySalesmanTable, totalReturnsBySalesmanTableData);
            }

            for(int i = 0; i< totalReturnsBySalesmanTableData.size();i++)
            {
                for(int j = 0; j < totalReturnsBySalesmanTableData.get(0).size();j++)
                {
                    totalReturnsBySalesmanTable.setValueAt(totalReturnsBySalesmanTableData.get(i).get(j), i, j);
                }
            }

            totalReturnsBySalesmanReturnTextField.setText(Float.toString(salesmanReturnTotal));
        } catch (Exception ex)
        {
            
        }
    }//GEN-LAST:event_totalReturnsBySalesManShowReturnsButtonActionPerformed

    private void totalReturnsByDateMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByDateMenuActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = TotalReturnsByDateJFrame;
        TotalReturnsByDateJFrame.show();
    }//GEN-LAST:event_totalReturnsByDateMenuActionPerformed

    private void overallShortItemsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overallShortItemsMenuActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = ShortItemsOverallJFrame;
        ShortItemsOverallJFrame.show();
    }//GEN-LAST:event_overallShortItemsMenuActionPerformed

    private void totalReturnsByMedicineMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByMedicineMenuActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = TotalReturnsByMedicineJFrame;
        TotalReturnsByMedicineJFrame.show();
    }//GEN-LAST:event_totalReturnsByMedicineMenuActionPerformed

    private void totalReturnsByCompanyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyMenuActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = TotalReturnsByCompanyJFrame;
        TotalReturnsByCompanyJFrame.show();
    }//GEN-LAST:event_totalReturnsByCompanyMenuActionPerformed

    private void totalReturnsBySalesmanMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsBySalesmanMenuActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        currentOnTopFrame.hide();
        currentOnTopFrame = TotalReturnsSalesManJFrame;
        TotalReturnsSalesManJFrame.show();
    }//GEN-LAST:event_totalReturnsBySalesmanMenuActionPerformed

    private void configureDiscountMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureDiscountMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ConfigureDiscount"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        String overallDiscount = JOptionPane.showInputDialog("Enter overall discount percentage (%)");
        if(!overallDiscount.equals(""))
        {
            overallDiscountPercentage = Long.parseLong(overallDiscount);
        }
    }//GEN-LAST:event_configureDiscountMenuActionPerformed

    private void shortItemsByCompanyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsByCompanyMenuActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        currentOnTopFrame.hide();
        currentOnTopFrame = ShortItemsCompanyJFrame;
        ShortItemsCompanyJFrame.show();
    }//GEN-LAST:event_shortItemsByCompanyMenuActionPerformed

    private void shortItemsOnRackMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOnRackMenuActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        currentOnTopFrame.hide();
        currentOnTopFrame = ShortItemsOnRackJFrame;
        ShortItemsOnRackJFrame.show();
    }//GEN-LAST:event_shortItemsOnRackMenuActionPerformed

    private void updateStockMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("AddUpdateStock"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        //String medicineName = JOptionPane.showInputDialog("Enter the medicine name");
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        

        currentOnTopFrame.hide();
        currentOnTopFrame = UpdateStockJFrame;
        UpdateStockJFrame.show();
    }//GEN-LAST:event_updateStockMenuActionPerformed

    public void clearCurrentOnTopFrame(JInternalFrame currentOnTopFrame)
    {
        switch(currentOnTopFrame.getTitle())
        {
            case "receiptjframe":
                clearReceiptJFrame();
                break;
            case "addstockjframe":
                clearAddStockJFrame();
                break;
            case "easystockadditionjframe":
                clearEasyStockAdditionJFrame();
                break;
            case "updatestockjframe":
                clearUpdateStockJFrame();
                break;
            case "totalsalesindaterange":
                clearTotalSalesInDateRangeJFrame();
                break;
            case "shortitemscompanyjframe":
                clearShortItemsCompanyJFrame();
                break;
            case "totalmedicinesalejframe":
                clearTotalMedicineSaleJFrame();
                break;
            case "totalcompanysalejframe":
                clearTotalCompanySaleJFrame();
                break;
            case "totalsalesmansalesjframe":
                clearTotalSalesmanSalesJFrame();
                break;
            case "totalreturnsbydatejframe":
                clearTotalReturnsByDateJFrame();
                break;
            case "totalreturnsbymedicinejframe":
                clearTotalReturnsByMedicineJFrame();
                break;
            case "totalreturnsbycompanyjframe":
                clearTotalReturnsByCompanyJFrame();
                break;
            case "totalreturnssalesmanjframe":
                clearTotalReturnsSalesManJFrame();
                break;
            case "shortitemsoveralljframe":
                clearShortItemsOverallJFrame();
                break;
            case "shortitemsonrackjframe":
                clearShortItemsOnRackJFrame();
                break;
            case "addusergroupfframe":
                clearAddUserGroupJFrame();
                break;
            case "adduserjframe":
                clearAddUserJFrame();
                break;
            case "removeuserjframe":
                clearRemoveUserJFrame();
                break;
        }
        currentOnTopFrame = ReceiptJFrame;
    }
    
    private void updateStockJFrameSaveMedicineInStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockJFrameSaveMedicineInStockButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            Medicine medicine = new Medicine();

            String medicineName = updateStockMedicineNameTextField.getText();
            long salesPrice = Long.parseLong(updateStockSalePriceTextField.getText());
            long costPrice = Long.parseLong(updateStockCostPriceTextField.getText());
            String companyName = updateStockCompanyNameTextField.getText().toString();
            long quantity = Long.parseLong(updateStockQuantityTextField.getText());
            String medicinGenericName = updateStockMedicineGenericNameTextField.getText();

            float unitCostPrice = 0;
            float unitSalesPrice = 0;

            if(updateStockLoosePurchasePerItemRadioButton.isSelected())
            {
                quantity = Long.parseLong(updateStockQuantityTextField.getText());
                unitCostPrice = costPrice/quantity;
                unitSalesPrice = salesPrice/quantity;
            }
            else if(updateStockLoosePurchasePerPackRadioButton.isSelected())
            {
                quantity = Long.parseLong(updateStockQuantityTextField.getText());
                long itemsPerBox = Long.parseLong(updateStockItemsPerPackBoxTextField.getText());
                quantity = quantity * itemsPerBox;
                unitCostPrice = costPrice/itemsPerBox;
                unitSalesPrice = salesPrice/itemsPerBox;
            }
            else if(updateStockLoosePurchasePerBoxRadioButton.isSelected())
            {
                quantity = Long.parseLong(updateStockQuantityTextField.getText());
                long itemsPerBox = Long.parseLong(updateStockItemsPerPackBoxTextField.getText());
                quantity = quantity * itemsPerBox;       
                unitCostPrice = costPrice/itemsPerBox;
                unitSalesPrice = salesPrice/itemsPerBox;
            }

            Date expiryDate = new Date();
            Date manufacturingDate = new Date();

            manufacturingDate =  updateStockManufacturingDateTextField.getDate();
            expiryDate = updateStockExpiryDateTextField.getDate();
            String rackNo = updateStockRackNumberTextField.getText();
            String distributorName = updateStockDistributorNameTextField.getText();

            medicine.medicineName = medicineName;
            medicine.quantity = quantity;
            medicine.companyName = companyName;
            medicine.costPrice = unitCostPrice;
            medicine.salesPrice = unitSalesPrice;
            medicine.expiryDate = expiryDate;
            medicine.manfacturingDate = manufacturingDate;
            medicine.rackNumber = rackNo;
            medicine.distributorName = distributorName;
            medicine.genericName = medicinGenericName;

            Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName.toLowerCase());
            if(null != medicineObject)
            {
                    SaadMedicineSaleSystem.medicineList.remove(medicineName.toLowerCase());
            }

            // TODO : Finding if the medicine exists in the database and removing it if required
            boolean databaseInsertionResult = DatabaseWrapper.updateMedicineInDatabase(medicine);
            SaadMedicineSaleSystem.medicineList.put(medicineName.toLowerCase(), medicine);
            clearUpdateStockJFrame();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_updateStockJFrameSaveMedicineInStockButtonActionPerformed

    private void updateStockCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockCancelButtonActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = ReceiptJFrame;
        ReceiptJFrame.show();
    }//GEN-LAST:event_updateStockCancelButtonActionPerformed

    private void updateStockMedicineNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockMedicineNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockMedicineNameTextFieldActionPerformed

    private void receiptTableInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_receiptTableInputMethodTextChanged
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, null);
    }//GEN-LAST:event_receiptTableInputMethodTextChanged

    private void addRemoveUsersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRemoveUsersMenuActionPerformed
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ModifySecurity"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        //AddUserJFrame.addUserUserGroups 
        currentOnTopFrame.hide();
        currentOnTopFrame = AddUserJFrame;
        AddUserJFrame.show();
    }//GEN-LAST:event_addRemoveUsersMenuActionPerformed

    private void addRemoveUserGroupsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRemoveUserGroupsMenuActionPerformed
        // TODO add your handling code here:
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ModifySecurity"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        currentOnTopFrame.hide();
        currentOnTopFrame = AddUserGroupJFrame;
        AddUserGroupJFrame.show();
    }//GEN-LAST:event_addRemoveUserGroupsMenuActionPerformed

    private void addUserUserLoginNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserUserLoginNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addUserUserLoginNameTextFieldActionPerformed

    private void addUserUserNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserUserNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addUserUserNameTextFieldActionPerformed

    private void addUserSaveUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserSaveUserButtonActionPerformed
        // TODO add your handling code here:
        String userName =  addUserUserNameTextField.getText();
        String userLoginName = addUserUserLoginNameTextField.getText();
        String userPasswordEntry1 = addUserUserPasswordTextField.getText();
        String userPasswordEntry2 = addUserReenterUserPasswordTextField.getText();
        String userGroup = (String)addUserUserGroups.getSelectedItem();

        if(userName.equals("") ||
                userLoginName.equals("") ||
                    userPasswordEntry1.equals("") ||
                        userPasswordEntry2.equals("") ||
                            userGroup.equals(""))
        {
            JOptionPane.showMessageDialog(null, "A required field is missing value. All fields are required.");
            return;
        }

        if(userPasswordEntry1.equals(userPasswordEntry2))
        {
            DatabaseWrapper.addUsertoUsersList(userName, userLoginName, userPasswordEntry1, userGroup);
            JOptionPane.showMessageDialog(null, "New User has been saved.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Passwords entered do not match.");
        }
    }//GEN-LAST:event_addUserSaveUserButtonActionPerformed

    private void addUserGroupAddUserGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserGroupAddUserGroupButtonActionPerformed
        // TODO add your handling code here:
        String userGroupName = addUserGroupUserGroupNameTextField.getText();

        List<String> list = addUserGroupPrivilesgesList.getSelectedValuesList();
        boolean addPrivilegeResult = Saad.classmodel.Security.SecurityManager.addSecurityPrivilegesforUserGroup(userGroupName, list);
        if(true == addPrivilegeResult)
        {
            JOptionPane.showMessageDialog(null, "UserGroup added successfully");
        }
        else
        {
            // TODO: Identify the cases in which user group addition could fail and fix those cases.
            JOptionPane.showMessageDialog(null, "UserGroup could not be added.");
        }
    }//GEN-LAST:event_addUserGroupAddUserGroupButtonActionPerformed

    private void addUserGroupUserGroupNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserGroupUserGroupNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addUserGroupUserGroupNameTextFieldActionPerformed

    private void AddUserJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_AddUserJFrameComponentShown
        // TODO add your handling code here:
        for(String userGroupName:userGroupNames)
        {
            addUserUserGroups.addItem(userGroupName);
        }
    }//GEN-LAST:event_AddUserJFrameComponentShown

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        DatabaseWrapper.releaseConnection();
    }//GEN-LAST:event_formWindowClosed

    private void addUserCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_addUserCancelButtonActionPerformed

    private void AddUserGroupJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_AddUserGroupJFrameComponentShown
        // TODO add your handling code here:
        ArrayList<String> securityGroups = SecurityManager.getListOfSecurityGroups();
        String[] arr = new String[securityGroups.size()];

        for(int i = 0;i < securityGroups.size();i++)
        {
            arr[i] = securityGroups.get(i);
        }

        addUserGroupPrivilesgesList.setListData(arr);
    }//GEN-LAST:event_AddUserGroupJFrameComponentShown

    private void removeUsersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUsersMenuActionPerformed
        // TODO add your handling code here:
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ModifySecurity"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        //AddUserJFrame.addUserUserGroups 
        currentOnTopFrame.hide();
        currentOnTopFrame = RemoveUserJFrame;
        RemoveUserJFrame.show();
    }//GEN-LAST:event_removeUsersMenuActionPerformed

    private void removeUserGroupsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserGroupsMenuActionPerformed
        // TODO add your handling code here:
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("ModifySecurity"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
        //AddUserJFrame.addUserUserGroups 
        currentOnTopFrame.hide();
        currentOnTopFrame = RemoveUserGroupJFrame;
        RemoveUserGroupJFrame.show();
    }//GEN-LAST:event_removeUserGroupsMenuActionPerformed

    private void removeUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserButtonActionPerformed
        // TODO add your handling code here:
        String userToRemove = usersToRemoveCombo.getSelectedItem().toString();
        if(SecurityManager.removeUser(userToRemove))
        {
            JOptionPane.showMessageDialog(null,"User has been removed.");
        }
    }//GEN-LAST:event_removeUserButtonActionPerformed

    private void removeUserCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserCancelButtonActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        //AddUserJFrame.addUserUserGroups 
        currentOnTopFrame.hide();
        currentOnTopFrame = ReceiptJFrame;
        ReceiptJFrame.show();
    }//GEN-LAST:event_removeUserCancelButtonActionPerformed

    private void RemoveUserJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_RemoveUserJFrameComponentShown
        // TODO add your handling code here:
        List<String> users = SecurityManager.getListOfUsers();

        for(String user:users)
        {
            usersToRemoveCombo.addItem(user);
        }

    }//GEN-LAST:event_RemoveUserJFrameComponentShown

    private void removeUserGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserGroupButtonActionPerformed
        // TODO add your handling code here:
        int reply = JOptionPane.showConfirmDialog(null, "Deleting user group would remove all users in group. Do you want to continue ?");
        if(reply == 0)
        {
            String userGroupToRemove = userGroupsToRemoveCombo.getSelectedItem().toString();
            boolean result = SecurityManager.removeUserGroup(userGroupToRemove);
            if(true == result)
            {
                JOptionPane.showMessageDialog(null, "User Group has been removed successfully.");
            }
        }
    }//GEN-LAST:event_removeUserGroupButtonActionPerformed

    private void removeGroupsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGroupsCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_removeGroupsCancelButtonActionPerformed

    private void RemoveUserGroupJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_RemoveUserGroupJFrameComponentShown
        // TODO add your handling code here:
        List<String> users = SecurityManager.getListOfUserGroups();

        for(String user:users)
        {
            userGroupsToRemoveCombo.addItem(user);
        }
    }//GEN-LAST:event_RemoveUserGroupJFrameComponentShown

    private void quantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTextFieldActionPerformed

    private void saveMedicineInStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMedicineInStockActionPerformed
        // TODO add your handling code here:
        try
        {
            if(medicineLabelTextField.getText().equalsIgnoreCase("") ||
                    salesPriceTextField.getText().equalsIgnoreCase("") ||
                        costPriceTextField.getText().equalsIgnoreCase("") ||
                            addStockCompanyNameTextField.getText().equalsIgnoreCase("") ||
                                medicineGenericNameTextField.getText().equalsIgnoreCase("") ||
                                    addStockRackNoTextField.getText().equalsIgnoreCase("") ||
                                        addStockDistributorNameTextField.getText().equalsIgnoreCase("")
            )
            {
                JOptionPane.showMessageDialog(null, "All of the fields are required.  Please fill complete information and save again.");
                return;
            }

            long quantity = 0;
            Medicine medicine = new Medicine();

            String medicineName = medicineLabelTextField.getText();
            float salesPrice = 0;
            float costPrice = 0;

            try
            {
                salesPrice = Float.parseFloat(salesPriceTextField.getText());
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Trade Price field contains invalid text. Please enter valid number and save again.");
            }

            try
            {
                costPrice = Float.parseFloat(costPriceTextField.getText());
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Retail Price field contains invalid text. Please enter valid number and save again.");
            }

            String companyName = addStockCompanyNameTextField.getText();
            String medicineGenericName = medicineGenericNameTextField.getText();

            float unitCostPrice = 0;
            float unitSalesPrice = 0;

            if(addStockLoosePurchasePerItemRadioButton.isSelected())
            {
                try
                {
                    quantity = Long.parseLong(quantityTextField.getText());
                } catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Quantity field contains invalid text. Please enter valid number and save again.");
                }

                unitCostPrice = costPrice;
                unitSalesPrice = salesPrice;
            }
            else if(addStockLoosePurchasePerPackRadioButton.isSelected())
            {
                quantity = Long.parseLong(quantityTextField.getText());
                long itemsPerBox = Long.parseLong(addStockItemsPerPackBoxTextField.getText());
                quantity = quantity * itemsPerBox;
                unitCostPrice = costPrice/itemsPerBox;
                unitSalesPrice = salesPrice/itemsPerBox;
            }
            else if(addStockLoosePurchasePerBoxRadioButton.isSelected())
            {
                quantity = Long.parseLong(quantityTextField.getText());
                long itemsPerBox = Long.parseLong(addStockItemsPerPackBoxTextField.getText());
                quantity = quantity * itemsPerBox;
                unitCostPrice = costPrice/itemsPerBox;
                unitSalesPrice = salesPrice/itemsPerBox;
            }

            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            Date expiryDate = new Date();
            Date manufacturingDate = new Date();

            manufacturingDate =  manufacturingDateTextField.getDate();
            expiryDate = expiryDateTextField.getDate();

            String rackNo = addStockRackNoTextField.getText();
            String distributorName = addStockDistributorNameTextField.getText();

            medicine.medicineName = medicineName;
            medicine.quantity = quantity;
            medicine.companyName = companyName;
            medicine.costPrice = unitCostPrice;
            medicine.salesPrice = unitSalesPrice;
            medicine.expiryDate = expiryDate;
            medicine.manfacturingDate = manufacturingDate;
            medicine.rackNumber = rackNo;
            medicine.distributorName = distributorName;
            medicine.genericName = medicineGenericName;

            Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName.toLowerCase());
            if(null != medicineObject)
            {
                SaadMedicineSaleSystem.medicineList.remove(medicineObject);
            }

            boolean databaseInsertionResult = DatabaseWrapper.insertMedicineInDatabase(medicine);
            SaadMedicineSaleSystem.medicineList.put(medicineName.toLowerCase(), medicine);

            // TODO Verify if adding a medicine at this stage has any effect
            SaadMedicineSaleSystem.stringMedicineList.add(medicineName.toLowerCase()  + " [" + medicine.genericName + "]");

            constructSearchableTermList();
            intializeMedicineDropDownString();

            clearAddStockJFrame();
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Medicine could not be saved. Please report this issue to vendor.");
            ex.printStackTrace();
            return;
        }
        JOptionPane.showMessageDialog(null, "Medicine has been saved successfully.");
    }//GEN-LAST:event_saveMedicineInStockActionPerformed

    private void addStockCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockCancelButtonActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        // TODO add your handling code here:
        currentOnTopFrame.hide();
        currentOnTopFrame = ReceiptJFrame;
        ReceiptJFrame.show();
    }//GEN-LAST:event_addStockCancelButtonActionPerformed

    private void medicineLabelTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineLabelTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineLabelTextFieldActionPerformed

    private void addStockItemsPerPackBoxTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockItemsPerPackBoxTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addStockItemsPerPackBoxTextFieldActionPerformed

    private void AddStockJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_AddStockJFrameComponentShown
        // TODO add your handling code here:
        addStockItemsPerPackBoxTextField.setEnabled(false);
    }//GEN-LAST:event_AddStockJFrameComponentShown

    private void addStockLoosePurchasePerItemRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockLoosePurchasePerItemRadioButtonItemStateChanged
        // TODO add your handling code here:
        if(addStockLoosePurchasePerItemRadioButton.isSelected())
        {
            addStockItemsPerPackBoxTextField.setEnabled(false);
        }
    }//GEN-LAST:event_addStockLoosePurchasePerItemRadioButtonItemStateChanged

    private void addStockLoosePurchasePerPackRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockLoosePurchasePerPackRadioButtonItemStateChanged
        // TODO add your handling code here:
        if(addStockLoosePurchasePerPackRadioButton.isSelected())
        {
            addStockItemsPerPackBoxTextField.setEnabled(true);
        }
    }//GEN-LAST:event_addStockLoosePurchasePerPackRadioButtonItemStateChanged

    private void addStockLoosePurchasePerBoxRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockLoosePurchasePerBoxRadioButtonItemStateChanged
        // TODO add your handling code here:
        if(addStockLoosePurchasePerBoxRadioButton.isSelected())
        {
            addStockItemsPerPackBoxTextField.setEnabled(true);
        }
    }//GEN-LAST:event_addStockLoosePurchasePerBoxRadioButtonItemStateChanged

    private void medicineGenericNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineGenericNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineGenericNameTextFieldActionPerformed

    private void updateStockMedicineGenericNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockMedicineGenericNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockMedicineGenericNameTextFieldActionPerformed

    private void updateStockLoosePurchasePerItemRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_updateStockLoosePurchasePerItemRadioButtonItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockLoosePurchasePerItemRadioButtonItemStateChanged

    private void updateStockLoosePurchasePerPackRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_updateStockLoosePurchasePerPackRadioButtonItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockLoosePurchasePerPackRadioButtonItemStateChanged

    private void updateStockLoosePurchasePerBoxRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_updateStockLoosePurchasePerBoxRadioButtonItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockLoosePurchasePerBoxRadioButtonItemStateChanged

    private void updateStockQuantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockQuantityTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockQuantityTextFieldActionPerformed

    private void updateStockItemsPerPackBoxTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockItemsPerPackBoxTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockItemsPerPackBoxTextFieldActionPerformed

    private void removeSelectedItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelectedItemButtonActionPerformed
        // TODO add your handling code here:
        receiptTableRowDeleted();
    }//GEN-LAST:event_removeSelectedItemButtonActionPerformed

    private void medicineNameDropDownFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medicineNameDropDownFieldKeyTyped
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "hello");
    }//GEN-LAST:event_medicineNameDropDownFieldKeyTyped

    private void medicineNameDropDownFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineNameDropDownFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_medicineNameDropDownFieldActionPerformed

    private void totalSalesInDateRangeCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSalesInDateRangeCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_totalSalesInDateRangeCancelButtonActionPerformed

    private void showMedicineSalesCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMedicineSalesCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_showMedicineSalesCancelButtonActionPerformed

    private void showCompanySalesCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCompanySalesCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_showCompanySalesCancelButtonActionPerformed

    private void showSalesmanSaleCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSalesmanSaleCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_showSalesmanSaleCancelButtonActionPerformed

    private void shortItemsOverallCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOverallCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_shortItemsOverallCancelButtonActionPerformed

    private void shortItemsByCompanyShowShortMedicineClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsByCompanyShowShortMedicineClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();        
    }//GEN-LAST:event_shortItemsByCompanyShowShortMedicineClearButtonActionPerformed

    private void shortItemsOnRackClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOnRackClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();        
    }//GEN-LAST:event_shortItemsOnRackClearButtonActionPerformed

    private void ReceiptJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ReceiptJFrameComponentShown
        this.setPreferredSize(screenDimension);
    }//GEN-LAST:event_ReceiptJFrameComponentShown

    private void salesReceiptTotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesReceiptTotalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesReceiptTotalTextFieldActionPerformed

    private void totalReturnsByMedicineCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByMedicineCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_totalReturnsByMedicineCancelButtonActionPerformed

    private void totalSalesInDateRangeClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSalesInDateRangeClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_totalSalesInDateRangeClearButtonActionPerformed

    private void showMedicineSalesClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMedicineSalesClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_showMedicineSalesClearButtonActionPerformed

    private void showCompanySalesClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCompanySalesClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_showCompanySalesClearButtonActionPerformed

    private void showSalesmanSaleClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSalesmanSaleClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_showSalesmanSaleClearButtonActionPerformed

    private void totalReturnsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();        
    }//GEN-LAST:event_totalReturnsCancelButtonActionPerformed

    private void totalReturnsClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_totalReturnsClearButtonActionPerformed

    private void totalReturnsByMedicineShowReturnsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByMedicineShowReturnsButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            clearTotalReturnsByMedicineReturnsTable();
            totalReturnsByMedicineReturnsTableData.clear();
            String medicineName = totalReturnsByMedicineMedicineNameTextField.getText();

            SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
            float totalReturns = 0;

            // TODO : Discuss if adding date to GUI would be useful.
            Date startDate = totalReturnsByMedicineStartDateTextField.getDate();
            Date endDate = totalReturnsByMedicineEndDateTextField.getDate();

            // TODO : Finding out which is more optimal (database search or list search)
            Set<Long> keyset = SaadMedicineSaleSystem.returnReceiptList.keySet();
            for(Long ln:keyset)
            {
                SalesReceipt sReceipt = SaadMedicineSaleSystem.returnReceiptList.get(ln);
                if(sReceipt.getSalesDate().after(startDate) && sReceipt.getSalesDate().before(endDate))
                {
                    for(Medicine medicineObject:sReceipt.medicineList)
                    {
                        if(null!= medicineName && medicineName.toLowerCase().equalsIgnoreCase(medicineObject.medicineName))
                        {
                            //Medicine medicineObject = sReceipt.medicineList.get(mKey);
                            ArrayList<String> sDate = new ArrayList<String>();
                            sDate.add(Long.toString(sReceipt.getReceiptID()));
                            sDate.add(sReceipt.getSalesDate().toString());
                            sDate.add(Float.toString(medicineObject.salesPrice));
                            totalReturnsByMedicineReturnsTableData.add(sDate);
                            totalReturns = totalReturns + medicineObject.salesPrice;
                        }
                        else
                        {
                            // Show some error here
                        }
                    }
                        //netProfit = netProfit + (medicineObject.costPrice - medicineObject.salesPrice);
                }
            }

            if(totalReturnsByMedicineReturnsTableData.size() > totalReturnsByMedicineReturnsTable.getRowCount())
            {
                generateTableModelForTableData(totalReturnsByMedicineReturnsTable, totalReturnsByMedicineReturnsTableData);
            }

            for(int i = 0; i< totalReturnsByMedicineReturnsTableData.size();i++)
            {
                for(int j = 0; j < totalReturnsByMedicineReturnsTableData.get(0).size();j++)
                {
                    totalReturnsByMedicineReturnsTable.setValueAt(totalReturnsByMedicineReturnsTableData.get(i).get(j), i, j);
                }
            }

            totalMedicineReturnsByMedicineReturnsTextField.setText(Float.toString(totalReturns));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_totalReturnsByMedicineShowReturnsButtonActionPerformed

    private void totalReturnsByCompanyCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_totalReturnsByCompanyCancelButtonActionPerformed

    private void totalReturnsByCompanyClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsByCompanyClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_totalReturnsByCompanyClearButtonActionPerformed

    private void totalReturnsBySalesManCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsBySalesManCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_totalReturnsBySalesManCancelButtonActionPerformed

    private void totalReturnsBySalesManClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalReturnsBySalesManClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_totalReturnsBySalesManClearButtonActionPerformed

    private void shortItemsOverallClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOverallClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_shortItemsOverallClearButtonActionPerformed

    private void shortItemsOnRackCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsOnRackCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_shortItemsOnRackCancelButtonActionPerformed

    private void salesReceiptNetProfitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesReceiptNetProfitTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesReceiptNetProfitTextFieldActionPerformed

    private void shortItemsByCompanyShowShortMedicineCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortItemsByCompanyShowShortMedicineCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_shortItemsByCompanyShowShortMedicineCancelButtonActionPerformed

    private void addUserReenterUserPasswordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserReenterUserPasswordTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addUserReenterUserPasswordTextFieldActionPerformed

    private void addUserClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserClearButtonActionPerformed
        // TODO add your handling code here:
        clearButtonPressedOnForm();
    }//GEN-LAST:event_addUserClearButtonActionPerformed

    private void addUserGroupsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserGroupsCancelButtonActionPerformed
        // TODO add your handling code here:
        cancelButtonPressedOnForm();
    }//GEN-LAST:event_addUserGroupsCancelButtonActionPerformed

    private void mainReciptChangeQuantityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainReciptChangeQuantityButtonActionPerformed
        // TODO add your handling code here:
        //receiptTable.getModel().fireTableChanged(new TableModelEvent(model, 0, lastRow, columnIn, TableModelEvent.UPDATE));
    }//GEN-LAST:event_mainReciptChangeQuantityButtonActionPerformed

    private void UpdateStockJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_UpdateStockJFrameComponentShown
        // TODO add your handling code here:
        updateStockLoosePurchasePerItemRadioButton.setSelected(true);
        updateStockItemsPerPackBoxTextField.setEnabled(false);
    }//GEN-LAST:event_UpdateStockJFrameComponentShown

    private void updateStockLoosePurchasePerPackRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockLoosePurchasePerPackRadioButtonActionPerformed
        // TODO add your handling code here:
        updateStockItemsPerPackBoxTextField.setEnabled(true);
    }//GEN-LAST:event_updateStockLoosePurchasePerPackRadioButtonActionPerformed

    private void updateStockLoosePurchasePerBoxRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockLoosePurchasePerBoxRadioButtonActionPerformed
        // TODO add your handling code here:
        updateStockItemsPerPackBoxTextField.setEnabled(true);
    }//GEN-LAST:event_updateStockLoosePurchasePerBoxRadioButtonActionPerformed

    private void updateStockLoosePurchasePerItemRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockLoosePurchasePerItemRadioButtonActionPerformed
        // TODO add your handling code here:
        updateStockItemsPerPackBoxTextField.setEnabled(false);
    }//GEN-LAST:event_updateStockLoosePurchasePerItemRadioButtonActionPerformed

    private void receiptJFrameOverallDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptJFrameOverallDiscountActionPerformed
        // TODO add your handling code here:
        if(totalPriceTextField.getText().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Discount can only be configured after total price has been computed.");
            return;
        }
        float overallDiscount = Float.parseFloat(receiptJFrameOverallDiscount.getText());
        float totalPrice = Float.parseFloat(totalPriceTextField.getText());
        totalPrice = totalPrice - overallDiscount;
        totalPriceTextField.setText(Float.toString(totalPrice));
    }//GEN-LAST:event_receiptJFrameOverallDiscountActionPerformed

    private void medicineNameDropDownFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_medicineNameDropDownFieldItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineNameDropDownFieldItemStateChanged

    private void medicineNameDropDownFieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_medicineNameDropDownFieldInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineNameDropDownFieldInputMethodTextChanged

    private void medicineNameDropDownFieldPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_medicineNameDropDownFieldPopupMenuCanceled
    }//GEN-LAST:event_medicineNameDropDownFieldPopupMenuCanceled

    private void clearMedicineDropDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearMedicineDropDownButtonActionPerformed
        MainFrame.state = MainFrame.ITEM_SELECTED;
        medicineNameDropDownField.getEditor().setItem("");
// TODO add your handling code here:
    }//GEN-LAST:event_clearMedicineDropDownButtonActionPerformed

    private void easyStockMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyStockMenuItemActionPerformed
        // TODO add your handling code here:
        User loggedUser = Saad.classmodel.Security.SecurityManager.getCurrentlyLoggedUser();

        if(!loggedUser.hasRightsOnOperation("AddUpdateStock"))
        {
            JOptionPane.showMessageDialog(null, "You don't have rights for this operation.");
            return;
        }

        // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        currentOnTopFrame.hide();
        currentOnTopFrame = EasyStockAdditionJFrame;
        EasyStockAdditionJFrame.show();
    }//GEN-LAST:event_easyStockMenuItemActionPerformed

    @SuppressWarnings({"unchecked", "unchecked"})
    private void EasyStockAdditionJFrameComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_EasyStockAdditionJFrameComponentShown
        // TODO add your handling code here:
        ArrayList <String> distributorNames = DatabaseWrapper.getDistributorNames();
       for(int i = 0;i < distributorNames.size();i++)
       {
           distributorNameComboBox.addItem(distributorNames.get(i));
       }
       
       
    }//GEN-LAST:event_EasyStockAdditionJFrameComponentShown

    private void distributorNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distributorNameComboBoxActionPerformed
        // TODO add your handling code here:
       easyStockAdditionCompanyNameComboBox.removeAllItems();
     String distname = (String)distributorNameComboBox.getSelectedItem();
     ArrayList <String> companyNames = DatabaseWrapper.getCompanyNames(distname);
     for(int j = 0;j < companyNames.size();j++)
     {
         easyStockAdditionCompanyNameComboBox.addItem(companyNames.get(j));
     }
    }//GEN-LAST:event_distributorNameComboBoxActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
          // TODO add your handling code here:
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });

        // TODO add your handling code here:
        currentOnTopFrame.hide();
        currentOnTopFrame = ReceiptJFrame;
        ReceiptJFrame.show();                       
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void costPriceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costPriceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costPriceTextFieldActionPerformed

    private void addStockDistributorNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockDistributorNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addStockDistributorNameTextFieldActionPerformed

    private void addStockCompanyNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockCompanyNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addStockCompanyNameTextFieldActionPerformed

    private void addStockLoosePurchasePerItemRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockLoosePurchasePerItemRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addStockLoosePurchasePerItemRadioButtonActionPerformed

    private void addStockLoosePurchasePerPackRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockLoosePurchasePerPackRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addStockLoosePurchasePerPackRadioButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
      
           try
           { 
            if(
            easyStockAdditionTradePriceTextField.getText().equalsIgnoreCase("") ||
            easyStockAdditionRetailPriceTextField.getText().equalsIgnoreCase("") ||
            easyStockAdditionRackNumberTextField.getText().equalsIgnoreCase("")
            )
            {
            JOptionPane.showMessageDialog(null, "All of the fields are required.  Please fill complete information and save again.");
            return;
            }
            long quantity = 0;
            Medicine medicine = new Medicine();

            String medicineName = (String)easyStockAdditionMedicineNameComboBox.getSelectedItem();
            float salesPrice = 0;
            float costPrice = 0;

            try
            {
                salesPrice = Float.parseFloat(easyStockAdditionRetailPriceTextField.getText());
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Trade Price field contains invalid text. Please enter valid number and save again.");
            }

            try
            {
                costPrice = Float.parseFloat(easyStockAdditionTradePriceTextField.getText());
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Retail Price field contains invalid text. Please enter valid number and save again.");
            }
            String medicineGenericName = easyStockAdditionGenericNameTextField.getText();
            String companyName = (String)easyStockAdditionCompanyNameComboBox.getSelectedItem();
            float unitCostPrice = 0;
            float unitSalesPrice = 0;
             if(easyAddStockAdditionRadioButtonLoosePurchasePerItem.isSelected())
            {
                try
                {
                    quantity = Long.parseLong(easyStockAdditionNumberOfItemsTextField.getText());
                } catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Quantity field contains invalid text. Please enter valid number and save again.");
                }

                unitCostPrice = costPrice;
                unitSalesPrice = salesPrice;
            }
            else if(easyStockAdditionRadioButtonLoosePurchasePerPack.isSelected())
            {
                quantity = Long.parseLong(easyStockAdditionNumberOfItemsTextField.getText());
                long itemsPerBox = Long.parseLong(easyStockAdditionItemsPerPackOrBoxTextField.getText());
                quantity = quantity * itemsPerBox;
                unitCostPrice = costPrice/itemsPerBox;
                unitSalesPrice = salesPrice/itemsPerBox;
            }
            else if(easyStockAdditionRadioButtonLoosePurchasePerBox.isSelected())
            {
                quantity = Long.parseLong(easyStockAdditionNumberOfItemsTextField.getText());
                long itemsPerBox = Long.parseLong(easyStockAdditionItemsPerPackOrBoxTextField.getText());
                quantity = quantity * itemsPerBox;
                unitCostPrice = costPrice/itemsPerBox;
                unitSalesPrice = salesPrice/itemsPerBox;
            }
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            Date expiryDate = new Date();
            Date manufacturingDate = new Date();

            manufacturingDate =  easyStockAdditionmanufacturingDateTextField.getDate();
            expiryDate = easyStockAdditionExpiryDateTextField.getDate();
             String distributorName = (String)distributorNameComboBox.getSelectedItem();
             String rackNo = easyStockAdditionRackNumberTextField.getText();
           
            medicine.medicineName = medicineName;
            medicine.quantity = quantity;
            medicine.companyName = companyName;
            medicine.costPrice = unitCostPrice;
            medicine.salesPrice = unitSalesPrice;
            medicine.expiryDate = expiryDate;
            medicine.manfacturingDate = manufacturingDate;
            medicine.rackNumber = rackNo;
            medicine.distributorName = distributorName;
            medicine.genericName = medicineGenericName;

            Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName.toLowerCase());
            if(null != medicineObject)
            {
                SaadMedicineSaleSystem.medicineList.remove(medicineObject);
            }

            boolean databaseInsertionResult = DatabaseWrapper.insertMedicineInDatabase(medicine);
            SaadMedicineSaleSystem.medicineList.put(medicineName.toLowerCase(), medicine);

            // TODO Verify if adding a medicine at this stage has any effect
            SaadMedicineSaleSystem.stringMedicineList.add(medicineName.toLowerCase()  + " [" + medicine.genericName + "]");
            constructSearchableTermList();
            intializeMedicineDropDownString();
            clearAddStockJFrame();
           }
           catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Medicine could not be saved. Please report this issue to vendor.");
            ex.printStackTrace();
            return;
        }
            JOptionPane.showMessageDialog(null, "Medicine has been saved successfully.");
    }//GEN-LAST:event_saveButtonActionPerformed

    private void distributorNameComboBoxComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_distributorNameComboBoxComponentShown
        // TODO add your handling code here:
      
    }//GEN-LAST:event_distributorNameComboBoxComponentShown

    private void easyStockAdditionCompanyNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyStockAdditionCompanyNameComboBoxActionPerformed
        // TODO add your handling code here:
      easyStockAdditionMedicineNameComboBox.removeAllItems();
      String CompanyNames = (String)easyStockAdditionCompanyNameComboBox.getSelectedItem();
      ArrayList <String> medicineNames = DatabaseWrapper.getMedicineNames(CompanyNames);
      for(int k = 0;k < medicineNames.size();k++)
      {
          easyStockAdditionMedicineNameComboBox.addItem(medicineNames.get(k));
      }
    }//GEN-LAST:event_easyStockAdditionCompanyNameComboBoxActionPerformed

    private void easyStockAdditionMedicineNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyStockAdditionMedicineNameComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_easyStockAdditionMedicineNameComboBoxActionPerformed

    private void addStockLoosePurchasePerBoxRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockLoosePurchasePerBoxRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addStockLoosePurchasePerBoxRadioButtonActionPerformed

    private void easyAddStockAdditionRadioButtonLoosePurchasePerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyAddStockAdditionRadioButtonLoosePurchasePerItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_easyAddStockAdditionRadioButtonLoosePurchasePerItemActionPerformed

    private void easyAddStockAdditionRadioButtonLoosePurchasePerItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_easyAddStockAdditionRadioButtonLoosePurchasePerItemItemStateChanged
        // TODO add your handling code here:
         if(easyAddStockAdditionRadioButtonLoosePurchasePerItem.isSelected())
        {
            easyStockAdditionItemsPerPackOrBoxTextField.setEnabled(false);
        }
    }//GEN-LAST:event_easyAddStockAdditionRadioButtonLoosePurchasePerItemItemStateChanged

    private void easyStockAdditionRadioButtonLoosePurchasePerPackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyStockAdditionRadioButtonLoosePurchasePerPackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_easyStockAdditionRadioButtonLoosePurchasePerPackActionPerformed

    private void easyStockAdditionRadioButtonLoosePurchasePerPackItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_easyStockAdditionRadioButtonLoosePurchasePerPackItemStateChanged
        // TODO add your handling code here:
         if(easyStockAdditionRadioButtonLoosePurchasePerPack.isSelected())
        {
            easyStockAdditionItemsPerPackOrBoxTextField.setEnabled(true);
        }
    }//GEN-LAST:event_easyStockAdditionRadioButtonLoosePurchasePerPackItemStateChanged

    private void easyStockAdditionRadioButtonLoosePurchasePerBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyStockAdditionRadioButtonLoosePurchasePerBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_easyStockAdditionRadioButtonLoosePurchasePerBoxActionPerformed

    private void easyStockAdditionRadioButtonLoosePurchasePerBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_easyStockAdditionRadioButtonLoosePurchasePerBoxItemStateChanged
        // TODO add your handling code here:
        if(easyStockAdditionRadioButtonLoosePurchasePerBox.isSelected())
        {
            easyStockAdditionItemsPerPackOrBoxTextField.setEnabled(true);
        }
    }//GEN-LAST:event_easyStockAdditionRadioButtonLoosePurchasePerBoxItemStateChanged

    private void orderOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderOnlineActionPerformed
        // TODO : Sales Receipt ID is to be printed on the print string also.
        SalesReceipt saleReceipt = constructSalesReceipt();
        String jsonString = saleReceipt.getJSONForSalesReceipt();

        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost("http://somesite.com/test.php");
            StringEntity params =new StringEntity("message=" + jsonString);
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            // handle response here...
            System.out.println(org.apache.http.util.EntityUtils.toString(response.getEntity()));
            org.apache.http.util.EntityUtils.consume(response.getEntity());
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }        
    }//GEN-LAST:event_orderOnlineActionPerformed
                
    public void clearReceiptTable()
    {
        for(int i = 0; i< receiptTable.getRowCount();i++)
        {
            for(int j = 0 ; j < receiptTable.getColumnCount();j++)
            {
                receiptTable.setValueAt("", i, j);
            }
        }
    }

    long returnReceiptID = 0;
    boolean receiptReturnFlag = false;
    public static String []dailySaleReceiptsColumnHeads = new String[]{"Sales Receipt #","Sales Receipt Date","Sales Total"};
    public static String []TotalMedicineSalesColumnHeads = new String[]{"Sales Receipt #","Sales Receipt Date","Sales Total"};
    public static javax.swing.JInternalFrame currentOnTopFrame;
    public static ArrayList<ArrayList<String>> medicineInReceiptList = new ArrayList<ArrayList<String>>();
    //public static float totalPrice = 0;
    public static float overallDiscount = 0;
    public static StringBuffer printString = new StringBuffer();
    public int receiptTableFocusedRow = -1;
    public static float overallDiscountPercentage = 0;
    ArrayList<ArrayList<String>> totalSalesInDateRangeSalesReceipt = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> totalMedicineSaleTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> totalCompanySaleTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> totalSalesManSalesTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> totalReturnsByDateRangeTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> totalReturnsByMedicineReturnsTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> shortItemsByCompanyShortMedicineTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> totalReturnsByCompanyReturnsTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> totalReturnsBySalesmanTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> shortItemsOverallTableData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> shortItemsOnRackTableData = new ArrayList<ArrayList<String>>();
    ArrayList<String> userGroupNames;// = DatabaseWrapper.getUserGroupNames();
    StringSearchable searchable = null;
    public static String vendorName;
    private List<String> medicineNameTerms = new ArrayList<String>();
    private List<String> genericNamesTerms = new ArrayList<String>();
    private List<String> companyNamesTerms = new ArrayList<String>();

    public static int MEDICINE_BEING_TYPED = 2;
    public static int RECEIPT_PRINTED = 3;
    public static int ITEM_SELECTED =  6;
    public static int RECEIPT_IDLE = 1;
    public static int state = RECEIPT_IDLE;
    public static int ROW_DELETED = 4;
    public static int MEDICINE_ADDED = 5;
    public Image appIcon;
    public static Dimension screenDimension;// = new Dimension();
    private static String printReceiptRemarks = "";
    private static String printReceiptReference= "";
    private static boolean postOnServer = true;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame AddStockJFrame;
    private javax.swing.JInternalFrame AddUserGroupJFrame;
    private javax.swing.JInternalFrame AddUserJFrame;
    private javax.swing.JInternalFrame EasyStockAdditionJFrame;
    private javax.swing.JInternalFrame ReceiptJFrame;
    private javax.swing.JInternalFrame RemoveUserGroupJFrame;
    private javax.swing.JInternalFrame RemoveUserJFrame;
    private static javax.swing.JInternalFrame ShortItemsCompanyJFrame;
    private static javax.swing.JInternalFrame ShortItemsOnRackJFrame;
    private static javax.swing.JInternalFrame ShortItemsOverallJFrame;
    private static javax.swing.JInternalFrame TotalCompanySaleJFrame;
    private static javax.swing.JInternalFrame TotalMedicineSaleJFrame;
    private static javax.swing.JInternalFrame TotalReturnsByCompanyJFrame;
    private static javax.swing.JInternalFrame TotalReturnsByDateJFrame;
    private static javax.swing.JInternalFrame TotalReturnsByMedicineJFrame;
    private static javax.swing.JInternalFrame TotalReturnsSalesManJFrame;
    private static javax.swing.JInternalFrame TotalSalesInDateRange;
    private static javax.swing.JInternalFrame TotalSalesManSalesJFrame;
    private javax.swing.JInternalFrame UpdateStockJFrame;
    private javax.swing.JMenuItem addRemoveUserGroupsMenu;
    private javax.swing.JMenuItem addRemoveUsersMenu;
    private javax.swing.JMenuItem addStock;
    private javax.swing.JButton addStockCancelButton;
    private javax.swing.JTextField addStockCompanyNameTextField;
    private javax.swing.JTextField addStockDistributorNameTextField;
    public static javax.swing.JTextField addStockItemsPerPackBoxTextField;
    private javax.swing.JRadioButton addStockLoosePurchasePerBoxRadioButton;
    private javax.swing.JRadioButton addStockLoosePurchasePerItemRadioButton;
    private javax.swing.JRadioButton addStockLoosePurchasePerPackRadioButton;
    private javax.swing.JTextField addStockRackNoTextField;
    private javax.swing.JButton addUserCancelButton;
    private javax.swing.JButton addUserClearButton;
    private javax.swing.JButton addUserGroupAddUserGroupButton;
    private javax.swing.JList addUserGroupPrivilesgesList;
    public static javax.swing.JTextField addUserGroupUserGroupNameTextField;
    private javax.swing.JButton addUserGroupsCancelButton;
    private javax.swing.JPasswordField addUserReenterUserPasswordTextField;
    private javax.swing.JButton addUserSaveUserButton;
    private static javax.swing.JComboBox addUserUserGroups;
    public static javax.swing.JTextField addUserUserLoginNameTextField;
    public static javax.swing.JTextField addUserUserNameTextField;
    private javax.swing.JPasswordField addUserUserPasswordTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearMedicineDropDownButton;
    public static javax.swing.JTextField companySalesCompanyTextField;
    public static javax.swing.JTextField companySalesDistributorTextField;
    public static javax.swing.JTextField companySalesMedicineNameTextField;
    public static javax.swing.JTextField companySalesNetProfitTextField;
    public static javax.swing.JTextField companySalesTotalSalesTextField;
    private javax.swing.JMenuItem configureDiscountMenu;
    public static javax.swing.JTextField costPriceTextField;
    private javax.swing.JComboBox distributorNameComboBox;
    private javax.swing.JRadioButton easyAddStockAdditionRadioButtonLoosePurchasePerItem;
    private javax.swing.JComboBox easyStockAdditionCompanyNameComboBox;
    private com.toedter.calendar.JDateChooser easyStockAdditionExpiryDateTextField;
    private javax.swing.JTextField easyStockAdditionGenericNameTextField;
    private javax.swing.JTextField easyStockAdditionItemsPerPackOrBoxTextField;
    private javax.swing.JComboBox easyStockAdditionMedicineNameComboBox;
    private javax.swing.JTextField easyStockAdditionNumberOfItemsTextField;
    private javax.swing.JTextField easyStockAdditionRackNumberTextField;
    private javax.swing.JRadioButton easyStockAdditionRadioButtonLoosePurchasePerBox;
    private javax.swing.JRadioButton easyStockAdditionRadioButtonLoosePurchasePerPack;
    private javax.swing.JTextField easyStockAdditionRetailPriceTextField;
    private javax.swing.JTextField easyStockAdditionTradePriceTextField;
    private com.toedter.calendar.JDateChooser easyStockAdditionmanufacturingDateTextField;
    private javax.swing.JMenuItem easyStockMenuItem;
    private com.toedter.calendar.JDateChooser expiryDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JDesktopPane mainDesktopPane;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JButton mainReciptChangeQuantityButton;
    private javax.swing.JToolBar mainToolbar;
    private com.toedter.calendar.JDateChooser manufacturingDateTextField;
    public static javax.swing.JTextField medicineGenericNameTextField;
    public static javax.swing.JTextField medicineLabelTextField;
    public static Saad.gui.AutocompleteJComboBox medicineNameDropDownField;
    private javax.swing.JButton orderOnline;
    private javax.swing.JMenuItem overallShortItemsMenu;
    private javax.swing.JButton printReceipt;
    private javax.swing.JMenuItem printReceiptMenu;
    private javax.swing.JMenuItem processReturnMenu;
    public static javax.swing.JTextField quantityTextField;
    public static javax.swing.JTextField receiptJFrameOverallDiscount;
    public static javax.swing.JTable receiptTable;
    private com.toedter.calendar.JDateChooser receiptsEndDateTextField;
    private com.toedter.calendar.JDateChooser receiptsStartDateTextField;
    private javax.swing.JButton removeGroupsCancelButton;
    private javax.swing.JButton removeSelectedItemButton;
    private javax.swing.JButton removeUserButton;
    private javax.swing.JButton removeUserCancelButton;
    private javax.swing.JButton removeUserGroupButton;
    private javax.swing.JMenuItem removeUserGroupsMenu;
    private javax.swing.JMenuItem removeUsersMenu;
    private javax.swing.JMenu salesInDateRangeMenu;
    public static javax.swing.JTextField salesPriceTextField;
    public static javax.swing.JTextField salesReceiptNetProfitTextField;
    public static javax.swing.JTextField salesReceiptTotalTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveMedicineInStock;
    private javax.swing.JMenu securityMenu;
    private javax.swing.JMenuItem shortItemsByCompanyMenu;
    private javax.swing.JTable shortItemsByCompanyShortItemsTable;
    private javax.swing.JButton shortItemsByCompanyShowShortMedicineButton;
    private javax.swing.JButton shortItemsByCompanyShowShortMedicineCancelButton;
    private javax.swing.JButton shortItemsByCompanyShowShortMedicineClearButton;
    private javax.swing.JSpinner shortItemsByRackSpinner;
    public static javax.swing.JTextField shortItemsCompanyDistributorNameTextField;
    public static javax.swing.JTextField shortItemsCompanyNameTextField;
    private javax.swing.JSpinner shortItemsCompanySpinner;
    private javax.swing.JButton shortItemsOnRackCancelButton;
    private javax.swing.JButton shortItemsOnRackClearButton;
    public static javax.swing.JTextField shortItemsOnRackCompanyNameTextField;
    public static javax.swing.JTextField shortItemsOnRackDistributorNameTextField;
    private javax.swing.JMenuItem shortItemsOnRackMenu;
    private javax.swing.JButton shortItemsOnRackShowShortMedicineButton;
    private javax.swing.JTable shortItemsOnRackTable;
    private javax.swing.JTable shortItemsOverAllMedicineTable;
    private javax.swing.JButton shortItemsOverallCancelButton;
    private javax.swing.JButton shortItemsOverallClearButton;
    private javax.swing.JButton shortItemsOverallShowShortMedicineButton;
    private javax.swing.JSpinner shortItemsOverallSpinner;
    public static javax.swing.JTextField shortItemsRackNoTextField;
    private javax.swing.JButton showComanySalesShowSales;
    private javax.swing.JButton showCompanySalesCancelButton;
    private javax.swing.JButton showCompanySalesClearButton;
    private javax.swing.JButton showMedicineSalesButton;
    private javax.swing.JButton showMedicineSalesCancelButton;
    private javax.swing.JButton showMedicineSalesClearButton;
    private javax.swing.JButton showReceiptsButton;
    private javax.swing.JButton showSalesmanSaleCancelButton;
    private javax.swing.JButton showSalesmanSaleClearButton;
    private javax.swing.JButton showSalesmanSaleShowSalesButton;
    private javax.swing.JMenuItem totalCompanySaleMenu;
    private javax.swing.JTable totalCompanySaleTable;
    public static javax.swing.JTextField totalMedicineReturnsByMedicineReturnsTextField;
    private com.toedter.calendar.JDateChooser totalMedicineSaleEndDateTextField;
    public static javax.swing.JTextField totalMedicineSaleMedicineNameTextField;
    private javax.swing.JMenuItem totalMedicineSaleMenu;
    public static javax.swing.JTextField totalMedicineSaleNetProfitTextField;
    private com.toedter.calendar.JDateChooser totalMedicineSaleStartDateTextField;
    public static javax.swing.JTextField totalMedicineSaleTextField;
    public static javax.swing.JTextField totalPriceTextField;
    private javax.swing.JButton totalReturnsByCompanyCancelButton;
    private javax.swing.JButton totalReturnsByCompanyClearButton;
    public static javax.swing.JTextField totalReturnsByCompanyCompanyNameTextField;
    public static javax.swing.JTextField totalReturnsByCompanyDistributorNameTextField;
    public static javax.swing.JTextField totalReturnsByCompanyMedicineNameTextField;
    private javax.swing.JMenuItem totalReturnsByCompanyMenu;
    private javax.swing.JTable totalReturnsByCompanyReturnsTable;
    public static javax.swing.JTextField totalReturnsByCompanyReturnsTextField;
    private javax.swing.JButton totalReturnsByCompanyShowReturnsButton;
    private javax.swing.JMenuItem totalReturnsByDateMenu;
    private javax.swing.JTable totalReturnsByDateReturnsTable;
    private javax.swing.JButton totalReturnsByMedicineCancelButton;
    private javax.swing.JButton totalReturnsByMedicineClearButton;
    private com.toedter.calendar.JDateChooser totalReturnsByMedicineEndDateTextField;
    public static javax.swing.JTextField totalReturnsByMedicineMedicineNameTextField;
    private javax.swing.JMenuItem totalReturnsByMedicineMenu;
    private javax.swing.JTable totalReturnsByMedicineReturnsTable;
    private javax.swing.JButton totalReturnsByMedicineShowReturnsButton;
    private com.toedter.calendar.JDateChooser totalReturnsByMedicineStartDateTextField;
    private javax.swing.JButton totalReturnsBySalesManCancelButton;
    private javax.swing.JButton totalReturnsBySalesManClearButton;
    private javax.swing.JButton totalReturnsBySalesManShowReturnsButton;
    private com.toedter.calendar.JDateChooser totalReturnsBySalesManStartDateTextField;
    private com.toedter.calendar.JDateChooser totalReturnsBySalesmanEndDateTextField;
    private javax.swing.JMenuItem totalReturnsBySalesmanMenu;
    public static javax.swing.JTextField totalReturnsBySalesmanNameTextField;
    public static javax.swing.JTextField totalReturnsBySalesmanReturnTextField;
    private javax.swing.JTable totalReturnsBySalesmanTable;
    private javax.swing.JButton totalReturnsCancelButton;
    private javax.swing.JButton totalReturnsClearButton;
    private com.toedter.calendar.JDateChooser totalReturnsEndDateTextField;
    private javax.swing.JButton totalReturnsShowReturnsButton;
    private com.toedter.calendar.JDateChooser totalReturnsStartDateTextField;
    public static javax.swing.JTextField totalReturnsTotalTextField;
    public static javax.swing.JTextField totalSalemanSaleTotalSalesTextField;
    private com.toedter.calendar.JDateChooser totalSalemanSalesEndDateTextField;
    private javax.swing.JButton totalSalesInDateRangeCancelButton;
    private javax.swing.JButton totalSalesInDateRangeClearButton;
    private javax.swing.JMenuItem totalSalesInDateRangeMenu;
    private javax.swing.JTable totalSalesInDateRangeReceiptTable;
    private javax.swing.JTable totalSalesManSaleTable;
    private javax.swing.JMenuItem totalSalesmanSaleMenu;
    public static javax.swing.JTextField totalSalesmanSaleSalemanNameTextField;
    private com.toedter.calendar.JDateChooser totalSalesmanSaleStartDateTextField;
    private javax.swing.JTable totalmedicineSaleTable;
    private javax.swing.JButton updateStockCancelButton;
    public static javax.swing.JTextField updateStockCompanyNameTextField;
    public static javax.swing.JTextField updateStockCostPriceTextField;
    public static javax.swing.JTextField updateStockDistributorNameTextField;
    private static com.toedter.calendar.JDateChooser updateStockExpiryDateTextField;
    public static javax.swing.JTextField updateStockItemsPerPackBoxTextField;
    private javax.swing.JButton updateStockJFrameSaveMedicineInStockButton;
    private javax.swing.JRadioButton updateStockLoosePurchasePerBoxRadioButton;
    private javax.swing.JRadioButton updateStockLoosePurchasePerItemRadioButton;
    private javax.swing.JRadioButton updateStockLoosePurchasePerPackRadioButton;
    private static com.toedter.calendar.JDateChooser updateStockManufacturingDateTextField;
    public static javax.swing.JTextField updateStockMedicineGenericNameTextField;
    public static Saad.gui.AutocompleteJComboBox updateStockMedicineNameDropDownField;
    public static javax.swing.JTextField updateStockMedicineNameTextField;
    private javax.swing.JMenuItem updateStockMenu;
    public static javax.swing.JTextField updateStockQuantityTextField;
    public static javax.swing.JTextField updateStockRackNumberTextField;
    public static javax.swing.JTextField updateStockSalePriceTextField;
    private javax.swing.JComboBox userGroupsToRemoveCombo;
    private javax.swing.JComboBox usersToRemoveCombo;
    // End of variables declaration//GEN-END:variables

    private void clearTotalSalesInDateRangeJFrame() {
        Date mDate = new Date();
        receiptsStartDateTextField.setDate(mDate);
        receiptsEndDateTextField.setDate(mDate);
        salesReceiptTotalTextField.setText("");
        salesReceiptNetProfitTextField.setText("");
        for(int i = 0; i< totalSalesInDateRangeSalesReceipt.size();i++)
        {
            for(int j = 0; j < dailySaleReceiptsColumnHeads.length;j++)
            {
                totalSalesInDateRangeReceiptTable.setValueAt("", i, j);
            }
        }
        totalSalesInDateRangeSalesReceipt.clear();
    }

    private void clearShortItemsCompanyJFrame() {
        shortItemsCompanyDistributorNameTextField.setText("");
        shortItemsCompanyNameTextField.setText("");
        shortItemsCompanySpinner.setValue("1");
        try
        {
            for(int i = 0; i< shortItemsByCompanyShortMedicineTableData.size();i++)
            {
                for(int j = 0; j < shortItemsByCompanyShortMedicineTableData.get(0).size();j++)
                {
                    shortItemsByCompanyShortItemsTable.setValueAt(shortItemsByCompanyShortMedicineTableData.get(i).get(j), i, j);
                }
            }
            shortItemsByCompanyShortMedicineTableData.clear();
        }catch(Exception ex)
        {
                ex.printStackTrace();
        }
    }

    private void clearTotalMedicineSaleJFrame() {
        totalMedicineSaleMedicineNameTextField.setText("");

        Date mDate = new Date();
        totalMedicineSaleStartDateTextField.setDate(mDate);
        totalMedicineSaleEndDateTextField.setDate(mDate);

        for(int i = 0; i< totalMedicineSaleTableData.size();i++)
        {
            for(int j = 0; j < totalMedicineSaleTableData.get(0).size();j++)
            {
                totalmedicineSaleTable.setValueAt("", i, j);
            }
        }
        totalMedicineSaleTableData.clear();
        totalMedicineSaleTextField.setText("");
        totalMedicineSaleNetProfitTextField.setText("");
    }

    private void clearTotalCompanySaleJFrame() {
        companySalesDistributorTextField.setText("");
        companySalesCompanyTextField.setText("");
        companySalesMedicineNameTextField.setText("");
        
        for(int i = 0; i< totalCompanySaleTableData.size();i++)
        {
            for(int j = 0; j < totalCompanySaleTableData.get(0).size();j++)
            {
                totalCompanySaleTable.setValueAt("", i, j);
            }
        }
        totalCompanySaleTableData.clear();
        companySalesTotalSalesTextField.setText("");
        companySalesNetProfitTextField.setText("");
    }

    private void clearTotalSalesmanSalesJFrame() {
        totalSalesmanSaleSalemanNameTextField.setText("");
        Date mDate = new Date();
        totalSalesmanSaleStartDateTextField.setDate(mDate);
        totalSalemanSalesEndDateTextField.setDate(mDate);
        totalSalemanSaleTotalSalesTextField.setText("");
        for(int i = 0; i< totalSalesManSalesTableData.size();i++)
        {
            for(int j = 0; j < totalSalesManSalesTableData.get(0).size();j++)
            {
                totalSalesManSaleTable.setValueAt("", i, j);
            }
        }

        totalSalesManSalesTableData.clear();
    }

    private void clearTotalReturnsByDateJFrame() {
        Date mDate = new Date();
        totalReturnsStartDateTextField.setDate(mDate);
        totalReturnsEndDateTextField.setDate(mDate);
        for(int i = 0; i< totalReturnsByDateRangeTableData.size();i++)
        {
            for(int j = 0; j < totalReturnsByDateRangeTableData.get(0).size();j++)
            {
                totalReturnsByDateReturnsTable.setValueAt("", i, j);
            }
        }

        totalReturnsByDateRangeTableData.clear();
    }

    private void clearTotalReturnsByMedicineJFrame() {
        Date mDate = new Date();
        totalReturnsByMedicineStartDateTextField.setDate(mDate);
        totalReturnsByMedicineEndDateTextField.setDate(mDate);

        try
        {
            for(int i = 0; i< totalReturnsByMedicineReturnsTableData.size();i++)
            {
                for(int j = 0; j < totalReturnsByMedicineReturnsTableData.get(0).size();j++)
                {
                    totalReturnsByMedicineReturnsTable.setValueAt("", i, j);
                }
            }
        }catch(Exception ex)
        {
                ex.printStackTrace();
        }
        totalReturnsByMedicineReturnsTableData.clear();
    }

    private void clearTotalReturnsByCompanyJFrame() {
        totalReturnsByCompanyDistributorNameTextField.setText("");
        totalReturnsByCompanyCompanyNameTextField.setText("");
        totalReturnsByCompanyMedicineNameTextField.setText("");
        totalReturnsByCompanyReturnsTextField.setText("");

        try
        {
            for(int i = 0; i< totalReturnsByCompanyReturnsTableData.size();i++)
            {
                for(int j = 0; j < totalReturnsByCompanyReturnsTableData.get(0).size();j++)
                {
                    totalReturnsByCompanyReturnsTable.setValueAt("", i, j);
                }
            }
        }catch(Exception ex)
        {
                ex.printStackTrace();
        }
        totalReturnsByCompanyReturnsTableData.clear();
    }

    private void clearTotalReturnsSalesManJFrame() {
        totalReturnsBySalesmanNameTextField.setText("");

        Date mDate = new Date();
        totalReturnsBySalesManStartDateTextField.setDate(mDate);
        totalReturnsBySalesmanEndDateTextField.setDate(mDate);

        try
        {
            for(int i = 0; i< totalReturnsBySalesmanTableData.size();i++)
            {
                for(int j = 0; j < totalReturnsBySalesmanTableData.get(0).size();j++)
                {
                    totalReturnsBySalesmanTable.setValueAt("", i, j);
                }
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        totalReturnsBySalesmanTableData.clear();
        totalReturnsBySalesmanReturnTextField.setText("");
    }

    private void clearShortItemsOverallJFrame() {
        shortItemsOverallSpinner.setValue("1");
        try
        {
            for(int i = 0; i< shortItemsOverallTableData.size();i++)
            {
                for(int j = 0; j < shortItemsOverallTableData.get(0).size();j++)
                {
                    shortItemsOverAllMedicineTable.setValueAt("", i, j);
                }
            }
        }catch(Exception ex)
        {
                ex.printStackTrace();
        }

        shortItemsOverallTableData.clear();
    }

    private void clearShortItemsOnRackJFrame() {
        shortItemsRackNoTextField.setText("");
        shortItemsOnRackDistributorNameTextField.setText("");
        shortItemsOnRackCompanyNameTextField.setText("");

        try
        {
            for(int i = 0; i< shortItemsOnRackTableData.size();i++)
            {
                for(int j = 0; j < shortItemsOnRackTableData.get(0).size();j++)
                {
                    shortItemsOnRackTable.setValueAt("", i, j);
                }
            }
        }catch(Exception ex)
        {
                ex.printStackTrace();
        }

        shortItemsOnRackTableData.clear();
    }

    private void clearAddUserGroupJFrame() {
        addUserGroupUserGroupNameTextField.setText("");
        addUserGroupPrivilesgesList.removeAll();
    }

    private void clearAddUserJFrame() {
        addUserUserNameTextField.setText("");
        addUserUserLoginNameTextField.setText("");
        addUserUserPasswordTextField.setText("");
        addUserReenterUserPasswordTextField.setText("");
        addUserUserGroups.removeAllItems();
    }

    private void clearRemoveUserJFrame() {
        usersToRemoveCombo.removeAll();
    }

    private void intializeMedicineDropDownString() {
        if(null != searchable)
        {
            searchable = null;
            searchable = new StringSearchable(medicineNameTerms, genericNamesTerms, companyNamesTerms);
        }
        else
        {
            searchable = new StringSearchable(medicineNameTerms, genericNamesTerms, companyNamesTerms);
        }

        medicineNameDropDownField.setSearchable(searchable);
        updateStockMedicineNameDropDownField.setSearchable(searchable);
    }

    private void constructSearchableTermList() {
        medicineNameTerms.clear();
        genericNamesTerms.clear();
        companyNamesTerms.clear();

        Set<String> keyset = SaadMedicineSaleSystem.medicineList.keySet();
        for(String medicineName:keyset)
        {
            Medicine medicineObject = SaadMedicineSaleSystem.medicineList.get(medicineName);
            medicineNameTerms.add(medicineName+ " [ " + medicineObject.genericName + " ]" + " [ " + medicineObject.companyName + " ]");
            genericNamesTerms.add(medicineObject.genericName + " [ " + medicineObject.medicineName + " ] [ " + medicineObject.companyName + " ]");
            companyNamesTerms.add(medicineObject.companyName+ " [ " + medicineObject.medicineName + " ] [ " + medicineObject.genericName + " ]");
        }
    }

    private void cleartotalSalesInDateRangeReceiptTable() 
    {
        for(int i = 0; i< totalSalesInDateRangeReceiptTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalSalesInDateRangeReceiptTable.getColumnCount();j++)
            {
                totalSalesInDateRangeReceiptTable.setValueAt("", i, j);
            }
        }
    }

    private void clearTotalCompanySaleTable() {
        for(int i = 0; i< totalCompanySaleTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalCompanySaleTable.getColumnCount();j++)
            {
                totalCompanySaleTable.setValueAt("", i, j);
            }
        }
    }

    private void clearTotalmedicineSaleTable() {
        for(int i = 0; i< totalmedicineSaleTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalmedicineSaleTable.getColumnCount();j++)
            {
                totalmedicineSaleTable.setValueAt("", i, j);
            }
        }
    }

    private void clearTotalSalesManSaleTable() {
        for(int i = 0; i< totalSalesManSaleTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalSalesManSaleTable.getColumnCount();j++)
            {
                totalSalesManSaleTable.setValueAt("", i, j);
            }
        }
    }

    private void clearTotalReturnsByDateReturnsTable() {
        for(int i = 0; i< totalReturnsByDateReturnsTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalReturnsByDateReturnsTable.getColumnCount();j++)
            {
                totalReturnsByDateReturnsTable.setValueAt("", i, j);
            }
        }
    }

    private void clearTotalReturnsByMedicineReturnsTable() {
        for(int i = 0; i< totalReturnsByMedicineReturnsTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalReturnsByMedicineReturnsTable.getColumnCount();j++)
            {
                totalReturnsByMedicineReturnsTable.setValueAt("", i, j);
            }
        }
    }

    private void clearTotalReturnsByCompanyReturnsTable() {
        for(int i = 0; i< totalReturnsByCompanyReturnsTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalReturnsByCompanyReturnsTable.getColumnCount();j++)
            {
                totalReturnsByCompanyReturnsTable.setValueAt("", i, j);
            }
        }
    }

    private void clearTotalReturnsBySalesmanTable() {
        for(int i = 0; i< totalReturnsBySalesmanTable.getRowCount();i++)
        {
            for(int j = 0 ; j < totalReturnsBySalesmanTable.getColumnCount();j++)
            {
                totalReturnsBySalesmanTable.setValueAt("", i, j);
            }
        }
    }

    private void clearShortItemsOverAllMedicineTable() {
        for(int i = 0; i< shortItemsOverAllMedicineTable.getRowCount();i++)
        {
            for(int j = 0 ; j < shortItemsOverAllMedicineTable.getColumnCount();j++)
            {
                shortItemsOverAllMedicineTable.setValueAt("", i, j);
            }
        }
    }

    private void clearShortItemsByCompanyShortItemsTable() {
        for(int i = 0; i< shortItemsByCompanyShortItemsTable.getRowCount();i++)
        {
            for(int j = 0 ; j < shortItemsByCompanyShortItemsTable.getColumnCount();j++)
            {
                shortItemsByCompanyShortItemsTable.setValueAt("", i, j);
            }
        }
    }

    private void clearShortItemsOnRackTable() {
        for(int i = 0; i< shortItemsOnRackTable.getRowCount();i++)
        {
            for(int j = 0 ; j < shortItemsOnRackTable.getColumnCount();j++)
            {
                shortItemsOnRackTable.setValueAt("", i, j);
            }
        }
    }

    public static void generateTableModelForTableData(JTable table, ArrayList<ArrayList<String>> data) {
        switch(table.getName())
        {
            case "shortItemsOnRackTable":
                generateShortItemsOnRackTable(table, data);
                break;
            case "shortItemsOverAllMedicineTable":
                generateShortItemsOverAllMedicineTable(table, data);
                break;
            case "shortItemsByCompanyShortItemsTable":
                generateShortItemsByCompanyShortItemsTable(table, data);
                break;
            case "receiptTable":
                generateReceiptTable(table, data);
                break;
            case "totalSalesInDateRangeReceiptTable":
                generateTotalSalesInDateRangeReceiptTable(table, data);
                break;
            case "totalmedicineSaleTable":
                generateTotalmedicineSaleTable(table, data);
                break;
            case "totalCompanySaleTable":
                generateTotalCompanySaleTable(table, data);
                break;
            case "totalSalesManSaleTable":
                generateTotalSalesManSaleTable(table, data);
                break;
            case "totalReturnsByDateReturnsTable":
                generateTotalReturnsByDateReturnsTable(table, data);
                break;
            case "totalReturnsByMedicineReturnsTable":
                generateTotalReturnsByMedicineReturnsTable(table, data);
                break;
            case "totalReturnsByCompanyReturnsTable":
                generateTotalReturnsByCompanyReturnsTable(table, data);
                break;
            case "totalReturnsBySalesmanTable":
                generateTotalReturnsBySalesmanTable(table, data);
                break;
        }
    }

    private static void generateShortItemsOnRackTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }    
    
    private static void generateShortItemsOverAllMedicineTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }    
    
    private static void generateTotalReturnsBySalesmanTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }    
    
    private static void generateTotalReturnsByCompanyReturnsTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }    
    
    private static void generateTotalReturnsByMedicineReturnsTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private static void generateTotalReturnsByDateReturnsTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Return ID", "Return Date", "Return Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private static void generateTotalSalesManSaleTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Sales ID", "Sales Date", "Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }    

    private static void generateTotalCompanySaleTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Sales ID", "Sales Date", "Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }    
    
    private static void generateTotalmedicineSaleTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];

        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Sales ID", "Sales Date", "Medicine Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    private static void generateShortItemsByCompanyShortItemsTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];

        // TODO : Find out if the objArray is to be initialized with null
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount"
            }
        )
        {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private static void generateTotalSalesInDateRangeReceiptTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];
        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Sales ID", "Sales Date", "Sales Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });        
    }

    private static void generateReceiptTable(JTable table, ArrayList<ArrayList<String>> data) {
        int rows = data.size();
        int cols = data.get(0).size();

        Object objArray[][] = new Object[rows][cols];

        table.setModel(new javax.swing.table.DefaultTableModel(
            objArray,
            new String [] {
                "Product Name", "Company Name", "Rack", "Price", "Discount", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private void clearButtonPressedOnForm() {
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                clearCurrentOnTopFrame(currentOnTopFrame);
            }
        });
    }

    private void clearEasyStockAdditionJFrame() {
        //To change body of generated methods, choose Tools | Templates.
        
    }
}
