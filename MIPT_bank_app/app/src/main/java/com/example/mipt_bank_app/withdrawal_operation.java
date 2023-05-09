package com.example.mipt_bank_app;


public class withdrawal_operation extends i_easy_money_operation {
    //public Withdrawal(BillDB trans) { this.trans_ = trans; }

    @Override
    public void execute_operation(String receiver_bill_id, String money_size, String type) {
        /*try {
            IBill receiver = trans_.billDeleteAndFind(receiver_bill_id);

            if (receiver.getCashSize() < money_size) {
                trans_.createBillQuery(receiver);
                throw new ArithmeticException("fuck you, lox!");
            }
            receiver.setCashSize(receiver.getCashSize() - money_size);

            trans_.createBillQuery(receiver);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }*/

    }

    @Override
    public void cancel_operation(String receiver_bill_id, String money_size) {
        /*Cursor receiver = trans_.getBill("" + receiver_bill_id);
        int money = Integer.parseInt(receiver.getString(3).trim()) + money_size;
        trans_.updateUserData(receiver.getString(0), receiver.getString(1), receiver.getString(2), "" + money);
    */}

//    private BillDB trans_;
}
