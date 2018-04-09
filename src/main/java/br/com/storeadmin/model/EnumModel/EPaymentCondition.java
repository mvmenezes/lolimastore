package br.com.storeadmin.model.EnumModel;

public enum EPaymentCondition {
    MONTHLY("M"),
    FORTNIGHTLY("F"),
    WEEKLY("W"),
    DAILY("D"),
    ONCE("N"),
    OTHER("O");

    EPaymentCondition(String s) {
    }
}
