package com.my.test;

public class Father {
    protected void tell() {
        System.out.println("我是父类tell方法！");
    }
}

class Son extends Father {
    // 1.子类default权限，父类tell方法是protected;子类覆盖的父类方法拥有了比父类原来的方法，有了更严格的访问控制
    // 这不符合覆盖的规则
    // void tell() {
    // 2.解决提高子类中覆盖方法的权限，
    // protected void tell() {
    public void tell() {
        System.out.println("我是子类的tell方法！");
    }

}
/**
 * @ 覆盖的注意事项：
 * 访问权限从严格到宽松顺序：
 * private default protected public
 */
