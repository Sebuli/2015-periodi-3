/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Sebbe
 */
/**
 * ValueComparator vertaa kahden mapin sisalla olevien stringien arvoja, niitten valuen perusteella
 * @author Sebbe
 */
public class ValueComparatorBlack implements Comparator<String> {

        Map<String, Double> base; 

        public ValueComparatorBlack(Map<String, Double> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } 
        }
    }
