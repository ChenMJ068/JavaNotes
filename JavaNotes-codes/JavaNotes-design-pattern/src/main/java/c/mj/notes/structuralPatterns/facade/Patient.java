package c.mj.notes.structuralPatterns.facade;

/**
 * @author ChenMJ
 * @version Patient.class, v 0.1 2020/4/19 15:41  Exp$
 */
public class Patient {
    public static void main(String[] args) {
        HospitalProxy proxy = new HospitalProxy();

        proxy.setRegistration();
        proxy.setSeeDoctor();
        proxy.setPharmacy();
    }
}
