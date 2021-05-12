package fr.diginamic.recensement.services.check;

public class DepartementCheck {
    public static boolean departementcheck(int numDeprt) {
        for (int i = 1; i <= 98; i++) {
            if(numDeprt == i){
                return true;
            }
        }
        return false;
    }
}
