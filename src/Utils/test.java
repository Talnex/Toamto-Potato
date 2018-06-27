package Utils;

import Beans.Potato;
import Potato.Potato_Upload;
import java.util.Date;
import java.util.List;

import Potato.Potato_Dowload;

public class test {
    public static void main(String[] args) {
//        Potato potato = new Potato(new Date(),false,1,"睡觉","睡觉","在宿舍睡");
//        System.out.println( Potato_Upload.insert(potato));
        List<Potato> potatos = Potato_Dowload.select();
        for (Potato a: potatos
             ) {
            System.out.println(a.toString());
        }

    }
}
