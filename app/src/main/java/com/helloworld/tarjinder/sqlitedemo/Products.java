package com.helloworld.tarjinder.sqlitedemo;


public class Products {

    private int _id;
    private String _productName;

    public Products() {

    }

    public Products(String productName) {
        this._productName = productName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public int get_id() {
        return _id;
    }

    public String get_productName() {
        return _productName;
    }
}
