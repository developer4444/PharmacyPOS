/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Saad.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author Saad Pasha
 */
public class ItemChangeListener implements ItemListener{
    public String lastItem;
    public String dropDownName;

    ItemChangeListener(String comboName)
    {
        super();
        dropDownName = comboName;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {

       if (event.getStateChange() == ItemEvent.SELECTED) {
          String item = event.getItem().toString();
          MainFrame.medicineNameDropDownField.setPopupVisible(false);
         
          // do something with object
          if(item.contains("[") && !item.equals(lastItem))
          {
              lastItem = item;
              if(dropDownName.equals("medicineNameDropDownField"))
              {
                MainFrame.enterPressedOnReceiptFrame();
              }
              else if(dropDownName.equals("updateStockMedicineNameDropDownField"))
              {
                MainFrame.enterPressedOnUpdateStockFrame();
              }
          }
         else if(item.contains("[") && item.equals(lastItem))
          {
              lastItem = "";
              MainFrame.state = MainFrame.ITEM_SELECTED;
          }
//          else
//          {
//              // MainFrame.state = MainFrame.ITEM_SELECTED;
//               MainFrame.medicineNameDropDownField.setPopupVisible(false);
//          }
       }
    }       
}
