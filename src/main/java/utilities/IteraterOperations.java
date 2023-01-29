package utilities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class IteraterOperations {

    public Iterator<Object[]> combine_iteratorObject(Iterator<Object[]> itr1,Iterator<Object[]> itr2) {
        List<Object[]> final_list=new ArrayList<Object[]>();
        //System.out.println(itr2.)
        while(itr1.hasNext()) {
            ArrayList<Object> final_temp=new ArrayList<Object>();
            ArrayList<Object> temp=new ArrayList<Object>(Arrays.asList(itr1.next()));
            ArrayList<Object> second_temp=new ArrayList<Object>(Arrays.asList(itr2.next()));
            for(int i=0;i < temp.size();i++) {
                final_temp.add(temp.get(i));
            }
            for(int i=0;i < second_temp.size();i++) {
                final_temp.add(second_temp.get(i));
            }
            Object ob[]=final_temp.toArray();
            final_list.add(ob);

        }
        return final_list.iterator();
    }



}