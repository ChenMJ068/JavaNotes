package c.mj.notes.structuralPatterns.facade;

import c.mj.notes.structuralPatterns.facade.impl.Pharmacy;
import c.mj.notes.structuralPatterns.facade.impl.Registration;
import c.mj.notes.structuralPatterns.facade.impl.SeeDoctor;

/**
 * @author ChenMJ
 * @version Hospitalproxy.class, v 0.1 2020/4/19 15:36  Exp$
 */
public class HospitalProxy {
    private Registration registration;
    private SeeDoctor seeDoctor;
    private Pharmacy pharmacy;

    public HospitalProxy() {
        this.registration = new Registration();
        this.seeDoctor = new SeeDoctor();
        this.pharmacy = new Pharmacy();
    }

    public void setRegistration(){
        registration.proxy();
    }
    public void setSeeDoctor(){
        seeDoctor.proxy();
    }
    public void setPharmacy(){
        pharmacy.proxy();
    }
}
