/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Saad.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pasha
 */
public class StringSearchable implements Searchable<String,String> {
    private List<String> medicineNameTerms = new ArrayList<String>();
    private List<String> genericNamesTerms = new ArrayList<String>();
    private List<String> companyNamesTerms = new ArrayList<String>();

    // Generic name to medicine name map
    private Map<String, List<String>> genericNameToMedicineNameCompanyNameMap;
    private Map<String, List<String>> companyNameToMedicineNameGenericNameMap;
    private Map<String, List<String>> medicineNameToGenericNameCompanyNameMap;

    /**
     * Constructs a new object based upon the parameter terms. 
     * @param medicineNames The inventory of terms to search.
     */
    public StringSearchable(List<String> medicineNames, List<String> genericNames, List<String> companyNames) {
        this.medicineNameTerms.addAll(medicineNames);
        this.genericNamesTerms.addAll(genericNames);
        this.companyNamesTerms.addAll(companyNames);

    }

    /**
     * Constructs a new object based upon the parameter terms. 
     * @param medicineNames The inventory of terms to search.
     */
    public StringSearchable(Map<String, List<String>> genericNameMap, Map<String, List<String>> companyNameMap, Map<String, List<String>> medicineNameMap) {
        this.genericNameToMedicineNameCompanyNameMap = genericNameMap;
        this.companyNameToMedicineNameGenericNameMap = companyNameMap;
        this.medicineNameToGenericNameCompanyNameMap = medicineNameMap;
    }

    @Override
    //public Collection<String> search(String value) {
    public Map<String, Integer> search(String value) {            

        boolean searchSingleCollection = true;

        /*
        List<String> medicineNamefounds = new ArrayList<String>();
        List<String> genericNamefounds = new ArrayList<String>();
        List<String> companyNamefounds = new ArrayList<String>();
        */
        //List<String> founds = new ArrayList<String>();
        Map<String, Integer> founds = new HashMap<String, Integer>();
        

        for ( String s : medicineNameTerms ){
                if ( s.indexOf(value) == 0 ){
                    founds.put(s, 1);
                        //founds.add(s);
                }
        }

        for ( String s : genericNamesTerms ){
                if ( s.indexOf(value) == 0 ){
                    founds.put(s, 2);
                }
        }

        for ( String s : companyNamesTerms ){
                if ( s.indexOf(value) == 0 ){
                        //founds.add(s);
                    founds.put(s, 3);
                }
        }

        return founds;
    }
}
