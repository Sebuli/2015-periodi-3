package AI;


import java.util.Comparator;
import java.util.Map;


/**
 * ValueComparator vertaa kahden mapin sisalla olevien stringien arvoja, niitten valuen perusteella
 * @author Sebbe
 */
public class ValueComparatorWhite implements Comparator<String> {

        Map<String, Double> base; 

        public ValueComparatorWhite(Map<String, Double> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) <= base.get(b)) {
                return -1;
            } else {
                return 1;
            } 
        }
    }