package c.mj.notes.enums;

/**
 * 自定义枚举类,在枚举上定义一些额外的API方法
 *
 * @author ChenMJ
 * @version Pizza.class, v 0.1 2020/4/14 10:51  Exp$
 */
public class Pizza {

    private PizzaStatus status;

    public enum PizzaStatus{
        ORDERED (5) {
            @Override
            public boolean isOrdered(){
                return true;
            }
        },
        READY (2){
            @Override
            public boolean isReady(){
                return true;
            }
        },
        DELIVERED (0){
            @Override
            public boolean isDelivered(){
                return true;
            }
        };

        private int timeToDelivery;

        PizzaStatus(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;

        }

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        public void setTimeToDelivery(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }

    }
    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        pizza.setStatus(Pizza.PizzaStatus.READY);
        System.out.println(pizza.isDeliverable());
    }
}
