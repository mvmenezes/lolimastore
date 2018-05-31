package br.com.storeadmin.business;

import java.util.List;

public abstract class BOModel<T> {



    public abstract List<T> importSheet(List<String> sheetLines);
}
